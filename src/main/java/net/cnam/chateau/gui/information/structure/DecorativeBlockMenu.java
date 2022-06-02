package net.cnam.chateau.gui.information.structure;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.component.*;
import net.cnam.chateau.utils.direction.Orientation;

public class DecorativeBlockMenu extends CFrame implements DisplayableComponent {
    public DecorativeBlockMenu(App app) {
        super(0, 0, "Les blocs décoratifs");

        // variables pour re définir proprement l'ajustement du texte
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

        CPanel deskPanel = new CPanel(HorizontalAlignment.CENTER, Orientation.HORIZONTAL, 1);
        deskPanel.getComponents().add(deskLetterLabel);
        deskPanel.getComponents().add(deskDescLabel);
        deskPanel.autoResize();

        CPanel tablePanel = new CPanel(HorizontalAlignment.CENTER, Orientation.HORIZONTAL, 1);
        tablePanel.getComponents().add(tableLetterLabel);
        tablePanel.getComponents().add(tableDescLabel);
        tablePanel.autoResize();

        CPanel seatPanel = new CPanel(HorizontalAlignment.CENTER, Orientation.HORIZONTAL, 1);
        seatPanel.getComponents().add(seatLetterLabel);
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