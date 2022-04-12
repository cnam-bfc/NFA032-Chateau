package net.cnam.generator;

import java.util.Random;
import net.cnam.structure.*;

public class CastleGenerator {

    private Random random;
    
//CONSTRUCTEUR
    public CastleGenerator(Random random) {
        this.random = random;
    }
    
    
//METHODES    
    //génère un tableau de pièce pour un étage
    public Stage[] generateStage(){
        Stage [] result = new Stage[this.random.nextInt(3,5)];
        
        for (int i = 0 ; i < result.length ; i++){
            result[i] = new Stage(random.nextInt(250, 350), random.nextInt(250,350));
            result[i] = cutStage(result[i]);
        }
        
        return result;
        
    }
    
    public Stage cutStage(Stage result){
        
        return result;
    }
    
    
}
