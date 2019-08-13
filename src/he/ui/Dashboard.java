/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package he.ui;

import javax.swing.JFrame;

/**
 *
 * @author Digvijay
 */
public class Dashboard {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        JFrame f=new JFrame("HE");
        f.setBounds(100, 100, 200, 500);
        f.setVisible(true);
        InputSearch_InternalFrame i=new InputSearch_InternalFrame();
        
        f.add(i);
        i.setVisible(true);
        
    }
    
}
