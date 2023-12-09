package busquedaDato;

/**
 *
 * @author leovi
 */
public class LlamaBusquedas 
{
    public static void main(String[] args) 
    {
        int array[] = {3,50,43,34,-1,1,0,87,12,-3,11};
        int dato = 1;
        BusquedaInicio hInicio = new BusquedaInicio(array, dato);
        Thread hfin = new Thread(new BusquedaFin(array, dato));
        hInicio.start();
        hfin.start();
        System.out.println("SE TERMINÓ EL MAIN");
        
        
    }
    
}
