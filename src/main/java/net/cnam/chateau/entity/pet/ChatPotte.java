package net.cnam.chateau.entity.pet;

import net.cnam.chateau.entity.Player;

public class ChatPotte extends Pet {

    private boolean power = true;

    public ChatPotte(Player player) {
        super(player, "ChatPotte");
    }
    
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

    @Override
    public String scream() {
        return "Miaou";
    }
}
