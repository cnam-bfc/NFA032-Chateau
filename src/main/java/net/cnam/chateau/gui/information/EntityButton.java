package net.cnam.chateau.gui.information;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.dialog.InfoDialog;

public class EntityButton extends CButton {

    private App app;

    public EntityButton(App app) {
        super(app, "Afficher les entités");

        this.app = app;
    }


    @Override
    public void execute() {
        app.getConsole().show(new InfoDialog(InfoDialog.Type.INFO,
                "Les différentes entités " +
                        "\nD rouge clair : Demogorgon" +
                        "\nH rouge clair : Harpie" +
                        "\nK rouge clair : Cavalier sans tête" +
                        "\nM rouge clair : Morbol" +
                        "\nS rouge clair : Araignée" +
                        "\nW rouge clair : Loup-garou" +
                        "\nZ rouge clair : Zombie" +
                        "\nP bleu clair : familier qui vous suit" +
                        "\nS rouge foncé : Un sage bloquant une porte"));
    }
}