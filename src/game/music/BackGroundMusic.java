package game.music;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

/**
 * 背景音乐
 * @author njdnhh
 */
public class BackGroundMusic extends ShortMusic{

    public BackGroundMusic(URL[] url){
        super(url);
    }

    @Override
    public  void play(int n){
        if(!canPlay){
            return;
        }
        music[n-1].start();
        music[n-1].loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop(int n){
        music[n-1].stop();
    }

}
