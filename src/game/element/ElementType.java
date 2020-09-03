package game.element;

/**
 * 元素种类
 * @author njdnhh
 */

public enum ElementType {
    /**
     * 不可摧毁的墙壁
     * 无元素
     * 可以被摧毁的墙壁
     * 炸弹
     * 玩家
     * 爆炸效果
     * 道具
     */
    NULL,
    BREAKABLE_WALL,
    UNBREAKABLE_WALL,
    BOMB,
    PLAYER,
    BOOM,
    ITEM;

    public static final int NULL_NUMBER = 0;
    public static final int BREAKABLE_WALL_NUMBER = 1;
    public static final int UNBREAKABLE_WALL_NUMBER = 2;
    public static final int BOMB_NUMBER = 3;
    public static final int PLAYER_NUMBER = 4;
    public static final int BOOM_NUMBER = 5;
    public static final int ITEM_NUMBER = 6;

    /**
     * 将数值转化为Enum
     * @param number 数值
     * @return Enum
     */
    public static ElementType changeNumberToEnum(int number){
        switch (number){
            case NULL_NUMBER:return NULL;
            case BREAKABLE_WALL_NUMBER:return  BREAKABLE_WALL;
            case UNBREAKABLE_WALL_NUMBER:return UNBREAKABLE_WALL;
            case BOMB_NUMBER:return BOMB;
            case PLAYER_NUMBER:return PLAYER;
            case BOOM_NUMBER:return BOOM;
            case ITEM_NUMBER:return ITEM;
            default:throw new IllegalArgumentException("wrong number location:ElementType");
        }
    }
    /**
     * 将Enum转化为数值
     * @param elementType Enum
     * @return 数值
     */
    public static int changeEnumToNumber(ElementType elementType){
        return elementType.ordinal();
//        elementType.ordinal();
//        switch (elementType){
//            case NULL:return NULL_NUMBER;
//            case BREAKABLE_WALL:return  BREAKABLE_WALL_NUMBER;
//            case UNBREAKABLE_WALL:return UNBREAKABLE_WALL_NUMBER;
//            case BOMB:return BOMB_NUMBER;
//            case PLAYER:return PLAYER_NUMBER;
//            case BOOM:return BOOM_NUMBER;
//            case ITEM:return ITEM_NUMBER;
//            default:throw new IllegalArgumentException("wrong number location:ElementType");
//        }
    }


}
