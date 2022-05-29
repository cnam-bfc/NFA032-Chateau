package net.cnam.chateau.item.weapon;

import net.cnam.chateau.item.Item;

public class Weapon extends  Item{

    private int strength;
    private int accuracy;
    private int speed;

    /**
     * Constructeur
     * 
     * @param name          Nom de l'arme
     * @param description   Description de l'arme
     * @param power         Force de l'arme (bonus/malus)
     * @param accuracy      Précision de l'arme (bonus/malus)
     * @param speed         Rapidité de l'arme (bonus/malus)
     */
    public Weapon(String name, String description, int power, int accuracy, int speed) {
        super(name, description);

        this.strength = power;
        this.accuracy = accuracy;
        this.speed = speed;
    }

    /**
     *  Getter permettant de récupérer la statistique de force de l'arme.
     * 
     * @return la statistique force de l'arme (int)
     */
    public int getStrength() {
        return strength;
    }

    /**
     *  Getter permettant de récupérer la statistique de précision de l'arme.
     * 
     * @return la statistique précision de l'arme (int)
     */
    public int getAccuracy() {
        return accuracy;
    }

    /**
     *  Getter permettant de récupérer la statistique de rapidité de l'arme.
     * 
     * @return la statistique rapidité de l'arme (int)
     */
    public int getSpeed() {
        return speed;
    }

    /**
     * Setter permettant de définir la statistique de force de l'arme.
     * 
     * @param strength la statistique force de l'arme (int)
     */
    public void setStrength(int strength) {
        this.strength = strength;
    }

    /**
     * Setter permettant de définir la statistique de précision de l'arme.
     * 
     * @param accuracy la statistique précision de l'arme (int)
     */
    public void setAccuracy(int accuracy) {
        this.accuracy = accuracy;
    }

    /**
     * Setter permettant de définir la statistique de rapidité de l'arme.
     * 
     * @param speed la statistique rapidité de l'arme (int)
     */
    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
