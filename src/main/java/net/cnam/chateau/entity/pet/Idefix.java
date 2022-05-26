package net.cnam.chateau.entity.pet;

import net.cnam.chateau.App;

public class Idefix extends Pet {
    private boolean power = true;

    public Idefix(App app) {
        super(app, "Idefix");
    }

    @Override
    public void power() {
        if (!power) {
            return;
        }
        // TODO Augmente temporairement la force (potion dans asterix et obelix)
        power = false;
    }
}
