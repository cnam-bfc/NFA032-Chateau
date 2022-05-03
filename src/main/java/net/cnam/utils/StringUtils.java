package net.cnam.utils;

/**
 * Classe utilitaire permettant de manipuler des chaines de caractères
 */
public class StringUtils {

    private static final String[] TO_EXCLUDE = new String[]{"\u001b[7m", "\u001b[27m"};

    /**
     * Retourne une chaine de caractères centré
     *
     * @param input La chaine de caractères initiale
     * @param spacer Caractère pour remplir la ligne
     * @param minLength Longueur minimale de la chaine retournée
     * @return La chaine de caractère centré
     */
    public static String centerString(String input, char spacer, int minLength) {
        return centerString(input, spacer, Character.MIN_VALUE, minLength);
    }

    /**
     * Retourne une chaine de caractères centré
     *
     * @param input La chaine de caractères initiale
     * @param spacer Caractère pour remplir la ligne
     * @param separator Caractère de chaque côté du texte
     * @param minLength Longueur minimale de la chaine retournée
     * @return La chaine de caractère centré
     */
    public static String centerString(String input, char spacer, char separator, int minLength) {
        String result = "";

        // Longueur du input
        int minimumLength = length(input);
        if (separator != Character.MIN_VALUE) {
            // + 2 pour les 2 separator (qui doivent êtres retourne obligatoirement)
            minimumLength += 2;
        }
        if (minimumLength > minLength) {
            minLength = minimumLength;
        }

        // Bourage avec des spacer pour qu'il y ai au moins minLength caractères dans la ligne
        // Longeur à retourner - La longueur du texte (input)
        int paddingLength = minLength - length(input);
        if (separator != Character.MIN_VALUE) {
            // - 2 pour les 2 separator (qui doivent êtres retourne obligatoirement)
            paddingLength -= 2;
        }
        for (int i = 0; i < paddingLength / 2; i++) {
            result += spacer;
        }

        if (separator != Character.MIN_VALUE) {
            result += separator;
        }
        result += input;
        if (separator != Character.MIN_VALUE) {
            result += separator;
        }

        // Bourage avec des spacer pour qu'il y ai au moins minLength caractères dans la ligne + un si la division de paddingLength à un reste
        for (int i = 0; i < paddingLength / 2 + paddingLength % 2; i++) {
            result += spacer;
        }

        return result;
    }

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
            if (length(str) > max) {
                max = length(str);
            }
        }

        return max;
    }

    private static int length(String str) {
        for (String exclude : TO_EXCLUDE) {
            str = str.replace(exclude, "");
        }

        return str.length();
    }
}
