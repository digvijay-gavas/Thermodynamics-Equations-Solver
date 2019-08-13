/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package he.resource.process;

import he.resource.formula.Formula;
import he.resource.formula.Formulae;

import he.resource.input.Inputs;

/**
 *
 * @author Digvijay
 */
public class Procedure {
    Inputs inputs;
    Formulae formulae;
    public static double requiredAccuracy=0.1;
    public void setInputs(Inputs inputs)
    {
        this.inputs=inputs;
    }
    
   
   
    public void run()
    {
        updateUnknownCount();
        for (int i = 0; i < formulae.size(); i++) {
            formulae.get(i).setInputs(inputs);
            if(formulae.get(i).unknownCount==1)
            {
                System.out.println("he.resource.process.Process.run()");
                System.out.println("ans"+formulae.get(i).solve());
                //updateUnknownCount();
            }
        }
    }
    
    private void updateUnknownCount()
    {
        for (int i = 0; i < formulae.size(); i++) {
            for (int j = 0; j < inputs.size(); j++) {
                //int fitNo=0;
                for (int k = 0; k < formulae.get(i).inputids.length; k++) {
                    if (formulae.get(i).inputids[k]==inputs.get(j).id && inputs.get(j).isUnknown) 
                    {
                        formulae.get(i).unknownCount++;
                        System.out.println(formulae.get(i).name+"  "+inputs.get(j).name +"  "+formulae.get(i).unknownCount);
                    }
                }
            }
        }
    }
    public void display()
    {
        
    }

    public void setFormulae(Formulae formulae) {
        this.formulae=formulae;//throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
}
