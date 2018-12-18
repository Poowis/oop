/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Tester;

import Engine.Data;
import Engine.Main;
import Exception.FileException;
import InputAndOutput.MainIO;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Poowis
 */
public class Tester {

//    public static void main(String[] args) {
//        try {
//            Data data = new Data("C:\\Users\\Poowis\\Desktop\\grades.csv");
//            for (ArrayList row : data) {
//                System.out.println(row);
//            }
//            System.out.println(data.getMetaInfo());
//            System.out.println(data.getDataSize());
//
//        } catch (IOException | FileException ex) {
//            Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
//        }
//
//        Double d = 0.0;
//        try {
//            d = Double.parseDouble("u");
//        } catch (Exception ex) {
//        }
//        System.out.println(d);
//        System.out.println("12   /fff/12".matches("\\d{1,2} */ *(\\d{1,2}|\\p{Alpha}{3,}) */\\d{2,4}"));
//
//    }
//    public static void main(String[] args) {
//        Main main = new Main();
//        try {
//            main.newWS();
//            main.getCurrentWS().importData("C:\\Users\\Poowis\\Desktop\\grades.csv");
//            MainIO.saveMain(main);
//        } catch (IOException | FileException ex) {
//            Logger.getLogger(Tester.class.getName()).log(Level.SEVERE, null, ex);
//        }
//    }
    public static void main(String[] args) {
        System.out.println("aswed -- -a".matches(".*(-|--).*"));
        System.out.println(Arrays.toString("aswed - a".split(" *- *", 2)));
    }
}