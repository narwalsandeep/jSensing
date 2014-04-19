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

/**
 *
 * @author sandeepnarwal
 */
public class SnrLight extends SnrCore implements InterfaceSnr{
	
	private static SnrLight instance = null;

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
		
		//setValue(currentValue);
		//this.printValue();
			
	}

	/**
	 *
	 * @param value
	 * @throws PhidgetException
	 */
	public void setSensitivity(int value) throws PhidgetException{
		
		//setSensorSensitivity(getSnrIndex(), getSnrValue());
	}
	

}
