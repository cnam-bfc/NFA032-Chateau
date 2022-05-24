package net.cnam.chateau.structure.block;

import java.util.Random;

import net.cnam.chateau.entity.Player;
import net.cnam.chateau.entity.pet.Pet;
import net.cnam.chateau.event.block.BlockListener;
import net.cnam.chateau.event.block.EntityEnterBlockEvent;
import net.cnam.chateau.event.block.EntityLeaveBlockEvent;
import net.cnam.chateau.gui.Console;
import net.cnam.chateau.gui.play.cage.CageMenu;

/**
 * Class permettant de créer un block Cage (cage qui contient un pet) pour la
 * map.
 */
public class Cage extends Block implements BlockListener {

    private Console console;
    private Pet pet;

    public Cage(Console console, Random random) {
        this.console = console;

        if (random.nextBoolean()) {
            this.pet = Pet.getAPet(random);
        }
    }

    public Cage(Console console, Pet pet) {
        this.console = console;
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
        return "P";
    }

    @Override
    public void onEntityEnterBlock(EntityEnterBlockEvent event) {
        if (event.getEntity() instanceof Player player && (player.hasPet() || this.hasPet())) {
            console.show(new CageMenu(player, this));
        }
    }

    @Override
    public void onEntityLeaveBlock(EntityLeaveBlockEvent event) {
    }
}
