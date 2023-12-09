package busquedaDato;

import static java.lang.Thread.sleep;

/**
 *
 * @author leovi
 */
public class BusquedaFin implements Runnable
{
    int arreglo[];
    int datoABuscar, numOperaciones;
    boolean termina;
    
    public BusquedaFin(int arr[], int dato)
    {
        arreglo = arr;
        datoABuscar = dato;
    }

    @Override
    public void run() 
    {
        int inicio = arreglo.length - 1;
        int fin = (inicio+1)/2;
        System.out.println("II Busqueda FIN desde "+inicio+" hasta "+fin);
        for(int i = inicio; i >= fin; i--)
        {
            try
            {
                sleep(1000);
            }catch(InterruptedException ex)
            {
                System.out.println("se interrumpió el hilo");
            }
            numOperaciones++;
            System.out.println("II - num operaciones "+numOperaciones);
            if(arreglo[i] == datoABuscar)
            {
                termina = true;
                System.out.println("II - encontró el dato "+datoABuscar+" en posicion: "+i);
                return;
            }
        }//fin for
        if(termina == false)
            System.out.println("II - No encontró el dato");
        
    
    }
}
