package net.cnam.chateau.structure.block.door;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.entity.Sage;
import net.cnam.chateau.event.block.EntityEnterBlockEvent;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.play.door.sage.SageDoorMenu;
import net.cnam.chateau.structure.Room;
import net.cnam.chateau.structure.Stage;

public class SageDoor extends Door {
    private final App app;
    private Sage sage;
    private boolean visited = false;

    public SageDoor(App app, Stage stage, Room roomOne, Room roomTwo, Sage sage) {
        super(stage, roomOne, roomTwo);

        this.app = app;
        this.sage = sage;
    }

    public Sage getSage() {
        return sage;
    }

    public void setSage(Sage sage) {
        this.sage = sage;
    }

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
     * Si un sage est vivant, présent et l'énigme non résolu affiche un "S" rouge
     * Si le sage est mort, ou énigme résolu, affiche un " " pour montrer l'accessibilité
     *
     * @return le cractère associé au passage
     */
    @Override
    public String getCharacter() {
        if (!visited) {
            return CColor.GREEN + "D" + CColor.GREEN.getForegroundReset();
        }
        if (isLocked()) {
            return CColor.RED + sage.getCharacter() + CColor.RED.getForegroundReset();
        } else {
            return CColor.GREEN + "D" + CColor.GREEN.getForegroundReset();
        }
    }
}
