package net.cnam.game;

import net.cnam.entity.enemy.Enemy;
import net.cnam.entity.*;
import net.cnam.gui.component.CFrame;
import net.cnam.gui.LoopDisplayableComponent;

public class Fight extends CFrame implements LoopDisplayableComponent {

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
    public boolean isDisplayable() {
        return display;
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
}
