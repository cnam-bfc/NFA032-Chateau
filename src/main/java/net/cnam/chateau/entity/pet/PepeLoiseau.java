package net.cnam.chateau.entity.pet;

import net.cnam.chateau.structure.Stage;
import net.cnam.chateau.utils.Location;

public class PepeLoiseau extends Pet {

    private boolean power = true;

    public PepeLoiseau(Stage stage, Location location) {
        super(stage, location, "Pepe Loiseau");
    }

    @Override
    public void power() {
        if (!power) {
            return;
        }
        //TODO Dévoile les pièces autours de celles du joueur
        power = false;
    }

    @Override
    public String scream() {
        return "couic-couic";
    }
}
