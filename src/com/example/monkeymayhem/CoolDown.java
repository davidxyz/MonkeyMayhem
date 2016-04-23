package com.example.monkeymayhem;

import java.util.Timer;
import java.util.TimerTask;

//a class to manage bullets shooting coolDown/delay 
public class CoolDown {
	private boolean valid;
	private Timer timer;
	private long delay = 400;

	public CoolDown() {
		timer = new Timer();
		valid = true;
	}
	public CoolDown(long d) {
		timer = new Timer();
		valid = true;
		delay=d;
	}

	public boolean checkValidity() {
		if (valid) {
			valid = false;
			timer.schedule(new Task(), delay);
			return true;
		}
		return false;
	}

	class Task extends TimerTask {

		public void run() {
			valid = true;

		}

	}

	public long getDelay() {
		return delay;
	}
	public void setDelay(long delay) {
		this.delay = delay;
	}
}