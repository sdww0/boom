package game.main;

import game.basement.Location;
import game.basement.TrueLocation;
import game.element.Bomb;
import game.element.Player;

import game.gamedata.GameConstant;
import game.gamedata.GameData;

import game.gui.GameFrame;

import game.music.MusicPlayer;

import javax.swing.*;
import java.awt.*;

/**
 * 主程序
 * @author njdnhh
 */
public class Main {


    public static void main(String[] args){
        initGame();
    }

    public static void initGame(){
        GameData.init();
        MusicPlayer.init();

        GameFrame mainFrame = new GameFrame(GameData.getBoard());
        //测试添加玩家用
        Player player1 = new Player(GameConstant.FIRST_PLAYER,new TrueLocation(0,0),new Bomb(1,1), GameData.playersLife,true);
        GameData.player1 = player1;
        mainFrame.getLayeredPane().add(player1, JLayeredPane.POPUP_LAYER);

        Player player2 = new Player(GameConstant.SECOND_PLAYER,Location.changeToTrueLocation(new Location(15,0)),new Bomb(1,1),GameData.playersLife,false);
        GameData.player2 = player2;
        mainFrame.getLayeredPane().add(player2,JLayeredPane.POPUP_LAYER);

        mainFrame.init();
        mainFrame.setVisible(true);
    }






}
