package com.example.monkeymayhem;

import java.util.Random;

public class PigGroundPiggyBack extends PigGround{
	public PigGroundPiggyBack(int width, int height, Monkey monkey) {
		super(width, height, monkey);
		score=5;
		health++;
		movementSpeed=5;
	}
	public PigGroundPiggyBack(int centerX, int centerY, int width, int height,Monkey monkey) {
		super(centerX,centerY, width,height,monkey);
		score=5;
		movementSpeed=5;
		health++;
	}
	@Override
	public void shoot(){//ai built into shoot
		if(cooldown.checkValidity()){
			Random rand = new Random();
			if(rand.nextInt(chanceOfShoot)==0){
					projectiles.add(new CornOnCobMissile(this.centerX,this.centerY+this.height-missileTexture.getHeight()/2,missileTexture.getWidth(),missileTexture.getHeight(),-6,3));//corn on the cob missile
					projectiles.add(new CornOnCobMissile(this.centerX,this.centerY+this.height/5,missileTexture.getWidth(),missileTexture.getHeight()));//corn on the cob missile
			}
		}
	}
}
