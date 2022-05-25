package net.cnam.chateau.entity.pet;

public class Ouini extends Pet {
    private boolean power = true;

    public Ouini() {
        super("Ouini");
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
