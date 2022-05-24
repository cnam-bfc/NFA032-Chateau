package net.cnam.chateau.gui.play.cage;

import net.cnam.chateau.entity.Player;
import net.cnam.chateau.entity.pet.Pet;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.structure.block.Cage;
import net.cnam.chateau.utils.Location;

public class TakePetButton extends CButton {

    private CageMenu menu;
    private Player player;
    private Cage cage;

    public TakePetButton(CageMenu menu, Player player, Cage cage) {
        super("Prendre " + cage.getPet().getName() + " en tant que compagnon !");

        this.menu = menu;
        this.player = player;
        this.cage = cage;
    }

    @Override
    public void execute() {
        Pet pet = cage.getPet();

        // On enlève le pet de la cage
        cage.setPet(null);

        // On ajoute le pet au joueur
        player.setPet(pet);
        pet.setStage(player.getStage());
        pet.setLocation(new Location(player.getLocation().getX(), player.getLocation().getY()));
        player.getStage().getEntities().add(pet);

        this.menu.stopDisplay();
    }
}
