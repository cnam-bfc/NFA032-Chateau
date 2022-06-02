package net.cnam.chateau.game;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.StringTokenizer;

public class Statistics {

    private final List<Statistic> statistics = new ArrayList<>();

    /**
     * Méthode permettant de récupérer les meilleurs stats (si elles existent).
     */
    public void loadStatistics() {

        try {
            //On démarre le flux en instanciant les objets nécessaires
            FileReader fichierTexte = new FileReader("stats.csv");
            BufferedReader entree = new BufferedReader(fichierTexte);

            int noLine = 0;

            String ligne = entree.readLine();
            while (ligne != null) {
                noLine++;

                // on incrémente maximum 10 fois la list de stat
                if (noLine > 10) {
                    break;
                }

                StringTokenizer token = new StringTokenizer(ligne, ";");

                // on vérifie qu'il y a bien le bon nombre d'éléments
                if (token.countTokens() != 7) {
                    continue;
                }

                // On ajoute à chaque itération dans la liste un nouvel objet statistic récupéré sur le csv
                this.statistics.add(new Statistic(
                        token.nextToken(),
                        Integer.parseInt(token.nextToken()),
                        Boolean.parseBoolean(token.nextToken()),
                        Integer.parseInt(token.nextToken()),
                        Integer.parseInt(token.nextToken()),
                        Integer.parseInt(token.nextToken()),
                        Long.parseLong(token.nextToken())));

                ligne = entree.readLine();
            }
            entree.close();

        } catch (IOException ignored) {
        }
        Collections.sort(this.statistics);
    }

    /**
     * Méthode permettant l'écriture des statistiques sur le csv.
     * de la forme : nomJoueur/score/bossVaincu(bool)/nbEnnemieTué/nbPiecesVisité/seed
     */
    public void writeStatistics() {
        try {
            // instanciation des objets permettant le flux d'écriture
            FileWriter fichierTexteEcrit = new FileWriter("stats.csv");
            PrintWriter sortie = new PrintWriter(fichierTexteEcrit);

            Statistic stat;
            int compteur = 0;
            do {
                stat = statistics.get(compteur);
                sortie.println("" +
                        stat.getPlayerName() + ";" +
                        stat.getScore() + ";" +
                        stat.isBossDefeated() + ";" +
                        stat.getNbEnemyKilled() + ";" +
                        stat.getNbRoomsVisited() + ";" +
                        stat.getNbRoomsCastle() + ";" +
                        stat.getSeed());
                compteur++;
            } while (compteur < this.statistics.size());

            sortie.close();
        } catch (IOException ignored) {
        }
    }

    /**
     * Méthode permettant d'ajouter une statistique à la liste des statistiques.
     *
     * @param stat la statistique à ajouter
     */
    public void addStatistic(Statistic stat) {
        if (stat.isCheatModeActivated()){
            return;
        }
        this.statistics.add(stat);
        // On tri et on remet la liste à la bonne taille pour écrire seulement les 10 meilleurs scores
        Collections.sort(this.statistics);
        while (statistics.size() > 10) {
            this.statistics.remove(this.statistics.size() - 1);
        }

        // On sauvegarde les statistiques sur le csv
        writeStatistics();
    }

    public List<Statistic> getStatistics() {
        return statistics;
    }
}
