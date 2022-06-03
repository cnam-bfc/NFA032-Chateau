package net.cnam.chateau.gui.main.menu.information;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.main.menu.information.entities.EntitiesMenu;

public class EntityButton extends CButton {

    private final App app;

    public EntityButton(App app) {
        super(app, "Afficher les entités");

        this.app = app;
    }


    @Override
    public void execute() {
        app.getConsole().show(new EntitiesMenu(app));
    }
}