package com.example.monkeymayhem;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.Semaphore;

import com.badlogic.gdx.graphics.Texture;

import screens.GameScreen;

public abstract class Enemy {
	protected int health, speedX,speedY, centerX, centerY, width, height,score;
	protected boolean dead=false,dying=false;//process of death
	protected Monkey monkey;
	protected Rectangle collisionBox;
	protected int movementSpeed;
	protected boolean onScreen;
	protected int chanceOfShoot;
	protected float alpha=1.0f;//fade out animation
	protected Background bg = GameScreen.getBg1();
	protected Projectile killingProjectile;
	protected ArrayList<Projectile> projectiles=new ArrayList<Projectile>();
	protected CoolDown cooldown=new CoolDown(2000);;
	protected PowerUp powerUp;
	protected static Texture missileTexture;
	protected boolean isHurt;
	//Behavioral Methods
	public Enemy(int width,int height,Monkey monkey){
		health=1;
		this.width=width;
		this.height=height;
		this.monkey=monkey;
		chanceOfShoot=20;
		collisionBox=new Rectangle(centerX,centerY,width,height);
		if(centerX<MainGame.SCREEN_WIDTH){
			onScreen=true;
		}else{
			onScreen=false;
		}
	}
	public Enemy(int centerX, int centerY, int width, int height,Monkey monkey){
		this.health=1;
		this.centerX=centerX;
		this.centerY=centerY;
		this.width=width;
		this.height=height;
		this.monkey=monkey;
		chanceOfShoot=20;
		this.collisionBox=new Rectangle(centerX,centerY,width,height);
		if(centerX<MainGame.SCREEN_WIDTH){
			onScreen=true;
		}else{
			onScreen=false;
		}
	}
	public void update() {
		path();
		collisionBox.setLocation((int)centerX,(int)centerY);
		hurtHandler(20,1.5f);
	}
	protected void hurtHandler(float fps, float timeTilReset){
		if(isHurt){
			//assuming 30fps
			alpha -= 1/(fps*timeTilReset*2);
			//restore alpha back
			if(alpha<0.5){
				isHurt = false;
				alpha = 1;
			}
		}
		
	}
	protected void path() {
		
	}
	public void shoot(){
		
	}
	public void die() {
		dying=true;
		if(this.hasPowerUp()){//drop powerUp
			powerUp.setDropped(true);
		}
	}
	public void hit(Projectile p){
		if(!dying){
			//more than one life: does hit effect call
			isHurt = true;
			alpha = 1;
			
			health-=1;
			if(health==0){
				killingProjectile=p;
				die();
			}
		}
	}
	public void setHealth(int health){
		this.health = health;
	}
	public int getHealth() {
		return health;
	}

	public int getSpeedX() {
		return speedX;
	}

	public int getCenterX() {
		return centerX;
	}

	public int getCenterY() {
		return centerY;
	}

	public void setSpeedX(int speedX) {
		this.speedX = speedX;
	}

	public void setCenterX(int centerX) {
		this.centerX = centerX;
	}

	public void setCenterY(int centerY) {
		this.centerY = centerY;
	}
	/*
	public void setBg(Background bg) {
		this.bg = bg;
	}*/
	public Rectangle getCollisionBox() {
		return collisionBox;
	}
	public void setCollisionBox(Rectangle collisionBox) {
		this.collisionBox = collisionBox;
	}
	public int getMovementSpeed() {
		return movementSpeed;
	}
	public boolean isDead() {
		return dead;
	}
	public boolean isDying() {
		return dying;
	}
	public float getAlpha() {
		return alpha;
	}
	public void setAlpha(float alpha) {
		this.alpha = alpha;
	}
	public Projectile getKillingProjectile() {
		return killingProjectile;
	}
	public void setKillingProjectile(Projectile killingProjectile) {
		this.killingProjectile = killingProjectile;
	}
	public ArrayList<Projectile> getProjectiles() {
		return projectiles;
	}
	public boolean hasPowerUp(){
		return powerUp!=null;
	}
	public PowerUp getPowerUp() {
		return powerUp;
	}
	public int getScore() {
		return score;
	}
	public static Texture getMissileTexture() {
		return missileTexture;
	}
	public static void setMissileTexture(Texture missileTexture) {
		Enemy.missileTexture = missileTexture;
	}
	public int getWidth() {
		return width;
	}
	public int getHeight() {
		return height;
	}
}
