/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JTable;


/**
 *
 * @author colom
 */
public class ClienteDAO {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean registrarCliente(Cliente cl){
        String sql = "INSERT INTO clientes (DNI,nombre,telefono,direccion,razon) VALUES(?,?,?,?,?)";//Inserta los valores en la base de datos
        
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, cl.getDNI());//Setea los valores correspondientes a través de los atributos del objeto cliente
            ps.setString(2, cl.getNombre());
            ps.setInt(3, cl.getTelefono());
            ps.setString(4, cl.getDireccion());
            ps.setString(5, cl.getRazon());
            
            ps.execute();
            ps.close();
            return true;
        } catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
    }
    
 public void eliminarCliente(JTable tabla){
     int filaSel = tabla.getSelectedRow();//Almacena la fila selecionada
     String id = tabla.getValueAt(filaSel, 0).toString();//Obtiene su ID
     
     try {
         String SQL="DELETE FROM clientes WHERE id='"+id+"'"; //Elimina la fila mediante SQL
         ps=con.prepareStatement(SQL);
         ps.executeUpdate();
         ps.close();
     } catch (SQLException e) {
         System.out.println(e.toString());
     }
 }
 
  

 
    public List listaCliente(){//Método que devuelve una lista
        List<Cliente> listaCl = new ArrayList();//Crea el array que va a contener elementos del tipo Cliente
        String SQL = "SELECT * FROM clientes";
        try {
            con = cn.getConexion();
            ps=con.prepareStatement(SQL);
            rs=ps.executeQuery();
            while(rs.next()){//Mientras la tabla continue con filas no vacias
                Cliente cl = new Cliente();
                cl.setID(rs.getInt("id"));
                cl.setDNI(rs.getInt("DNI"));
                cl.setNombre(rs.getString("nombre"));
                cl.setTelefono(rs.getInt("telefono"));
                cl.setDireccion(rs.getString("direccion"));
                cl.setRazon(rs.getString("razon"));
                listaCl.add(cl);//Agrega el objeto cliente al array
            }
            ps.close();
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        
        
        return listaCl;
    }


    public void modificarCliente(JTable tabla,Cliente cl){
        int filaSel = tabla.getSelectedRow();
        String id = tabla.getValueAt(filaSel, 0).toString();//Almacena la fila seleccionada y su respectivo ID

        try {
            String SQL="UPDATE clientes SET DNI=?,nombre=?,telefono=?,direccion=?,razon=?"
                    + "WHERE id='"+id+"'"; //Consulta para actualizar la fila
            ps = con.prepareStatement(SQL);
            ps.setInt(1, cl.getDNI());
            ps.setString(2, cl.getNombre());
            ps.setInt(3, cl.getTelefono());
            ps.setString(4, cl.getDireccion());
            ps.setString(5, cl.getRazon());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
 
}
