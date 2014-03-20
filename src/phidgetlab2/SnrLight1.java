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
public class SnrLight1 extends SnrCore implements InterfaceSnr{
	
	@Override
	public void initialize(int currentValue){
		
		setValue(currentValue);
		
		System.out.println("getValue() "+getValue());
		
		/*
		Writer writer = new Writer();
		writer.setContent(Integer.toString(snrValue));
		writer.setFile("toL1File.csv");
		
		try {
			boolean flag = writer.write();
		} catch (IOException ex) {
			Logger.getLogger(SnrLight1.class.getName()).log(Level.SEVERE, null, ex);
		}
		*/
		
	}

	public void writeCSV(){
		
		
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
