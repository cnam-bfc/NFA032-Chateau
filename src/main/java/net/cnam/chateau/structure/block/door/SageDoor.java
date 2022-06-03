package net.cnam.chateau.structure.block.door;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.entity.Sage;
import net.cnam.chateau.event.block.EntityEnterBlockEvent;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.play.fight.sage.SageDoorMenu;
import net.cnam.chateau.structure.Room;
import net.cnam.chateau.structure.Stage;

/**
 * Classe permettant de créer une porte référençant un Sage qui la bloque
 */
public class SageDoor extends Door {
    private final App app;
    private Sage sage;
    private boolean visited = false;

    /**
     * Constructeur de la porte Sage
     * @param app       L'application
     * @param stage     L'Étage dans lequel se trouve la porte
     * @param roomOne   La première salle de la porte
     * @param roomTwo   La deuxième salle de la porte
     * @param sage      Le Sage qui bloque la porte
     */
    public SageDoor(App app, Stage stage, Room roomOne, Room roomTwo, Sage sage) {
        super(stage, roomOne, roomTwo);

        this.app = app;
        this.sage = sage;
    }

    /**
     * Méthode d'accès au Sage qui bloque la porte
     * @return Le Sage qui bloque la porte
     */
    public Sage getSage() {
        return sage;
    }

    /**
     * Méthode d'altération du Sage qui bloque la porte
     * @param sage Le Sage qui bloque la porte
     */
    public void setSage(Sage sage) {
        this.sage = sage;
    }

    /**
     * Méthode pour savoir si la porte est bloquée
     * @return true si la porte est bloquée, false sinon
     */
    @Override
    public boolean isLocked() {
        return sage != null;
    }

    @Override
    public void onEntityEnterBlock(EntityEnterBlockEvent event) {
        visited = true;
        if (isLocked()) {
            if (event.getEntity() instanceof Player player) {
                app.getConsole().show(new SageDoorMenu(app, player, this));
            }
        }
        super.onEntityEnterBlock(event);
    }

    /**
     * Retourne le caractère qui sera défini sur la map.
     * Si un sage est vivant, présent et l'énigme non résolue affiche un "S" rouge
     * Si le sage est mort, ou énigme résolue, affiche un " " pour montrer l'accessibilité
     *
     * @return le caractère associé au passage
     */
    @Override
    public String getCharacter() {
        if (!visited) {
            return CColor.GREEN + "D" + CColor.GREEN.getForegroundReset();
        }
        if (isLocked()) {
            return CColor.YELLOW + sage.getCharacter() + CColor.YELLOW.getForegroundReset();
        } else {
            return CColor.GREEN + "D" + CColor.GREEN.getForegroundReset();
        }
    }
}
