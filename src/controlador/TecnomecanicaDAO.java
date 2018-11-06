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
import modelo.TecnomecanicaVO;

/**
 *
 * @author SFS
 */
public class TecnomecanicaDAO {
    
    private Connection conexion;
    private Conexion conector;
    
    public TecnomecanicaDAO(){
        
        conector = new Conexion();
        conexion = (Connection) conector.conexionSQL();
        
    }
    
        
    public boolean ingresarTecno(TecnomecanicaVO tecno ){
        
        try{
            java.sql.PreparedStatement pstmt = conexion.prepareStatement("insert into tecnomecanica (codigo_tecno,centro_diagnostico_tecno,fecha_tecno,fecha_vigencia_tecno,ruta_foto_tecno,id_func,estado_tecno)"
            + "values(?,?,?,?,?,?,?)");
            
            pstmt.setString(1,tecno.getCodigo());
            pstmt.setString(2,tecno.getCentroDiagnostico());
            java.sql.Date sqlDate = new java.sql.Date(tecno.getFechaExpedicion().getTime());
            pstmt.setDate(3,sqlDate);
            java.sql.Date sqlDate2 = new java.sql.Date(tecno.getFechaVigente().getTime());
            pstmt.setDate(4,sqlDate2);
            pstmt.setString(5,tecno.getRutaFoto());
            pstmt.setString(6,tecno.getId_func());
            pstmt.setBoolean(7,tecno.isEstado());
            pstmt.executeUpdate();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,
					"No se guardo la información\n" + e,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
			return false;
        }
        
        return true;
    }
    
    public ArrayList<String> listaTecno(){
        Statement stms;
        ArrayList<String> lista = new ArrayList<>();
        try{
            stms=conexion.createStatement();
            ResultSet resultado = stms.executeQuery("select codigo_tecno,centro_diagnostico_tecno,fecha_tecno,fecha_vigencia_tecno  from tecnomecanica where estado_tecno=1");
            
            while(resultado.next()==true)
            {
                lista.add(resultado.getString("codigo_tecno"));
                lista.add(resultado.getString("centro_diagnostico_tecno"));
                lista.add(resultado.getString("fecha_tecno"));
                lista.add(resultado.getString("fecha_vigencia_tecno"));
                
            }
            
            return lista;
        }catch(Exception e){
            e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"La consulta de tecno esta mal\n" + e,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
        }
            return null;
    }
    
    public boolean modificaTecno(TecnomecanicaVO tecno){
        
        try{
        PreparedStatement pstmt= conexion.prepareStatement("update tecnomecanica set codigo_tecno=?,centro_diagnostico_tecno=?,fecha_tecno=?,fecha_vigencia_tecno=?,ruta_foto_tecno=? where codigo_tecno=?");
        
        pstmt.setString(1,tecno.getCodigo());
        pstmt.setString(2,tecno.getCentroDiagnostico());
        java.sql.Date sqlDate = new java.sql.Date(tecno.getFechaExpedicion().getTime());
        pstmt.setDate(3,sqlDate);
        java.sql.Date sqlDate1 = new java.sql.Date(tecno.getFechaVigente().getTime());
        pstmt.setDate(4,sqlDate1);
        pstmt.setString(5, tecno.getRutaFoto());
        pstmt.setString(6,tecno.getCodigo());
        pstmt.executeUpdate();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,
					"No se realizo la actualización\n" + e,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
			return false;
        }
        return true;
    }
    
    public boolean modificarEstadoTecno(int id){
        
        try{
        PreparedStatement pstmt= conexion.prepareStatement("update tecnomecanica set estado_tecno=? where codigo_tecno=?");
        
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
    
       
    
    
    public ArrayList<String> buscarTecno(String idTecno){
        PreparedStatement pstmt;
        ResultSet resultado;
        ArrayList<String> conductor = new ArrayList<>();
        try {
            pstmt=conexion.prepareStatement("select codigo_tecno,centro_diagnostico_tecno,fecha_tecno,fecha_vigencia_tecno,ruta_foto_tecno,estado_tecno from tecnomecanica where codigo_tecno=?");
            pstmt.setString(1,idTecno);
            
            resultado=pstmt.executeQuery();
                            
            while (resultado.next() == true) {
                
	    conductor.add(resultado.getString("codigo_tecno"));
            conductor.add(resultado.getString("centro_diagnostico_tecno"));
            conductor.add(resultado.getString("fecha_tecno"));
            conductor.add(resultado.getString("fecha_vigencia_tecno"));
            conductor.add(resultado.getString("ruta_foto_tecno"));
            conductor.add(resultado.getString("estado_tecno"));
            
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
        PreparedStatement pstmt = conexion.prepareStatement("select ruta_foto_tecno from tecnomecanica where codigo_tecno=?");
        pstmt.setString(1, id);
        
        ResultSet resul= pstmt.executeQuery();
        
        if (resul.next() == true) {
			resultado = resul.getString("ruta_foto_tecno");
			return resultado;

		} else {
			return null;
	        }
                       
    }
}
