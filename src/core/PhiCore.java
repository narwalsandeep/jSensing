/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package core;

import com.phidgets.*;


/**
 *
 * @author author
 */
public class PhiCore {
    
    // wait 10 seconds only
    static int WAIT_MILI_SECONDS = 5000;
    
    // kit obj
    static InterfaceKitPhidget ObjPhiKit;
    
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
			System.out.println("Attaching sensors ...");
			// at this point sensors are attached
			// configure all sensor trigger change gap
			ObjPhiKit.setSensorChangeTrigger(SnrCore.SNR_IR_DISTANCE, 20);
			ObjPhiKit.setSensorChangeTrigger(SnrCore.SNR_300_ROTATOR, 1);
			ObjPhiKit.setSensorChangeTrigger(SnrCore.SNR_LIGHT, 1);
			//ObjPhiKit.setSensorChangeTrigger(SnrCore.SNR_VIBRATION_1, 1);
			//ObjPhiKit.setSensorChangeTrigger(SnrCore.SNR_VIBRATION_2, 50);
			ObjPhiKit.setSensorChangeTrigger(SnrCore.SNR_MOTION, 0);
			System.out.println("Done.");
			System.out.println("________________________");

			//ObjPhiKit.
			
			
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
 