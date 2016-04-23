package com.example.monkeymayhem;

import java.awt.Rectangle;

import screens.GameScreen;

public class Projectile {
	protected int x, y,width,height;
	protected double  speedX,speedY;
	protected boolean visible;
	protected Background bg = GameScreen.getBg1();
	protected int gameWidth=MainGame.SCREEN_WIDTH;
	protected Rectangle collisionBox;
    public static enum type{
    	watermelon,banana
    }
    
	public Projectile(int startX, int startY,int width,int height){//around the monkey's gun position
		x = startX;
		y = startY;
		this.width=width;
		this.height=height;
		visible = true;
		collisionBox=new Rectangle(startX,startY,width,height);
	}
	public Projectile(int startX, int startY,int width,int height,double speedX,double speedY){//added y component to the speed
		x = startX;
		y = startY;
		this.width=width;
		this.height=height;
		this.speedX=speedX;
		this.speedY=speedY;
		visible = true;
		collisionBox=new Rectangle(startX,startY,width,height);
	}
	
	public void update(){
		x += bg.getSpeedX()+speedX;
		y+=speedY;
		if (x+width <= 0||y+height<=0){
			visible = false;
		}	
		collisionBox.setLocation(x,y);
		
	}
	public boolean hasYComponent(){
		return speedY==0?false:true;
	}
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public double getSpeedX() {
		return speedX;
	}

	public boolean isVisible() {
		return visible;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public void setSpeedX(double speedX) {
		this.speedX = speedX;
	}

	public void setVisible(boolean visible) {
		this.visible = visible;
	}

	public Rectangle getCollisionBox() {
		return collisionBox;
	}

	public void setCollisionBox(Rectangle collisionBox) {
		this.collisionBox = collisionBox;
	}

	public int getWidth() {
		return width;
	}

	public int getHeight() {
		return height;
	}

	public void setWidth(int width) {
		this.width = width;
	}

	public void setHeight(int height) {
		this.height = height;
	}
}
