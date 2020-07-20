package game.element;

import game.basement.Location;
import game.basement.TrueLocation;
import game.basement.UsefulFunction;
import game.gamedata.GameConstant;
import game.gamedata.GameData;
import game.gamedata.Judge;
import game.image.ImageReader;
import game.music.MusicPlayer;
import game.thread.PlayerStatusUpdateThread;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

/**
 * 玩家元素（或者是机器人）
 * 注：与其他元素不同的是玩家将独立于那些方格，直接放置在方格构成的整体上
 * 含
 * 位置
 * 炸弹的范围
 * 炸弹的伤害
 * 血量
 *
 * @author njdnhh
 */
public class Player extends JComponent {
    /**
     * 玩家位置
     * 放置的炸弹列表（如果可以放置，则对应位置为null，不可放置则有炸弹）
     * 玩家放置的炸弹属性
     * 玩家的生命值
     * 移动速度
     * 目前图片
     * 是哪个位置的玩家
     * 是不是玩家1
     */
    private TrueLocation playerLocation;
    private volatile ArrayList<Bomb> bombs;
    private Bomb bomb;
    private int life;
    private int speed;
    private ImageIcon currentImageIcon;
    private int whichPlayer;
    private boolean isPlayer1;
    private Location virtualLocation;
    private Location lastLocation;



    public Player(int whichPlayer, TrueLocation playerLocation, Bomb bomb, boolean isPlayer1) {
        this.whichPlayer = whichPlayer;
        this.playerLocation = playerLocation;
        this.bomb = bomb;
        this.isPlayer1 = isPlayer1;
        init();
    }

    private void init(){
        lastLocation = virtualLocation = playerLocation.changeToVirtualLocation();
        this.bombs = new ArrayList<Bomb>();
        this.bombs.add(null);
        this.life = GameData.playersLife;
        setLayout(null);
        setSize(GameConstant.SQUARE_SIZE,GameConstant.SQUARE_SIZE);
        setLocation(playerLocation.getX(),playerLocation.getY());
        currentImageIcon = ImageReader.ALL_PLAYER[whichPlayer-1][0];
        this.speed = GameData.playersDefaultSpeed;
        new PlayerStatusUpdateThread(this).start();
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintPlayer(g);
    }

    private void paintPlayer(Graphics g){
            g.drawImage(currentImageIcon.getImage(), 0, 0, null);
    }

    @Override
    public void update(Graphics g){
        super.update(g);
        paintPlayer(g);
    }

    public TrueLocation getPlayerLocation() {
        return playerLocation;
    }

    public synchronized void setPlayerLocation(TrueLocation playerLocation) {
        TrueLocation finalLocation = showAnimation(this.playerLocation,playerLocation);
        lastLocation = new Location(virtualLocation.getX(), virtualLocation.getY());
        this.playerLocation = finalLocation;
        virtualLocation = new Location(Math.round(this.playerLocation.getX()/(float)GameConstant.SQUARE_SIZE),
                Math.round(this.playerLocation.getY()/(float)GameConstant.SQUARE_SIZE));
        if(lastLocation.getX()!=virtualLocation.getX()||lastLocation.getY()!=virtualLocation.getY()){
            UsefulFunction.setPlayer(null,lastLocation);
            UsefulFunction.setPlayer(this,virtualLocation);
        }
        setLocation(finalLocation.getX(),finalLocation.getY());
    }

    public Location getVirtualLocation() {
        return virtualLocation;
    }

    public Location getLastLocation() {
        return lastLocation;
    }

    /**
     * 不建议使用
     * 使用setPlayerLocation
     * @param deltaX x变化量
     * @param deltaY y变化量
     */
    @Deprecated
    public void moveXAndYPosition(int deltaX, int deltaY){
        TrueLocation finalLocation = showAnimation(playerLocation,new TrueLocation(this.playerLocation.getX()+deltaX,this.playerLocation.getY()+deltaY));
        lastLocation = new Location(Math.round(this.playerLocation.getX()/(float)GameConstant.SQUARE_SIZE),
                Math.round(this.playerLocation.getY()/(float)GameConstant.SQUARE_SIZE));
        this.playerLocation.setX(finalLocation.getX()+deltaX);
        this.playerLocation.setY(finalLocation.getY()+deltaY);
        virtualLocation = new Location(Math.round(this.playerLocation.getX()/(float)GameConstant.SQUARE_SIZE),
                Math.round(this.playerLocation.getY()/(float)GameConstant.SQUARE_SIZE));
        setLocation(playerLocation.getX(),playerLocation.getY());
    }

