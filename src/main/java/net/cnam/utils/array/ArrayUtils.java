package net.cnam.utils.array;

import java.lang.reflect.Array;

/**
 * Classe utilitaire permettant de manipuler des tableaux
 */
public class ArrayUtils {

    /**
     * Méthode générique pour ajouter des éléments à un tableau
     *
     * @param <T> Type des éléments du tableau et de l'éléments
     * @param table Tableau où on ajoute les élements
     * @param elements Éléments à ajouter au tableau
     * @return Tableau avec les élements ajoutés à la fin
     */
    public static <T> T[] addOnBottomOfArray(T[] table, T[] elements) {
        T[] result = extendArray(table, elements.length);

        try {
            copyArray(elements, result, table.length);
        } catch (ArrayTooSmallException ex) {
        }

        return result;
    }

    /**
     * Méthode générique pour ajouter un élément à un tableau
     *
     * @param <T> Type des éléments du tableau et de l'élément
     * @param table Tableau où on ajoute l'élement
     * @param element Élément à ajouter au tableau
     * @return Tableau avec l'élement ajouté à la fin
     */
    public static <T> T[] addOnBottomOfArray(T[] table, T element) {
        table = extendArray(table);
        table[table.length - 1] = element;
        return table;
    }

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
        if (extendedSize < 0) {
            throw new IllegalArgumentException("La taille en plus doit être positive !");
        }

        // Bidouille chelou pour pouvoir créer un nouveau tableau du type T
        T[] result = (T[]) Array.newInstance(table.getClass().getComponentType(), table.length + extendedSize); // conversion explicite obligatoire à cause du principe d'effacement

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
        copyArray(from, to, 0);
    }

    /**
     * Méthode générique qui copie de contenu d'un tableau dans un autre tableau
     *
     * @param <T> Type des éléments des tableaux
     * @param from Tableau d'origine
     * @param to Tableau de destination
     * @param copyIndex Index du tableau de destination où il faut commencer à
     * copier le tableau d'origine
     * @throws net.cnam.utils.array.ArrayTooSmallException Exception lorsque le
     * tableau de destination est trop petit
     */
    public static <T> void copyArray(T[] from, T[] to, int copyIndex) throws ArrayTooSmallException {
        if (copyIndex < 0) {
            throw new IllegalArgumentException("L'index de copie doit être positif !");
        }
        if (to.length - copyIndex < from.length) {
            throw new ArrayTooSmallException("Tableau de destination trop petit (" + to.length + "), il faut un tableau plus grand (" + (from.length + copyIndex) + ")");
        }

        for (int i = 0; i < from.length; i++) {
            to[i + copyIndex] = from[i];
        }
    }
}
