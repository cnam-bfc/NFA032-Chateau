package net.cnam.chateau.gui.main.menu.information.commands;

import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.component.*;
import net.cnam.chateau.utils.direction.Orientation;

public class CommandsMenu extends CFrame implements DisplayableComponent {
    public CommandsMenu() {
        super(0, 0, "Les commandes");

        // variables pour redéfinir proprement l'ajustement du texte
        int labelLength = 0;
        int valueLength = 0;

        CPanel panel = new CPanel(HorizontalAlignment.CENTER, Orientation.VERTICAL, 1);

        // Ajout des textes qui seront sur la partie gauche de l'écran
        CLabel upLabel = new CLabel(HorizontalAlignment.RIGHT, "Monter :");
        upLabel.getColors().add(CColor.GREEN);
        if (upLabel.getLength() > labelLength) {
            labelLength = upLabel.getLength();
        }

        CLabel downLabel = new CLabel(HorizontalAlignment.RIGHT, "Descendre :");
        downLabel.getColors().add(CColor.GREEN);
        if (downLabel.getLength() > labelLength) {
            labelLength = downLabel.getLength();
        }

        CLabel leftLabel = new CLabel(HorizontalAlignment.RIGHT, "Gauche :");
        leftLabel.getColors().add(CColor.GREEN);
        if (leftLabel.getLength() > labelLength) {
            labelLength = leftLabel.getLength();
        }

        CLabel rightLabel = new CLabel(HorizontalAlignment.RIGHT, "Droite :");
        rightLabel.getColors().add(CColor.GREEN);
        if (rightLabel.getLength() > labelLength) {
            labelLength = rightLabel.getLength();
        }

        // ajustement des textes à gauche
        upLabel.setLength(labelLength);
        downLabel.setLength(labelLength);
        leftLabel.setLength(labelLength);
        rightLabel.setLength(labelLength);

        // Ajout des textes qui seront sur la partie droite de l'écran
        // première commande (haut)
        CLabel upDescOne = new CLabel(HorizontalAlignment.LEFT, "Flèche haut");
        upDescOne.getColors().add(CColor.RED);

        CLabel upDescTwo = new CLabel(HorizontalAlignment.LEFT, "ou");
        upDescTwo.getColors().add(CColor.YELLOW);

        CLabel upDescThree = new CLabel(HorizontalAlignment.LEFT, "Z");
        upDescThree.getColors().add(CColor.RED);

        CPanel upDescription = new CPanel(HorizontalAlignment.CENTER, Orientation.HORIZONTAL, 1);
        upDescription.getComponents().add(upDescOne);
        upDescription.getComponents().add(upDescTwo);
        upDescription.getComponents().add(upDescThree);
        upDescription.autoResize();
        if (upDescription.getLength() > valueLength) {
            valueLength = upDescription.getLength();
        }

        CPanel upValue = new CPanel(HorizontalAlignment.LEFT, 0, 0, Orientation.HORIZONTAL, false);
        upValue.getComponents().add(upDescription);
        upValue.autoResize();

        // deuxième commande (bas)
        CLabel downDescOne = new CLabel(HorizontalAlignment.LEFT, "Flèche bas");
        downDescOne.getColors().add(CColor.RED);

        CLabel downDescTwo = new CLabel(HorizontalAlignment.LEFT, "ou");
        downDescTwo.getColors().add(CColor.YELLOW);

        CLabel downDescThree = new CLabel(HorizontalAlignment.LEFT, "S");
        downDescThree.getColors().add(CColor.RED);

        CPanel downDescription = new CPanel(HorizontalAlignment.LEFT, Orientation.HORIZONTAL, 1);
        downDescription.getComponents().add(downDescOne);
        downDescription.getComponents().add(downDescTwo);
        downDescription.getComponents().add(downDescThree);
        downDescription.autoResize();
        if (downDescription.getLength() > valueLength) {
            valueLength = downDescription.getLength();
        }

        CPanel downValue = new CPanel(HorizontalAlignment.LEFT, 0, 0, Orientation.HORIZONTAL, false);
        downValue.getComponents().add(downDescription);
        downValue.autoResize();

        // troisième commande (gauche)
        CLabel leftDescOne = new CLabel(HorizontalAlignment.LEFT, "Flèche gauche");
        leftDescOne.getColors().add(CColor.RED);

        CLabel leftDescTwo = new CLabel(HorizontalAlignment.LEFT, "ou");
        leftDescTwo.getColors().add(CColor.YELLOW);

        CLabel leftDescThree = new CLabel(HorizontalAlignment.LEFT, "Q");
        leftDescThree.getColors().add(CColor.RED);

        CPanel leftDescription = new CPanel(HorizontalAlignment.LEFT, Orientation.HORIZONTAL, 1);
        leftDescription.getComponents().add(leftDescOne);
        leftDescription.getComponents().add(leftDescTwo);
        leftDescription.getComponents().add(leftDescThree);
        leftDescription.autoResize();
        if (leftDescription.getLength() > valueLength) {
            valueLength = leftDescription.getLength();
        }

        CPanel leftValue = new CPanel(HorizontalAlignment.LEFT, 0, 0, Orientation.HORIZONTAL, false);
        leftValue.getComponents().add(leftDescription);
        leftValue.autoResize();

        // quatrième commande (droite)
        CLabel rightDescOne = new CLabel(HorizontalAlignment.LEFT, "Flèche droite");
        rightDescOne.getColors().add(CColor.RED);

        CLabel rightDescTwo = new CLabel(HorizontalAlignment.LEFT, "ou");
        rightDescTwo.getColors().add(CColor.YELLOW);

        CLabel rightDescThree = new CLabel(HorizontalAlignment.LEFT, "D");
        rightDescThree.getColors().add(CColor.RED);

        CPanel rightDescription = new CPanel(HorizontalAlignment.LEFT, Orientation.HORIZONTAL, 1);
        rightDescription.getComponents().add(rightDescOne);
        rightDescription.getComponents().add(rightDescTwo);
        rightDescription.getComponents().add(rightDescThree);
        rightDescription.autoResize();
        if (rightDescription.getLength() > valueLength) {
            valueLength = rightDescription.getLength();
        }

        CPanel rightValue = new CPanel(HorizontalAlignment.LEFT, 0, 0, Orientation.HORIZONTAL, false);
        rightValue.getComponents().add(rightDescription);
        rightValue.autoResize();

        // ajustement des textes à droite
        upValue.setLength(valueLength);
        downValue.setLength(valueLength);
        leftValue.setLength(valueLength);
        rightValue.setLength(valueLength);

        CPanel upPanel = new CPanel(HorizontalAlignment.CENTER, Orientation.HORIZONTAL, 1);
        upPanel.getComponents().add(upLabel);
        upPanel.getComponents().add(upValue);
        upPanel.autoResize();

        CPanel downPanel = new CPanel(HorizontalAlignment.CENTER, Orientation.HORIZONTAL, 1);
        downPanel.getComponents().add(downLabel);
        downPanel.getComponents().add(downValue);
        downPanel.autoResize();

        CPanel leftPanel = new CPanel(HorizontalAlignment.CENTER, Orientation.HORIZONTAL, 1);
        leftPanel.getComponents().add(leftLabel);
        leftPanel.getComponents().add(leftValue);
        leftPanel.autoResize();

        CPanel rightPanel = new CPanel(HorizontalAlignment.CENTER, Orientation.HORIZONTAL, 1);
        rightPanel.getComponents().add(rightLabel);
        rightPanel.getComponents().add(rightValue);
        rightPanel.autoResize();

        // ajout de tous les textes au panel + ajustement
        panel.getComponents().add(upPanel);
        panel.getComponents().add(downPanel);
        panel.getComponents().add(leftPanel);
        panel.getComponents().add(rightPanel);
        panel.autoResize();

        this.getContentPane().getComponents().add(new CLabel(HorizontalAlignment.CENTER, "Les commandes:"));
        this.getContentPane().getComponents().add(panel);
    }

    @Override
    public boolean isInFullScreenMode() {
        return true;
    }

    @Override
    public boolean isInLoopingMode() {
        return false;
    }

    @Override
    public void stopLoopingMode() {
    }
}