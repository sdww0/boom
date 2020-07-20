package game.statemachine.robot;

import game.robot.RobotController;
import game.statemachine.StateBase;


/**
 * 正常状态
 * @author njdnhh
 */
public class NormalState implements StateBase<RobotController> {

    public static NormalState Instance;

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

    }

    @Override
    public void exit(RobotController type) {

    }
}
