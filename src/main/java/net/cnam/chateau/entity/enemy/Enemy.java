package net.cnam.chateau.entity.enemy;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Entity;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.event.entity.EntityApproachEvent;
import net.cnam.chateau.event.entity.EntityListener;
import net.cnam.chateau.gui.play.fight.Fight;
import net.cnam.chateau.structure.Stage;
import net.cnam.chateau.utils.Location;

/**
 * Classe d'un ennemi
 */
public abstract class Enemy extends Entity implements EntityListener {
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
