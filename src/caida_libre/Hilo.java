/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package caida_libre;

/**
 *
 * @author Benlly
 */
public class Hilo extends Thread
{

    public Hilo(Ventana caidalibre){
        msg = 0;
        pause = true;
        parent = caidalibre;
    }

    public void run(){
        long l = System.currentTimeMillis();
        do{
            int i = getMsg();
            parent.canvas.mover();
            if(i == 1){
                pause = true;
            }
            try{//detencion del hilo durante cierto tiempo
                Thread.sleep(Math.max(20L, l - System.currentTimeMillis()));
            }
            catch(InterruptedException interruptedexception){
                return;
            }
        } while(true);
    }

    public synchronized int getMsg(){
        while(pause) {
            try {
                wait(1000L);
            } catch (InterruptedException interruptedexception) {
            }
        }
        return msg;
    }

    public synchronized void putMsg(int i)
    {
        msg = i;
        pause = msg != 2 ? msg != 1 : false;
        notify();
    }

    Ventana parent;
    public static final int PAUSE = 0;
    public static final int STEP = 1;
    public static final int RUN = 2;
    int msg;
    boolean pause;
}
