package net.cnam.utils.array;

import java.lang.reflect.Array;

/**
 * Classe utilitaire permettant de manipuler des tableaux
 */
public class ArrayUtils {

    /**
     * Méthode générique pour ajouter des éléments au début d'un tableau
     *
     * @param <T> Type des éléments du tableau et de l'éléments
     * @param table Tableau où on ajoute les élements
     * @param elements Éléments à ajouter au tableau
     * @return Tableau avec les élements ajoutés au début
     */
    public static <T> T[] addOnTopOfArray(T[] table, T[] elements) {
        T[] result = extendOnTopOfArray(table, elements.length);

        try {
            copyArray(elements, result);
        } catch (ArrayTooSmallException ex) {
        }

        return result;
    }

    /**
     * Méthode générique pour ajouter des éléments à la fin d'un tableau
     *
     * @param <T> Type des éléments du tableau et de l'éléments
     * @param table Tableau où on ajoute les élements
     * @param elements Éléments à ajouter au tableau
     * @return Tableau avec les élements ajoutés à la fin
     */
    public static <T> T[] addOnBottomOfArray(T[] table, T[] elements) {
        T[] result = extendOnBottomOfArray(table, elements.length);

        try {
            copyArray(elements, result, table.length);
        } catch (ArrayTooSmallException ex) {
        }

        return result;
    }

    /**
     * Méthode générique pour ajouter un élément au début d'un tableau
     *
     * @param <T> Type des éléments du tableau et de l'élément
     * @param table Tableau où on ajoute l'élement
     * @param element Élément à ajouter au tableau
     * @return Tableau avec l'élement ajouté au début
     */
    public static <T> T[] addOnTopOfArray(T[] table, T element) {
        table = extendOnTopOfArray(table);
        table[0] = element;
        return table;
    }

    /**
     * Méthode générique pour ajouter un élément à la fin d'un tableau
     *
     * @param <T> Type des éléments du tableau et de l'élément
     * @param table Tableau où on ajoute l'élement
     * @param element Élément à ajouter au tableau
     * @return Tableau avec l'élement ajouté à la fin
     */
    public static <T> T[] addOnBottomOfArray(T[] table, T element) {
        table = extendOnBottomOfArray(table);
        table[table.length - 1] = element;
        return table;
    }

    /**
     * Méthode générique pour aggrandir la taille d'un tableau au début
     *
     * @param <T> Type des éléments du tableau
     * @param table Tableau à agrandir
     * @return Tableau agrandi au début
     */
    public static <T> T[] extendOnTopOfArray(T[] table) {
        return ArrayUtils.extendOnTopOfArray(table, 1);
    }

    /**
     * Méthode générique pour aggrandir la taille d'un tableau à la fin
     *
     * @param <T> Type des éléments du tableau
     * @param table Tableau à agrandir
     * @return Tableau agrandi à la fin
     */
    public static <T> T[] extendOnBottomOfArray(T[] table) {
        return ArrayUtils.extendOnBottomOfArray(table, 1);
    }

    /**
     * Méthode générique pour aggrandir la taille d'un tableau au début
     *
     * @param <T> Type des éléments du tableau
     * @param table Tableau à agrandir
     * @param extendedSize Taille en plus
     * @return Tableau agrandi au début
     */
    public static <T> T[] extendOnTopOfArray(T[] table, int extendedSize) {
        if (extendedSize < 0) {
            throw new IllegalArgumentException("La taille en plus doit être positive !");
        }

        // Bidouille chelou pour pouvoir créer un nouveau tableau du type T
        T[] result = (T[]) Array.newInstance(table.getClass().getComponentType(), table.length + extendedSize); // conversion explicite obligatoire à cause du principe d'effacement

        try {
            copyArray(table, result, extendedSize);
        } catch (ArrayTooSmallException ex) {
        }

        return result;
    }

    /**
     * Méthode générique pour aggrandir la taille d'un tableau à la fin
     *
     * @param <T> Type des éléments du tableau
     * @param table Tableau à agrandir
     * @param extendedSize Taille en plus
     * @return Tableau agrandi à la fin
     */
    public static <T> T[] extendOnBottomOfArray(T[] table, int extendedSize) {
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
