package net.cnam.structure.block;

import net.cnam.entity.LivingEntity;

/**
 * Class permettant de créer un block Porte (Door) pour la map.
 */
public class Door extends Block {

    private boolean verouiller;
    private boolean pieger;
    private LivingEntity sage; //à modifier le type quand on aura trouver

    /**
     * Constructeur attribuant au block Porte(Door) le caractère 'D' pour
     * l'affichage sur la map
     */
    public Door() {
        super('D');
    }

}
