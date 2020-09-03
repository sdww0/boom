package game.element;

import game.basement.Location;
import game.basement.TrueLocation;
import game.basement.UsefulFunction;
import game.gamedata.GameConstant;
import game.gamedata.GameData;
import game.image.ImageReader;
import game.robot.RobotController;

import java.util.LinkedList;

/**机器人控制类
 * @author njdnhh
 */
public class Robot extends Player{

    RobotController robotController;

    public Robot(int whichPlayer, TrueLocation playerLocation, Bomb bomb) {
        super(whichPlayer, playerLocation, bomb);
    }

    @Override
    public void init(){
        super.init();
        robotController = new RobotController(this);
        robotController.init();
    }



    public boolean xLocationPlus() {
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
                if(!setRobotLocation(trueLocation, Location.RIGHT)){
                    return false;
                }
                if (super.getVirtualLocation().getX() + 1 >= GameConstant.SQUARE_AMOUNT ||
                        GameData.getMap()[super.getVirtualLocation().getX() + 1][super.getVirtualLocation().getY()] != ElementType.NULL_NUMBER) {
                    break;
                }
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean xLocationMinus() {
        int x = super.getVirtualLocation().getX();
        int targetX = super.getVirtualLocation().getX() - 1;
        super.setCurrentImageIcon(ImageReader.ALL_PLAYER[super.getWhichPlayer() - 1][GameConstant.IMAGE_LEFT]);
        super.getPlayerLocation().setY(super.getVirtualLocation().changeToTrueLocation().getY());
        try {
            while (x - super.getVirtualLocation().getX() == 0) {
                TrueLocation trueLocation = new TrueLocation(super.getPlayerLocation().getX() - super.getSpeed(), super.getPlayerLocation().getY());
            /*
            将机器人的目标位置根据方向转换后再转化成标准以防越出界
             */
                Location temp = trueLocation.changeToVirtualLocationWithDirection(Location.LEFT);
                if (targetX == temp.getX()) {
                    trueLocation.setX(temp.changeToTrueLocation().getX());
                }
                if(!setRobotLocation(trueLocation, Location.LEFT)) {
                    return false;
                }
                if (super.getVirtualLocation().getX() - 1 < 0 ||
                        GameData.getMap()[super.getVirtualLocation().getX() - 1][super.getVirtualLocation().getY()] != ElementType.NULL_NUMBER) {
                    break;
                }
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    public boolean yLocationPlus(){
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
            if(!setRobotLocation(trueLocation,Location.DOWN)){
                return false;
            }

            if(super.getVirtualLocation().getY()+1>=GameConstant.SQUARE_AMOUNT||
                    GameData.getMap()[super.getVirtualLocation().getX()][super.getVirtualLocation().getY()+1]!=ElementType.NULL_NUMBER){
                break;
            }
            Thread.sleep(10);
        }
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
        return true;
    }

    public boolean yLocationMinus(){
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
                if(!setRobotLocation(trueLocation, Location.UP)){
                    return false;
                }
                if (super.getVirtualLocation().getY() - 1 < 0 ||
                        GameData.getMap()[super.getVirtualLocation().getX()][super.getVirtualLocation().getY() - 1] != ElementType.NULL_NUMBER) {
                    break;
                }
                Thread.sleep(10);
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return true;
    }

    /**
     * 将机器人移动到目标位置，有严格要求，x和y轴仅有一个有变化或者都无变化
     * 移动一次就返回重新进行判断
     * @param location 目标位置
     * @return 布尔值，true代表正常运作，false代表需要重新判断
     */
    public boolean moveRobotToPosition(Location location){
        int xChange = location.getX()-getVirtualLocation().getX();
        int yChange = location.getY()-getVirtualLocation().getY();
        if(xChange==0&&yChange==0){
            return true;
        }
        if((xChange!=0&&yChange!=0)){
            throw new IllegalArgumentException("error Location RobotController(moveRobotToPosition)");
        }
        if(xChange<0){
            if(!xLocationMinus()){
                return false;
            }
        }
        else if(xChange>0){
            if(!xLocationPlus()){
                return false;
            }
        }
        else if(yChange<0){
            if(!yLocationMinus()){
                return false;
            }
        }
        else if(yChange>0){
            if(!yLocationPlus()){
                return false;
            }
        }
        return true;
    }

    /**
     * 设定机器人的位置
     * @param location 目标位置
     * @param direction 方向
     * @return true代表运行正常，false代表有阻断
     */
    private boolean setRobotLocation(TrueLocation location,int direction) {
        TrueLocation finalLocation = super.showAnimation(super.getPlayerLocation(), location);
        super.setLastLocation( super.getVirtualLocation().clone());
        super.setPlayerLocation(finalLocation.clone());
        super.setVirtualLocation(super.getPlayerLocation().changeToVirtualLocationWithDirection(direction));
        if (!super.getLastLocation().equals(super.getVirtualLocation())) {
            UsefulFunction.setPlayer(this, super.getVirtualLocation());
            UsefulFunction.removePlayer(this, super.getLastLocation());
        }
        setLocation(finalLocation.getX(), finalLocation.getY());
        if(location.equals(finalLocation)){
            return true;
        }else{
            return false;
        }

    }
}

