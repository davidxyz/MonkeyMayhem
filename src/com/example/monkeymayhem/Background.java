package com.example.monkeymayhem;


public class Background {
	private float bgX, bgY, speedX;
	private final int GAME_WIDTH=MainGame.SCREEN_WIDTH;
	private final int OFFSET=MainGame.BACKGROUND_OFFSET;
	public Background (int x, int y){
		   bgX = x;
		   bgY = y;
		   speedX = 0;
		}
	public void update() {
		bgX += speedX;

		if (bgX <= -GAME_WIDTH+OFFSET){
			bgX += (GAME_WIDTH+OFFSET)*2;
		}
	}
	public float getBgX() {
		return bgX;
	}
	public float getBgY() {
		return bgY;
	}
	public float getSpeedX() {
		return speedX;
	}
	public void setBgX(float bgX) {
		this.bgX = bgX;
	}
	public void setBgY(float bgY) {
		this.bgY = bgY;
	}
	public void setBgPosition(float bgX,float bgY){
		this.bgX = bgX;
		this.bgY = bgY;
	}
	public void setSpeedX(float speedX) {
		this.speedX = speedX;
	}
}