package game.statemachine.robot;

import game.basement.Location;
import game.robot.RobotAndLocation;
import game.robot.RobotController;
import game.robot.RobotPlaceBomb;
import game.statemachine.StateBase;

import java.util.*;


/**
 * 正常状态
 * @author njdnhh
 */
public class NormalState implements StateBase<RobotController> {

    private Thread normalThread;


    private NormalState() {
    }

    public static NormalState getInstance() {
        return new NormalState();
    }

    @Override
    public void init(RobotController type) {

    }

    @Override
    public void enter(RobotController type) {
        if (normalThread == null) {
            normalThread = new Thread(() -> {
                try {
                    while (true) {
                        type.getRobot().moveRobotToPosition(RobotAndLocation.update(type.getRobot().getVirtualLocation()));
                        if(RobotPlaceBomb.placeBomb(type.getRobot().getVirtualLocation())){
                            type.getRobot().placeBomb();
                        }
                        Thread.sleep(10);
                    }
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            });
            normalThread.start();
        } else {
            if(!normalThread.isAlive()) {
                normalThread.notify();
            }
        }

    }

    @Override
    public void execute(RobotController type) {
    }

    @Override
    public void exit(RobotController type) {
        try {
            normalThread.wait();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
