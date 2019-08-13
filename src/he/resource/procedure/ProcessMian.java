/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package he.resource.procedure;

import he.resource.formula.Formula;
import he.resource.formula.FormulaDB;
import he.resource.formula.Formulae;
//import he.resource.formula.HeatEquation;
import he.resource.input.Input;
import he.resource.input.Inputs;
import java.io.FileNotFoundException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Digvijay
 */
public class ProcessMian {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        // in real scenario it will be inputed by GUI---------------------------
        Inputs inputs = new Inputs();
        //inputs.add(new Input(1, 6.93));
        //inputs.add(new Input(2, 6.3));
        //inputs.add(new Input(3, 3810));
        //inputs.add(new Input(4, 4180));
        //inputs.add(new Input(5, 273+66));
        //inputs.add(new Input(6, 273+10));
        //inputs.add(new Input(7, 273+42));
        inputs.add(new Input(8, 273+0));//unknown
        //inputs.add(new Input(9, 568));
        //inputs.add(new Input(10, 72));
        //inputs.add(new Input(11, 1,true));
        //inputs.add(new Input(12, 1,true));
        inputs.add(new Input(13, 100,true));
        //inputs.add(new Input(14, 1,true));
        //----------------------------------------------------------------------
        //---------------------inputing Formulae--------------------------------
        //String inputids="5,6,95";
        //Arrays.stream(inputids.split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();
        //int ids[] = Arrays.stream(inputids.split(",")).map(String::trim).mapToInt(Integer::parseInt).toArray();
        //Formulae formulae=new Formulae();
        //formulae.add(new Formula(1));
        //formulae.add(new Formula(2));
        
        //Formula g = null;
        //----------------------------------------------------------------------
        
        //-----------------------Process----------------------------------------
        //Process p=new Process();
        //p.setInputs(inputs);
        //p.setFormulae(formulae);
        
        //p.run();
        try {
            //Formula f1=new Formula(2,"test",inputs,"");
            
            //FormulaDB.add(f1, "({8}*{13}-45*Math.pow({13},2))");
            //Formula f2=new Formula(2);
            //f2.setInputs(inputs);
            Procedure.requiredAccuracy=0.001;
            //System.out.println("he " +f2.solve());
            
            //----------------------------------------------------------------------
        } catch (Exception ex) {
            Logger.getLogger(ProcessMian.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
