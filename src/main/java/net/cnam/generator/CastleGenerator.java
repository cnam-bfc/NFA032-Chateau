package net.cnam.generator;

import java.util.Random;
import net.cnam.structure.*;

public class CastleGenerator {
    
//CHAMPS DE CLASSE
    private static final int MIN_SIZE_STAGE = 15; //taille mini d'un étage
    private static final int MAX_SIZE_STAGE = 30; //taille maxi d'un étage
    private static final int MIN_STAGE = 3; //nombre mini d'étage
    private static final int MAX_STAGE = 5; //nombre maxi d'étage
    

//CHAMPS D'OBJET
    private Random random;
    
//CONSTRUCTEUR
    public CastleGenerator(Random random) {
        this.random = random;
    }
    
    
//METHODES    
    /**
     * génère un tableau de pièce pour un étage
     */
    public Stage[] generateStage(){
        Stage [] result = new Stage[this.random.nextInt(MIN_STAGE,MAX_STAGE)];
        
        for (int i = 0 ; i < result.length ; i++){
            result[i] = new Stage(random.nextInt(MIN_SIZE_STAGE, MAX_SIZE_STAGE), random.nextInt(MIN_SIZE_STAGE,MAX_SIZE_STAGE));
        }
        
        return result;
        
    }
    
    /*public Stage cutStage(Stage result){
        
        
        
        return result;
    }*/
    
}

/* idée à try, considérer des carrés (comme des chunks minecraft */
