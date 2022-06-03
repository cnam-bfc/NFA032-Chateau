package net.cnam.chateau.gui;

/**
 * Interface permettant d'associer un caractère à un objet.
 *
 * Un "caractère" est une chaine de caractères qui doit faire une longueur de 1 à l'affichage,
 * elle peut par exemple contenir des couleurs qui vont augmenter la longueur de la chaine,
 * mais à l'affichage la chaine sera toujours de longueur 1 sur l'écran
 */
public interface DisplayableObject {
    /**
     * Méthode permettant de récupérer le caractère associé à l'objet
     *
     * @return le caractère
     */
    String getCharacter();
}
