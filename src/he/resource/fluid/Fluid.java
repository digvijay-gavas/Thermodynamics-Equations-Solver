/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package he.resource.fluid;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
/**
 *==========================================================================================================
 *         Author                Date                                   Description
 *==========================================================================================================
 */
public class Fluid {
    
    
    
    private String Fluid;
    private double density;
    ResultSet f;
    public Fluid(String FluidName) throws Exception {
        //FluidDatabase.retriveFluid(FluidName);
        this.Fluid=FluidName;

    }
    
    private ResultSet getVicinityDataPoints(Temperature temp,Pressure pre,String fluidproperty) throws SQLException{
        Connection con1 = FluidDatabase.getDatabaseConnection();
        PreparedStatement psF = con1.prepareStatement("select temperature,pressure,"+ fluidproperty +","
                + "((temperature-?)*(temperature-?)+(pressure-?)*(pressure-?)) as Y from "+ Fluid +" order by Y");
        
        //psF.setString(1, fluidproperty);
        psF.setDouble(1, temp.getValue());
        psF.setDouble(2, temp.getValue());
        psF.setDouble(3, pre.getValue());
        psF.setDouble(4, pre.getValue());
                
        ResultSet rF=psF.executeQuery();
        
        return rF;
    }
    
    private double[] getPropertyNearestDataArray(Temperature temp,Pressure pre,String property) throws SQLException{
        ResultSet rF= getVicinityDataPoints(temp, pre, property);
        
        int temp1 = 0;
        double[] tempArray = new double[3];
        while(temp1<=2)
        {
            rF.next();
                tempArray[temp1] = rF.getDouble(property);
                System.out.println(property +" = " + tempArray[temp1]);
                temp1++;
        }
        return tempArray;
    }
    
    private boolean IsPropertyArrayHaveDuplicateValues(double[] Array){
        return Array[0] == Array[1] && Array[1] == Array[2];
    }
    
     private boolean IsPointEnclosedByData(Temperature temp,Pressure pre) throws SQLException{
           
        double[] tempArray = getPropertyNearestDataArray(temp, pre, "Temperature");
        double[] pressueArray = getPropertyNearestDataArray(temp, pre, "Pressure");
        //double[] densityArray = getPropertyNearestDataArray(temp, pre, "Density");
           double A = (tempArray[0]*(pressueArray[1] - pressueArray[2]) + tempArray[1]*(pressueArray[2] - pressueArray[0]) + tempArray[2]*(pressueArray[0]-pressueArray[1]))/2;
           double A1 = (temp.getValue()*(pressueArray[1] - pressueArray[2]) + tempArray[1]*(pressueArray[2] - pre.getValue()) + tempArray[2]*(pre.getValue()-pressueArray[1]))/2;
           double A2 = (tempArray[0]*(pre.getValue() - pressueArray[2]) + temp.getValue()*(pressueArray[2] - pressueArray[0]) + tempArray[2]*(pressueArray[0]-pre.getValue()))/2;
           double A3 = (tempArray[0]*(pressueArray[1] - pre.getValue()) + tempArray[1]*(pre.getValue() - pressueArray[0]) + temp.getValue()*(pressueArray[0]-pressueArray[1]))/2;
           if (A == A1 + A2 + A3)
           {
               return true; 
           }
           System.out.println("No encapsulating data found for interpolation, accuracy comprmized");
           return false;
       }
     
     private double calculateInterpolatedProperty(double[] Array1, double[] Array2, double[] Array3, Temperature temp,Pressure pre){
         if (Array1[0] == Array1[1] && Array1[1] == Array1[2])
       {
           double ans = Array3[0] + ((pre.getValue() - Array2[0])*(Array3[1]-Array3[0])/(Array2[1]-Array2[0]));
           System.out.println(ans);
           return ans;
       }
       if (Array2[0] == Array2[1] && Array2[1] == Array2[2])
       {
           double ans = Array3[0] + ((temp.getValue() - Array1[0])*(Array3[1]-Array3[0])/(Array1[1]-Array1[0]));
           System.out.println(ans);
           return ans;
       }
       
       else{
                double m = ((Array3[2] - Array3[0])-((Array3[1] - Array3[0])*(Array1[2]-Array1[0])/(Array1[1]-Array1[0])))/
                ((Array2[2]-Array2[0])-((Array2[1]-Array2[0])*(Array1[2]-Array1[0])/(Array1[1]-Array1[0])));
        
                double n = ((Array3[1] - Array3[0]) - m*(Array2[1]-Array2[0]))/(Array1[1]-Array1[0]);
        
                double c = Array3[0] - m * Array2[0] - n * Array1[0];
        
                System.out.println(m+ "   " + n + "   " + c);
        
                double ans = m * temp.getValue() + n * pre.getValue() + c;
        
                System.out.println(ans);
                
                return ans;
       }
     }

