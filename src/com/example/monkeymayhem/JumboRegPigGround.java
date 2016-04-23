package com.example.monkeymayhem;

import java.util.Random;

public class JumboRegPigGround extends PigGround{
	public JumboRegPigGround(int centerX, int centerY, int width, int height,
			Monkey monkey) {
		
		super(centerX, centerY,(int)(width*2), (int)(height*2), monkey);
		health++;
		score*=2;
		// TODO Auto-generated constructor stub
	}
	public JumboRegPigGround(int width, int height, Monkey monkey){
		super((int)(width*2), (int)(height*2), monkey);
		health++;
		score*=2;
	}
	@Override
	public void shoot(){
		if(cooldown.checkValidity()){
			Random rand = new Random();
			if(rand.nextInt(chanceOfShoot)==0){
				projectiles.add(new CornOnCobMissile(this.centerX,this.centerY+this.height-missileTexture.getHeight()/2,(int)(missileTexture.getWidth()*1.5),(int)(missileTexture.getHeight()*1.5),-6,3));//corn on the cob missile
			}
		}
	}
}
