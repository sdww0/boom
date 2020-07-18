package game.robot.statemachine;



/**
 * 状态机基础
 * @author njdnhh
 */
public interface StateBase<T> {
    /**
     * 状态初始化执行函数
     * @param type 类型
     */
     void init(T type);
    /**
     * 进入状态执行函数
     * @param type 类型
     */
     void enter(T type);
    /**
     * 状态运行中执行函数
     * @param type 类型
     */
     void execute(T type);
    /**
     * 退出状态执行函数
     * @param type 类型
     */
     void exit(T type);

}
