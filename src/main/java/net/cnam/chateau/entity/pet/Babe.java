package net.cnam.chateau.entity.pet;

import net.cnam.chateau.App;

public class Babe extends Pet {
    private boolean power = true;

    public Babe(App app) {
        super(app, "Babe");
    }

    @Override
    public void power() {
        if (!power) {
            return;
        }
        // TODO détruit un mur
        power = false;
    }
}
