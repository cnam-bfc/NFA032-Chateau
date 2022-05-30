package net.cnam.chateau.gui.play.door.trap;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.dialog.InfoDialog;
import net.cnam.chateau.structure.block.door.TrappedDoor;
import net.cnam.chateau.structure.block.trap.BadTrap;
import net.cnam.chateau.structure.block.trap.GoodTrap;

import java.util.Random;

public class TryToLeaveButton extends CButton {
    private final App app;
    private final TrappedDoorMenu menu;
    private final TrappedDoor door;
    private final Player player;

    public TryToLeaveButton(App app, TrappedDoorMenu menu, TrappedDoor door, Player player) {
        super(app, "Tentative de fuite !");

        this.app = app;
        this.menu = menu;
        this.door = door;
        this.player = player;
    }

    @Override
    public void execute() {
        door.getTrap().setActivate(false);
        if (player.getSpeed() < new Random().nextInt(0, 50)) {
            if (door.getTrap() instanceof BadTrap trap) {
                app.getConsole().show(new InfoDialog(InfoDialog.Type.INFO, "Vous avez échoué votre fuite !\n \n" +
                        "Malgré tous vos efforts vous vous prenez le piège ! \nC'était " +
                        trap.getDescription() + "\nCela vous a infligé : " + trap.getDmg() + " points de dégâts !"));
            }
            if (door.getTrap() instanceof GoodTrap trap) {
                app.getConsole().show(new InfoDialog(InfoDialog.Type.INFO, "Vous avez échoué votre fuite !\n \n" +
                        "Par chance c'était un piège bénéfique ! \nC'était " +
                        trap.getDescription() + "\nCela vous a restauré : " + trap.getHealth() + " points de vies !"));
            }
            door.getTrap().useEffect(player);
        }
        this.menu.stopDisplay();
    }
}
