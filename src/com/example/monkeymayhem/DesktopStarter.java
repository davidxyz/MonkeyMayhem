package com.example.monkeymayhem;

//import com.badlogic.gdx.backends.jglfw.JglfwApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.tools.texturepacker.TexturePacker;
//TexturePacker.process("/Users/davidhunpatin/Desktop/2dgameart/monkey mayhem/enemies/reg_pig_fly", "/Users/davidhunpatin/Documents/workspace/MonkeyMayhemSource/data", "reg_pig_fly.atlas");
public class DesktopStarter {
	public static void main(String[] args)
	{
		//JglfwApplication
		//TexturePacker.process("/Users/davidhunpatin/Desktop/Hobbies/2dgameart/monkey mayhem/enemies/jumbo_boar_pig_fly/", "/Users/davidhunpatin/Documents/workspace/MonkeyMayhemSource/data", "jumbo_boar_pig_fly.atlas");
	    new LwjglApplication(new MainGame(), "Monkey Mayhem", MainGame.SCREEN_WIDTH, MainGame.SCREEN_HEIGHT);
		//new JglfwApplication(new MainGame(), "Monkey Mayhem", MainGame.SCREEN_WIDTH, MainGame.SCREEN_HEIGHT,true);
		
	}
}
