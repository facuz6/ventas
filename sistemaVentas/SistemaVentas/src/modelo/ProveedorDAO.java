/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.List;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JTable;
/**
 *
 * @author Facu
 */
public class ProveedorDAO {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public boolean registrarProveedor(Proveedor pr){
        String sql = "INSERT INTO proveedor (CUIT, nombre, telefono, direccion, razon) VALUES(?,?,?,?,?)";
        
        try {
            con = cn.getConexion();
            ps = con.prepareStatement(sql);
            ps.setInt(1, pr.getCUIT());
            ps.setString(2, pr.getNombre());
            ps.setInt(3, pr.getTelefono());
            ps.setString(4, pr.getDireccion());
            ps.setString(5, pr.getRazon());
            
            ps.execute();
            return true;
        } catch (Exception e) {
            System.out.println(e.toString());
            return false;
        }
    }
    
    public List listaProveedor(){
        List<Proveedor> listaPr = new ArrayList();
        String SQL = "SELECT * FROM proveedor";
        try {
            con = cn.getConexion();
            ps=con.prepareStatement(SQL);
            rs=ps.executeQuery();
            while(rs.next()){
                Proveedor pr = new Proveedor();
                pr.setID(rs.getInt("id"));
                pr.setCUIT(rs.getInt("CUIT"));
                pr.setNombre(rs.getString("nombre"));
                pr.setTelefono(rs.getInt("telefono"));
                pr.setDireccion(rs.getString("direccion"));
                pr.setRazon(rs.getString("razon"));
                listaPr.add(pr);
            }
            
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
        return listaPr;
    }
    
    public void modificarProveedor(JTable tabla,Proveedor pr){
        int filaSel = tabla.getSelectedRow();
        String id = tabla.getValueAt(filaSel, 0).toString();//Almacena la fila seleccionada y su respectivo ID

        try {
            String SQL="UPDATE proveedor SET CUIT=?,nombre=?,telefono=?,direccion=?,razon=? "
                    + "WHERE id='"+id+"'"; //Consulta para actualizar la fila
            ps = con.prepareStatement(SQL);
            ps.setInt(1, pr.getCUIT());
            ps.setString(2, pr.getNombre());
            ps.setInt(3, pr.getTelefono());
            ps.setString(4, pr.getDireccion());
            ps.setString(5, pr.getRazon());
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
    
    public void eliminarProveedor(JTable tabla){
        int filaSel = tabla.getSelectedRow();//Almacena la fila selecionada
        String id = tabla.getValueAt(filaSel, 0).toString();//Obtiene su ID

        try {
            String SQL="DELETE FROM proveedor WHERE id='"+id+"'"; //Elimina la fila mediante SQL
            ps=con.prepareStatement(SQL);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
     
    
}
