package game.statemachine.robot;

import game.robot.RobotController;
import game.statemachine.StateBase;

/**
 * 逃跑状态
 * @author njdnhh
 */
public class RunState implements StateBase<RobotController> {
    public static RunState Instance;

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

    }

    @Override
    public void exit(RobotController type) {

    }
}
