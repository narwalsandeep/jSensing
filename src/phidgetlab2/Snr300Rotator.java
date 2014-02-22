/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package phidgetlab2;

import com.phidgets.PhidgetException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author sandeepnarwal
 */
public class Snr300Rotator extends SnrCore implements InterfaceSnr{
    
	final private static int PORT = 0;
	
	@Override
	public void performTask(){
		
		System.out.println(snrValue);
		
		Writer writer = new Writer();
		writer.setContent(Integer.toString(snrValue));
		writer.setFile("toFile.csv");
		
		try {
			boolean flag = writer.write();
		} catch (IOException ex) {
			Logger.getLogger(Snr300Rotator.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}
	
	/**
	 *
	 * @throws PhidgetException
	 */
	public void setDistanceSensorSensitivity() throws PhidgetException{
		
		// calculate how much to change distance sensor sensitivity
		
		int value = 0;
		ObjSnrIrDistance.setSensitivity(value);
		
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
