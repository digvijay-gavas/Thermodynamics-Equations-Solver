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
public class HeatEquation implements FormulaSover{
    /**
     *
     * @param value k
     * @return k
     */
    
    @Override
    public double fzero(double value[]) {
        System.out.println("he.user.resource.formulae.HeatEquation.solve()");
        return (value[0]-(value[1]*value[2]*value[3]));
    }

   

}
