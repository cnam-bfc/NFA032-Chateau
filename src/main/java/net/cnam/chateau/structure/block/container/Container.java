package net.cnam.chateau.structure.block.container;

import net.cnam.chateau.App;
import net.cnam.chateau.entity.Player;
import net.cnam.chateau.event.player.PlayerInteractEvent;
import net.cnam.chateau.event.player.PlayerInteractListener;
import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.play.container.ContainerMenu;
import net.cnam.chateau.item.Item;
import net.cnam.chateau.structure.block.Block;

public abstract class Container extends Block implements PlayerInteractListener {
    private final App app;
    private Item hiddenItem;
    private boolean opened = false;

    /**
     * Constructeur
     *
     * @param app        L'application
     * @param hiddenItem Objet dans le container
     */
    public Container(App app, String name, Item hiddenItem) {
        super(name);

        this.app = app;
        this.hiddenItem = hiddenItem;
    }

    /**
     * Méthode pour savoir si le container contient un objet.
     *
     * @return true si un objest est dans le coffre / false si aucun objet est dans le coffre
     */
    public boolean hasItem() {
        return this.hiddenItem != null;
    }

    /**
     * Getter permettant de récupérer l'objet dans le coffre.
     *
     * @return l'objet dans le coffre
     */
    public Item getHiddenItem() {
        return hiddenItem;
    }

    /**
     * Setter permettant de définir un objet dans le coffre.
     *
     * @param hiddenItem l'objet à mettre dans le coffre
     */
    public void setHiddenItem(Item hiddenItem) {
        this.hiddenItem = hiddenItem;
    }


    /**
     * Méthode permettant d'afficher un caractère sur la carte.
     * Ici on récupère le caractère de la classe fille et on lui met la couleur en fonction de son contenu.
     *
     * @param string le caractère à afficher sans couleur (String)
     * @return le caractère à afficher (String)
     */
    public String getCharacter(String string) {
        if (this.opened) {
            if (this.hasItem()) {
                return CColor.MAGENTA + string + CColor.MAGENTA.getForegroundReset();
            } else {
                return CColor.GREEN + string + CColor.GREEN.getForegroundReset();
            }
        }
        return string;
    }

    /**
     * Redéfinition de la méthode permettant de savoir quand une entité entre en contact avec le bloc.
     *
     * @param event l'évènement quand un joueur interagit avec le bloc
     */
    @Override
    public void onPlayerInteract(PlayerInteractEvent event) {
        Player player = event.getPlayer();
        this.opened = true;
        if (this.hasItem() || player.hasItem() || player.hasWeapon()) {
            app.getConsole().show(new ContainerMenu(app, player, this));
        }
    }
}