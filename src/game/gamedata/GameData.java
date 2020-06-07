package game.gamedata;

import game.element.Player;
import org.omg.CORBA.PUBLIC_MEMBER;

import java.util.ArrayList;

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

    private static int[][] map = {

    };

    public static ArrayList<Player> players = new ArrayList<>();


    public static int[][] getMap() {
        return map;
    }

}
