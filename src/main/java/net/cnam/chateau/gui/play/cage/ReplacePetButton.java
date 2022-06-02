package net.cnam.chateau.gui.play.cage;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.EntityAlreadyTeleportedException;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.entity.pet.Pet;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.structure.CoordinatesOutOfBoundsException;
import net.cnam.chateau.structure.block.Cage;
import net.cnam.chateau.utils.Location;

public class ReplacePetButton extends CButton {
    private final Player player;
    private final Cage cage;

    public ReplacePetButton(App app, Player player, Cage cage) {
        super(app, "Échanger les familiers");

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
    }

    public Player getPlayer() {
        return player;
    }

    public Cage getCage() {
        return cage;
    }
}
