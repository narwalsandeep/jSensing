/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sensors;

import core.SnrCore;
import com.phidgets.PhidgetException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import lib.HighPassFilter;
import logics.Context;

/**
 *
 * @author null
 */
public class SnrLight extends SnrCore implements InterfaceSnr{
	
	private static SnrLight instance = null;

	private int snrValue;
	private int snrIndex;
	private HighPassFilter ObjHPFilter;
	private double hpF;
	private boolean dayLightStatus;

	public SnrLight(){
		
	}
	
	public static SnrLight getInstance() {
	  if(instance == null) {
		 instance = new SnrLight();
	  }
	  return instance;
	}

	@Override
	public void trigger(int currentValue){
		
		setSnrValue(currentValue);
		this.setDayLightStatus();
		
		this.setContext();

	}

	public void setContext(){
		try {
			Context.getInstance().setContext(snrValue, instance);
		} catch (PhidgetException ex) {
			Logger.getLogger(SnrLight.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	/**
	 *
	 * @param value
	 * @throws PhidgetException
	 */
	@Override
	public void setSensitivity(int value) throws PhidgetException{
		setSensorSensitivity(this.getSnrIndex(),value);
		//this.print(Integer.toString(getSnrValue()));
		
	}

	public int getSnrIndex() {
		return snrIndex;
	}

	public void setSnrIndex(int snrIndex) {
		this.snrIndex = snrIndex;
	}

	public int getSnrValue() {
		return snrValue;
	}

	public void setSnrValue(int snrValue) {
		this.snrValue = snrValue;
	}

	public void setDayLightStatus(){
		if(this.snrValue < 200){
			this.dayLightStatus = false;
		}
		else{
			this.dayLightStatus = true;
		}
			
	}
	
	public boolean getDayLightStatus() {
		return this.dayLightStatus;
	}
	

}
