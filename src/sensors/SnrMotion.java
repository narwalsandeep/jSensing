/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sensors;

import com.phidgets.PhidgetException;
import core.SnrCore;
import java.util.logging.Level;
import java.util.logging.Logger;
import lib.HighPassFilter;
import lib.LiveChart;
import bl.Context;
import java.io.IOException;
import lib.Writer;

/**
 *
 * @author author
 */
public class SnrMotion extends SnrCore implements InterfaceSnr{
	
	private static SnrMotion instance = null;

	private int snrValue;
	private int snrIndex;
	private HighPassFilter ObjHPFilter;
	private double hpF;
	
	private boolean currentState;
	
	public SnrMotion(){
		
	}
	public static SnrMotion getInstance() {
	  if(instance == null) {
		 instance = new SnrMotion();
	  }
	  return instance;
	}
	
	@Override
	public void trigger(int currentValue){
		
		double newV = LiveChart.getInstance().estimate(currentValue);
		
		// set new snr value from particle filter
		this.setSnrValue((int)newV);
		
		
		this.setState();
		this.setContext();
	
	}
	
	public void setContext(){
		try {
			Context.getInstance().setContext(snrValue, instance);
		} catch (PhidgetException ex) {
			Logger.getLogger(SnrMotion.class.getName()).log(Level.SEVERE, null, ex);
		}

	}

	/**
	 *
	 * @param value
	 * @throws PhidgetException
	 */
	@Override
	public void setSensitivity(int value) throws PhidgetException{
		//this.print(Integer.toString(getSnrValue()));
		setSensorSensitivity(this.getSnrIndex(),value);
		//this.print(Integer.toString(getSnrValue()));

	}

	public int getSnrIndex() {
		return snrIndex;
	}

	public void setSnrIndex(int snrIndex) {
		this.snrIndex = snrIndex;
	}

	public int getSnrValue() {
		return snrValue;
	}

	public void setSnrValue(int snrValue) {
		this.snrValue = snrValue;
	}

	public boolean getCurrentState() {
		return currentState;
	}

	public void setCurrentState(boolean currentState) {
		this.currentState = currentState;
	}
	
	public void setState(){
		
		if(this.snrValue > 600 || this.snrValue < 400){
			this.setCurrentState(true);
		}
		else{
			this.setCurrentState(false);
		}
	}
}
