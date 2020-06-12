package game.main;

import game.basement.Location;
import game.element.Bomb;
import game.element.Player;

import game.gamedata.GameConstant;
import game.gamedata.GameData;

import game.gui.GameFrame;

import game.image.ImageReader;
import game.music.MusicPlayer;

import java.awt.*;

/**
 * 主程序
 * @author njdnhh
 */
public class Main {


    public static void main(String[] args){
        GameData.init();
        MusicPlayer.init();

        GameFrame mainFrame = new GameFrame(GameData.getBoard());
        Player player1 = new Player(0,new Location(0,0),new Bomb(1,1), GameData.playersLife);
        GameData.players.add(player1);
        GameData.getBoard().getSquare()[0][0].setElement(player1);

        MenuBar menuBar = new MenuBar();
        Menu menu = new Menu("Menu");
        Menu music = new Menu("Music");
        MenuItem start = new MenuItem("start");
        MenuItem stop = new MenuItem("stop");
        MenuItem lower = new MenuItem("lower Volume ");
        MenuItem higher = new MenuItem("higher Volume ");
        lower.addActionListener(e->MusicPlayer.lowerVolume());
        higher.addActionListener(e->MusicPlayer.higherVolume());
        start.addActionListener(e->MusicPlayer.playBackGroundMusic());
        stop.addActionListener(e->MusicPlayer.stopBackGroundMusic());
        menuBar.add(menu);
        menu.add(music);
        music.add(start);
        music.add(stop);
        music.add(lower);
        music.add(higher);
        mainFrame.setMenuBar(menuBar);

        mainFrame.add(GameData.getRightMenu());
        mainFrame.add(GameData.getBoard());
        mainFrame.setVisible(true);


    }


}
