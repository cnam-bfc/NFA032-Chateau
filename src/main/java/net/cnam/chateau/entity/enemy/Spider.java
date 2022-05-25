package net.cnam.chateau.entity.enemy;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.Console;
import net.cnam.chateau.structure.Stage;
import net.cnam.chateau.utils.Location;

/**
 * Classe d'une arraignée
 */
public class Spider extends Enemy {

    /**
     * Constructeur
     *
     * @param app      L'application
     * @param stage    L'étage où se situe l'arraignée
     * @param location Coordonnées de l'arraignée
     */
    public Spider(App app, Stage stage, Location location) {
        super(app, stage, location, "Maurice");
    }

    /**
     * Retourne le caractère à afficher pour un joueur
     *
     * @return Le caractère
     */
    @Override
    public String getCharacter() {
        return CColor.BRIGHT_RED + "S" + CColor.BRIGHT_RED.getForegroundReset();
    }
}
