package game.element;

import game.basement.Location;
import game.gamedata.GameConstant;

import javax.swing.*;
import java.awt.*;

/**
 * 墙壁的类
 * 含：
 * 是否可被破坏
 * 位置
 * 图片
 * @author njdnhh
 */
public class Walls extends JComponent {

    private boolean unBreak;

    public Walls(boolean unBreak) {
        this.unBreak = unBreak;
        setLayout(null);
        setSize(GameConstant.SQUARE_SIZE,GameConstant.SQUARE_SIZE);

    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintWall(g);
    }

    private void paintWall(Graphics g){
        g.drawImage(GameConstant.WALL_IMAGE, 0, 0, (img, infoflags, x, y, width, height) -> false);
    }
    public boolean isUnBreak() {
        return unBreak;
    }

    public void setUnBreak(boolean unBreak) {
        this.unBreak = unBreak;
    }



}
