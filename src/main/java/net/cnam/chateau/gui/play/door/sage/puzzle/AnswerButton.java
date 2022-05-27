package net.cnam.chateau.gui.play.door.sage.puzzle;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.game.EntityDeadException;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.structure.block.door.SageDoor;
import net.cnam.chateau.utils.Couple;

import java.util.Random;

public class AnswerButton extends CButton {
    private final PuzzleMenu menu;
    private final Couple<String, Boolean> answers;
    private final SageDoor door;
    private final Player player;

    public AnswerButton(App app, Player player, SageDoor door, Couple<String, Boolean> answers, PuzzleMenu menu) {
        super(app, answers.getElemOne());

        this.player = player;
        this.door = door;
        this.answers = answers;
        this.menu = menu;
    }

    @Override
    public void execute() {
        if (answers.getElemTwo()) {
            door.setSage(null);
        } else {
            try {
                player.damage(new Random().nextInt(5, 11));
                door.getSage().affectRandomPuzzle();
            } catch (EntityDeadException ignored) {
            }
        }
        this.menu.stopDisplay();
    }
}
