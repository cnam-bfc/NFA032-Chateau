package net.cnam.chateau.gui.play.cage;

import net.cnam.chateau.entity.Player;
import net.cnam.chateau.entity.pet.Pet;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.structure.block.Cage;

public class DropPetButton extends CButton {

    private CageMenu menu;
    private Player player;
    private Cage cage;

    public DropPetButton(CageMenu menu, Player player, Cage cage) {
        super("Deposer " + player.getPet().getName());

        this.menu = menu;
        this.player = player;
        this.cage = cage;
    }

    @Override
    public void execute() {
        Pet pet = player.getPet();

        cage.setPet(pet);
        player.setPet(null);
        pet.getStage().getEntities().remove(pet);

        this.menu.stopDisplay();
    }
}