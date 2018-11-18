/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tester;

import Engine.Data;
import Exception.FileException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Poowis
 */
public class Tester {
    
    public static void main(String[] args) {
        try {
            Data data = new Data("C:\\Users\\Poowis\\Desktop\\grades.csv");
            for (ArrayList row : data){
                System.out.println(row);
            }
            System.out.println(data.getMetaInfo());
            System.out.println(data.getDataSize());
                
        } catch (IOException | FileException ex) {
            Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    
}
