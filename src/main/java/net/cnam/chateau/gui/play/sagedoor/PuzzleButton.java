package net.cnam.chateau.gui.play.sagedoor;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.play.sagedoor.puzzle.PuzzleMenu;
import net.cnam.chateau.structure.block.door.SageDoor;

public class PuzzleButton extends CButton {

    private App app;
    private Player player;
    private SageDoor door;
    private SageDoorMenu menu;

    public PuzzleButton(App app, Player player, SageDoor door, SageDoorMenu menu) {
        super(app.getSettings(), "Enigme");

        this.app = app;
        this.player = player;
        this.door = door;
        this.menu = menu;
    }

    @Override
    public void execute() {
        app.getConsole().show(new PuzzleMenu(app.getSettings(),player, door));
        this.menu.stopDisplay();
    }
}
