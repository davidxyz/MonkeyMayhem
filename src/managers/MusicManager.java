package managers;

import java.util.HashMap;
import java.util.Set;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;

public class MusicManager{
	private boolean enabled=true;
	private HashMap<String,SoundByte> sounds;
	public MusicManager(){
		sounds=new HashMap<String,SoundByte>();
		Gdx.audio.newSound(Gdx.files.internal("data/monkeyFly.wav"));
		sounds.put("monkeyFly",new SoundByte(Gdx.audio.newSound(Gdx.files.internal("data/monkeyFly.wav"))));
		sounds.put("monkeyShoot",new SoundByte(Gdx.audio.newSound(Gdx.files.internal("data/monkeyShoot.wav"))));
	}
	 /**
     * Enables or disabled the music.
     */
    public void setEnabled(
        boolean enabled )
    {
        this.enabled = enabled;

        // if the music is being deactivated, stop any music being played
        if(!enabled) {
            stop();
        }
    }
    public void playOnLoop(String key){
    	if(sounds.containsKey(key)){
    		SoundByte currentSoundByte=sounds.get(key);
    		currentSoundByte.id=currentSoundByte.sound.loop();
    	}
    }
    public void stopLoop(String key){
    	if(sounds.containsKey(key)){
    		sounds.get(key).sound.stop(sounds.get(key).id);
    	}
    }
    public void play(String key){
    	if(sounds.containsKey(key)){
    		sounds.get(key).sound.play();
    	}
    }
    private class SoundByte{
    	public Sound sound;
    	public long id;
    	public SoundByte(Sound sound){
    		this.sound=sound;
    	}
    }
    public void stop()
    {
        Set<String> keys=sounds.keySet();
        for(String key: keys){
        	sounds.get(key).sound.dispose();
        }
    }
}
