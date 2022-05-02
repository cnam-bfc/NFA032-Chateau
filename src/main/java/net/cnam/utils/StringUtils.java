package net.cnam.utils;

/**
 * Classe utilitaire permettant de manipuler des chaines de caractères
 */
public class StringUtils {

    /**
     * Retourne une chaine de caractères centré
     *
     * @param text Le texte initial
     * @param spacer Caractère pour remplir la ligne
     * @param length Longueur minimale de la chaine retournée
     * @return La chaine de caractère centré
     */
    public static String centerText(String text, char spacer, int length) {
        return centerText(text, spacer, spacer, length);
    }

    /**
     * Retourne une chaine de caractères centré
     *
     * @param text Le texte initial
     * @param spacer Caractère pour remplir la ligne
     * @param separator Caractère de chaque côté du texte
     * @param length Longueur minimale de la chaine retournée
     * @return La chaine de caractère centré
     */
    public static String centerText(String text, char spacer, char separator, int length) {
        String result = "";

        // Longueur du text + 2 pour les 2 separator (qui doivent êtres retourne obligatoirement)
        int minimumLength = text.length() + 2;
        if (minimumLength > length) {
            length = minimumLength;
        }

        // Bourage avec des spacer pour qu'il y ai au moins length caractères dans la ligne
        // Longeur à retourner - La longueur du texte (text + les 2 separator) obligatoire à retourner
        int paddingLength = length - text.length() - 2;
        for (int i = 0; i < paddingLength / 2; i++) {
            result += spacer;
        }

        result += separator;
        result += text;
        result += separator;

        // Bourage avec des spacer pour qu'il y ai au moins length caractères dans la ligne + un si la division de paddingLength à un reste
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
            if (str.length() > max) {
                max = str.length();
            }
        }

        return max;
    }
}
