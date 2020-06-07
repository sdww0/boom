package game.basement;

/**
 * 炸弹类
 * 含
 * 范围半径
 * 伤害
 * 多久爆炸
 * @author njdnhh
 */
public class Bomb {
    /**
     * 一旦放下便不可以再进行操作
     */
    private int radius;
    private int damage;
    private int time;

    public Bomb(int radius, int damage, int time) {
        this.radius = radius;
        this.damage = damage;
        this.time = time;
    }

    public int getRadius() {
        return radius;
    }

    public int getDamage() {
        return damage;
    }

    public int getTime() {
        return time;
    }
}
