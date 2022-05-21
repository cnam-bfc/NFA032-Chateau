package net.cnam.chateau.entity.pet;

import net.cnam.chateau.structure.Stage;
import net.cnam.chateau.utils.Location;

public class Idefix extends Pet {

    private boolean power = true;

    public Idefix(Stage stage, Location location) {
        super(stage, location, "Idefix");
    }

    @Override
    public void power() {
        if (!power) {
            return;
        }
        //TODO Augmente temporairement la force (potion dans asterix et obelix) 
        power = false;
    }

    @Override
    public String scream() {
        return "Ouaf";
    }
}
