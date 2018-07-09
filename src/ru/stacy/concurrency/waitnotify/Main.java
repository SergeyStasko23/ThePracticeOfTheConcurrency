package ru.stacy.concurrency.waitnotify;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class Main {
    public static void main(String[] args) throws Exception {
        Guitar guitar = new Guitar();

        // newCachedThreadPool() создает один поток для каждой задачи
        ExecutorService exec = Executors.newCachedThreadPool();
        exec.execute(new PlayOnTheGuitar(guitar));
        exec.execute(new TuneOn(guitar));

        TimeUnit.SECONDS.sleep(5);

        // прервать все задачи методом interrupt()
        exec.shutdownNow();
    }
}
