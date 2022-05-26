package net.cnam.chateau.entity.pet;

import net.cnam.chateau.App;

public class ChatPotte extends Pet {
    private boolean power = true;

    public ChatPotte(App app) {
        super(app, "ChatPotte");
    }

    @Override
    public void power() {
        if (!power) {
            return;
        }
        // TODO Vole l'arme de l'adversaire
        power = false;
    }
}
