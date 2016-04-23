package com.example.monkeymayhem;

import screens.*;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.InputProcessor;
public class MainGame extends Game implements InputProcessor {
	public static int SCREEN_WIDTH = 700;//800
	public static int SCREEN_HEIGHT = 400;//480
	public static final int BACKGROUND_OFFSET=-2;
	public static final int MAX_DIST_ALLOWED=180;
	public static final int GROUND=25;
	public static final boolean PRODUCTION=true;//0=developing
	//screens
	public MenuScreen mainMenuScreen;
	public GameScreen gameScreen;
	public GameOverScreen gameOverScreen;

	@Override
	public void create() {  
		SCREEN_WIDTH=Gdx.graphics.getWidth();
		SCREEN_HEIGHT=Gdx.graphics.getHeight();
		mainMenuScreen = new MenuScreen(this);
        gameScreen = new GameScreen(this);
        gameOverScreen=new GameOverScreen(this);
        setScreen(mainMenuScreen);
   
        Gdx.input.setInputProcessor(this);
	}
	@Override
	public void dispose() {
		super.dispose();
	}

	@Override
	public void render() {        
		//level1 
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		// show the splash screen when the game is resized for the first time;
        // this approach avoids calling the screen's resize method repeatedly

	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}
	@Override
	public boolean keyDown(int keycode) {
		if(getScreen() instanceof GameScreen){
			switch (keycode) {
			case Keys.LEFT:
				gameScreen.monkey.moveLeft();
				break;
			case Keys.RIGHT:
				gameScreen.monkey.moveRight();
				break;
			case Keys.UP:
				gameScreen.monkey.moveUp();
				if(gameScreen.monkey.intersectsWithCollisionBox(gameScreen.portaltree.getCollisionBox())){
					gameScreen.levelUp();
				}
				break;
			case Keys.DOWN:
				gameScreen.monkey.moveDown();
				break;
			case Keys.X:
				gameScreen.monkey.shoot(Projectile.type.banana);
				break;
			case Keys.C:
				gameScreen.monkey.shoot(Projectile.type.watermelon);
				break;
			case Keys.NUM_1:
				gameScreen.monkey.absorbPowerUp(new PowerUpMissile(0,0));
				break;
			case Keys.NUM_2:
				gameScreen.monkey.absorbPowerUp(new PowerUpCannon(0,0));
				break;
			case Keys.NUM_3:
				PowerUpShield p=new PowerUpShield(0,0,gameScreen.monkey);
				gameScreen.powerUps.add(p);
				gameScreen.monkey.absorbPowerUp(p);
				break;
			}
		}
			return true;
	}
	@Override
	public boolean keyUp(int keycode) {
		if(getScreen() instanceof GameScreen){
			switch (keycode) {
			case Keys.LEFT:
				gameScreen.monkey.stopLeft();
				break;
			case Keys.RIGHT:
				gameScreen.monkey.stopRight();
				break;
			case Keys.UP:
				gameScreen.monkey.stopUp();
				break;
			case Keys.DOWN:
				gameScreen.monkey.stopDown();
				break;
			case Keys.X:
			case Keys.C:
				gameScreen.monkey.stopShoot();
				break;
			}
		}
		return true;
	}
	@Override
	public boolean mouseMoved(int arg0, int arg1) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean scrolled(int arg0) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchDown(int x, int y, int arg2, int arg3) {
		// TODO Auto-generated method stub
		if(getScreen() instanceof MenuScreen){
			if(mainMenuScreen.playButton.getX()<x&&x<mainMenuScreen.playButton.getWidth()+mainMenuScreen.playButton.getX()&&y>mainMenuScreen.playButton.getY()&&y<mainMenuScreen.playButton.getHeight()+mainMenuScreen.playButton.getY()){
				setScreen(gameScreen);
			}
		}
		if(getScreen() instanceof GameScreen){
			
		}
		return false;
	}
	@Override
	public boolean touchDragged(int arg0, int arg1, int arg2) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean touchUp(int arg0, int arg1, int arg2, int arg3) {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public boolean keyTyped(char arg0) {
		// TODO Auto-generated method stub
		return false;
	}


}
