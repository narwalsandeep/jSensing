/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sensors;

import core.SnrCore;
import com.phidgets.PhidgetException;
import core.PhiCore;
/**
 *
 * @author sandeepnarwal
 */
public class SnrIrDistance extends SnrCore implements InterfaceSnr{

	private static SnrIrDistance instance = null;

	public SnrIrDistance(){
		
	}
	
	public static SnrIrDistance getInstance() {
	  if(instance == null) {
		 instance = new SnrIrDistance();
	  }
	  return instance;
	}

    @Override
	public void trigger(int currentValue){
  		//setValue(currentValue);
		//this.printValue();
			
		this.getObjPhiKit();
		
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
