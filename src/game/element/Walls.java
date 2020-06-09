package game.element;

import game.basement.Location;
import game.gamedata.GameConstant;
import game.gamedata.GameData;
import game.image.ImageReader;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * 墙壁的类
 * 含：
 * 是否可被破坏
 * 位置
 * 图片
 * @author njdnhh
 */
public class Walls extends JComponent {

    private boolean canBreak;
    private int imagePosition;

    public Walls(boolean canBreak) {
        this.canBreak = canBreak;
        setLayout(null);
        setSize(GameConstant.SQUARE_SIZE,GameConstant.SQUARE_SIZE);
        if(canBreak){
            imagePosition = (int)(Math.random()*ImageReader.BREAK.length);
        }else{
            imagePosition = (int)(Math.random()*ImageReader.UN_BREAK.length);
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintWall(g);
    }

    private void paintWall(Graphics g){
        if(canBreak){
            g.drawImage(ImageReader.BREAK[imagePosition].getImage(), 0, 0, null);
        }else{
            g.drawImage(ImageReader.UN_BREAK[imagePosition].getImage(), 0, 0, null);
        }
    }



    public boolean isCanBreak() {
        return canBreak;
    }

    public void setCanBreak(boolean unBreak) {
        this.canBreak = unBreak;
    }



}
