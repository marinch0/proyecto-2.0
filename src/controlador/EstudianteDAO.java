/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.EstudianteVO;

/**
 *
 * @author SFS
 */
public class EstudianteDAO {
    private Connection conexion;
    private Conexion conector;
    
    public EstudianteDAO(){
        conector = new Conexion();
        conexion = conector.conexionSQL();
    }
    
    public boolean ingresarEstudiante(EstudianteVO estudiante) {
        
        PreparedStatement pstmt;
        try {
            pstmt = conexion.prepareStatement("insert into estudiante (codigo_est,id_est,nombre_est,apellido_est,celular_est,correo_est,direccion_est,grado_est,ciudad_est,acudiente_est,celular_acu_est,correo_acu_est,direccion_acu_est,id_func,estado_est)"
                    +"values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
       
        pstmt.setString(1,estudiante.getCodigo());
        pstmt.setString(2,estudiante.getIdentificacion());
        pstmt.setString(3,estudiante.getNombre());
        pstmt.setString(4,estudiante.getApellido());
        pstmt.setString(5,estudiante.getCelular());
        pstmt.setString(6,estudiante.getCorreo());
        pstmt.setString(7,estudiante.getDireccion());
        pstmt.setString(8,estudiante.getGrado());
        pstmt.setString(9,estudiante.getCiudad());
        pstmt.setString(10,estudiante.getNombreAcudiente());
        pstmt.setString(11,estudiante.getCelularAcudiente());
        pstmt.setString(12,estudiante.getMailAcudiente());
        pstmt.setString(13,estudiante.getDireccionAcudiente());
        pstmt.setString(14,estudiante.getIdFunc());
        pstmt.setBoolean(15,estudiante.isEstado());
        
        pstmt.executeUpdate();
         } catch (SQLException ex) {
            Logger.getLogger(EstudianteDAO.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(null,
					"No se guardo la información\nIdentificación ya existe, puede estar inactivo\n" + ex,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
			return false;
        }
        return true;
    
    }
    
    public ArrayList<String> listaEstudiante(){
        Statement stms;
        ArrayList<String> lista = new ArrayList<>();
        
        try {
            stms = conexion.createStatement();
            ResultSet resultado = stms.executeQuery("select codigo_est,id_est,nombre_est,apellido_est,celular_est,correo_est,direccion_est,grado_est,ciudad_est,acudiente_est,celular_acu_est,correo_acu_est,direccion_acu_est  from estudiante where estado_est=1");
            
            while(resultado.next()==true){
         
            lista.add(resultado.getString("codigo_est"));
            lista.add(resultado.getString("id_est"));            
            lista.add(resultado.getString("nombre_est"));
            lista.add(resultado.getString("apellido_est"));
            lista.add(resultado.getString("celular_est"));
            lista.add(resultado.getString("correo_est"));
            lista.add(resultado.getString("direccion_est"));
            lista.add(resultado.getString("grado_est"));
            lista.add(resultado.getString("ciudad_est"));
            lista.add(resultado.getString("acudiente_est"));            
            lista.add(resultado.getString("celular_acu_est"));
            lista.add(resultado.getString("correo_acu_est"));
            lista.add(resultado.getString("direccion_acu_est"));
            
         
         }
        return lista;
         } catch (SQLException ex) {
            Logger.getLogger(EstudianteDAO.class.getName()).log(Level.SEVERE, null, ex);
             ex.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"La consulta de estudiantes esta mal\n" + ex,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
                        
        }
        return null;
    }
    
    public boolean modificarEstudiante(EstudianteVO estudiante){
        
        try {
            PreparedStatement pstmt = conexion.prepareStatement("update estudiante set codigo_est=?,id_est=?,nombre_est=?,apellido_est=?,celular_est=?,correo_est=?,direccion_est=?,grado_est=?,ciudad_est=?,acudiente_est=?,celular_acu_est=?,correo_acu_est=?,direccion_acu_est=? where codigo_est=?");
            
        pstmt.setString(1,estudiante.getCodigo());
        pstmt.setString(2,estudiante.getIdentificacion());
        pstmt.setString(3,estudiante.getNombre());
        pstmt.setString(4,estudiante.getApellido());
        pstmt.setString(5,estudiante.getCelular());
        pstmt.setString(6,estudiante.getCorreo());
        pstmt.setString(7,estudiante.getDireccion());
        pstmt.setString(8,estudiante.getGrado());
        pstmt.setString(9,estudiante.getCiudad());
        pstmt.setString(10,estudiante.getNombreAcudiente());
        pstmt.setString(11,estudiante.getCelularAcudiente());
        pstmt.setString(12,estudiante.getMailAcudiente());
        pstmt.setString(13, estudiante.getDireccionAcudiente());
        pstmt.setString(14,estudiante.getCodigo());
            pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(EstudianteDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
					"No se realizo la actualización\n" + ex,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
			return false;
        }
        return true;
    }
    
    public boolean modificarEstadoEstudiante(String codEst){
        
        try {
            PreparedStatement pstmt = conexion.prepareStatement("update estudiante set estado_est=? where codigo_est=?");
            pstmt.setBoolean(1,false);
            pstmt.setString(2, codEst);
            pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(EstudianteDAO.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(null,
					"No se realizo la eliminación\n" + ex,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
			return false;
        }
        return true;
    }
    
        
    public ArrayList<String> buscarEstudiante(String codEst){
        PreparedStatement pstmt;
        ResultSet resultado;
        ArrayList<String> estudiante = new ArrayList<>();
        try {
            pstmt=conexion.prepareStatement("select codigo_est,id_est,nombre_est,apellido_est,celular_est,correo_est,direccion_est,grado_est,ciudad_est,acudiente_est,celular_acu_est,correo_acu_est,direccion_acu_est,estado_est  from estudiante where codigo_est=?");
            pstmt.setString(1,codEst);
            
            resultado=pstmt.executeQuery();
                            
            while (resultado.next() == true) {
                
	    estudiante.add(resultado.getString("codigo_est"));
            estudiante.add(resultado.getString("id_est"));            
            estudiante.add(resultado.getString("nombre_est"));
            estudiante.add(resultado.getString("apellido_est"));
            estudiante.add(resultado.getString("celular_est"));
            estudiante.add(resultado.getString("correo_est"));
            estudiante.add(resultado.getString("direccion_est"));
            estudiante.add(resultado.getString("grado_est"));
            estudiante.add(resultado.getString("ciudad_est"));
            estudiante.add(resultado.getString("acudiente_est"));            
            estudiante.add(resultado.getString("celular_acu_est"));
            estudiante.add(resultado.getString("correo_acu_est"));
            estudiante.add(resultado.getString("direccion_acu_est"));
            estudiante.add(resultado.getString("estado_est"));
            
            
	}
        return estudiante;                   
        } catch (SQLException ex) {
            Logger.getLogger(EstudianteDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
					"No se realizo la busqueda\n" + ex,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
			
        }
	return null;
	
        
    }
    
    
       
    
}
