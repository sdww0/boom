package game.music;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.sound.sampled.*;
import java.io.IOException;

/**
 * 播放音乐
 * @author njdnhh
 */
public class MusicPlayer {
    final static public int BOOM = 1;
    final static public int PLACE = 2;
    final static public int PICKUP = 3;
    final static public int HURT = 4;


    private static Clip BackGroundMusic;
    private static FloatControl gainControl;
    private static float volume;

    public static void init() {
        AudioInputStream audioInput = null;
        volume = 0.0f;
        try {

            audioInput = AudioSystem.getAudioInputStream(MusicPlayer.class.getResource("/game/music/BackGroundMusic.wav"));
            BackGroundMusic = AudioSystem.getClip();
            BackGroundMusic.open(audioInput);
            BackGroundMusic.stop();
            gainControl = (FloatControl) BackGroundMusic.getControl(FloatControl.Type.MASTER_GAIN);
        } catch (UnsupportedAudioFileException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void Play(int wave) {
        String waveName = "";

        switch (wave) {
            case BOOM:
                waveName = "Boom.wav";
                break;
            case PLACE:
                waveName = "Place.wav";
                break;
            case PICKUP:
                waveName = "PickUp.wav";
                break;
            case HURT:
                waveName = "Hurt.wav";
                break;
            default:
                break;
        }

        try {
            AudioStream as = new AudioStream(MusicPlayer.class.getResourceAsStream("/game/music/"+waveName));
            AudioPlayer.player.start(as);
        } catch (Exception ignore) {

        }

    }

    public static void playBackGroundMusic() {
        BackGroundMusic.start();
        BackGroundMusic.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public static void stopBackGroundMusic() {
        BackGroundMusic.stop();
    }

    public static void lowerVolume(){
        gainControl.setValue(volume-=5.0f);
    }

    public static void higherVolume(){
        gainControl.setValue(volume+=5.0f);
    }

}
