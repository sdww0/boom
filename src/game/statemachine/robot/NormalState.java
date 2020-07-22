package game.statemachine.robot;

import game.basement.Location;
import game.robot.RobotAndLocation;
import game.robot.RobotController;
import game.statemachine.StateBase;

import java.util.*;


/**
 * 正常状态
 * @author njdnhh
 */
public class NormalState implements StateBase<RobotController> {

    public static NormalState Instance;
    private HashMap<Location,Integer> hashMap;
    private Location lastLocation;

    private NormalState(){}

    public static NormalState getInstance() {
        if(Instance==null){
            Instance = new NormalState();
        }
        return Instance;
    }

    @Override
    public void init(RobotController type) {

    }

    @Override
    public void enter(RobotController type) {

    }

    @Override
    public void execute(RobotController type) {
        type.getRobot().moveRobotToPosition(RobotAndLocation.update(type.getRobot().getVirtualLocation()));
    }

    @Override
    public void exit(RobotController type) {

    }
}
