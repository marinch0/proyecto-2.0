/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.Connection;
import java.sql.DriverManager;
import javax.swing.JOptionPane;

/**
 *
 * @author SFS
 */

//clase que permite la conexión con la base de datos
public class Conexion {
    
    Connection conectar = null;
    
    //metodo constructor llama metodo de conexión
    public Conexion(){
        conexionSQL();
    }
    
    public Connection conexionSQL(){
        try{
            
            //cargar el driver MySQL
            Class.forName("com.mysql.jdbc.Driver");
           // conectar=(Connection)DriverManager.getConnection("jdbc:mysql://localhost:3307/DBTransporte","root","root");
            conectar = (Connection) DriverManager.getConnection("jdbc:mysql://localhost/transporte","root","root");
            }catch(Exception e){
            JOptionPane.showMessageDialog(null, "Error "+e);
        }
        
        return conectar;
    }
    
    
}
