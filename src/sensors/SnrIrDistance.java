/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sensors;

import core.SnrCore;
import com.phidgets.PhidgetException;

/**
 *
 * @author sandeepnarwal
 */
public class SnrIrDistance extends SnrCore implements InterfaceSnr{

	final private static int PORT = 1;
	
	
    @Override
	public void initialize(int currentValue){
       
    }
	
	/**
	 *
	 * @param value
	 * @throws PhidgetException
	 */
	public void setSensitivity(int value) throws PhidgetException{
		
		setSensorSensitivity(snrIndex, snrValue);
	}
}
