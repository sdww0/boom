package game.basement;

import javax.swing.*;

/**
 *
 * 直接指定目标图片来创建一个
 * @author njdnhh
 */
public class MyButton extends JButton{


    public MyButton(ImageIcon imageIcon){
        super(imageIcon);
        setFocusPainted(false);
        setBorderPainted(false);
        setContentAreaFilled(false);
        setSize(imageIcon.getIconWidth(),imageIcon.getIconHeight());
    }


}
