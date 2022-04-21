package net.cnam.utils;

/**
 * Classe utilitaire permettant de manipuler des chaines de caractères
 */
public class StringUtils {

    /**
     * Méthode retournant un tableau de chaines de caractères en séparant celle
     * passé en paramètre par les retours à la ligne
     *
     * @param string Chaine de caractères à séparer
     * @return Un tableau de chaines de caractères
     */
    public static String[] convertStringToStringArray(String string) {
        return string.split("\n");
    }

    /**
     * Méthode retournant la chaine de caractère concaténé du tableau passé en
     * paramètres
     *
     * @param table Le tableau des chaines de caractères
     * @return Les chaines de caractère concaténé en une seule chaine
     */
    public static String convertStringArrayToString(String[] table) {
        String output = "";

        for (int i = 0; i < table.length; i++) {
            String str = table[i];

            output += str;

            if (i != table.length - 1) {
                output += "\n";
            }
        }

        return output;
    }

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
