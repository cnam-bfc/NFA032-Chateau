package net.cnam.chateau.entity.enemy;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Entity;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.event.entity.EntityApproachEvent;
import net.cnam.chateau.event.entity.EntityListener;
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

    public static void initSpecialEnemys(App app) {
        specialEnemys.add(new Demogorgon(app, null, null, "Chef demogorgon : Demo-Bob", 100, 100, 100, 100, 100));
        specialEnemys.add(new Harpy(app, null, null, "Cheffe harpie : Senga-Eiram", 100, 100, 100, 100, 100));
        specialEnemys.add(new HeadlessKnight(app, null, null, "Chef chevalier sans tete : 720-headshot", 100, 100, 100, 100, 100));
        specialEnemys.add(new Morbol(app, null, null, "Chef morbol : Gilou", 100, 100, 100, 100, 100));
        specialEnemys.add(new Spider(app, null, null, "Chef araignée : Aragog", 100, 100, 100, 100, 100));
        specialEnemys.add(new Werewolf(app, null, null, "Cheffe loup-garou : Aela", 100, 100, 100, 100, 100));
        specialEnemys.add(new Zombie(app, null, null, "Chef zombie : Maxime", 100, 100, 100, 100, 100));
    }

    /**
     * Constructeur
     *
     * @param app        L'application
     * @param stage      L'étage où il se situe
     * @param location   Coordonnées où il se situe
     * @param name       Le nom
     * @param health     La santé
     * @param resistance La résistance
     * @param strength   La force
     * @param accuracy   La précision
     * @param speed      La rapidité
     */
    public Enemy(App app, Stage stage, Location location, String name, int health, int resistance, int strength,
                 int accuracy,
                 int speed) {
        super(app, stage, location, name, health, resistance, strength, accuracy, speed);
    }

    /**
     * Constructeur
     *
     * @param app      L'application
     * @param stage    L'étage où se situe l'ennemi
     * @param location Coordonnées de l'ennemi
     * @param name     Le nom de l'ennemi
     */
    public Enemy(App app, Stage stage, Location location, String name) {
        super(app, stage, location, name);
    }

    @Override
    public void onEntityApproachEvent(EntityApproachEvent event) {
        if (event.getEntity() instanceof Player player) {
            Fight fight = this.fight(player);
            if (!fight.isOver()) {
                event.setCanceled(true);
            }
        }
    }
}
