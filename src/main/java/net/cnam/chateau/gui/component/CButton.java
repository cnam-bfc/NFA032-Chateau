package net.cnam.chateau.gui.component;

import net.cnam.chateau.gui.CColor;

public abstract class CButton extends CLabel implements SelectableComponent {

    private boolean selected = false;

    public CButton(String text) {
        this(HorizontalAlignment.CENTER, text);
    }

    public CButton(HorizontalAlignment horizontalAlignment, String text) {
        super(horizontalAlignment, text);
    }

    public CButton(String text, int length) {
        this(HorizontalAlignment.CENTER, text, length);
    }

    public CButton(HorizontalAlignment horizontalAlignment, String text, int length) {
        super(horizontalAlignment, text, length);
    }

    public CButton(String text, int length, int height) {
        this(HorizontalAlignment.CENTER, text, length, height);
    }

    public CButton(HorizontalAlignment horizontalAlignment, String text, int length, int height) {
        super(horizontalAlignment, text, length, height);
    }

    public abstract void execute();

    @Override
    public String[] render() {
        if (selected) {
            if (!this.getColors().contains(CColor.REVERSE)) {
                this.getColors().add(CColor.REVERSE);
            }
        } else {
            this.getColors().remove(CColor.REVERSE);
        }

        return super.render();
    }

    @Override
    public void onKeyPressed(int key) {
        super.onKeyPressed(key);

        // 10 = Entrée dans netbeans ; 13 = Entrée dans un terminal
        if (this.isSelected() && (key == 10 || key == 13)) {
            this.execute();
        }
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
