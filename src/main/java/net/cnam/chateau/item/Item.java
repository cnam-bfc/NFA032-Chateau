package net.cnam.chateau.item;

public class Item implements PortableItem {

    private String name;

    public Item(String name) {
        this.name = name;
    }

    @Override
    public void isCarry() {
    }

    @Override
    public void drop() {
        //TODO poser l'item à l'emplacement ou se trouve le joueur
    }

    public void setName(String name) {
        this.name = name;
    }

}
