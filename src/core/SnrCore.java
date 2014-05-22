/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package core;

import lib.LiveChart;
import lib.Http;
import lib.HighPassFilter;
import com.phidgets.*;
import com.phidgets.event.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import bl.Context;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import sensors.Snr300Rotator;
import sensors.SnrIrDistance;
import sensors.SnrLight;
import sensors.SnrMotion;
/**
 *
 * @author author
 */
public class SnrCore extends PhiCore{
 
    
	// must define all sensor port number
	// whenever sensors are attached
	static int SNR_VIBRATION_2		= 7;
	static int SNR_IR_DISTANCE		= 4;
	static int SNR_MOTION			= 5;
    static int SNR_300_ROTATOR		= 3;
    static int SNR_LIGHT			= 1;
	static int SNR_VIBRATION_1		= 0;
	    	
	private static SnrCore instance = null;
	
	
    public SnrCore(){
		
	}
	public static SnrCore getInstance() {
	  if(instance == null) {
		 instance = new SnrCore();
	  }
	  return instance;
	}
	
		
	public InterfaceKitPhidget getObjPhiKit(){
		
		return ObjPhiKit;
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
    public void initSensors() throws PhidgetException, MalformedURLException, IOException{
        
		SnrMotion.getInstance().setSnrIndex(SNR_MOTION);
		SnrIrDistance.getInstance().setSnrIndex(SNR_IR_DISTANCE);
		SnrLight.getInstance().setSnrIndex(SNR_LIGHT);
		Snr300Rotator.getInstance().setSnrIndex(SNR_300_ROTATOR);
	    ObjPhiKit.setOutputState(0,false);

		Context.getInstance().setContext(0,null);
		
		LiveChart.getInstance().initJPanel();
		
		// sensor change even lister
		// from phidget API
        ObjPhiKit.addSensorChangeListener(new SensorChangeListener(){

            @Override
            public void sensorChanged(SensorChangeEvent ChangedSnr)  {
                
				/*
				TODO use for loop to auto set values
				put all sensors in an array of objects and loop through each
				adding a sensor should be as simple as adding into array and assigning the port num
				*/
                //return null;
				int currentIndex = ChangedSnr.getIndex();
                int currentValue = ChangedSnr.getValue();
                
                if(currentIndex == SNR_MOTION){
					SnrMotion.getInstance().trigger(currentValue);
                }
                if(currentIndex == SNR_300_ROTATOR){
					Snr300Rotator.getInstance().trigger(currentValue);
                }
				if(currentIndex == SNR_LIGHT){
					SnrLight.getInstance().trigger(currentValue);					
				}
				if(currentIndex == SNR_IR_DISTANCE){
					SnrIrDistance.getInstance().trigger(currentValue);
				}
								
            }
        
        });
        
    }
	
	public void print(String s){
		System.out.println(s);
	}

}
