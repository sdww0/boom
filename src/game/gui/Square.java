package game.gui;

import com.sun.istack.internal.NotNull;
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
     */
    private Object element;

    private Location squareLocation;
    private Color color;

    public Square(Location squareLocation, Color color) {
        this.squareLocation = squareLocation;
        this.color = color;
        this.element = null;
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
        g.fillRect(0, 0, getWidth() , getHeight() );

    }

    /**
     *
     * @return 含有的元素类型
     * -1 代表  不可摧毁的墙壁
     * 0  代表  无元素
     * 1  代表  可摧毁的墙壁
     * 2  代表  炸弹
     * 3  代表  玩家
     * 4  代表  爆炸
     */
    public int getElement(){
        if(this.element==null){
            return 0;
        }
        switch (this.element.getClass().getName()) {
            case "game.element.Boom":
                return 4;
            case "game.element.Player":
                return 3;
            case "game.element.Bomb":
                return 2;
            case "game.element.Walls":
                Walls walls = (Walls)(this.element);
                if(walls.isCanBreak()){
                    return 1;
                }else {
                    return -1;
                }
            default:
                return 0;
        }
    }


    public void removeAllElement(){
        this.element = null;
        removeAll();
        repaint();
    }


    /**
     *getter and setter
     */
    public void setElement(Object element) {
        removeAll();
        this.element = element;
        add((Component) element);
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
