package net.cnam.entity;

public class Statistic {
    
//CHAMPS
    private int strength;
    private int accuracy;
    private int speed;

//CONSTRUCTEUR

    public Statistic(int strength, int accuracy, int speed) {
        this.strength = strength;
        this.accuracy = accuracy;
        this.speed = speed;
    }
    
//GETTERS AND SETTERS

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
    
//METHODES
    //méthode pour comparer la précision. RETOURNE VRAI Si la vitesse passé en paramètre est plus forte
    //renvoie vrai ou faux (1chance sur 2) si les vitesses sont égales
    public boolean compareAccuracy(Statistic accuracyTest){
        if (this.accuracy == accuracyTest.getAccuracy()) return (Math.random() <= 0.50);
        return this.accuracy >= accuracyTest.getAccuracy();
    }
    
    //méthode pour comparer la force. RETOURNE VRAI Si la vitesse passé en paramètre est plus forte
    //renvoie vrai ou faux (1chance sur 2) si les vitesses sont égales
    public boolean compareStrength(Statistic strengthTest){
        if (this.strength == strengthTest.getStrength()) return (Math.random() <= 0.50);
        return this.strength >= strengthTest.getAccuracy();
    }
    
    //méthode pour comparer la vitesse. RETOURNE VRAI Si la vitesse passé en paramètre est plus forte
    //renvoie vrai ou faux (1chance sur 2) si les vitesses sont égales
    public boolean compareSpeed(Statistic speedTest){
        if (this.speed == speedTest.getAccuracy()) return (Math.random() <= 0.50);
        return this.speed >= speedTest.getAccuracy();
    }
}
