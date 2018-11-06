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
import modelo.AsistenteVO;

/**
 *
 * @author SFS
 */
public class AsistenteDAO {
    
    private Connection conexion;
    private Conexion conector;
    
    public AsistenteDAO(){
        conector = new Conexion();
        conexion = conector.conexionSQL();
    }
    
    public boolean ingresarAsistente(AsistenteVO asistente) {
        
        PreparedStatement pstmt;
        try {
            pstmt = conexion.prepareStatement("insert into asistente (id_asis,nombre_asis,apellido_asis,celular_asis,correo_asis,direccion_asis,ruta_foto_asis,id_func,estado_asis)"
                    +"values (?,?,?,?,?,?,?,?,?)");
       
        
        pstmt.setString(1,asistente.getIdentificacion());
        pstmt.setString(2,asistente.getNombre());
        pstmt.setString(3,asistente.getApellido());
        pstmt.setString(4,asistente.getCelular());
        pstmt.setString(5,asistente.getCorreo());
        pstmt.setString(6,asistente.getDireccion());
        pstmt.setString(7,asistente.getRutaFoto());
        pstmt.setString(8,asistente.getIdFunc());
        pstmt.setBoolean(9,asistente.isEstado());
        pstmt.executeUpdate();
         } catch (SQLException ex) {
            Logger.getLogger(AsistenteDAO.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(null,
					"No se guardo la información\nIdentificación ya existe, puede estar inactivo\n" + ex,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
			return false;
        }
        return true;
    
    }
    
    public ArrayList<String> listaAsistente(){
        Statement stms;
        ArrayList<String> lista = new ArrayList<>();
        
        try {
            stms = conexion.createStatement();
            ResultSet resultado = stms.executeQuery("select id_asis,nombre_asis,apellido_asis,celular_asis,correo_asis,direccion_asis,ruta_foto_asis  from asistente where estado_asis=1");
            
            while(resultado.next()==true)
            {
                lista.add(resultado.getString("id_asis"));
                lista.add(resultado.getString("nombre_asis"));
                lista.add(resultado.getString("apellido_asis"));
                lista.add(resultado.getString("celular_asis"));
                lista.add(resultado.getString("correo_asis"));
                lista.add(resultado.getString("direccion_asis"));
                lista.add(resultado.getString("ruta_foto_asis"));
                
                
               
            }
        return lista;
         } catch (SQLException ex) {
            Logger.getLogger(AsistenteDAO.class.getName()).log(Level.SEVERE, null, ex);
             ex.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"La consulta de asistentes esta mal\n" + ex,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
                        
        }
        return null;
    }
    
    public boolean modificarAsistente(AsistenteVO asistente){
        
        try {
            PreparedStatement pstmt = conexion.prepareStatement("update asistente set id_asis=?,nombre_asis=?,apellido_asis=?,celular_asis=?,correo_asis=?,direccion_asis=?,ruta_foto_asis=?,id_func=? where id_asis=?");
            
            pstmt.setString(1, asistente.getIdentificacion());
            pstmt.setString(2, asistente.getNombre());
            pstmt.setString(3, asistente.getApellido());
            pstmt.setString(4, asistente.getCelular());
            pstmt.setString(5, asistente.getCorreo());
            pstmt.setString(6, asistente.getDireccion());
            pstmt.setString(7, asistente.getRutaFoto());
            pstmt.setString(8, asistente.getIdFunc());
            pstmt.setString(9, asistente.getIdentificacion());
            pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(AsistenteDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
					"No se realizo la actualización\n" + ex,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
			return false;
        }
        return true;
    }
    
    public boolean modificarEstadoAsistente(String idAsis){
        
        try {
            PreparedStatement pstmt = conexion.prepareStatement("update asistente set estado_asis=? where id_asis=?");
            pstmt.setBoolean(1,false);
            pstmt.setString(2, idAsis);
            pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(AsistenteDAO.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(null,
					"No se realizo la eliminación\n" + ex,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
			return false;
        }
        return true;
    }
    
        
    public ArrayList<String> buscarAsistente(String idAsis){
        PreparedStatement pstmt;
        ResultSet resultado;
        ArrayList<String> asistente = new ArrayList<>();
        try {
            pstmt=conexion.prepareStatement("select id_asis, nombre_asis, apellido_asis, celular_asis, correo_asis,direccion_asis,ruta_foto_asis,estado_asis from asistente where id_asis=?");
            pstmt.setString(1,idAsis);
            
            resultado=pstmt.executeQuery();
                            
            while (resultado.next() == true) {
                
	    asistente.add(resultado.getString("id_asis"));
            asistente.add(resultado.getString("nombre_asis"));
            asistente.add(resultado.getString("apellido_asis"));
            asistente.add(resultado.getString("celular_asis"));
            asistente.add(resultado.getString("correo_asis"));
            asistente.add(resultado.getString("direccion_asis"));
            asistente.add(resultado.getString("ruta_foto_asis"));
            asistente.add(resultado.getString("estado_asis"));
            
	}
        return asistente;                   
        } catch (SQLException ex) {
            Logger.getLogger(AsistenteDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
					"No se realizo la busqueda\n" + ex,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
			
        }
	return null;
	
        
    }
    
    
     public String buscarRutaFoto(String id) throws SQLException{
        String resultado;
        PreparedStatement pstmt = conexion.prepareStatement("select ruta_foto_asis from asistente where id_asis=?");
        pstmt.setString(1, id);
        
        ResultSet resul= pstmt.executeQuery();
        
        if (resul.next() == true) {
			resultado = resul.getString("ruta_foto_asis");
			return resultado;

		} else {
			return null;
	        }
                       
    }
    
     
    public String buscarAsistenteVeh(String id) throws SQLException{
        String resultado;
        PreparedStatement pstmt = conexion.prepareStatement("select concat(nombre_asis,' ',apellido_asis) as nombre from asistente where id_asis=? and estado_asis=1");
        pstmt.setString(1, id);
        
        ResultSet resul= pstmt.executeQuery();
        
        if (resul.next() == true) {
			resultado = resul.getString("nombre");
			return resultado;

		} else {
			return null;
	        }
                       
    }
     
    public String buscarIdAsistente(String Asis) throws SQLException {

		String resultado;
		PreparedStatement pstmt;
		pstmt = conexion
				.prepareStatement("select id_asis from asistente where concat(nombre_asis,' ',apellido_asis)=?");
		pstmt.setString(1, Asis);

		ResultSet res = pstmt.executeQuery();

		if (res.next() == true) {
			resultado = res.getString("id_asis");
			return resultado;

		} else {
			return null;
		}

	}
    
    public ArrayList<String> buscarListaAsistente(){
        Statement stms;
        ArrayList<String> lista = new ArrayList<>();
        try{
            stms=conexion.createStatement();
            ResultSet resultado = stms.executeQuery("select concat(nombre_asis,' ',apellido_asis) as nombre from asistente where estado_asis=1 order by nombre_asis");
            
            while(resultado.next()==true)
            {
                lista.add(resultado.getString("nombre"));
            }
            
            return lista;
        }catch(Exception e){
            e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"La consulta de Asistente esta mal\n" + e,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
        }
            return null;
    }
   
}
