package com.example.monkeymayhem;

import java.awt.Rectangle;
import java.util.ArrayList;

import screens.GameScreen;

import com.badlogic.gdx.graphics.Texture;

public class Monkey {
	// Constants are Here
	private final int FLYSPEED=3;
	private final int MOVESPEED = 3;
	private final int GROUND = MainGame.GROUND;
	private final int MAX_DIST_ALLOWED=MainGame.MAX_DIST_ALLOWED;
    private static int gameHeight= MainGame.SCREEN_HEIGHT;
    private static int gameWidth= MainGame.SCREEN_WIDTH;
    
    private int centerX = 0;
    private int centerY = GROUND;
    private int deltaX=0,deltaY=0;//how much x and y position moves
    private int missilePower=1,cannonPower=1,specialPower=1;
    private boolean flying = false;
    private boolean movingLeft = false;
    private boolean movingRight = false;
	private boolean movingUp=false;
	private boolean movingDown=false;
    private boolean ducked = false;
    private boolean shooting=false;
    private boolean dying=false,dead=false;
    private int monkeyWidth,monkeyHeight;
    private int backgroundDistanceCovered=0,maxBackgroundDistanceCovered;
    private CoolDown cooldown;
    private boolean shieldOn=false;
//initalize the background variables
    private static Background bg1 = GameScreen.getBg1();                 
    private static Background bg2 = GameScreen.getBg2();
        //initialize speed variables
    private int speedX = 0;
    private int speedY = 0;
    private int life=5;
    
    private ArrayList<Projectile> projectiles = new ArrayList<Projectile>();
    private Texture bananaMissileTexture,watermelonCannonTexture;
    private ArrayList<Rectangle> collisionBoxes=new ArrayList<Rectangle>();
    public Monkey(){
    	
    }
    public Monkey(int width,int height){
    	monkeyWidth=width;
    	monkeyHeight=height;
    	cooldown=new CoolDown(500);
    	
    	int endOfTopRect=monkeyHeight-(int)(monkeyHeight*0.60);
    	collisionBoxes.add(new Rectangle(centerX+(int)(monkeyWidth*0.3),centerY,monkeyWidth-(int)(monkeyWidth*0.6),endOfTopRect));//bottom rectangle
    	collisionBoxes.add(new Rectangle(centerX,centerY+endOfTopRect,monkeyWidth,monkeyHeight-endOfTopRect));//top rectangle
    }
    public void update() {
    	//records the change of the x and y position
    	deltaX=centerX;
    	deltaY=centerY;
    	
        // Moves Character or Scrolls Background accordingly.
        if (speedX < 0) {
            centerX += speedX;
        }
        //keep background stationary if our character moves to the left or stays still
       
        if (speedX == 0 || speedX < 0||backgroundDistanceCovered>=maxBackgroundDistanceCovered) {
            bg1.setSpeedX(0);
            bg2.setSpeedX(0);
        }
        //only move the monkey if the monkey is behind the max distance
        if ((centerX <= MAX_DIST_ALLOWED && speedX > 0) || backgroundDistanceCovered>=maxBackgroundDistanceCovered) {
            centerX += speedX;
        }
        //scroll the background if the monkey is farther than the max distance and has positive speed
        //background will move at 1/5 the character's speed in order for parallax scrolling .

        if (speedX > 0 && centerX >= MAX_DIST_ALLOWED&&backgroundDistanceCovered<maxBackgroundDistanceCovered){
        	   bg1.setSpeedX(-MOVESPEED);
        	   bg2.setSpeedX(-MOVESPEED);
        	}
        // Updates Y Position
        centerY += speedY;
        //handles flying
        if (centerY <= GROUND) {
        	centerY = GROUND;
        	speedY = 0;
        	flying = false;
        }
        //can't go higher than the ceiling
        if (centerY+monkeyHeight >=gameHeight) {
        	centerY = gameHeight-monkeyHeight;
        }
        // Prevents going beyond X coordinate of 0
        if (centerX<= 0) {
            centerX = 0;
        }
        //when monkey beats level
        if (centerX+monkeyWidth>= gameWidth) {
            centerX = gameWidth-monkeyWidth;
        }
        
        deltaX-=centerX;
        deltaY-=centerY;
        backgroundDistanceCovered+=speedX;
        //collisionBoxes move with character
        for(Rectangle r: collisionBoxes){
        	r.setLocation((int)r.getX()-deltaX,(int)r.getY()-deltaY);
        }
    }
    //after beating a level
    public void restart(){
    	deltaX=centerX;
    	deltaY=centerY;
    	
    	centerX=0;
    	centerY=GROUND;
    	
    	deltaX-=centerX;
        deltaY-=centerY;
        flying = false;
        movingLeft = false;
        movingRight = false;
        movingUp=false;
        movingDown=false;
        ducked = false;
        shooting=false;
        backgroundDistanceCovered=0;
        for(Rectangle r: collisionBoxes){
        	r.setLocation((int)r.getX()-deltaX,(int)r.getY()-deltaY);
        }
    }
    public void hit(){
    	life--;
    	if(life==0){
    		dying=true;
    	}
    }
    public boolean intersectsWithCollisionBox(Rectangle otherCollisionBox,boolean enemyProjectile){
    	if(enemyProjectile&&shieldOn){
    		return false;
    	}
    	for(Rectangle collisionBox:collisionBoxes){
    		if(collisionBox.intersects(otherCollisionBox)){
    			return true;
    		}
    	}
    	return false;
    }
    public boolean intersectsWithCollisionBox(Rectangle otherCollisionBox){
    	for(Rectangle collisionBox:collisionBoxes){
    		if(collisionBox.intersects(otherCollisionBox)){
    			return true;
    		}
    	}
    	return false;
    }
    public void moveRight() {
        if (ducked == false) {
            speedX = MOVESPEED;
            setMovingRight(true);
        }
    }

