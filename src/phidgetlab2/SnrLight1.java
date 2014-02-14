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
public class SnrLight1 extends SnrCore{

	@Override
	public void performTask(){
		
		System.out.println("Snr 1 " + snrValue);
		
		Writer writer = new Writer();
		writer.setContent(Integer.toString(snrValue));
		writer.setFile("toL1File.csv");
		
		try {
			boolean flag = writer.write();
		} catch (IOException ex) {
			Logger.getLogger(SnrLight1.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		
	}

	public void writeCSV(){
		
		
	}
	

}
