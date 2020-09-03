package game.robot;

import game.basement.Location;
import game.element.Bomb;
import game.element.ElementType;
import game.gamedata.GameConstant;
import game.gamedata.GameData;


import java.util.HashSet;
import java.util.LinkedList;

import static game.basement.Location.UP;
import static game.basement.Location.DOWN;
import static game.basement.Location.LEFT;
import static game.basement.Location.RIGHT;


/**
 * 机器人移动运算算法
 * 根据机器人位置为上下左右四个方位进行估值
 *
 * @author njdnhh
 */
public class RobotAndLocation {
    private static int UNBREAKABLE_WALL_VALUE = 0;
    private static int BREAKABLE_WALL_VALUE = 8;
    private static int NULL_VALUE = 1;
    private static int BOOM_VALUE = 0;
    private static int PLAYER_VALUE = 3;
    private static int BOMB_VALUE = Integer.MIN_VALUE;
    private static int ITEM_VALUE = 30;

    /**
     * 对输入位置进行估值
     *
     * @param location 选定位置
     * @return 位置
     */
    public static Location update(Location location) {
        LinkedList<Location> locations = new LinkedList<>();
        LinkedList<Integer> values = new LinkedList<>();
        int x;
        int y;
        int direction = UP;
        /*
         * 前置遍历
         * 检测上下左右是否有炸弹并且获取该位置炸弹爆炸范围建立对应的位置哈希
         */
        HashSet<Location> notLocate = new HashSet<>();
        while (true) {
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
                    break;
                default:
                    throw new IllegalArgumentException("error Location:RobotAndLocation.java(update)");
            }
            boolean hasBomb = false;
            boolean canBreak = false;
            while (true) {
                x += xChange;
                y += yChange;
                if (notLocate.contains(new Location(x, y))) {
                    continue;
                }
                if (y < 0 || y >= GameConstant.SQUARE_AMOUNT || x < 0 || x >= GameConstant.SQUARE_AMOUNT) {
                    break;
                }
                switch (GameData.getBoard().getSquare()[x][y].getElementType()) {
                    case BOMB:
                        hasBomb = true;
                        break;
                    case UNBREAKABLE_WALL:
                    case BREAKABLE_WALL:
                        canBreak = true;
                    default:
                        break;
                }
                if (canBreak) {
                    break;
                }
                /*
                是否有炸弹
                如果有则对反方向进行遍历，在notLocate中添加遍历位置直到逃离爆炸范围
                 */
                if (hasBomb) {
                    Object bomb =  GameData.getBoard().getSquare()[x][y].getOrSetElement(true,null);
                    int bombSize = 0;
                    if (bomb!=null&&bomb.getClass().getName().equals(GameConstant.BOMB_FILE_LOCATION)) {
                        //w未来要修改，这样效率低下
                        try {
                            bombSize = ((Bomb) bomb).getRadius();
                        }catch (Exception ignore){
                            hasBomb = false;
                            continue;
                        }
                        GameData.getBoard().getSquare()[x][y].canSetElement = true;
                    }else{
                        hasBomb = false;
                        GameData.getBoard().getSquare()[x][y].canSetElement = true;
                        x -= xChange;
                        y -= yChange;
                        continue;
                    }
                    notLocate.add(new Location(x, y));
                    for (int n = 0; n < bombSize; n++) {
                        x -= xChange;
                        y -= yChange;
                        notLocate.add(new Location(x, y));
                        if (y < 0 || y >= GameConstant.SQUARE_AMOUNT || x < 0 || x >= GameConstant.SQUARE_AMOUNT) {
                            break;
                        }
                        switch (GameData.getBoard().getSquare()[x][y].getElementType()) {
                            case BOMB:
                            case UNBREAKABLE_WALL:
                            case BREAKABLE_WALL:
                                canBreak = true;
                            default:
                                break;
                        }
                        if (canBreak) {
                            break;
                        }
                    }
                    break;
                }
            }
            if (direction == RIGHT) {
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
                locations.add(currentLocation.clone());
                values.add(value);
            }
            switch (direction) {
                case UP:
                    direction = DOWN;
                    break;
                case DOWN:
                    direction = LEFT;
                    break;
                case LEFT:
                    direction = RIGHT;
                    break;
                case RIGHT:
                    direction = UP;
                    break;
                default:
                    throw new IllegalArgumentException("error Location:RobotAndLocation.java(update)");
            }
        } while (direction != UP);

