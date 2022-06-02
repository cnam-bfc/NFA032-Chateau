package net.cnam.chateau.structure.block.container;

import net.cnam.chateau.App;
import net.cnam.chateau.item.Item;

public class Wardrobe extends Container {
    /**
     * Constructeur
     *
     * @param app l'application
     */
    public Wardrobe(App app) {
        super(app, "Armoire", null);
    }

    /**
     * Constructeur
     *
     * @param app        L'application
     * @param hiddenItem L'objet à placer dans l'armoire
     */
    public Wardrobe(App app, Item hiddenItem) {
        super(app, "Armoire", hiddenItem);
    }

    /**
     * Redéfinition de la méthode permettant de récupérer le caractère à afficher sur la carte.
     *
     * @return le caractère à afficher, ici un "W" (String)
     */
    @Override
    public String getCharacter() {
        return super.getCharacter("W");
    }
}
