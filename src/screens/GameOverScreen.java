package screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.example.monkeymayhem.MainGame;

public class GameOverScreen implements Screen {
	private Texture backgroundTexture;
	private SpriteBatch batch;
	private BitmapFontCache fontCache;
	private MainGame game;
	public GameOverScreen(MainGame game){
		backgroundTexture=new Texture(Gdx.files.internal("data/gameOverScreen.png"));
		batch=new SpriteBatch();
		fontCache=new BitmapFontCache(new BitmapFont(Gdx.files.internal("data/score.fnt"),Gdx.files.internal("data/score.png"),false));
		this.game=game;
	}
	@Override
	public void dispose() {
		// TODO Auto-generated method stub
		backgroundTexture.dispose();
		batch.dispose();
	}

	@Override
	public void hide() {
		// TODO Auto-generated method stub
		dispose();
	}

	@Override
	public void pause() {
		// TODO Auto-generated method stub

	}

	@Override
	public void render(float arg0) {
		// TODO Auto-generated method stub
		batch.begin();
		batch.draw(backgroundTexture,-5,-15);
		fontCache.addText(game.gameScreen.getScore()+"", MainGame.SCREEN_WIDTH/2, 100);
		fontCache.draw(batch);
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

}
