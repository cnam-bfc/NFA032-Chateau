package net.cnam.chateau.gui.play.cage;

import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.structure.block.container.Cage;
import net.cnam.chateau.utils.Location;

public class TakePetButton extends CButton {

    private CageMenu menu;
    private Player player;
    private Cage cage;

    public TakePetButton(CageMenu menu, Player player, Cage cage){
        super("Prendre " + cage.getPet().getName() + " en tant que compagnon !");

        this.menu = menu;
        this.player = player;
        this.cage = cage;
    }

    @Override
    public void execute() {
        player.setPet(cage.getPet());
        cage.setPet(null);
        player.getStage().addEntity(player.getPet());
        player.getPet().setStage(player.getStage()); // TODO fixer le pb autrement
        player.getPet().setLocation(new Location(player.getLocation().getX(), player.getLocation().getY())); // TODO fixer le pb autrement
        this.menu.stopDisplay();
    }
}
