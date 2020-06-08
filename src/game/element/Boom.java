package game.element;

import game.gamedata.GameConstant;
import game.image.ImageReader;

import javax.swing.*;
import java.awt.*;

/**
 * 爆炸效果
 * @author njdnhh
 */
public class Boom extends JComponent {

    public Boom() {
        setLayout(null);
        setSize(GameConstant.SQUARE_SIZE,GameConstant.SQUARE_SIZE);
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        paintBoom(g);
    }

    private void paintBoom(Graphics g){
        g.drawImage(ImageReader.BOOM.getImage(), 0, 0, null);
    }

}
