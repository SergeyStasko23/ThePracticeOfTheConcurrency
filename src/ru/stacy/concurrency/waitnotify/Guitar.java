package ru.stacy.concurrency.waitnotify;

class Guitar {
    // флаг состояния настройки гитары
    private boolean tuneOn = false;

    synchronized void tuned() {
        tuneOn = true;         // гитара настроена и готова к использованию
        notify();              // пробуждение задачи
    }

    synchronized void played() {
        tuneOn = false;        // на гитаре поиграли и ее необходимо настроить
        notify();              // пробуждение задачи
    }

    synchronized void waitForTuning() throws InterruptedException {
        while(!tuneOn) {
            wait();            // приостановление вызывающей задачи и освобождение блокировки
        }
    }

    synchronized void waitForPlaying() throws InterruptedException {
        while(tuneOn) {
            wait();            // приостановление вызывающей задачи и освобождение блокировки
        }
    }
}
