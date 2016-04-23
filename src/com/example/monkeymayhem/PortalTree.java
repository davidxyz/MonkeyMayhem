package com.example.monkeymayhem;

import java.awt.Rectangle;

import screens.GameScreen;

public class PortalTree {
private int centerX,centerY,width;
private boolean visible=false;
private Monkey monkey;
private Rectangle portalCollisionBox;
//initalize the background variables             
private static Background bg2 = GameScreen.getBg2();

	public PortalTree(int width,int height,Monkey monkey) {
		this.centerX = MainGame.SCREEN_WIDTH;
		this.centerY = MainGame.GROUND;
		this.width=width;
		this.monkey=monkey;
		portalCollisionBox=new Rectangle(centerX+width/2-30,centerY+height/2,80,75);
	}
	public void update(){
		if(monkey.getBackgroundDistanceCovered()+width>=monkey.getMaxBackgroundDistanceCovered()){
			visible=true;
			
		}
		if(visible){
			centerX+=bg2.getSpeedX();
			portalCollisionBox.setLocation((int)(portalCollisionBox.getX()+bg2.getSpeedX()),(int)portalCollisionBox.getY());
		}
	}
	public int getCenterX() {
		return centerX;
	}
	public int getCenterY() {
		return centerY;
	}
	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}
	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}
	public boolean isVisible() {
		return visible;
	}
	public void setVisible(boolean visible) {
		this.visible = visible;
	}
	public Rectangle getCollisionBox() {
		return portalCollisionBox;
	}
}
