package game.gamedata;

import game.basement.Location;
import game.element.Player;
import game.gui.Board;
import game.map.MapList;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 *
 * 游戏数据库
 * 地图
 * 进行时间
 * 玩家数量
 * 待添加...
 * @author njdnhh
 */
public class GameData {

    private static int[][] map ;

    private static Board board = null;
    private static ThreadPoolExecutor bombControlPool = new ThreadPoolExecutor(20,40,
            GameConstant.BOMB_SECONDS+1, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(20));

    public static ArrayList<Player> players = new ArrayList<>();
    public static LinkedList<Location> bombExplodedLocation = new LinkedList<>();

    public static int playersLife = 5;

    public static int[][] getMap() {
        return map;
    }

    public static Board getBoard() {
        return board;
    }

    public static ThreadPoolExecutor getBombControlPool() {
        return bombControlPool;
    }

    public static void setMap(int[][] map) {
        GameData.map = map;
    }

    public static void init(){
        map = new int[GameConstant.SQUARE_AMOUNT][GameConstant.SQUARE_AMOUNT];
        for(int x = 0;x<GameConstant.SQUARE_AMOUNT;x++){
            for(int y = 0;y<GameConstant.SQUARE_AMOUNT;y++){
                map[x][y] = MapList.map1[y][x];
            }
        }

        board = new Board();


    }
}
