package game.element;

import com.sun.corba.se.impl.orbutil.graph.Graph;
import game.basement.Location;
import game.gamedata.GameConstant;
import game.image.ImageReader;
import game.thread.PlayerLifeControlThread;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.util.ArrayList;

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
public class Player extends JComponent {
    /**
     * 玩家位置
     * 放置的炸弹列表（如果可以放置，则对应位置为null，不可放置则有炸弹）
     * 玩家放置的炸弹属性
     * 玩家的生命值
     * 测试用颜色
     */
    private Location playerLocation;
    private ArrayList<Bomb> bombs;
    private Bomb bomb;
    private float life;
    private ImageIcon currentImageIcon;

    public Player(Location playerLocation, Bomb bomb, float life) {
        this.playerLocation = playerLocation;
        this.bombs = new ArrayList<Bomb>();
        this.bombs.add(null);
        this.bomb = bomb;
        this.life = life;
        setLayout(null);
        setSize(GameConstant.SQUARE_SIZE,GameConstant.SQUARE_SIZE);
        currentImageIcon = ImageReader.PLAYER1[0];
        new PlayerLifeControlThread(this).start();
    }

    public Player(Bomb bomb, float life) {
        this.bombs = new ArrayList<Bomb>();
        this.bombs.add(null);
        this.bomb = bomb;
        this.life = life;
        setLayout(null);
        setSize(GameConstant.SQUARE_SIZE,GameConstant.SQUARE_SIZE);
        currentImageIcon = ImageReader.PLAYER1[0];
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



    public Location getPlayerLocation() {
        return playerLocation;
    }

    public void setPlayerLocation(Location playerLocation) {
        this.playerLocation = playerLocation;
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
    }

    public float getLife() {
        return life;
    }

    public void setLife(float life) {
        this.life = life;
    }
}
