package net.cnam.chateau.entity.pet;

import net.cnam.chateau.App;

public class ChatPotte extends Pet {
    private boolean power = true;

    /**
     * Constructeur
     * 
     * @param app L'application
     */
    public ChatPotte(App app) {
        super(app, "ChatPotte");
    }

    /**
     * Redéfinition de la méthode permettant au familier d'avoir un pouvoir et de l'utiliser.
     */
    @Override
    public void power() {
        if (!power) {
            return;
        }
        // TODO Vole l'arme de l'adversaire
        power = false;
    }
}
