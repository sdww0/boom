package game.gui;

import game.gui.basement.PlayerStatus;
import game.image.ImageReader;

import javax.swing.*;

/**
 * 右侧菜单
 * @author njdnhh
 */
public class RightMenu extends JPanel {

    private PlayerStatus[] playerStatuses;

    public RightMenu(){
        setLayout(null);
        setLocation(640,0);
        setSize(ImageReader.PLAYER_STATUS[0].getIconWidth(),640);
        playerStatuses = new PlayerStatus[4];
        for(int n =0;n<4;n++){
            playerStatuses[n] = new PlayerStatus(n);
            playerStatuses[n].setLocation(0,n*ImageReader.PLAYER_STATUS[0].getIconHeight());
            add(playerStatuses[n]);
        }

    }

    public int getPlayerStatusNumber(int playerNumber,int type) {
        return Integer.parseInt(playerStatuses[playerNumber].getPlayerStatus()[type].getText());
    }

    public void setPlayerStatusNumber(int playerNumber,int type,int amount) {
        this.playerStatuses[playerNumber].getPlayerStatus()[type].setText(amount+"");
        this.playerStatuses[playerNumber].getPlayerStatus()[type].repaint();
    }

}
