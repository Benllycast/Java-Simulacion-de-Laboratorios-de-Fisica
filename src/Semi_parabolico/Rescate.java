package Semi_parabolico;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author River
 */
public class Rescate extends Thread{

    Alaska aux;
     int msg;
    private boolean pause;
    
   public Rescate(Alaska a) {
        aux=a;
    }

    @Override
      public void run()
    {
        long time = System.currentTimeMillis();
        do
        {
            int m = getMsg();
                    aux.Mover();
            if(m == 1)
                pause = true;
            try
            {
                Thread.sleep(Math.max(10L, time - System.currentTimeMillis()));
            }
            catch(InterruptedException e)
            {
                return;
            }
        } while(true);
    }

    void putMsg(int i) {
         msg = i;
        pause = msg != 2 ? msg != 1 : false;
 }

    public synchronized int getMsg() {
        while(pause) 
            try
            {
                wait(1000L);
            }
            catch(InterruptedException interruptedexception) { }
        return msg;
    }
    
}
