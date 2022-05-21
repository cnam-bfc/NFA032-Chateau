package net.cnam.chateau.entity.pet;

import net.cnam.chateau.structure.Stage;
import net.cnam.chateau.utils.Location;

public class Ouini extends Pet {

    private boolean power = true;

    public Ouini(Stage stage, Location location) {
        super(stage, location, "Ouini");
    }

    @Override
    public void power() {
        if (!power) {
            return;
        }
        //TODO Restaure de la santé (miel)
        power = false;
    }

    @Override
    public String scream() {
        return "Rrrrr";
    }
}
