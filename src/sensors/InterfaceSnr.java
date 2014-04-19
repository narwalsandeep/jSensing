/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sensors;

import com.phidgets.PhidgetException;
import core.SnrCore;

/**
 *
 * @author sandeepnarwal
 */
public interface InterfaceSnr {
	

	public void setSensitivity(int value) throws PhidgetException;

	public void trigger(int value) throws PhidgetException;
	

}
