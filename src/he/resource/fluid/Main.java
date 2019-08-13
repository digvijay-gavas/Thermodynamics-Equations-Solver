/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package he.resource.fluid;

import java.sql.ResultSet;

/**
 *==========================================================================================================
 *         Author                Date                                   Description
 *==========================================================================================================
 *
 */
public class Main {

   
    public static void main(String[] args) throws Exception{
        Fluid Water = new Fluid("water");
        Temperature t = new Temperature(11.6);
        Pressure p = new Pressure(1);
        Water.getDensity(t, p);
        ResultSet r = FluidDatabase.getAllFluidProperties("water");
    }

}