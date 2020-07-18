package game.robot.statemachine;

import com.sun.istack.internal.NotNull;

/**
 * 状态机
 * @author njdnhh
 */
public class StateMachine<T> {

    T pType;
    StateBase<T> m_pCurrentState;
    StateBase<T> m_pPreviousState;
    StateBase<T> m_pGlobalState;
    StateBase<T> m_pLateGlobalState;

    public StateMachine(T pType){
        this.pType = pType;
    }

    public void update() {
        if(m_pGlobalState!=null){
            m_pGlobalState.execute(pType);
        }
        if(m_pCurrentState!=null){
            m_pCurrentState.execute(pType);
        }
        if(m_pLateGlobalState!=null){
            m_pLateGlobalState.execute(pType);
        }
    }

    public void changeGlobalState(@NotNull StateBase<T> pNewState) {
        m_pGlobalState.exit(pType);
        m_pGlobalState = pNewState;
        m_pGlobalState.enter(pType);
    }

    public void changeLateGlobalState(@NotNull StateBase<T> pNewState) {
        m_pLateGlobalState.exit(pType);
        m_pLateGlobalState = pNewState;
        m_pLateGlobalState.enter(pType);
    }

    public void changeState(@NotNull StateBase<T> pNewState) {

        m_pPreviousState = m_pCurrentState;
        m_pCurrentState.exit(pType);
        m_pCurrentState = pNewState;
        m_pCurrentState.enter(pType);
    }

    public void changeToPreviousState() {
        changeState(m_pPreviousState);
    }

    
    public T getpType() {
        return pType;
    }

    public void setpType(T pType) {
        this.pType = pType;
    }

    public StateBase<T> getM_pCurrentState() {
        return m_pCurrentState;
    }

    public void setM_pCurrentState(StateBase<T> m_pCurrentState) {
        this.m_pCurrentState = m_pCurrentState;
    }

    public StateBase<T> getM_pPreviousState() {
        return m_pPreviousState;
    }

    public void setM_pPreviousState(StateBase<T> m_pPreviousState) {
        this.m_pPreviousState = m_pPreviousState;
    }

    public StateBase<T> getM_pGlobalState() {
        return m_pGlobalState;
    }

    public void setM_pGlobalState(StateBase<T> m_pGlobalState) {
        this.m_pGlobalState = m_pGlobalState;
    }

    public StateBase<T> getM_pLateGlobalState() {
        return m_pLateGlobalState;
    }

    public void setM_pLateGlobalState(StateBase<T> m_pLateGlobalState) {
        this.m_pLateGlobalState = m_pLateGlobalState;
    }



}
