package game.gamedata;

import game.basement.Location;
import game.element.Player;
import game.gui.Board;
import game.gui.RightMenu;
import game.map.MapList;

import java.util.LinkedList;
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
    private static RightMenu rightMenu = null;
    private static ThreadPoolExecutor bombControlThreadPool = new ThreadPoolExecutor(20,40,
            GameConstant.BOMB_SECONDS+1, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(20));
    private static ThreadPoolExecutor GameExecutePool = new ThreadPoolExecutor(20,40,
            GameConstant.BOMB_SECONDS+1, TimeUnit.SECONDS,new ArrayBlockingQueue<Runnable>(20));

    /**
     * 一号玩家
     * 二号玩家（如果无二号玩家则设置成null)
     */
    public static Player player1 = null;
    public static Player player2 = null;
    public static volatile LinkedList<Location> bombExplodedLocation = new LinkedList<>();


    public static int playersLife = 5;
    public static int playersDefaultSpeed = 10;

    public static int[][] getMap() {
        return map;
    }

    public static Board getBoard() {
        return board;
    }

    public static ThreadPoolExecutor getBombControlThreadPool() {
        return bombControlThreadPool;
    }

    public static void setMap(int[][] map) {
        GameData.map = map;
    }

    public static RightMenu getRightMenu() {
        return rightMenu;
    }

    public static void init(){
        map = new int[GameConstant.SQUARE_AMOUNT][GameConstant.SQUARE_AMOUNT];
        for(int x = 0;x<GameConstant.SQUARE_AMOUNT;x++){
            for(int y = 0;y<GameConstant.SQUARE_AMOUNT;y++){
                map[x][y] = MapList.map1[y][x];
            }
        }
        rightMenu = new RightMenu();
        board = new Board();
        playersLife = 5;
        player1 = null;
        player2 = null;

    }

    public static ThreadPoolExecutor getGameExecutePool() {
        return GameExecutePool;
    }
}
