package net.cnam.chateau.game;

import net.cnam.chateau.gui.CColor;
import net.cnam.chateau.gui.component.CLabel;
import net.cnam.chateau.gui.component.CPanel;
import net.cnam.chateau.gui.component.HorizontalAlignment;
import net.cnam.chateau.utils.direction.Orientation;

public class Statistic extends CPanel implements Comparable<Statistic> {
    private final long seed;
    private String playerName;
    private boolean bossDefeated;
    private int nbEnemyKilled;
    private int nbRoomsVisited;
    private int nbRoomsCastle;
    private int score;

    private boolean cheatModeActivate;

    /**
     * Constructeur
     *
     * @param seed       la graine de la carte utilisée
     * @param playerName le nom du joueur de la partie
     */
    public Statistic(long seed, String playerName) {
        this(playerName, 0, false, 0, 0, seed);
    }

    public Statistic(String playerName, int score, boolean bossDefeated, int nbEnemyKilled, int nbRoomsVisited, long seed) {
        super(HorizontalAlignment.CENTER, Orientation.VERTICAL, 0);

        this.seed = seed;
        this.playerName = playerName;
        this.bossDefeated = bossDefeated;
        this.nbEnemyKilled = nbEnemyKilled;
        this.nbRoomsVisited = nbRoomsVisited;
        this.score = score;

        this.update();
    }

    private void update() {
        this.getComponents().clear();
        int labelLength = 0;
        int valueLength = 0;

        // Nom des valeurs
        CLabel playerNameLabel = new CLabel(HorizontalAlignment.RIGHT, "Nom du joueur:");
        playerNameLabel.getColors().add(CColor.GREEN);
        if (playerNameLabel.getLength() > labelLength) {
            labelLength = playerNameLabel.getLength();
        }
        CLabel scoreLabel = new CLabel(HorizontalAlignment.RIGHT, "Score:");
        scoreLabel.getColors().add(CColor.GREEN);
        if (scoreLabel.getLength() > labelLength) {
            labelLength = scoreLabel.getLength();
        }
        CLabel bossDefeatedLabel = new CLabel(HorizontalAlignment.RIGHT, "Boss vaincu:");
        bossDefeatedLabel.getColors().add(CColor.GREEN);
        if (bossDefeatedLabel.getLength() > labelLength) {
            labelLength = bossDefeatedLabel.getLength();
        }
        CLabel nbEnemyKilledLabel = new CLabel(HorizontalAlignment.RIGHT, "Nombre d'ennemis tués:");
        nbEnemyKilledLabel.getColors().add(CColor.GREEN);
        if (nbEnemyKilledLabel.getLength() > labelLength) {
            labelLength = nbEnemyKilledLabel.getLength();
        }
        CLabel nbRoomsVisitedLabel = new CLabel(HorizontalAlignment.RIGHT, "Nombre de pièces visitées:");
        nbRoomsVisitedLabel.getColors().add(CColor.GREEN);
        if (nbRoomsVisitedLabel.getLength() > labelLength) {
            labelLength = nbRoomsVisitedLabel.getLength();
        }
        CLabel seedLabel = new CLabel(HorizontalAlignment.RIGHT, "Graine utilisée:");
        seedLabel.getColors().add(CColor.GREEN);
        if (seedLabel.getLength() > labelLength) {
            labelLength = seedLabel.getLength();
        }
        playerNameLabel.setLength(labelLength);
        scoreLabel.setLength(labelLength);
        bossDefeatedLabel.setLength(labelLength);
        nbEnemyKilledLabel.setLength(labelLength);
        nbRoomsVisitedLabel.setLength(labelLength);
        seedLabel.setLength(labelLength);

        // Valeurs
        CLabel playerNameValue = new CLabel(HorizontalAlignment.LEFT, this.playerName);
        playerNameValue.getColors().add(CColor.YELLOW);
        if (playerNameValue.getLength() > valueLength) {
            valueLength = playerNameValue.getLength();
        }
        CLabel scoreValue = new CLabel(HorizontalAlignment.LEFT, String.valueOf(this.score));
        scoreValue.getColors().add(CColor.YELLOW);
        if (scoreValue.getLength() > valueLength) {
            valueLength = scoreValue.getLength();
        }
        CLabel bossDefeatedValue = new CLabel(HorizontalAlignment.LEFT, (this.bossDefeated ? "Oui" : "Non"));
        bossDefeatedValue.getColors().add(CColor.YELLOW);
        if (bossDefeatedValue.getLength() > valueLength) {
            valueLength = bossDefeatedValue.getLength();
        }
        CLabel nbEnemyKilledValue = new CLabel(HorizontalAlignment.LEFT, String.valueOf(this.nbEnemyKilled));
        nbEnemyKilledValue.getColors().add(CColor.YELLOW);
        if (nbEnemyKilledValue.getLength() > valueLength) {
            valueLength = nbEnemyKilledValue.getLength();
        }
        CLabel nbRoomsVisitedValue = new CLabel(HorizontalAlignment.LEFT, String.valueOf(this.nbRoomsVisited));
        nbRoomsVisitedValue.getColors().add(CColor.YELLOW);
        if (nbRoomsVisitedValue.getLength() > valueLength) {
            valueLength = nbRoomsVisitedValue.getLength();
        }
        CLabel seedValue = new CLabel(HorizontalAlignment.LEFT, String.valueOf(this.seed));
        seedValue.getColors().add(CColor.YELLOW);
        if (seedValue.getLength() > valueLength) {
            valueLength = seedValue.getLength();
        }
        playerNameValue.setLength(valueLength);
        scoreValue.setLength(valueLength);
        bossDefeatedValue.setLength(valueLength);
        nbEnemyKilledValue.setLength(valueLength);
        nbRoomsVisitedValue.setLength(valueLength);
        seedValue.setLength(valueLength);

        // Panels pour chaque champ
        CPanel playerNamePanel = new CPanel(HorizontalAlignment.CENTER, Orientation.HORIZONTAL, 1);
        playerNamePanel.getComponents().add(playerNameLabel);
        playerNamePanel.getComponents().add(playerNameValue);
        playerNamePanel.autoResize();
        this.getComponents().add(playerNamePanel);

        CPanel scorePanel = new CPanel(HorizontalAlignment.CENTER, Orientation.HORIZONTAL, 1);
        scorePanel.getComponents().add(scoreLabel);
        scorePanel.getComponents().add(scoreValue);
        scorePanel.autoResize();
        this.getComponents().add(scorePanel);

        CPanel bossDefeatedPanel = new CPanel(HorizontalAlignment.CENTER, Orientation.HORIZONTAL, 1);
        bossDefeatedPanel.getComponents().add(bossDefeatedLabel);
        bossDefeatedPanel.getComponents().add(bossDefeatedValue);
        bossDefeatedPanel.autoResize();
        this.getComponents().add(bossDefeatedPanel);

        CPanel nbEnemyKilledPanel = new CPanel(HorizontalAlignment.CENTER, Orientation.HORIZONTAL, 1);
        nbEnemyKilledPanel.getComponents().add(nbEnemyKilledLabel);
        nbEnemyKilledPanel.getComponents().add(nbEnemyKilledValue);
        nbEnemyKilledPanel.autoResize();
        this.getComponents().add(nbEnemyKilledPanel);

        CPanel nbRoomsVisitedPanel = new CPanel(HorizontalAlignment.CENTER, Orientation.HORIZONTAL, 1);
        nbRoomsVisitedPanel.getComponents().add(nbRoomsVisitedLabel);
        nbRoomsVisitedPanel.getComponents().add(nbRoomsVisitedValue);
        nbRoomsVisitedPanel.autoResize();
        this.getComponents().add(nbRoomsVisitedPanel);

        CPanel seedPanel = new CPanel(HorizontalAlignment.CENTER, Orientation.HORIZONTAL, 1);
        seedPanel.getComponents().add(seedLabel);
        seedPanel.getComponents().add(seedValue);
        seedPanel.autoResize();
        this.getComponents().add(seedPanel);

        this.autoResize();
    }

