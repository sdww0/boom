package game.map.gui;

import game.basement.Location;
import game.element.Walls;
import game.gamedata.GameConstant;
import game.map.MapData;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

/**
 * 地图编辑方格
 * @author njdnhh
 */
public class MapEditorSquare extends JPanel {
    /**
     * 棋盘上含有什么元素（炸弹？墙壁？玩家？）
     */
    private Object element;

    private Location squareLocation;
    private Color color;

    public MapEditorSquare(Location squareLocation, Color color) {
        this.squareLocation = squareLocation;
        this.color = color;
        this.element = null;
        setLayout(new GridLayout(1, 1));
        setSize(GameConstant.SQUARE_SIZE,GameConstant.SQUARE_SIZE);
        addMouseListener(new MouseListener() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if(!MapData.clear) {
                    setElement(new Walls(MapData.canBreak));
                    if (MapData.canBreak) {
                        MapData.getMap()[squareLocation.getX()][squareLocation.getY()] = 1;
                    } else {
                        MapData.getMap()[squareLocation.getX()][squareLocation.getY()] = 2;
                    }
                }else{
                    MapData.getMap()[squareLocation.getX()][squareLocation.getY()] = 0;
                    removeAllElement();
                }
            }

            @Override
            public void mousePressed(MouseEvent e) {

            }

            @Override
            public void mouseReleased(MouseEvent e) {

            }

            @Override
            public void mouseEntered(MouseEvent e) {

            }

            @Override
            public void mouseExited(MouseEvent e) {

            }
        });
    }

    @Override
    protected void paintComponent(Graphics g) {//画出原件位置的图像
        super.paintComponent(g);
        paintSquare(g);
    }

    private void paintSquare(Graphics g) {
        g.setColor(color);
        g.fillRect(0, 0, getWidth()-1 , getHeight()-1 );

    }

    /**
     * 注意！！调用此方法前必须用getElementType获得样式
     * @return 含有的element
     */
    public Object getElement(){
        return element;
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
