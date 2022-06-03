package net.cnam.chateau.entity.pet;

import net.cnam.chateau.App;

public class PanPan extends Pet {
    private boolean power = true;

    /**
     * Constructeur
     * 
     * @param app L'application
     */
    public PanPan(App app, int health, int strength, int accuracy, int speed) {
        super(app, "PanPan", health, strength, accuracy, speed);
    }

    /**
     * Redéfinition de la méthode permettant au familier d'avoir un pouvoir et de l'utiliser.
     */
    @Override
    public void power() {
        if (!power) {
            return;
        }
        // TODO Déterre un objet aléatoire rare
        power = false;
    }
}
