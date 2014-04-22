/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logics;

import sensors.SnrIrDistance;
import sensors.SnrLight;
import sensors.SnrMotion;

/**
 *
 * @author "as2d3f"
 */
public class Context {
	
	private boolean userInRoomStatus;
	
	private boolean userOnSeatStatus;
	
	private int lastTimeUserIn;
	
	private int lastTimeUserOut;
	
	private int userInSince;
	
	private int userOutSince;
	
	private boolean isDayLight;

	private static Context instance = null;
	
	
	public Context(){
		
	}
	
	public static Context getInstance() {
	  if(instance == null) {
		 instance = new Context();
	  }
	  return instance;
	}

	public void setContext(int snrValue,Object remoteInstance){
		
		if(remoteInstance instanceof SnrIrDistance){
			this.distanceSnrTriggered(snrValue);
			//System.out.println("distance = " + SnrIrDistance.getInstance().getCurrentDistance() + " cm");
		}
		if(remoteInstance instanceof SnrMotion){
			this.motionSnrTriggered(snrValue);
			//System.out.println("In room = " + snrValue);
		}
		if(remoteInstance instanceof SnrLight){
			this.lightSnrTriggered(snrValue);
			//System.out.println("light on = " + snrValue);

		}
			

	}

	private void lightSnrTriggered(int snrValue) {

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
		if(SnrMotion.getInstance().getCurrentState()){
			this.userInRoomStatus = true;
		}
		else{
			this.userInRoomStatus = false;
		}
		this.triggerLaptop();
			
	}

	private void distanceSnrTriggered(int snrValue) {
		if(SnrIrDistance.getInstance().getCurrentDistance() > 0){
			this.userOnSeatStatus = true;
		} 
		else {
			this.userOnSeatStatus = false;
		}
		this.triggerLaptop();

	}

	public void triggerLamp(){
		if(!this.isDayLight && this.userOnSeatStatus){
			// turn it ON
		}
		else{
			// turn it OFF
			
		}
	}
	
	public void triggerLaptop(){
		if(!this.userOnSeatStatus){
			// loggoff laptop
			// put skype away
		}
		else{
			// login laptop
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

}
