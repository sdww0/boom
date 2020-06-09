package game.thread;

import game.element.Player;
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

    ControlRunnable(Player player){
        this.player = player;
    }

    @Override
    public void run() {
        while(true){
            if(player.getLife()<=0){
                GameData.players.remove(player);
                JOptionPane.showConfirmDialog(GameData.getBoard(),"Player Dead!!!!");
                GameData.getBoard().getSquare()[player.getPlayerLocation().getX()]
                        [player.getPlayerLocation().getY()].removeAllElement();
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


