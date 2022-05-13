package net.cnam.chateau.gui.component;

import net.cnam.chateau.utils.StringUtils;

public class CTextField extends CComponent {

    private String text = "";
//    private int pointer = 0;

    public CTextField(String text, int length, int height) {
        super(length, height);

        this.text = text;
    }

    public String getText() {
        return text;
    }

    @Override
    public String[] render() {
        String[] result = new String[this.getHeight()];
        int linePointer = 0;
        String emptyLine = " ".repeat(this.getLength());

        // Lignes de la console - lignes de texte au millieu
        int paddingHeight = this.getHeight() - 1;
        for (int i = 0; i < paddingHeight / 2; i++) {
            result[linePointer++] = emptyLine;
        }

        String line = text;
        if (line.length() > this.getLength()) {
            line = line.substring(0, this.getLength());
        } else if (line.length() < this.getLength()) {
            line = StringUtils.centerString(line, ' ', this.getLength());
        }

        result[linePointer++] = line;

        // Bourage à la fin
        for (; linePointer < result.length; linePointer++) {
            result[linePointer] = emptyLine;
        }

        return result;
    }

    @Override
    public void onKeyPressed(int key) {
        char character = (char) key;

        text += character;
    }
}
