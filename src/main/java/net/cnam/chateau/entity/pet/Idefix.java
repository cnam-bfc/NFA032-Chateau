package net.cnam.chateau.entity.pet;

public class Idefix extends Pet {
    private boolean power = true;

    public Idefix() {
        super("Idefix");
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
