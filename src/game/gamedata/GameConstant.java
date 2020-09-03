package game.gamedata;

import game.basement.Location;
import game.element.Bomb;
import game.element.Boom;
import game.element.Player;
import game.element.Walls;

import java.awt.*;
import java.util.logging.ConsoleHandler;

/**
 * 游戏常量
 * 如图片，恒量等
 *
 * @author njdnhh
 */
public class GameConstant {

    public static final int BOARD_SIZE = 640;
    public static final int SQUARE_AMOUNT = 16;
    public static final int SQUARE_SIZE = BOARD_SIZE/SQUARE_AMOUNT;
    public static final int MAX_SPEED = 5;

    public static final int PLAYER_BOOM_RADIUS_TYPE = 0;
    public static final int PLAYER_LIFE_TYPE = 1;
    public static final int PLAYER_BOMB_AMOUNT_TYPE = 2;
    public static final int PLAYER_SPEED = 3;


    public static final Color BOARD_COLOR1 = new Color(119, 100, 188);
    public static final Color BOARD_COLOR2 = new Color(0,0,0);

    public static final int FIRST_PLAYER = 1;
    public static final int SECOND_PLAYER = 2;
    public static final int THIRD_PLAYER = 3;
    public static final int FOURTH_PLAYER = 4;

    public static final Location FIRST_POSITION = new Location(0,0);
    public static final Location SECOND_POSITION = new Location(15,0);
    public static final Location THIRD_POSITION = new Location(0,15);
    public static final Location FOURTH_POSITION = new Location(15,15);

    public static final String BOOM_FILE_LOCATION = Boom.class.getName();
    public static final String BOMB_FILE_LOCATION = Bomb.class.getName();
    public static final String PLAYER_FILE_LOCATION = Player.class.getName();
    public static final String WALLS_FILE_LOCATION = Walls.class.getName();

    public static final int IMAGE_UP = 1;
    public static final int IMAGE_DOWN = 0;
    public static final int IMAGE_LEFT = 3;
    public static final int IMAGE_RIGHT = 2;



    public static final int BOMB_SECONDS = 4 ;

}



