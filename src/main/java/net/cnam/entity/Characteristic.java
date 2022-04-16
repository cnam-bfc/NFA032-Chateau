package net.cnam.entity;

/**
 * Class permettant de stocker les caractéristiques des entités.
 * Force (strength) : puissance du joueur
 * Précision (accuracy) : efficacité à ne pas louper de coup
 * Rapidité (speed) : rapidité de l'entité pour savoir l'ordre d'attaque
 */
public class Characteristic {
    private int strength;
    private int accuracy;
    private int speed;

    /**
     * Constructeur
     *
     * @param strength force
     * @param accuracy précision
     * @param speed vitesse
     */
    public Characteristic(int strength, int accuracy, int speed) {
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

    /* a voir si on en a besoin
    
    //méthode pour comparer la précision. RETOURNE VRAI Si la vitesse passé en paramètre est plus forte
    //renvoie vrai ou faux (1chance sur 2) si les vitesses sont égales
    public boolean compareAccuracy(Characteristic accuracyTest){
        if (this.accuracy == accuracyTest.getAccuracy()) return (Math.random() < 0.50);
        return this.accuracy >= accuracyTest.getAccuracy();
    }
    
    //méthode pour comparer la force. RETOURNE VRAI Si la vitesse passé en paramètre est plus forte
    //renvoie vrai ou faux (1chance sur 2) si les vitesses sont égales
    public boolean compareStrength(Characteristic strengthTest){
        if (this.strength == strengthTest.getStrength()) return (Math.random() < 0.50);
        return this.strength >= strengthTest.getAccuracy();
    }
*/
    
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
