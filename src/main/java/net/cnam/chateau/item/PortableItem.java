package net.cnam.chateau.item;

import net.cnam.chateau.entity.Entity;

public interface PortableItem {

    //permet à l'objet d'être tenu par une entité
    public void isCarry();

    //lache l'objet dans la pièce
    public void drop();
    
    //détruit l'objet 
    public void destroy(Entity entity);
}
