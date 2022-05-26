package net.cnam.chateau.gui.play.sagedoor.puzzle;

import net.cnam.chateau.AppSettings;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.component.*;
import net.cnam.chateau.gui.play.sagedoor.AttackButton;
import net.cnam.chateau.gui.play.sagedoor.LeaveButton;
import net.cnam.chateau.gui.play.sagedoor.PuzzleButton;
import net.cnam.chateau.structure.block.door.SageDoor;
import net.cnam.chateau.utils.array.ArrayUtils;

import java.util.Collections;

public class PuzzleMenu extends CFrame implements DisplayableComponent {
    private boolean display = true;

    public PuzzleMenu(AppSettings settings, Player player, SageDoor door) {
        super(0, 0, "Énigme");

        CLabel question = new CLabel(door.getSage().getPuzzle().getQuestion());
        question.getColors().add(CColor.CYAN);
        this.getContentPane().getComponents().add(question);

        SelectableComponent[] selectableComponent = new SelectableComponent[0];

        Collections.shuffle(door.getSage().getPuzzle().getAnswers());
        for (int i = 0 ; i < door.getSage().getPuzzle().getAnswers().size() ; i++){
            selectableComponent = ArrayUtils.addOnBottomOfArray(selectableComponent, new AnswerButton(settings, player, door,
                    door.getSage().getPuzzle().getAnswers().get(i), this));
        }

        selectableComponent = ArrayUtils.addOnBottomOfArray(selectableComponent, new LeavePuzzleButton(settings, this));

        CChoices choices = new CChoices(settings, selectableComponent, 1);

        this.getContentPane().getComponents().add(choices);
    }

    @Override
    public boolean isInLoopingMode() {
        return display;
    }

    @Override
    public boolean isInFullScreenMode() {
        return true;
    }

    public void stopDisplay() {
        this.display = false;
    }
}