package com.example.monkeymayhem;

import java.util.Timer;
import java.util.TimerTask;

public class PowerUpShield extends PowerUp {
	private boolean activated=false;
	private Monkey shieldHolder;//monkey
	private int activatedWidth,activatedHeight;
	private Timer timer;
	private boolean timesUp=false;
	public PowerUpShield(int x, int y) {
		super(x, y);
		timer=new Timer();
	}
	public PowerUpShield(int x, int y,Monkey monkey) {
		super(x, y);
		timer=new Timer();
		shieldHolder=monkey;
		activated=true;
	}
	@Override
	public void update(){
		if(!activated){
			super.update();
		}else{
	        	x-=shieldHolder.getDeltaX();
	        	y-=shieldHolder.getDeltaY();
		}
	}
	public boolean isActivated() {
		return activated;
	}
	public void activate(Monkey monkey) {
		this.activated = true;
		shieldHolder=monkey;
		//position shield around monkey;
		x=(monkey.getCenterX()+monkey.getWidth()/2)-activatedWidth/2;
		y=(monkey.getCenterY()+monkey.getHeight()/2)-activatedHeight/2;
		if(activated){
			timer.schedule(new ShieldDurationTask(), 10*1000);
		}
	}
	class ShieldDurationTask extends TimerTask {
		public void run() {
			timesUp = true;
		}
	}
	public boolean isTimesUp() {
		return timesUp;
	}
	public void setTimesUp(boolean timesUp) {
		this.timesUp = timesUp;
	}
	public int getActivatedWidth() {
		return activatedWidth;
	}
	public int getActivatedHeight() {
		return activatedHeight;
	}
	public void setActivatedWidth(int activatedWidth) {
		this.activatedWidth = activatedWidth;
	}
	public void setActivatedHeight(int activatedHeight) {
		this.activatedHeight = activatedHeight;
	}

	

}
