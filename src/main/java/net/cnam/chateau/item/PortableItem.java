package net.cnam.chateau.item;

public interface PortableItem {

    //permet à l'objet d'être tenu par une entité
    public void isCarry();

    //lache l'objet dans la pièce
    public void drop();
}
