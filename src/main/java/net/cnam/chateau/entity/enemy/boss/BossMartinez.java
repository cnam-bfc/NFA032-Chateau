package net.cnam.chateau.entity.enemy.boss;

import net.cnam.chateau.App;
import net.cnam.chateau.item.weapon.Weapon;
import net.cnam.chateau.structure.Stage;
import net.cnam.chateau.utils.Location;

import java.util.ArrayList;
import java.util.List;

public class BossMartinez extends Boss {
    private static final String CHARACTER = "M";

    /**
     * Constructeur
     *
     * @param app      L'application
     * @param stage    L'étage où il se situe
     * @param location Coordonnées où il se situe
     */
    public BossMartinez(App app, Stage stage, Location location) {
        super(app, stage, location, "Martinez", 100, 100, 100, 100, 100, CHARACTER);

        this.setWeapon(new Weapon("Livre : \"Java pour les nuls\"", "L'arme divine pour apprendre à coder", 10, 10, 10));

        // initialisation du dialogue
        List<String> dialogue = new ArrayList<>();
        dialogue.add("Vous avez réussi à arriver jusque ici ?");
        dialogue.add("Malheureusement c'est ici que s'arrête votre chemin !");
        this.setDialogue(dialogue);
    }
}
