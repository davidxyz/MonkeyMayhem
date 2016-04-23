package screens;

import java.awt.Rectangle;
import java.util.ArrayList;
import java.util.Random;

import managers.MusicManager;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.Texture.TextureFilter;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.example.monkeymayhem.*;

public class GameScreen implements Screen{
	private static int SCREEN_WIDTH = MainGame.SCREEN_WIDTH;
	private static int SCREEN_HEIGHT = MainGame.SCREEN_HEIGHT;
	private static final int BACKGROUND_OFFSET=MainGame.BACKGROUND_OFFSET;
	private static final int MAX_DIST_ALLOWED=MainGame.MAX_DIST_ALLOWED;
	private SpriteBatch batch;
	private Texture standStillTexture,shootTexture,backgroundTexture,deadRegPigFlyTexture,
	deadRegPigFly2Texture,bananaMissileTexture,watermelonCannonTexture,
	portalTreeTexture,movePadTexture,shootMissileTexture,shootCannonTexture,powerUpMissileTexture,
	powerUpLifeTexture,powerUpCannonTexture,powerUpSpecialTexture,
	powerUpShieldTexture,shieldTexture,lifeBoardTexture,lifeTexture;
	
	private TextureAtlas monkeyFlyTextureAtlas,monkeyWalkTextureAtlas,regPigFlyFlyAtlas,regPigGroundAtlas,
	cornProjectileAtlas,piggyBackGroundAtlas,angryBoarFlyAtlas,jumboRegPigFlyAtlas,jumboRegPigGroundAtlas,jumboPiggyBackGround,
	jumboPiggyBackGroundAtlas,jumboBoarFlyAtlas;
	
	private Animation monkeyWalkAnimation,monkeyFlyAnimation,regPigFlyFlyForwardAnimation,
	regPigFlyFlyStillAnimation,regPigFlyFlyBackwardAnimation,regPigGroundAnimation,cornProjectileAnimation,
	piggyBackGroundAnimation,angryBoarFlyFlyForwardAnimation,angryBoarFlyFlyStillAnimation,
	angryBoarFlyFlyBackwardAnimation, jumboRegPigFlyForwardAnimation,
	jumboRegPigFlyStillAnimation,jumboRegPigFlyBackwardAnimation,jumboRegPigGroundAnimation,
	jumboPiggyBackGroundAnimation,jumboBoarFlyForwardAnimation,jumboBoarFlyStillAnimation,
	jumboBoarFlyBackwardAnimation;
	
