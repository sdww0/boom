package game.element;

import game.gamedata.GameConstant;
import game.gamedata.GameData;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.ImageObserver;

/**
 * 炸弹类
 * 含
 * 范围半径
 * 伤害
 * 多久爆炸
 * @author njdnhh
 */
public class Bomb extends JComponent {
    /**
     * 一旦放下便不可以再进行操作
     */
    private int radius;
    private int damage;
    private int time;
    //test
    private Color color;


    public Bomb(int radius, int damage, int time) {
        setLayout(null);
        setSize(GameConstant.SQUARE_SIZE,GameConstant.SQUARE_SIZE);
        this.radius = radius;
        this.damage = damage;
        this.time = time;
        color = new Color(226, 4, 20);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintBomb(g);
    }

    private void paintBomb(Graphics g){
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int spacing = (int) (GameConstant.SQUARE_SIZE * 0.05);
        RadialGradientPaint tempPaint = new RadialGradientPaint(spacing,spacing, (float) Math.max(GameConstant.SQUARE_SIZE - 2 * spacing,0.01),new float[]{0.0f,1.0f},
                new Color[]{Color.WHITE,color});
        ((Graphics2D) g).setPaint(tempPaint);
        ((Graphics2D) g).fill(new Ellipse2D.Double(spacing, spacing, GameConstant.SQUARE_SIZE - 2 * spacing, GameConstant.SQUARE_SIZE - 2 * spacing));


        g.drawImage(GameConstant.BOMB_IMAGE, 0, 0, (img, infoflags, x, y, width, height) -> false);
    }


    public int getRadius() {
        return radius;
    }

    public int getDamage() {
        return damage;
    }

    public int getTime() {
        return time;
    }
}
