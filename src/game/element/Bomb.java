package game.element;

import game.gamedata.GameConstant;
import game.gamedata.GameData;

import javax.swing.*;
import java.awt.*;
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

    public Bomb(int radius, int damage, int time) {
        setLayout(null);
        setSize(GameConstant.SQUARE_SIZE,GameConstant.SQUARE_SIZE);
        this.radius = radius;
        this.damage = damage;
        this.time = time;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintBomb(g);
    }

    private void paintBomb(Graphics g){
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
