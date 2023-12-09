package Ejemplo1;

/**
 *
 * @author leovi
 */
public class HiloPrueba extends Thread
{
    String nombre;
    int espera;
    
    /**
     * constructor para crear un hilo con un nombre y un tiempo
     * que es el tiempo que se va a dormir el hilo
     * @param nom
     * @param tiempo 
     */
    public HiloPrueba(String nom, int tiempo)
    {
        nombre = nom;
        espera = tiempo;
    }
    
    @Override
    public void run()
    {
        for(int i =  0; i < 15; i++)
        {
            try {
                System.out.println("El hilo "+nombre+"va a esperar "+espera+" milisegundos");
                sleep(espera);
            } catch (InterruptedException ex) {
                System.out.println("se ha interrumpido el hilo");
            }
        }//fin for
        System.out.println("Se terminó el hilo "+this.getName());
        
    }
    
}
