package net.cnam.chateau.entity.pet;

import net.cnam.chateau.App;

public class PepeLoiseau extends Pet {
    private boolean power = true;

    /**
     * Constructeur
     * 
     * @param app L'application
     */
    public PepeLoiseau(App app) {
        super(app, "Pepe Loiseau");
    }

    /**
     * Redéfinition de la méthode permettant au familier d'avoir un pouvoir et de l'utiliser.
     */
    @Override
    public void power() {
        if (!power) {
            return;
        }
        // TODO Dévoile les pièces autours de celles du joueur
        power = false;
    }
}
