package net.cnam.chateau.gui.information;

import net.cnam.chateau.App;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.gui.dialog.InfoDialog;

public class BlocksButton extends CButton {

    private App app;

    public BlocksButton(App app) {
        super(app, "Afficher les structures");

        this.app = app;
    }


    @Override
    public void execute() {
        app.getConsole().show(new InfoDialog(InfoDialog.Type.INFO,
                "Les différentes structures " +
                        "\nmettre un mur : mur" +
                        "\nD vert : Porte passable" +
                        "\nD rouge : Porte bloquée par une clé" +
                        "\nC blanc : Coffre OU Coffre piégé" +
                        "\nC vert : Coffre vide" +
                        "\nC magenta : Coffre contenant un objet" +
                        "\nW blanc : Armoire non visitée" +
                        "\nW vert : Armoire ne contenant pas d'objet" +
                        "\nW magenta : Armoire contenant un objet" +
                        "\nB blanc : Lit non utilisé" +
                        "\nB magenta : Lit utilisé" +
                        "\nP blanc : cage non visité" +
                        "\nP vert : cage vide" +
                        "\nD S T : bureau / chaise / table (blocs décoratifs)" +
                        "\nU dorée : Escalier permettant de monter d'un étage"+
                        "\nD dorée : Escalier permettant de descendre d'un étage" +
                        "\nEspace doré : Sortie du château"));
    }
}