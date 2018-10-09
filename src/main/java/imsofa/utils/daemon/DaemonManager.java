/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package imsofa.utils.daemon;

import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author lendle
 */
public class DaemonManager {
    private List<Daemon> daemons=new ArrayList<Daemon>();
    private FinishedTaskMonitoringDaemon ftd=new FinishedTaskMonitoringDaemon();
    public DaemonManager() {
        ftd.start();
    }
    
    public synchronized void addDaemon(DaemonTask task){
        DefaultDaemonImpl t=new DefaultDaemonImpl(task);
        daemons.add(t);
        t.start();
    }
    
    public synchronized void stop(){
        for(Daemon t : daemons){
            t.terminate();
        }
        daemons.clear();
        ftd.setRunning(false);
        ftd.interrupt();
    }

    public List<Daemon> getDaemons() {
        return new ArrayList<Daemon>(daemons);
    }

    public synchronized void setDaemons(List<Daemon> daemons) {
        this.daemons.clear();
        this.daemons.addAll(daemons);
    }
    
    class FinishedTaskMonitoringDaemon extends Thread{
        private boolean running=true;
        public FinishedTaskMonitoringDaemon() {
            this.setDaemon(true);
        }

        public boolean isRunning() {
            return running;
        }

        public void setRunning(boolean running) {
            this.running = running;
        }
        
        
        public void run(){
            while(running){
                try {
                    Thread.sleep(60000);
                } catch (InterruptedException ex) {
                    Logger.getLogger(DaemonManager.class.getName()).log(Level.SEVERE, null, ex);
                }
                List<Daemon> daemons=getDaemons();
                List<Daemon> activeDaemons=new ArrayList<Daemon>();
                for(Daemon daemon : daemons){
                    if(daemon.isFinished()==false){
                        activeDaemons.add(daemon);
                    }
                }
                setDaemons(activeDaemons);
            }
        }
    }
}
