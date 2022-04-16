package net.cnam.utils.array;

/**
 * Classe utilitaire permettant de manipuler des tableaux
 */
public class ArrayUtils {

    /**
     * Méthode générique pour aggrandir la taille d'un tableau
     *
     * @param <T> Type des éléments du tableau
     * @param table Tableau à agrandir
     * @return Tableau agrandi
     */
    public static <T> T[] extendArray(T[] table) {
        return ArrayUtils.extendArray(table, 1);
    }

    /**
     * Méthode générique pour aggrandir la taille d'un tableau
     *
     * @param <T> Type des éléments du tableau
     * @param table Tableau à agrandir
     * @param extendedSize Taille en plus
     * @return Tableau agrandi
     */
    public static <T> T[] extendArray(T[] table, int extendedSize) {
        T[] result = (T[]) new Object[table.length + extendedSize]; //conversion explicite obligatoire à cause du principe d'effacement 

        try {
            copyArray(table, result);
        } catch (ArrayTooSmallException ex) {
        }

        return result;
    }

    /**
     * Méthode générique qui copie de contenu d'un tableau dans un autre tableau
     *
     * @param <T> Type des éléments des tableaux
     * @param from Tableau d'origine
     * @param to Tableau de destination
     * @throws net.cnam.utils.array.ArrayTooSmallException Exception lorsque le
     * tableau de destination est trop petit
     */
    public static <T> void copyArray(T[] from, T[] to) throws ArrayTooSmallException {
        if (to.length < from.length) {
            throw new ArrayTooSmallException("Tableau de destination trop petit (" + to.length + "), il faut un tableau plus grand (" + from.length + ")");
        }

        for (int i = 0; i < from.length; i++) {
            to[i] = from[i];
        }
    }

}
