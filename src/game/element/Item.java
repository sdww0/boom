package game.element;

import game.gamedata.GameConstant;
import game.image.ImageReader;

import javax.swing.*;
import java.awt.*;

/**
 * 道具
 * @author njdnhh
 */
public class Item extends JComponent {
    public static final int BOMB_AMOUNT = 1;
    public static final int BOMB_AREA = 2;
    public static final int CURE = 3;
    public static final int SPEED = 4;
    /**
     * 道具类型
     * 1：提升炸弹数量
     * 2：提升炸弹范围
     * 3：治疗
     */

    private int type;

    public Item(int type) {
        setLayout(null);
        setSize(GameConstant.SQUARE_SIZE,GameConstant.SQUARE_SIZE);
        this.type = type;
    }

    public ImageIcon getImageIcon(){
        try {
            return ImageReader.ITEM[type - 1];
        }catch(Exception e){
            return null;
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintItem(g);
    }

    private void paintItem(Graphics g){
            g.drawImage(getImageIcon().getImage(), 0, 0, null);
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
        paintItem(g);
    }

    public int getType() {
        return type;
    }
}
