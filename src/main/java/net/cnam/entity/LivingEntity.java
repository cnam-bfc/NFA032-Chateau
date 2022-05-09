package net.cnam.entity;

import net.cnam.object.Location;
import net.cnam.object.weapon.Weapon;

/**
 * Classe abstraite d'une entité vivante
 */
public abstract class LivingEntity extends Entity {

    private Characteristic characteristics;
    private Weapon weapon;
    private String nom;

    /**
     * Constructeur sans arme
     *
     * @param characteristics Les caractéristiques de l'entité vivante
     * @param location Coordonnées de l'entité
     */
    public LivingEntity(Characteristic characteristics, Location location, String nom) {
        super(location);
        this.characteristics = characteristics;
        this.nom = nom;
    }

    /**
     * Constructeur avec arme
     *
     * @param characteristics Les caractéristiques de l'entité vivante
     * @param weapon l'arme que porte l'entité
     * @param location Coordonnées de l'entité
     */
    public LivingEntity(Characteristic characteristics, Weapon weapon, Location location, String nom) {
        super(location);
        this.characteristics = characteristics;
        this.weapon = weapon;
        this.nom = nom;
    }


    /**
     * Méthode permettant de récupérer les caractérisiques de l'entité
     *
     * @return les caractéristiques
     */
    public Characteristic getCharacteristics() {
        return characteristics;
    }

    /**
     * Méthode permettant de définir les caractéristiques de l'entité
     *
     * @param characteristics les nouvelles caractéristiques
     */
    public void setCharacteristics(Characteristic characteristics) {
        this.characteristics = characteristics;
    }
    
    /**
     * Méthode qui permet de récupérer l'arme possédé par l'ennemi
     *
     * @return l'arme
     */
    public Weapon getWeapon() {
        return weapon;
    }

    /**
     * Méthode qui permet de définir l'arme possédé par l'ennemi. null est
     * équivalent aux mains nues
     *
     * @param weapon la nouvelle arme
     */
    public void setWeapon(Weapon weapon) {
        this.weapon = weapon;
    }
    
    /**
     * Méthode pour vérifier que l'entité possède une arme
     * 
     * @return un boolean (vrai si possède arme / sinon faux)
     */
    public boolean haveWeapon(){
        return (this.weapon != null);
    }
    
    /**
     * Méthode permettant de récupérer le nom du personnage.
     *
     * @return un string "nom"
     */
    public String getNom() {
        return nom;
    }

    /**
     * Méthode permettant de définir le nom du personnage.
     *
     * @param nom String "nom personnage"
     */
    public void setNom(String nom) {
        this.nom = nom;
    }
    
}
