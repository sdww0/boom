package game.basement;

import game.gamedata.GameConstant;
import game.gamedata.GameData;

import java.util.Objects;

/**
 * 显示在面板上面真正的位置
 * @author njdnhh
 */
public class TrueLocation {

    private int x;
    private int y;

    public TrueLocation(int x,int y){
        this.x = x;
        this.y = y;
    }

    public TrueLocation(Location location){
        this.x = location.getX()*40;
        this.y = location.getY()*40;
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

    public Location changeToVirtualLocation(){
        return new Location(x/ GameConstant.SQUARE_SIZE,y/GameConstant.SQUARE_SIZE);
    }

    public static Location changeToVirtualLocation(TrueLocation trueLocation){
        return new Location(Math.round(trueLocation.getX()/ (float)GameConstant.SQUARE_SIZE),Math.round(trueLocation.getY()/ (float)GameConstant.SQUARE_SIZE));

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrueLocation location = (TrueLocation) o;
        return x == location.x &&
                y == location.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }

    public TrueLocation clone(){
        return new TrueLocation(this.x,this.y);
    }

    /**
     * 带有方向的变换
     * 如方向为x方向+1
     * 则会返回一个Math。floor的数值
     * @param direction 方向
     * @return 位置
     */
    public Location changeToVirtualLocationWithDirection(int direction){
        if(direction==Location.UP||direction==Location.LEFT){
            return new Location((int)Math.ceil(this.getX() /(double)GameConstant.SQUARE_SIZE),
                    (int)Math.ceil(this.getY() /(double)GameConstant.SQUARE_SIZE));
        }else if(direction==Location.DOWN||direction==Location.RIGHT){
            return new Location((int)Math.floor(this.getX() /(double)GameConstant.SQUARE_SIZE),
                    (int)Math.floor(this.getY() /(double)GameConstant.SQUARE_SIZE));
        }else{
            throw new IllegalArgumentException("error location:TrueLocation(changeToVirtualLocationWithDirection)");
        }


    }


}
