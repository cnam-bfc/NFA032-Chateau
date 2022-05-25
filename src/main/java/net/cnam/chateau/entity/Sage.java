package net.cnam.chateau.entity;

import java.util.LinkedList;
import java.util.List;
import net.cnam.chateau.game.event.Puzzle;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.structure.Stage;
import net.cnam.chateau.utils.Location;

public class Sage extends Entity {
    public static List<Sage> sages = new LinkedList<>();

    public static void initSages() {
        sages.add(new Sage(null, null, "Dumbledore"));
        sages.add(new Sage(null, null, "Merlin"));
        sages.add(new Sage(null, null, "Kristoff"));
        sages.add(new Sage(null, null, "Sage Gris"));
        sages.add(new Sage(null, null, "Salomon"));

        // TODO Si le fichier csv existe :
        //Boucler dessus et ajouter les sages à la liste.
        //Sinon finir la méthode
    }

    private Puzzle puzzle;

    /**
     * Constructeur
     *
     * @param stage L'étage où se situe le sage
     * @param location Coordonnées du sage
     * @param name Le nom du sage
     */
    public Sage(Stage stage, Location location, String name) {
        super(stage, location, name);
    }

    @Override
    public String getCharacter() {
        return CColor.CYAN + "S" + CColor.CYAN.getForegroundReset();
    }
}
