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
import modelo.ConductorVO;

/**
 *
 * @author SFS
 */
public class ConductorDAO {
    
    private Connection conexion;
    private Conexion conector;
    
    public ConductorDAO(){
        conector = new Conexion();
        conexion = conector.conexionSQL();
    }
    
    public boolean ingresarConductor(ConductorVO conductor) {
        
        PreparedStatement pstmt;
        try {
            pstmt = conexion.prepareStatement("insert into conductor (id_cond,nombre_cond,apellido_cond,ruta_foto_cond,celular_cond,correo_cond,direccion_cond,categoria_licencia,vigencia_licencia,ciudad_licencia,restriccion_licencia,ruta_foto_licencia,id_func,estado_cond)"
                    +"values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
       
        
        pstmt.setString(1,conductor.getIdentificacion());
        pstmt.setString(2,conductor.getNombre());
        pstmt.setString(3,conductor.getApellido());
        pstmt.setString(4,conductor.getRutaFoto());
        pstmt.setString(5,conductor.getCelular());
        pstmt.setString(6,conductor.getCorreo());
        pstmt.setString(7,conductor.getDireccion());
        pstmt.setString(8,conductor.getCatLin());
        java.sql.Date sqlDate = new java.sql.Date(conductor.getVigLin().getTime());
        pstmt.setDate(9,sqlDate);
        pstmt.setString(10,conductor.getCiuLin());
        pstmt.setString(11,conductor.getRestLin());
        pstmt.setString(12,conductor.getRutaFotoLin());
        pstmt.setString(13,conductor.getId_func());
        pstmt.setBoolean(14, conductor.isEstado());
        pstmt.executeUpdate();
        
         } catch (SQLException ex) {
            Logger.getLogger(ConductorDAO.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(null,
					"No se guardo la información\nIdentificación ya existe, puede estar inactivo\n" + ex,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
			return false;
        }
        return true;
    
    }
    
    public ArrayList<String> listaConductor(){
        Statement stms;
        ArrayList<String> lista = new ArrayList<>();
        
        try {
            stms = conexion.createStatement();
            ResultSet resultado = stms.executeQuery("select id_cond,nombre_cond,apellido_cond,ruta_foto_cond,celular_cond,correo_cond,direccion_cond,categoria_licencia,vigencia_licencia,ciudad_licencia,restriccion_licencia,ruta_foto_licencia  from conductor where estado_cond=1");
            
            while(resultado.next()==true)
            {
            
            lista.add(resultado.getString("id_cond"));
            lista.add(resultado.getString("nombre_cond"));
            lista.add(resultado.getString("apellido_cond"));
            lista.add(resultado.getString("ruta_foto_cond"));
            lista.add(resultado.getString("celular_cond"));
            lista.add(resultado.getString("correo_cond"));
            lista.add(resultado.getString("direccion_cond"));
            lista.add(resultado.getString("categoria_licencia"));
            lista.add(resultado.getString("vigencia_licencia"));
            lista.add(resultado.getString("ciudad_licencia"));
            lista.add(resultado.getString("restriccion_licencia"));
            lista.add(resultado.getString("ruta_foto_licencia"));
                                    
               
            }
        return lista;
         } catch (SQLException ex) {
            Logger.getLogger(ConductorDAO.class.getName()).log(Level.SEVERE, null, ex);
             ex.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"La consulta de conductors esta mal\n" + ex,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
                        
        }
        return null;
    }
    
    public boolean modificarConductor(ConductorVO conductor){
        
        try {
            PreparedStatement pstmt = conexion.prepareStatement("update conductor set id_cond=?,nombre_cond=?,apellido_cond=?,ruta_foto_cond=?,celular_cond=?,correo_cond=?,direccion_cond=?,categoria_licencia=?,vigencia_licencia=?,ciudad_licencia=?,restriccion_licencia=?,ruta_foto_licencia=? where id_cond=?");
            
        pstmt.setString(1,conductor.getIdentificacion());
        pstmt.setString(2,conductor.getNombre());
        pstmt.setString(3,conductor.getApellido());
        pstmt.setString(4,conductor.getRutaFoto());
        pstmt.setString(5,conductor.getCelular());
        pstmt.setString(6,conductor.getCorreo());
        pstmt.setString(7,conductor.getDireccion());
        pstmt.setString(8,conductor.getCatLin());
        java.sql.Date sqlDate = new java.sql.Date(conductor.getVigLin().getTime());
        pstmt.setDate(9,sqlDate);
        pstmt.setString(10,conductor.getCiuLin());
        pstmt.setString(11,conductor.getRestLin());
        pstmt.setString(12,conductor.getRutaFotoLin());
        pstmt.setString(13,conductor.getIdentificacion());
        pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ConductorDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
					"No se realizo la actualización\n" + ex,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
			return false;
        }
        return true;
    }
    
    public boolean modificarEstadoConductor(String idCond){
        
        try {
            PreparedStatement pstmt = conexion.prepareStatement("update conductor set estado_cond=? where id_cond=?");
            pstmt.setBoolean(1,false);
            pstmt.setString(2, idCond);
            pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(ConductorDAO.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(null,
					"No se realizo la eliminación\n" + ex,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
			return false;
        }
        return true;
    }
    
        
    public ArrayList<String> buscarConductor(String idCond){
        PreparedStatement pstmt;
        ResultSet resultado;
        ArrayList<String> conductor = new ArrayList<>();
        try {
            pstmt=conexion.prepareStatement("select id_cond,nombre_cond,apellido_cond,ruta_foto_cond,celular_cond,correo_cond,direccion_cond,categoria_licencia,vigencia_licencia,ciudad_licencia,restriccion_licencia,ruta_foto_licencia,estado_cond from conductor where id_cond=?");
            pstmt.setString(1,idCond);
            
            resultado=pstmt.executeQuery();
                            
            while (resultado.next() == true) {
                
	    conductor.add(resultado.getString("id_cond"));
            conductor.add(resultado.getString("nombre_cond"));
            conductor.add(resultado.getString("apellido_cond"));
            conductor.add(resultado.getString("ruta_foto_cond"));
            conductor.add(resultado.getString("celular_cond"));
            conductor.add(resultado.getString("correo_cond"));
            conductor.add(resultado.getString("direccion_cond"));
            conductor.add(resultado.getString("categoria_licencia"));
            conductor.add(resultado.getString("vigencia_licencia"));
            conductor.add(resultado.getString("ciudad_licencia"));
            conductor.add(resultado.getString("restriccion_licencia"));
            conductor.add(resultado.getString("ruta_foto_licencia"));
            conductor.add(resultado.getString("estado_cond"));
            
	}
        return conductor;                   
        } catch (SQLException ex) {
            Logger.getLogger(ConductorDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
					"No se realizo la busqueda\n" + ex,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
			
        }
	return null;
	
        
    }
    
    
     public String buscarRutaFoto(String id) throws SQLException{
        String resultado;
        PreparedStatement pstmt = conexion.prepareStatement("select ruta_foto_cond from conductor where id_cond=?");
        pstmt.setString(1, id);
        
        ResultSet resul= pstmt.executeQuery();
        
        if (resul.next() == true) {
			resultado = resul.getString("ruta_foto_cond");
			return resultado;

		} else {
			return null;
	        }
                       
    }
    
    public String buscarConductorVeh(String id) throws SQLException{
        String resultado;
        PreparedStatement pstmt = conexion.prepareStatement("select concat(nombre_cond,' ',apellido_cond) as nombre from conductor where id_cond=? and estado_cond=1");
        pstmt.setString(1, id);
        
        ResultSet resul= pstmt.executeQuery();
        
        if (resul.next() == true) {
			resultado = resul.getString("nombre");
			return resultado;

		} else {
			return null;
	        }
                       
    }
     
    public String buscarIdConductor(String cond) throws SQLException {

		String resultado;
		PreparedStatement pstmt;
		pstmt = conexion
				.prepareStatement("select id_cond from conductor where concat(nombre_cond,' ',apellido_cond)=?");
		pstmt.setString(1, cond);

		ResultSet res = pstmt.executeQuery();

		if (res.next() == true) {
			resultado = res.getString("id_cond");
			return resultado;

		} else {
			return null;
		}

	}
    
    public ArrayList<String> buscarListaConductor(){
        Statement stms;
        ArrayList<String> lista = new ArrayList<>();
        try{
            stms=conexion.createStatement();
            ResultSet resultado = stms.executeQuery("select concat(nombre_cond,' ',apellido_cond) as nombre from conductor where estado_cond=1 order by nombre_cond");
            
            while(resultado.next()==true)
            {
                lista.add(resultado.getString("nombre"));
            }
            
            return lista;
        }catch(Exception e){
            e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"La consulta de Conductor esta mal\n" + e,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
        }
            return null;
    }
    
}
