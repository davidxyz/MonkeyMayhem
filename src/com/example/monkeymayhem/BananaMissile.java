package com.example.monkeymayhem;

public class BananaMissile extends Projectile{
	private final int MAX_DIST_TILL_STABLE=200;
	private int startX;
	private double speedY;
	public static enum type{
		up,up1,up2,up3,down,down1,down2,down3
	}
	public BananaMissile(int startX, int startY,int width,int height,double initSpeedY){
		super(startX,startY,width,height);
		setSpeedX(6);
		speedY=initSpeedY;
		this.startX=startX;
	}
	@Override
	public void update(){
		x += speedX;
		y +=speedY;
		
		if (x >  gameWidth){
			visible = false;
		}
		
		if(x-startX>=MAX_DIST_TILL_STABLE){
			speedY=0;
		}
		collisionBox.setLocation(x,y);
	}
}
