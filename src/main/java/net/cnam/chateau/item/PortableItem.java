package net.cnam.chateau.item;

import net.cnam.chateau.entity.Entity;

public interface PortableItem {

    //permet à l'objet d'être tenu par une entité
    void isCarry();

    //lache l'objet dans la pièce
    void drop();
    
    //détruit l'objet 
    void destroy(Entity entity);
}
