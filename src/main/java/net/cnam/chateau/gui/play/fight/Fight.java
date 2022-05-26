package net.cnam.chateau.gui.play.fight;

import net.cnam.chateau.App;
import net.cnam.chateau.audio.Music;
import net.cnam.chateau.entity.Entity;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.game.EntityDeadException;
import net.cnam.chateau.gui.component.*;
import net.cnam.chateau.gui.play.EntityStats;
import net.cnam.chateau.utils.array.ArrayUtils;
import net.cnam.chateau.utils.audio.SimpleAudioPlayer;
import net.cnam.chateau.utils.direction.Orientation;

import javax.sound.sampled.LineUnavailableException;
import javax.sound.sampled.UnsupportedAudioFileException;
import java.io.IOException;
import java.util.Random;

public class Fight extends CFrame implements DisplayableComponent {
    private static final int ACCURACY = 20;

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

    // TODO Message quand perd, et à la fin du combat
    public Fight(App app, Player player, Entity enemy) {
        super(0, 0, "Combat");

        this.player = player;
        this.enemy = enemy;
        this.random = new Random();

        this.getContentPane().setRenderingMainPadding(false);
        this.getContentPane().setRenderingOrientation(Orientation.HORIZONTAL);

        this.leftPanel = new CPanel(0, 0);
        this.playerStats = new EntityStats(player);
        leftPanel.getComponents().add(playerStats);
        this.getContentPane().getComponents().add(leftPanel);

        this.centerPanel = new CPanel(20, 0);
        SelectableComponent[] components = new SelectableComponent[]{new AttackButton(app.getSettings(), this)};
        if (player.haveItem()) {
            components = ArrayUtils.addOnBottomOfArray(components, new UseItemButton(app.getSettings(), player.getItem()));
        }
        components = ArrayUtils.addOnBottomOfArray(components, new RunAwayButton(app.getSettings(), this));

        this.menu = new CChoices(app.getSettings(), components, 1);
        menu.setLength(20);
        centerPanel.getComponents().add(menu);
        this.getContentPane().getComponents().add(centerPanel);

        this.rightPanel = new CPanel(0, 0);
        this.enemyStats = new EntityStats(enemy);
        rightPanel.getComponents().add(enemyStats);
        this.getContentPane().getComponents().add(rightPanel);

        CPanel footer = new CPanel(0, 0, Orientation.HORIZONTAL, false);

        this.logs = new CLabel("");
        logs.setHeight(0);
        footer.getComponents().add(logs);

        this.setFooter(footer);

        try {
            this.audioPlayer = new SimpleAudioPlayer(Music.FIGHT.getFilePath());
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
        this.rightPanel.setLength(statsLength / 2);
        this.enemyStats.setLength(statsLength / 2);
    }

    @Override
    public void setHeight(int height) {
        this.getFooter().setHeight(this.logs.getHeight());
        super.setHeight(height);
        this.leftPanel.setHeight(this.getContentPane().getHeight());
        this.centerPanel.setHeight(this.getContentPane().getHeight());
        this.rightPanel.setHeight(this.getContentPane().getHeight());
    }

    public boolean isOver() {
        return over;
    }

    public void attack() {
        try {
            if (player.hasPet()) {
                attackWithPet();
            } else {
                attackWithoutPet();
            }
        } catch (EntityDeadException e) {
            over = true;
            stop();
        }
    }

    private void attackWithPet() throws EntityDeadException {
        int playerSpeed = player.getSpeed();
        int playerStrength = player.getStrength();
        int enemySpeed = enemy.getSpeed();
        int enemyStrength = enemy.getStrength();
        int petSpeed = player.getPet().getSpeed();
        int petStrength = player.getPet().getStrength();

        // SI le joueur est le premier à attaquer
        if (playerSpeed > enemySpeed && playerSpeed > petSpeed) {
            if (attackIsSuccess(player)) {
                enemy.damage(playerStrength);
            }
            if (enemySpeed > petSpeed) {
                if (attackIsSuccess(enemy)) {
                    if (random.nextBoolean()) {
                        player.damage(enemyStrength);
                    } else {
                        try {
                            player.getPet().damage(enemyStrength);
                        } catch (EntityDeadException ignored) {
                        }
                    }
                }
            } else {
                if (attackIsSuccess(player.getPet())) {
                    enemy.damage(petStrength);
                }
            }
            return; // return car on ne sait jamais si on met des malus, il ne faut pas plusieurs attack /
            // round
        }

        // SI l'ennemie joueur est le premier à attaquer
        if (enemySpeed > playerSpeed && enemySpeed > petSpeed) {
            if (attackIsSuccess(enemy)) {
                if (random.nextBoolean()) {
                    player.damage(enemyStrength);
                } else {
                    try {
                        player.getPet().damage(enemyStrength);
                    } catch (EntityDeadException ignored) {
                    }
                }
            }
            if (playerSpeed > petSpeed) {
                if (attackIsSuccess(player)) {
                    enemy.damage(playerStrength);
                }
            } else {
                if (attackIsSuccess(player.getPet())) {
                    enemy.damage(petStrength);
                }
            }
            return; // return car on ne sait jamais si on met des malus, il ne faut pas plusieurs attack /
            // round
        }

        // SI le pet est le premier à attaquer
        if (petSpeed > enemySpeed && petSpeed > playerSpeed) {
            if (playerSpeed > enemySpeed) {
                if (attackIsSuccess(player.getPet())) {
                    enemy.damage(petStrength);
                }
            } else {
                if (attackIsSuccess(enemy)) {
                    if (random.nextBoolean()) {
                        player.damage(enemyStrength);
                    } else {
                        try {
                            player.getPet().damage(enemyStrength);
                        } catch (EntityDeadException ignored) {
                        }
                    }
                }
            }
            return; // return car on ne sait jamais si on met des malus, il ne faut pas plusieurs attack /
            // round
        }
    }

    private void attackWithoutPet() throws EntityDeadException {
        int playerSpeed = player.getSpeed();
        int playerStrength = player.getStrength();
        int enemySpeed = enemy.getSpeed();
        int enemyStrength = enemy.getStrength();

        if (playerSpeed > enemySpeed) {
            if (attackIsSuccess(player)) {
                enemy.damage(playerStrength);
            }
            if (attackIsSuccess(enemy)) {
                player.damage(enemyStrength);
            }
        } else {
            if (attackIsSuccess(enemy)) {
                player.damage(enemyStrength);
            }
            if (attackIsSuccess(player)) {
                enemy.damage(playerStrength);
            }
        }
    }

    private boolean attackIsSuccess(Entity entity) {
        int Accuracy = entity.getAccuracy();
        return random.nextInt(0, ACCURACY) < Accuracy;
    }
}
