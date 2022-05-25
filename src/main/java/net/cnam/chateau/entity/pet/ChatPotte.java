package net.cnam.chateau.entity.pet;

public class ChatPotte extends Pet {
    private boolean power = true;

    public ChatPotte() {
        super("ChatPotte");
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
