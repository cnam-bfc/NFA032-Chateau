package net.cnam.gui.component;

import net.cnam.utils.StringUtils;

public abstract class CComponent {

    private int length;
    private int height;

    public CComponent(int length, int height) {
        this.length = length;
        this.height = height;
    }

    public abstract String[] render();

    public abstract void onKeyPressed(int key);

    public void setSize(int length, int height) {
        this.setLength(length);
        this.setHeight(height);
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    @Override
    public String toString() {
        return StringUtils.convertStringArrayToString(render());
    }
}
