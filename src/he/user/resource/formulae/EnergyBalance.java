/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package he.user.resource.formulae;

import he.resource.formula.Formula;
import he.resource.formula.FormulaSover;
import java.util.Vector;

/**
 *==========================================================================================================
 *         Author                Date                                   Description
 *==========================================================================================================
 *
 */
public class EnergyBalance implements FormulaSover{
    /*public double getSolution(double Me, double Cpe, double Teo, double Tei, double Mw, double Cpw, double Two, double Twi){
    return (Me*Cpe*(Teo-Tei))-(Mw*Cpw*(Two-Twi));
    }*/

    

    @Override
    public double fzero(double value[]) {
        System.out.println("he.user.resource.formulae.EnergyBalance.solve()");
        return (value[0]*value[2]*(value[6]-value[4]))-(value[1]*value[3]*(value[7]-value[5]));    
    }

}
