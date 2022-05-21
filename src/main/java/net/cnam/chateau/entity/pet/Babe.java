package net.cnam.chateau.entity.pet;

import net.cnam.chateau.structure.Stage;
import net.cnam.chateau.utils.Location;

public class Babe extends Pet {

    private boolean power = true;

    public Babe(Stage stage, Location location) {
        super(stage, location, "Babe");
    }

    @Override
    public void power() {
        if (!power) {
            return;
        }
        //TODO détruit un mur
        power = false;
    }

    @Override
    public String scream() {
        return "groin-groin";
    }
}