        if (locations.size() == 0) {
            Location location1 = noPlaceToGoSituation(notLocate, location);
            if (location1 == null) {
                return location;
            } else {
                return location1;
            }
        }

        return findMaxDistance(locations, values, location);
    }

    /**
     * 计算位置的估值
     * @param direction 方向
     * @param location 位置
     * @return 估值
     */
    private static Integer valueOfLocation(int direction, Location location) {
        int value = 0;
        int xChange = 0, yChange = 0;
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
                throw new IllegalArgumentException("error Location:RobotAndLocation.java(valueOfDirection)");
        }
        int x = location.getX() + xChange;
        int y = location.getY() + yChange;
        if (!(y < 0 || y >= GameConstant.SQUARE_AMOUNT || x < 0 || x >= GameConstant.SQUARE_AMOUNT)) {
            switch (ElementType.changeNumberToEnum(GameData.getMap()[x][y])) {
                case UNBREAKABLE_WALL:
                    value = UNBREAKABLE_WALL_VALUE;
                    break;
                case BREAKABLE_WALL:
                    value = BREAKABLE_WALL_VALUE;
                    break;
                default:
                    break;
            }
        }
        if (direction == UP || direction == DOWN) {
            return getValueOfDirection(LEFT, location) + getValueOfDirection(RIGHT, location) + value;
        } else {
            return getValueOfDirection(UP, location) + getValueOfDirection(DOWN, location) + value;
        }
    }

    /**
     * 获取目标方向的估值
     * @param direction 方向
     * @param location 位置
     * @return 估值
     */
    private static Integer getValueOfDirection(int direction, Location location) {
        int x = location.getX();
        int y = location.getY();
        int xChange = 0, yChange = 0;
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
                throw new IllegalArgumentException("error Location:RobotAndLocation.java(getValueOfDirection)");
        }
        int value = 0;
        value += changeMapNumberToValue(GameData.getMap()[x][y]);
        while (true) {
            x += xChange;
            y += yChange;
            if (y < 0 || y >= GameConstant.SQUARE_AMOUNT || x < 0 || x >= GameConstant.SQUARE_AMOUNT) {
                break;
            }
            if (GameData.getMap()[x][y] == ElementType.BREAKABLE_WALL_NUMBER) {
                value += BREAKABLE_WALL_VALUE;
                break;
            }
            if (GameData.getMap()[x][y] == ElementType.UNBREAKABLE_WALL_NUMBER) {
                value += UNBREAKABLE_WALL_VALUE;
                break;
            }
            if (GameData.getMap()[x][y] == ElementType.BOOM_NUMBER) {
                value += BOOM_VALUE;
                break;
            }
            if (GameData.getMap()[x][y] == ElementType.PLAYER_NUMBER) {
                value+= PLAYER_VALUE;
            } else if (GameData.getMap()[x][y] == ElementType.NULL_NUMBER) {
                value += NULL_VALUE;
            } else if (GameData.getMap()[x][y] == ElementType.ITEM_NUMBER) {
                value += ITEM_VALUE;
            } else if (GameData.getMap()[x][y] == ElementType.BOMB_NUMBER) {
                /*
                如果是炸弹则原路返回
                 */
                Object bomb =  GameData.getBoard().getSquare()[x][y].getOrSetElement(true,null);
                int bombSize = 0;
                if (bomb!=null&&bomb.getClass().getName().equals(GameConstant.BOMB_FILE_LOCATION)) {
                    try {
                        bombSize = ((Bomb) bomb).getRadius();
                    }catch (Exception ignore){
                        continue;
                    }
                }else{
                    x -= xChange;
                    y -= yChange;
                    continue;
                }

                for (int n = 0; n < bombSize; n++) {
                    x -= xChange;
                    y -= yChange;
                    if (x == location.getX() && y == location.getY()) {
                        return BOMB_VALUE;
                    }
                    if (GameData.getMap()[x][y] == ElementType.PLAYER_NUMBER) {
                        value -= PLAYER_VALUE;
                    } else if (GameData.getMap()[x][y] == ElementType.NULL_NUMBER) {
                        value -= NULL_VALUE;
                    } else if (GameData.getMap()[x][y] == ElementType.ITEM_NUMBER) {
                        value -= ITEM_VALUE;
                    }

                }
                return value;
            }

        }
        return value;

    }

    /**
     * 寻找最远的路
     * @param locations 位置list
     * @param values 估值list
     * @param currentLocation 当前位置
     * @return 目标位置
     */
    private static Location findMaxDistance(LinkedList<Location> locations, LinkedList<Integer> values, Location currentLocation) {
        int max = Integer.MIN_VALUE;
        for (int n : values) {
            if (n > max) {
                max = n;
            }
        }
        for (int n = 0; n != values.size(); ) {
            if (values.get(n) != max) {
                locations.remove(n);
                values.remove(n);
            } else {
                values.set(n, Math.abs(currentLocation.getX() - locations.get(n).getX()) + Math.abs(currentLocation.getY() - locations.get(n).getY()));
                n++;
            }
        }
        max = 0;
        for (int n = 1; n < locations.size(); n++) {
            if (values.get(n) > values.get(max)) {
                max = n;
            }
        }
        if (locations.size() == 0) {

        }
        return locations.get(max);
    }

    private static int changeMapNumberToValue(int mapNumber) {
        switch (ElementType.changeNumberToEnum(mapNumber)) {
            case NULL:
                return NULL_VALUE;
            case BREAKABLE_WALL:
                return BREAKABLE_WALL_VALUE;
            case UNBREAKABLE_WALL:
                return UNBREAKABLE_WALL_VALUE;
            case BOMB:
                return BOMB_VALUE;
            case PLAYER:
                return PLAYER_VALUE;
            case BOOM:
                return BOOM_VALUE;
            case ITEM:
                return ITEM_VALUE;
            default:
                throw new IllegalArgumentException("wrong number location:RobotAndLocation(changeMapNumberToValue)");
        }

    }

    /**
     * 没有任何地方走
     * @param notLocate 不能走的地方
     * @param currentLocation 目前位置
     * @return 目标为至
     */
    private static Location noPlaceToGoSituation(HashSet<Location> notLocate, Location currentLocation) {
        int[] values = new int[notLocate.size()];
        int n = 0;
        for (Location location : notLocate) {
            int direction = 0;
            if (location.getX() - currentLocation.getX() != 0) {
                if (location.getX() - currentLocation.getX() < 0) {
                    direction = LEFT;
                } else {
                    direction = RIGHT;
                }
            } else {
                if (location.getY() - currentLocation.getY() < 0) {
                    direction = UP;
                } else {
                    direction = DOWN;
                }
            }
            if (location.getX() < 0 || location.getX() >= GameConstant.SQUARE_AMOUNT ||
                    location.getY() < 0 || location.getY() >= GameConstant.SQUARE_AMOUNT) {
                continue;
            }
            values[n++] = valueOfLocation(direction, location);
        }

        n = 1;
        int max = 0;
        while (n < values.length) {
            if (values[n] > values[max]) {
                max = n;
            }
            n++;
        }
        n = 0;
        for (Location location : notLocate) {
            if (n == max) {
                return location;
            }
            n++;
        }
        return null;
    }

}
