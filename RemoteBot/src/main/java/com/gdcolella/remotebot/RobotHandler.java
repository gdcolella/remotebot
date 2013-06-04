package com.gdcolella.remotebot;

/**
 * Created by Greg on 6/4/13.
 */
public class RobotHandler {
    RobotController myController;

    final double TOLERANCE = .05;

    volatile double power;

    volatile double latestTurn, latestPower , prevTurn, prevPower;

    Thread updateThread;
    SendThread updater;

    public RobotHandler(RobotController toControl ){
        myController = toControl;
        updater = new SendThread();
        updateThread = new Thread(updater);
        updateThread.start();
    }

    public void close(){
        updater.stop();

        try{updateThread.join();}
        catch(InterruptedException e){
            e.printStackTrace();
        }


        myController.close();

    }

    public boolean shouldUpdate(){
        return (Math.abs(latestTurn-prevTurn) < TOLERANCE && Math.abs(latestPower - prevPower) < TOLERANCE);
    }

    public void request(double turnPercentage, double powerPercentage){
        latestTurn = turnPercentage;
        latestPower = powerPercentage;
    }

    private void execute(double turnPercentage, double powerPercentage){
        myController.setMotorPower((powerPercentage*power + (turnPercentage*power)), (powerPercentage*power - (turnPercentage*power)));
    }


    class SendThread implements Runnable {
        volatile boolean shouldRun;

        public void stop(){
            shouldRun = true;
        }

        public void run(){
            while(shouldRun){
                tryWait(50);
                if(shouldUpdate()){
                    execute(latestTurn,latestPower);
                    prevPower = latestPower;
                    prevTurn = latestTurn;
                }
            }
        }

        public void tryWait(long waitTime){
            try{Thread.sleep(waitTime);}
            catch(InterruptedException e){e.printStackTrace();}
        }


    }
}
