package game.robot;


import game.element.Player;
import game.statemachine.StateMachine;
import game.statemachine.robot.NormalState;
import game.statemachine.robot.RunState;

/**
 * 机器人控制
 * @author njdnhh
 */
public class RobotController {
    /**
     * 机器人属性
     */
    private Player robot;
    /**
     * 机器人状态机
     * 初步构想：状态包含
     * 血量<=2
     * 逃跑模式
     * 其他血量直接中规中矩就好了
     * 或者就一个中规中矩模式
     */
    private StateMachine<RobotController> robotStateMachine;
    private Thread controlRobotThread;
    private int robotLife;


    public RobotController(Player robot){
        this.robot = robot;
        init();
    }

    private void init(){
        robotStateMachine = new StateMachine<>(this);
        robotStateMachine.setCurrentState(NormalState.getInstance());
        NormalState.getInstance().init(this);
        RunState.getInstance().init(this);
        controlRobotThread = new Thread(this::update);
        robotLife = robot.getLife();
    }

    public Player getRobot() {
        return robot;
    }

    private void update(){
        while(true) {
            if (robot.getLife() == 0) {
                break;
            }
            if(robotLife!=robot.getLife()) {
                if(robotLife>2&&robot.getLife()<=2){
                    robotStateMachine.setCurrentState(RunState.getInstance());
                }else if(robotLife<=2&&robot.getLife()>2){
                    robotStateMachine.setCurrentState(NormalState.getInstance());
                }
                robotLife = robot.getLife();
            }
            robotStateMachine.update();
            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }



}
