package net.cnam.chateau.entity.pet;

public class Babe extends Pet {
    private boolean power = true;

    public Babe() {
        super("Babe");
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
