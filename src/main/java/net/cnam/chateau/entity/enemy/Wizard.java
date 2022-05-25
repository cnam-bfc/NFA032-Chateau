package net.cnam.chateau.entity.enemy;

import java.util.Random;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.Console;
import net.cnam.chateau.structure.Stage;
import net.cnam.chateau.utils.Location;

/**
 * Classe d'une sorcière
 */
public class Wizard extends Enemy {

    /**
     * Constructeur permettant de créer un sorcier à partir de ceux en
     * énumération.
     *
     * @param app      l'application
     * @param stage    l'étage de la sorcière
     * @param location l'emplacement de l'entité dans l'étage
     * @param random   objet random associé à la seed du Château
     */
    public Wizard(App app, Stage stage, Location location, Random random) {
        // TODO Donner des caractéristiques random à la sorcière
        super(app, stage, location, "");
    }

    /**
     * Retourne le caractère à afficher pour un joueur
     *
     * @return Le caractère
     */
    @Override
    public String getCharacter() {
        return CColor.BRIGHT_MAGENTA + "W" + CColor.BRIGHT_MAGENTA.getForegroundReset();
    }
}
