/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imsofa.utils.daemon;

/**
 * implement the task executed by the daemon
 * the task will be surrounded by a while loop
 * @author lendle
 */
public interface DaemonTask {
    public void executeRound();
    public boolean shouldExecuteNextRound();
    public boolean isFinished();
    public long getWaitingInterval();
    public void shutdown();
}
