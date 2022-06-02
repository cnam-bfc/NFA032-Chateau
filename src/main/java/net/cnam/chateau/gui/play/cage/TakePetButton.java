package net.cnam.chateau.gui.play.cage;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.EntityAlreadyTeleportedException;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.entity.pet.Pet;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.structure.CoordinatesOutOfBoundsException;
import net.cnam.chateau.structure.block.Cage;
import net.cnam.chateau.utils.Location;

public class TakePetButton extends CButton {
    private final App app;
    private final CageMenu menu;
    private final Player player;
    private final Cage cage;

    public TakePetButton(App app, CageMenu menu, Player player, Cage cage) {
        super(app, "Prendre le familier");

        this.app = app;
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
        pet.setPlayer(player);
        try {
            pet.teleport(player.getStage(), new Location(player.getLocation().getX(), player.getLocation().getY()));
        } catch (CoordinatesOutOfBoundsException | EntityAlreadyTeleportedException ignored) {
        }
        if (!player.getStage().getEntities().contains(pet))
            player.getStage().getEntities().add(pet);

        PutPetButton putPetButton = new PutPetButton(app, menu, player, cage);
        putPetButton.setSelected(true);
        menu.getButtons().replace(this, putPetButton);
    }

    public Cage getCage() {
        return cage;
    }
}
