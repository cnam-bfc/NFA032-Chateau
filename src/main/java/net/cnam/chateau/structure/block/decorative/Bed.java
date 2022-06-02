package net.cnam.chateau.structure.block.decorative;

import net.cnam.chateau.App;
import net.cnam.chateau.event.player.PlayerInteractEvent;
import net.cnam.chateau.event.player.PlayerInteractListener;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.play.bed.BedMenu;

public class Bed extends DecorativeBlock implements PlayerInteractListener {
    private final App app;
    private boolean used = false;

    public Bed(App app) {
        super("Lit");

        this.app = app;
    }

    @Override
    public String getCharacter() {
        if (this.used) {
            return CColor.MAGENTA + "B" + CColor.MAGENTA.getForegroundReset();
        } else {
            return CColor.GREEN + "B" + CColor.GREEN.getForegroundReset();
        }
    }

    public void setUsed(boolean used) {
        this.used = used;
    }

    public boolean isUsed() {
        return used;
    }

    @Override
    public void onPlayerInteract(PlayerInteractEvent event) {
        if (!isUsed()) {
            app.getConsole().show(new BedMenu(app, event.getPlayer(), this));
        }
    }
}
