package net.cnam.structure;

/**
 * Classe d'un chateau
 */
public class Castle {

    private final Stage[] stages;
    private final long seed;

    /**
     * Constructeur
     *
     * @param stages Tableau des étages du chateau
     * @param seed La graine de génération du chateau
     */
    public Castle(Stage[] stages, long seed) {
        this.stages = stages;
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
}
