/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package core;


/**
 *
 * @author author
 */
public class Init {

    
    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception{

        // kit core class object
        PhiCore ObjPhiCore = new PhiCore();
        // initilaze the kit 
        ObjPhiCore.initialize();
        
        // sensor core class object
        // all sensor extend this
        SnrCore ObjSnrCore = SnrCore.getInstance();
        // trigger the change event
        
		ObjSnrCore.initSensors();
        
        // wait for input, so that we see the output
        System.in.read();

        // close, if any key above id pressed.
        ObjPhiCore.close();
    }
     
}
