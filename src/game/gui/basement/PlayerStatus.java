package game.gui.basement;

import game.gamedata.GameData;
import game.image.ImageReader;

import javax.swing.*;
import java.awt.*;

/**
 * 玩家的状态
 * 为右侧菜单栏的基本
 * @author njdnhh
 */
public class PlayerStatus extends JComponent {

    private int playerNumber;
    private JLabel[] playerStatus;

    public PlayerStatus(int playerNumber){
        this.playerNumber = playerNumber;
        playerStatus = new JLabel[4];
        for(int n =0;n<playerStatus.length;n++){
            playerStatus[n] = new JLabel();
        }
        playerStatus[0].setText("1");
        playerStatus[1].setText(GameData.playersLife+"");
        playerStatus[2].setText("1");
        playerStatus[3].setText("1");

        playerStatus[0].setBounds(102,128,20,20);
        playerStatus[1].setBounds(203,5,70,36);
        playerStatus[2].setBounds(203,50,70,36);
        playerStatus[3].setBounds(203,100,70,36);

        for(int n =0;n<playerStatus.length;n++){
            playerStatus[n].setHorizontalTextPosition(JLabel.CENTER);
            playerStatus[n].setVerticalTextPosition(JLabel.CENTER);
            add(playerStatus[n]);
        }

        setSize(ImageReader.PLAYER_STATUS[0].getIconWidth(),ImageReader.PLAYER_STATUS[0].getIconHeight());
    }

    @Override
    protected void paintComponent(Graphics g){
        super.paintComponent(g);
        g.drawImage(ImageReader.PLAYER_STATUS[playerNumber].getImage(),0,0,null);
    }

    public JLabel[] getPlayerStatus() {
        return playerStatus;
    }

    public void setPlayerStatus(int type,int amount) {
        this.playerStatus[type].setText(amount+"");
    }
}
