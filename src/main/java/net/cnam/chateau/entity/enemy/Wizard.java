package net.cnam.chateau.entity.enemy;

import java.util.Random;
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
     * @param console  La console
     * @param stage    l'étage de la sorcière
     * @param location l'emplacement de l'entité dans l'étage
     * @param random   objet random associé à la seed du Château
     */
    public Wizard(Console console, Stage stage, Location location, Random random) {
        // TODO Donner des caractéristiques random à la sorcière
        super(console, stage, location, "");

        WizardEnum type = WizardEnum.values()[random.nextInt(WizardEnum.values().length)];
        this.setName(type.getName());
        if (type.getWeapon() != null) {
            this.setWeapon(type.getWeapon());
        }
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
