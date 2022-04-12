package net.cnam.entity;

import net.cnam.structure.block.CaracterBlockEntity;

public class Entity implements CaracterBlockEntity{
    
//CHAMPS
    private char caracter;

//CONSTRUCTEUR
    public Entity(char caracter) {
        this.caracter = caracter;
    }
    
//METHODES
    @Override
    public char getCaracter() {  
        return caracter ;  
    }
    

}
