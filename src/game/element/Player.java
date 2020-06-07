package game.element;

import game.basement.Location;
import game.gamedata.GameConstant;

import javax.swing.*;
import java.awt.*;
import java.awt.geom.Ellipse2D;

/**
 * 玩家元素
 * 含
 * 位置
 * 炸弹的范围
 * 炸弹的伤害
 * 血量
 *
 * @author njdnhh
 */
public class Player extends JComponent {

    private Location playerLocation;
    private Bomb bomb;
    private float life;
    private Color color;

    public Player(Location playerLocation, Bomb bomb, float life) {
        this.playerLocation = playerLocation;
        this.bomb = bomb;
        this.life = life;
        setLayout(null);
        setSize(GameConstant.SQUARE_SIZE,GameConstant.SQUARE_SIZE);
        color = new Color(5, 220, 221);
    }

    public Player(Bomb bomb, float life) {
        this.bomb = bomb;
        this.life = life;
        setLayout(null);
        setSize(GameConstant.SQUARE_SIZE,GameConstant.SQUARE_SIZE);
        color = new Color(5, 220, 221);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintWall(g);
    }

    private void paintWall(Graphics g){
        ((Graphics2D) g).setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        int spacing = (int) (GameConstant.SQUARE_SIZE * 0.05);
        RadialGradientPaint tempPaint = new RadialGradientPaint(spacing,spacing, (float) Math.max(GameConstant.SQUARE_SIZE - 2 * spacing,0.01),new float[]{0.0f,1.0f},
                new Color[]{Color.WHITE,color});
        ((Graphics2D) g).setPaint(tempPaint);
        ((Graphics2D) g).fill(new Ellipse2D.Double(spacing, spacing, GameConstant.SQUARE_SIZE - 2 * spacing, GameConstant.SQUARE_SIZE - 2 * spacing));

        //    g.drawImage(GameConstant.WALL_IMAGE, 0, 0, (img, infoflags, x, y, width, height) -> false);
    }










    public Location getPlayerLocation() {
        return playerLocation;
    }

    public void setPlayerLocation(Location playerLocation) {
        this.playerLocation = playerLocation;
    }

    public Bomb getBomb() {
        return bomb;
    }

    public void setBomb(Bomb bomb) {
        this.bomb = bomb;
    }

    public float getLife() {
        return life;
    }

    public void setLife(float life) {
        this.life = life;
    }
}
