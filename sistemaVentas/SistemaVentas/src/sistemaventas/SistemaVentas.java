/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package sistemaventas;
import vistas.Login;
import vistas.Sistemas;
import javax.swing.JFrame;
        


/**
 *
 * @author facu
 */
public class SistemaVentas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       Login ventanaLogin= new Login();
        ventanaLogin.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaLogin.pack(); 
        ventanaLogin.setVisible(true); 
       
    }
    
}
