package net.cnam.chateau.gui.play.fight.sage.puzzle;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.common.QuitComponentButton;
import net.cnam.chateau.gui.component.CChoices;
import net.cnam.chateau.gui.component.CFrame;
import net.cnam.chateau.gui.component.CLabel;
import net.cnam.chateau.gui.DisplayableComponent;
import net.cnam.chateau.structure.block.door.SageDoor;

import java.util.Collections;

public class PuzzleMenu extends CFrame implements DisplayableComponent {
    private boolean display = true;

    public PuzzleMenu(App app, Player player, SageDoor door) {
        super(0, 0, "Énigme");

        CLabel question = new CLabel(door.getSage().getPuzzle().getQuestion());
        question.getColors().add(CColor.CYAN);
        this.getContentPane().getComponents().add(question);

        CChoices choices = new CChoices(app, 1);

        Collections.shuffle(door.getSage().getPuzzle().getAnswers());
        for (int i = 0; i < door.getSage().getPuzzle().getAnswers().size(); i++) {
            choices.add(new AnswerButton(app, player, door, door.getSage().getPuzzle().getAnswers().get(i), this));
        }

        choices.add(new QuitComponentButton(app, this, "Répondre plus tard"));

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

    @Override
    public void stopLoopingMode() {
        this.display = false;
    }
}