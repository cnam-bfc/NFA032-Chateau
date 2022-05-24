package net.cnam.chateau.structure.block.decorative;

public class Bed extends DecorativeBlock {

    private boolean used = false;

    public Bed() {
        super("Bed");
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