    public void moveLeft() {
        if (ducked == false) {
            speedX = -MOVESPEED;
            setMovingLeft(true);
        }
    }
    public void moveUp() {
        if (ducked == false) {
            speedY = FLYSPEED;
            setFlying(true);
            setMovingUp(true);
        }
    }
    public void moveDown() {
        if (ducked == false && flying==true) {
            speedY = -FLYSPEED;
            setMovingDown(true);
        }
    }

    public void stopRight() {
        setMovingRight(false);
        stop();
    }

    public void stopLeft() {
        setMovingLeft(false);
        stop();
    }
    public void stopUp() {
        setMovingUp(false);
        stop();
    }

    public void stopDown() {
        setMovingDown(false);
        stop();
    }
    public void stopShoot(){
    	shooting=false;
    }

    private void stop() {
        if (isMovingRight() == false && isMovingLeft() == false) {
            speedX = 0;
        }

        if (isMovingRight() == false && isMovingLeft() == true) {
            moveLeft();
        }

        if (isMovingRight() == true && isMovingLeft() == false) {
            moveRight();
        }
        if (isMovingDown() == false && isMovingUp() == false) {
            speedY = 0;
        }

        if (isMovingDown() == false && isMovingUp() == true) {
            moveUp();
        }

        if (isMovingDown() == true && isMovingUp() == false) {
            moveDown();
        }

    }
    public void absorbPowerUp(PowerUp powerup){
    	if(powerup instanceof PowerUpCannon){
    		cannonPower++;
    		if(cannonPower>=3){//max amount of cannons
    			cannonPower=3;
    		}
    	}else if(powerup instanceof PowerUpMissile){
    		missilePower++;
    		if(missilePower>=4){//max amount of missiles
    			missilePower=4;
    		}
    	}else if(powerup instanceof PowerUpSpecial){
    		specialPower++;
    	}else if(powerup instanceof PowerUpLife){
    		life++;
    		if(life>=8){//max amount of hearts
    			life=8;
    		}
    	}else{
    		shieldOn=true;
    		((PowerUpShield)powerup).activate(this);
    	}
    }
    public void shoot(Projectile.type type) {
    	if(type==Projectile.type.banana){
    		if(cooldown.checkValidity()){
    			switch(missilePower){
    			case 1:
    				//x,y,width,height,initial_speedY;
    				projectiles.add(new BananaMissile(centerX + monkeyWidth+5, centerY+(int)(monkeyHeight*0.70),bananaMissileTexture.getWidth(),bananaMissileTexture.getHeight(),2));
    				projectiles.add(new BananaMissile(centerX + monkeyWidth+5, centerY+(int)(monkeyHeight*0.70),bananaMissileTexture.getWidth(),bananaMissileTexture.getHeight(),-2));
    				break;
    			case 2:
    				projectiles.add(new BananaMissile(centerX + monkeyWidth+5, centerY+(int)(monkeyHeight*0.70),bananaMissileTexture.getWidth(),bananaMissileTexture.getHeight(),2));
    				projectiles.add(new BananaMissile(centerX + monkeyWidth+5, centerY+(int)(monkeyHeight*0.70),bananaMissileTexture.getWidth(),bananaMissileTexture.getHeight(),3));
    				projectiles.add(new BananaMissile(centerX + monkeyWidth+5, centerY+(int)(monkeyHeight*0.70),bananaMissileTexture.getWidth(),bananaMissileTexture.getHeight(),-2));
    				projectiles.add(new BananaMissile(centerX + monkeyWidth+5, centerY+(int)(monkeyHeight*0.70),bananaMissileTexture.getWidth(),bananaMissileTexture.getHeight(),-3));
    				break;
    			case 3:
    				projectiles.add(new BananaMissile(centerX + monkeyWidth+5, centerY+(int)(monkeyHeight*0.70),bananaMissileTexture.getWidth(),bananaMissileTexture.getHeight(),2));
    				projectiles.add(new BananaMissile(centerX + monkeyWidth+5, centerY+(int)(monkeyHeight*0.70),bananaMissileTexture.getWidth(),bananaMissileTexture.getHeight(),3));
    				projectiles.add(new BananaMissile(centerX + monkeyWidth+5, centerY+(int)(monkeyHeight*0.70),bananaMissileTexture.getWidth(),bananaMissileTexture.getHeight(),4));
    				projectiles.add(new BananaMissile(centerX + monkeyWidth+5, centerY+(int)(monkeyHeight*0.70),bananaMissileTexture.getWidth(),bananaMissileTexture.getHeight(),-2));
    				projectiles.add(new BananaMissile(centerX + monkeyWidth+5, centerY+(int)(monkeyHeight*0.70),bananaMissileTexture.getWidth(),bananaMissileTexture.getHeight(),-3));
    				projectiles.add(new BananaMissile(centerX + monkeyWidth+5, centerY+(int)(monkeyHeight*0.70),bananaMissileTexture.getWidth(),bananaMissileTexture.getHeight(),-4));
    			case 4:
    				projectiles.add(new BananaMissile(centerX + monkeyWidth+5, centerY+(int)(monkeyHeight*0.70),bananaMissileTexture.getWidth(),bananaMissileTexture.getHeight(),2));
    				projectiles.add(new BananaMissile(centerX + monkeyWidth+5, centerY+(int)(monkeyHeight*0.70),bananaMissileTexture.getWidth(),bananaMissileTexture.getHeight(),3));
    				projectiles.add(new BananaMissile(centerX + monkeyWidth+5, centerY+(int)(monkeyHeight*0.70),bananaMissileTexture.getWidth(),bananaMissileTexture.getHeight(),4));
    				projectiles.add(new BananaMissile(centerX + monkeyWidth+5, centerY+(int)(monkeyHeight*0.70),bananaMissileTexture.getWidth(),bananaMissileTexture.getHeight(),5));
    				projectiles.add(new BananaMissile(centerX + monkeyWidth+5, centerY+(int)(monkeyHeight*0.70),bananaMissileTexture.getWidth(),bananaMissileTexture.getHeight(),-2));
    				projectiles.add(new BananaMissile(centerX + monkeyWidth+5, centerY+(int)(monkeyHeight*0.70),bananaMissileTexture.getWidth(),bananaMissileTexture.getHeight(),-3));
    				projectiles.add(new BananaMissile(centerX + monkeyWidth+5, centerY+(int)(monkeyHeight*0.70),bananaMissileTexture.getWidth(),bananaMissileTexture.getHeight(),-4));
    				projectiles.add(new BananaMissile(centerX + monkeyWidth+5, centerY+(int)(monkeyHeight*0.70),bananaMissileTexture.getWidth(),bananaMissileTexture.getHeight(),-5));
    			}
    		}
    	}else if(type==Projectile.type.watermelon){
    		//middle of monkey
    		if(cooldown.checkValidity()){
    			switch(cannonPower){
    			case 1:
    				projectiles.add(new WatermelonCanon(centerX + monkeyWidth+5, centerY+(int)(monkeyHeight*0.50),watermelonCannonTexture.getWidth(),watermelonCannonTexture.getHeight()));
    				break;
    			case 2:
    				//bigger cannon
    				projectiles.add(new WatermelonCanon(centerX + monkeyWidth+5, centerY+(int)(monkeyHeight*0.50),(int)(watermelonCannonTexture.getWidth()*1.2),(int)(watermelonCannonTexture.getHeight()*1.2)));
    				break;
    			case 3:
    				//two big cannons
    			{
    				projectiles.add(new WatermelonCanon(centerX + monkeyWidth+5, (centerY+(int)(monkeyHeight*0.50))+(int)(watermelonCannonTexture.getHeight()*1.2)/2,(int)(watermelonCannonTexture.getWidth()*1.2),(int)(watermelonCannonTexture.getHeight()*1.2)));
    				projectiles.add(new WatermelonCanon(centerX + monkeyWidth+5, centerY+(int)(monkeyHeight*0.50)-(int)(watermelonCannonTexture.getHeight()*1.2)/2,(int)(watermelonCannonTexture.getWidth()*1.2),(int)(watermelonCannonTexture.getHeight()*1.2)));
    			}
    				break;
    			case 4://3 big cannnons
    			{
    				projectiles.add(new WatermelonCanon(centerX + monkeyWidth+5, (centerY+(int)(monkeyHeight*0.50))+(int)(watermelonCannonTexture.getHeight()*1.2),(int)(watermelonCannonTexture.getWidth()*1.2),(int)(watermelonCannonTexture.getHeight()*1.2)));
    				projectiles.add(new WatermelonCanon(centerX + monkeyWidth+5, centerY+(int)(monkeyHeight*0.50),(int)(watermelonCannonTexture.getWidth()*1.2),(int)(watermelonCannonTexture.getHeight()*1.2)));
    				projectiles.add(new WatermelonCanon(centerX + monkeyWidth+5, (centerY+(int)(monkeyHeight*0.50))-(int)(watermelonCannonTexture.getHeight()*1.2),(int)(watermelonCannonTexture.getWidth()*1.2),(int)(watermelonCannonTexture.getHeight()*1.2)));
    			}
    				break;
    			}
    		}else{//special Power bananarang attacks 3 monsters
    			
    		}
    	}
    	shooting=true;
	}
    public ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}

    public int getCenterX() {
        return centerX;
    }

    public int getCenterY() {
        return centerY;
    }

    public boolean isFlying() {
        return flying;
    }
    public boolean isWalking() {
        return !flying;
    }
    public boolean isShooting(){
    	return shooting;
    }

    public double getSpeedX() {
        return speedX;
    }

    public double getSpeedY() {
        return speedY;
    }

    public void setCenterX(int centerX) {
        this.centerX = centerX;
    }

    public void setCenterY(int centerY) {
        this.centerY = centerY;
    }

    public void setFlying(boolean flying) {
        this.flying = flying;
    }

    public void setSpeedX(int speedX) {
        this.speedX = speedX;
    }

    public void setSpeedY(int speedY) {
        this.speedY = speedY;
    }

    public boolean isDucked() {
        return ducked;
    }

    public void setDucked(boolean ducked) {
        this.ducked = ducked;
    }

    public boolean isMovingRight() {
        return movingRight;
    }

    public void setMovingRight(boolean movingRight) {
        this.movingRight = movingRight;
    }
 

    public boolean isMovingLeft() {
        return movingLeft;
    }

    public void setMovingLeft(boolean movingLeft) {
        this.movingLeft = movingLeft;
    }
    public boolean isStandingStill(){
    	return !isMovingLeft()&&!isMovingRight()&&!isMovingUp()&&!isMovingDown();
    }

    public boolean isMovingUp() {
        return movingUp;
    }

    public void setMovingUp(boolean movingUp) {
        this.movingUp = movingUp;
    }

    public boolean isMovingDown() {
        return movingDown;
    }

    public void setMovingDown(boolean movingDown) {
        this.movingDown = movingDown;
    }
    public void setShooting(boolean shooting){
    	this.shooting=shooting;
    }
    public ArrayList<Rectangle> getCollisionRectangles(){
    	return collisionBoxes;
    }
    public int getWidth(){
    	return monkeyWidth;
    }
    public int getHeight(){
    	return monkeyHeight;
    }
	public Texture getBananaMissileTexture() {
		return bananaMissileTexture;
	}
	public Texture getWatermelonCannonTexture() {
		return watermelonCannonTexture;
	}
	public void setBananaMissileTexture(Texture bananaMissileTexture) {
		this.bananaMissileTexture = bananaMissileTexture;
	}
	public void setWatermelonCannonTexture(Texture watermelonCannonTexture) {
		this.watermelonCannonTexture = watermelonCannonTexture;
	}
	public int getBackgroundDistanceCovered() {
		return backgroundDistanceCovered;
	}
	public int getMaxBackgroundDistanceCovered() {
		return maxBackgroundDistanceCovered;
	}
	public void setMaxBackgroundDistanceCovered(int maxBackgroundDistanceCovered) {
		this.maxBackgroundDistanceCovered = maxBackgroundDistanceCovered;
	}
	public int getLife() {
		return life;
	}
	public int getMonkeyWidth() {
		return monkeyWidth;
	}
	public int getMonkeyHeight() {
		return monkeyHeight;
	}
	public void setShieldOn(boolean shieldOn) {
		this.shieldOn = shieldOn;
	}
	public int getDeltaX() {
		return deltaX;
	}
	public int getDeltaY() {
		return deltaY;
	}
	public int getMissilePower() {
		return missilePower;
	}
	public int getCannonPower() {
		return cannonPower;
	}
	public int getSpecialPower() {
		return specialPower;
	}

}
