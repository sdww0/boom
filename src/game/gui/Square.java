package game.gui;

import game.basement.Location;
import game.element.Bomb;
import game.element.Player;
import game.element.Walls;

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

    public Square(Bomb bomb , Location squareLocation, Color color) {
        this.bomb = bomb;
        this.squareLocation = squareLocation;
        this.color = color;
        this.walls = null;
        this.player = null;
    }

    public Square(Walls walls, Location squareLocation, Color color) {
        this.walls = walls;
        this.squareLocation = squareLocation;
        this.color = color;
        this.bomb = null;
        this.player = null;
    }

    public Square(Player player, Location squareLocation, Color color) {
        this.player = player;
        this.squareLocation = squareLocation;
        this.color = color;
        this.walls = null;
        this.bomb = null;
    }

    public Square( Location squareLocation, Color color) {
        this.squareLocation = squareLocation;
        this.color = color;
        this.player = null;
        this.walls = null;
        this.bomb = null;
    }

    public Bomb getBomb() {
        return bomb;
    }

    public void setBomb(Bomb bomb) {
        this.bomb = bomb;
        this.walls = null;
        this.player = null;
    }

    public Walls getWalls() {
        return walls;
    }

    public void setWalls(Walls walls) {
        this.walls = walls;
        this.bomb = null;
        this.player = null;
    }

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
        this.walls = null;
        this.bomb = null;
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
