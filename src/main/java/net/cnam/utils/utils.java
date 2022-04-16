package net.cnam.utils;

/**
 *
 * @author Rouault Alban (alban.rouault.auditeur@lecnam.net)
 */
public class utils {
    
    /**
     * méthode générique pour aggrandir la taille d'un tableau
     * @param <A> tableau du même type que celui passé en paramètre
     * @param tableau peut importe le type
     * @return tableau du type du tableau passé en paramètre
     */
    public static <A> A [] increaseArray(A [] tableau){
        
        A [] result = (A[]) new Object[tableau.length + 1]; //conversion explicite obligatoire à cause du principe d'effacement 
        
        for (int i = 0 ; i < tableau.length ; i++){
            result[i] = tableau[i];
        }
        
        return result;
    }
    
    

}
