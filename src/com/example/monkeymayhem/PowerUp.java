package com.example.monkeymayhem;

import screens.GameScreen;

public abstract class PowerUp {
	private static int width,height;
	protected int x;
	protected int y;
	private boolean dropped=false;//if enemy dies
	private boolean offscreen;
	public PowerUp(int x, int y){
		this.x=x;
		this.y=y;
	}
	public void update(){
		if(dropped){
			x+=GameScreen.getBg1().getSpeedX();
			if(x+PowerUp.getWidth()<0){//if powerUp is off screen remove it
				offscreen=true;
			}
		}
	}
	public int getX() {
		return x;
	}
	public int getY() {
		return y;
	}
	public boolean isDropped() {
		return dropped;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public void setDropped(boolean dropped) {
		this.dropped = dropped;
	}
	public static int getWidth() {
		return width;
	}
	public static int getHeight() {
		return height;
	}
	public static void setWidth(int width) {
		PowerUp.width = width;
	}
	public static void setHeight(int height) {
		PowerUp.height = height;
	}
	public boolean isOffscreen() {
		return offscreen;
	}
}
