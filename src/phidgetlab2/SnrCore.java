/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package phidgetlab2;

import com.phidgets.*;
import com.phidgets.event.*;
import java.util.HashMap;
import java.util.Hashtable;

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
    static int SNR_LIGHT_1 = 3;
	static int SNR_LIGHT_2 = 6;
	
	/*
	TODO later use hashmap instead of this array
	*/
	//int connectedSnr = {  };
    
    // sensitivity level, can be from 0 - 1000
    // lower the number higher the sensitivity
    static int SENSITIVE_LEVEL_1 = 900;
    static int SENSITIVE_LEVEL_2 = 700;
    static int SENSITIVE_LEVEL_3 = 550;
    static int SENSITIVE_LEVEL_4 = 350;
    static int SENSITIVE_LEVEL_5 = 200;
    static int SENSITIVE_LEVEL_6 = 100;
    static int SENSITIVE_LEVEL_7 = 50;
    static int SENSITIVE_LEVEL_8 = 20;
    static int SENSITIVE_LEVEL_9 = 1;
    
	// all sensor object goes here
    Snr300Rotator ObjSnr300Rotator;
    SnrIrDistance ObjSnrIrDistance;
    SnrLight1 ObjSnrLight1;
	SnrLight2 ObjSnrLight2;
	
    public SnrCore(){
		
		/*
		TODO refine it, this should contain the list of all sensors
		*/

		/*
		Hashtable<Integer, String> source = new Hashtable<Integer,String>();
		HashMap<Integer, String>  map = new HashMap(source);
		map.put(0, "SNR_300_RORATOR");
		*/
		
	}
	
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
    public void triggerChangedSensors(){
        
		// sensor change even lister
		// from phidget API
        ObjPhiKit.addSensorChangeListener(new SensorChangeListener(){

            @Override
            public void sensorChanged(SensorChangeEvent ChangedSnr) {
                
				/*
				TODO use for loop to auto set values
				put all sensors in an array of objects and loop through each
				adding a sensor should be as simple as adding into array and assigning the port num
				*/
                
				int currentIndex = ChangedSnr.getIndex();
                int currentValue = ChangedSnr.getValue();
                
                if(currentIndex == SNR_300_ROTATOR){
					ObjSnr300Rotator.setValue(currentValue);
                    ObjSnr300Rotator.performTask();
                }
                
				if(currentIndex == SNR_LIGHT_1){
					ObjSnrLight1.setValue(currentValue);

					// get data only in dark light
					if(currentValue < SENSITIVE_LEVEL_5)
	                    ObjSnrLight1.performTask();
                }
				
				if(currentIndex == SNR_LIGHT_2){
                    ObjSnrLight2.setValue(currentValue);

					// get data only in dark light
					if(currentValue < SENSITIVE_LEVEL_5)
		                ObjSnrLight2.performTask();
                }
				
				
				
            }
        
        });
        
    }

    /**
     *
     * @param snr
     */
    public void initializeSensor(int snr) throws PhidgetException {
        
		/*
			TODO later convert to for loop
			so that you do not have to initialize sensors manuall
		
		*/
		// you must initialize each sensor object if want to use
		if(snr == SNR_300_ROTATOR){
			ObjSnr300Rotator = new Snr300Rotator();
			ObjSnr300Rotator.setSensorSensitivity(snr,SENSITIVE_LEVEL_9);
		}
		
		if(snr == SNR_LIGHT_1){
			ObjSnrLight1 = new SnrLight1();
			ObjSnrLight1.setSensorSensitivity(snr,SENSITIVE_LEVEL_9);
		}
		
		if(snr == SNR_LIGHT_2){
			ObjSnrLight2 = new SnrLight2();
			ObjSnrLight2.setSensorSensitivity(snr,SENSITIVE_LEVEL_9);
		}

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
