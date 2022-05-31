package net.cnam.chateau.gui.play.fight;

import net.cnam.chateau.App;
import net.cnam.chateau.audio.Music;
import net.cnam.chateau.entity.Entity;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.entity.enemy.Enemy;
import net.cnam.chateau.entity.enemy.boss.Boss;
import net.cnam.chateau.game.EntityDeadException;
import net.cnam.chateau.gui.component.*;
import net.cnam.chateau.gui.play.fight.loot.LootMenu;
import net.cnam.chateau.item.consumable.Consumable;
import net.cnam.chateau.utils.audio.SimpleAudioPlayer;
import net.cnam.chateau.utils.direction.Orientation;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class Fight extends CFrame implements DisplayableComponent {
    private static final int ACCURACY = 20;

    private final App app;
    private final Player player;
    private final Entity enemy;
    private final Random random;
    private SimpleAudioPlayer audioPlayer;
    private boolean display = true;
    private boolean over = false;

    private final CPanel leftPanel;
    private final EntityStats playerStats;
    private final CPanel centerPanel;
    private final CChoices menu;
    private final CPanel rightPanel;
    private final EntityStats enemyStats;
    private final CLabel logs;

    private EntityStats petStats;

    // TODO Message quand perd, et à la fin du combat
    public Fight(App app, Player player, Entity enemy) {
        super(0, 0, "Combat");

        this.app = app;
        this.player = player;
        this.enemy = enemy;
        this.random = new Random();

        this.getContentPane().setRenderingMainPadding(false);
        this.getContentPane().setRenderingOrientation(Orientation.HORIZONTAL);

        this.leftPanel = new CPanel(0, 0);

        this.playerStats = new EntityStats(player, Orientation.VERTICAL);
        leftPanel.getComponents().add(playerStats);

        if (player.hasPet()) {
            this.petStats = new EntityStats(player.getPet(), Orientation.VERTICAL);
            leftPanel.getComponents().add(this.petStats);
        }

        this.getContentPane().getComponents().add(leftPanel);

        this.centerPanel = new CPanel(20, 0);

        this.menu = new CChoices(app, 1);
        menu.add(new AttackButton(app, this));
        if (player.hasItem() && player.getItem() instanceof Consumable) {
            menu.add(new UseItemButton(app, player.getItem()));
        }
        menu.add(new RunAwayButton(app, this));
        menu.setLength(20);

        centerPanel.getComponents().add(menu);
        this.getContentPane().getComponents().add(centerPanel);

        this.rightPanel = new CPanel(0, 0);
        this.enemyStats = new EntityStats(enemy, Orientation.VERTICAL);
        rightPanel.getComponents().add(enemyStats);
        this.getContentPane().getComponents().add(rightPanel);

        CPanel footer = new CPanel(0, 0, Orientation.HORIZONTAL, false);

        this.logs = new CLabel("");
        logs.setHeight(0);
        footer.getComponents().add(logs);

        this.setFooter(footer);

        try {
            this.audioPlayer = app.createAudioPlayer(Music.FIGHT);
            audioPlayer.setVolume(app.getSettings().getMusicVolume());
            audioPlayer.setLoop(true);
            audioPlayer.play();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException |
                 IllegalArgumentException ignored) {
        }
    }

    @Override
    public boolean isInLoopingMode() {
        return display;
    }

    @Override
    public boolean isInFullScreenMode() {
        return true;
    }

    public boolean isOver() {
        return over;
    }

    public void stop() {
        display = false;
        if (audioPlayer != null) {
            audioPlayer.stop();
        }
    }

    @Override
    public void setLength(int length) {
        super.setLength(length);
        int statsLength = this.getContentPane().getLength() - this.menu.getLength() - 2;
        this.leftPanel.setLength(statsLength / 2);
        this.playerStats.setLength(statsLength / 2);
        if (this.petStats != null) {
            this.petStats.setLength(statsLength / 2);
        }
        this.rightPanel.setLength(statsLength / 2);
        this.enemyStats.setLength(statsLength / 2);
        this.logs.setLength(this.getContentPane().getLength());
    }

    @Override
    public void setHeight(int height) {
        this.getFooter().setHeight(this.logs.getHeight());
        super.setHeight(height);
        this.leftPanel.setHeight(this.getContentPane().getHeight());
        this.centerPanel.setHeight(this.getContentPane().getHeight());
        this.rightPanel.setHeight(this.getContentPane().getHeight());
    }

    public void attack() {
        List<String> logs = new LinkedList<>();

        if (player.hasPet()) {
            attackWithPet(logs);
        } else {
            attackWithoutPet(logs);
        }

        // On affiche les logs
        this.logs.setHeight(logs.size());
        StringBuilder text = new StringBuilder();
        for (String log : logs) {
            text.append("\n").append(log);
        }
        this.logs.setText(text.toString().replaceFirst("\n", ""));
        this.setHeight(this.getHeight());

        playerStats.update();
        enemyStats.update();
        if (petStats != null) {
            petStats.update();
        }

        if (player.isDead() || enemy.isDead()) {
            over = true;
            display = false;
            // On affiche le menu de butin
            if (enemy.isDead()) {
                if (enemy instanceof Boss) {
                    app.getCurrentGame().getStatistics().setBossDefeated(true);
                } else if (enemy instanceof Enemy) {
                    app.getCurrentGame().getStatistics().addAEnemyKill();
                }
                if (enemy.hasWeapon() || enemy.hasItem()) {
                    menu.getComponents().clear();
                    menu.getComponents().add(new CLabel("Appuyez sur\nune touche\npour le pilier..."));
                    app.getConsole().show(this);
                    app.getConsole().show(new LootMenu(app, player, enemy));
                    playerStats.update();
                    enemyStats.update();
                }
            }
            menu.getComponents().clear();
            menu.getComponents().add(new CLabel("Appuyez sur\nune touche\npour continuer..."));
            app.getConsole().show(this);
            stop();
        }
    }

    private void attackWithPet(List<String> logs) {
        int playerSpeed = player.getSpeed();
        int playerStrength = player.getStrength();
        int enemySpeed = enemy.getSpeed();
        int enemyStrength = enemy.getStrength();
        int petSpeed = player.getPet().getSpeed();
        int petStrength = player.getPet().getStrength();

        // SI le joueur est le premier à attaquer
        if (playerSpeed > enemySpeed && playerSpeed > petSpeed) {
            if (attackIsSuccess(player)) {
                logs.add("Vous avez infligé " + playerStrength + " dégâts à " + enemy.getName() + ".");
                try {
                    enemy.damage(playerStrength);
                } catch (EntityDeadException e) {
                    logs.add("Vous avez tué " + enemy.getName() + ".");
                    return;
                }
            } else {
                logs.add("Vous avez raté votre attaque.");
            }
            // Comparaison Enemy et pet après l'attaque du joueur
            if (enemySpeed > petSpeed) {
                if (attackIsSuccess(enemy)) {
                    if (random.nextBoolean()) {
                        logs.add(enemy.getName() + " vous a infligé " + enemyStrength + " dégâts.");
                        try {
                            player.damage(enemyStrength);
                        } catch (EntityDeadException e) {
                            logs.add(enemy.getName() + " vous a tué.");
                            return;
                        }
                    } else {
                        logs.add(enemy.getName() + " a infligé " + enemyStrength + " dégâts à " + player.getPet().getName() + ".");
                        try {
                            player.getPet().damage(enemyStrength);
                        } catch (EntityDeadException ex) {
                            logs.add(enemy.getName() + " a tué " + player.getPet().getName() + ".");
                        }
                    }
                } else {
                    logs.add(enemy.getName() + " a raté son attaque.");
                }
                if (attackIsSuccess(player.getPet())) {
                    logs.add(player.getPet().getName() + " a infligé " + petStrength + " dégâts à " + enemy.getName() + ".");
                    try {
                        enemy.damage(petStrength);
                    } catch (EntityDeadException e) {
                        logs.add(player.getPet().getName() + " a tué " + enemy.getName() + ".");
                        return;
                    }
                } else {
                    logs.add(player.getPet().getName() + " a raté son attaque.");
                }
            } else {
                if (attackIsSuccess(player.getPet())) {
                    logs.add(player.getPet().getName() + " a infligé " + petStrength + " dégâts à " + enemy.getName() + ".");
                    try {
                        enemy.damage(petStrength);
                    } catch (EntityDeadException e) {
                        logs.add(player.getPet().getName() + " a tué " + enemy.getName() + ".");
                        return;
                    }
                } else {
                    logs.add(player.getPet().getName() + " a raté son attaque.");
                }
                if (attackIsSuccess(enemy)) {
                    if (random.nextBoolean()) {
                        logs.add(enemy.getName() + " vous a infligé " + enemyStrength + " dégâts.");
                        try {
                            player.damage(enemyStrength);
                        } catch (EntityDeadException e) {
                            logs.add(enemy.getName() + " vous a tué.");
                            return;
                        }
                    } else {
                        logs.add(enemy.getName() + " a infligé " + enemyStrength + " dégâts à " + player.getPet().getName() + ".");
                        try {
                            player.getPet().damage(enemyStrength);
                        } catch (EntityDeadException ex) {
                            logs.add(enemy.getName() + " a tué " + player.getPet().getName() + ".");
                        }
                    }
                } else {
                    logs.add(enemy.getName() + " a raté son attaque.");
                }
            }
            return; // return car on ne sait jamais si on met des malus, il ne faut pas plusieurs attack / round
        }

        // SI l'ennemie joueur est le premier à attaquer
        if (enemySpeed > playerSpeed && enemySpeed > petSpeed) {
            if (attackIsSuccess(enemy)) {
                if (random.nextBoolean()) {
                    logs.add(enemy.getName() + " vous a infligé " + enemyStrength + " dégâts.");
                    try {
                        player.damage(enemyStrength);
                    } catch (EntityDeadException e) {
                        logs.add(enemy.getName() + " vous a tué.");
                        return;
                    }
                } else {
                    logs.add(enemy.getName() + " a infligé " + enemyStrength + " dégâts à " + player.getPet().getName() + ".");
                    try {
                        player.getPet().damage(enemyStrength);
                    } catch (EntityDeadException ex) {
                        logs.add(enemy.getName() + " a tué " + player.getPet().getName() + ".");
                    }
                }
            } else {
                logs.add(enemy.getName() + " a raté son attaque.");
            }
            if (playerSpeed > petSpeed) {
                if (attackIsSuccess(player)) {
                    logs.add("Vous avez infligé " + playerStrength + " dégâts à " + enemy.getName() + ".");
                    try {
                        enemy.damage(playerStrength);
                    } catch (EntityDeadException e) {
                        logs.add("Vous avez tué " + enemy.getName() + ".");
                        return;
                    }
                } else {
                    logs.add("Vous avez raté votre attaque.");
                }
                if (attackIsSuccess(player.getPet())) {
                    logs.add(player.getPet().getName() + " a infligé " + petStrength + " dégâts à " + enemy.getName() + ".");
                    try {
                        enemy.damage(petStrength);
                    } catch (EntityDeadException e) {
                        logs.add(player.getPet().getName() + " a tué " + enemy.getName() + ".");
                        return;
                    }
                } else {
                    logs.add(player.getPet().getName() + " a raté son attaque.");
                }
            } else {
                if (attackIsSuccess(player.getPet())) {
                    logs.add(player.getPet().getName() + " a infligé " + petStrength + " dégâts à " + enemy.getName() + ".");
                    try {
                        enemy.damage(petStrength);
                    } catch (EntityDeadException e) {
                        logs.add(player.getPet().getName() + " a tué " + enemy.getName() + ".");
                        return;
                    }
                } else {
                    logs.add(player.getPet().getName() + " a raté son attaque.");
                }
                if (attackIsSuccess(player)) {
                    logs.add("Vous avez infligé " + playerStrength + " dégâts à " + enemy.getName() + ".");
                    try {
                        enemy.damage(playerStrength);
                    } catch (EntityDeadException e) {
                        logs.add("Vous avez tué " + enemy.getName() + ".");
                        return;
                    }
                } else {
                    logs.add("Vous avez raté votre attaque.");
                }
            }
            return; // return car on ne sait jamais si on met des malus, il ne faut pas plusieurs attack / round
        }

        // SI le pet est le premier à attaquer
        if (petSpeed > enemySpeed && petSpeed > playerSpeed) {
            if (attackIsSuccess(player.getPet())) {
                logs.add(player.getPet().getName() + " a infligé " + petStrength + " dégâts à " + enemy.getName() + ".");
                try {
                    enemy.damage(petStrength);
                } catch (EntityDeadException e) {
                    logs.add(player.getPet().getName() + " a tué " + enemy.getName() + ".");
                    return;
                }
            } else {
                logs.add(player.getPet().getName() + " a raté son attaque.");
            }
            if (playerSpeed > enemySpeed) {
                if (attackIsSuccess(player.getPet())) {
                    logs.add(player.getPet().getName() + " a infligé " + petStrength + " dégâts à " + enemy.getName() + ".");
                    try {
                        enemy.damage(petStrength);
                    } catch (EntityDeadException e) {
                        logs.add(player.getPet().getName() + " a tué " + enemy.getName() + ".");
                        return;
                    }
                } else {
                    logs.add(player.getPet().getName() + " a raté son attaque.");
                }
                if (attackIsSuccess(enemy)) {
                    if (random.nextBoolean()) {
                        logs.add(enemy.getName() + " vous a infligé " + enemyStrength + " dégâts.");
                        try {
                            player.damage(enemyStrength);
                        } catch (EntityDeadException e) {
                            logs.add(enemy.getName() + " vous a tué.");
                            return;
                        }
                    } else {
                        logs.add(enemy.getName() + " a infligé " + enemyStrength + " dégâts à " + player.getPet().getName() + ".");
                        try {
                            player.getPet().damage(enemyStrength);
                        } catch (EntityDeadException ex) {
                            logs.add(enemy.getName() + " a tué " + player.getPet().getName() + ".");
                        }
                    }
                } else {
                    logs.add(enemy.getName() + " a raté son attaque.");
                }
            } else {
                if (attackIsSuccess(enemy)) {
                    if (random.nextBoolean()) {
                        logs.add(enemy.getName() + " vous a infligé " + enemyStrength + " dégâts.");
                        try {
                            player.damage(enemyStrength);
                        } catch (EntityDeadException e) {
                            logs.add(enemy.getName() + " vous a tué.");
                            return;
                        }
                    } else {
                        logs.add(enemy.getName() + " a infligé " + enemyStrength + " dégâts à " + player.getPet().getName() + ".");
                        try {
                            player.getPet().damage(enemyStrength);
                        } catch (EntityDeadException ex) {
                            logs.add(enemy.getName() + " a tué " + player.getPet().getName() + ".");
                        }
                    }
                } else {
                    logs.add(enemy.getName() + " a raté son attaque.");
                }
                if (attackIsSuccess(player.getPet())) {
                    logs.add(player.getPet().getName() + " a infligé " + petStrength + " dégâts à " + enemy.getName() + ".");
                    try {
                        enemy.damage(petStrength);
                    } catch (EntityDeadException e) {
                        logs.add(player.getPet().getName() + " a tué " + enemy.getName() + ".");
                        return;
                    }
                } else {
                    logs.add(player.getPet().getName() + " a raté son attaque.");
                }
            }
            return; // return car on ne sait jamais si on met des malus, il ne faut pas plusieurs attack / round
        }
    }

    private void attackWithoutPet(List<String> logs) {
        int playerSpeed = player.getSpeed();
        int playerStrength = player.getStrength();
        int enemySpeed = enemy.getSpeed();
        int enemyStrength = enemy.getStrength();

        if (playerSpeed > enemySpeed) {
            if (attackIsSuccess(player)) {
                logs.add("Vous avez infligé " + playerStrength + " dégâts à " + enemy.getName() + ".");
                try {
                    enemy.damage(playerStrength);
                } catch (EntityDeadException e) {
                    logs.add("Vous avez tué " + enemy.getName() + ".");
                    return;
                }
            } else {
                logs.add("Vous avez raté votre attaque.");
            }
            if (attackIsSuccess(enemy)) {
                logs.add(enemy.getName() + " vous a infligé " + enemyStrength + " dégâts.");
                try {
                    player.damage(enemyStrength);
                } catch (EntityDeadException e) {
                    logs.add(enemy.getName() + " vous a tué.");
                    return;
                }
            } else {
                logs.add(enemy.getName() + " a raté son attaque.");
            }
        } else {
            if (attackIsSuccess(enemy)) {
                logs.add(enemy.getName() + " vous a infligé " + enemyStrength + " dégâts.");
                try {
                    player.damage(enemyStrength);
                } catch (EntityDeadException e) {
                    logs.add(enemy.getName() + " vous a tué.");
                    return;
                }
            } else {
                logs.add(enemy.getName() + " a raté son attaque.");
            }
            if (attackIsSuccess(player)) {
                logs.add("Vous avez infligé " + playerStrength + " dégâts à " + enemy.getName() + ".");
                try {
                    enemy.damage(playerStrength);
                } catch (EntityDeadException e) {
                    logs.add("Vous avez tué " + enemy.getName() + ".");
                    return;
                }
            } else {
                logs.add("Vous avez raté votre attaque.");
            }
        }
    }

    private boolean attackIsSuccess(Entity entity) {
        int Accuracy = entity.getAccuracy();
        return random.nextInt(0, ACCURACY) < Accuracy;
    }
}
