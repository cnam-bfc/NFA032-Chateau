package net.cnam.chateau.structure.block.door;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.event.block.BlockListener;
import net.cnam.chateau.event.block.EntityEnterBlockEvent;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.play.door.trap.TrappedDoorMenu;
import net.cnam.chateau.structure.Room;
import net.cnam.chateau.structure.Stage;
import net.cnam.chateau.structure.block.trap.BadTrap;
import net.cnam.chateau.structure.block.trap.GoodTrap;
import net.cnam.chateau.structure.block.trap.Trap;

import java.util.Random;

/**
 * Classe permettant de créer un block Porte Piégée
 */
public class TrappedDoor extends Door implements BlockListener {
    private final App app;
    private final Trap trap;

    /**
     * Constructeur de la classe Porte Piégée
     * @param app       L'application
     * @param stage     L'étage où se situe la porte
     * @param roomOne   La première pièce de la porte
     * @param roomTwo   La deuxième pièce de la porte
     * @param random    Le random de la partie
     */
    public TrappedDoor(App app, Stage stage, Room roomOne, Room roomTwo, Random random) {
        super(stage, roomOne, roomTwo);

        this.app = app;
        if (random.nextInt(0, 3) == 0) {
            this.trap = new GoodTrap(random);
        } else {
            this.trap = new BadTrap(random);
        }
    }

    /**
     * Méthode d'accès au piège
     * @return Le piège
     */
    public Trap getTrap() {
        return trap;
    }

    /**
     * Méthode permettant de savoir si le porte de la piège a été activée
     * @return True si le porte de la piège a été activée, false sinon
     */
    @Override
    public boolean isLocked() {
        return !this.trap.isUsed();
    }

    @Override
    public void onEntityEnterBlock(EntityEnterBlockEvent event) {
        if (isLocked()) {
            if (event.getEntity() instanceof Player player) {
                app.getConsole().show(new TrappedDoorMenu(app, player, this));
            }
        }
        super.onEntityEnterBlock(event);
    }

    /**
     * Méthode permettant de récupérer le caractère représentant la porte.
     * "D" vert
     * @return Le caractère représentant la porte
     */
    @Override
    public String getCharacter() {
        return CColor.GREEN + "D" + CColor.GREEN.getForegroundReset();
    }
}
