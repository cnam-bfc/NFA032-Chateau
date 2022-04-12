package net.cnam.object;

/**
 *
 * @author Rouault Alban <alban.rouault.auditeur@lecnam.net>
 */
public interface UsableObject {
    
    //détruit l'objet si il n'a plus de durabilité
    public void destroy();
    
    //utilise l'objet en réduisant sa durabilité
    public void use();
    
    //répare l'objet et donc réaugmente sa durabilité
    public void repair();
    
    //améliore l'objet en augmentant les statistiques de celle-ci
    public void upgrade();

}
