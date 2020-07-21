package game.element;

import game.basement.Location;
import game.basement.TrueLocation;
import game.gamedata.GameConstant;
import game.gamedata.GameData;
import game.image.ImageReader;

import java.util.LinkedList;

public class Robot extends Player{


    public Robot(int whichPlayer, TrueLocation playerLocation, Bomb bomb) {
        super(whichPlayer, playerLocation, bomb);
    }

    public void xLocationPlus(){
        int x = super.getVirtualLocation().getX();
        super.setCurrentImageIcon(ImageReader.ALL_PLAYER[super.getWhichPlayer()-1][GameConstant.IMAGE_RIGHT]);
        while(x-super.getVirtualLocation().getX()==0){
            super.setPlayerLocation(new TrueLocation(super.getPlayerLocation().getX()+super.getSpeed(),super.getPlayerLocation().getY()));
            if(super.getVirtualLocation().getX()+1>=GameConstant.SQUARE_AMOUNT||
                    GameData.getMap()[super.getVirtualLocation().getX()+1][super.getVirtualLocation().getY()]!=ElementType.NULL_NUMBER){
                break;
            }
        }

    }

    public void xLocationMinus(){
        int x = super.getVirtualLocation().getX();
        super.setCurrentImageIcon(ImageReader.ALL_PLAYER[super.getWhichPlayer()-1][GameConstant.IMAGE_LEFT]);
        while(x-super.getVirtualLocation().getX()==0){
            super.setPlayerLocation(new TrueLocation(super.getPlayerLocation().getX()-super.getSpeed(),super.getPlayerLocation().getY()));
            if(super.getVirtualLocation().getX()-1<0||
                    GameData.getMap()[super.getVirtualLocation().getX()-1][super.getVirtualLocation().getY()]!=ElementType.NULL_NUMBER){
                break;
            }
        }

    }

    public void yLocationPlus(){
        int x = super.getVirtualLocation().getY();
        super.setCurrentImageIcon(ImageReader.ALL_PLAYER[super.getWhichPlayer()-1][GameConstant.IMAGE_DOWN]);
        while(x-super.getVirtualLocation().getY()==0){
            super.setPlayerLocation(new TrueLocation(super.getPlayerLocation().getX(),super.getPlayerLocation().getY()+super.getSpeed()));
            if(super.getVirtualLocation().getY()+1>=GameConstant.SQUARE_AMOUNT||
                    GameData.getMap()[super.getVirtualLocation().getX()][super.getVirtualLocation().getY()+1]!=ElementType.NULL_NUMBER){
                break;
            }
        }

    }

    public void yLocationMinus(){
        int x = super.getVirtualLocation().getY();
        super.setCurrentImageIcon(ImageReader.ALL_PLAYER[super.getWhichPlayer()-1][GameConstant.IMAGE_UP]);
        while(x-super.getVirtualLocation().getY()==0){
            super.setPlayerLocation(new TrueLocation(super.getPlayerLocation().getX(),super.getPlayerLocation().getY()-super.getSpeed()));
            if(super.getVirtualLocation().getY()-1<0||
                    GameData.getMap()[super.getVirtualLocation().getX()][super.getVirtualLocation().getY()-1]!=ElementType.NULL_NUMBER){
                break;
            }
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


}

