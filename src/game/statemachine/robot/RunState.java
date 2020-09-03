package game.statemachine.robot;

import game.basement.Location;
import game.robot.RobotController;
import game.statemachine.StateBase;

import java.util.HashMap;
import java.util.LinkedList;

/**
 * 逃跑状态
 * 暂未启用
 * @author njdnhh
 */
public class RunState implements StateBase<RobotController> {
    public static RunState Instance;
    private HashMap<Location,Integer> hashMap;

    private RunState(){}

    public static RunState getInstance() {
        if(Instance==null){
            Instance = new RunState();
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
        hashMap = new HashMap<>();
    }

    @Override
    public void exit(RobotController type) {

    }
}
