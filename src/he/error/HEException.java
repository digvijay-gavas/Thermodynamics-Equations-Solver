/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package he.error;

/**
 *
 * @author Digvijay
 */
public class HEException extends Exception{

    public HEException(String message) {
        super(message);
    }

    public HEException(String message,Throwable e) {
        super(message,e);        
    }
    public HEException(Throwable e) {
        super(e);        
    }
    
    
    
}
