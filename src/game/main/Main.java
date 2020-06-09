package game.main;

import game.basement.Location;
import game.element.Bomb;
import game.element.Player;
import game.element.Walls;
import game.gamedata.GameConstant;
import game.gamedata.GameData;
import game.gui.Board;
import game.gui.GameFrame;
import game.image.ImageReader;
import game.map.MapList;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 主程序
 * @author njdnhh
 */
public class Main {


    public static void main(String[] args){
        GameData.init();
        GameFrame mainFrame = new GameFrame(GameData.getBoard());
        Player player1 = new Player(new Location(0,0),new Bomb(1,1,1),100);
        GameData.players.add(player1);
        GameData.getBoard().getSquare()[0][0].setElement(player1);

        GameData.players.get(0).getBombs().add(null);
        GameData.players.get(0).getBombs().add(null);
        GameData.players.get(0).getBombs().add(null);
        GameData.players.get(0).getBombs().add(null);
        mainFrame.add(GameData.getBoard());
        mainFrame.setVisible(true);


    }


}
