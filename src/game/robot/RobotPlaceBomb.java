package game.robot;

import game.basement.Location;
import game.element.ElementType;
import game.element.Robot;
import game.gamedata.GameConstant;
import game.gamedata.GameData;
import javafx.util.converter.LocalDateStringConverter;

import java.util.ArrayList;
import java.util.LinkedList;

import static game.basement.Location.*;
import static game.basement.Location.RIGHT;

/**
 * 机器人放置炸弹类
 * 暂时废弃
 * @author njdnhh
 */
public class RobotPlaceBomb {

    public static boolean placeBomb(Location currentLocation) {
        int x;
        int y;
        int xChange = 0, yChange = 0;
        int direction = UP;
        while (direction != RIGHT) {
            x = currentLocation.getX();
            y = currentLocation.getY();
            switch (direction) {
                case UP:
                    yChange = -1;
                    break;
                case DOWN:
                    yChange = 1;
                    break;
                case LEFT:
                    xChange = -1;
                    break;
                case RIGHT:
                    xChange = 1;
                    break;
                default:
                    throw new IllegalArgumentException("error Location:RobotPlaceBomb.java(placeBomb)");
            }
            x += xChange;
            y += yChange;
            if (y < 0 || y >= GameConstant.SQUARE_AMOUNT || x < 0 || x >= GameConstant.SQUARE_AMOUNT) {
                direction = changeDirection(direction);
                continue;
            }
            int value = GameData.getMap()[x][y];
            if (value == ElementType.BREAKABLE_WALL_NUMBER || value == ElementType.PLAYER_NUMBER) {
                return true;
            }
            x += xChange;
            y += yChange;
            if (y < 0 || y >= GameConstant.SQUARE_AMOUNT || x < 0 || x >= GameConstant.SQUARE_AMOUNT) {
                direction = changeDirection(direction);
                continue;
            }
            value = GameData.getMap()[x][y];
            if (value == ElementType.PLAYER_NUMBER) {
                return true;
            }
            direction = changeDirection(direction);

        }

    return false;
    }

    private static int changeDirection(int direction){
        switch (direction) {
            case UP:
                return DOWN;
            case DOWN:
                return LEFT;
            case LEFT:
                return RIGHT;
            case RIGHT:
                return UP;
            default:
                throw new IllegalArgumentException("error Location:RobotPlaceBomb.java(changeDirection)");
        }
    }

}
