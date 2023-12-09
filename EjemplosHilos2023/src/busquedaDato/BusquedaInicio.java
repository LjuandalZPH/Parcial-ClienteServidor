package busquedaDato;

/**
 *
 * @author leovi
 */
public class BusquedaInicio extends Thread
{
    int arreglo[];
    int datoABuscar, numOperaciones;
    boolean termina;
    
    public BusquedaInicio(int arr[], int dato)
    {
        arreglo = arr;
        datoABuscar = dato;
    }
    
    @Override
    public void run()
    {
        int inicio = 0;
        int fin = (arreglo.length)/2;
        System.out.println("I Busqueda inicio "+inicio+" hasta "+fin);
        for(int i = inicio; i < fin; i++)
        {
            try
            {
                sleep(1000);
            }catch(InterruptedException ex)
            {
                System.out.println("se interrumpió el hilo");
            }
            numOperaciones++;
            System.out.println("I - num operaciones "+numOperaciones);
            if(arreglo[i] == datoABuscar)
            {
                termina = true;
                System.out.println("I - encontró el dato "+datoABuscar+" en posicion: "+i);
                return;
            }
        }//fin for
        if(termina == false)
            System.out.println("I - No encontró el dato");
        
    }
}