    /**
     * Méthode incrémentant de 1 le nombre d'ennemis tué.
     */
    public void addAEnemyKill() {
        this.nbEnemyKilled += 1;
        this.update();
    }

    /**
     * Méthode permettant de signifier que le mode de triche a été activé impactant la non sauvegarde de la partie.
     */
    public void activeCheat() {
        this.cheatModeActivate = true;
        this.update();
    }

    /**
     * Setter permettant de définir le nombre de pièces visitées par le joueur.
     *
     * @param nbRoomsVisited nombre de pièces visitées
     */
    public void setNbRoomsVisited(int nbRoomsVisited) {
        this.nbRoomsVisited = nbRoomsVisited;
        this.update();
    }

    /**
     * Setter permettant de définir le nombre de pièces totales du château
     *
     * @param nbRoomsCastle nombre de pièces du château
     */
    public void setNbRoomsCastle(int nbRoomsCastle) {
        this.nbRoomsCastle = nbRoomsCastle;
        this.update();
    }

    /**
     * Setter pour changer le nom défini dans les statistiques.
     *
     * @param playerName le nouveau nom à mettre
     */
    public void setPlayerName(String playerName) {
        this.playerName = playerName;
        this.update();
    }

    /**
     * Méthode permettant de définir si le boss a été vaincu ou non durant la partie.
     *
     * @param bossDefeated true le boss est vaincu / false le boss n'est pas vaincu
     */
    public void setBossDefeated(boolean bossDefeated) {
        this.bossDefeated = bossDefeated;
        this.update();
    }

    /**
     * Méthode pour simuler un calcul de score non exhaustif.
     */
    public void calculScore() {
        this.score = (int) ((double) nbRoomsVisited / nbRoomsCastle * 1000);
        this.score += this.nbEnemyKilled * 150;
        if (this.bossDefeated) {
            this.score += 2000;
        }
        this.update();
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
     * @return le nombre d'ennemis tué
     */
    public int getNbEnemyKilled() {
        return nbEnemyKilled;
    }

    /**
     * Getter permettant de récupérer le nombre de pièces visitées.
     *
     * @return le nombre de pièces visitées
     */
    public int getNbRoomsVisited() {
        return nbRoomsVisited;
    }

    /**
     * Getter permettant de récupérer le score.
     *
     * @return le score
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
        if (stat == null) {
            return 1;
        }

        // On compare les scores
        if (this.score != stat.score) {
            return stat.score - this.score;
        }

        // On vérifie les boss
        if (this.bossDefeated && !stat.bossDefeated) {
            return -1;
        }
        if (!this.bossDefeated && stat.bossDefeated) {
            return 1;
        }

        // on vérifie le nombre d'ennemis tuer
        if (this.nbEnemyKilled - stat.nbEnemyKilled != 0) {
            return stat.nbEnemyKilled - this.nbEnemyKilled;
        }

        // on vérifie le nombre de pièces visité
        if (this.nbRoomsVisited - stat.nbRoomsVisited != 0) {
            return stat.nbRoomsVisited - this.nbRoomsVisited;
        }

        // enfin on vérifie le pseudo du joueur
        if (this.playerName.compareTo(stat.playerName) != 0) {
            return stat.playerName.compareTo(this.playerName);
        }

        return 0;
    }
}
