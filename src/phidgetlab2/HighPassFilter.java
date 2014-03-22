package phidgetlab2;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 * 
 * about - this filter pass the high peaks, and use the low peaks
 */

/**
 *
 * @author sandeepnarwal
 */
public class HighPassFilter {
	
	double xml, yml;

	public HighPassFilter(){
		
		yml = 0.0;
		xml = 0.0;
		
	}
	
	public double getFilter(double i, double r){
		
		
		yml = i - xml + r * yml;
		xml = i;
		
		return (yml * 500) + 512;
		
	}
}
