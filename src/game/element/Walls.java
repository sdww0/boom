package game.element;

import game.basement.Location;
import game.gamedata.GameConstant;
import game.gamedata.GameData;

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

    private boolean unBreak;
    private Color color;

    public Walls(boolean unBreak) {
        this.unBreak = unBreak;
        setLayout(null);
        setSize(GameConstant.SQUARE_SIZE,GameConstant.SQUARE_SIZE);
        color = new Color(3, 171, 14);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintWall(g);
    }

    private void paintWall(Graphics g){
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);//抗锯齿



            int spacing = (int) (GameConstant.SQUARE_SIZE * 0.05);
            RadialGradientPaint tempPaint = new RadialGradientPaint(spacing,spacing, (float) Math.max(GameConstant.SQUARE_SIZE - 2 * spacing,0.01),new float[]{0.0f,1.0f},
                    new Color[]{Color.WHITE,color});
            ((Graphics2D) g).setPaint(tempPaint);
            ((Graphics2D) g).fill(new Ellipse2D.Double(spacing, spacing, GameConstant.SQUARE_SIZE - 2 * spacing, GameConstant.SQUARE_SIZE - 2 * spacing));

    //    g.drawImage(GameConstant.WALL_IMAGE, 0, 0, (img, infoflags, x, y, width, height) -> false);
    }
    public boolean isUnBreak() {
        return unBreak;
    }

    public void setUnBreak(boolean unBreak) {
        this.unBreak = unBreak;
    }



}