    /**
     * 表演动画\
     * 如果在中途取消播放，则返回一个终止的位置
     * @param lastLocation 之前位置
     * @param targetLocation 目标位置
     * @return 中断后的位置
     */
    private TrueLocation showAnimation(TrueLocation lastLocation,TrueLocation targetLocation){
        int xChange = 0, yChange = 0;
        int different = 0;
        if(targetLocation.getX()-lastLocation.getX()!=0){
            xChange = (targetLocation.getX()-lastLocation.getX())/
                    Math.abs(targetLocation.getX()-lastLocation.getX());
            different = Math.abs(targetLocation.getX()-lastLocation.getX());
        }else if(targetLocation.getY()-lastLocation.getY()!=0){
            yChange = (targetLocation.getY()-lastLocation.getY())/
                    Math.abs(targetLocation.getY()-lastLocation.getY());
            different = Math.abs(targetLocation.getY()-lastLocation.getY());
        }
        int canMoveX = 0;
        int canMoveY = 0;
        TrueLocation location = new TrueLocation(lastLocation.getX(),lastLocation.getY());
        for(int n =0;n<different;n++){

            if (location.getX()+xChange <= -1 || location.getX()+xChange >= GameConstant.BOARD_SIZE
                    || location.getY() + yChange <= -1 || location.getY() + yChange >= GameConstant.BOARD_SIZE) {
                break;
            }
            if (xChange > 0 || yChange > 0) {
                canMoveX = (int) (Math.ceil((location.getX() + xChange) / (double) GameConstant.SQUARE_SIZE));
                canMoveY = (int) (Math.ceil((location.getY() + yChange) / (double) GameConstant.SQUARE_SIZE));
            } else {
                canMoveX = (int) (Math.floor((location.getX() + xChange) / (double) GameConstant.SQUARE_SIZE));
                canMoveY = (int) (Math.floor((location.getY() + yChange) / (double) GameConstant.SQUARE_SIZE));
            }
            if (canMoveX <= -1 || canMoveX >= GameConstant.SQUARE_AMOUNT
                    || canMoveY <= -1 || canMoveY >= GameConstant.SQUARE_AMOUNT) {
                break;
            }
            ElementType elementType = GameData.getBoard().getSquare()[canMoveX][canMoveY].getElementType();
            if(!(elementType== ElementType.NULL||elementType==ElementType.PLAYER)){
                break;
            }
            if (GameData.getBoard().getSquare()[canMoveX][canMoveY].getItem() != null) {
                reactPickUpItem(new Location(canMoveX, canMoveY));
            }


            setLocation(location.getX()+xChange,location.getY()+yChange);
            location.setY(location.getY()+yChange);
            location.setX(location.getX()+xChange);

            try {
                Thread.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return location;
    }

    private void reactPickUpItem(Location location) {
        MusicPlayer.Play(MusicPlayer.PICKUP);
        switch (GameData.getBoard().getSquare()[location.getX()][location.getY()].getItem().getType()) {
            case 1:
                bombs.add(null);
                break;
            case 2:
                bomb.setRadius(bomb.getRadius() + 1);
                break;
            case 3:
                life+=1;
                break;
            case 4:
                if(speed>10){
                    break;
                }
                speed+=1;
                break;
            default:
        }
        GameData.getBoard().getSquare()[location.getX()][location.getY()].setItem(null);
    }


    public void setVirtualLocation(Location virtualLocation) {
        this.lastLocation = this.virtualLocation;
        this.virtualLocation = virtualLocation;
    }

    public ArrayList<Bomb> getBombs() {
        return bombs;
    }

    public void setBombs(ArrayList<Bomb> bombs) {
        this.bombs = bombs;
    }

    public void setBomb(Bomb bomb) {
        this.bomb = bomb;
    }

    public Bomb getBomb() {
        return bomb;
    }

    public ImageIcon getCurrentImageIcon() {
        return currentImageIcon;
    }

    public void setCurrentImageIcon(ImageIcon currentImageIcon) {
        this.currentImageIcon = currentImageIcon;
        repaint();
    }

    public int getLife() {
        return life;
    }

    public void setLife(int life) {
        this.life = life;
    }

    public int getWhichPlayer() {
        return whichPlayer;
    }

    public boolean isPlayer1() {
        return isPlayer1;
    }

    public int getSpeed() {
        return speed;
    }

    public void setSpeed(int speed) {
        this.speed = speed;
    }

    public void hurt(){
        MusicPlayer.Play(MusicPlayer.HURT);
        life--;
    }

}
