package game.element;

import game.gamedata.GameConstant;
import game.gamedata.GameData;
import game.image.ImageReader;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;
import java.awt.image.ImageConsumer;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;

/**
 * 炸弹类
 * 含
 * 范围半径
 * 伤害
 * 多久爆炸
 * @author njdnhh
 */
public class Bomb extends JComponent {

    private int radius;
    private int damage;


    public Bomb(int radius, int damage) {
        setLayout(null);
        setSize(GameConstant.SQUARE_SIZE,GameConstant.SQUARE_SIZE);
        this.radius = radius;
        this.damage = damage;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintBomb(g);
    }

    private void paintBomb(Graphics g){
        g.drawImage(ImageReader.BOMB.getImage(), 0, 0, null);
    }


    public int getRadius() {
        return radius;
    }

    public int getDamage() {
        return damage;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }
}
