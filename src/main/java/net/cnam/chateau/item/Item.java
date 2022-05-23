package net.cnam.chateau.item;

import net.cnam.chateau.entity.Entity;

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
    
    public String getName() {
        return name;
    }

    @Override
    public void destroy(Entity entity) {
        // TODO mettre une phrase pour dire l'item est détruit
        entity.setItem(null);
    }   
}
