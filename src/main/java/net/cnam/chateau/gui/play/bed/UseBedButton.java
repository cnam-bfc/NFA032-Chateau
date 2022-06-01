package net.cnam.chateau.gui.play.bed;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.gui.component.CButton;
import net.cnam.chateau.structure.block.decorative.Bed;

import java.util.Random;

public class UseBedButton extends CButton {
    private final BedMenu menu;
    private final Bed bed;
    private final Player player;

    public UseBedButton(App app, BedMenu menu, Bed bed, Player player) {
        super(app, "Dormir");

        this.menu = menu;
        this.bed = bed;
        this.player = player;
    }

    /**
     * Méthode permettant d'utiliser un lit pour restaurer de la vie. Le lit est
     * utilisable une seule fois. S'il est déjà utilisé rien ne se passe S'il
     * n'a pas était utilisé passe la variable used (booléenne) à true et
     * restaure de la vie au joueur
     */
    @Override
    public void execute() {
        bed.setUsed(true);
        player.heal(new Random().nextInt(10, 31));
        this.menu.stopDisplay();
    }
}
