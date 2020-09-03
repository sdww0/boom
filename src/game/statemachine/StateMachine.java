package game.statemachine;

import com.sun.istack.internal.NotNull;

/**
 * 状态机
 * @author njdnhh
 */
public class StateMachine<T> {

    T controllerType;
    /**
     * 两个基本状态
     * 目前状态
     * 之前的状态
     */
    StateBase<T> currentState;
    StateBase<T> previousState;

    public StateMachine(T controllerType){
        this.controllerType = controllerType;
    }

    public void update() {
        if(currentState !=null){
            currentState.execute(controllerType);
        }
    }

    public void changeState(@NotNull StateBase<T> newState) {

        previousState = currentState;
        currentState.exit(controllerType);
        currentState = newState;
        currentState.enter(controllerType);
    }

    public void changeToPreviousState() {
        changeState(previousState);
    }

    
    public T getControllerType() {
        return controllerType;
    }

    public void setControllerType(T controllerType) {
        this.controllerType = controllerType;
    }

    public StateBase<T> getCurrentState() {
        return currentState;
    }

    public void setCurrentState(StateBase<T> currentState) {
        if(previousState!=null){
            previousState.exit(controllerType);
        }
        this.currentState = currentState;
        currentState.enter(controllerType);
    }

    public StateBase<T> getPreviousState() {
        return previousState;
    }

    public void setPreviousState(StateBase<T> previousState) {
        this.previousState = previousState;
    }



}
