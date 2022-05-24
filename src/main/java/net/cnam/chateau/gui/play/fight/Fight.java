package net.cnam.chateau.gui.play.fight;

import java.nio.channels.SelectableChannel;
import java.util.Random;

import net.cnam.chateau.entity.Entity;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.entity.enemy.Enemy;
import net.cnam.chateau.event.key.KeyPressedEvent;
import net.cnam.chateau.game.EntityDeadException;
import net.cnam.chateau.gui.component.CChoices;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.CLabel;
import net.cnam.chateau.gui.component.DisplayableComponent;
import net.cnam.chateau.gui.component.SelectableComponent;
import net.cnam.chateau.utils.array.ArrayUtils;

public class Fight extends CFrame implements DisplayableComponent {

    private static final int ACCURACY = 20;

    private final Player player;
    private final Enemy enemy;
    private final Random random;

    private boolean display = true;

    public Fight(Player player, Enemy enemy) {
        super(new CLabel("Combat avec " + enemy.getName()), 0, 0);

        this.player = player;
        this.enemy = enemy;
        this.random = new Random();
    }

    @Override
    public void onKeyPressed(KeyPressedEvent event) {
        int key = event.getKey();

        // TODO Enlever ça, temporaire
        if (key == 13 || key == 10) {
            stopDisplaying();
            return;
        }

        // On transmet la touche appuyé aux composants dans cette fenêtre
        super.onKeyPressed(event);
    }

    @Override
    public boolean isInLoopingMode() {
        return display;
    }

    @Override
    public boolean isInFullScreenMode() {
        return true;
    }

    public void stopDisplaying() {
        display = false;
    }

    public void chooseAction() {
        this.getContentPane().getComponents().clear();

        SelectableComponent[] components = new SelectableComponent[] { new AttackButton() };
        if (player.haveItem()) {
            components = ArrayUtils.addOnBottomOfArray(components, new UseItemButton(player.getItem()));
        }
        components = ArrayUtils.addOnBottomOfArray(components, new RunAwayButton());

        CChoices choices = new CChoices(components, 1);

        this.getContentPane().getComponents().add(choices);
    }

    public void attack() {
        try {
            if (player.hasPet()) {
                attackWithPet();
            } else {
                attackWithoutPet();
            }
        } catch (EntityDeadException e) {
            stopDisplaying();
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
                        } catch (EntityDeadException e) {
                        }
                    }
                }
            } else {
                if (attackIsSuccess(player.getPet())) {
                    enemy.damage(petStrength);
                }
            }
            return; // return car on sait jamais si on met des malus faut pas plusieurs attack /
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
                    } catch (EntityDeadException e) {
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
            return; // return car on sait jamais si on met des malus faut pas plusieurs attack /
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
                        } catch (EntityDeadException e) {
                        }
                    }
                }
            }
            return; // return car on sait jamais si on met des malus faut pas plusieurs attack /
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
