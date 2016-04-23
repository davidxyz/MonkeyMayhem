package com.example.monkeymayhem;

public class CornOnCobMissile extends Projectile{
	public CornOnCobMissile(int startX, int startY,int width,int height){
		super(startX,startY,width,height);
		setSpeedX(-6);
	}
	public CornOnCobMissile(int startX, int startY,int width,int height,double speedX,double speedY){
		super(startX,startY,width,height,speedX,speedY);
		
	}
	@Override
	public void update(){
		super.update();
	}
}
