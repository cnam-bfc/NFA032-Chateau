package net.cnam.chateau.structure.block;

import net.cnam.chateau.App;
import net.cnam.chateau.event.player.PlayerInteractEvent;
import net.cnam.chateau.event.player.PlayerInteractListener;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.play.bed.BedMenu;

/**
 * Classe permettant de créer un block Lit
 */
public class Bed extends Block implements PlayerInteractListener {
    private final App app;
    private boolean used = false;

    /**
     * Constructeur de la classe Bed
     * @param app L'application
     */
    public Bed(App app) {
        super("Lit");

        this.app = app;
    }

    /**
     * Méthode permettant de récupérer le caractère représentant le block sur la carte
     * @return Le caractère représentant le block sur la carte
     */
    @Override
    public String getCharacter() {
        if (this.used) {
            return CColor.MAGENTA + "B" + CColor.MAGENTA.getForegroundReset();
        } else {
            return CColor.GREEN + "B" + CColor.GREEN.getForegroundReset();
        }
    }

    /**
     * Méthode d'altération permettant de définir si le block est utilisé
     * @param used True si le block est utilisé, false sinon
     */
    public void setUsed(boolean used) {
        this.used = used;
    }

    /**
     * Méthode permettant de voir si le lit a été utilisé
     * @return True si le lit a été utilisé, false sinon
     */
    public boolean isUsed() {
        return used;
    }

    /**
     * Méthode permettant de réagir à un événement de type PlayerInteractEvent
     * @param event L'événement de type PlayerInteractEvent
     */
    @Override
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (!isUsed()) {
            app.getConsole().show(new BedMenu(app, event.getPlayer(), this));
        }
    }
}
