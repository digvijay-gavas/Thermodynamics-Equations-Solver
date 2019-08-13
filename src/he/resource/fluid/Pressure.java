/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package he.resource.fluid;

/**
 *==========================================================================================================
 *         Author                Date                                   Description
 *==========================================================================================================
 *  Prasad Patil              18/09/2016                 Initial version created
 */
public class Pressure extends FluidProperty{
    private double pressure;
    
    public Pressure(double pressure){
        this.pressure = pressure;
    }

    /**
     * @return the pressure
     */
    public double getValue() {
        return pressure;
    }

    /**
     * @param pressure the pressure to set
     */
    public void setValue(double pressure) {
        this.pressure = pressure;
    }
    

}
