package net.cnam.chateau.entity.enemy;

import java.util.ArrayList;
import net.cnam.chateau.item.weapon.Weapon;

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

    public static ArrayList<WizardEnum> getAllWizardEnum(){
        WizardEnum[] tabWizardEnum = WizardEnum.values();
        ArrayList<WizardEnum> result = new ArrayList<>();
       for (int i = 0 ; i < tabWizardEnum.length ; i++){
           result.add(tabWizardEnum[0]);
       }
       //TODO modifier pour ajouter csv   
        return result;
    }
    
    
        
}
