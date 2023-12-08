
package servidordesdecero;


import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author usuario
 */
public class LogicaServidor {
    /**
     * Los sockets enlazan comunicacion entre dos programas
     */
    //Servidor como tal
    static ServerSocket servidor;
    //cliente como tal
    static Socket conexion;
    static int contador = 1;
    //Flujos
    static ObjectInputStream entrada;
    static ObjectOutputStream salida;
    
    //Aprovechamos y ponemos el servidor aqui, ya que los metodos no estaticos nos darian
    //problema si no hacemos esto 
     static GUI interfaz;

    public void ejecutarServidor()
    {
        interfaz = new GUI();

        try {
            servidor = new ServerSocket(1234, 1);
            while(true)
            {
                esperarConexion();
                obtenerFlujos();
                procesarConexion();
            }
        } catch (IOException ex) {
            Logger.getLogger(LogicaServidor.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
    public void esperarConexion() throws IOException
    {
       interfaz.mensajePantalla("Esperando la conexion.. \n");
       //Este metodo frena todo lo demas hasta que acepte el cliente
       conexion = servidor.accept();
       interfaz.mensajePantalla("Conexion hecha Cliente: "+(int)contador+" conectado.");
       
    }
    public void obtenerFlujos() throws IOException
    {
        salida = new ObjectOutputStream(conexion.getOutputStream()); 
        salida.flush();
        entrada = new ObjectInputStream(conexion.getInputStream()) ;
        interfaz.mensajePantalla("Se obtuvieron flujos\n");
    }
    public void procesarConexion()
    {
        String mensaje = "Conexion exitosa";
        
        enviarDatos(mensaje);
        interfaz.areaParaEscribir.setEnabled(true);
        
        do {
            try {

                mensaje = (String)entrada.readObject();
                if(tieneNumero(mensaje) == true)
                {
                    enviarDatos("No mandes numero, bastardo");
                }
                else
                {
                    interfaz.mensajePantalla(mensaje);
                    enviarDatos("La cantidad de palabras son: "+String.valueOf(contarPalabras(mensaje)));
                    enviarDatos(contarVocales(mensaje));
                }
              
                

                
            } catch (IOException ex) {
                Logger.getLogger(LogicaServidor.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(LogicaServidor.class.getName()).log(Level.SEVERE, null, ex);
            }
        } while (true);
        
    }
    public static void enviarDatos(String msj)
    {
        try {
            
            salida.writeObject("Servidor >>"+msj+"\n");
            salida.flush();
            interfaz.mensajePantalla("Servidor >>"+msj+"\n");
        } catch (IOException ex) {
          interfaz.mensajePantalla("\n Error al escribir objeto");
        }
    }
     public static void actuar(String mensajeDelCampo)
    {
        enviarDatos(mensajeDelCampo);
        interfaz.limpiarCampo();

    }
    private boolean tieneNumero(String msj) 
    {
    for (char C : msj.toCharArray()) 
      {
        if (Character.isDigit(C)) 
        {
            return true;
        }
    }
        return false;
      }
    public int contarPalabras(String msj)
    {
      String[] Pal = msj.split(",");
      return Pal.length;
    }
    public String contarVocales(String mensajes)
    {
        int[] vocalesarray = new int[5];
              
      String mensajeListo = mensajes.toLowerCase();

      for(char c : mensajeListo.toCharArray())
          switch(c)
          {
              case 'a': 
                  vocalesarray[0]++;
              break;
              case 'e':
                  vocalesarray[1]++;
              break;
              case 'i':
                  vocalesarray[2]++;
              break;
              case 'o':
                  vocalesarray[3]++;
              break;
              case 'u':
                  vocalesarray[4]++;
              break;
          }
      int nuevo = vocalesarray[1];
      int correcion = nuevo - 2;
      vocalesarray[1] = correcion;
      
      int nuevo2 = vocalesarray[2];
      int correcion2 = nuevo - 1;
      vocalesarray[2] = correcion;
      
      return "A: "+vocalesarray[0]+"\n"+ "E: "+vocalesarray[1]+"\n"+ "I: "+vocalesarray[2]+"\n"+ "O:"+vocalesarray[3]
              +"\n"+"U: "+vocalesarray[4];
    }
}
