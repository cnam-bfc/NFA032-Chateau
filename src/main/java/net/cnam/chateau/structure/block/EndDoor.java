package net.cnam.chateau.structure.block;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.event.block.BlockListener;
import net.cnam.chateau.event.block.EntityEnterBlockEvent;
import net.cnam.chateau.event.block.EntityLeaveBlockEvent;
import net.cnam.chateau.gui.CColor;

public class EndDoor extends Block implements BlockListener {

    private App app;

    /**
     * Constructeur
     *
     * @param app L'application
     */
    public EndDoor(App app) {
        this.app = app;
    }

    /**
     * Redéfinition de la méthode permettant d'afficher un caractère sur la carte.
     * Ici on affiche la porte de sortie avec un espace jaune dans un mur.
     *
     * @return Un espace jaune
     */
    @Override
    public String getCharacter() {
        return CColor.BRIGHT_YELLOW + " " + CColor.BRIGHT_YELLOW.getForegroundReset();
    }

    @Override
    public void onEntityEnterBlock(EntityEnterBlockEvent event) {
        if (event.getEntity() instanceof Player player) {
            //app.getConsole().show(new FinishMenu(app, player, this));
        }
    }

    @Override
    public void onEntityLeaveBlock(EntityLeaveBlockEvent event) {

    }
}
