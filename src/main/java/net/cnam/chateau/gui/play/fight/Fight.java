package net.cnam.chateau.gui.play.fight;

import net.cnam.chateau.App;
import net.cnam.chateau.audio.Music;
import net.cnam.chateau.entity.Entity;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.entity.enemy.Enemy;
import net.cnam.chateau.entity.enemy.boss.Boss;
import net.cnam.chateau.entity.pet.Pet;
import net.cnam.chateau.game.EntityDeadException;
import net.cnam.chateau.gui.component.*;
import net.cnam.chateau.gui.play.EntityStats;
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
    private static final int ACCURACY = 30;

    private final App app;
    private final Player player;
    private final Entity enemy;
    private final boolean runAway;
    private final Random random;
    private State state = State.FIGHTING;
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
    public Fight(App app, Player player, Entity enemy, boolean runAway) {
        super(0, 0, "Combat");

        this.app = app;
        this.player = player;
        this.enemy = enemy;
        this.runAway = runAway;
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
        updateMenuButtons();

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
        if (state.equals(State.FINISHED)) {
            stop();
        } else
            // On vérifie si le combat est terminé
            if (state.equals(State.FIGHTING) && (player.isDead() || enemy.isDead())) {
                over = true;
                // Si l'ennemi est mort
                if (enemy.isDead()) {
                    // On actualise les stats du joueur
                    if (enemy instanceof Boss) {
                        app.getCurrentGame().getStatistic().setBossDefeated(true);
                    } else if (enemy instanceof Enemy) {
                        app.getCurrentGame().getStatistic().addAEnemyKill();
                    }

                    // On affiche le menu de butin
                    if (enemy.hasWeapon() || enemy.hasItem()) {
                        menu.getComponents().clear();
                        menu.getComponents().add(new CLabel("Appuyez sur\nune touche\npour le pilier..."));
                        menu.setHeight(3);
                        state = State.LOOTING;
                    } else {
                        // Quitter le combat
                        menu.getComponents().clear();
                        menu.getComponents().add(new CLabel("Appuyez sur\nune touche\npour continuer..."));
                        menu.setHeight(3);
                        state = State.FINISHED;
                    }
                } else {
                    // Quitter le combat
                    menu.getComponents().clear();
                    menu.getComponents().add(new CLabel("Appuyez sur\nune touche\npour continuer..."));
                    menu.setHeight(3);
                    state = State.FINISHED;
                }
            } else if (state.equals(State.LOOTING)) {
                app.getConsole().show(new LootMenu(app, player, enemy));
                playerStats.update();
                enemyStats.update();
                // Quitter le combat
                menu.getComponents().clear();
                menu.getComponents().add(new CLabel("Appuyez sur\nune touche\npour continuer..."));
                menu.setHeight(3);
                state = State.FINISHED;
            }

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

    public void updateMenuButtons() {
        menu.removeAll();
        menu.add(new AttackButton(app, this));
        if (player.hasItem() && player.getItem() instanceof Consumable consumable) {
            List<Entity> fightEntities = new LinkedList<>();
            if (!enemy.isDead()) {
                fightEntities.add(enemy);
            }
            if (!player.isDead()) {
                fightEntities.add(player);
            }
            if (player.hasPet() && !player.getPet().isDead()) {
                fightEntities.add(player.getPet());
            }
            menu.add(new UseItemButton(app, this, consumable, fightEntities));
        }
        if (runAway) {
            menu.add(new RunAwayButton(app, this));
        }
        menu.setLength(20);
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

        // Les entités s'attaquent
        if (player.hasPet()) {
            attackWithPet(logs);
        } else {
            attackWithoutPet(logs);
        }

        // On affiche les logs du combat
        this.logs.setHeight(logs.size());
        StringBuilder text = new StringBuilder();
        for (String log : logs) {
            text.append("\n").append(log);
        }
        this.logs.setText(text.toString().replaceFirst("\n", ""));
        this.setHeight(this.getHeight());

        // On actualise les stats
        playerStats.update();
        enemyStats.update();
        if (petStats != null) {
            petStats.update();
        }
    }

    private void attackWithPet(List<String> logs) {
        Pet pet = player.getPet();

        int playerSpeed = player.getSpeed();
        int enemySpeed = enemy.getSpeed();
        int petSpeed = pet.getSpeed();

        // Si le joueur est le premier à attaquer
        if (playerSpeed >= enemySpeed && playerSpeed >= petSpeed) {
            // Le joueur attaque l'ennemi
            if (attack(logs, player, enemy)) return;

            // Si l'ennemi est plus rapide que le pet
            if (enemySpeed >= petSpeed) {
                // L'ennemi attaque
                if (random.nextBoolean()) {
                    // L'ennemi attaque le joueur
                    if (attack(logs, enemy, player)) return;
                } else {
                    // L'ennemi attaque le pet
                    if (attack(logs, enemy, pet)) return;
                }

                // Le pet attaque l'ennemi
                attack(logs, pet, enemy);

                // Sinon si le pet est plus rapide que l'ennemi
            } else {
                // Le pet attaque l'ennemi
                if (attack(logs, pet, enemy)) return;

                // L'ennemi attaque
                if (random.nextBoolean()) {
                    // L'ennemi attaque le joueur
                    attack(logs, enemy, player);
                } else {
                    // L'ennemi attaque le pet
                    attack(logs, enemy, pet);
                }
            }

            // Sinon si l'ennemi est le premier à attaquer
        } else if (enemySpeed >= playerSpeed && enemySpeed >= petSpeed) {
            // L'ennemi attaque
            if (random.nextBoolean()) {
                // L'ennemi attaque le joueur
                if (attack(logs, enemy, player)) return;
            } else {
                // L'ennemi attaque le pet
                if (attack(logs, enemy, pet)) return;
            }

            // Si le joueur est plus rapide que le pet
            if (playerSpeed >= petSpeed) {
                // Le joueur attaque l'ennemi
                if (attack(logs, player, enemy)) return;

                // Le pet attaque l'ennemi
                attack(logs, pet, enemy);

                // Sinon si le pet est plus rapide que le joueur
            } else {
                // Le pet attaque l'ennemi
                if (attack(logs, pet, enemy)) return;

                // Le joueur attaque l'ennemi
                attack(logs, player, enemy);
            }

            // Sinon si le pet est le premier à attaquer
        } else {
            // Le pet attaque l'ennemi
            if (attack(logs, pet, enemy)) return;

            // Si le joueur est plus rapide que l'ennemi
            if (playerSpeed >= enemySpeed) {
                // Le joueur attaque l'ennemi
                if (attack(logs, player, enemy)) return;

                // L'ennemi attaque
                if (random.nextBoolean()) {
                    // L'ennemi attaque le joueur
                    attack(logs, enemy, player);
                } else {
                    // L'ennemi attaque le pet
                    attack(logs, enemy, pet);
                }

                // Sinon si l'ennemi est plus rapide que le joueur
            } else {
                // L'ennemi attaque
                if (random.nextBoolean()) {
                    // L'ennemi attaque le joueur
                    if (attack(logs, enemy, player)) return;
                } else {
                    // L'ennemi attaque le pet
                    if (attack(logs, enemy, pet)) return;
                }

                // Le joueur attaque l'ennemi
                attack(logs, player, enemy);
            }
        }
    }

    private void attackWithoutPet(List<String> logs) {
        // Si le joueur est le premier à attaquer
        if (player.getSpeed() >= enemy.getSpeed()) {
            // Le joueur attaque l'ennemi
            if (attack(logs, player, enemy)) return;

            // L'ennemi attaque le joueur
            attack(logs, enemy, player);

            // Sinon si l'ennemi est le premier à attaquer
        } else {
            // L'ennemi attaque le joueur
            if (attack(logs, enemy, player)) return;

            // Le joueur attaque l'ennemie
            attack(logs, player, enemy);
        }
    }

    /**
     * Méthode qui permet à une entité d'attaquer une autre entité.
     *
     * @param logs     Les logs
     * @param attacker L'entité qui attaque
     * @param attacked L'entité attaquée
     * @return true si le combat est fini, false sinon
     */
    private boolean attack(List<String> logs, Entity attacker, Entity attacked) {
        if (attacked.isDead() || attacker.isDead()) {
            return attacked == player || attacked == enemy;
        }
        if (canAttack(attacker)) {
            if (attacker == player) {
                logs.add("Vous avez infligé " + attacker.getStrength() + " dégâts à " + attacked.getName() + ".");
            } else if (attacked == player) {
                logs.add(attacker.getName() + " vous a infligé " + attacker.getStrength() + " dégâts.");
            } else {
                logs.add(attacker.getName() + " a infligé " + attacker.getStrength() + " dégâts à " + attacked.getName() + ".");
            }

            try {
                attacked.damage(attacker.getStrength());
            } catch (EntityDeadException e) {
                if (attacker == player) {
                    logs.add("Vous avez tué " + attacked.getName() + ".");
                } else if (attacked == player) {
                    logs.add(attacker.getName() + " vous a tué.");
                } else {
                    logs.add(attacker.getName() + " a tué " + attacked.getName() + ".");
                }
                return attacked == player || attacked == enemy;
            }
        } else {
            if (attacker == player) {
                logs.add("Vous avez raté votre attaque.");
            } else {
                logs.add(attacker.getName() + " a raté son attaque.");
            }
        }
        return false;
    }

    private boolean canAttack(Entity entity) {
        return random.nextInt(0, ACCURACY) < entity.getAccuracy();
    }

    private enum State {
        FIGHTING,
        LOOTING,
        FINISHED
    }
}
