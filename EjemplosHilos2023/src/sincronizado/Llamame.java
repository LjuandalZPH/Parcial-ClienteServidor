package sincronizado;

public class Llamame 
{
    private int valor;
    
    public synchronized void metodo(String msg, int v)
    {
        valor += v;
        System.out.println("["+msg+ " valor "+valor+"]");
    }
    
}
