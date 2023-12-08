
package servidordesdecero;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import static javax.swing.WindowConstants.EXIT_ON_CLOSE;

/**
 *
 * @author usuario
 */
public class GUI extends JFrame
{
    JTextField areaParaEscribir;
    JTextArea areaPantalla;
    JScrollPane barras;
    
    public GUI()
    {
        setTitle("Servidor");
        setSize(350,470);
        creaGui();
        setLocationRelativeTo(null);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }
    
    public void creaGui()
    {
     areaParaEscribir = new JTextField();
     areaPantalla = new JTextArea(25, 20);
     areaPantalla.setEditable(false);
     barras = new JScrollPane(areaPantalla);
     
     areaParaEscribir.setEnabled(false);
     add(areaParaEscribir, BorderLayout.SOUTH);
     add(barras, BorderLayout.CENTER);
     
      ManejadoraEventos ev = new ManejadoraEventos();
      areaParaEscribir.addActionListener(ev);
  
    }
    public void limpiarCampo()
    {
        areaParaEscribir.setText("");
    }
    

    public void mensajePantalla(String mensaje)
    {
        areaPantalla.append(mensaje);
    }
    
   
    
    
    class ManejadoraEventos implements ActionListener
    {

        @Override
        public void actionPerformed(ActionEvent e) 
        {
           
            LogicaServidor.actuar(e.getActionCommand());
        }

    }
}    

            

   
 
