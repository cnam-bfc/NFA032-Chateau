package net.cnam.chateau.structure.block.door;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.event.block.EntityEnterBlockEvent;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.play.door.DoorMenu;
import net.cnam.chateau.item.Key;
import net.cnam.chateau.structure.Room;
import net.cnam.chateau.structure.Stage;

/**
 * Classe permettant de créer un block Porte bloquée
 */
public class LockedDoor extends Door {
    private final App app;
    private boolean lock = true;
    private boolean tryDestroyDoor = false;
    private Key key;

    /**
     * Constructeur de la classe LockedDoor
     * @param app       L'application
     * @param stage     L'étage de la porte
     * @param roomOne   La première pièce de la porte
     * @param roomTwo   La seconde pièce de la porte
     * @param key       La clé de la porte
     */
    public LockedDoor(App app, Stage stage, Room roomOne, Room roomTwo, Key key) {
        super(stage, roomOne, roomTwo);

        this.app = app;
        this.key = key;
    }

    /**
     * Méthode permettant de savoir si une tentative de destruction de porte a été faite
     * @return True si une tentative de destruction de porte a été faite, false sinon
     */
    public boolean hasTryDestroy() {
        return tryDestroyDoor;
    }

    /**
     * Méthode pour renter de détruire la porte
     * @return True si la porte a été détruite, false sinon
     */
    public boolean tryDestroy() {
        this.tryDestroyDoor = true;
        return (Math.random() * 100 > 80);
    }

    /**
     * Méthode d'accès à la clé de la porte
     * @return La clé de la porte
     */
    public Key getKey() {
        return key;
    }

    /**
     * Méthode d'altération de la clé de la porte
     * @param key La nouvelle clé de la porte
     */
    public void setKey(Key key) {
        this.key = key;
    }

    /**
     * Méthode permettant de bloquer/débloquer la porte.
     * @param lock True pour bloquer la porte, false pour débloquer la porte
     */
    public void setLock(boolean lock) {
        this.lock = lock;
    }

    /**
     * Méthode permettant de savoir si la porte est bloquée
     * @return True si la porte est bloquée, false sinon
     */
    @Override
    public boolean isLocked() {
        return lock;
    }

    /**
     * Méthode permettant de retourner le caractère de la porte.
     * "D" rouge si bloqué, "D" vert si débloqué
     * @return Le caractère de la porte
     */
    @Override
    public String getCharacter() {
        if (isLocked()) {
            return CColor.BRIGHT_RED + "D" + CColor.BRIGHT_RED.getForegroundReset();
        } else {
            return CColor.GREEN + "D" + CColor.GREEN.getForegroundReset();
        }
    }

    @Override
    public void onEntityEnterBlock(EntityEnterBlockEvent event) {
        if (event.getEntity() instanceof Player player && this.lock) {
            app.getConsole().show(new DoorMenu(app, player, this));
        }

        super.onEntityEnterBlock(event);
    }
}
