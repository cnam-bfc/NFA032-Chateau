package net.cnam.object;

/**
 *
 * @author Rouault Alban <alban.rouault.auditeur@lecnam.net>
 */
public interface ConsumableObject {
    
    //consume l'objet, donne le bénéfice à l'entité et détruit l'objet
    public void consume();

}
