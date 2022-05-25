package net.cnam.chateau.structure.block;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.entity.pet.Pet;
import net.cnam.chateau.event.block.BlockListener;
import net.cnam.chateau.event.block.EntityEnterBlockEvent;
import net.cnam.chateau.event.block.EntityLeaveBlockEvent;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.play.cage.CageMenu;

import java.util.Random;

/**
 * Class permettant de créer un block Cage (cage qui contient un pet) pour la
 * map.
 */
public class Cage extends Block implements BlockListener {

    private final App app;
    private Pet pet;
    private boolean visited;

    public Cage(App app, Random random) {
        this.app = app;

        if (random.nextBoolean()) {
            this.pet = Pet.getAPet(random);
        }
    }

    public Cage(App app, Pet pet) {
        this.app = app;
        this.pet = pet;
    }

    public Pet getPet() {
        return pet;
    }

    public void setPet(Pet pet) {
        this.pet = pet;
    }

    public boolean hasPet() {
        return pet != null;
    }

    @Override
    public String getCharacter() {
        if (this.visited) {
            if (this.hasPet()) {
                return CColor.BRIGHT_RED + "P" + CColor.BRIGHT_RED.getForegroundReset();
            } else {
                return CColor.GREEN + "P" + CColor.GREEN.getForegroundReset();
            }
        }
        return "P";
    }

    @Override
    public void onEntityEnterBlock(EntityEnterBlockEvent event) {
        visited = true;
        if (event.getEntity() instanceof Player player && (player.hasPet() || this.hasPet())) {
            app.getConsole().show(new CageMenu(app.getSettings(), player, this));
        }
    }

    @Override
    public void onEntityLeaveBlock(EntityLeaveBlockEvent event) {
    }
}
