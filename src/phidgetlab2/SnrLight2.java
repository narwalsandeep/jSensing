/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package phidgetlab2;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author sandeepnarwal
 */
public class SnrLight2 extends SnrCore{

	@Override
	public void performTask(){
		
		System.out.println("Snr 2 " + snrValue);
		
		Writer writer = new Writer();
		writer.setContent(Integer.toString(snrValue));
		writer.setFile("toL2File.csv");
		
		try {
			boolean flag = writer.write();
		} catch (IOException ex) {
			Logger.getLogger(SnrLight2.class.getName()).log(Level.SEVERE, null, ex);
		}
		
		
	}

}
