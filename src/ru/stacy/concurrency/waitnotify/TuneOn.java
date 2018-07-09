package ru.stacy.concurrency.waitnotify;

import java.util.concurrent.TimeUnit;

public class TuneOn implements Runnable {
    private Guitar guitar;

    TuneOn(Guitar guitar) {
        this.guitar = guitar;
    }

    public void run() {
        try {
            while(!Thread.interrupted()) {
                // процесс настройки гитары
                System.out.println("Tune On! ");

                // время для настройки гитары
                TimeUnit.MILLISECONDS.sleep(200);

                // гитара настроена
                guitar.tuned();

                // приостановление задачи методом wait(), пока задача PlayOnTheGuitar.run() не вызовет played(), изменяя состояние и вызывая notifyAll()
                guitar.waitForPlaying();
            }
        } catch(InterruptedException e) {
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending \"Tune On\" task");
    }
}
