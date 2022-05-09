package net.cnam.entity;

/**
 * Class permettant de stocker les caractéristiques des entités.
 * Force (strength) : puissance du joueur
 * Précision (accuracy) : efficacité à ne pas louper de coup
 * Rapidité (speed) : rapidité de l'entité pour savoir l'ordre d'attaque
 */
public class Characteristic {
    private int health;
    private int resistance;
    private int strength;
    private int accuracy;
    private int speed;

    /**
     * Constructeur
     *
     * @param health point de vie
     * @param resistance point de resistance
     * @param strength force
     * @param accuracy précision
     * @param speed vitesse
     */
    public Characteristic(int health, int resistance, int strength, int accuracy, int speed) {
        this.health = health;
        this.resistance = resistance;
        this.strength = strength;
        this.accuracy = accuracy;
        this.speed = speed;
    }

    /**
     * Méthode permettant de récupérer la force.
     * 
     * @return un entier (force)
     */
    public int getStrength() {
        return strength;
    }
    
    /**
     * méthode permettant de définir/modifier la force dans les caractéristiques.
     * 
     * @param strength un entier (force)
     */
    public void setStrength(int strength) {
        this.strength = strength;
    }
    
    /**
     * Méthode permettant de récupérer la précision.
     * 
     * @return un entier (précision)
     */
    public int getAccuracy() {
        return accuracy;
    }

    /**
     * méthode permettant de définir/modifier la précision dans les caractéristiques.
     * 
     * @param accuracy un entier (précision)
     */
    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    /**
     * Méthode permettant de récupérer la rapidité.
     * 
     * @return un entier (rapidité)
     */
    public int getSpeed() {
        return speed;
    }
    
    /**
     * méthode permettant de définir/modifier la rapidité dans les caractéristiques.
     * 
     * @param speed un entier (rapidité)
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }
    
    /**
     * Méthode permettant de récupérer la santé de l'entité
     *
     * @return la santé
     */
    public int getHealth() {
        return health;
    }

    /**
     * Méthode permettant de définir la santé de l'entité
     *
     * @param health la nouvelle santé
     */
    public void setHealth(int health) {
        this.health = health;
    }

    /**
     * Méthode permettant de récupérer la résistance de l'entité
     *
     * @return la résistance
     */
    public int getResistance() {
        return resistance;
    }

    /**
     * Méthode permettant de définir la résistance de l'entité
     *
     * @param resistance la nouvelle résistance
     */
    public void setResistance(int resistance) {
        this.resistance = resistance;
    }
    
    /**
     * méthode pour comparer la vitesse et définir le premier à attaquer lors d'un combat.
     * @param speedTest un objet caractéristique
     * @return si les vitesses sont égales, aléatoires 1/2 chance
     * sinon si la vitesse passé en paramètre est plus forte TRUE
     */
    public boolean attackFirst(Characteristic speedTest){
        if (this.speed == speedTest.getAccuracy()) return (Math.random() < 0.50);
        return this.speed >= speedTest.getAccuracy();
    }
}
