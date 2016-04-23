package screens;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.scenes.scene2d.Actor;
import com.badlogic.gdx.scenes.scene2d.InputEvent;
import com.badlogic.gdx.scenes.scene2d.ui.Button;
import com.badlogic.gdx.scenes.scene2d.utils.ClickListener;
import com.example.monkeymayhem.MainGame;
public class MenuScreen
    implements
      Screen
{
	private final SpriteBatch spriteBatch;

	private final Texture backgroundTexture,logoTexture,playButtonTexture,settingsButtonTexture;
	public Button playButton,settingsButton;


    // setup the dimensions of the menu buttons
    private MainGame mainGame;
    public MenuScreen(
        MainGame game )
    {
    	mainGame=game;
    	spriteBatch = new SpriteBatch();
		backgroundTexture = new Texture(Gdx.files.internal("data/background1.png"));
		logoTexture = new Texture(Gdx.files.internal("data/monkeyMayhem.png"));
		
		playButtonTexture = new Texture(Gdx.files.internal("data/playButton.png"));
		playButton=new Button();
		settingsButton=new Button();
		playButton.setX(MainGame.SCREEN_WIDTH/2-playButtonTexture.getWidth()/2);
		playButton.setY(MainGame.SCREEN_HEIGHT-logoTexture.getHeight()-150);
		playButton.setWidth(playButtonTexture.getWidth());
		playButton.setHeight(playButtonTexture.getHeight());
		settingsButtonTexture=new Texture(Gdx.files.internal("data/settingsButton.png"));
		settingsButton.setX(30);
		settingsButton.setY(20);
		settingsButton.setWidth(settingsButton.getWidth());
		settingsButton.setHeight(settingsButton.getHeight());
    }
 
    @Override
    public void resize( int width, int height )
    {
   
    }

	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		spriteBatch.dispose();
		backgroundTexture.dispose();
		logoTexture.dispose();
		playButtonTexture.dispose();
		settingsButtonTexture.dispose();
	}

	@Override
	public void hide() {
		dispose();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(float delta) {
		// TODO Auto-generated method stub
		spriteBatch.begin();
		spriteBatch.draw(backgroundTexture,0,0);
		spriteBatch.draw(logoTexture,MainGame.SCREEN_WIDTH/2-logoTexture.getWidth()/2,MainGame.SCREEN_HEIGHT-logoTexture.getHeight()-20);
		spriteBatch.draw(playButtonTexture,MainGame.SCREEN_WIDTH/2-playButtonTexture.getWidth()/2,MainGame.SCREEN_HEIGHT-logoTexture.getHeight()-150);
		spriteBatch.draw(settingsButtonTexture,30,20);
		spriteBatch.end();
	}

	@Override
	public void resume() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void show() {
		// TODO Auto-generated method stub
		
	}

}