// Decompiled by DJ v3.9.9.91 Copyright 2005 Atanas Neshkov  Date: 04/10/2009 17:29:01
// Home Page : http://members.fortunecity.com/neshkov/dj.html  - Check often for new version!
// Decompiler options: packimports(3)
// Source File Name:   Hilo.java

package Rozamiento;


// Referenced classes of package rozamiento9:
//            RozamientoApplet9, MiCanvas

public class Hilo extends Thread
{

    public Hilo(Rozamiento p)
    {
        msg = 0;
        pause = true;
        parent = p;
    }

    public void run()
    {
        long time = System.currentTimeMillis();
        do
        {
            int m = getMsg();
            parent.canvas.mover();
            if(m == 1)
                pause = true;
            try
            {
                Thread.sleep(Math.max(30L, time - System.currentTimeMillis()));
            }
            catch(InterruptedException e)
            {
                return;
            }
        } while(true);
    }

    public synchronized int getMsg()
    {
        while(pause)
            try
            {
                wait(1000L);
            }
            catch(InterruptedException interruptedexception) { }
        return msg;
    }

    public synchronized void putMsg(int m)
    {
        msg = m;
        pause = msg != 2 ? msg != 1 : false;
        notify();
    }

    Rozamiento parent;
    public static final int PAUSE = 0;
    public static final int STEP = 1;
    public static final int RUN = 2;
    int msg;
    boolean pause;
}