package net.cnam.chateau.gui.information.structure;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.CButton;

public class BackButtonContainerBlockMenu extends CButton {
    private final ContainerBlockMenu menu;

    public BackButtonContainerBlockMenu(App app, ContainerBlockMenu menu) {
        super(app, "Retour");

        this.menu = menu;
        this.setSelected(true);
    }

    @Override
    public void execute() {
        menu.stopDisplaying();
    }
}

