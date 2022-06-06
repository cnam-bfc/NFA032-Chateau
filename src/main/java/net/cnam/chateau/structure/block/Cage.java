package net.cnam.chateau.structure.block;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.entity.Pet;
import net.cnam.chateau.event.player.PlayerInteractEvent;
import net.cnam.chateau.event.player.PlayerInteractListener;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.play.cage.CageMenu;

/**
 * Class permettant de créer un block Cage (cage qui contient un pet) pour la
 * map.
 */
public class Cage extends Block implements PlayerInteractListener {
    private final App app;
    private Pet pet;
    private boolean visited;

    public Cage(App app) {
        this(app, null);
    }

    /**
     * Constructeur
     *
     * @param app L'application
     * @param pet Le familier dans la cage
     */
    public Cage(App app, Pet pet) {
        super("Cage");

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
                return CColor.MAGENTA + "P" + CColor.MAGENTA.getForegroundReset();
            } else {
                return CColor.GREEN + "P" + CColor.GREEN.getForegroundReset();
            }
        }
        return "P";
    }


    @Override
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        visited = true;
        if (player.hasPet() || this.hasPet()) {
            app.getConsole().show(new CageMenu(app, player, this));
        }
    }
}
