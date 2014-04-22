/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sensors;

import com.phidgets.InterfaceKitPhidget;
import core.SnrCore;
import com.phidgets.PhidgetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;
import lib.HighPassFilter;
import lib.Http;
import lib.LiveChart;
import logics.Context;


/**
 *
 * @author null
 */
public class Snr300Rotator extends SnrCore implements InterfaceSnr{
    
	private int snrIndex;
	private int snrValue;
	
	
	private static Snr300Rotator instance = null;

	/**
	 *
	 */
	public Snr300Rotator(){
		
	}
	
	/**
	 *
	 * @return
	 */
	public static Snr300Rotator getInstance() {
	  if(instance == null) {
		 instance = new Snr300Rotator();
	  }
	  return instance;
	}
	

	/**
	 *
	 * @param currentValue
	 */
	@Override
	public void trigger(int currentValue){
		
		setSnrValue(currentValue);
		try {
			//this.print(Integer.toString(getSnrValue()));
			SnrIrDistance.getInstance().setSensitivity(getSnrValue());
			SnrMotion.getInstance().setSensitivity(getSnrValue());
			SnrLight.getInstance().setSensitivity(getSnrValue());
			this.setContext();
		} catch (PhidgetException ex) {
			Logger.getLogger(Snr300Rotator.class.getName()).log(Level.SEVERE, null, ex);
		}
		
	}
	
	public void setContext(){
		try {
			Context.getInstance().setContext(snrValue, instance);
		} catch (PhidgetException ex) {
			Logger.getLogger(Snr300Rotator.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
	/**
	 *
	 * @param value
	 * @throws PhidgetException
	 */
	@Override
	public void setSensitivity(int value) throws PhidgetException{
		
		setSensorSensitivity(getSnrIndex(),getSnrValue());
	}

	/**
	 *
	 * @return
	 */
	public int getSnrIndex() {
		return snrIndex;
	}

	/**
	 *
	 * @param snrIndex
	 */
	public void setSnrIndex(int snrIndex) {
		this.snrIndex = snrIndex;
	}

	/**
	 *
	 * @return
	 */
	public int getSnrValue() {
		return snrValue;
	}

	/**
	 *
	 * @param snrValue
	 */
	public void setSnrValue(int snrValue) {
		this.snrValue = snrValue;
	}
}
