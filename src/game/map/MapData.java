package game.map;

import game.basement.Location;
import game.element.Player;
import game.gamedata.GameConstant;
import game.gamedata.GameData;
import game.gui.Board;
import game.map.gui.MapEditorBoard;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * 地图数据
 * @author njdnhh
 */
public class MapData {
    private static int[][] map ;
    public static boolean canBreak;

    private static MapEditorBoard board = null;

    public static int[][] getMap() {
        return map;
    }

    public static MapEditorBoard getBoard() {
        return board;
    }

    public static void setMap(int[][] map) {
        MapData.map = map;
    }

    public static void init(){
        map = new int[GameConstant.SQUARE_AMOUNT][GameConstant.SQUARE_AMOUNT];
        for(int x = 0;x<GameConstant.SQUARE_AMOUNT;x++){
            for(int y = 0;y<GameConstant.SQUARE_AMOUNT;y++){
                map[x][y] = 0;
            }
        }
        board = new MapEditorBoard();
    }

}
