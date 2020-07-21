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
        hashMap = RobotAndLocation.update(type.getRobot().getVirtualLocation());
        Iterator<Location> keys=hashMap.keySet().iterator();
        Iterator<Integer> values=hashMap.values().iterator();
        Location location = null;
        int max = Integer.MIN_VALUE;
        while (keys.hasNext()){
            int value = values.next();
            if(value>max){
                location = keys.next();
                max = value;
            }else{
                keys.next();
            }
        }
        if(lastLocation==type.getRobot().getLastLocation()){
            return;
        }
        lastLocation = location;
        assert location != null;
        type.getRobot().moveRobotToPosition(location);

    }

    @Override
    public void exit(RobotController type) {

    }
}
