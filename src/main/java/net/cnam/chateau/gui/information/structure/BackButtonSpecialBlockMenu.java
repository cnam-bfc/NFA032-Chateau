package net.cnam.chateau.gui.information.structure;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.CButton;

public class BackButtonSpecialBlockMenu extends CButton {
    private final SpecialBlockMenu menu;

    public BackButtonSpecialBlockMenu(App app, SpecialBlockMenu menu) {
        super(app, "Retour");

        this.menu = menu;
        this.setSelected(true);
    }

    @Override
    public void execute() {
        menu.stopDisplaying();
    }
}
