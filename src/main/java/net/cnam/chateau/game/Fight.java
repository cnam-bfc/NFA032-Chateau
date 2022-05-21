package net.cnam.chateau.game;

import java.util.Random;
import net.cnam.chateau.entity.Entity;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.entity.enemy.Enemy;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.DisplayableComponent;
import net.cnam.chateau.gui.event.key.KeyEvent;
import net.cnam.chateau.gui.event.key.KeyListener;

public class Fight extends CFrame implements DisplayableComponent, KeyListener {

    private static final int ACCURACY = 20;

    private final Player player;
    private final Enemy enemy;
    private final Random random;

    private boolean display = true;

    public Fight(Player player, Enemy enemy) {
        super(0, 0);

        this.player = player;
        this.enemy = enemy;
        this.random = new Random();
    }

    @Override
    public void onKeyPressed(KeyEvent event) {
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
        //texte Attaquer
        //utiliser un objet
        //fuire
    }

    //TODO voir pour gérer si une entité meurt
    public void attack() {
        if (player.hasPet()) {
            attackWithPet();
        } else {
            attackWithoutPet();
        }
    }

    private void attackWithPet() {
        int playerSpeed = player.getSpeed();
        int playerStrength = player.getStrength();
        int enemySpeed = enemy.getSpeed();
        int enemyStrength = enemy.getStrength();
        int petSpeed = player.getPet().getSpeed();
        int petStrength = player.getPet().getStrength();

        // SI le joueur est le premier à attaquer
        if (playerSpeed > enemySpeed && playerSpeed > petSpeed) {
            if (testAttack(player)) {
                enemy.damage(playerStrength);
            }
            if (enemySpeed > petSpeed) {
                if (testAttack(enemy)) {
                    if (random.nextBoolean()) {
                        player.damage(enemyStrength);
                    } else {
                        player.getPet().damage(enemyStrength);
                    }
                }
            } else {
                if (testAttack(player.getPet())) {
                    enemy.damage(petStrength);
                }
            }
            return; //return car on sait jamais si on met des malus faut pas plusieurs attack / round
        }

        // SI l'ennemie joueur est le premier à attaquer
        if (enemySpeed > playerSpeed && enemySpeed > petSpeed) {
            if (testAttack(enemy)) {
                if (random.nextBoolean()) {
                    player.damage(enemyStrength);
                } else {
                    player.getPet().damage(enemyStrength);
                }
            }
            if (playerSpeed > petSpeed) {
                if (testAttack(player)) {
                    enemy.damage(playerStrength);
                }
            } else {
                if (testAttack(player.getPet())) {
                    enemy.damage(petStrength);
                }
            }
            return; //return car on sait jamais si on met des malus faut pas plusieurs attack / round
        }

        // SI le pet est le premier à attaquer
        if (petSpeed > enemySpeed && petSpeed > playerSpeed) {
            if (playerSpeed > enemySpeed) {
                if (testAttack(player.getPet())) {
                    enemy.damage(petStrength);
                }
            } else {
                if (testAttack(enemy)) {
                    if (random.nextBoolean()) {
                        player.damage(enemyStrength);
                    } else {
                        player.getPet().damage(enemyStrength);
                    }
                }
            }
            return; //return car on sait jamais si on met des malus faut pas plusieurs attack / round
        }
    }

    private void attackWithoutPet() {
        int playerSpeed = player.getSpeed();
        int playerStrength = player.getStrength();
        int enemySpeed = enemy.getSpeed();
        int enemyStrength = enemy.getStrength();

        if (playerSpeed > enemySpeed) {
            if (testAttack(player)) {
                enemy.damage(playerStrength);
            }
            if (testAttack(enemy)) {
                player.damage(enemyStrength);
            }
        } else {
            if (testAttack(enemy)) {
                player.damage(enemyStrength);
            }
            if (testAttack(player)) {
                enemy.damage(playerStrength);
            }
        }
    }

    public boolean testAttack(Entity entity) {
        int Accuracy = entity.getAccuracy();
        return random.nextInt(0, ACCURACY) < Accuracy;
    }
}
