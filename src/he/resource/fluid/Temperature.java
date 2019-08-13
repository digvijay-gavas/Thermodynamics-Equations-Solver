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
 */
public class Temperature extends FluidProperty{
    private double temperature;

    public Temperature(double temperature) {
        this.temperature = temperature;
    }

    /**
     * @return the temperature
     */
    public double getValue() {
        return temperature;
    }

    /**
     * @param temperature the temperature to set
     */
    public void setValue(double temperature) {
        this.temperature = temperature;
    }

    
}
