package game.robot;

import game.basement.Location;
import game.element.Bomb;
import game.element.ElementType;
import game.element.Item;
import game.gamedata.GameConstant;
import game.gamedata.GameData;

import java.util.HashMap;
import java.util.HashSet;

/**
 * 机器人移动运算算法
 * 根据机器人位置为上下左右四个方位进行估值
 *
 * @author njdnhh
 */
public class RobotAndLocation {
    private static  int UNBREAKABLE_WALL_VALUE = 0;
    private static  int BREAKABLE_WALL_VALUE = 2;
    private static  int NULL_VALUE = 2;
    private static  int BOOM_VALUE = 0;
    private static  int PLAYER_VALUE = 1;
    private static  int BOMB_VALUE = Integer.MIN_VALUE;
    private static  int ITEM_VALUE = 4;

    private static final int UP = 1;
    private static final int DOWN = 2;
    private static final int LEFT = 3;
    private static final int RIGHT = 4;



    /**
     * 对输入位置进行估值
     * @param location 选定位置
     * @return 哈希表映射代表每个位置的估值
     */
    public static HashMap<Location,Integer> update(Location location){
        HashMap<Location,Integer> values = new HashMap<>(2* GameConstant.SQUARE_AMOUNT);
        int x = location.getX();
        int y = location.getY();
        int direction = UP;
        /*
         * 前置遍历
         * 检测上下左右是否有炸弹并且获取该位置炸弹爆炸范围建立对应的位置哈希
         */
        HashSet<Location> notLocate = new HashSet<>();
        while(true){
            x = location.getX();
            y = location.getY();
            int xChange = 0,yChange = 0;
            switch (direction){
                case UP:yChange = -1;
                direction = DOWN;
                break;
                case DOWN:yChange = 1;
                    direction = LEFT;
                break;
                case LEFT:xChange = -1;
                    direction = RIGHT;
                break;
                case RIGHT:xChange = 1;
                break;
                default:throw new IllegalArgumentException("error Location:RobotAndLocation.java(update)");
            }
            boolean hasBomb = false;
            boolean canBreak = false;
            while(true){
                x+=xChange;
                y+=yChange;
                if(notLocate.contains(new Location(x,y))){
                    continue;
                }
                if(y<0||y>=GameConstant.SQUARE_AMOUNT||x<0||x>=GameConstant.SQUARE_AMOUNT){
                    break;
                }
                switch (GameData.getBoard().getSquare()[x][y].getElementType()){
                    case BOMB:hasBomb = true;break;
                    case UNBREAKABLE_WALL:
                    case BREAKABLE_WALL:canBreak =true;
                    default:break;
                }
                if(canBreak){
                    break;
                }
                /*
                是否有炸弹
                如果有则对反方向进行遍历，在notLocate中添加遍历位置直到逃离爆炸范围
                 */
                if(hasBomb){
                    int bombSize = ((Bomb) GameData.getBoard().getSquare()[x][y].getElement()).getRadius();
                    notLocate.add(new Location(x,y));
                    for (int n = 0; n < bombSize; n++) {
                        x-=xChange;
                        y-=yChange;
                        notLocate.add(new Location(x,y));
                        if(y<0||y>=GameConstant.SQUARE_AMOUNT||x<0||x>=GameConstant.SQUARE_AMOUNT){
                            break;
                        }
                        switch (GameData.getBoard().getSquare()[x][y].getElementType()){
                            case BOMB:
                            case UNBREAKABLE_WALL:
                            case BREAKABLE_WALL:canBreak =true;
                            default:break;
                        }
                        if(canBreak){
                            break;
                        }
                    }
                    break;
                }
            }
            if(direction==RIGHT){
                break;
            }
        }
        direction = UP;
        do {
            x = location.getX();
            y = location.getY();
            int xChange = 0, yChange = 0;
            switch (direction) {
                case UP:
                    yChange = -1;
                    direction = DOWN;
                    break;
                case DOWN:
                    yChange = 1;
                    direction = LEFT;
                    break;
                case LEFT:
                    xChange = -1;
                    direction = RIGHT;
                    break;
                case RIGHT:
                    xChange = 1;
                    direction = UP;
                    break;
                default:
                    throw new IllegalArgumentException("error Location:RobotAndLocation.java(update)");
            }
            boolean canBreak = false;
            Location currentLocation = new Location(x, y);
            while (true) {
                int value = 0;
                x += xChange;
                y += yChange;
                currentLocation.setX(x);
                currentLocation.setY(y);

                if (notLocate.contains(currentLocation) ||
                        y < 0 || y >= GameConstant.SQUARE_AMOUNT || x < 0 || x >= GameConstant.SQUARE_AMOUNT) {
                    break;
                }
                switch (ElementType.changeNumberToEnum(GameData.getMap()[x][y])) {
                    case UNBREAKABLE_WALL:
                        canBreak = true;
                        break;
                    case BREAKABLE_WALL:
                        canBreak = true;
                        break;
                    default:
                        break;
                }
                if (canBreak) {
                    break;
                }

                value += valueOfLocation(direction, currentLocation);
                values.put(new Location(currentLocation.getX(),currentLocation.getY()), value);

            }

        } while (direction != UP);
        return values;
    }

