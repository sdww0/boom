package game.basement;

import game.gamedata.GameConstant;
import game.gamedata.GameData;

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
        return new Location(trueLocation.getX()/ GameConstant.SQUARE_SIZE,trueLocation.getY()/GameConstant.SQUARE_SIZE);
    }

}
