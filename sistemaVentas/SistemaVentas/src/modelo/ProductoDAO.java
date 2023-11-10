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
import javax.swing.JComboBox;
import javax.swing.JTable;

/**
 *
 * @author colom
 */
public class ProductoDAO {
    Connection con;
    Conexion cn = new Conexion();
    PreparedStatement ps;
    ResultSet rs;
    
    public String comprobarCodigoRepetido(Producto prod){
        try {
            String SQL="SELECT codigo FROM productos";
            con = cn.getConexion();
            ps = con.prepareStatement(SQL);
            rs=ps.executeQuery();
            String codigo=prod.getCodigo();
            
            
            while(rs.next()){
                
                 
                if(rs.getString(1).equals(codigo)){
                    return codigo;
                    
                }
               
                
            }
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
        return "";
        
    }
    
    public void rellenarListaProveedores(JComboBox lista){
        String SQL = "SELECT nombre FROM proveedor";
        
        try {
            con= cn.getConexion();
            ps = con.prepareStatement(SQL);
            rs=ps.executeQuery();
            while(rs.next()){
                lista.addItem(rs.getString(1));
            }
            
            
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
    
    public boolean actualizarStockPrecio(Producto prod,String codigo){
        
        try {
            
            
             
            String sql="UPDATE productos SET stock=stock+?,precio=(precio*stock+?*?)/(stock+?) WHERE codigo=?";
                ps = con.prepareStatement(sql);
                ps.setInt(1, prod.getStock());
                ps.setFloat(2, prod.getPrecio());
                ps.setInt(3, prod.getStock());
                ps.setInt(4, prod.getStock());
                ps.setString(5,codigo);
                ps.executeUpdate();
                ps.close();
                
                
                
        } catch (SQLException e) {
             System.out.println(e.toString());
             return false;
        }
        
         return true;
    }
    
    public boolean registrarProducto(Producto prod){
        try {
           
                String sql = "INSERT INTO productos (codigo,descripcion,proveedor,stock,precio) VALUES(?,?,?,?,?)";
                con = cn.getConexion();
                ps=con.prepareStatement(sql);
                ps.setString(1, prod.getCodigo());
                ps.setString(2, prod.getDescripcion());
                ps.setString(3, prod.getProveedor());
                ps.setInt(4, prod.getStock());
                ps.setFloat(5, prod.getPrecio());

                ps.execute();
            }
            
           
         catch (SQLException e) {
            System.out.println(e.toString());
            return false;
        }
         return true;
    }
    
    public void modificarProducto(JTable tabla,Producto prod){
        int filaSel = tabla.getSelectedRow();
        String codigo = tabla.getValueAt(filaSel, 0).toString();//Almacena la fila seleccionada y su código

        try {
            String SQL="UPDATE productos SET codigo=?,descripcion=?,proveedor=?,stock=?,precio=? "
                    + "WHERE codigo=?"; //Consulta para actualizar la fila
            ps = con.prepareStatement(SQL);
            ps.setString(1, prod.getCodigo());
            ps.setString(2, prod.getDescripcion());
            ps.setString(3, prod.getProveedor());
            ps.setInt(4, prod.getStock());
            ps.setFloat(5, prod.getPrecio());
            ps.setString(6, codigo);
            ps.executeUpdate();
            ps.close();
        } catch (SQLException e) {
            System.out.println(e.toString());
        }
    }
    
     public List listaProducto(){
        List<Producto> listaProd = new ArrayList();
        String SQL = "SELECT * FROM productos";
        try {
            con = cn.getConexion();
            ps=con.prepareStatement(SQL);
            rs=ps.executeQuery();
            while(rs.next()){
                Producto prod = new Producto();
                prod.setPrecio(rs.getFloat("precio"));
                prod.setStock(rs.getInt("stock"));
                prod.setCodigo(rs.getString("codigo"));
                prod.setProveedor(rs.getString("proveedor"));
                prod.setDescripcion(rs.getString("descripcion"));
                listaProd.add(prod);
            }
            
        } catch (Exception e) {
            System.out.println(e.toString());
        }
        
        return listaProd;
    }
    
     public void eliminarProducto(JTable tabla){
     int filaSel = tabla.getSelectedRow();//Almacena la fila selecionada
     String codigo = tabla.getValueAt(filaSel, 0).toString();//Obtiene su código
     
     try {
         String SQL="DELETE FROM productos WHERE codigo=?"; //Elimina la fila mediante SQL
         ps=con.prepareStatement(SQL);
         ps.setString(1, codigo);
         ps.executeUpdate();
         ps.close();
     } catch (SQLException e) {
         System.out.println(e.toString());
     }
 }
    
}
