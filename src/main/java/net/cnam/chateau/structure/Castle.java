package net.cnam.chateau.structure;

import net.cnam.chateau.utils.Location;

/**
 * Classe d'un chateau
 */
public class Castle {

    private final Stage[] stages;
    private final long seed;

    private final Location playerStartLocation;

    /**
     * Constructeur
     *
     * @param stages Tableau des étages du chateau
     * @param playerStartLocation Départ du joueur
     * @param seed La graine de génération du chateau
     */
    public Castle(Stage[] stages, Location playerStartLocation, long seed) {
        this.stages = stages;
        this.playerStartLocation = playerStartLocation;
        this.seed = seed;
    }

    /**
     * Méthode pour récupérer les étages du chateau
     *
     * @return Les étages du chateau
     */
    public Stage[] getStages() {
        return stages;
    }

    /**
     * Méthode pour récupérer la seed avec laquelle a été généré le chateau
     *
     * @return La seed
     */
    public long getSeed() {
        return seed;
    }

    /**
     * Getter permettant de récupérer les coordonnées du joueur.
     * 
     * @return objet Location comportant les coordonnées du joueur
     */
    public Location getPlayerStartLocation() {
        return playerStartLocation;
    }
}
