package net.cnam.chateau.structure.block.door;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.entity.Sage;
import net.cnam.chateau.event.block.EntityEnterBlockEvent;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.play.sagedoor.SageDoorMenu;
import net.cnam.chateau.structure.Room;
import net.cnam.chateau.structure.Stage;

import java.util.Random;

public class SageDoor extends Door {

    private Sage sage;
    private App app;
    private boolean visited = false;

    public SageDoor(App app, Stage stage, Room roomOne, Room roomTwo, Random random) {
        super(stage, roomOne, roomTwo);

        getASage(random);
        this.app = app;
    }

    public Sage getSage() {
        return sage;
    }

    public void setSage(Sage sage) {
        this.sage = sage;
    }

    private void getASage(Random random) {
        if (!Sage.sages.isEmpty()) {
            this.sage = Sage.sages.remove(random.nextInt(0, Sage.sages.size()));
        }
    }

    @Override
    public boolean isLocked() {
        return (sage != null);
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
        if (!visited){
            return CColor.GREEN + "D" + CColor.GREEN.getForegroundReset();
        }
        if (isLocked()) {
            return CColor.RED + sage.getCharacter() + CColor.RED.getForegroundReset();
        } else {
            return CColor.GREEN + "D" + CColor.GREEN.getForegroundReset();
        }
    }
}
