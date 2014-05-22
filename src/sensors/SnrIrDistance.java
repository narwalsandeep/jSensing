/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sensors;

import core.SnrCore;
import com.phidgets.PhidgetException;
import core.PhiCore;
import java.util.logging.Level;
import java.util.logging.Logger;
import lib.HighPassFilter;
import lib.LiveChart;
import bl.Context;
import lib.Writer;
/**
 *
 * @author author
 */
public class SnrIrDistance extends SnrCore implements InterfaceSnr{

	
	private int snrValue;
	private int snrIndex;
	private HighPassFilter ObjHPFilter;
	private double hpF;
	
	private double currentDistance;

	private static SnrIrDistance instance = null;
		
	public SnrIrDistance(){
		
	}
	
	public static SnrIrDistance getInstance() {
	  if(instance == null) {
		 instance = new SnrIrDistance();
	  }
	  return instance;
	}

    @Override
	public void trigger(int currentValue){
		
		//this.setSnrValue(currentValue);
		double newV = LiveChart.getInstance().estimate(currentValue);
		
		// set new snr value from particle filter
		this.setSnrValue((int)newV);
		
		this.calculateDistance();
		this.setContext();

		
    }

	public void setContext(){
		try {
			Context.getInstance().setContext(snrValue, instance);
		} catch (PhidgetException ex) {
			Logger.getLogger(SnrIrDistance.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
	
	/**
	 *
	 * @param value
	 * @throws PhidgetException
	 */
	@Override
	public void setSensitivity(int value) throws PhidgetException{
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

	public double getCurrentDistance() {
		return currentDistance;
	}

	public void setCurrentDistance(double currentDistance) {
		this.currentDistance = currentDistance;
	}
	
	private void calculateDistance(){

		if(this.snrValue > 80 && this.snrValue < 530){
			double distance  = 2076 / (this.snrValue - 11 );
			this.setCurrentDistance(distance);
			//this.print("Distance at: " + String.valueOf(distance) + "cm.");
		}
		else{
			this.setCurrentDistance(0);
		}

		
	}

}
