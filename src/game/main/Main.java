package game.main;

import game.basement.Location;
import game.basement.TrueLocation;
import game.element.Bomb;
import game.element.Player;

import game.element.Robot;
import game.gamedata.GameConstant;
import game.gamedata.GameData;

import game.gui.GameFrame;

import game.music.MusicPlayer;
import game.robot.RobotController;

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
        Player player1 = new Player(GameConstant.THIRD_PLAYER,GameConstant.THIRD_POSITION.changeToTrueLocation(),new Bomb(1,1),true);
        GameData.player1 = player1;
        player1.init();
        GameData.getMainFrame().getLayeredPane().add(player1, JLayeredPane.POPUP_LAYER);

        Player player2 = new Player(GameConstant.SECOND_PLAYER,GameConstant.SECOND_POSITION.changeToTrueLocation(),new Bomb(1,1),false);
        GameData.player2 = player2;
        player2.init();
        GameData.getMainFrame().getLayeredPane().add(player2,JLayeredPane.POPUP_LAYER);

        Robot robot1 = new Robot(GameConstant.FOURTH_PLAYER,GameConstant.FOURTH_POSITION.changeToTrueLocation(),new Bomb(1,1));
        robot1.init();
        GameData.getMainFrame().getLayeredPane().add(robot1,JLayeredPane.POPUP_LAYER);
        GameData.robots.add(robot1);

        Robot robot2 = new Robot(GameConstant.FIRST_PLAYER,GameConstant.FIRST_POSITION.changeToTrueLocation(),new Bomb(1,1));
        robot2.init();
        GameData.getMainFrame().getLayeredPane().add(robot2,JLayeredPane.POPUP_LAYER);
        GameData.robots.add(robot2);

        GameData.getMainFrame().init();
        GameData.getMainFrame().setVisible(true);
    }
}