    private static Integer valueOfLocation(int direction,Location location){
        int value = 0;
        int xChange = 0,yChange = 0;
        switch (direction){
            case UP:yChange = -1;break;
            case DOWN:yChange = 1;break;
            case LEFT:xChange = -1;break;
            case RIGHT:xChange = 1;break;
            default:throw new IllegalArgumentException("error Location:RobotAndLocation.java(valueOfDirection)");
        }
        int x = location.getX()+xChange;
        int y = location.getY()+yChange;
        switch (ElementType.changeNumberToEnum(GameData.getMap()[x][y])) {
            case UNBREAKABLE_WALL:
                value =UNBREAKABLE_WALL_VALUE;
                break;
            case BREAKABLE_WALL:
                value = BREAKABLE_WALL_VALUE;
                break;
            default:
                break;
        }
        if(direction==UP||direction==DOWN){
            return getValueOfDirection(LEFT,location)+getValueOfDirection(RIGHT,location)+value;
        }else{
            return getValueOfDirection(UP,location)+getValueOfDirection(DOWN,location)+value;
        }
    }

    private static Integer getValueOfDirection(int direction,Location location){
        int x = location.getX();
        int y = location.getY();
        int xChange = 0,yChange = 0;
        switch (direction){
            case UP:yChange = -1;break;
            case DOWN:yChange = 1;break;
            case LEFT:xChange = -1;break;
            case RIGHT:xChange = 1;break;
            default:throw new IllegalArgumentException("error Location:RobotAndLocation.java(getValueOfDirection)");
        }
        int value = 0;
        while(true){
            x+=xChange;
            y+=yChange;
            if(y<0||y>=GameConstant.SQUARE_AMOUNT||x<0||x>=GameConstant.SQUARE_AMOUNT){
                break;
            }
            if(GameData.getMap()[x][y]== ElementType.BREAKABLE_WALL_NUMBER){
                value += BREAKABLE_WALL_VALUE;
                break;
            }
            if(GameData.getMap()[x][y]== ElementType.UNBREAKABLE_WALL_NUMBER){
                value += UNBREAKABLE_WALL_VALUE;
                break;
            }
            if(GameData.getMap()[x][y]== ElementType.BOOM_NUMBER){
                value += BOOM_VALUE;
                break;
            }
            if(GameData.getMap()[x][y]== ElementType.PLAYER_NUMBER){
                value += PLAYER_VALUE;
            } else if(GameData.getMap()[x][y]== ElementType.NULL_NUMBER){
                value += NULL_VALUE;
            } else if(GameData.getMap()[x][y]== ElementType.ITEM_NUMBER){
                value += ITEM_VALUE;
            }else if(GameData.getMap()[x][y]==ElementType.BOMB_NUMBER) {
                int bombSize = ((Bomb) GameData.getBoard().getSquare()[x][y].getElement()).getRadius();
                for (int n = 0; n < bombSize; n++) {
                    x-=xChange;
                    y-=yChange;
                    if (GameData.getMap()[x][y] == ElementType.PLAYER_NUMBER) {
                        value -= PLAYER_VALUE;
                    } else if (GameData.getMap()[x][y] == ElementType.NULL_NUMBER) {
                        value -= NULL_VALUE;
                    } else if (GameData.getMap()[x][y] == ElementType.ITEM_NUMBER) {
                        value -= ITEM_VALUE;
                        if (y == location.getY()) {
                            return BOMB_VALUE;
                        }
                    }

                }
            }

        }
        return value;

    }


}
