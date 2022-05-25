package net.cnam.chateau.gui.component;

import net.cnam.chateau.event.key.KeyListener;
import net.cnam.chateau.event.key.KeyPressedEvent;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.utils.StringUtils;
import net.cnam.chateau.utils.direction.Direction;
import net.cnam.chateau.utils.direction.DirectionNotFoundException;
import net.cnam.chateau.utils.direction.DirectionUtils;

public class CTextField extends CComponent implements SelectableComponent, KeyListener {
    private boolean selected = true;
    private String text;
    private int pointer;

    public CTextField(int length) {
        this(HorizontalAlignment.CENTER, length);
    }

    public CTextField(HorizontalAlignment horizontalAlignment, int length) {
        this(horizontalAlignment, null, length);
    }

    public CTextField(String text, int length) {
        this(HorizontalAlignment.CENTER, text, length);
    }

    public CTextField(HorizontalAlignment horizontalAlignment, String text, int length) {
        super(horizontalAlignment, length, 1);

        if (text != null) {
            this.text = text;
            this.pointer = text.length();
        } else {
            this.text = "";
            this.pointer = 0;
        }
    }

    public String getText() {
        return text;
    }

    @Override
    public String[] render() {
        String[] result = new String[this.getHeight()];
        int linePointer = 0;
        String emptyLine = " ".repeat(this.getLength());

        // Lignes de la console - lignes de texte au milieu
        int paddingHeight = this.getHeight() - 1;
        for (int i = 0; i < paddingHeight / 2; i++) {
            result[linePointer++] = emptyLine;
        }

        String line;
        if (selected) {
            line = text.substring(0, pointer);
            line += "" + CColor.BLINKING + CColor.REVERSE + " " + CColor.REVERSE.getForegroundReset() + CColor.BLINKING.getForegroundReset();
            if (text.length() >= this.getLength()) {
                line += text.substring(pointer, this.getLength() - 1);
            } else {
                // TODO Faire le rendu en fonction de l'alignement
                line += text.substring(pointer);
                line = StringUtils.centerString(line, ' ', this.getLength()
                        + CColor.BLINKING.getForeground().length() + CColor.BLINKING.getForegroundReset().length()
                        + CColor.REVERSE.getForeground().length() + CColor.REVERSE.getForegroundReset().length());
            }
        } else {
            if (text.length() > this.getLength()) {
                line = text.substring(0, this.getLength());
            } else {
                line = StringUtils.centerString(text, ' ', this.getLength());
            }
        }

        result[linePointer++] = line;

        // Bourrage à la fin
        for (; linePointer < result.length; linePointer++) {
            result[linePointer] = emptyLine;
        }

        return result;
    }

    @Override
    public void onKeyPressed(KeyPressedEvent event) {
        if (!isSelected()) {
            return;
        }

        int key = event.getKey();

        try {
            Direction direction = DirectionUtils.parseDirection(key);
            switch (direction) {
                case LEFT -> {
                    if (pointer > 0) {
                        pointer--;
                    }
                }
                case RIGHT -> {
                    if (pointer < text.length()) {
                        pointer++;
                    }
                }
            }
            return;
        } catch (DirectionNotFoundException ignored) {
        }

        if (text.length() > 0) {
            // 8 = backspace ; 127 = DEL
            if ((key == 8 || key == 127) && pointer > 0) {
                String newText = text.substring(0, pointer - 1);
                newText += text.substring(pointer);
                text = newText;
                pointer--;
                // 57427 = suppr
            } else if (key == 57427 && pointer < text.length()) {
                String newText = text.substring(0, pointer);
                newText += text.substring(pointer + 1);
                text = newText;
            }
        }

        // Si caractères spéciaux alors on n'ajoute pas le caractère
        // Source: https://upload.wikimedia.org/wikipedia/commons/1/1b/ASCII-Table-wide.svg
        if (key < 32 || key == 127 || key == 57427) {
            return;
        }

        char character = (char) key;
        String newText = text.substring(0, pointer);
        newText += character;
        newText += text.substring(pointer);
        text = newText;
        pointer++;
    }

    @Override
    public boolean isSelected() {
        return selected;
    }

    @Override
    public void setSelected(boolean selected) {
        this.selected = selected;
    }
}
