package com.breakidea.lotus.common;

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

public class Main {

    public static void main( String[] args ) throws Exception {
        AutoReloadLocator load = new AutoReloadLocator();
        int name = System.in.read();
        if (name > 0) {
            load.shutdown();
        }
    }
}

class AutoReloadLocator implements Observer {

    private final WatchObservable watchService = new WatchObservable();

    public AutoReloadLocator() throws IOException {
        watchService.addObserver(this);
        watchService.excute();
    }

    @Override
    public void update( Observable o, Object arg ) {
        System.out.println("AA:BB");
    }

    public void shutdown() {
        watchService.deleteObserver(this);
        watchService.shutdown();
    }
}

class WatchObservable extends Observable implements Runnable {

    private volatile boolean idle = true;

    private Thread thread = new Thread(this);

    private void startTask() {
        while (idle) {
            this.setChanged();
            this.notifyObservers();
            try {
                Thread.sleep(1 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    public void excute() {
        thread.start();
    }

    public void shutdown() {
        idle = false;
    }

    @Override
    public void run() {
        this.startTask();
    }
}