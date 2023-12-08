
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author usuario
 */
public class ClienteDesdeCero  extends JFrame{

    JTextField campoIntroducir;
    JTextArea areaPantalla;
    JScrollPane barras;
    
    Socket cliente;
    ObjectOutputStream salida;
    ObjectInputStream entrada;
    
    public ClienteDesdeCero()
    {
        setTitle("Cliente");
        setVisible(true);
        setLocationRelativeTo(null);
        setSize(350,470);
        creaGui();
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void creaGui()
    {
     campoIntroducir = new JTextField();
     areaPantalla = new JTextArea(25, 20);
     barras = new JScrollPane(areaPantalla);
     
     campoIntroducir.setEnabled(false);
     add(campoIntroducir, BorderLayout.SOUTH);
     add(barras, BorderLayout.CENTER);
     revalidate();

     
     ManejaEventosClientes ev = new ManejaEventosClientes();
     campoIntroducir.addActionListener(ev);
    }
    
    public void mostrarMensaje(String mensaje)
    {
        areaPantalla.append(mensaje);
    }
    
    public void ejecutarCliente()
    {
        try
        {
            conectarAlServidor();
            obtenerFlujos();
            procesarConexion();
        }catch(IOException ioe){
            mostrarMensaje("error en la conexion con el cliente");
        }finally{
            cerrarConexion();
        }
    }
   

    public void conectarAlServidor() throws IOException
    {
        mostrarMensaje("Intentando estrablecer conexion.....");
        cliente = new Socket("127.0.0.1", 1234);
        mostrarMensaje("Conectado a: "+cliente.getInetAddress());
    }
    
    public void obtenerFlujos() throws IOException
    {
        salida = new ObjectOutputStream(cliente.getOutputStream());
        salida.flush();
        entrada = new ObjectInputStream(cliente.getInputStream());
        mostrarMensaje("Se obtuvieron los flujos");
    }

    public void procesarConexion() throws IOException
    {
        String mensaje = "";
        campoIntroducir.setEnabled(true);
        do
        {
            try {
                mensaje = (String) entrada.readObject();
                mostrarMensaje("\n"+mensaje);
            } catch (ClassNotFoundException ex) {
                mostrarMensaje("error tipo de dato incorrecto");
            }
            
        }while(!mensaje.equals("SERVIDOR>>> TERMINAR"));
    }
    
    public void cerrarConexion()
    {
        mostrarMensaje("cerrando conexion...");
        try {
            salida.close();
            entrada.close();
            cliente.close();
            campoIntroducir.setEnabled(false);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    public void enviarDatos(String mensaje)
    {
        try {
            salida.writeObject("CLIENTE>>> "+mensaje+"\n");
            salida.flush();
            mostrarMensaje("CLIENTE>>> "+mensaje+"\n");
        } catch (IOException ex) {
            mostrarMensaje("Error al mandar daatos al servidor");
        }
        
    }

    public static void main(String[] args) {
        ClienteDesdeCero cliente = new ClienteDesdeCero();
        cliente.ejecutarCliente();
    }
    class ManejaEventosClientes implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
            enviarDatos(e.getActionCommand());
            campoIntroducir.setText("");
        }
    }
}
