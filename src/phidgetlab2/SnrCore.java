/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package phidgetlab2;

import com.phidgets.*;
import com.phidgets.event.*;

/**
 *
 * @author sandeepnarwal
 */
public class SnrCore extends PhiCore{
 
    int snrIndex, snrValue;
	
	// must define all sensor port number
	// whenever sensors are attached
    static int SNR_300_ROTATOR = 0;
    static int SNR_IR_DISTANCE = 1;
    
    // sensitivity level, can be from 0 - 1000
    // lower the number higher the sensitivity
    static int SENSITIVE_LEVEL_1 = 900;
    static int SENSITIVE_LEVEL_2 = 650;
    static int SENSITIVE_LEVEL_3 = 450;
    static int SENSITIVE_LEVEL_4 = 250;
    static int SENSITIVE_LEVEL_5 = 1;
    
	// all sensor object goes here
    Snr300Rotator ObjSnr300Rotator;
    SnrIrDistance ObjSnrIrDistance;
    
    /**
     *
     * @param Snr
     * @param level
     * @throws PhidgetException
     */
    public void setSensorSensitivity(int Snr, int level) throws PhidgetException {    
        
		// this is where any sensor change any sensitivity of any other sensor
		// depends what object is using this call
        ObjPhiKit.setSensorChangeTrigger(Snr,level);
    }

    
    /**
     *  
     */
    public void getChangedSensors(){
        
		// sensor change even lister
		// from phidget API
        ObjPhiKit.addSensorChangeListener(new SensorChangeListener(){

            @Override
            public void sensorChanged(SensorChangeEvent ChangedSnr) {
                
                int currentIndex = ChangedSnr.getIndex();
                
                //System.out.println(data);
                if(currentIndex == SNR_300_ROTATOR){
                    ObjSnr300Rotator.setValue(ChangedSnr.getValue());
                    ObjSnr300Rotator.performTask();
                }
                
                //System.out.println(data);
                if(snrIndex == SNR_IR_DISTANCE){
                    //ObjSnrIrDistance.setValue(snrValue);
                    //ObjSnrIrDistance.performTask();
                }
            }
        
        });
        
    }

    /**
     *
     * @param snr
     */
    public void initializeSensor(int snr) {
        
		// you must initialize each sensor object if want to use
		if(snr == SNR_300_ROTATOR)
			ObjSnr300Rotator = new Snr300Rotator();
        
		if(snr == SNR_IR_DISTANCE)
			ObjSnrIrDistance = new SnrIrDistance();
        
    }
	
	/**
     *
     * @param val
     */
    public void setValue(int val){
        snrValue = val;
    }
    
    /**
     *
     */
    public int getValue(){
		
		return snrValue;
    }
    
    /**
     *
     */
    public void printValue(){
        System.out.println(snrValue);
    }
    
    /**
     * you must override this method
     */
    public void performTask(){
        
    }

}
