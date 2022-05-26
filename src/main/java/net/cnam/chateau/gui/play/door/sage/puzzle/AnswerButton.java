package net.cnam.chateau.gui.play.door.sage.puzzle;

import net.cnam.chateau.AppSettings;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.game.EntityDeadException;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.structure.block.door.SageDoor;
import net.cnam.chateau.utils.Couple;

import java.util.Random;

public class AnswerButton extends CButton {

    private PuzzleMenu menu;
    private Couple<String, Boolean> answer;
    private SageDoor door;
    private Player player;

    public AnswerButton(AppSettings settings, Player player, SageDoor door, Couple<String, Boolean> answer, PuzzleMenu menu) {
        super(settings, answer.getElemOne());

        this.player = player;
        this.door = door;
        this.answer = answer;
        this.menu = menu;
    }

    @Override
    public void execute() {
        if (answer.getElemTwo()) {
            door.setSage(null);
        } else {
            try {
                player.damage(new Random().nextInt(5, 11));
                door.getSage().affectAPuzzle();
            } catch (EntityDeadException ignored) {
            }
        }
        this.menu.stopDisplay();
    }
}
