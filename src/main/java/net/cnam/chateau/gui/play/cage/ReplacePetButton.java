package net.cnam.chateau.gui.play.cage;

import net.cnam.chateau.entity.Player;
import net.cnam.chateau.entity.pet.Pet;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.structure.block.container.Cage;
import net.cnam.chateau.utils.Location;

public class ReplacePetButton extends CButton {

    private CageMenu menu;
    private Player player;
    private Cage cage;

    public ReplacePetButton(CageMenu menu, Player player, Cage cage){
        super("Remplacer " + player.getPet().getName() + " avec " + cage.getPet().getName());

        this.menu = menu;
        this.player = player;
        this.cage = cage;
    }

    @Override
    public void execute() {
        Pet pet = cage.getPet();
        cage.setPet(player.getPet());
        player.setPet(pet);
        player.getStage().addEntity(player.getPet());
        cage.getPet().getStage().delEntity(cage.getPet());
        player.getPet().setStage(player.getStage()); // TODO fixer le pb autrement
        player.getPet().setLocation(new Location(player.getLocation().getX(), player.getLocation().getY())); // TODO fixer le pb autrement
        this.menu.stopDisplay();
    }
}
