package net.cnam.chateau.entity.pet;

import net.cnam.chateau.structure.Stage;
import net.cnam.chateau.utils.Location;

public class PanPan extends Pet {

    private boolean power = true;

    public PanPan(Stage stage, Location location) {
        super(stage, location, "PanPan");
    }

    @Override
    public void power() {
        if (!power) {
            return;
        }
        //TODO détere un objet aléatoire rare
        power = false;
    }

    @Override
    public String scream() {
        return "Knknknkn";
    }
}
