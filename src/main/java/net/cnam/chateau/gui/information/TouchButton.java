package net.cnam.chateau.gui.information;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.dialog.InfoDialog;

public class TouchButton extends CButton {

    private App app;

    public TouchButton(App app) {
        super(app, "Afficher les commandes");

        this.app = app;
    }


    @Override
    public void execute() {
        app.getConsole().show(new InfoDialog(InfoDialog.Type.INFO,
                "Commandes :"+
                        "\nMonter : \"flèche haut\" ou \"Z" +
                        "\nDescendre : \"flèche bas\" ou \"S" +
                        "\nGauche : \"flèche gauche\" ou \"Q" +
                        "\nDroite : \"flèche droite\" ou \"D"));
    }
}