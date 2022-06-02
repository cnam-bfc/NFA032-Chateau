package net.cnam.chateau.entity.pet;

import net.cnam.chateau.App;

public class Ludo extends Pet {
    private boolean power = true;

    /**
     * Constructeur
     *
     * @param app L'application
     */
    public Ludo(App app) {
        super(app, "Super Ludo", 150, 25, 25, 25);
    }

    /**
     * Redéfinition de la méthode permettant au familier d'avoir un pouvoir et de l'utiliser.
     */
    @Override
    public void power() {
        if (!power) {
            return;
        }
        // TODO tue l'adversaire d'un coup
        power = false;
    }
}
