package net.cnam.chateau.entity.pet;

import net.cnam.chateau.App;

public class Idefix extends Pet {
    private boolean power = true;

    /**
     * Constructeur
     * 
     * @param app L'application
     */
    public Idefix(App app) {
        super(app, "Idefix");
    }

    /**
     * Redéfinition de la méthode permettant au familier d'avoir un pouvoir et de l'utiliser.
     */
    @Override
    public void power() {
        if (!power) {
            return;
        }
        // TODO Augmente temporairement la force (potion dans asterix et obelix)
        power = false;
    }
}
