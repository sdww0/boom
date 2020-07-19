package game.basement;

import game.gamedata.GameConstant;
import game.gamedata.GameData;

/**
 * 虚拟化位置
 * @author njdnhh
 */
public class Location {

    private int x;
    private int y;

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public static TrueLocation changeToTrueLocation(Location location){
        return new TrueLocation(GameConstant.SQUARE_SIZE*location.getX(),GameConstant.SQUARE_SIZE*location.getY());
    }

    public TrueLocation changeToTrueLocation(){
        return new TrueLocation(GameConstant.SQUARE_SIZE*x,GameConstant.SQUARE_SIZE*y);
    }
}
