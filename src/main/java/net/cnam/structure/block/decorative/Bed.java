package net.cnam.structure.block.decorative;

import net.cnam.item.Item;

public class Bed extends DecorativeBlock {

    private boolean used = false;

    public Bed() {
        this(null);
    }

    public Bed(Item hiddenItem) {
        super(hiddenItem);
    }

    @Override
    public String getCharacter() {
        return "B";
    }

    /**
     * Méthode permettant d'utiliser un lit pour restaurer de la vie. Le lit est
     * utilisable une seule fois. S'il est déjà utilisé rien ne se passe S'il
     * n'a pas était utilisé passe la variable used (booleenne) à true et
     * restaure de la vie au joueur
     */
    public void use() {
        if (this.used) {
            return;
        }
        //TODO ajouter de la vie au joueur
        used = true;
    }

    public boolean isUsed() {
        return used;
    }
}
