package game.element;

import game.basement.Location;

/**
 * 玩家元素
 * 含
 * 位置
 * 炸弹的范围
 * 炸弹的伤害
 * 血量
 *
 * @author njdnhh
 */
public class Player {

    private Location playerLocation;
    private Bomb bomb;
    private float life;

    public Player(Location playerLocation, Bomb bomb, float life) {
        this.playerLocation = playerLocation;
        this.bomb = bomb;
        this.life = life;
    }

    public Player(Bomb bomb, float life) {
        this.bomb = bomb;
        this.life = life;
    }

    public Location getPlayerLocation() {
        return playerLocation;
    }

    public void setPlayerLocation(Location playerLocation) {
        this.playerLocation = playerLocation;
    }

    public Bomb getBomb() {
        return bomb;
    }

    public void setBomb(Bomb bomb) {
        this.bomb = bomb;
    }

    public float getLife() {
        return life;
    }

    public void setLife(float life) {
        this.life = life;
    }
}
