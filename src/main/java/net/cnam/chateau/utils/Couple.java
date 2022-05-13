package net.cnam.chateau.utils;

/**
 * Classe générique permettant de former un couple d'objet
 *
 * @param <A> Type de A
 * @param <B> Type de B
 */
public class Couple<A, B> {

    private A ElemOne;
    private B ElemTwo;

    /**
     * Constructeur
     *
     * @param ElemOne Objet Un
     * @param ElemTwo Objet Deux
     */
    public Couple(A ElemOne, B ElemTwo) {
        this.ElemOne = ElemOne;
        this.ElemTwo = ElemTwo;
    }

    /**
     * Méthode pour récupérer l'élément Un.
     *
     * @return l'objet un
     */
    public A getElemOne() {
        return ElemOne;
    }

    /**
     * Méthode pour définir l'élément deux dans le couple
     *
     * @param ElemOne Objet deux
     */
    public void setElemOne(A ElemOne) {
        this.ElemOne = ElemOne;
    }

    /**
     * Méthode pour récupérer l'élément Deux.
     *
     * @return l'objet deux
     */
    public B getElemTwo() {
        return ElemTwo;
    }

    /**
     * Méthode pour définir l'élément deux dans le couple
     *
     * @param ElemTwo Objet deux
     */
    public void setElemTwo(B ElemTwo) {
        this.ElemTwo = ElemTwo;
    }
}
