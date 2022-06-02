package net.cnam.chateau.gui.information.commands;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.component.*;
import net.cnam.chateau.utils.direction.Orientation;

public class CommandsMenu extends CFrame implements DisplayableComponent {
    public CommandsMenu(App app) {
        super(0, 0, "Les commandes");

        // variables pour re définir proprement l'ajustement du texte
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

        CPanel upValue = new CPanel(HorizontalAlignment.LEFT, Orientation.HORIZONTAL, 1);
        upValue.getComponents().add(upDescOne);
        upValue.getComponents().add(upDescTwo);
        upValue.getComponents().add(upDescThree);
        upValue.autoResize();
        if(upValue.getLength() > valueLength) {
            valueLength = upValue.getLength();
        }

        // deuxième commande (bas)
        CLabel downDescOne = new CLabel(HorizontalAlignment.LEFT, "Flèche bas");
        downDescOne.getColors().add(CColor.RED);

        CLabel downDescTwo = new CLabel(HorizontalAlignment.LEFT, "ou");
        downDescTwo.getColors().add(CColor.YELLOW);

        CLabel downDescThree = new CLabel(HorizontalAlignment.LEFT, "S");
        downDescThree.getColors().add(CColor.RED);

        CPanel downValue = new CPanel(HorizontalAlignment.LEFT, Orientation.HORIZONTAL, 1);
        downValue.getComponents().add(downDescOne);
        downValue.getComponents().add(downDescTwo);
        downValue.getComponents().add(downDescThree);
        downValue.autoResize();
        if(downValue.getLength() > valueLength) {
            valueLength = downValue.getLength();
        }

        // troisième commande (gauche)
        CLabel leftDescOne = new CLabel(HorizontalAlignment.LEFT, "Flèche gauche");
        leftDescOne.getColors().add(CColor.RED);

        CLabel leftDescTwo = new CLabel(HorizontalAlignment.LEFT, "ou");
        leftDescTwo.getColors().add(CColor.YELLOW);

        CLabel leftDescThree = new CLabel(HorizontalAlignment.LEFT, "Q");
        leftDescThree.getColors().add(CColor.RED);

        CPanel leftValue = new CPanel(HorizontalAlignment.LEFT, Orientation.HORIZONTAL, 1);
        leftValue.getComponents().add(leftDescOne);
        leftValue.getComponents().add(leftDescTwo);
        leftValue.getComponents().add(leftDescThree);
        leftValue.autoResize();
        if(leftValue.getLength() > valueLength) {
            valueLength = leftValue.getLength();
        }

        // quatrième commande (droite)
        CLabel rightDescOne = new CLabel(HorizontalAlignment.LEFT, "Flèche droite");
        rightDescOne.getColors().add(CColor.RED);

        CLabel rightDescTwo = new CLabel(HorizontalAlignment.LEFT, "ou");
        rightDescTwo.getColors().add(CColor.YELLOW);

        CLabel rightDescThree = new CLabel(HorizontalAlignment.LEFT, "D");
        rightDescThree.getColors().add(CColor.RED);

        CPanel rightValue = new CPanel(HorizontalAlignment.LEFT, Orientation.HORIZONTAL, 1);
        rightValue.getComponents().add(rightDescOne);
        rightValue.getComponents().add(rightDescTwo);
        rightValue.getComponents().add(rightDescThree);
        rightValue.autoResize();
        if(rightValue.getLength() > valueLength) {
            valueLength = rightValue.getLength();
        }

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
    public boolean isInLoopingMode() {
        return false;
    }

    @Override
    public boolean isInFullScreenMode() {
        return true;
    }


}