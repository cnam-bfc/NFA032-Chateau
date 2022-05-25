package net.cnam.chateau.gui.play.cage;

import net.cnam.chateau.AppSettings;
import net.cnam.chateau.entity.EntityAlreadyTeleportedException;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.entity.pet.Pet;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.structure.CoordinatesOutOfBoundsException;
import net.cnam.chateau.structure.block.Cage;
import net.cnam.chateau.utils.Location;

public class TakePetButton extends CButton {
    private final CageMenu menu;
    private final Player player;
    private final Cage cage;

    public TakePetButton(AppSettings settings, CageMenu menu, Player player, Cage cage) {
        super(settings, "Prendre " + cage.getPet().getName() + " en tant que compagnon !");

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
        try {
            pet.teleport(player.getStage(), new Location(player.getLocation().getX(), player.getLocation().getY()));
        } catch (CoordinatesOutOfBoundsException | EntityAlreadyTeleportedException ignored) {
        }
        if (!player.getStage().getEntities().contains(pet))
            player.getStage().getEntities().add(pet);

        this.menu.stopDisplay();
    }
}
