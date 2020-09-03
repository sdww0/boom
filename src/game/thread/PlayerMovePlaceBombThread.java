package game.thread;

import game.gamedata.GameData;

/**
 * 每隔一段时间从一个哈系表中获取是否有传进来的keyCode，有的话进行相应操作
 * @author njdnhh
 */
public class PlayerMovePlaceBombThread extends Thread{

    public PlayerMovePlaceBombThread(int keyCode,boolean isPlayer1){
        super(new CheckRunnable(keyCode,isPlayer1));
    }

}

class CheckRunnable implements Runnable{

    private int keyCode;
    private  boolean isPlayer1;
    CheckRunnable(int keyCode,boolean isPlayer1){
        this.keyCode = keyCode;
        this.isPlayer1 = isPlayer1;

    }


    @Override
    public void run() {
        while(true){
            if(GameData.getMainFrame().check.contains(keyCode)){
                   GameData.getMainFrame().sendTask(keyCode,isPlayer1);
            }
            if(isPlayer1){
                if(GameData.player1==null){
                    break;
                }
            }else{
                if(GameData.player2==null){
                    break;
                }
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}



