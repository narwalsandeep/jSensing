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
import bl.Context;
import com.google.gson.Gson;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;


/**
 *
 * @author author
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
		// @TODO ideally it should be a schedular to fetch GPS coordinates every few minutes.
		// but for the purpose of proof we are using 300 rotator sensor, 
		// as soon as we rotate it over 600 degree, it will hit the URL and fet the cordinates.
		// this way we are simulating GPS fetch, by rotating 300 rotator sensor above 600 value every once a minute.
		if(currentValue > 200){
			try {
				String gpsC = getCoordinates();
				Gson gson = new Gson();
				GpsCordinate gps = gson.fromJson(gpsC, GpsCordinate.class);
				Context.getInstance().setGpsLat(gps.getGpsLat());
				Context.getInstance().setGpsLong(gps.getGpsLong());
				Context.getInstance().checkLatLongWithContext();
				
			} catch (IOException ex) {
			}
		}
		this.setContext();
		
	}
	
	public void setContext(){
		try {
			Context.getInstance().setContext(snrValue, instance);
		} catch (PhidgetException ex) {
			Logger.getLogger(Snr300Rotator.class.getName()).log(Level.SEVERE, null, ex);
		}

	}
	
	public String getCoordinates() throws MalformedURLException, IOException{
		
		URL oracle = new URL("http://localhost/sandbox/test/sensoroid.php");
        URLConnection yc = oracle.openConnection();
        BufferedReader in = new BufferedReader(new InputStreamReader(yc.getInputStream()));
        
		String gps;
        gps = in.readLine();
		in.close();		
		return gps;
		
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
	
	public class GpsCordinate{
		
		private double gpsLat;
		private double gpsLong;

		public double getGpsLat() {
			return gpsLat;
		}

		public void setGpsLat(double gpsLat) {
			this.gpsLat = gpsLat;
		}

		public double getGpsLong() {
			return gpsLong;
		}

		public void setGpsLong(double gpsLong) {
			this.gpsLong = gpsLong;
		}
		
	}
}
