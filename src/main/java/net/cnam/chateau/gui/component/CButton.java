package net.cnam.chateau.gui.component;

import net.cnam.chateau.gui.CColor;

public abstract class CButton extends CLabel implements SelectableComponent {

    private boolean selected = false;

    public CButton(String text) {
        super(text);
    }

    public CButton(String text, int length, int height) {
        super(text, length, height);
    }

    public CButton(String[] lines) {
        super(lines);
    }

    public CButton(String[] lines, int length, int height) {
        super(lines, length, height);
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
