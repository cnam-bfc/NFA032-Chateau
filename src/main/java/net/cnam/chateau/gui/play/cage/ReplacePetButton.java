package net.cnam.chateau.gui.play.cage;

import net.cnam.chateau.AppSettings;
import net.cnam.chateau.entity.EntityAlreadyTeleportedException;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.entity.pet.Pet;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.structure.CoordinatesOutOfBoundsException;
import net.cnam.chateau.structure.block.Cage;
import net.cnam.chateau.utils.Location;

public class ReplacePetButton extends CButton {
    private final CageMenu menu;
    private final Player player;
    private final Cage cage;

    public ReplacePetButton(AppSettings settings, CageMenu menu, Player player, Cage cage) {
        super(settings, "Remplacer " + player.getPet().getName() + " avec " + cage.getPet().getName());

        this.menu = menu;
        this.player = player;
        this.cage = cage;
    }

    @Override
    public void execute() {
        Pet pet = cage.getPet();
        player.getPet().getStage().getEntities().remove(player.getPet());
        cage.setPet(player.getPet());

        player.setPet(pet);
        pet.setPlayer(player);
        if (!player.getStage().getEntities().contains(pet))
            player.getStage().getEntities().add(pet);
        try {
            pet.teleport(player.getStage(), new Location(player.getLocation().getX(), player.getLocation().getY()));
        } catch (CoordinatesOutOfBoundsException | EntityAlreadyTeleportedException ignored) {
        }

        this.menu.stopDisplay();
    }
}
