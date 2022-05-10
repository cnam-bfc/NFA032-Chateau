package net.cnam.entity.enemy;

import net.cnam.object.weapon.Weapon;

public enum WizardEnum{
    
    HARRY_POTDEFLEUR("Harry PotDeFleur", null),
    CASANOVA("Casanova", null),
    SHIVA("Shiva", null),
    IFRIT("Ifrit", null);
    
    private String name;
    private Weapon weapon;
    
    WizardEnum(String name, Weapon weapon){
        this.name = name;
        this.weapon = weapon;
    }

    /**
     * Récupère le nom du sorcier venant de l'énumération.
     * 
     * @return String (nom du sorcier)
     */
    public String getName() {
        return name;
    }

    /**
     * Récupère l'arme du sorcier venant de l'énumération.
     * peut être null si aucune arme n'est associé
     * 
     * @return Weapon (Arme du sorcier)
     */
    public Weapon getWeapon() {
        return weapon;
    }

//TODO modifier pour ajouter csv    
//    public static WizardEnum[] getAllWizardEnum(){
//        WizardEnum[] test2 = WizardEnum.values();
//        //ECRIRE LE CODE POUR RECUP LE CSV ET L"AJOUTER 
//        return test2;
//    }
    
    
        
}
