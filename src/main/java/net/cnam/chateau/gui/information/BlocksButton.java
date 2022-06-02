package net.cnam.chateau.gui.information;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.information.structure.SpecialBlockMenu;

public class BlocksButton extends CButton {
    private final App app;

    public BlocksButton(App app) {
        super(app, "Afficher les structures");

        this.app = app;
    }

    @Override
    public void execute() {
        app.getConsole().show(new SpecialBlockMenu(app));
    }
}