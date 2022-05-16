package net.cnam.chateau.game;

import net.cnam.chateau.entity.Player;
import net.cnam.chateau.entity.enemy.Enemy;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.DisplayableComponent;

public class Fight extends CFrame implements DisplayableComponent {

    private Player player;
    private Enemy enemy;
    private boolean display = true;

    public Fight(Player player, Enemy enemy) {
        super(0, 0);

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
}
