package game.basement;

import game.gamedata.GameConstant;
import game.gamedata.GameData;

import java.util.Objects;

/**
 * 虚拟化位置
 * @author njdnhh
 */
public class Location {

    private int x;
    private int y;

    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 3;
    public static final int RIGHT = 4;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Location location = (Location) o;
        return x == location.x &&
                y == location.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    @Override
    public String toString() {
        return "Location{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }

    @Override
    public Location clone(){
        return new Location(this.x,this.y);
    }


}
