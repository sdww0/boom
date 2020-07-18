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
     */
    UNBREAKABLE_WALL,
    NULL,
    BREAKABLE_WALL,
    BOMB,
    PLAYER,
    BOOM;
}
