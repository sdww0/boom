package game.element;

import game.basement.Location;
import game.basement.TrueLocation;
import game.basement.UsefulFunction;
import game.gamedata.GameConstant;
import game.gamedata.GameData;
import game.image.ImageReader;

import java.util.LinkedList;

/**机器人控制类
 * @author njdnhh
 */
public class Robot extends Player{

    public Robot(int whichPlayer, TrueLocation playerLocation, Bomb bomb) {
        super(whichPlayer, playerLocation, bomb);

    }

    public void xLocationPlus() {
        int x = super.getVirtualLocation().getX();
        int targetX = super.getVirtualLocation().getX() + 1;
        super.setCurrentImageIcon(ImageReader.ALL_PLAYER[super.getWhichPlayer() - 1][GameConstant.IMAGE_RIGHT]);
        super.getPlayerLocation().setY(super.getVirtualLocation().changeToTrueLocation().getY());
        try {
            while (x - super.getVirtualLocation().getX() == 0) {

                TrueLocation trueLocation = new TrueLocation(super.getPlayerLocation().getX() + super.getSpeed(), super.getPlayerLocation().getY());
            /*
            将机器人的目标位置根据方向转换后再转化成标准以防越出界
             */
                Location temp = trueLocation.changeToVirtualLocationWithDirection(Location.RIGHT);
                if (targetX == temp.getX()) {
                    trueLocation.setX(temp.changeToTrueLocation().getX());
                }
                setRobotLocation(trueLocation, Location.RIGHT);
                if (super.getVirtualLocation().getX() + 1 >= GameConstant.SQUARE_AMOUNT ||
                        GameData.getMap()[super.getVirtualLocation().getX() + 1][super.getVirtualLocation().getY()] != ElementType.NULL_NUMBER) {
                    break;
                }
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void xLocationMinus(){
        int x = super.getVirtualLocation().getX();
        int targetX = super.getVirtualLocation().getX()-1;
        super.setCurrentImageIcon(ImageReader.ALL_PLAYER[super.getWhichPlayer()-1][GameConstant.IMAGE_LEFT]);
        super.getPlayerLocation().setY(super.getVirtualLocation().changeToTrueLocation().getY());
        try{
        while(x-super.getVirtualLocation().getX()==0){
            TrueLocation trueLocation = new TrueLocation(super.getPlayerLocation().getX()-super.getSpeed(),super.getPlayerLocation().getY());
            /*
            将机器人的目标位置根据方向转换后再转化成标准以防越出界
             */
            Location temp = trueLocation.changeToVirtualLocationWithDirection(Location.LEFT);
            if(targetX==temp.getX()){
                trueLocation.setX(temp.changeToTrueLocation().getX());
            }
            setRobotLocation(trueLocation,Location.LEFT);
            if(super.getVirtualLocation().getX()-1<0||
                    GameData.getMap()[super.getVirtualLocation().getX()-1][super.getVirtualLocation().getY()]!=ElementType.NULL_NUMBER){
                break;
            }
            Thread.sleep(10);
        }
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    }

    public void yLocationPlus(){
        int y = super.getVirtualLocation().getY();
        int targetY = super.getVirtualLocation().getY()+1;
        super.setCurrentImageIcon(ImageReader.ALL_PLAYER[super.getWhichPlayer()-1][GameConstant.IMAGE_DOWN]);
        //不太安全，建议修改一下
        super.getPlayerLocation().setX(super.getVirtualLocation().changeToTrueLocation().getX());
        try{
        while(y-super.getVirtualLocation().getY()==0){
            TrueLocation trueLocation = new TrueLocation(super.getPlayerLocation().getX(),super.getPlayerLocation().getY()+super.getSpeed());
            /*
            将机器人的目标位置根据方向转换后再转化成标准以防越出界
             */
            Location temp = trueLocation.changeToVirtualLocationWithDirection(Location.DOWN);
            if(targetY==temp.getY()){
                trueLocation.setY(temp.changeToTrueLocation().getY());
            }
            setRobotLocation(trueLocation,Location.DOWN);
            if(super.getVirtualLocation().getY()+1>=GameConstant.SQUARE_AMOUNT||
                    GameData.getMap()[super.getVirtualLocation().getX()][super.getVirtualLocation().getY()+1]!=ElementType.NULL_NUMBER){
                break;
            }
            Thread.sleep(10);
        }
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    }

    public void yLocationMinus(){
        int y = super.getVirtualLocation().getY();
        int targetY = super.getVirtualLocation().getY()-1;
        super.setCurrentImageIcon(ImageReader.ALL_PLAYER[super.getWhichPlayer()-1][GameConstant.IMAGE_UP]);
        super.getPlayerLocation().setX(super.getVirtualLocation().changeToTrueLocation().getX());
        try {
            while (y - super.getVirtualLocation().getY() == 0) {
                TrueLocation trueLocation = new TrueLocation(super.getPlayerLocation().getX(), super.getPlayerLocation().getY() - super.getSpeed());
            /*
            将机器人的目标位置根据方向转换后再转化成标准以防越出界
             */
                Location temp = trueLocation.changeToVirtualLocationWithDirection(Location.UP);
                if (targetY == temp.getY()) {
                    trueLocation.setY(temp.changeToTrueLocation().getY());
                }
                setRobotLocation(trueLocation, Location.UP);
                if (super.getVirtualLocation().getY() - 1 < 0 ||
                        GameData.getMap()[super.getVirtualLocation().getX()][super.getVirtualLocation().getY() - 1] != ElementType.NULL_NUMBER) {
                    break;
                }
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 将机器人移动到目标位置，有严格要求，x和y轴有且仅有一个有变化
     * @param location 目标位置
     */
    public void moveRobotToPosition(Location location){
        int xChange = location.getX()-getVirtualLocation().getX();
        int yChange = location.getY()-getVirtualLocation().getY();
        if((xChange!=0&&yChange!=0)||(xChange==0&&yChange==0)){
            throw new IllegalArgumentException("error Location RobotController(moveRobotToPosition)");
        }
        while(xChange<0){
            xLocationMinus();
            xChange++;
        }
        while(xChange>0){
            xLocationPlus();
            xChange--;
        }
        while(yChange<0){
            yLocationMinus();
            yChange++;
        }
        while(yChange>0){
            yLocationPlus();
            yChange--;
        }
    }

    /**
     * 设定机器人的位置
     * @param location 目标位置
     * @param direction 方向
     */
    private void setRobotLocation(TrueLocation location,int direction) {
        TrueLocation finalLocation = super.showAnimation(super.getPlayerLocation(), location);
        super.setLastLocation( super.getVirtualLocation().clone());
        super.setPlayerLocation(finalLocation.clone());
        super.setVirtualLocation(super.getPlayerLocation().changeToVirtualLocationWithDirection(direction));
        if (!super.getLastLocation().equals(super.getVirtualLocation())) {
            UsefulFunction.setPlayer(this, super.getVirtualLocation());
            UsefulFunction.removePlayer(this, super.getLastLocation());
        }
        setLocation(finalLocation.getX(), finalLocation.getY());
    }
}

