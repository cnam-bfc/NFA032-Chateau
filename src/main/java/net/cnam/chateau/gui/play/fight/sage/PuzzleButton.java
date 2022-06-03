package net.cnam.chateau.gui.play.fight.sage;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.common.OpenComponentButton;
import net.cnam.chateau.gui.play.fight.sage.puzzle.PuzzleMenu;
import net.cnam.chateau.structure.block.door.SageDoor;

public class PuzzleButton extends OpenComponentButton {
    private final SageDoorMenu menu;

    public PuzzleButton(App app, Player player, SageDoor door, SageDoorMenu menu) {
        super(app, new PuzzleMenu(app, player, door), "Énigme");

        this.menu = menu;
    }

    @Override
    public void execute() {
        super.execute();

        this.menu.stopLoopingMode();
    }
}
