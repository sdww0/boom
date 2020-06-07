package game.image;

import javax.swing.*;
import java.awt.*;

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
            new ImageIcon(ImageReader.class.getResource("/game/image/walls/break/flower3.png"))
    };
    public static final ImageIcon[] TREE = {
            new ImageIcon(ImageReader.class.getResource("/game/image/walls/unbreak/tree1.png")),
            new ImageIcon(ImageReader.class.getResource("/game/image/walls/unbreak/tree2.png")),
            new ImageIcon(ImageReader.class.getResource("/game/image/walls/unbreak/tree3.png")),
    };
    public static final ImageIcon[] BREAK = {BUILDING[0],BUILDING[1],BUILDING[2],FLOWER[0],FLOWER[1],FLOWER[2],FLOWER[3]};
    public static final ImageIcon[] UN_BREAK = {TREE[0],TREE[1],TREE[2]};
    public static final ImageIcon BOMB = new ImageIcon(ImageReader.class.getResource("/game/image/bomb.png"));


    public static boolean isBreakable(ImageIcon imageIcon){
        for (ImageIcon icon : BREAK) {
            if (imageIcon == icon) {
                return true;
            }
        }
        return false;
    }



}
