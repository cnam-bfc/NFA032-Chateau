package net.cnam.chateau.gui.play.door.sage;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.play.door.sage.puzzle.PuzzleMenu;
import net.cnam.chateau.structure.block.door.SageDoor;

public class PuzzleButton extends CButton {

    private final App app;
    private final Player player;
    private final SageDoor door;
    private final SageDoorMenu menu;

    public PuzzleButton(App app, Player player, SageDoor door, SageDoorMenu menu) {
        super(app, "Énigme");

        this.app = app;
        this.player = player;
        this.door = door;
        this.menu = menu;
    }

    @Override
    public void execute() {
        app.getConsole().show(new PuzzleMenu(app, player, door));
        this.menu.stopDisplay();
    }
}
