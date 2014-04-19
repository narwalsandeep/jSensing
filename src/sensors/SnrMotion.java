/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sensors;

import com.phidgets.PhidgetException;
import core.SnrCore;

/**
 *
 * @author null
 */
public class SnrMotion extends SnrCore implements InterfaceSnr{
	
	private static SnrMotion instance = null;

	private int snrValue;
	private int snrIndex;
	
	public SnrMotion(){
		
	}
	public static SnrMotion getInstance() {
	  if(instance == null) {
		 instance = new SnrMotion();
	  }
	  return instance;
	}
	
	@Override
	public void trigger(int currentValue){
		
		setSnrValue(currentValue);
		this.print(Integer.toString(getSnrValue()));

	}

	/**
	 *
	 * @param value
	 * @throws PhidgetException
	 */
	@Override
	public void setSensitivity(int value) throws PhidgetException{
		this.print(Integer.toString(getSnrValue()));
		setSensorSensitivity(this.getSnrIndex(),value);
		this.print(Integer.toString(getSnrValue()));

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
