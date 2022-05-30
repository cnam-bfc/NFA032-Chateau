package net.cnam.chateau.structure.block;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.entity.pet.Pet;
import net.cnam.chateau.event.block.BlockListener;
import net.cnam.chateau.event.block.EntityEnterBlockEvent;
import net.cnam.chateau.event.block.EntityLeaveBlockEvent;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.play.cage.CageMenu;

/**
 * Class permettant de créer un block Cage (cage qui contient un pet) pour la
 * map.
 */
public class Cage extends Block implements BlockListener {
    private final App app;
    private Pet pet;
    private boolean visited;

    public Cage(App app) {
        this(app, null);
    }

    /**
     * Constructeur
     * 
     * @param app   L'application
     * @param pet   Le familier dans la cage
     */
    public Cage(App app, Pet pet) {
        this.app = app;
        this.pet = pet;
    }

    /**
     * Getter permettant de récupérer le familier dans la cage.
     * 
     * @return le familier dans la cage (Pet)
     */
    public Pet getPet() {
        return pet;
    }

    /**
     * Setter permettant de mettre un familier dans la cage.
     * 
     * @param pet le familier à mettre dans la cage
     */
    public void setPet(Pet pet) {
        this.pet = pet;
    }

    /**
     * Méthode permettant de vérifier s'il y a un familier dans la cage.
     * 
     * @return true : il y a un familier / false : il n'y a pas de familier
     */
    public boolean hasPet() {
        return pet != null;
    }

    /**
     * Redéfinition de la méthode permettant d'afficher un caractère sur la carte.
     * 
     * @return un String correspondant à "P" en rouge si un familier est dans la cage, sinon en vert
     */
    @Override
    public String getCharacter() {
        if (this.visited) {
            if (this.hasPet()) {
                return CColor.BRIGHT_RED + "P" + CColor.BRIGHT_RED.getForegroundReset();
            } else {
                return CColor.GREEN + "P" + CColor.GREEN.getForegroundReset();
            }
        }
        return "P";
    }

    /**
     * TODO victor
     * 
     * @param event
     */
    @Override
    public void onEntityEnterBlock(EntityEnterBlockEvent event) {
        visited = true;
        if (event.getEntity() instanceof Player player && (player.hasPet() || this.hasPet())) {
            app.getConsole().show(new CageMenu(app, player, this));
        }
    }

    /**
     * TODO Victor
     * 
     * @param event
     */
    @Override
    public void onEntityLeaveBlock(EntityLeaveBlockEvent event) {
    }
}
