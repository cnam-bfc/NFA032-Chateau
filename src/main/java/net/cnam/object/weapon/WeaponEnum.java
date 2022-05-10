package net.cnam.object.weapon;

public enum WeaponEnum {
    
    //Armes spéciales
    EXCALIBURNE("Excaliburne", 10, 0, 2),
    POELE("Poele", 2, -3 , 5),
    FROSTMOURN("Frostmourn", 8, 0, 4),
    COUTEAU_A_BEURRE("Couteau a beurre", 2, 5, 8),
    LE_RATEAU("Rateau", 4, 0 , 8),
    LA_GROSSE_PELLE("La grosse pelle !", 5 ,5 ,5);
    
    private String name;
    private int power;
    private int accuracy;
    private int speed;

    private WeaponEnum(String name, int power, int accuracy, int speed) {
        this.name = name;
        this.power = power;
        this.accuracy = accuracy;
        this.speed = speed;
    }
            
    public String getName() {
        return name;
    }

    public int getPower() {
        return power;
    }

    public int getAccuracy() {
        return accuracy;
    }

    public int getSpeed() {
        return speed;
    }
    
    
    
}
