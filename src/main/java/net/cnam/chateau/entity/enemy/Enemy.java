package net.cnam.chateau.entity.enemy;

import net.cnam.chateau.entity.Entity;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.event.entity.EntityApprochEvent;
import net.cnam.chateau.event.entity.EntityListener;
import net.cnam.chateau.gui.Console;
import net.cnam.chateau.gui.play.fight.Fight;
import net.cnam.chateau.structure.Stage;
import net.cnam.chateau.utils.Location;

import java.util.LinkedList;
import java.util.List;

/**
 * Classe d'un ennemi
 */
public abstract class Enemy extends Entity implements EntityListener {

    public static List<Enemy> specialEnemys = new LinkedList<>();

    public static void initSpecialEnemys(Console console){
        specialEnemys.add(new Demogorgon(console, null, null, "Chef demogorgon : Demo-Bob", 100,100,100,100,100));
        specialEnemys.add(new Harpy(console, null, null, "Cheffe harpie : Senga-Eiram", 100,100,100,100,100));
        specialEnemys.add(new HeadlessKnight(console, null, null, "Chef chevalier sans tete : 720-headshot", 100,100,100,100,100));
        specialEnemys.add(new Morbol(console, null, null, "Chef morbol : Gilou", 100,100,100,100,100));
        specialEnemys.add(new Spider(console, null, null, "Chef araignée : Aragog", 100,100,100,100,100));
        specialEnemys.add(new Werewolf(console, null, null, "Cheffe loup-garou : Aela", 100,100,100,100,100));
        specialEnemys.add(new Zombie(console, null, null, "Chef zombie : Maxime", 100,100,100,100,100));
    }
    private final Console console;

    /**
     * Constructeur
     *
     * @param console    La console
     * @param stage      L'étage où il se situe
     * @param location   Coordonnées où il se situe
     * @param name       Le nom
     * @param health     La santé
     * @param resistance La résistance
     * @param strength   La force
     * @param accuracy   La précision
     * @param speed      La rapidité
     */
    public Enemy(Console console, Stage stage, Location location, String name, int health, int resistance, int strength,
            int accuracy,
            int speed) {
        super(stage, location, name, health, resistance, strength, accuracy, speed);

        this.console = console;
    }

    /**
     * Constructeur
     *
     * @param console  La console
     * @param stage    L'étage où se situe l'ennemi
     * @param location Coordonnées de l'ennemi
     * @param name     Le nom de l'ennemi
     */
    public Enemy(Console console, Stage stage, Location location, String name) {
        super(stage, location, name);

        this.console = console;
    }

    @Override
    public void onEntityApprochEvent(EntityApprochEvent event) {
        if (event.getEntity() instanceof Player player) {
            Fight fight = new Fight(player, this);
            console.show(fight);
        }
    }
}
