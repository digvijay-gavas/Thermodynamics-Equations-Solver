/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package he.resource.formula;

import he.resource.input.Input;
import he.resource.input.Inputs;
import java.lang.reflect.Array;
import java.util.Vector;

/**
 *==========================================================================================================
 *         Author                Date                                   Description
 *==========================================================================================================
 *
 */
public class Main {
    public static void main(String args[]){
        /*double a1 = 5;
        double a2 = 6;
        double a3 = 150;
        double a4 = 80;
        double a5 = 4;
        double a6 = 20;
        double tukka = 0;
        double a8 = 100;
        
        double answer = EnergyBalance.getSolution(a1, a2, a3, a4, a5, tukka, a6, a8);
        System.out.println(EnergyBalance.getSolution(a1, a2, a3, a4, a5, tukka, a6, a8));
        
        if (EnergyBalance.getSolution(a1, a2, a3, a4, a5, tukka, a6, a8)>-100000){
            double tukka1 = tukka + 10;
            double answer1 = EnergyBalance.getSolution(a1, a2, a3, a4, a5, tukka1, a6, a8);
            System.out.println(EnergyBalance.getSolution(a1, a2, a3, a4, a5, tukka, a6, a8));
            double m = (answer1 - answer)/(tukka1 - tukka);
            double c = answer - (m * tukka);
            double tukka2 = -c/m;
            System.out.println(EnergyBalance.getSolution(a1, a2, a3, a4, a5, tukka2, a6, a8));
            System.out.println(tukka2);
        }
        */
        
        /*
        double Q = 40;
        double U = 14;
        double A = 2;
        double Tm = 5;
        double d[]={ Q, U, A, Tm};
                
        Formula h = new Formula(2);
        double answer = h.solve(d);
        System.out.println("Input Count = " + h.numberofinputsrequired());
        if (h.solve(d)<0){
            double tukka1 = Tm + 10;
            d[d.length-1]=tukka1;
            double answer1 = h.solve(d);
            System.out.println(h.solve(d));
            double m = (answer1 - answer)/(tukka1 - Tm);
            double c = answer - (m * Tm);
            double tukka2 = -c/m;
            d[d.length-1]=tukka2;
            System.out.println(h.solve(d));
            System.out.println(tukka2);
            for (int i = 0; i < d.length; i++) {
                System.out.println(i+"  > "+d[i]);
            }
            
            
        }*/
        
        Inputs inputs = new Inputs();
        inputs.add(new Input(1, 6.93));
        inputs.add(new Input(2, 6.3));
        inputs.add(new Input(3, 3810));
        inputs.add(new Input(4, 4180));
        inputs.add(new Input(5, 273+66));
        inputs.add(new Input(6, 273+10));
        inputs.add(new Input(7, 273+42));
        inputs.add(new Input(8, 273+0,true));//unknown
        inputs.add(new Input(9, 568));
        inputs.add(new Input(10, 72));
        //inputs.add(new Input(11, 1,true));
        //inputs.add(new Input(12, 1,true));
        Formula h = new Formula(2);
        //h.solve(inputs);
        for (int i = 0; i < h.inputids.length; i++) {
            System.err.println(">> "+h.inputids[i]);
        }
        
        
    }

}
