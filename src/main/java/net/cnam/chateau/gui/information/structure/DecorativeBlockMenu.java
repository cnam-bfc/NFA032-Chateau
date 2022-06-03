package net.cnam.chateau.gui.information.structure;

import net.cnam.chateau.App;
import net.cnam.chateau.event.key.KeyPressedEvent;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.component.*;
import net.cnam.chateau.utils.direction.Direction;
import net.cnam.chateau.utils.direction.DirectionNotFoundException;
import net.cnam.chateau.utils.direction.DirectionUtils;
import net.cnam.chateau.utils.direction.Orientation;

public class DecorativeBlockMenu extends CFrame implements DisplayableComponent {
    private final App app;

    private boolean display = true;

    public DecorativeBlockMenu(App app) {
        super(0, 0, "Les blocs décoratifs");

        this.app = app;

        // variables pour redéfinir proprement l'ajustement du texte
        int labelLength = 0;
        int valueLength = 0;

        CPanel panel = new CPanel(HorizontalAlignment.CENTER, Orientation.VERTICAL, 1);

        // Ajout des textes qui seront sur la partie gauche de l'écran
        CLabel deskLetterLabel = new CLabel(HorizontalAlignment.RIGHT, "D");
        if (deskLetterLabel.getLength() > labelLength) {
            labelLength = deskLetterLabel.getLength();
        }

        CLabel tableLetterLabel = new CLabel(HorizontalAlignment.RIGHT, "T");
        if (tableLetterLabel.getLength() > labelLength) {
            labelLength = tableLetterLabel.getLength();
        }

        CLabel seatLetterLabel = new CLabel(HorizontalAlignment.RIGHT, "S");
        if (seatLetterLabel.getLength() > labelLength) {
            labelLength = seatLetterLabel.getLength();
        }

        // ajustement des textes à gauche
        deskLetterLabel.setLength(labelLength);
        tableLetterLabel.setLength(labelLength);
        seatLetterLabel.setLength(labelLength);

        // Ajout des textes qui seront sur la partie droite de l'écran
        CLabel deskDescLabel = new CLabel(HorizontalAlignment.LEFT, "Bureau");
        deskDescLabel.getColors().add(CColor.YELLOW);
        if (deskDescLabel.getLength() > valueLength) {
            valueLength = deskDescLabel.getLength();
        }

        CLabel tableDescLabel = new CLabel(HorizontalAlignment.LEFT, "Table");
        tableDescLabel.getColors().add(CColor.YELLOW);
        if (tableDescLabel.getLength() > valueLength) {
            valueLength = tableDescLabel.getLength();
        }

        CLabel seatDescLabel = new CLabel(HorizontalAlignment.LEFT, "Chaise");
        seatDescLabel.getColors().add(CColor.YELLOW);
        if (seatDescLabel.getLength() > valueLength) {
            valueLength = seatDescLabel.getLength();
        }

        // ajustement des textes à droite
        deskDescLabel.setLength(valueLength);
        tableDescLabel.setLength(valueLength);
        seatDescLabel.setLength(valueLength);

        CLabel separatorLabel = new CLabel(HorizontalAlignment.LEFT, "-");

        CPanel deskPanel = new CPanel(HorizontalAlignment.CENTER, Orientation.HORIZONTAL, 1);
        deskPanel.getComponents().add(deskLetterLabel);
        deskPanel.getComponents().add(separatorLabel);
        deskPanel.getComponents().add(deskDescLabel);
        deskPanel.autoResize();

        CPanel tablePanel = new CPanel(HorizontalAlignment.CENTER, Orientation.HORIZONTAL, 1);
        tablePanel.getComponents().add(tableLetterLabel);
        tablePanel.getComponents().add(separatorLabel);
        tablePanel.getComponents().add(tableDescLabel);
        tablePanel.autoResize();

        CPanel seatPanel = new CPanel(HorizontalAlignment.CENTER, Orientation.HORIZONTAL, 1);
        seatPanel.getComponents().add(seatLetterLabel);
        seatPanel.getComponents().add(separatorLabel);
        seatPanel.getComponents().add(seatDescLabel);
        seatPanel.autoResize();

        // ajout de tous les textes au panel + ajustement
        panel.getComponents().add(deskPanel);
        panel.getComponents().add(tablePanel);
        panel.getComponents().add(seatPanel);
        panel.autoResize();

        this.getContentPane().getComponents().add(panel);

        this.setFooter(new CPanel(HorizontalAlignment.CENTER, 0, 1, Orientation.HORIZONTAL, 1));

        CPanel rightTextPanel = new CPanel(HorizontalAlignment.LEFT, Orientation.HORIZONTAL, 1);
        rightTextPanel.getComponents().add(new CLabel(HorizontalAlignment.LEFT, "Appuyez flèche gauche"));
        rightTextPanel.autoResize();

        CPanel leaveButtonPanel = new CPanel(HorizontalAlignment.CENTER, Orientation.HORIZONTAL, 1);
        leaveButtonPanel.getComponents().add(new BackButtonDecorativeBlockMenu(app, this));
        leaveButtonPanel.autoResize();

        CPanel leftTextPanel = new CPanel(HorizontalAlignment.RIGHT, Orientation.HORIZONTAL, 1);
        leftTextPanel.getComponents().add(new CLabel(HorizontalAlignment.RIGHT, "Appuyez flèche droite"));
        leftTextPanel.autoResize();

        this.getFooter().getComponents().add(rightTextPanel);
        this.getFooter().getComponents().add(leaveButtonPanel);
        this.getFooter().getComponents().add(leftTextPanel);
    }

    @Override
    public boolean isInFullScreenMode() {
        return true;
    }

    @Override
    public boolean isInLoopingMode() {
        return display;
    }

    public void stopLoopingMode() {
        display = false;
    }

    @Override
    public void onKeyPressed(KeyPressedEvent event) {
        super.onKeyPressed(event);

        try {
            Direction direction = DirectionUtils.parseDirection(event.getKey(), false);
            switch (direction) {
                case RIGHT, TOP -> app.getConsole().show(new SpecialBlockMenu(app));
                case LEFT, BOTTOM -> app.getConsole().show(new ContainerBlockMenu(app));
            }
            stopLoopingMode();
        } catch (DirectionNotFoundException ignored) {
        }
    }
}
