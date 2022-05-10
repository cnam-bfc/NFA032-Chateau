package net.cnam.item;

public interface UsableItem {

    //détruit l'objet 
    public void destroy();

    //utilise l'objet en réduisant sa durabilité
    public void wear();

    //répare l'objet et donc réaugmente sa durabilité
    public void repair();

    //améliore l'objet en augmentant les statistiques de celle-ci
    public void upgrade();
}
