package ru.stacy.concurrency.waitnotify;

import java.util.concurrent.TimeUnit;

public class PlayOnTheGuitar implements Runnable {
    private Guitar guitar;

    PlayOnTheGuitar(Guitar guitar) {
        this.guitar = guitar;
    }

    public void run() {
        try {
            while(!Thread.interrupted()) {
                // приостановление задачи методом wait(), пока задача TuneOn.run() не вызовет tuned(), изменяя состояние и вызывая notifyAll()
                guitar.waitForTuning();

                // процесс игры на гитаре
                System.out.println("Play On The Guitar! ");

                // время для игры на гитаре
                TimeUnit.MILLISECONDS.sleep(200);

                // на гитаре поиграли
                guitar.played();
            }
        } catch(InterruptedException e) {
            System.out.println("Exiting via interrupt");
        }
        System.out.println("Ending \"Play On The Guitar\" task");
    }
}
