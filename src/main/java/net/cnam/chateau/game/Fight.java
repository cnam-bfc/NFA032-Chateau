package net.cnam.chateau.game;

import net.cnam.chateau.entity.LivingEntity;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.entity.enemy.Enemy;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.DisplayableComponent;

public class Fight extends CFrame implements DisplayableComponent {

    private Player player;
    private Enemy enemy;
    private boolean display = true;

    public Fight(Player player, Enemy enemy) {
        this.player = player;
        this.enemy = enemy;
    }

    @Override
    public void onKeyPressed(int key) {
        // TODO Enlever ça, temporaire
        if (key == 13 || key == 10) {
            stopDisplaying();
            return;
        }

        // On transmet la touche appuyé aux composants dans cette fenêtre
        super.onKeyPressed(key);
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

    //le joueur choisie attaque ou fuite ou utiliser un objet
    // si le joueur attaque, le plus rapide attaque en 1er
    //une attaque peut échouer (précision)
    
    public void attack(Player player, LivingEntity enemy){
        if (player.havePet()){
            attackWithPet(player, enemy);
        }
        else {
            attackWithPet(player, enemy);
        }
    }
    
    public void attackWithPet(Player player, LivingEntity enemy){
        int playerSpeed = player.getSpeed();
        int playerStrength = player.getSrength();
        int playerAccuracy = player.getSrength();
        int enemySpeed = enemy.getSpeed();
        int enemyStrength = enemy.getSrength();
        int ennemyAccuracy = enemy.getAccuracy();
        int petSpeed = player.getPet().getSpeed();
        int petStrength = player.getPet().getSrength();
        int petAccuracy = player.getPet().getAccuracy();
        
        if (playerSpeed > enemySpeed && playerSpeed > petSpeed){
            //faire attaquer joueur
            if (enemySpeed > petSpeed){
                //faire attaquer l'ennemie entre le pet ou le joueur
                //faire attaquer le pet
            }
            else {
                //faire attaquer le pet
                //faire attaquer l'ennemie entre le pet ou le joueur
            }
            return; //return car on sait jamais si on met des malus faut pas plusieurs attack / round
        }
        
        if (enemySpeed > playerSpeed && enemySpeed > petSpeed){
            //faire attaquer l'ennemie entre le pet ou le joueur
            if (playerSpeed > petSpeed){
                //faire attaquer le joueur
                //faire attaquer le pet
            }
            else {
                //faire attaquer le pet
                //faire attaquer le joueur
            }
            return; //return car on sait jamais si on met des malus faut pas plusieurs attack / round
        }
        
        if (petSpeed > enemySpeed && petSpeed > playerSpeed){
            //faire attaquer le pet
            if (playerSpeed > enemySpeed){
                //faire attaquer le joueur
                //faire attaquer l'ennemie entre le pet ou le joueur
            }
            else {
                //faire attaquer l'ennemie entre le pet ou le joueur
                //faire attaquer le joueur
            }
            return; //return car on sait jamais si on met des malus faut pas plusieurs attack / round
        }
    }
    
    public void attackWithoutPet(Player player, LivingEntity enemy){
        int playerSpeed = player.getSpeed();
        int playerStrength = player.getSrength();
        int playerAccuracy = player.getSrength();
        int enemySpeed = enemy.getSpeed();
        int enemyStrength = enemy.getSrength();
        int ennemyAccuracy = enemy.getAccuracy();
        
        if (playerSpeed > enemySpeed){
            //vérifier si le joueur touche ennemie
            //si touche retirer pv enemie
            //même chose avec l'ennemie
        }
        else {
            //vérifier si l'ennemie touche le joueur
            //si touche retirer pv joueur
            //même chose avec joueur
        }
    }
}
