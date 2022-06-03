package net.cnam.chateau.structure.block.door;

import net.cnam.chateau.entity.Player;
import net.cnam.chateau.entity.enemy.Enemy;
import net.cnam.chateau.event.block.EntityEnterBlockEvent;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.play.fight.Fight;
import net.cnam.chateau.structure.Room;
import net.cnam.chateau.structure.Stage;

/**
 * Class permettant de créer un block Porte (Door) référençant un ennemi bloquant le passage.
 */
public class EnemyDoor extends Door {
    private Enemy enemy;
    private boolean visited = false;

    /**
     * Constructeur de la classe Door.
     * @param stage     L'étage sur lequel se trouve la porte.
     * @param roomOne   La première pièce reliée à la porte
     * @param roomTwo   La seconde pièce reliée à la porte
     * @param enemy     L'ennemi qui bloque le passage
     */
    public EnemyDoor(Stage stage, Room roomOne, Room roomTwo, Enemy enemy) {
        super(stage, roomOne, roomTwo);

        this.enemy = enemy;
    }

    /**
     * Méthode d'accès à l'ennemi qui bloque le passage
     * @return L'ennemi qui bloque le passage
     */
    public Enemy getEnemy() {
        return enemy;
    }

    /**
     * Méthode d'altération de l'ennemi qui bloque le passage
     * @param enemy    L'ennemi qui bloque le passage
     */
    public void setEnemy(Enemy enemy) {
        this.enemy = enemy;
    }

    /**
     * Méthode vérifiant si la porte est bloquée
     *
     * @return true si la porte est bloquée, false sinon
     */
    @Override
    public boolean isLocked() {
        return enemy != null;
    }

    @Override
    public void onEntityEnterBlock(EntityEnterBlockEvent event) {
        this.visited = true;
        if (event.getEntity() instanceof Player player) {
            if (this.enemy != null) {
                Fight fight = enemy.fight(player, true);
                if (!fight.isOver()) {
                    event.setCanceled(true);
                } else {
                    this.enemy = null;
                }
            }
        }
        super.onEntityEnterBlock(event);
    }

    /**
     * Méthode permettant de retourner le block qui sera affiché sur la carte
     * "D" si la porte est ouverte, sinon récupère le caractère de l'ennemi qui bloque le passage
     *
     * @return Le block qui sera affiché sur la carte
     */
    @Override
    public String getCharacter() {
        if (!visited || this.enemy == null) {
            return CColor.GREEN + "D" + CColor.GREEN.getForegroundReset();
        } else {
            return CColor.BRIGHT_RED + enemy.getCharacter() + CColor.BRIGHT_RED.getForegroundReset();
        }
    }
}
