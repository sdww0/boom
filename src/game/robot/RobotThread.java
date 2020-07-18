package game.robot;

import java.util.concurrent.locks.LockSupport;

/**
 * 机器人线程
 * 包含：
 * 移动线程
 * 运算线程
 * @author njdnhh
 */
public class RobotThread {

    Thread moveThread;
    Thread calcThread;
    volatile boolean endMove;

    public RobotThread(Runnable s1,Runnable s2){
        moveThread = new Thread(s1);
        calcThread = new Thread(s2);
    }

    /**
     * 停止移动
     */
    public void endMove(){
        try {
            moveThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void startMove(){
        LockSupport.unpark(moveThread);
    }


}
