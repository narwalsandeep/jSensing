/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package phidgetlab2;


/**
 *
 * @author sandeepnarwal
 */
public class PhidgetLab2 {

    
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
        SnrCore ObjSnrCore = new SnrCore();
        // trigger the change event
        
		ObjSnrCore.initSensors();
        
        // wait for input, so that we see the output
        System.in.read();

        // close, if any key above id pressed.
        ObjPhiCore.close();
    }
     
}
