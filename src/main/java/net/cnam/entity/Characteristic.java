package net.cnam.entity;

/**
 * @TODO Faire JavaDoc
 */
public class Characteristic {
    private int strength;
    private int accuracy;
    private int speed;

    public Characteristic(int strength, int accuracy, int speed) {
        this.strength = strength;
        this.accuracy = accuracy;
        this.speed = speed;
    }

    public int getStrength() {
        return strength;
    }

    public void setStrength(int strength) {
        this.strength = strength;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    public int getSpeed() {
        return speed;
    }

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
     * @return si les vitesses sont égales, aléatoires 1/2 chance
     * sinon si la vitesse passé en paramètre est plus forte TRUE
     */
    public boolean attackFirst(Characteristic speedTest){
        if (this.speed == speedTest.getAccuracy()) return (Math.random() < 0.50);
        return this.speed >= speedTest.getAccuracy();
    }
}
