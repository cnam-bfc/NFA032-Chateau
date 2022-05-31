package net.cnam.chateau.game;

public class Statistic implements Comparable<Statistic> {

    private long seed;
    private String playerName;
    private boolean bossDefeated = false;
    private int nbEnemyKilled = 0;
    private int nbRoomsVisited;
    private int nbRoomsCastle;
    private int score;

    private boolean cheatModeActivate;

    /**
     * Constructeur
     *
     * @param seed la graine de la carte utilisé
     * @param playerName le nom du joueur de la partie
     */
    public Statistic(long seed, String playerName) {
        this.playerName = playerName;
        this.seed = seed;
    }

    public Statistic(String playerName, int score, boolean bossDefeated, int nbEnemyKilled, int nbRoomsVisited, long seed) {
        this.seed = seed;
        this.playerName = playerName;
        this.bossDefeated = bossDefeated;
        this.nbEnemyKilled = nbEnemyKilled;
        this.nbRoomsVisited = nbRoomsVisited;
        this.score = score;
    }

    /**
     * Méthode incrémentant de 1 le nombre d'ennemie tué.
     */
    public void addAEnemyKill(){
        this.nbEnemyKilled +=1;
    }

    /**
     * Méthode permettant de signifier que le mode de triche a été activé impactant la non sauvegarde de la partie.
     */
    public void activeCheat(){
        this.cheatModeActivate = true;
    }

    /**
     * Setter permettant de définir le nombre de pièces visitées par le joueur.
     *
     * @param nbRoomsVisited nombre de pièces visitées
     */
    public void setNbRoomsVisited(int nbRoomsVisited) {
        this.nbRoomsVisited = nbRoomsVisited;
    }

    /**
     * Setter permettant de définir le nombre de pièces totales du château
     *
     * @param nbRoomsCastle nombre de pièce du château
     */
    public void setNbRoomsCastle(int nbRoomsCastle) {
        this.nbRoomsCastle = nbRoomsCastle;
    }

    /**
     * Setter pour changer le nom défini dans les statistiques.
     *
     * @param playerName le nouveau nom à mettre
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
    }

    /**
     * Méthode permettant de définir si le boss a été vaincu ou non durant la partie.
     *
     * @param bossDefeated true le boss est vaincu / false le boss n'est pas vaincu
     */
    public void setBossDefeated(boolean bossDefeated) {
        this.bossDefeated = bossDefeated;
    }

    /**
     * Méthode pour simuler un calcul de score non exhaustif.
     */
    public void calculScore(){
        this.score = nbRoomsVisited / nbRoomsCastle * 1000;
        this.score += this.nbEnemyKilled * 100;
        if (this.bossDefeated){
            this.score += 2000;
        }
    }

    /**
     * Getter permettant de récupérer la graine de la partie.
     *
     * @return la graine de la partie
     */
    public long getSeed() {
        return seed;
    }

    /**
     * Getter permettant de récupérer le nom du joueur.
     *
     * @return le nom du joueur
     */
    public String getPlayerName() {
        return playerName;
    }

    /**
     * Méthode permettant de vérifier si le boss du jeu a été vaincu.
     *
     * @return true si le boss est vaincu / false si non vaincu
     */
    public boolean isBossDefeated() {
        return bossDefeated;
    }

    /**
     * Getter permettant de savoir le nombre d'ennemies tués.
     *
     * @return le nombre d'ennemie tué
     */
    public int getNbEnemyKilled() {
        return nbEnemyKilled;
    }

    /**
     * Getter permettant de récupérer le nombre de pièces visitées.
     *
     * @return
     */
    public int getNbRoomsVisited() {
        return nbRoomsVisited;
    }

    /**
     * Getter permettant de récupérer le score.
     *
     * @return le score.
     */
    public int getScore() {
        return score;
    }

    /**
     * Redéfinition de la méthode compareTo de l'interface Comparable.
     *
     * @param stat la Statistic à comparer
     * @return l'entier provenant de la comparaison.
     */
    @Override
    public int compareTo(Statistic stat) {

        // On regarde si l'objet passé en paramètre n'est pas null
        if (stat == null){
            return 1;
        }

        // On compare les scores
        if (this.score != stat.score){
            return stat.score - this.score;
        }

        // On vérifie les boss
        if (this.bossDefeated && !stat.bossDefeated){
            return -1;
        }
        if (!this.bossDefeated && stat.bossDefeated){
            return 1;
        }

        // on vérifie le nombre d'ennemie tuer
        if (this.nbEnemyKilled - stat.nbEnemyKilled !=0){
            return stat.nbEnemyKilled - this.nbEnemyKilled;
        }

        // on vérifie le nombre de pièce visité
        if (this.nbRoomsVisited - stat.nbRoomsVisited !=0){
            return stat.nbRoomsVisited - this.nbRoomsVisited;
        }

        // enfin on vérifie le pseudo du joueur
        if (this.playerName.compareTo(stat.playerName) != 0) {
            return stat.playerName.compareTo(this.playerName);
        }

        return 0;
    }
}
