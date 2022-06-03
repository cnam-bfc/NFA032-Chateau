package net.cnam.chateau.utils;

/**
 * Classe utilitaire permettant de manipuler des chaines de caractères
 */
public class StringUtils {
    /**
     * Retourne une chaine de caractères centrée
     *
     * @param input     La chaine de caractères initiale
     * @param spacer    Caractère pour remplir la ligne
     * @param minLength Longueur minimale de la chaine retournée
     * @return La chaine de caractère centré
     */
    public static String centerString(String input, char spacer, int minLength) {
        return centerString(input, spacer, Character.MIN_VALUE, minLength);
    }

    /**
     * Retourne une chaine de caractères centrée
     *
     * @param input     La chaine de caractères initiale
     * @param spacer    Caractère pour remplir la ligne
     * @param separator Caractère de chaque côté du texte
     * @param minLength Longueur minimale de la chaine retournée
     * @return La chaine de caractère centré
     */
    public static String centerString(String input, char spacer, char separator, int minLength) {
        StringBuilder result = new StringBuilder();

        // Longueur de l'input
        int minimumLength = input.length();
        if (separator != Character.MIN_VALUE) {
            // + 2 pour les 2 separator (qui doivent être retourné obligatoirement)
            minimumLength += 2;
        }
        if (minimumLength > minLength) {
            minLength = minimumLength;
        }

        // Bourrage avec des spacer pour qu'il y ait au moins minLength caractères dans la ligne
        // Longueur à retourner - La longueur du texte (input)
        int paddingLength = minLength - input.length();
        if (separator != Character.MIN_VALUE) {
            // - 2 pour les 2 separator (qui doivent être retourné obligatoirement)
            paddingLength -= 2;
        }
        for (int i = 0; i < paddingLength / 2; i++) {
            result.append(spacer);
        }

        if (separator != Character.MIN_VALUE) {
            result.append(separator);
        }
        result.append(input);
        if (separator != Character.MIN_VALUE) {
            result.append(separator);
        }

        // Bourrage avec des spacer pour qu'il y ait au moins minLength caractères dans la ligne + un si la division de paddingLength à un reste
        for (int i = 0; i < paddingLength / 2 + paddingLength % 2; i++) {
            result.append(spacer);
        }

        return result.toString();
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
        StringBuilder output = new StringBuilder();

        for (int i = 0; i < table.length; i++) {
            String str = table[i];

            output.append(str);

            if (i != table.length - 1) {
                output.append("\n");
            }
        }

        return output.toString();
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

    /**
     * Méthode qui permet de compter le nombre d'occurrences d'une chaine de caractère dans une autre chaine de caractères
     *
     * @param string La chaine de caractère dans laquelle on cherche
     * @param search La chaine de caractère à chercher
     * @return Le nombre de fois ou la chaine de caractère est trouvée dans la chaine de caractère
     */
    public static int countOccurrences(String string, String search) {
        int count = 0;
        int index = 0;

        while ((index = string.indexOf(search, index)) != -1) {
            index++;
            count++;
        }

        return count;
    }
}
