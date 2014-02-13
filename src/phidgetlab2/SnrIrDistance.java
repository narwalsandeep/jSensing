/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package phidgetlab2;

import com.phidgets.PhidgetException;

/**
 *
 * @author sandeepnarwal
 */
public class SnrIrDistance extends SnrCore{
    
    @Override
	public void performTask(){
       
    }
	
	public void setSensitivity(int value) throws PhidgetException{
		
		setSensorSensitivity(snrIndex, snrValue);
	}
}
