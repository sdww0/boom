package game.image;

import javax.swing.*;
import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * 读取图片
 * @author njdnhh
 */
public class ImageReader {

    public static final ImageIcon[] BUILDING = {
            new ImageIcon(ImageReader.class.getResource("/game/image/walls/break/building1.png")),
            new ImageIcon(ImageReader.class.getResource("/game/image/walls/break/building2.png")),
            new ImageIcon(ImageReader.class.getResource("/game/image/walls/break/building3.png"))
    };
    public static final ImageIcon[] FLOWER = {
            new ImageIcon(ImageReader.class.getResource("/game/image/walls/break/flower1.png")),
            new ImageIcon(ImageReader.class.getResource("/game/image/walls/break/flower2.png")),
            new ImageIcon(ImageReader.class.getResource("/game/image/walls/break/flower3.png")),
            new ImageIcon(ImageReader.class.getResource("/game/image/walls/break/flower4.png"))
    };
    public static final ImageIcon[] TREE = {
            new ImageIcon(ImageReader.class.getResource("/game/image/walls/unbreak/tree1.png")),
            new ImageIcon(ImageReader.class.getResource("/game/image/walls/unbreak/tree2.png")),
         //   new ImageIcon(ImageReader.class.getResource("/game/image/walls/unbreak/tree3.png")),
    };
    public static final ImageIcon[] PLAYER1 = {
            new ImageIcon(ImageReader.class.getResource("/game/image/player/blue1.png")),
            new ImageIcon(ImageReader.class.getResource("/game/image/player/blue2.png")),
            new ImageIcon(ImageReader.class.getResource("/game/image/player/blue3.png")),
            new ImageIcon(ImageReader.class.getResource("/game/image/player/blue4.png"))
    };
    public static final ImageIcon[] PLAYER2 = {
            new ImageIcon(ImageReader.class.getResource("/game/image/player/green1.png")),
            new ImageIcon(ImageReader.class.getResource("/game/image/player/green2.png")),
            new ImageIcon(ImageReader.class.getResource("/game/image/player/green3.png")),
            new ImageIcon(ImageReader.class.getResource("/game/image/player/green4.png"))
    };
    public static final ImageIcon[] PLAYER3 = {
            new ImageIcon(ImageReader.class.getResource("/game/image/player/orange1.png")),
            new ImageIcon(ImageReader.class.getResource("/game/image/player/orange2.png")),
            new ImageIcon(ImageReader.class.getResource("/game/image/player/orange3.png")),
            new ImageIcon(ImageReader.class.getResource("/game/image/player/orange4.png"))
    };
    public static final ImageIcon[] PLAYER4 = {
            new ImageIcon(ImageReader.class.getResource("/game/image/player/pink1.png")),
            new ImageIcon(ImageReader.class.getResource("/game/image/player/pink2.png")),
            new ImageIcon(ImageReader.class.getResource("/game/image/player/pink3.png")),
            new ImageIcon(ImageReader.class.getResource("/game/image/player/pink4.png"))
    };
    public static final ImageIcon[] PLAYER_STATUS = {
            new ImageIcon(ImageReader.class.getResource("/game/image/player/player1.PNG")),
            new ImageIcon(ImageReader.class.getResource("/game/image/player/player2.PNG")),
            new ImageIcon(ImageReader.class.getResource("/game/image/player/player3.PNG")),
            new ImageIcon(ImageReader.class.getResource("/game/image/player/player4.PNG"))
    };

    public static final ImageIcon RECOVER = new ImageIcon(ImageReader.class.getResource("/game/image/item/recover2.png"));
    public static final ImageIcon MORE_BOMB = new ImageIcon(ImageReader.class.getResource("/game/image/item/MoreBomb.png"));
    public static final ImageIcon AREA_BOMB = new ImageIcon(ImageReader.class.getResource("/game/image/item/Area.png"));
    public static final ImageIcon SPEED = new ImageIcon(ImageReader.class.getResource("/game/image/item/speed.png"));

    public static final ImageIcon[] ITEM = {MORE_BOMB,AREA_BOMB,RECOVER,SPEED};
    public static final ImageIcon[][] ALL_PLAYER = {PLAYER1, PLAYER2, PLAYER3, PLAYER4};
    public static final ImageIcon[] BREAK = {BUILDING[0],BUILDING[1],BUILDING[2],FLOWER[0],FLOWER[1],FLOWER[2],FLOWER[3]};
    public static final ImageIcon[] UN_BREAK = {TREE[0],TREE[1],/*TREE[2]*/};
    public static final ImageIcon BOMB = new ImageIcon(ImageReader.class.getResource("/game/image/bomb.png"));
    public static final ImageIcon BOOM = new ImageIcon(ImageReader.class.getResource("/game/image/boom.png"));


    public static boolean isBreakable(ImageIcon imageIcon){
        for (ImageIcon icon : BREAK) {
            if (imageIcon == icon) {
                return true;
            }
        }
        return false;
    }

}
