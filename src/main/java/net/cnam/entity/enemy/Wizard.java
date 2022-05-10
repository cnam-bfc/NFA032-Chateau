package net.cnam.entity.enemy;

import java.util.Random;
import net.cnam.entity.Characteristic;
import net.cnam.utils.Location;
import net.cnam.item.weapon.Weapon;

public class Wizard extends Enemy {
    
    /**
     * Constructeur permettant de créer un sorcier à partir de ceux en énumération.
     * 
     * @param location l'emplacement de l'entité dans l'étage
     * @param random objet random associé à la seed du Château
     */
    public Wizard(Location location, Random random) {
        super(null, "", location);
        WizardEnum type = WizardEnum.values()[random.nextInt(WizardEnum.values().length)];
        this.setNom(type.getName());
        if (type.getWeapon() != null) this.setWeapon(type.getWeapon());
        //TODO retirer les caractéristiques par défaut et use le random
        this.setCharacteristics(new Characteristic(DEFAULT_HEALTH, DEFAULT_RESISTANCE, DEFAULT_STRENGTH, DEFAULT_ACCURACY, DEFAULT_SPEED));
        
    }

    public Wizard(Characteristic characteristics, String nom, Location location) {
        super(characteristics, nom, location);
    }

    public Wizard(Characteristic characteristics, Weapon weapon, String nom, Location location) {
        super(characteristics, weapon, nom, location);
    }
    
    

    @Override
    public String getCharacter() {
        return "W";
    }
    
    
    
    

}
