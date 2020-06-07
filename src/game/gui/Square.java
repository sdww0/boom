package game.gui;

import game.basement.Location;
import game.element.Bomb;
import game.element.Player;
import game.element.Walls;
import game.gamedata.GameConstant;

import javax.swing.*;
import java.awt.*;

/**
 * swing面板上组成原件
 * @author njdnhh
 */
public class Square extends JPanel {
    /**
     * 棋盘上含有什么元素（炸弹？墙壁？玩家？）
     * 三个量只能有一个存在，当一个存在时，设其他为null
     */
    private Bomb bomb;
    private Walls walls;
    private Player player;

    private Location squareLocation;
    private Color color;

    public Square(Location squareLocation, Color color) {
        this.squareLocation = squareLocation;
        this.color = color;
        this.player = null;
        this.walls = null;
        this.bomb = null;
        setLayout(new GridLayout(1, 1));
        setSize(GameConstant.SQUARE_SIZE,GameConstant.SQUARE_SIZE);
    }

    @Override
    protected void paintComponent(Graphics g) {//画出原件位置的图像
        super.paintComponent(g);
        paintSquare(g);
    }

    private void paintSquare(Graphics g) {
        g.setColor(color);
        g.fillRect(0, 0, getWidth() - 1, getHeight() - 1);

    }

    /**
     *
     * @return 含有的元素类型
     * 0 代表无元素
     * 1 代表 墙壁
     * 2 代表 炸弹
     * 3 代表 玩家
     */
    public int getElement(){
        if(player!=null){
            return 3;
        }else if(bomb!=null){
            return 2;
        }else if(walls!=null){
            return 1;
        }else {
            return 0;
        }
    }


    public void removeAllElement(){
        player = null;
        bomb = null;
        walls = null;
        removeAll();
    }


    /**
     *getter and setter
     */
    public Bomb getBomb() {
        return bomb;
    }

    public void setBomb(Bomb bomb) {
        this.bomb = bomb;
        this.walls = null;
        this.player = null;
        add(bomb);
        repaint();
    }

    public Walls getWalls() {
        return walls;
    }

    public void setWalls(Walls walls) {
        this.walls = walls;
        this.bomb = null;
        this.player = null;
        add(walls);
        repaint();
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
        this.walls = null;
        this.bomb = null;
        add(player);
        repaint();
    }

    public Location getSquareLocation() {
        return squareLocation;
    }

    public void setSquareLocation(Location squareLocation) {
        this.squareLocation = squareLocation;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }
}
