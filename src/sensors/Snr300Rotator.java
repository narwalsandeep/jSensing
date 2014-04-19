/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sensors;

import com.phidgets.InterfaceKitPhidget;
import core.SnrCore;
import com.phidgets.PhidgetException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author sandeepnarwal
 */
public class Snr300Rotator extends SnrCore implements InterfaceSnr{
    
	private int snrIndex;
	private int snrValue;
	
	private static Snr300Rotator instance = null;

	public Snr300Rotator(){
		
	}
	
	public static Snr300Rotator getInstance() {
	  if(instance == null) {
		 instance = new Snr300Rotator();
	  }
	  return instance;
	}
	

	/**
	 *
	 * @param currentValue
	 * @throws com.phidgets.PhidgetException
	 */
	@Override
	public void trigger(int currentValue) throws PhidgetException{
		
		setSnrValue(currentValue);
		
		this.print(Integer.toString(getSnrValue()));
		SnrMotion.getInstance().setSensitivity(getSnrValue());
		
		
		//getObjSnrCore().getObjSnrMotion().setValue(getObjSnr300Rotator().getValue());
		//SnrMotion.getInstance().printValue();
		
		//getObjSnrMotion().setValue(this.getValue());
		//getObjSnrMotion().printValue();
	}
	
	/**
	 *
	 * @param value
	 * @throws PhidgetException
	 */
	@Override
	public void setSensitivity(int value) throws PhidgetException{
		
		setSensorSensitivity(getSnrIndex(),getSnrValue());
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
}
