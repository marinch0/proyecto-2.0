/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controlador;

import com.mysql.jdbc.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import modelo.SOATVO;

/**
 *
 * @author SFS
 */
public class SOATDAO {
    
    private Connection conexion;
    private Conexion conector;
    
    public SOATDAO(){
        
        conector = new Conexion();
        conexion = (Connection) conector.conexionSQL();
        
    }
    
        
    public boolean ingresarSOAT(SOATVO soat ){
        
        try{
            java.sql.PreparedStatement pstmt = conexion.prepareStatement("insert into soat (codigo_soat,codigo_aseg_soat,fecha_soat,fecha_vigencia_soat,ruta_foto_soat,id_func,estado_soat)"
            + "values(?,?,?,?,?,?,?)");
            
            pstmt.setString(1,soat.getCodigoSoat());
            pstmt.setInt(2,soat.getAseguradora());
            java.sql.Date sqlDate = new java.sql.Date(soat.getFechaSoat().getTime());
            pstmt.setDate(3,sqlDate);
            java.sql.Date sqlDate2 = new java.sql.Date(soat.getFechaVigenciaSoat().getTime());
            pstmt.setDate(4,sqlDate2);
            pstmt.setString(5,soat.getRutaFoto());
            pstmt.setString(6,soat.getId_func());
            pstmt.setBoolean(7,soat.isEstado());
            pstmt.executeUpdate();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,
					"No se guardo la información\n" + e,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
			return false;
        }
        
        return true;
    }
    
    public ArrayList<String> listaSOAT(){
        Statement stms;
        ArrayList<String> lista = new ArrayList<>();
        try{
            stms=conexion.createStatement();
            ResultSet resultado = stms.executeQuery("select codigo_soat,codigo_aseg_soat,fecha_soat,fecha_vigencia_soat  from SOAT where estado_soat=1");
            
            while(resultado.next()==true)
            {
                lista.add(resultado.getString("codigo_soat"));
                lista.add(String.valueOf(resultado.getInt("codigo_aseg_soat")));
                lista.add(resultado.getString("fecha_soat"));
                lista.add(resultado.getString("fecha_vigencia_soat"));
                
            }
            
            return lista;
        }catch(Exception e){
            e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"La consulta de SOAT esta mal\n" + e,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
        }
            return null;
    }
    
    public boolean modificaSOAT(SOATVO soat){
        
        try{
        PreparedStatement pstmt= conexion.prepareStatement("update SOAT set codigo_soat=?,codigo_aseg_soat=?,fecha_soat=?,fecha_vigencia_soat=?,ruta_foto_soat=? where codigo_soat=?");
        
        pstmt.setString(1,soat.getCodigoSoat());
        pstmt.setInt(2,soat.getAseguradora());
        java.sql.Date sqlDate = new java.sql.Date(soat.getFechaSoat().getTime());
        pstmt.setDate(3,sqlDate);
        java.sql.Date sqlDate1 = new java.sql.Date(soat.getFechaVigenciaSoat().getTime());
        pstmt.setDate(4,sqlDate1);
        pstmt.setString(5, soat.getRutaFoto());
        pstmt.setString(6,soat.getCodigoSoat());
        pstmt.executeUpdate();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,
					"No se realizo la actualización\n" + e,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
			return false;
        }
        return true;
    }
    
    public boolean modificarEstadoSOAT(int id){
        
        try{
        PreparedStatement pstmt= conexion.prepareStatement("update SOAT set estado_soat=? where codigo_soat=?");
        
        pstmt.setBoolean(1, false);
        pstmt.setInt(2, id);
        pstmt.executeUpdate();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,
					"No se realizo la eliminación\n" + e,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
			return false;
        }
        return true;
    }
    
    
    
        
    public ArrayList<String> buscarSoat(String idSoat){
        PreparedStatement pstmt;
        ResultSet resultado;
        ArrayList<String> conductor = new ArrayList<>();
        try {
            pstmt=conexion.prepareStatement("select codigo_soat,codigo_aseg_soat,fecha_soat,fecha_vigencia_soat,ruta_foto_soat,estado_soat from soat where codigo_soat=?");
            pstmt.setString(1,idSoat);
            
            resultado=pstmt.executeQuery();
                            
            while (resultado.next() == true) {
                
	    conductor.add(resultado.getString("codigo_soat"));
            conductor.add(String.valueOf(resultado.getInt("codigo_aseg_soat")));
            conductor.add(resultado.getString("fecha_soat"));
            conductor.add(resultado.getString("fecha_vigencia_soat"));
            conductor.add(resultado.getString("ruta_foto_soat"));
            conductor.add(resultado.getString("estado_soat"));
            
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
        PreparedStatement pstmt = conexion.prepareStatement("select ruta_foto_soat from soat where codigo_soat=?");
        pstmt.setString(1, id);
        
        ResultSet resul= pstmt.executeQuery();
        
        if (resul.next() == true) {
			resultado = resul.getString("ruta_foto_soat");
			return resultado;

		} else {
			return null;
	        }
                       
    }
    
    
}
