package game.thread;

import game.element.Player;
import game.gamedata.GameConstant;
import game.gamedata.GameData;

import javax.swing.*;

/**
 * 监控玩家的血量
 * 如果低于某血量则删除该玩家
 * @author njdnhh
 */
public class PlayerLifeControlThread extends Thread {

    public PlayerLifeControlThread(Player player){
        super(new ControlRunnable(player));
    }

}

class ControlRunnable implements Runnable{
    Player player;
    int playerLife ;
    int playerBoom ;
    int playerBombAmount ;

    ControlRunnable(Player player){
         playerLife = player.getLife();
         playerBoom = player.getBomb().getRadius();
         playerBombAmount = player.getBombs().size();
        this.player = player;
    }

    @Override
    public void run() {
        while(true){

            if(playerLife!=player.getLife()){
                GameData.getRightMenu().setPlayerStatusNumber(player.getWhichPlayer(), GameConstant.PLAYER_LIFE_TYPE,player.getLife());
                playerLife = player.getLife();
            }
            if(playerBoom!=player.getBomb().getRadius()){
                GameData.getRightMenu().setPlayerStatusNumber(player.getWhichPlayer(), GameConstant.PLAYER_BOOM_RADIUS_TYPE,player.getBomb().getRadius());
                playerBoom=player.getBomb().getRadius();
            }
            if(playerBombAmount<player.getBombs().size()){
                GameData.getRightMenu().setPlayerStatusNumber(player.getWhichPlayer(), GameConstant.PLAYER_BOMB_AMOUNT_TYPE,player.getBombs().size());
                playerBombAmount=player.getBombs().size();
            }



            if(player.getLife()<=0){
                if(player.isPlayer1()) {
                    GameData.player1 = null;
                    JOptionPane.showConfirmDialog(GameData.getBoard(),"Player1 Dead!!!!");
                }else{
                    GameData.player2 = null;
                    JOptionPane.showConfirmDialog(GameData.getBoard(),"Player1 Dead!!!!");
                }
                GameData.getBoard().getSquare()[player.getPlayerLocation().changeToVirtualLocation().getX()]
                        [player.getPlayerLocation().changeToVirtualLocation().getY()].removeAllElement();
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


