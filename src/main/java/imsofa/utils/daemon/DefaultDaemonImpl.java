/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imsofa.utils.daemon;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lendle
 */
public class DefaultDaemonImpl extends Thread implements Daemon{
    private DaemonTask task=null;
    private boolean finished=false;
    private boolean running=true;
    private long waitingInterval=60000;
            
    public DefaultDaemonImpl(DaemonTask task){
        this.task=task;
        this.setDaemon(true);
    }

    public long getWaitingInterval() {
        return waitingInterval;
    }

    public void setWaitingInterval(long waitingInterval) {
        this.waitingInterval = waitingInterval;
    }
    
    @Override
    public boolean isFinished() {
        return this.finished;
    }

    @Override
    public void terminate() {
        this.running=false;
        try{
            this.task.shutdown();
            this.interrupt();
        }catch(Exception e){}
    }

    @Override
    public void run() {
        while(true && running){
            if(this.task.shouldExecuteNextRound()){
                Logger.getLogger(this.getClass().getName()).fine("Daemon: "+this.task+" is executing......");
                this.task.executeRound();
            }
            if(this.task.isFinished()){
                this.finished=true;
                this.running=false;
                break;
            }
            try {
                Thread.sleep(this.task.getWaitingInterval());
            } catch (InterruptedException ex) {
                Logger.getLogger(DefaultDaemonImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    
}
