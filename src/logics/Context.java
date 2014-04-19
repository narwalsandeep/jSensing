/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package logics;

/**
 *
 * @author "as2d3f"
 */
public class Context {
	
	private boolean userIn;
	
	private boolean userOnSeat;
	
	private int lastTimeUserIn;
	
	private int lastTimeUserOut;
	
	private int userInSince;
	
	private int userOutSince;
	
	private boolean lightStatus;

	public boolean isUserIn() {
		return userIn;
	}

	public void setUserIn(boolean userIn) {
		this.userIn = userIn;
	}

	public boolean isUserOnSeat() {
		return userOnSeat;
	}

	public void setUserOnSeat(boolean userOnSeat) {
		this.userOnSeat = userOnSeat;
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

	public boolean isLightStatus() {
		return lightStatus;
	}

	public void setLightStatus(boolean lightStatus) {
		this.lightStatus = lightStatus;
	}
	
	
}
