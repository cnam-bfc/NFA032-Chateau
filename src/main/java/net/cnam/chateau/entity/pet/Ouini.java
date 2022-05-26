package net.cnam.chateau.entity.pet;

import net.cnam.chateau.App;

public class Ouini extends Pet {
    private boolean power = true;

    public Ouini(App app) {
        super(app, "Ouini");
    }

    @Override
    public void power() {
        if (!power) {
            return;
        }
        // TODO Restaure de la santé (miel)
        power = false;
    }
}
