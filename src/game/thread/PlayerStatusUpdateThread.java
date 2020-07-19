package game.thread;

import game.element.Player;
import game.gamedata.GameConstant;
import game.gamedata.GameData;

import javax.swing.*;

/**
 * 监控玩家的数值
 * 自动监控更新
 * 如果低于某血量则删除该玩家
 * @author njdnhh
 */
public class PlayerStatusUpdateThread extends Thread {

    public PlayerStatusUpdateThread(Player player){
        super(new ControlRunnable(player));
    }

}

class ControlRunnable implements Runnable{
    Player player;
    int playerLife ;
    int playerBoom ;
    int playerBombAmount ;
    int playerSpeed;

    ControlRunnable(Player player){
         playerLife = player.getLife();
         playerBoom = player.getBomb().getRadius();
         playerBombAmount = player.getBombs().size();
         playerSpeed = player.getSpeed();
        this.player = player;
    }

    @Override
    public void run() {
        while(true){

            if(playerLife!=player.getLife()){
                GameData.getRightMenu().setPlayerStatusNumber(player.getWhichPlayer()-1, GameConstant.PLAYER_LIFE_TYPE,player.getLife());
                playerLife = player.getLife();
            }
            if(playerBoom!=player.getBomb().getRadius()){
                GameData.getRightMenu().setPlayerStatusNumber(player.getWhichPlayer()-1, GameConstant.PLAYER_BOOM_RADIUS_TYPE,player.getBomb().getRadius());
                playerBoom=player.getBomb().getRadius();
            }
            if(playerBombAmount<player.getBombs().size()){
                GameData.getRightMenu().setPlayerStatusNumber(player.getWhichPlayer()-1, GameConstant.PLAYER_BOMB_AMOUNT_TYPE,player.getBombs().size());
                playerBombAmount=player.getBombs().size();
            }
            if(playerSpeed!=player.getSpeed()){
                GameData.getRightMenu().setPlayerStatusNumber(player.getWhichPlayer()-1, GameConstant.PLAYER_SPEED,player.getSpeed()-GameData.playersDefaultSpeed+1);
                playerSpeed=player.getSpeed();
            }

            if(player.getLife()<=0){
                if(player.isPlayer1()) {
                    GameData.player1 = null;
                    JOptionPane.showConfirmDialog(GameData.getBoard(),"Player1 Dead!!!!");
                }else{
                    GameData.player2 = null;
                    JOptionPane.showConfirmDialog(GameData.getBoard(),"Player2 Dead!!!!");
                }
                GameData.getBoard().getSquare()[player.getVirtualLocation().getX()]
                        [player.getVirtualLocation().getY()].setPlayer(null);
                GameData.getMainFrame().getLayeredPane().remove(player);
                GameData.getMainFrame().repaint();
                break;
            }

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }
}


