package net.cnam.chateau.entity.pet;

import net.cnam.chateau.App;

public class PepeLoiseau extends Pet {
    private boolean power = true;

    public PepeLoiseau(App app) {
        super(app, "Pepe Loiseau");
    }

    @Override
    public void power() {
        if (!power) {
            return;
        }
        // TODO Dévoile les pièces autours de celles du joueur
        power = false;
    }
}
