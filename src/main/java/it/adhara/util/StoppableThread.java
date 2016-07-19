package it.adhara.util;

/**
 * Created by Adhara on 19/07/16.
 */
public class StoppableThread implements IStoppable {

    private final Thread m_thread;

    private StoppableThread(Thread thread){
        m_thread = thread;
    }

    public static IStoppable create(String name, final IThreadStrategy strategy){
        Thread t = new Thread(new Runnable(){

            public void run() {
                strategy.execute();
            }
        }, name);
        t.start();

        return new StoppableThread(t);
    }


    public void stop() {
        m_thread.interrupt();
        try {
            m_thread.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
    }
}
