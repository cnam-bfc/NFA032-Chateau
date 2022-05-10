package net.cnam.item;

public class Item implements PortableItem {

    private String nom;

    public Item(String nom) {
        this.nom = nom;
    }

    @Override
    public void isCarry() {
    }

    @Override
    public void drop() {
        //TODO poser l'item à l'emplacement ou se trouve le joueur
    }
}
