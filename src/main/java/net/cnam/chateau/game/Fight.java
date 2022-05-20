package net.cnam.chateau.game;

import java.util.Random;
import net.cnam.chateau.entity.LivingEntity;
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
    public void attack(Player player, LivingEntity enemy) {
        if (player.havePet()) {
            attackWithPet(player, enemy);
        } else {
            attackWithoutPet(player, enemy);
        }
    }

    public void attackWithPet(Player player, LivingEntity enemy) {
        int playerSpeed = player.getSpeed();
        int playerStrength = player.getSrength();
        int enemySpeed = enemy.getSpeed();
        int enemyStrength = enemy.getSrength();
        int petSpeed = player.getPet().getSpeed();
        int petStrength = player.getPet().getSrength();

        // SI le joueur est le premier à attaquer
        if (playerSpeed > enemySpeed && playerSpeed > petSpeed) {
            if (testAttack(player)) {
                enemy.getCharacteristics().setHealth(enemy.getCharacteristics().getHealth() - playerStrength);
            }
            if (enemySpeed > petSpeed) {
                if (testAttack(enemy)) {
                    if (random.nextBoolean()) {
                        player.getCharacteristics().setHealth(player.getCharacteristics().getHealth() - enemyStrength);
                    } else {
                        player.getPet().getCharacteristics().setHealth(player.getPet().getCharacteristics().getHealth() - enemyStrength);
                    }
                }
            } else {
                if (testAttack(player.getPet())) {
                    enemy.getCharacteristics().setHealth(enemy.getCharacteristics().getHealth() - petStrength);
                }
            }
            return; //return car on sait jamais si on met des malus faut pas plusieurs attack / round
        }

        // SI l'ennemie joueur est le premier à attaquer
        if (enemySpeed > playerSpeed && enemySpeed > petSpeed) {
            if (testAttack(enemy)) {
                if (random.nextBoolean()) {
                    player.getCharacteristics().setHealth(player.getCharacteristics().getHealth() - enemyStrength);
                } else {
                    player.getPet().getCharacteristics().setHealth(player.getPet().getCharacteristics().getHealth() - enemyStrength);
                }
            }
            if (playerSpeed > petSpeed) {
                if (testAttack(player)) {
                    enemy.getCharacteristics().setHealth(enemy.getCharacteristics().getHealth() - playerStrength);
                }
            } else {
                if (testAttack(player.getPet())) {
                    enemy.getCharacteristics().setHealth(enemy.getCharacteristics().getHealth() - petStrength);
                }
            }
            return; //return car on sait jamais si on met des malus faut pas plusieurs attack / round
        }

        // SI le pet est le premier à attaquer
        if (petSpeed > enemySpeed && petSpeed > playerSpeed) {
            if (playerSpeed > enemySpeed) {
                if (testAttack(player.getPet())) {
                    enemy.getCharacteristics().setHealth(enemy.getCharacteristics().getHealth() - petStrength);
                }
            } else {
                if (testAttack(enemy)) {
                    if (random.nextBoolean()) {
                        player.getCharacteristics().setHealth(player.getCharacteristics().getHealth() - enemyStrength);
                    } else {
                        player.getPet().getCharacteristics().setHealth(player.getPet().getCharacteristics().getHealth() - enemyStrength);
                    }
                }
            }
            return; //return car on sait jamais si on met des malus faut pas plusieurs attack / round
        }
    }

    public void attackWithoutPet(Player player, LivingEntity enemy) {
        int playerSpeed = player.getSpeed();
        int playerStrength = player.getSrength();
        int enemySpeed = enemy.getSpeed();
        int enemyStrength = enemy.getSrength();

        if (playerSpeed > enemySpeed) {
            if (testAttack(player)) {
                enemy.getCharacteristics().setHealth(enemy.getCharacteristics().getHealth() - playerStrength);
            }
            if (testAttack(enemy)) {
                player.getCharacteristics().setHealth(player.getCharacteristics().getHealth() - enemyStrength);
            }
        } else {
            if (testAttack(enemy)) {
                player.getCharacteristics().setHealth(player.getCharacteristics().getHealth() - enemyStrength);
            }
            if (testAttack(player)) {
                enemy.getCharacteristics().setHealth(enemy.getCharacteristics().getHealth() - playerStrength);
            }
        }
    }

    public boolean testAttack(LivingEntity entity) {
        int Accuracy = entity.getAccuracy();
        return random.nextInt(0, ACCURACY) < Accuracy;
    }
}
