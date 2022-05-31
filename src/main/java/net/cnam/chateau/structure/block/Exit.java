package net.cnam.chateau.structure.block;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.event.block.BlockListener;
import net.cnam.chateau.event.block.EntityEnterBlockEvent;
import net.cnam.chateau.event.block.EntityLeaveBlockEvent;
import net.cnam.chateau.gui.CColor;

public class Exit extends Block implements BlockListener {
    private final App app;

    /**
     * Constructeur
     *
     * @param app L'application
     */
    public Exit(App app) {
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
        return CColor.BRIGHT_YELLOW.getBackground() + " " + CColor.BRIGHT_YELLOW.getBackgroundReset();
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
