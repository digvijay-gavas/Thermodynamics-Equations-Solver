/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package he.resource.formula;

import he.resource.input.Input;

/**
 *==========================================================================================================
 *         Author                Date                                   Description
 *==========================================================================================================
 *
 */
public class UnknownIterator {
    Input input;
    Formula formula;
    double Q = 40;
        double U = 14;
        double A = 2;
        double Tm = 5;
    double d[]={ Q, U, A, Tm};
    public UnknownIterator(Input input, Formula formula)
    {
        this.input = input;
        this.formula = formula;
    }
    
    /*public double iterator()
    {
        if (input.isUnknown())
        {
            double answer = formula.solve(d);
            if (formula.solve(d)<0)
            {
                double tukka1 = Tm + 10;
                d[d.length-1]=tukka1;
                double answer1 = formula.solve(d);
                System.out.println(formula.solve(d));
                double m = (answer1 - answer)/(tukka1 - Tm);
                double c = answer - (m * Tm);
                double tukka2 = -c/m;
                d[d.length-1]=tukka2;
                System.out.println(formula.solve(d));
                System.out.println(tukka2);
                for (int i = 0; i < d.length; i++)
                {
                    System.out.println(i+"  > "+d[i]);
                }
            }
        }
        return 5;
    }*/
}
    
    

