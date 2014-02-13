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
        
        // sensor core class object
        // all sensor extend this
        SnrCore ObjSnrCore = new SnrCore();
        
        // initilaze the kit 
        ObjPhiCore.initialize();
        
        // set sensor sensitivity if any, optional
        ObjSnrCore.setSensorSensitivity(SnrCore.SNR_300_ROTATOR, SnrCore.SENSITIVE_LEVEL_5);
        
        // init sensors one by one, as you attach more sensors
		// you can initialize here, or in the codes when needed
        ObjSnrCore.initializeSensor(SnrCore.SNR_300_ROTATOR);
        ObjSnrCore.initializeSensor(SnrCore.SNR_IR_DISTANCE);
        
        // trigger the change event
        ObjSnrCore.getChangedSensors();
        
        // wait for input, so that we see the output
        System.in.read();

        // close, if any key above id pressed.
        ObjPhiCore.close();
    }
     
}
