package net.cnam.generator;

import java.util.Random;
import net.cnam.structure.*;

public class Generator {
    
    private long seed;
    private Random random;
    
//CONSTRUCTEUR
    public Generator(long seed) {
        this.seed = seed;
        this.random = new Random(seed);
    }

    

//METHODES
    //genère un tableau d'étage pour le chateau
    public Castle generateCastle(){
        CastleGenerator castleGen = new CastleGenerator(random);
        Castle result = new Castle(castleGen.generateStage());
        return result;
    }

    //méthode pour récupérer la seed du chateau
    public long getSeed() {
        return seed;
    }
    
    
}
