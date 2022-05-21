package net.cnam.chateau.entity.pet;

import net.cnam.chateau.structure.Stage;
import net.cnam.chateau.utils.Location;

public class ChatPotte extends Pet {

    private boolean power = true;

    public ChatPotte(Stage stage, Location location) {
        super(stage, location, "ChatPotte");
    }

    @Override
    public void power() {
        if (!power) {
            return;
        }
        //TODO Vole l'arme de l'adversaire
        power = false;
    }

    @Override
    public String scream() {
        return "Miaou";
    }
}