	private static Background bg1,bg2;
	private float elapsedTime = 0;
	private ArrayList<Enemy> enemies;
	private int level=1;
	private int score;
	public ArrayList<PowerUp> powerUps;
	private BitmapFont font;
	public MusicManager musicManager;
	//game
	private MainGame game;
	public Monkey monkey;
	public PortalTree portaltree;
	//testing
	ShapeRenderer shapeRenderer;
	public Sound s;
	public GameScreen(MainGame game){
        this.game = game;
		batch = new SpriteBatch();
		//texture of standstill monkey
		deadRegPigFlyTexture=new Texture(Gdx.files.internal("data/reg_pig_fly_dead.png"));
		deadRegPigFly2Texture=new Texture(Gdx.files.internal("data/reg_pig_fly_dead_2.png"));
		standStillTexture=new Texture(Gdx.files.internal("data/monkey_touchdown.png"));
		shootTexture=new Texture(Gdx.files.internal("data/monkey_flying_shoot.png"));
		backgroundTexture=new Texture(Gdx.files.internal("data/background1.png"));
		bananaMissileTexture=new Texture(Gdx.files.internal("data/bananamissile.png"));
		watermelonCannonTexture=new Texture(Gdx.files.internal("data/watermelonCannon.png"));
		portalTreeTexture=new Texture(Gdx.files.internal("data/portaltree.png"));
		movePadTexture=new Texture(Gdx.files.internal("data/movePad.png"));
		shootMissileTexture=new Texture(Gdx.files.internal("data/shootMissile.png"));
		shootCannonTexture=new Texture(Gdx.files.internal("data/shootCannon.png"));
		powerUpMissileTexture=new Texture(Gdx.files.internal("data/powerUpMissile1.png"));
		powerUpCannonTexture=new Texture(Gdx.files.internal("data/powerUpCannon1.png"));
		powerUpShieldTexture=new Texture(Gdx.files.internal("data/powerUpShield.png"));
		shieldTexture=new Texture(Gdx.files.internal("data/shield.png"));
		lifeBoardTexture=new Texture(Gdx.files.internal("data/lifeBoard.png"));
		lifeTexture=new Texture(Gdx.files.internal("data/life.png"));
		powerUpLifeTexture=new Texture(Gdx.files.internal("data/powerUpLife.png"));
		//powerUpSpecialTexture=new Texture(Gdx.files.internal("data/powerUpSpecial1.png"));
		//atlas of walking animation
		monkeyWalkTextureAtlas=new TextureAtlas(Gdx.files.internal("data/monkey_walk.atlas"));
		monkeyFlyTextureAtlas=new TextureAtlas(Gdx.files.internal("data/monkey_fly.atlas"));
		regPigFlyFlyAtlas=new TextureAtlas(Gdx.files.internal("data/reg_pig_fly.atlas"));
		regPigGroundAtlas=new TextureAtlas(Gdx.files.internal("data/reg_pig_ground.atlas"));
		cornProjectileAtlas=new TextureAtlas(Gdx.files.internal("data/cornProjectile.atlas"));
		piggyBackGroundAtlas = new TextureAtlas(Gdx.files.internal("data/piggy_back_ground.atlas"));
		angryBoarFlyAtlas=new TextureAtlas(Gdx.files.internal("data/angry_boar_pig_fly.atlas"));
		//jumbo
		jumboRegPigFlyAtlas=new TextureAtlas(Gdx.files.internal("data/jumbo_pig_fly.atlas"));
		jumboRegPigGroundAtlas=new TextureAtlas(Gdx.files.internal("data/jumbo_pig_ground.atlas"));
		jumboPiggyBackGroundAtlas = new TextureAtlas(Gdx.files.internal("data/jumbo_piggy_back_ground.atlas"));
		jumboBoarFlyAtlas=new TextureAtlas(Gdx.files.internal("data/jumbo_boar_pig_fly.atlas"));
		
		monkeyFlyAnimation = new Animation(0.25f, monkeyFlyTextureAtlas.getRegions());
		monkeyWalkAnimation = new Animation(0.1f, monkeyWalkTextureAtlas.getRegions());
		regPigGroundAnimation = new Animation(0.5f,regPigGroundAtlas.getRegions());
		piggyBackGroundAnimation = new Animation(0.5f,piggyBackGroundAtlas.getRegions());
		cornProjectileAnimation=new Animation(0.2f, cornProjectileAtlas.getRegions());
		regPigFlyFlyForwardAnimation= new Animation(0.3f, regPigFlyFlyAtlas.findRegion("reg_pig_fly_forward",1),
				regPigFlyFlyAtlas.findRegion("reg_pig_fly_forward",2),regPigFlyFlyAtlas.findRegion("reg_pig_fly_forward",3));
		regPigFlyFlyStillAnimation= new Animation(0.3f, (regPigFlyFlyAtlas.findRegion("reg_pig_fly_standstill",1)),(regPigFlyFlyAtlas.findRegion("reg_pig_fly_standstill",2)));
		regPigFlyFlyBackwardAnimation= new Animation(0.3f, regPigFlyFlyAtlas.findRegion("reg_pig_fly_backward",1),
				regPigFlyFlyAtlas.findRegion("reg_pig_fly_backward",2),regPigFlyFlyAtlas.findRegion("reg_pig_fly_backward",3));
		angryBoarFlyFlyForwardAnimation= new Animation(0.3f, angryBoarFlyAtlas.findRegion("angry_boar_pig_fly_forward",1),
				angryBoarFlyAtlas.findRegion("angry_boar_pig_fly_forward",2),angryBoarFlyAtlas.findRegion("angry_boar_pig_fly_forward",3));
		angryBoarFlyFlyStillAnimation= new Animation(0.3f, (angryBoarFlyAtlas.findRegion("angry_boar_pig_fly_standstill",1)),(angryBoarFlyAtlas.findRegion("angry_boar_pig_fly_standstill",2)));
		angryBoarFlyFlyBackwardAnimation= new Animation(0.3f, angryBoarFlyAtlas.findRegion("angry_boar_pig_fly_backward",1),
				angryBoarFlyAtlas.findRegion("angry_boar_pig_fly_backward",2),angryBoarFlyAtlas.findRegion("angry_boar_pig_fly_backward",3));
		//jumbo
		jumboRegPigFlyForwardAnimation= new Animation(0.3f, jumboRegPigFlyAtlas.findRegion("jumbo_pig_fly_forward0",-1),
				jumboRegPigFlyAtlas.findRegion("jumbo_pig_fly_forward1",-1),jumboRegPigFlyAtlas.findRegion("jumbo_pig_fly_forward2",-1));
		jumboRegPigFlyStillAnimation= new Animation(0.3f, (jumboRegPigFlyAtlas.findRegion("pig_fly_standstill",-1)),(jumboRegPigFlyAtlas.findRegion("pig_fly_standstill1",-1)));
		jumboRegPigFlyBackwardAnimation= new Animation(0.3f, jumboRegPigFlyAtlas.findRegion("jumbo_pig_fly_backward",-1),
				jumboRegPigFlyAtlas.findRegion("jumbo_pig_fly_backward0",-1),jumboRegPigFlyAtlas.findRegion("jumbo_pig_fly_backward2",-1));
		jumboBoarFlyForwardAnimation= new Animation(0.3f, jumboBoarFlyAtlas.findRegion("jumbo_boar_pig_fly_forward",1),
				jumboBoarFlyAtlas.findRegion("jumbo_boar_pig_fly_forward",2),jumboBoarFlyAtlas.findRegion("jumbo_boar_pig_fly_forward",3));
		jumboBoarFlyStillAnimation= new Animation(0.3f, (jumboBoarFlyAtlas.findRegion("jumbo_boar_pig_fly_standstill",1)),(jumboBoarFlyAtlas.findRegion("jumbo_boar_pig_fly_standstill",2)));
		jumboBoarFlyBackwardAnimation= new Animation(0.3f, jumboBoarFlyAtlas.findRegion("jumbo_boar_pig_fly_backward",1),
				jumboBoarFlyAtlas.findRegion("jumbo_boar_pig_fly_backward",2),jumboBoarFlyAtlas.findRegion("jumbo_boar_pig_fly_backward",3));
		
		
		
		//the -2 is a constant that makes the background looks nice
		bg1=new Background(0+BACKGROUND_OFFSET,0);
		bg2=new Background(SCREEN_WIDTH+BACKGROUND_OFFSET,0);
		monkey=new Monkey(standStillTexture.getWidth(),standStillTexture.getHeight());
		monkey.setBananaMissileTexture(bananaMissileTexture);
		monkey.setWatermelonCannonTexture(watermelonCannonTexture);
		PowerUp.setHeight(powerUpMissileTexture.getHeight());
		PowerUp.setWidth(powerUpMissileTexture.getWidth());
		Enemy.setMissileTexture(cornProjectileAtlas.getTextures().first());
		enemies=new ArrayList<Enemy>();
		powerUps=new ArrayList<PowerUp>();
		shapeRenderer=new ShapeRenderer();
		font=new BitmapFont(Gdx.files.internal("data/score.fnt"),Gdx.files.internal("data/score.png"),false);
		
		levelWriter();
	}
	private void levelWriter(){
		int lengthOfLevel;
		switch(level){
		case 1:
		{
			lengthOfLevel=SCREEN_WIDTH*4;
			portaltree=new PortalTree(portalTreeTexture.getWidth(),portalTreeTexture.getHeight(),monkey);
			monkey.setMaxBackgroundDistanceCovered(MAX_DIST_ALLOWED+lengthOfLevel);
			int width=regPigFlyFlyAtlas.findRegion("reg_pig_fly_standstill").getRegionWidth();
			int height=regPigFlyFlyAtlas.findRegion("reg_pig_fly_standstill").getRegionHeight();
			int distanceCoveredByEnemy=0;//in order to arrange the enemies favorably in the map keep track of pigs position
			//rand.nextInt((max - min) + 1) + min;
			Random rand=new Random();
			distanceCoveredByEnemy+=MAX_DIST_ALLOWED*2;
			while(distanceCoveredByEnemy<=lengthOfLevel){
				enemies.add(new PigFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
				enemies.add(new PigFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
				enemies.add(new PigFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
				enemies.add(new PigFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
				enemies.add(new PigFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
				enemies.add(new PigFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
				distanceCoveredByEnemy+=lengthOfLevel/5;
			}
		}
		break;
		case 2:
		{
			lengthOfLevel=SCREEN_WIDTH*4;
			portaltree=new PortalTree(portalTreeTexture.getWidth(),portalTreeTexture.getHeight(),monkey);
			monkey.setMaxBackgroundDistanceCovered(MAX_DIST_ALLOWED+lengthOfLevel);
			int width=regPigFlyFlyAtlas.findRegion("reg_pig_fly_standstill").getRegionWidth();
			int height=regPigFlyFlyAtlas.findRegion("reg_pig_fly_standstill").getRegionHeight();
			int distanceCoveredByEnemy=0;
			//rand.nextInt((max - min) + 1) + min;
			Random rand=new Random();
			while(distanceCoveredByEnemy<=lengthOfLevel){
				enemies.add(new PigFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
				enemies.add(new PigFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
				enemies.add(new PigFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
				enemies.add(new PigGround(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),MainGame.GROUND,width,height,monkey));
				enemies.add(new PigGround(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),MainGame.GROUND,width,height,monkey));
				enemies.add(new PigGround(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),MainGame.GROUND,width,height,monkey));
				distanceCoveredByEnemy+=lengthOfLevel/5;
			}
		}
		break;
		case 3:
		{
			lengthOfLevel=SCREEN_WIDTH*5;
			portaltree=new PortalTree(portalTreeTexture.getWidth(),portalTreeTexture.getHeight(),monkey);
			monkey.setMaxBackgroundDistanceCovered(MAX_DIST_ALLOWED+lengthOfLevel);
			int width=regPigFlyFlyAtlas.findRegion("reg_pig_fly_standstill").getRegionWidth();
			int height=regPigFlyFlyAtlas.findRegion("reg_pig_fly_standstill").getRegionHeight();
			int distanceCoveredByEnemy=0;
			//in order to arrange the enemies favorably in the map keep track of pigs position
			//rand.nextInt((max - min) + 1) + min;
			Random rand=new Random();
			while(distanceCoveredByEnemy<=lengthOfLevel){
				enemies.add(new PigFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
				enemies.add(new PigFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
				enemies.add(new PigFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
				enemies.add(new PigFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
				enemies.add(new PigFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
				enemies.add(new PigGroundPiggyBack(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),MainGame.GROUND,width,height,monkey));
				enemies.add(new PigGroundPiggyBack(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),MainGame.GROUND,width,height,monkey));
				enemies.add(new PigGroundPiggyBack(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),MainGame.GROUND,width,height,monkey));
				enemies.add(new PigGround(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),MainGame.GROUND,width,height,monkey));
				distanceCoveredByEnemy+=lengthOfLevel/5;
			}
		}
		break;
		case 4:
		{
			lengthOfLevel=SCREEN_WIDTH*5;
			portaltree=new PortalTree(portalTreeTexture.getWidth(),portalTreeTexture.getHeight(),monkey);
			monkey.setMaxBackgroundDistanceCovered(MAX_DIST_ALLOWED+lengthOfLevel);
			int width=regPigFlyFlyAtlas.findRegion("reg_pig_fly_standstill").getRegionWidth();
			int height=regPigFlyFlyAtlas.findRegion("reg_pig_fly_standstill").getRegionHeight();
			int distanceCoveredByEnemy=0;//in order to arrange the enemies favorably in the map keep track of pigs position
			//rand.nextInt((max - min) + 1) + min;
			Random rand=new Random();
			distanceCoveredByEnemy+=MAX_DIST_ALLOWED*2;
			while(distanceCoveredByEnemy<=lengthOfLevel){
				enemies.add(new PigFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
				enemies.add(new PigFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
				enemies.add(new BoarFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
				enemies.add(new BoarFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
				enemies.add(new BoarFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
				enemies.add(new BoarFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
				enemies.add(new PigGroundPiggyBack(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),MainGame.GROUND,width,height,monkey));
				enemies.add(new PigGroundPiggyBack(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),MainGame.GROUND,width,height,monkey));
				enemies.add(new PigGroundPiggyBack(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),MainGame.GROUND,width,height,monkey));
				distanceCoveredByEnemy+=lengthOfLevel/5;
			}
		}
		break;
		case 5:
		{
			lengthOfLevel=SCREEN_WIDTH*6;
			portaltree=new PortalTree(portalTreeTexture.getWidth(),portalTreeTexture.getHeight(),monkey);
			monkey.setMaxBackgroundDistanceCovered(MAX_DIST_ALLOWED+lengthOfLevel);
			int width=regPigFlyFlyAtlas.findRegion("reg_pig_fly_standstill").getRegionWidth();
			int height=regPigFlyFlyAtlas.findRegion("reg_pig_fly_standstill").getRegionHeight();
			int distanceCoveredByEnemy=0;//in order to arrange the enemies favorably in the map keep track of pigs position
			//rand.nextInt((max - min) + 1) + min;
			Random rand=new Random();
			distanceCoveredByEnemy+=MAX_DIST_ALLOWED*2;
			while(distanceCoveredByEnemy<=lengthOfLevel){
					enemies.add(new JumboRegPigFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
					enemies.add(new JumboRegPigFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
					enemies.add(new JumboRegPigFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
					enemies.add(new JumboRegPigFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
					enemies.add(new BoarFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
					enemies.add(new BoarFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
					enemies.add(new PigGroundPiggyBack(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),MainGame.GROUND,width,height,monkey));
					enemies.add(new PigGroundPiggyBack(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),MainGame.GROUND,width,height,monkey));
					enemies.add(new PigGroundPiggyBack(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),MainGame.GROUND,width,height,monkey));
				distanceCoveredByEnemy+=lengthOfLevel/5;
			}
		}
		break;
		case 6:
		{
			lengthOfLevel=SCREEN_WIDTH*6;
			portaltree=new PortalTree(portalTreeTexture.getWidth(),portalTreeTexture.getHeight(),monkey);
			monkey.setMaxBackgroundDistanceCovered(MAX_DIST_ALLOWED+lengthOfLevel);
			int width=regPigFlyFlyAtlas.findRegion("reg_pig_fly_standstill").getRegionWidth();
			int height=regPigFlyFlyAtlas.findRegion("reg_pig_fly_standstill").getRegionHeight();
			int distanceCoveredByEnemy=0;//in order to arrange the enemies favorably in the map keep track of pigs position
			//rand.nextInt((max - min) + 1) + min;
			Random rand=new Random();
			distanceCoveredByEnemy+=MAX_DIST_ALLOWED*2;
			while(distanceCoveredByEnemy<=lengthOfLevel){
				enemies.add(new JumboRegPigFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
				enemies.add(new JumboRegPigFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
				enemies.add(new JumboRegPigFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
				enemies.add(new JumboRegPigFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
				enemies.add(new BoarFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
				enemies.add(new BoarFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
				enemies.add(new PigGroundPiggyBack(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),MainGame.GROUND,width,height,monkey));
				enemies.add(new PigGroundPiggyBack(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),MainGame.GROUND,width,height,monkey));
				enemies.add(new PigGroundPiggyBack(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),MainGame.GROUND,width,height,monkey));
				distanceCoveredByEnemy+=lengthOfLevel/5;
			}
		}
		break;
		case 7:
		{
			lengthOfLevel=SCREEN_WIDTH*7;
			portaltree=new PortalTree(portalTreeTexture.getWidth(),portalTreeTexture.getHeight(),monkey);
			monkey.setMaxBackgroundDistanceCovered(MAX_DIST_ALLOWED+lengthOfLevel);
			int width=regPigFlyFlyAtlas.findRegion("reg_pig_fly_standstill").getRegionWidth();
			int height=regPigFlyFlyAtlas.findRegion("reg_pig_fly_standstill").getRegionHeight();
			int distanceCoveredByEnemy=0;//in order to arrange the enemies favorably in the map keep track of pigs position
			//rand.nextInt((max - min) + 1) + min;
			Random rand=new Random();
			distanceCoveredByEnemy+=MAX_DIST_ALLOWED*2;
			while(distanceCoveredByEnemy<=lengthOfLevel){
				enemies.add(new JumboRegPigFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
				enemies.add(new JumboRegPigFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
				enemies.add(new JumboRegPigFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
				enemies.add(new JumboRegPigFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
				enemies.add(new BoarFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
				enemies.add(new BoarFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
				enemies.add(new JumboRegPigGround(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),MainGame.GROUND,width,height,monkey));
				enemies.add(new JumboRegPigGround(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),MainGame.GROUND,width,height,monkey));
				enemies.add(new PigGroundPiggyBack(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),MainGame.GROUND,width,height,monkey));
				distanceCoveredByEnemy+=lengthOfLevel/5;
			}
		}
		break;
		case 8:
		{
			lengthOfLevel=SCREEN_WIDTH*7;
			portaltree=new PortalTree(portalTreeTexture.getWidth(),portalTreeTexture.getHeight(),monkey);
			monkey.setMaxBackgroundDistanceCovered(MAX_DIST_ALLOWED+lengthOfLevel);
			int width=regPigFlyFlyAtlas.findRegion("reg_pig_fly_standstill").getRegionWidth();
			int height=regPigFlyFlyAtlas.findRegion("reg_pig_fly_standstill").getRegionHeight();
			int distanceCoveredByEnemy=0;//in order to arrange the enemies favorably in the map keep track of pigs position
			//rand.nextInt((max - min) + 1) + min;
			Random rand=new Random();
			distanceCoveredByEnemy+=MAX_DIST_ALLOWED*2;
			while(distanceCoveredByEnemy<=lengthOfLevel){
					enemies.add(new JumboRegPigFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
					enemies.add(new JumboRegPigFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
					enemies.add(new JumboRegPigFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
					enemies.add(new BoarFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
					enemies.add(new BoarFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
					enemies.add(new JumboRegPigGround(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),MainGame.GROUND,width,height,monkey));
					enemies.add(new JumboRegPigGround(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),MainGame.GROUND,width,height,monkey));
					enemies.add(new JumboPigGroundPiggyBack(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),MainGame.GROUND,width,height,monkey));
					enemies.add(new JumboPigGroundPiggyBack(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),MainGame.GROUND,width,height,monkey));
					enemies.add(new JumboPigGroundPiggyBack(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),MainGame.GROUND,width,height,monkey));
				distanceCoveredByEnemy+=lengthOfLevel/5;
			}
		}
		case 9:
		{
			lengthOfLevel=SCREEN_WIDTH*8;
			portaltree=new PortalTree(portalTreeTexture.getWidth(),portalTreeTexture.getHeight(),monkey);
			monkey.setMaxBackgroundDistanceCovered(MAX_DIST_ALLOWED+lengthOfLevel);
			int width=regPigFlyFlyAtlas.findRegion("reg_pig_fly_standstill").getRegionWidth();
			int height=regPigFlyFlyAtlas.findRegion("reg_pig_fly_standstill").getRegionHeight();
			int distanceCoveredByEnemy=0;//in order to arrange the enemies favorably in the map keep track of pigs position
			//rand.nextInt((max - min) + 1) + min;
			Random rand=new Random();
			distanceCoveredByEnemy+=MAX_DIST_ALLOWED*2;
			while(distanceCoveredByEnemy<=lengthOfLevel){
				enemies.add(new JumboRegPigFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
				enemies.add(new JumboBoarFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
				enemies.add(new JumboBoarFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
				enemies.add(new JumboBoarFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
				enemies.add(new JumboBoarFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
				enemies.add(new JumboRegPigGround(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),MainGame.GROUND,width,height,monkey));
				enemies.add(new JumboPigGroundPiggyBack(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),MainGame.GROUND,width,height,monkey));
				enemies.add(new JumboPigGroundPiggyBack(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),MainGame.GROUND,width,height,monkey));
				enemies.add(new JumboPigGroundPiggyBack(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),MainGame.GROUND,width,height,monkey));
				enemies.add(new JumboPigGroundPiggyBack(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),MainGame.GROUND,width,height,monkey));
				distanceCoveredByEnemy+=lengthOfLevel/5;
			}
		}
		break;
		case 10:
		{	
			lengthOfLevel=SCREEN_WIDTH*(8+(int)(level-10)/2);
			portaltree=new PortalTree(portalTreeTexture.getWidth(),portalTreeTexture.getHeight(),monkey);
			monkey.setMaxBackgroundDistanceCovered(MAX_DIST_ALLOWED+lengthOfLevel);
			int width=regPigFlyFlyAtlas.findRegion("reg_pig_fly_standstill").getRegionWidth();
			int height=regPigFlyFlyAtlas.findRegion("reg_pig_fly_standstill").getRegionHeight();
			int distanceCoveredByEnemy=0;//in order to arrange the enemies favorably in the map keep track of pigs position
			//rand.nextInt((max - min) + 1) + min;
			Random rand=new Random();
			distanceCoveredByEnemy+=MAX_DIST_ALLOWED*2;
			while(distanceCoveredByEnemy<=lengthOfLevel){
				enemies.add(new JumboRegPigFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
				enemies.add(new JumboRegPigFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
				enemies.add(new JumboBoarFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
				enemies.add(new JumboBoarFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
				enemies.add(new JumboBoarFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
				enemies.add(new JumboBoarFly(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),rand.nextInt(((SCREEN_HEIGHT-height)-MainGame.GROUND)+1)+MainGame.GROUND,width,height,monkey));
				enemies.add(new JumboRegPigGround(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),MainGame.GROUND,width,height,monkey));
				enemies.add(new JumboPigGroundPiggyBack(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),MainGame.GROUND,width,height,monkey));
				enemies.add(new JumboPigGroundPiggyBack(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),MainGame.GROUND,width,height,monkey));
				enemies.add(new JumboPigGroundPiggyBack(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),MainGame.GROUND,width,height,monkey));
				enemies.add(new JumboPigGroundPiggyBack(distanceCoveredByEnemy+MAX_DIST_ALLOWED+rand.nextInt(width*2+1),MainGame.GROUND,width,height,monkey));
				distanceCoveredByEnemy+=lengthOfLevel/5;
			}
		}
		default://endless levelssss
			break;
		}
	}
	@Override
	public void dispose() {
		batch.dispose();
		standStillTexture.dispose();
		bananaMissileTexture.dispose();
		watermelonCannonTexture.dispose();
		deadRegPigFly2Texture.dispose();
		shootTexture.dispose();
		deadRegPigFlyTexture.dispose();
		portalTreeTexture.dispose();
		monkeyFlyTextureAtlas.dispose();
		shapeRenderer.dispose();
		monkeyWalkTextureAtlas.dispose();
		cornProjectileAtlas.dispose();
		regPigFlyFlyAtlas.dispose();
		regPigGroundAtlas.dispose();
		movePadTexture.dispose();
		shootMissileTexture.dispose();
		shootCannonTexture.dispose();
		powerUpMissileTexture.dispose();
		powerUpCannonTexture.dispose();
		powerUpLifeTexture.dispose();
		powerUpShieldTexture.dispose();
		shieldTexture.dispose();
		lifeBoardTexture.dispose();
		lifeTexture.dispose();
		backgroundTexture.dispose();
		font.dispose();
		//musicManager.stop();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		//dispose();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		batch.begin();
		monkey.update();
		bg1.update();
		bg2.update();
		
		portaltree.update();
		batch.draw(backgroundTexture,bg1.getBgX(),bg1.getBgY(),SCREEN_WIDTH+2,SCREEN_HEIGHT);
		batch.draw(backgroundTexture,bg2.getBgX(),bg2.getBgY(),SCREEN_WIDTH+2,SCREEN_HEIGHT);
		
		batch.draw(portalTreeTexture,portaltree.getCenterX(),portaltree.getCenterY());
		elapsedTime += delta;
		//monkey handler
		if(monkey.isFlying()){
			if(monkey.isShooting()){
				batch.draw(shootTexture,monkey.getCenterX(),monkey.getCenterY());
			}else{
				batch.draw(monkeyFlyAnimation.getKeyFrame(elapsedTime, true),monkey.getCenterX(), monkey.getCenterY());
			}
		}else{
			TextureRegion t=monkeyWalkAnimation.getKeyFrame(elapsedTime, true);
			if(!t.isFlipX())t.flip(monkey.isMovingLeft(),false);else t.flip(monkey.isMovingRight(),false) ;
			if(monkey.isMovingRight()){
				batch.draw(t,monkey.getCenterX(), monkey.getCenterY());
			}else if(monkey.isMovingLeft()){
				batch.draw(t,monkey.getCenterX(), monkey.getCenterY());
			}else{
				batch.draw(standStillTexture,monkey.getCenterX(), monkey.getCenterY());
			}
		}
		ArrayList<Projectile> projectiles = monkey.getProjectiles();
		for (int i = 0; i < projectiles.size(); i++) {
			Projectile p = projectiles.get(i);
			if (p.isVisible() == true) {
				p.update();
				if(p instanceof BananaMissile){
					batch.draw(bananaMissileTexture,p.getX(),p.getY());
				}else{
					batch.draw(watermelonCannonTexture,p.getX(),p.getY(),p.getWidth(),p.getHeight());
				}
				for(Enemy enemy:enemies){
					if(p.getCollisionBox().intersects(enemy.getCollisionBox())&&!enemy.isDying()){
						enemy.hit(p);
						if((enemy.isDying()||enemy.isDead())){
							score+=enemy.getScore();
							if(enemy.hasPowerUp()){
								powerUps.add(enemy.getPowerUp());
							}
						}
						projectiles.remove(i);
						break;
					}
				}
			} else {
				projectiles.remove(i);
			}
		}
		
		for(int i=0;i<powerUps.size();i++){//powerUp handler
			PowerUp powerup=powerUps.get(i);
			powerup.update();
			if(powerup instanceof PowerUpCannon){
				batch.draw(powerUpCannonTexture,powerup.getX(),powerup.getY());
	    	}else if(powerup instanceof PowerUpMissile){
	    		batch.draw(powerUpMissileTexture,powerup.getX(),powerup.getY());
	    	}else if(powerup instanceof PowerUpSpecial){
	    		batch.draw(powerUpSpecialTexture,powerup.getX(),powerup.getY());
	    	}else if(powerup instanceof PowerUpLife){
	    		batch.draw(powerUpLifeTexture,powerup.getX(),powerup.getY());
	    	}else if(powerup instanceof PowerUpShield){
	    		PowerUpShield shield=(PowerUpShield)powerup;
	    		if(shield.isActivated()){
	    			if(!shield.isTimesUp()){
	    				batch.draw(shieldTexture,shield.getX(),shield.getY());
	    			}else{
	    				monkey.setShieldOn(false);
	    				powerUps.remove(i);
	    			}
	    		}else{
	    			batch.draw(powerUpShieldTexture,powerup.getX(),powerup.getY());
	    		}
	    			
	    	}
			if(powerup.isOffscreen()){//if powerUp is off screen remove it
				powerUps.remove(i);
			}
			//monkey is connecting with powerup.absorb it.
			if(monkey.intersectsWithCollisionBox(new Rectangle(powerup.getX(),powerup.getY(),PowerUp.getWidth(),PowerUp.getHeight()))){
				if(!(powerup instanceof PowerUpShield)){
					powerUps.remove(i);
				}else{
					((PowerUpShield)powerup).setActivatedWidth(shieldTexture.getWidth());
					((PowerUpShield)powerup).setActivatedHeight(shieldTexture.getHeight());
				}
				monkey.absorbPowerUp(powerup);
			}
		}
		for (int i=0;i<enemies.size();i++) {///enemy handler
			Enemy enemy=enemies.get(i);
			if(!enemy.isDead()){
				enemy.update();
					if(!enemy.isDying()){
						//for hiteffectcall
						batch.setColor(1.0f, 1.0f, 1.0f, enemy.getAlpha());
						//handle jumbos and then default to regular pigs and boars
						if(enemy instanceof JumboBoarFly){
							if(enemy.getMovementSpeed()<0){
								batch.draw(jumboBoarFlyForwardAnimation.getKeyFrame(elapsedTime, true),enemy.getCenterX(),enemy.getCenterY());
							}else if(enemy.getMovementSpeed()>0){
								batch.draw(angryBoarFlyFlyBackwardAnimation.getKeyFrame(elapsedTime, true),enemy.getCenterX(),enemy.getCenterY());
							}else{//==0
								batch.draw(angryBoarFlyFlyStillAnimation.getKeyFrame(elapsedTime, true),enemy.getCenterX(),enemy.getCenterY());
							}
						}else if(enemy instanceof JumboRegPigFly){
							if(enemy.getMovementSpeed()<0){
								batch.draw(jumboRegPigFlyForwardAnimation.getKeyFrame(elapsedTime, true),enemy.getCenterX(),enemy.getCenterY());
							}else if(enemy.getMovementSpeed()>0){
								batch.draw(jumboRegPigFlyBackwardAnimation.getKeyFrame(elapsedTime, true),enemy.getCenterX(),enemy.getCenterY());
							}else{//==0
								batch.draw(jumboRegPigFlyStillAnimation.getKeyFrame(elapsedTime, true),enemy.getCenterX(),enemy.getCenterY());
							}
						}else if(enemy instanceof JumboRegPigGround){
							TextureRegion t=jumboRegPigGroundAnimation.getKeyFrame(elapsedTime, true);
							if(!t.isFlipX())t.flip(enemy.getMovementSpeed()>0,false);else t.flip(enemy.getMovementSpeed()<0,false);
							batch.draw(t,enemy.getCenterX(),enemy.getCenterY(),enemy.getWidth(),enemy.getHeight());
						}else if(enemy instanceof JumboPigGroundPiggyBack){
							TextureRegion t=jumboPiggyBackGroundAnimation.getKeyFrame(elapsedTime, true);
							if(!t.isFlipX())t.flip(enemy.getMovementSpeed()>0,false);else t.flip(enemy.getMovementSpeed()<0,false);
							batch.draw(t,enemy.getCenterX(),enemy.getCenterY(),enemy.getWidth(),enemy.getHeight());
						}
						else if(enemy instanceof PigFly){
							if(enemy.getMovementSpeed()<0){
								batch.draw(regPigFlyFlyForwardAnimation.getKeyFrame(elapsedTime, true),enemy.getCenterX(),enemy.getCenterY());
							}else if(enemy.getMovementSpeed()>0){
								batch.draw(regPigFlyFlyBackwardAnimation.getKeyFrame(elapsedTime, true),enemy.getCenterX(),enemy.getCenterY());
							}else{//==0
								batch.draw(regPigFlyFlyStillAnimation.getKeyFrame(elapsedTime, true),enemy.getCenterX(),enemy.getCenterY());
							}
						}else if(enemy instanceof PigGroundPiggyBack){
							TextureRegion t=piggyBackGroundAnimation.getKeyFrame(elapsedTime, true);
							if(!t.isFlipX())t.flip(enemy.getMovementSpeed()>0,false);else t.flip(enemy.getMovementSpeed()<0,false);
							batch.draw(t,enemy.getCenterX(),enemy.getCenterY(),enemy.getWidth(),enemy.getHeight());
						}else if(enemy instanceof PigGround){
							//flip pig ground if he is walking backwards
							TextureRegion t=regPigGroundAnimation.getKeyFrame(elapsedTime, true);
							if(!t.isFlipX())t.flip(enemy.getMovementSpeed()>0,false);else t.flip(enemy.getMovementSpeed()<0,false);
							batch.draw(t,enemy.getCenterX(),enemy.getCenterY(),enemy.getWidth(),enemy.getHeight());
						}else if(enemy instanceof BoarFly){
							if(enemy.getMovementSpeed()<0){
								batch.draw(angryBoarFlyFlyForwardAnimation.getKeyFrame(elapsedTime, true),enemy.getCenterX(),enemy.getCenterY());
							}else if(enemy.getMovementSpeed()>0){
								batch.draw(angryBoarFlyFlyBackwardAnimation.getKeyFrame(elapsedTime, true),enemy.getCenterX(),enemy.getCenterY());
							}else{//==0
								batch.draw(angryBoarFlyFlyStillAnimation.getKeyFrame(elapsedTime, true),enemy.getCenterX(),enemy.getCenterY());
							}
						}
					}else{
						//fadeout effect
						batch.setColor(1.0f, 1.0f, 1.0f, enemy.getAlpha());
						if(enemy.getKillingProjectile() instanceof BananaMissile){
							batch.draw(deadRegPigFlyTexture,enemy.getCenterX(),enemy.getCenterY());
						}else{
							batch.draw(deadRegPigFly2Texture,enemy.getCenterX(),enemy.getCenterY());
						}
						batch.setColor(1.0f, 1.0f, 1.0f, 1.0f);
						enemy.setAlpha(enemy.getAlpha()-0.03f);
					}
					ArrayList<Projectile> enemyProjectiles=enemy.getProjectiles();
					//shapeRenderer.setColor(1, 1, 1, 1);
					for(int n=0;n<enemyProjectiles.size();n++){
						CornOnCobMissile corn=(CornOnCobMissile)enemyProjectiles.get(n);
						if (corn.isVisible() == true) {
							corn.update();
							if(enemy instanceof PigFly||enemy instanceof BoarFly){
								batch.draw(cornProjectileAnimation.getKeyFrame(elapsedTime, true),corn.getX(),corn.getY());
							}else if(enemy instanceof PigGroundPiggyBack){
								if(corn.hasYComponent()){//little piggy
									TextureRegion t=cornProjectileAnimation.getKeyFrame(elapsedTime, true);
									batch.draw(t,corn.getX(),corn.getY(),t.getRegionWidth()/2,t.getRegionHeight()/2,t.getRegionWidth(),t.getRegionHeight(), 1.0f, 1.0f, -35.0f);
								}else{
									batch.draw(cornProjectileAnimation.getKeyFrame(elapsedTime, true),corn.getX(),corn.getY());
								}
							}else if(enemy instanceof PigGround){
								TextureRegion t=cornProjectileAnimation.getKeyFrame(elapsedTime, true);
								batch.draw(t,corn.getX(),corn.getY(),t.getRegionWidth()/2,t.getRegionHeight()/2,t.getRegionWidth(),t.getRegionHeight(), 1.0f, 1.0f, -35.0f);
							}
							if(monkey.intersectsWithCollisionBox(corn.getCollisionBox(),true)){
								monkey.hit();
								enemyProjectiles.remove(n);
								if(monkey.getLife()==0){
									game.setScreen(game.gameOverScreen);
								}
							}
							
						}else{
							enemyProjectiles.remove(n);
						}
					}
			}else{
				enemies.remove(i);
			}
		}
		batch.draw(movePadTexture,0,5);
		batch.draw(lifeBoardTexture,movePadTexture.getWidth()+20,0);
		for(int i=0;i<monkey.getLife();i++){
			batch.draw(lifeTexture,movePadTexture.getWidth()+60+i*lifeTexture.getWidth(),8);
		}
		batch.draw(shootCannonTexture,SCREEN_WIDTH-shootCannonTexture.getWidth()-(int)(shootMissileTexture.getWidth()*1.5),10);
		batch.draw(shootMissileTexture,SCREEN_WIDTH-shootMissileTexture.getWidth()-20,10);
		font.draw(batch, score+"", SCREEN_WIDTH/2, SCREEN_HEIGHT);
		batch.end();
	}

	@Override
	public void resize(int arg0, int arg1) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

	public void levelUp(){
		level++;
		monkey.restart();
		portaltree.setVisible(false);
		enemies.clear();
		powerUps.clear();
		
		bg1.setBgPosition(0+BACKGROUND_OFFSET,0);
		bg2.setBgPosition(SCREEN_WIDTH+BACKGROUND_OFFSET,0);
		levelWriter();
	}

	public static Background getBg1(){
		return bg1;
	}
	public static Background getBg2(){
		return bg2;
	}
	public int getScore() {
		return score;
	}

}
