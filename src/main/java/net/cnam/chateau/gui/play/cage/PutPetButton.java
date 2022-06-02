package net.cnam.chateau.gui.play.cage;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.entity.pet.Pet;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.structure.block.Cage;

public class PutPetButton extends CButton {
    private final App app;
    private final CageMenu menu;
    private final Player player;
    private final Cage cage;

    public PutPetButton(App app, CageMenu menu, Player player, Cage cage) {
        super(app, "Déposer le familier");

        this.app = app;
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

        TakePetButton takePetButton = new TakePetButton(app, menu, player, cage);
        takePetButton.setSelected(true);
        menu.getButtons().replace(this, takePetButton);
    }

    public Player getPlayer() {
        return player;
    }
}