package net.cnam.chateau.entity.pet;

import net.cnam.chateau.App;

public class PanPan extends Pet {
    private boolean power = true;

    /**
     * Constructeur
     * 
     * @param app L'application
     */
    public PanPan(App app) {
        super(app, "PanPan");
    }

    /**
     * Redéfinition de la méthode permettant au familier d'avoir un pouvoir et de l'utiliser.
     */
    @Override
    public void power() {
        if (!power) {
            return;
        }
        // TODO détere un objet aléatoire rare
        power = false;
    }
}
