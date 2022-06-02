package net.cnam.chateau.gui.information.structure;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.CButton;

public class BackButtonDecorativeBlockMenu extends CButton {
    private final DecorativeBlockMenu menu;

    public BackButtonDecorativeBlockMenu(App app, DecorativeBlockMenu menu) {
        super(app, "Retour");

        this.menu = menu;
        this.setSelected(true);
    }

    @Override
    public void execute() {
        menu.stopDisplaying();
    }
}
