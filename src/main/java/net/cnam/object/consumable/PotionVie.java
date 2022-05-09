package net.cnam.object.consumable;

public class PotionVie extends Consumable {
    
    public PotionVie() {
        super("Potion de régénération");
    }
    
    @Override
    public void consume() {
        //TODO remet de la vie au joueur (pas de vie en +, vie supplémentaire = delete)
    }

}
