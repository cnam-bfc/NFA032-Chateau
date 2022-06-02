package net.cnam.chateau.structure.block.container;

import net.cnam.chateau.App;
import net.cnam.chateau.item.Item;

/**
 * Class permettant de créer un block Coffre (Chest) pour la map.
 */
public class Chest extends Container {
    /**
     * Constructeur
     *
     * @param app L'application
     */
    public Chest(App app) {
        super(app, "Coffre", null);
    }

    /**
     * Constructeur
     *
     * @param app        L'application
     * @param hiddenItem L'item dans le coffre
     */
    public Chest(App app, Item hiddenItem) {
        super(app, "Coffre", hiddenItem);
    }

    /**
     * Redéfinition de la méthode permettant d'afficher le coffre sur la carte.
     *
     * @return Le caractère à afficher sur la carte
     */
    @Override
    public String getCharacter() {
        return super.getCharacter("C");
    }
}
