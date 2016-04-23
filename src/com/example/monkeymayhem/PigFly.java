package com.example.monkeymayhem;

import java.util.Random;
public class PigFly extends Enemy {
	public PigFly(int width, int height, Monkey monkey) {
		super(width, height, monkey);
		Random rand = new Random();
		cooldown=new CoolDown(1000);
		score=2;
		movementSpeed=-2;
		if(rand.nextInt(15)==0){//1/15 chance of getting a power up and 1/3 chance of getting cannon
			int randNum=rand.nextInt(4);
			if(randNum==0){
				powerUp=new PowerUpCannon(centerX,centerY);
			}else if(randNum==1){
				powerUp=new PowerUpMissile(centerX,centerY);
			}else if(randNum==2){
				powerUp=new PowerUpLife(centerX,centerY);
			}else{
				powerUp=new PowerUpShield(centerX,centerY);
			}
		}
	}
	public PigFly(int centerX, int centerY, int width, int height,Monkey monkey) {
		super(centerX,centerY, width,height,monkey);
		Random rand = new Random();
		cooldown=new CoolDown(1000);
		score=2;
		movementSpeed=-2;
		if(rand.nextInt(15)==0){
			int randNum=rand.nextInt(4);
			if(randNum==0){
				powerUp=new PowerUpCannon(centerX+width/2,centerY+height/2);
			}else if(randNum==1){
				powerUp=new PowerUpMissile(centerX+width/2,centerY+height/2);
			}else if(randNum==2){
				powerUp=new PowerUpLife(centerX+width/2,centerY+height/2);
			}else{
				powerUp=new PowerUpShield(centerX+width/2,centerY+height/2);
			}
		}
	}

	@Override
	public void update(){
		super.update();
		//shooting algorithm
		if(onScreen&&!dying){
			shoot();
		}
		//constraints
		if(centerY+height>=MainGame.SCREEN_HEIGHT){
			centerY=MainGame.SCREEN_HEIGHT-height;
		}
		if(centerY<=MainGame.GROUND+20&!dying){
			centerY=MainGame.GROUND+20;
		}
		if(dying&&centerY<=MainGame.GROUND){//dying drops to ground and stays there
			centerY=MainGame.GROUND;
			if(projectiles.isEmpty()){//enemy can't truly die unless its projectiles are offscreen
				dead=true;
			}
		}
		if(alpha<=0){
			alpha=0;
		}
		if(hasPowerUp()&&!powerUp.isDropped()){
			powerUp.setX(centerX);
			powerUp.setY(centerY);
		}
		collisionBox.setLocation((int)centerX,(int)centerY);
	}
	@Override
	protected void path(){
		if(this.centerX<monkey.getCenterX()){//if pig is ahead of monkey go backwards
			//rand.nextInt((max - min) + 1) + min;
			if(this.centerY>monkey.getCenterY()){
				speedY=-1;
			}else if(this.centerY<monkey.getCenterY()){
				speedY=1;
			}else{
				speedY=0;
			}
			movementSpeed=7;
		}else if(centerX>=MainGame.SCREEN_WIDTH-width){//if pig hits screen go towards monkey
			movementSpeed=-2;
			speedY=0;
		}
		if(!onScreen){//if pig isn't on the screen, pig is stationary
			movementSpeed=0;
			if(centerX<=MainGame.SCREEN_WIDTH){
				onScreen=true;
			}
		}
		if(dying){//if pig is dying, fall from sky and fade out
			movementSpeed=0;
			speedY=-3;
		}
		speedX=(int)bg.getSpeedX()+movementSpeed;
		centerX += speedX;
		centerY+=speedY;
	}
	@Override
	public void shoot(){
		if(cooldown.checkValidity()){
			Random rand = new Random();
			if(rand.nextInt(chanceOfShoot)==0){
				projectiles.add(new CornOnCobMissile(this.centerX,this.centerY+this.height-missileTexture.getHeight()/2,missileTexture.getWidth(),missileTexture.getHeight()));//corn on the cob missile
			}
		}
	}
}
