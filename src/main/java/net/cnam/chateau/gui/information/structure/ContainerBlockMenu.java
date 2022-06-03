package net.cnam.chateau.gui.information.structure;

import net.cnam.chateau.App;
import net.cnam.chateau.event.key.KeyPressedEvent;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.component.*;
import net.cnam.chateau.utils.direction.Direction;
import net.cnam.chateau.utils.direction.DirectionNotFoundException;
import net.cnam.chateau.utils.direction.DirectionUtils;
import net.cnam.chateau.utils.direction.Orientation;

public class ContainerBlockMenu extends CFrame implements DisplayableComponent {
    private final App app;

    private boolean display = true;

    public ContainerBlockMenu(App app) {
        super(0, 0, "Les blocs conteneur");

        this.app = app;

        // variables pour redéfinir proprement l'ajustement du texte
        int labelLength = 0;
        int valueLength = 0;

        CPanel panel = new CPanel(HorizontalAlignment.CENTER, Orientation.VERTICAL, 1);

        // Ajout des textes qui seront sur la partie gauche de l'écran
        CLabel unVisitedChestLetterLabel = new CLabel(HorizontalAlignment.RIGHT, "C");
        if (unVisitedChestLetterLabel.getLength() > labelLength) {
            labelLength = unVisitedChestLetterLabel.getLength();
        }

        CLabel emptyChestLetterLabel = new CLabel(HorizontalAlignment.RIGHT, "C");
        emptyChestLetterLabel.getColors().add(CColor.GREEN);
        if (emptyChestLetterLabel.getLength() > labelLength) {
            labelLength = emptyChestLetterLabel.getLength();
        }

        CLabel fullChestLetterLabel = new CLabel(HorizontalAlignment.RIGHT, "C");
        fullChestLetterLabel.getColors().add(CColor.MAGENTA);
        if (fullChestLetterLabel.getLength() > labelLength) {
            labelLength = fullChestLetterLabel.getLength();
        }

        CLabel unVisitedWardrobeLetterLabel = new CLabel(HorizontalAlignment.RIGHT, "W");
        if (unVisitedWardrobeLetterLabel.getLength() > labelLength) {
            labelLength = unVisitedWardrobeLetterLabel.getLength();
        }

        CLabel emptyWardrobeLetterLabel = new CLabel(HorizontalAlignment.RIGHT, "W");
        emptyWardrobeLetterLabel.getColors().add(CColor.GREEN);
        if (emptyWardrobeLetterLabel.getLength() > labelLength) {
            labelLength = emptyWardrobeLetterLabel.getLength();
        }

        CLabel fullWardrobeLetterLabel = new CLabel(HorizontalAlignment.RIGHT, "W");
        fullWardrobeLetterLabel.getColors().add(CColor.MAGENTA);
        if (fullWardrobeLetterLabel.getLength() > labelLength) {
            labelLength = fullWardrobeLetterLabel.getLength();
        }

        CLabel unVisitedCageLetterLabel = new CLabel(HorizontalAlignment.RIGHT, "P");
        if (unVisitedCageLetterLabel.getLength() > labelLength) {
            labelLength = unVisitedCageLetterLabel.getLength();
        }

        CLabel emptyCageLetterLabel = new CLabel(HorizontalAlignment.RIGHT, "P");
        emptyCageLetterLabel.getColors().add(CColor.GREEN);
        if (emptyCageLetterLabel.getLength() > labelLength) {
            labelLength = emptyCageLetterLabel.getLength();
        }

        CLabel fullCageLetterLabel = new CLabel(HorizontalAlignment.RIGHT, "P");
        fullCageLetterLabel.getColors().add(CColor.MAGENTA);
        if (fullCageLetterLabel.getLength() > labelLength) {
            labelLength = fullCageLetterLabel.getLength();
        }

        // ajustement des textes à gauche
        unVisitedChestLetterLabel.setLength(labelLength);
        emptyChestLetterLabel.setLength(labelLength);
        fullChestLetterLabel.setLength(labelLength);
        unVisitedWardrobeLetterLabel.setLength(labelLength);
        emptyWardrobeLetterLabel.setLength(labelLength);
        fullWardrobeLetterLabel.setLength(labelLength);
        unVisitedCageLetterLabel.setLength(labelLength);
        emptyCageLetterLabel.setLength(labelLength);
        fullCageLetterLabel.setLength(labelLength);

        // Ajout des textes qui seront sur la partie droite de l'écran
        CLabel unVisitedChestDescLabel = new CLabel(HorizontalAlignment.LEFT, "Coffre (piégé) non visité");
        unVisitedChestDescLabel.getColors().add(CColor.YELLOW);
        if (unVisitedChestDescLabel.getLength() > valueLength) {
            valueLength = unVisitedChestDescLabel.getLength();
        }

        CLabel emptyChestDescLabel = new CLabel(HorizontalAlignment.LEFT, "Coffre vide");
        emptyChestDescLabel.getColors().add(CColor.YELLOW);
        if (emptyChestDescLabel.getLength() > valueLength) {
            valueLength = emptyChestDescLabel.getLength();
        }

        CLabel fullChestDescLabel = new CLabel(HorizontalAlignment.LEFT, "Coffre contenant un objet");
        fullChestDescLabel.getColors().add(CColor.YELLOW);
        if (fullChestDescLabel.getLength() > valueLength) {
            valueLength = fullChestDescLabel.getLength();
        }

        CLabel unVisitedWardrobeDescLabel = new CLabel(HorizontalAlignment.LEFT, "Armoire non visitée");
        unVisitedWardrobeDescLabel.getColors().add(CColor.YELLOW);
        if (unVisitedWardrobeDescLabel.getLength() > valueLength) {
            valueLength = unVisitedWardrobeDescLabel.getLength();
        }

        CLabel emptyWardrobeDescLabel = new CLabel(HorizontalAlignment.LEFT, "Armoire vide");
        emptyWardrobeDescLabel.getColors().add(CColor.YELLOW);
        if (emptyWardrobeDescLabel.getLength() > valueLength) {
            valueLength = emptyWardrobeDescLabel.getLength();
        }

        CLabel fullWardrobeDescLabel = new CLabel(HorizontalAlignment.LEFT, "Armoire contenant un objet");
        fullWardrobeDescLabel.getColors().add(CColor.YELLOW);
        if (fullWardrobeDescLabel.getLength() > valueLength) {
            valueLength = fullWardrobeDescLabel.getLength();
        }

        CLabel unVisitedCageDescLabel = new CLabel(HorizontalAlignment.LEFT, "Cage non visitée");
        unVisitedCageDescLabel.getColors().add(CColor.YELLOW);
        if (unVisitedCageDescLabel.getLength() > valueLength) {
            valueLength = unVisitedCageDescLabel.getLength();
        }

        CLabel emptyCageDescLabel = new CLabel(HorizontalAlignment.LEFT, "Cage vide");
        emptyCageDescLabel.getColors().add(CColor.YELLOW);
        if (emptyCageDescLabel.getLength() > valueLength) {
            valueLength = emptyCageDescLabel.getLength();
        }

        CLabel fullCageDescLabel = new CLabel(HorizontalAlignment.LEFT, "Cage contenant un familier");
        fullCageDescLabel.getColors().add(CColor.YELLOW);
        if (fullCageDescLabel.getLength() > valueLength) {
            valueLength = fullCageDescLabel.getLength();
        }

        // ajustement des textes à droite
        unVisitedChestDescLabel.setLength(valueLength);
        emptyChestDescLabel.setLength(valueLength);
        fullChestDescLabel.setLength(valueLength);
        unVisitedWardrobeDescLabel.setLength(valueLength);
        emptyWardrobeDescLabel.setLength(valueLength);
        fullWardrobeDescLabel.setLength(valueLength);
        unVisitedCageDescLabel.setLength(valueLength);
        emptyCageDescLabel.setLength(valueLength);
        fullCageDescLabel.setLength(valueLength);

        CLabel separatorLabel = new CLabel(HorizontalAlignment.LEFT, "-");

        CPanel unVisitedChestPanel = new CPanel(HorizontalAlignment.CENTER, Orientation.HORIZONTAL, 1);
        unVisitedChestPanel.getComponents().add(unVisitedChestLetterLabel);
        unVisitedChestPanel.getComponents().add(separatorLabel);
        unVisitedChestPanel.getComponents().add(unVisitedChestDescLabel);
        unVisitedChestPanel.autoResize();

        CPanel emptyChestPanel = new CPanel(HorizontalAlignment.CENTER, Orientation.HORIZONTAL, 1);
        emptyChestPanel.getComponents().add(emptyChestLetterLabel);
        emptyChestPanel.getComponents().add(separatorLabel);
        emptyChestPanel.getComponents().add(emptyChestDescLabel);
        emptyChestPanel.autoResize();

        CPanel fullChestPanel = new CPanel(HorizontalAlignment.CENTER, Orientation.HORIZONTAL, 1);
        fullChestPanel.getComponents().add(fullChestLetterLabel);
        fullChestPanel.getComponents().add(separatorLabel);
        fullChestPanel.getComponents().add(fullChestDescLabel);
        fullChestPanel.autoResize();

        CPanel unVisitedWardrobePanel = new CPanel(HorizontalAlignment.CENTER, Orientation.HORIZONTAL, 1);
        unVisitedWardrobePanel.getComponents().add(unVisitedWardrobeLetterLabel);
        unVisitedWardrobePanel.getComponents().add(separatorLabel);
        unVisitedWardrobePanel.getComponents().add(unVisitedWardrobeDescLabel);
        unVisitedWardrobePanel.autoResize();

        CPanel emptyWardrobePanel = new CPanel(HorizontalAlignment.CENTER, Orientation.HORIZONTAL, 1);
        emptyWardrobePanel.getComponents().add(emptyWardrobeLetterLabel);
        emptyWardrobePanel.getComponents().add(separatorLabel);
        emptyWardrobePanel.getComponents().add(emptyWardrobeDescLabel);
        emptyWardrobePanel.autoResize();

        CPanel fullWardrobePanel = new CPanel(HorizontalAlignment.CENTER, Orientation.HORIZONTAL, 1);
        fullWardrobePanel.getComponents().add(fullWardrobeLetterLabel);
        fullWardrobePanel.getComponents().add(separatorLabel);
        fullWardrobePanel.getComponents().add(fullWardrobeDescLabel);
        fullWardrobePanel.autoResize();

        CPanel unVisitedCagePanel = new CPanel(HorizontalAlignment.CENTER, Orientation.HORIZONTAL, 1);
        unVisitedCagePanel.getComponents().add(unVisitedCageLetterLabel);
        unVisitedCagePanel.getComponents().add(separatorLabel);
        unVisitedCagePanel.getComponents().add(unVisitedCageDescLabel);
        unVisitedCagePanel.autoResize();

        CPanel emptyCagePanel = new CPanel(HorizontalAlignment.CENTER, Orientation.HORIZONTAL, 1);
        emptyCagePanel.getComponents().add(emptyCageLetterLabel);
        emptyCagePanel.getComponents().add(separatorLabel);
        emptyCagePanel.getComponents().add(emptyCageDescLabel);
        emptyCagePanel.autoResize();

        CPanel fullCagePanel = new CPanel(HorizontalAlignment.CENTER, Orientation.HORIZONTAL, 1);
        fullCagePanel.getComponents().add(fullCageLetterLabel);
        fullCagePanel.getComponents().add(separatorLabel);
        fullCagePanel.getComponents().add(fullCageDescLabel);
        fullCagePanel.autoResize();

        // ajout de tous les textes au panel + ajustement
        panel.getComponents().add(unVisitedChestPanel);
        panel.getComponents().add(emptyChestPanel);
        panel.getComponents().add(fullChestPanel);
        panel.getComponents().add(unVisitedWardrobePanel);
        panel.getComponents().add(emptyWardrobePanel);
        panel.getComponents().add(fullWardrobePanel);
        panel.getComponents().add(unVisitedCagePanel);
        panel.getComponents().add(emptyCagePanel);
        panel.getComponents().add(fullCagePanel);
        panel.autoResize();

        this.getContentPane().getComponents().add(panel);

        this.setFooter(new CPanel(HorizontalAlignment.CENTER, 0, 1, Orientation.HORIZONTAL, 1));

        CPanel rightTextPanel = new CPanel(HorizontalAlignment.LEFT, Orientation.HORIZONTAL, 1);
        rightTextPanel.getComponents().add(new CLabel(HorizontalAlignment.LEFT, "Appuyez flèche gauche"));
        rightTextPanel.autoResize();

        CPanel leaveButtonPanel = new CPanel(HorizontalAlignment.CENTER, Orientation.HORIZONTAL, 1);
        leaveButtonPanel.getComponents().add(new BackButtonContainerBlockMenu(app, this));
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
                case RIGHT, TOP -> app.getConsole().show(new DecorativeBlockMenu(app));
                case LEFT, BOTTOM -> app.getConsole().show(new SpecialBlockMenu(app));
            }
            stopLoopingMode();
        } catch (DirectionNotFoundException ignored) {
        }
    }
}
