package net.cnam.chateau.structure.block.decorative;

import net.cnam.chateau.entity.Player;
import net.cnam.chateau.event.block.BlockListener;
import net.cnam.chateau.event.block.EntityEnterBlockEvent;
import net.cnam.chateau.event.block.EntityLeaveBlockEvent;
import net.cnam.chateau.gui.Console;
import net.cnam.chateau.gui.play.bed.BedMenu;

public class Bed extends DecorativeBlock implements BlockListener {

    private boolean used = false;
    private Console console;

    public Bed(Console console) {
        super("Bed");
        this.console = console;
    }

    @Override
    public String getCharacter() {
        return "B";
    }

    /**
     * Méthode permettant d'utiliser un lit pour restaurer de la vie. Le lit est
     * utilisable une seule fois. S'il est déjà utilisé rien ne se passe S'il
     * n'a pas était utilisé passe la variable used (booleenne) à true et
     * restaure de la vie au joueur
     */
    public void use() {
        if (this.used) {
            return;
        }
        //TODO ajouter de la vie au joueur
        used = true;
    }

    public boolean isUsed() {
        return used;
    }

    @Override
    public void onEntityEnterBlock(EntityEnterBlockEvent event) {
        if (!isUsed()) {
            if (event.getEntity() instanceof Player player) {
                console.show(new BedMenu(player, this));
            }
        }
    }

    @Override
    public void onEntityLeaveBlock(EntityLeaveBlockEvent event) {
    }

}
