package net.cnam.structure;

/**
 * Classe d'un chateau
 */
public class Castle {

    private final Stage[] stages;

    /**
     * Constructeur
     *
     * @param stages Tableau des étages du chateau
     */
    public Castle(Stage[] stages) {
        this.stages = stages;
    }

    /**
     * Méthode pour récupérer les étages du chateau
     *
     * @return Les étages du chateau
     */
    public Stage[] getStages() {
        return stages;
    }
}
