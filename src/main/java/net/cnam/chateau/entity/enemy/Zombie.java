package net.cnam.chateau.entity.enemy;

import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.Console;
import net.cnam.chateau.structure.Stage;
import net.cnam.chateau.utils.Location;

/**
 * Classe d'un zombie
 */
public class Zombie extends Enemy {

    /**
     * Constructeur
     *
     * @param console  La console
     * @param stage    L'étage où se situe le zombie
     * @param location Coordonnées du zombie
     */
    public Zombie(Console console, Stage stage, Location location) {
        super(console, stage, location, "Twilette");
    }

    /**
     * Retourne le caractère à afficher pour un joueur
     *
     * @return Le caractère
     */
    @Override
    public String getCharacter() {
        return CColor.BRIGHT_GREEN + "Z" + CColor.BRIGHT_GREEN.getForegroundReset();
    }
}