    public double getDensity(Temperature temp,Pressure pre) throws SQLException {
       
        double[] tempArray = getPropertyNearestDataArray(temp, pre, "Temperature");
        double[] pressueArray = getPropertyNearestDataArray(temp, pre, "Pressure");
        double[] densityArray = getPropertyNearestDataArray(temp, pre, "Density");
         
        System.out.println(IsPointEnclosedByData(temp, pre));
        //System.out.println(IsPropertyArrayHaveDuplicateValues(tempArray));
        return calculateInterpolatedProperty(tempArray, pressueArray, densityArray, temp, pre);
       
    }   
    
    public double getSatVaporPressure(Temperature temp,Pressure pre) throws SQLException {
       
        double[] tempArray = getPropertyNearestDataArray(temp, pre, "Temperature");
        double[] pressueArray = getPropertyNearestDataArray(temp, pre, "Pressure");
        double[] satvaporpressureArray = getPropertyNearestDataArray(temp, pre, "SatVaporPressure");
         
        System.out.println(IsPointEnclosedByData(temp, pre));
        //System.out.println(IsPropertyArrayHaveDuplicateValues(tempArray));
        return calculateInterpolatedProperty(tempArray, pressueArray, satvaporpressureArray, temp, pre);
    
    }
    
    public double getSpecificEnthalpy(Temperature temp,Pressure pre) throws SQLException {
       
        double[] tempArray = getPropertyNearestDataArray(temp, pre, "Temperature");
        double[] pressueArray = getPropertyNearestDataArray(temp, pre, "Pressure");
        double[] spenthalpyArray = getPropertyNearestDataArray(temp, pre, "SpEnthalpy");
         
        System.out.println(IsPointEnclosedByData(temp, pre));
        //System.out.println(IsPropertyArrayHaveDuplicateValues(tempArray));
        return calculateInterpolatedProperty(tempArray, pressueArray, spenthalpyArray, temp, pre);
    
    }
    
    public double getSpecificHeat(Temperature temp,Pressure pre) throws SQLException {
       
        double[] tempArray = getPropertyNearestDataArray(temp, pre, "Temperature");
        double[] pressueArray = getPropertyNearestDataArray(temp, pre, "Pressure");
        double[] spheatArray = getPropertyNearestDataArray(temp, pre, "SpHeat");
         
        System.out.println(IsPointEnclosedByData(temp, pre));
        //System.out.println(IsPropertyArrayHaveDuplicateValues(tempArray));
        return calculateInterpolatedProperty(tempArray, pressueArray, spheatArray, temp, pre);
    
    }
    
    public double getVolumetricHeatCapacity(Temperature temp,Pressure pre) throws SQLException {
       
        double[] tempArray = getPropertyNearestDataArray(temp, pre, "Temperature");
        double[] pressueArray = getPropertyNearestDataArray(temp, pre, "Pressure");
        double[] volheatcapacityArray = getPropertyNearestDataArray(temp, pre, "VolHeatCapacity");
         
        System.out.println(IsPointEnclosedByData(temp, pre));
        //System.out.println(IsPropertyArrayHaveDuplicateValues(tempArray));
        return calculateInterpolatedProperty(tempArray, pressueArray, volheatcapacityArray, temp, pre);
    
    }
    
        public double getDynamicViscosity(Temperature temp,Pressure pre) throws SQLException {
       
        double[] tempArray = getPropertyNearestDataArray(temp, pre, "Temperature");
        double[] pressueArray = getPropertyNearestDataArray(temp, pre, "Pressure");
        double[] dynamicViscosityArray = getPropertyNearestDataArray(temp, pre, "DynamicViscosity");
         
        System.out.println(IsPointEnclosedByData(temp, pre));
        //System.out.println(IsPropertyArrayHaveDuplicateValues(tempArray));
        return calculateInterpolatedProperty(tempArray, pressueArray, dynamicViscosityArray, temp, pre);
    
    }
      
}
