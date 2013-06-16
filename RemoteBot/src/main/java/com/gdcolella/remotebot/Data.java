package com.gdcolella.remotebot;

/**
 * Created by greg on 6/14/13.
 */
public class Data {
    static volatile Data data = new Data();

    public synchronized static Data defaultData(){
        return data;
    }


    public volatile Connection myConnection;

}
