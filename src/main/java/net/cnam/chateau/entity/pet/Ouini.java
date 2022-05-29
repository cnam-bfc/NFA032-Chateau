package net.cnam.chateau.entity.pet;

import net.cnam.chateau.App;

public class Ouini extends Pet {
    private boolean power = true;

    /**
     * Constructeur
     * 
     * @param app L'application
     */
    public Ouini(App app) {
        super(app, "Ouini");
    }

    /**
     * Redéfinition de la méthode permettant au familier d'avoir un pouvoir et de l'utiliser.
     */
    @Override
    public void power() {
        if (!power) {
            return;
        }
        // TODO Restaure de la santé (miel)
        power = false;
    }
}
