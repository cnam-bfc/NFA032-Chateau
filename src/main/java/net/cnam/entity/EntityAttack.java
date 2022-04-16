package net.cnam.entity;

/**
 * Interface permetteant à une entité d'attaquer une autre entité
 */
public interface EntityAttack {

    /**
     * Méthode permettant à une entité d'attaquer en prenant en compte ses
     * statistiques et son arme
     *
     * @param attackedEntity L'entité vivante attaquée
     */
    public void attack(LivingEntity attackedEntity);
}
