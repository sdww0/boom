package game.basement;

import game.element.*;
import game.gamedata.GameData;
import game.gamedata.GameConstant;
import org.jetbrains.annotations.NotNull;

/**
 * 比较有用的函数
 * @author njdnhh
 */
public class UsefulFunction {
    /**
     * 在某个位置设定某个元素
     * PLAYER元素请见下面的函数
     * 同时修改board以及map
     * @param element 元素
     * @param location 设定元素所在位置
     */
    public static void setElementType(Object element,Location location){
        int number = -1;

        if(element!=null) {
            String n = element.getClass().getName();
            if(n.equals(GameConstant.BOOM_FILE_LOCATION)){
                number = ElementType.BOOM_NUMBER;
            }else if(n.equals(GameConstant.BOMB_FILE_LOCATION)){
                number = ElementType.BOMB_NUMBER;
            }else if(n.equals(GameConstant.WALLS_FILE_LOCATION)){
                Walls walls = (Walls)(element);
                if(walls.isCanBreak()){
                    number = ElementType.BREAKABLE_WALL_NUMBER;
                }else {
                    number = ElementType.UNBREAKABLE_WALL_NUMBER;
                }
            }
        }else{
            number = ElementType.NULL_NUMBER;
        }
        if(number==-1){
            throw new IllegalArgumentException("error Location:UsefulFunction");
        }

        GameData.getBoard().getSquare()[location.getX()][location.getY()].setElement(element);
        GameData.getMap()[location.getX()][location.getY()] =number;
    }
    /**
     * 设定某个位置的玩家信息
     * 同时修改board以及map
     * @param player 玩家
     * @param location 设定元素所在位置
     */
    public static void setPlayer(@NotNull Player player, Location location){
            GameData.getBoard().getSquare()[location.getX()][location.getY()].setPlayer(player);
            GameData.getMap()[location.getX()][location.getY()] = ElementType.PLAYER_NUMBER;
    }

    /**
     * 删除某个位置的玩家信息
     * 同时修改board以及map
     * @param player 玩家
     * @param location 设定元素所在位置
     */
    public static void removePlayer(@NotNull Player player, Location location){
        GameData.getBoard().getSquare()[location.getX()][location.getY()].removePlayer(player);
        GameData.getMap()[location.getX()][location.getY()] = ElementType.NULL_NUMBER;
    }

    /**
     * 设置某个位置的item
     * 同时修改board以及map
     * @param item item
     * @param location 设定元素所在位置
     */
    public static void setItem(Item item, Location location){
        GameData.getBoard().getSquare()[location.getX()][location.getY()].setItem(item);
        GameData.getMap()[location.getX()][location.getY()] = ElementType.ITEM_NUMBER;
    }


}
