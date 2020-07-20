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

        //测试添加玩家用
        Player player1 = new Player(GameConstant.FIRST_PLAYER,GameConstant.FIRST_POSITION.changeToTrueLocation(),new Bomb(1,1),true);
        GameData.player1 = player1;
        GameData.getMainFrame().getLayeredPane().add(player1, JLayeredPane.POPUP_LAYER);

        Player player2 = new Player(GameConstant.SECOND_PLAYER,GameConstant.SECOND_POSITION.changeToTrueLocation(),new Bomb(1,1),false);
        GameData.player2 = player2;
        GameData.getMainFrame().getLayeredPane().add(player2,JLayeredPane.POPUP_LAYER);

        GameData.getMainFrame().init();
        GameData.getMainFrame().setVisible(true);
    }






}
