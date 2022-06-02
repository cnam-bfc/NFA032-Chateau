package net.cnam.chateau.entity.enemy;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Entity;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.event.entity.EntityApproachEvent;
import net.cnam.chateau.event.entity.EntityListener;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.play.fight.Fight;
import net.cnam.chateau.structure.Stage;
import net.cnam.chateau.utils.Location;

/**
 * Classe d'un ennemi
 */
public abstract class Enemy extends Entity implements EntityListener {
    private final String character;

    /**
     * Constructeur
     *
     * @param app       L'application
     * @param stage     L'étage où il se situe
     * @param location  Coordonnées où il se situe
     * @param name      Le nom
     * @param health    La santé
     * @param strength  La force
     * @param accuracy  La précision
     * @param speed     La rapidité
     * @param character Le caractère d'affichage
     */
    public Enemy(App app, Stage stage, Location location, String name, int health, int strength,
                 int accuracy,
                 int speed, String character) {
        super(app, stage, location, name, health, strength, accuracy, speed);

        this.character = character;
    }

    /**
     * Constructeur
     *
     * @param app       L'application
     * @param stage     L'étage où se situe l'ennemi
     * @param location  Coordonnées de l'ennemi
     * @param name      Le nom de l'ennemi
     * @param character Le caractère d'affichage
     */
    public Enemy(App app, Stage stage, Location location, String name, String character) {
        super(app, stage, location, name);

        this.character = character;
    }

    /**
     * Redéfinition de la méthode permettant d'afficher l'entité sur la carte.
     *
     * @return Le caractère représentant l'entité
     */
    @Override
    public String getCharacter() {
        return CColor.BRIGHT_RED + this.character + CColor.BRIGHT_RED.getForegroundReset();
    }

    /**
     * Redéfinition de la méthode permettant de détecter une entité approche d'une autre entité.
     * Ici on gère le cas du joueur pour lancer un combat
     *
     * @param event
     */
    @Override
    public void onEntityApproachEvent(EntityApproachEvent event) {
        if (event.getEntity() instanceof Player player) {
            Fight fight = this.fight(player, false);
            if (!fight.isOver()) {
                event.setCanceled(true);
            }
        }
    }
}
