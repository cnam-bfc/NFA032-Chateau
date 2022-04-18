package net.cnam.utils;

public class StringUtils {
    // Retourne la longueur maximal des chaines de caractères contenu dans le tableau

    /**
     * Méthode retournant la longueur maximale de chaines de caractères
     *
     * @param table Le tableau des chaines de caractères
     * @return La longueur la plus élevée
     */
    public static int getMaximumLength(String[] table) {
        int max = 0;

        for (String str : table) {
            if (str.length() > max) {
                max = str.length();
            }
        }

        return max;
    }
}
