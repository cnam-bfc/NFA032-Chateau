package net.cnam.chateau.gui.information.structure;

import net.cnam.chateau.App;
import net.cnam.chateau.event.key.KeyPressedEvent;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.component.*;
import net.cnam.chateau.utils.direction.Direction;
import net.cnam.chateau.utils.direction.DirectionNotFoundException;
import net.cnam.chateau.utils.direction.DirectionUtils;
import net.cnam.chateau.utils.direction.Orientation;

public class SpecialBlockMenu extends CFrame implements DisplayableComponent {
    private final App app;
    private boolean display = true;

    public SpecialBlockMenu(App app) {
        super(0, 0, "Les blocs spéciaux");

        this.app = app;

        // variables pour re définir proprement l'ajustement du texte
        int labelLength = 0;
        int valueLength = 0;

        CPanel panel = new CPanel(HorizontalAlignment.CENTER, Orientation.VERTICAL, 1);

        // Ajout des textes qui seront sur la partie gauche de l'écran
        /*CLabel wallLetterLabel = new CLabel(HorizontalAlignment.RIGHT, " ");
        wallLetterLabel.getColors().add(CColor.REVERSE);
        wallLetterLabel.getColors().add(CColor.BRIGHT_BLACK);
        if (wallLetterLabel.getLength() > labelLength) {
            labelLength = wallLetterLabel.getLength();
        }*/

        CLabel doorLetterLabel = new CLabel(HorizontalAlignment.RIGHT, "D");
        doorLetterLabel.getColors().add(CColor.GREEN);
        if (doorLetterLabel.getLength() > labelLength) {
            labelLength = doorLetterLabel.getLength();
        }

        CLabel lockedDoorLetterLabel = new CLabel(HorizontalAlignment.RIGHT, "D");
        lockedDoorLetterLabel.getColors().add(CColor.RED);
        if (lockedDoorLetterLabel.getLength() > labelLength) {
            labelLength = lockedDoorLetterLabel.getLength();
        }

        CLabel unUsedBedLetterlabel = new CLabel(HorizontalAlignment.RIGHT, "B");
        unUsedBedLetterlabel.getColors().add(CColor.WHITE);
        if (unUsedBedLetterlabel.getLength() > labelLength) {
            labelLength = unUsedBedLetterlabel.getLength();
        }

        CLabel usedBedLetterlabel = new CLabel(HorizontalAlignment.RIGHT, "B");
        usedBedLetterlabel.getColors().add(CColor.MAGENTA);
        if (usedBedLetterlabel.getLength() > labelLength) {
            labelLength = usedBedLetterlabel.getLength();
        }

        CLabel upStairLetterLabel = new CLabel(HorizontalAlignment.RIGHT, "U");
        upStairLetterLabel.getColors().add(CColor.BRIGHT_YELLOW);
        if (upStairLetterLabel.getLength() > labelLength) {
            labelLength = upStairLetterLabel.getLength();
        }

        CLabel downStairLetterLabel = new CLabel(HorizontalAlignment.RIGHT, "D");
        downStairLetterLabel.getColors().add(CColor.BRIGHT_YELLOW);
        if (downStairLetterLabel.getLength() > labelLength) {
            labelLength = downStairLetterLabel.getLength();
        }

        // ajustement des textes à gauche
        doorLetterLabel.setLength(labelLength);
        lockedDoorLetterLabel.setLength(labelLength);
        unUsedBedLetterlabel.setLength(labelLength);
        usedBedLetterlabel.setLength(labelLength);
        upStairLetterLabel.setLength(labelLength);
        downStairLetterLabel.setLength(labelLength);

        // Ajout des textes qui seront sur la partie droite de l'écran
        CLabel doorDescLabel = new CLabel(HorizontalAlignment.LEFT, "Porte ouverte");
        doorDescLabel.getColors().add(CColor.YELLOW);
        if (doorDescLabel.getLength() > valueLength) {
            valueLength = doorDescLabel.getLength();
        }

        CLabel lockedDoorDescLabel = new CLabel(HorizontalAlignment.LEFT, "Porte fermée à clé");
        lockedDoorDescLabel.getColors().add(CColor.YELLOW);
        if (lockedDoorDescLabel.getLength() > valueLength) {
            valueLength = lockedDoorDescLabel.getLength();
        }

        CLabel unUsedBedDescLabel = new CLabel(HorizontalAlignment.LEFT, "Lit non utilisé");
        unUsedBedDescLabel.getColors().add(CColor.YELLOW);
        if (unUsedBedDescLabel.getLength() > valueLength) {
            valueLength = unUsedBedDescLabel.getLength();
        }

        CLabel usedBedDescLabel = new CLabel(HorizontalAlignment.LEFT, "Lit utilisé");
        usedBedDescLabel.getColors().add(CColor.YELLOW);
        if (usedBedDescLabel.getLength() > valueLength) {
            valueLength = usedBedDescLabel.getLength();
        }

        CLabel upStairDescLabel = new CLabel(HorizontalAlignment.LEFT, "Escalier montant");
        upStairDescLabel.getColors().add(CColor.YELLOW);
        if (upStairDescLabel.getLength() > valueLength) {
            valueLength = upStairDescLabel.getLength();
        }

        CLabel downStairDescLabel = new CLabel(HorizontalAlignment.LEFT, "Escalier descendant");
        downStairDescLabel.getColors().add(CColor.YELLOW);
        if (downStairDescLabel.getLength() > valueLength) {
            valueLength = downStairDescLabel.getLength();
        }

        // ajustement des textes à droite
        doorDescLabel.setLength(valueLength);
        lockedDoorDescLabel.setLength(valueLength);
        unUsedBedDescLabel.setLength(valueLength);
        usedBedDescLabel.setLength(valueLength);
        upStairDescLabel.setLength(valueLength);
        downStairDescLabel.setLength(valueLength);

        CLabel seperatorLabel = new CLabel(HorizontalAlignment.LEFT, "-");

        CPanel doorPanel = new CPanel(HorizontalAlignment.CENTER, Orientation.HORIZONTAL, 1);
        doorPanel.getComponents().add(doorLetterLabel);
        doorPanel.getComponents().add(seperatorLabel);
        doorPanel.getComponents().add(doorDescLabel);
        doorPanel.autoResize();

        CPanel lockedDoorPanel = new CPanel(HorizontalAlignment.CENTER, Orientation.HORIZONTAL, 1);
        lockedDoorPanel.getComponents().add(lockedDoorLetterLabel);
        lockedDoorPanel.getComponents().add(seperatorLabel);
        lockedDoorPanel.getComponents().add(lockedDoorDescLabel);
        lockedDoorPanel.autoResize();

        CPanel unUsedBedPanel = new CPanel(HorizontalAlignment.CENTER, Orientation.HORIZONTAL, 1);
        unUsedBedPanel.getComponents().add(unUsedBedLetterlabel);
        unUsedBedPanel.getComponents().add(seperatorLabel);
        unUsedBedPanel.getComponents().add(unUsedBedDescLabel);
        unUsedBedPanel.autoResize();

        CPanel usedBedPanel = new CPanel(HorizontalAlignment.CENTER, Orientation.HORIZONTAL, 1);
        usedBedPanel.getComponents().add(usedBedLetterlabel);
        usedBedPanel.getComponents().add(seperatorLabel);
        usedBedPanel.getComponents().add(usedBedDescLabel);
        usedBedPanel.autoResize();

        CPanel upStairPanel = new CPanel(HorizontalAlignment.CENTER, Orientation.HORIZONTAL, 1);
        upStairPanel.getComponents().add(upStairLetterLabel);
        upStairPanel.getComponents().add(seperatorLabel);
        upStairPanel.getComponents().add(upStairDescLabel);
        upStairPanel.autoResize();

        CPanel downStairPanel = new CPanel(HorizontalAlignment.CENTER, Orientation.HORIZONTAL, 1);
        downStairPanel.getComponents().add(downStairLetterLabel);
        downStairPanel.getComponents().add(seperatorLabel);
        downStairPanel.getComponents().add(downStairDescLabel);
        downStairPanel.autoResize();

        // ajout de tous les textes au panel + ajustement
        panel.getComponents().add(doorPanel);
        panel.getComponents().add(lockedDoorPanel);
        panel.getComponents().add(unUsedBedPanel);
        panel.getComponents().add(usedBedPanel);
        panel.getComponents().add(upStairPanel);
        panel.getComponents().add(downStairPanel);
        panel.autoResize();

        this.getContentPane().getComponents().add(panel);

        this.setFooter(new CPanel(HorizontalAlignment.CENTER,0,1, Orientation.HORIZONTAL, 1));

        CPanel rightTextPanel = new CPanel(HorizontalAlignment.LEFT, Orientation.HORIZONTAL, 1);
        rightTextPanel.getComponents().add(new CLabel(HorizontalAlignment.LEFT, "Appuyez flèche gauche"));
        rightTextPanel.autoResize();

        CPanel leaveButtonPanel = new CPanel(HorizontalAlignment.CENTER, Orientation.HORIZONTAL, 1);
        leaveButtonPanel.getComponents().add(new BackButtonSpecialBlockMenu(app, this));
        leaveButtonPanel.autoResize();

        CPanel leftTextPanel = new CPanel(HorizontalAlignment.RIGHT, Orientation.HORIZONTAL, 1);
        leftTextPanel.getComponents().add(new CLabel(HorizontalAlignment.RIGHT, "Appuyez flèche droite"));
        leftTextPanel.autoResize();

        this.getFooter().getComponents().add(rightTextPanel);
        this.getFooter().getComponents().add(leaveButtonPanel);
        this.getFooter().getComponents().add(leftTextPanel);
    }

    @Override
    public boolean isInLoopingMode() {
        return display;
    }

    @Override
    public boolean isInFullScreenMode() {
        return true;
    }

    public void stopDisplaying() {
        display = false;
    }

    @Override
    public void onKeyPressed(KeyPressedEvent event) {
        super.onKeyPressed(event);

        try {
            Direction direction = DirectionUtils.parseDirection(event.getKey(),false);
            switch (direction){
                case RIGHT, TOP -> {
                    app.getConsole().show(new ContainerBlockMenu(app));
                }
                case LEFT, BOTTOM -> {
                    app.getConsole().show(new DecorativeBlockMenu(app));
                }
            }
            stopDisplaying();
        } catch (DirectionNotFoundException ignored) {
        }
    }
}
