package game.gui;

import game.basement.Location;
import game.element.*;
import game.gamedata.GameConstant;

import javax.swing.*;
import java.awt.*;

/**
 * swing面板上组成原件
 * @author njdnhh
 */
public class Square extends JPanel {
    /**
     * 棋盘上含有什么元素（炸弹/墙壁）
     */
    private Object element;
    private Item item;
    /**
     * 如果无玩家在此方格上则为null
     * 如有则为该玩家
     */
    private Player player;

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
     */
    public ElementType getElementType(){
        if(player!=null){
            return ElementType.PLAYER;
        }
        if(this.element==null){
            return ElementType.NULL;
        }
        switch (this.element.getClass().getName()) {
            case "game.element.Boom":
                return ElementType.BOOM;
            case "game.element.Bomb":
                return ElementType.BOMB;
            case "game.element.Walls":
                Walls walls = (Walls)(this.element);
                if(walls.isCanBreak()){
                    return ElementType.BREAKABLE_WALL;
                }else {
                    return ElementType.UNBREAKABLE_WALL;
                }
            default:
                return ElementType.NULL;
        }
    }

    /**
     * @return 含有的element
     */
    public Object getElement(){
        return element;
    }

    public Item getItem(){
        return item;
    }

    public void setItem(Item item) {
        if(item==null){
            if(this.item!=null){
                remove(this.item);
                this.item = null;
                repaint();
            }
        }else {
            if (this.item != null) {
                remove(this.item);
            }
            this.item = item;
            add(item);
            item.repaint();
        }

    }

    public void removeAllElement(){
        this.element = null;
        removeAll();
        if(item!=null){
            add(item);
        }
        repaint();
    }

    /**
     *getter and setter
     */
    public void setElement(Object element) {
        removeAllElement();
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

    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    @Override
    public String toString() {
        if(player!=null){
            return "1";
        }else {
            return "0";
        }

    }
}
