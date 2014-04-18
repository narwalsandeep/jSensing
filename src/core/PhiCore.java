/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package core;

import com.phidgets.*;

/**
 *
 * @author sandeepnarwal
 */
public class PhiCore {
    
    // wait 10 seconds only
    static int WAIT_MILI_SECONDS = 10000;
    
    // kit obj
    static InterfaceKitPhidget ObjPhiKit;
    
	static int MIN_CHANGE_TRIGGER = 1;
    //SnrIrDistance ObjSnrIrDistance;

    /**
     *
     */
    public PhiCore(){
        
    }
    
    /**
     * must throws Exception because of InterfaceKitPhidget implementations
     * @throws Exception
     */
     public void initialize() throws Exception{
        
        // Phi kit object
        ObjPhiKit = new InterfaceKitPhidget();
        
		
        // open Phi board, there can be more then one Phi board attached,
        // in such a case use open(ID).
        // below will open first available Phidget
        // this will open the port for phi so that it can connect
        // NOTE: phi is not ready yet
        ObjPhiKit.openAny();
        
        try{
            // not check if phi is ready and well connected
            ObjPhiKit.waitForAttachment(WAIT_MILI_SECONDS);
			
			// at this point sensors are attached
			// configure all sensor to be used in change event
			ObjPhiKit.setSensorChangeTrigger(SnrCore.SNR_IR_DISTANCE, MIN_CHANGE_TRIGGER);
			ObjPhiKit.setSensorChangeTrigger(SnrCore.SNR_300_ROTATOR, MIN_CHANGE_TRIGGER);
			ObjPhiKit.setSensorChangeTrigger(SnrCore.SNR_LIGHT, MIN_CHANGE_TRIGGER);
			ObjPhiKit.setSensorChangeTrigger(SnrCore.SNR_VIBRATION_1, MIN_CHANGE_TRIGGER);
			ObjPhiKit.setSensorChangeTrigger(SnrCore.SNR_VIBRATION_2, MIN_CHANGE_TRIGGER);
			ObjPhiKit.setSensorChangeTrigger(SnrCore.SNR_MOTION, MIN_CHANGE_TRIGGER);
                
        }
        catch(Exception e){
            // if not connectint even after 10 seconds
            System.out.println("Not connected.");
        }
        
    }
    
    
    /**
     *
     * @throws PhidgetException
     */
    public void close() throws PhidgetException{
        
        // always close every device you attach
        ObjPhiKit.close();
        
        // and set object to null
        ObjPhiKit = null;

    }
}
 