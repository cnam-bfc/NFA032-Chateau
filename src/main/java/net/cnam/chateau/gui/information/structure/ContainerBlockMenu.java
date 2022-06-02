package net.cnam.chateau.gui.information.structure;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.component.*;
import net.cnam.chateau.utils.direction.Orientation;

public class ContainerBlockMenu extends CFrame implements DisplayableComponent {
    public ContainerBlockMenu(App app) {
        super(0, 0, "Les blocs conteneur");

        // variables pour re définir proprement l'ajustement du texte
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
        fullChestLetterLabel.getColors().add(CColor.BRIGHT_RED);
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
        fullWardrobeLetterLabel.getColors().add(CColor.BRIGHT_RED);
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
        fullCageLetterLabel.getColors().add(CColor.BRIGHT_RED);
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

        // TODO tu en étais ici Alban

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

        CPanel deskPanel = new CPanel(HorizontalAlignment.CENTER, Orientation.HORIZONTAL, 1);
        deskPanel.getComponents().add(unVisitedCageLetterLabel);
        deskPanel.getComponents().add(deskDescLabel);
        deskPanel.autoResize();

        CPanel tablePanel = new CPanel(HorizontalAlignment.CENTER, Orientation.HORIZONTAL, 1);
        tablePanel.getComponents().add(emptyCageLetterLabel);
        tablePanel.getComponents().add(tableDescLabel);
        tablePanel.autoResize();

        CPanel seatPanel = new CPanel(HorizontalAlignment.CENTER, Orientation.HORIZONTAL, 1);
        seatPanel.getComponents().add(fullCageLetterLabel);
        seatPanel.getComponents().add(seatDescLabel);
        seatPanel.autoResize();

        // ajout de tous les textes au panel + ajustement
        panel.getComponents().add(deskPanel);
        panel.getComponents().add(tablePanel);
        panel.getComponents().add(seatPanel);
        panel.autoResize();

        this.getContentPane().getComponents().add(panel);
    }

    @Override
    public boolean isInLoopingMode() {
        return false;
    }

    @Override
    public boolean isInFullScreenMode() {
        return true;
    }
}