/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package bl;

import com.phidgets.InterfaceKitPhidget;
import com.phidgets.PhidgetException;
import core.SnrCore;
import sensors.SnrIrDistance;
import sensors.SnrLight;
import sensors.SnrMotion;

/**
 *
 * @author author
 */
public class Context extends SnrCore{
	
	private static double GPS_LAT_STATIC = 1.1111;
	private static double GPS_LONG_STATIC = 2.2222;
	
	private boolean isGuest = false;
	
	private boolean userInRoomStatus;
	
	private boolean userOnSeatStatus;
	
	private boolean userAwayStatus;
	
	private int lastTimeUserIn;
	
	private int lastTimeUserOut;
	
	private int userInSince;
	
	private int userOutSince;
	
	private boolean isDayLight;
	
	private double gpsLat;
	private double gpsLong;

	private static Context instance = null;
	
	public Context(){
		
	}
	
	public static Context getInstance() {
	  if(instance == null) {
		 instance = new Context();
	  }
	  return instance;
	}

	public void setContext(int snrValue,Object remoteInstance) throws PhidgetException{
		
		if(remoteInstance instanceof SnrIrDistance){
			this.distanceSnrTriggered(snrValue);
		}
		if(remoteInstance instanceof SnrMotion){
			this.motionSnrTriggered(snrValue);
		}
		if(remoteInstance instanceof SnrLight){
			this.lightSnrTriggered(snrValue);
		}
	}

	private void lightSnrTriggered(int snrValue) throws PhidgetException {

		// if current state is true, means detected
		if(SnrLight.getInstance().getDayLightStatus()){
			this.isDayLight = true;
		}
		else{
			this.isDayLight = false;
		}
		this.triggerLamp();
		
	}

	private void motionSnrTriggered(int snrValue) {
		
		// if current state is true, means detected
		if(SnrMotion.getInstance().getCurrentState() == true){
			this.userInRoomStatus = true;
			this.p("GUEST or USER-IN-ROOM");

		}
		else{
			this.userInRoomStatus = false;
		}
		this.triggerLaptop();
			
	}

	private void distanceSnrTriggered(int snrValue) {
		if(SnrIrDistance.getInstance().getCurrentDistance() > 0){
			this.userOnSeatStatus = true;
			this.p("GUEST or USER-AT-DESK");

		} 
		else {
			this.userOnSeatStatus = false;
		}
		this.triggerLaptop();

	}

	public void triggerLamp() throws PhidgetException{
		if(!this.isDayLight && this.userOnSeatStatus){
			p("turn LED on");
			getObjPhiKit().setOutputState(0,true);
		}
		else{
			getObjPhiKit().setOutputState(0,false);
			p("turn LED off");
		}
	}
	
	public void triggerLaptop(){
		if(!this.userOnSeatStatus){
			//p("logoff 000 ");
			// put skype away
		}
		else{
			//p("login 111 ");
			// skype available
		}
	}
	
	
	
	public boolean isUserInRoomStatus() {
		return userInRoomStatus;
	}

	public void setUserInRoomStatus(boolean userInRoomStatus) {
		this.userInRoomStatus = userInRoomStatus;
	}

	public boolean isUserOnSeatStatus() {
		return userOnSeatStatus;
	}

	public void setUserOnSeatStatus(boolean userOnSeatStatus) {
		this.userOnSeatStatus = userOnSeatStatus;
	}

	public int getLastTimeUserIn() {
		return lastTimeUserIn;
	}

	public void setLastTimeUserIn(int lastTimeUserIn) {
		this.lastTimeUserIn = lastTimeUserIn;
	}

	public int getLastTimeUserOut() {
		return lastTimeUserOut;
	}

	public void setLastTimeUserOut(int lastTimeUserOut) {
		this.lastTimeUserOut = lastTimeUserOut;
	}

	public int getUserInSince() {
		return userInSince;
	}

	public void setUserInSince(int userInSince) {
		this.userInSince = userInSince;
	}

	public int getUserOutSince() {
		return userOutSince;
	}

	public void setUserOutSince(int userOutSince) {
		this.userOutSince = userOutSince;
	}

	private void p(String s){
		System.out.println(s);
	}

	public boolean isUserAwayStatus() {
		return userAwayStatus;
	}

	public void setUserAwayStatus(boolean userAwayStatus) {
		this.userAwayStatus = userAwayStatus;
	}

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

	public void checkLatLongWithContext() {
		
		if(this.getGpsLat() == Context.GPS_LAT_STATIC  && this.getGpsLong() == Context.GPS_LONG_STATIC){

			this.isGuest = false;
			
			if(this.userInRoomStatus == true && this.userOnSeatStatus == true){	
				this.p("USER-AT-DESK");
			}
		}
		else{
			if(this.userInRoomStatus == true && this.userOnSeatStatus == true){
				this.isGuest = true;
				this.p("GUEST-AT-DESK");
			}
		}
		
	}
}
