package net.cnam.chateau.entity.pet;

public class PepeLoiseau extends Pet {
    private boolean power = true;

    public PepeLoiseau() {
        super("Pepe Loiseau");
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
