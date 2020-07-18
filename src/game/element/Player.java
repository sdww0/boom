package game.element;

import com.sun.corba.se.impl.orbutil.graph.Graph;
import game.basement.Location;
import game.basement.TrueLocation;
import game.gamedata.GameConstant;
import game.gamedata.GameData;
import game.image.ImageReader;
import game.thread.PlayerLifeControlThread;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

/**
 * 玩家元素
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

    public Player(int whichPlayer, TrueLocation playerLocation, Bomb bomb, float life, boolean isPlayer1) {
        this.whichPlayer = whichPlayer;
        this.playerLocation = playerLocation;
        this.bombs = new ArrayList<Bomb>();
        this.bombs.add(null);
        this.bomb = bomb;
        this.life = GameData.playersLife;
        this.isPlayer1 = isPlayer1;
        this.speed = GameData.playersDefaultSpeed;
        setLayout(null);
        setSize(GameConstant.SQUARE_SIZE,GameConstant.SQUARE_SIZE);
        setLocation(playerLocation.getX(),playerLocation.getY());
        currentImageIcon = ImageReader.ALL_PLAYER[whichPlayer-1][0];
        new PlayerLifeControlThread(this).start();
    }

    public Player(Bomb bomb, float life,boolean isPlayer1) {
        this.bombs = new ArrayList<Bomb>();
        this.bombs.add(null);
        this.bomb = bomb;
        this.life = GameData.playersLife;
        this.isPlayer1 = isPlayer1;
        this.speed = GameData.playersDefaultSpeed;
        setLayout(null);
        setSize(GameConstant.SQUARE_SIZE,GameConstant.SQUARE_SIZE);
        setLocation(playerLocation.getX(),playerLocation.getY());
        currentImageIcon = ImageReader.ALL_PLAYER[whichPlayer-1][0];
        new PlayerLifeControlThread(this).start();
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

    public void setPlayerLocation(TrueLocation playerLocation) {
        this.playerLocation = playerLocation;
    }

    public void moveXAndYPosition(int deltaX,int deltaY){
        this.playerLocation.setX(this.playerLocation.getX()+deltaX);
        this.playerLocation.setY(this.playerLocation.getY()+deltaY);
        setLocation(playerLocation.getX(),playerLocation.getY());
        repaint();
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
}
