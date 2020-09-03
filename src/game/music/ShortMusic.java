package game.music;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

/**
 * 音效类
 * @author njdnhh
 */
public class ShortMusic {
    protected Clip[] music;
    protected FloatControl[] gainControl;
    protected  float volume;
    public boolean canPlay;

    public ShortMusic(URL[] url){
        AudioInputStream audioInput = null;
        volume = 0.0f;
        music = new Clip[url.length];
        gainControl = new FloatControl[url.length];
        canPlay = true;
        try {
        for(int n =0;n<url.length;n++){
            audioInput = AudioSystem.getAudioInputStream(url[n]);
            music[n] = AudioSystem.getClip();
            music[n].open(audioInput);
            music[n].stop();
            gainControl[n] = (FloatControl) music[n].getControl(FloatControl.Type.MASTER_GAIN);
        }
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public  void play(int n){
        if(!canPlay){
            return;
        }
        music[n-1].start();
        music[n-1].setMicrosecondPosition(0);
    }

    public  void lowerVolume(){
        volume-=5.0f;
        for(int n =0;n<gainControl.length;n++){
            gainControl[n].setValue(volume);
        }
    }

    public  void higherVolume(){
        if(volume-5.0f<=0.01f) {
            return;
        }
        volume+=5.0f;
        for(int n =0;n<gainControl.length;n++){
            gainControl[n].setValue(volume);
        }
    }



}
