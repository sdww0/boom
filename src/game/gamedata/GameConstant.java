package game.gamedata;

import game.element.Boom;

import java.awt.*;

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

    public static final int PLAYER_BOOM_RADIUS_TYPE = 0;
    public static final int PLAYER_LIFE_TYPE = 1;
    public static final int PLAYER_BOMB_AMOUNT_TYPE = 2;
    public static final int PLAYER_UNKNOWN_TYPE = 3;

    public static final Color BOARD_COLOR1 = new Color(119, 100, 188);
    public static final Color BOARD_COLOR2 = new Color(0,0,0);

    public static final int FIRST_PLAYER = 1;
    public static final int SECOND_PLAYER = 2;
    public static final int THIRD_PLAYER = 3;
    public static final int FOURTH_PLAYER = 4;

    public static final int BOMB_SECONDS = 4 ;

    public static final int UNBREAK_WALL = -1;

}



