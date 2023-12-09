import java.io.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class EjemploFile 
{
    public static void main(String[] args) 
    {
        File miArchivo = new File("src//prueba.txt");
        String linea;
        FileReader fr = null;
        try {
            fr = new FileReader(miArchivo);
        } catch (FileNotFoundException ex) {
            System.out.println("No encontre el archivo");
        }
        //El buffer es para traer la linea completa
        BufferedReader br = new BufferedReader(fr);
        
        
        try {
            while((linea=br.readLine())!=null)
            {
                System.out.println(linea);
            }
        } catch (IOException ex) {
            System.out.println("No pude leer la linea");
        } finally {
            try {
                fr.close(); //Cerrando el flujo
            } catch (IOException ex){
                System.out.println("Tuve problemas cerrando el flujo");
            }
        }
    }
    
}
