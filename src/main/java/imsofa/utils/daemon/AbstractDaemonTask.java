/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imsofa.utils.daemon;

/**
 *
 * @author lendle
 */
public abstract class AbstractDaemonTask implements DaemonTask{
    @Override
    public boolean shouldExecuteNextRound() {
        return true;
    }

    @Override
    public boolean isFinished() {
        return false;
    }

    @Override
    public long getWaitingInterval() {
        return 5*60*1000;
    }

    @Override
    public void shutdown() {
        
    }
    
    
}
