package net.cnam.structure;

import net.cnam.structure.block.Block;

/**
 *
 * @author Rouault Alban (alban.rouault.auditeur@lecnam.net)
 */
public class StageMap {

//CHAMPS
    private Block[][] stageMap;

//CONSTRUCTEUR
    public StageMap(Block[][] block) {
        this.stageMap = block;
    }

//METHODES
    
    /**
     * redéfinition de toString
     * @return map de l'étage sous forme de String
     */
    @Override
    public String toString() {
        String result = "";
        for (int j = 0; j < this.stageMap.length; j++) {
            for (int i = 0; i < this.stageMap[j].length; i++) {
                result += this.stageMap[j][i];
            }
            result += "\n";
        }
        return result;
    }

}
