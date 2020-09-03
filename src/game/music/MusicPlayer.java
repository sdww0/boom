package game.music;

import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

import javax.sound.sampled.*;
import java.io.IOException;
import java.net.URL;

/**
 * 播放音乐
 * @author njdnhh
 */
public class MusicPlayer {
    final static public int BOOM = 1;
    final static public int PLACE = 2;
    final static public int PICKUP = 3;
    final static public int HURT = 4;
    final static private URL[] SHORT_MUSIC_URL = {
            MusicPlayer.class.getResource("/game/music/Boom.wav"),
            MusicPlayer.class.getResource("/game/music/Place.wav"),
            MusicPlayer.class.getResource("/game/music/PickUp.wav"),
            MusicPlayer.class.getResource("/game/music/Hurt.wav")
    };
    /**
     * 开始菜单游戏背景音乐
     * 玩游戏的时候背景音乐
     */
    final static public int START_MENU_BACK_GROUND_MUSIC = 1;
    final static public int PLAYING_BACK_GROUND_MUSIC = 2;
    final static private URL[] BACKGROUND_MUSIC_URL = {
            MusicPlayer.class.getResource("/game/music/BackGroundMusic.wav"),
            MusicPlayer.class.getResource("/game/music/GameBackGroundMusic.wav")
    };

    private static ShortMusic totalShortMusic;

    private static BackGroundMusic backGroundMusic;


    public static void init() {
        backGroundMusic = new BackGroundMusic(BACKGROUND_MUSIC_URL);
        totalShortMusic = new ShortMusic(SHORT_MUSIC_URL);
    }

    public static void playBackGroundMusic(int n) {
        backGroundMusic.canPlay = true;
        backGroundMusic.play(n);
    }

    public static void stopBackGroundMusic(int n) {
        backGroundMusic.canPlay = false;
        backGroundMusic.stop(n);
    }

    public static void lowerBackGroundMusicVolume(){
        backGroundMusic.lowerVolume();
    }

    public static void higherBackGroundMusicVolume(){
        backGroundMusic.higherVolume();
    }

    public static void playShortMusic() {
        totalShortMusic.canPlay = true;
    }

    public static void stopShortMusic() {
        totalShortMusic.canPlay = false;
    }

    public static void lowerShortMusicVolume(){
        totalShortMusic.lowerVolume();
    }

    public static void higherShortMusicVolume(){
        totalShortMusic.higherVolume();
    }

    public static void playShortMusic(int n){
        totalShortMusic.play(n);
    }


}
