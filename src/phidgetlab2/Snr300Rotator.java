/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package phidgetlab2;

/**
 *
 * @author sandeepnarwal
 */
public class Snr300Rotator extends SnrCore{
    

	@Override
	public void performTask(){
		
	}
	
	public void setDistanceSensorSensitivity(){
		
		// calculate how much to change distance sensor sensitivity
		
		int value = 0;
		ObjSnrIrDistance.setSensitivity(value);
		
	}
}
