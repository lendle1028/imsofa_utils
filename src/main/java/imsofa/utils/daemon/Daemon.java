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
public interface Daemon {
    /**
     * return true if the task is finished
     * @return 
     */
    public boolean isFinished();
    /**
     * force terminate the daemon
     */
    public void terminate();
}
