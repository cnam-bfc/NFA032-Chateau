package net.cnam.gui.component;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import net.cnam.gui.Console;
import net.cnam.utils.console.RawConsoleInput;
import net.cnam.utils.direction.Direction;
import net.cnam.utils.direction.DirectionNotFoundException;
import net.cnam.utils.direction.DirectionUtils;

public class CButtons extends CPanel {

    private final List<CButton> buttons = new ArrayList<>();
    private final int spacing;

    public CButtons(CButton[] buttons) {
        this(buttons, 0);
    }

    public CButtons(CButton[] buttons, int spacing) {
        super(0, 0, false);

        this.spacing = spacing;
        for (int i = 0; i < buttons.length; i++) {
            CButton button = buttons[i];
            if (i == 0) {
                button.setSelected(true);
            } else {
                button.setSelected(false);
            }
            if (spacing > 0 && this.getHeight() != 0) {
                this.setHeight(this.getHeight() + spacing);
            }
            if (button.getLength() > this.getLength()) {
                this.setLength(button.getLength());
            }
            this.setHeight(this.getHeight() + button.getHeight());
            this.buttons.add(button);
            this.getContent().add(button);
        }
    }

    public void askKeyboard(Console console) {
        mainLoop:
        while (true) {
            console.print();
            try {
                int input = RawConsoleInput.read(true);
                // 13 = Entrée dans un terminal ; 10 = Entrée dans netbeans
                if (input == 13 || input == 10) {
                    for (CButton button : buttons) {
                        if (button.isSelected()) {
                            button.execute();
                            break mainLoop;
                        }
                    }
                }

                if (buttons.size() < 2) {
                    return;
                }

                Direction direction = DirectionUtils.parseDirection(input);
                switch (direction) {
                    case TOP, LEFT -> {
                        for (int i = 0; i < buttons.size(); i++) {
                            CButton button = buttons.get(i);
                            if (button.isSelected()) {
                                button.setSelected(false);
                                if (i == 0) {
                                    buttons.get(buttons.size() - 1).setSelected(true);
                                } else {
                                    buttons.get(i - 1).setSelected(true);
                                }
                                break;
                            }
                        }
                    }
                    case BOTTOM ,RIGHT -> {
                        for (int i = 0; i < buttons.size(); i++) {
                            CButton button = buttons.get(i);
                            if (button.isSelected()) {
                                button.setSelected(false);
                                if (i == buttons.size() - 1) {
                                    buttons.get(0).setSelected(true);
                                } else {
                                    buttons.get(i + 1).setSelected(true);
                                }
                                break;
                            }
                        }
                    }
                }
            } catch (IOException ex) {
                System.out.println("ERREUR");
                System.exit(1);
            } catch (DirectionNotFoundException ex) {

            }
        }
    }
}
