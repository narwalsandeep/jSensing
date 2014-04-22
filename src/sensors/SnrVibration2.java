/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sensors;

import com.phidgets.PhidgetException;
import core.SnrCore;
import logics.Context;

/**
 *
 * @author sandeepnarwal
 */
public class SnrVibration2 extends SnrCore implements InterfaceSnr{
	
	private static SnrVibration2 instance = null;

	public SnrVibration2(){
		
	}
	
	public static SnrVibration2 getInstance() {
	  if(instance == null) {
		 instance = new SnrVibration2();
	  }
	  return instance;
	}

	@Override
	public void trigger(int currentValue){
		
		//setValue(currentValue);
		//this.printValue();
				
	}

	public void setContext(){
	//	Context.getInstance().setContext(snrValue, instance);

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
