package net.cnam.chateau.entity;

import java.util.LinkedList;
import java.util.List;
import net.cnam.chateau.game.event.Puzzle;
import net.cnam.chateau.item.weapon.Weapon;
import net.cnam.chateau.utils.Location;

public class Sage extends LivingEntity {
    
    public static List<Sage> sages = new LinkedList<>();
    public static List<String> puzzles = new LinkedList<>();
    
    public static void initSages(){
        sages.add(new Sage(null,null,null,null));
        sages.add(new Sage(null,null,null,null));
        sages.add(new Sage(null,null,null,null));
        sages.add(new Sage(null,null,null,null));
        
        //Si le fichier csv existe :
        //Boucler dessus et ajouter les sages à la liste.
        //Sinon finir la méthode
    }
    
    private Puzzle puzzle;
    
    public Sage(Characteristic characteristics, String nom, Location location) {
        super(characteristics, nom, location);
    }

    public Sage(Characteristic characteristics, Weapon weapon, String nom, Location location) {
        super(characteristics, weapon, nom, location);
    }
    
    @Override
    public String getCharacter() {
        return "S";
    }
    
}
