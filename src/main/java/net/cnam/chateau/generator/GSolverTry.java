package net.cnam.chateau.generator;

import java.util.HashSet;
import net.cnam.chateau.structure.Room;

/**
 *
 * @author Rouault Alban (alban.rouault.auditeur@lecnam.net)
 */
public class GSolverTry {
    
    public void initSolver(Room start){
        solver(start, 0, new HashSet<Room>());
    }
    
    public void solver(Room actualRoom, int iteration, HashSet sac){
        iteration +=1;
        sac.add(actualRoom);
        
        //tant que pièce adjacente non exploré bouclé
        solver(actualRoom, iteration, sac);
        //if bloquer return
    }
    
    /*
    Commence dans une pièce
    Choisie de passer dans une pièce à côté (accessible) au hasard, et ainsi de suite tant que c'est possible
    Si ne peut plus aller dans une nouvelle pièce reviens en arrière
    répèter jusqu'à avoir explorer tout le labyrinth 
    noter la distance par rapport à la pièce initial et en choisir une suffisament loin pour en faire la sortie
    possibiliter de bloquer l'accès à la pièce et cacher un objet clé dans une des autres pièces
    */

}
