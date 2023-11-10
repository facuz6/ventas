/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
/**
 *
 * @author colom
 */



public class Conexion {
    private Connection conexion;

    public Conexion() {
        // La conexión se abre solo cuando se instancia la clase, pero no se cierra aquí.
        // Debes cerrarla de manera explícita en el código que la use.
    }

    public Connection getConexion() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            String jdbcUrl = "jdbc:mysql://localhost:3306/sistemaventas?serverTimeZone=UTC&autoReconnect=true";
            conexion = DriverManager.getConnection(jdbcUrl, "root", "");
            System.out.println("Conexión exitosa a la base de datos");
        }
        return conexion;
    }

    public void cerrarConexion() throws SQLException {
        if (conexion != null && !conexion.isClosed()) {
            conexion.close();
            System.out.println("Conexión cerrada");
        }
    }
}
