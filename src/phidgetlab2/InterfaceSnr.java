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
public interface InterfaceSnr {
	
	public void setSensitivity(int value) throws PhidgetException;

	public void initialize(int value);
	
}
