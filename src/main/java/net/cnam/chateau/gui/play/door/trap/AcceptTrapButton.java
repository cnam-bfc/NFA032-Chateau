package net.cnam.chateau.gui.play.door.trap;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.dialog.InfoDialog;
import net.cnam.chateau.structure.block.door.TrappedDoor;
import net.cnam.chateau.structure.block.trap.BadTrap;
import net.cnam.chateau.structure.block.trap.GoodTrap;

public class AcceptTrapButton extends CButton {

    App app;
    TrappedDoorMenu menu;
    TrappedDoor door;
    Player player;

    public AcceptTrapButton(App app, TrappedDoorMenu menu, TrappedDoor door, Player player) {
        super(app.getSettings(), "Accepter votre sort !");

        this.app = app;
        this.menu = menu;
        this.door = door;
        this.player = player;
    }

    @Override
    public void execute() {
        if (door.getTrap() instanceof BadTrap trap) {
            app.getConsole().show(new InfoDialog(InfoDialog.Type.INFO, "Vous avez fait le choix d'accepter votre sort !\n\n"+
                    "Malheureusement, c'était une mauvaise idée ! \nC'était " +
            trap.getDescription() + "\nCela vous a infligé : " + trap.getDmg() + " points de dégâts !"  ));
        }
        if (door.getTrap() instanceof GoodTrap trap){
            app.getConsole().show(new InfoDialog(InfoDialog.Type.INFO, "Vous avez fait le choix d'accepter votre sort !\n\n"+
                    "Par chance c'était un piège bénéfique ! \nC'était " +
                    trap.getDescription() + "\nCela vous a restauré : " + trap.getHealth() + " points de vies !"  ));
        }
        door.getTrap().useEffect(player);
        this.menu.stopDisplay();
    }
}
