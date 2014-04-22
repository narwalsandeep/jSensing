/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package sensors;

import core.SnrCore;
import com.phidgets.PhidgetException;
import core.PhiCore;
import lib.HighPassFilter;
import lib.LiveChart;
import logics.Context;
/**
 *
 * @author sandeepnarwal
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
		
		this.setSnrValue(currentValue);
		
		//double newValue = ((double)SnrIrDistance.getInstance().getSnrValue())/1024.0;
		//hpF = (double) HighPassFilter.getInstance().getFilter(newValue,0.8);
		//LiveChart.getInstance().estimate(hpF);
			
		this.calculateDistance();
		this.setContext();

		
    }

	public void setContext(){
		Context.getInstance().setContext(snrValue, instance);

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
		}
		else{
			this.setCurrentDistance(0);
		}

		
	}

}
