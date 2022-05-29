package net.cnam.chateau.item.wearable;

import net.cnam.chateau.item.Item;

public class Wearable extends Item {

    private int strength;
    private int accuracy;
    private int speed;

    /**
     * Constructeur
     * 
     * @param name          Nom de l'objet
     * @param description   Description de l'objet
     * @param strength      Statistique force de l'objet (bonus/malus)
     * @param accuracy      Statistique précision de l'objet (bonus/malus)
     * @param speed         Statistique rapidité de l'objet (bonus/malus)
     */
    public Wearable(String name, String description, int strength, int accuracy, int speed) {
        super(name, description);

        this.strength = strength;
        this.accuracy = accuracy;
        this.speed = speed;
    }

    /**
     * Getter permettant de récupérer la statistique de force de l'objet.
     * 
     * @return la statistique force de l'objet (int)
     */
    public int getStrength() {
        return strength;
    }

    /**
     * Setter permettant de définir la statistique de force de l'objet.
     * 
     * @param strength la statistique force de l'objet (int)
     */
    public void setStrength(int strength) {
        this.strength = strength;
    }

    /**
     * Getter permettant de récupérer la statistique de précision de l'objet.
     * 
     * @return la statistique précision de l'objet (int)
     */
    public int getAccuracy() {
        return accuracy;
    }

    /**
     * Setter permettant de définir la statistique de précision de l'objet.
     * 
     * @param accuracy la statistique précision de l'objet (int)
     */
    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    /**
     * Getter permettant de récupérer la statistique de précision de l'objet.
     * 
     * @return la statistique précision de l'objet (int)
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Setter permettant de définir la statistique de rapidité de l'objet.
     * 
     * @param speed la statistique rapidité de l'objet (int)
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
