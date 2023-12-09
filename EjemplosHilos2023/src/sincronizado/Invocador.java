package sincronizado;

/**
 *
 * @author leovi
 */
public class Invocador implements Runnable
{
    private String msg;
    private Llamame tarjet;
    private int value;
    
    public Invocador(String ms, int v, Llamame t)
    {
        msg = ms;
        value = v;
        tarjet = t;
        
        new Thread(this).start();
    }

    @Override
    public void run() 
    {
        tarjet.metodo(msg, value);
        
    }
    
}
