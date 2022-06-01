package net.cnam.chateau.gui.information;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.dialog.InfoDialog;

public class CreditButton extends CButton {

    private App app;

    public CreditButton(App app) {
        super(app, "Crédits");

        this.app = app;
    }


    @Override
    public void execute() {
        app.getConsole().show(new InfoDialog(InfoDialog.Type.INFO,
                "Projet crée par" +
                "\nVAIZAND Victor le maître affichage"+
                "\nANNETTE Nathan l'écrivain"+
                "\nROUAULT Alban le poseur d'idée"));
    }
}
