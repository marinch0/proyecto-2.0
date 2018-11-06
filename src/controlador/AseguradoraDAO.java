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
import javax.swing.JOptionPane;
import modelo.AseguradoraVO;

/**
 *
 * @author SFS
 */
public class AseguradoraDAO {
    
    private Connection conexion;
    private Conexion conector;
    
    public AseguradoraDAO(){
        
        conector = new Conexion();
        conexion = (Connection) conector.conexionSQL();
        
    }
    
    public int identificarIDAcual() throws SQLException{
        
        java.sql.Statement s = conexion.createStatement();
        ResultSet r = s.executeQuery("select count(*) as rowcount from aseguradora");
        r.next();
        int idActual = r.getInt("rowcount");
        r.close();
        return idActual;
        
    }
    
    public boolean ingresarAseguradora(AseguradoraVO aseguradora ){
        
        try{
            java.sql.PreparedStatement pstmt = conexion.prepareStatement("insert into aseguradora (codigo_aseg,nombre_aseg,id_func,estado_aseg)"
            + "values(?,?,?,?)");
            
            pstmt.setInt(1, aseguradora.getCodigo());
            pstmt.setString(2, aseguradora.getNombre());
            pstmt.setString(3, aseguradora.getId_func());
            pstmt.setBoolean(4, aseguradora.isEstado());
            pstmt.executeUpdate();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,
					"No se guardo la información\n" + e,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
			return false;
        }
        
        return true;
    }
    
    public ArrayList<String> listaAseguradora(){
        Statement stms;
        ArrayList<String> lista = new ArrayList<>();
        try{
            stms=conexion.createStatement();
            ResultSet resultado = stms.executeQuery("select codigo_aseg,nombre_aseg,estado_aseg  from aseguradora where estado_aseg=1");
            
            while(resultado.next()==true)
            {
                lista.add(String.valueOf(resultado.getInt("codigo_aseg")));
                lista.add(resultado.getString("nombre_aseg"));
            }
            
            return lista;
        }catch(Exception e){
            e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"La consulta de aseguradoraes esta mal\n" + e,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
        }
            return null;
    }
    
    public boolean modificaAseguradora(AseguradoraVO aseguradora){
        
        try{
        PreparedStatement pstmt= conexion.prepareStatement("update aseguradora set nombre_aseg=? where codigo_aseg=?");
        
        pstmt.setString(1, aseguradora.getNombre());
        pstmt.setInt(2, aseguradora.getCodigo());
        pstmt.executeUpdate();
        }catch(Exception e){
            JOptionPane.showMessageDialog(null,
					"No se realizo la actualización\n" + e,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
			return false;
        }
        return true;
    }
    
    public boolean modificarEstadoAseguradora(int id){
        
        try{
        PreparedStatement pstmt= conexion.prepareStatement("update aseguradora set estado_aseg=? where codigo_aseg=?");
        
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
    
    public String buscarAseguradora(int id) throws SQLException{
        String resultado;
        PreparedStatement pstmt = conexion.prepareStatement("select nombre_aseg from aseguradora where codigo_aseg=? and estado_aseg=1");
        pstmt.setInt(1, id);
        
        ResultSet resul= pstmt.executeQuery();
        
        if (resul.next() == true) {
			resultado = resul.getString("nombre_aseg");
			return resultado;

		} else {
			return null;
	        }
                       
    }
    
    public int buscarIdAseguradora(String aseguradora) throws SQLException {

		int resultado;
		PreparedStatement pstmt;
		pstmt = conexion
				.prepareStatement("select codigo_aseg from aseguradora where nombre_aseg=?");
		pstmt.setString(1, aseguradora);

		ResultSet res = pstmt.executeQuery();

		if (res.next() == true) {
			resultado = res.getInt("codigo_aseg");
			return resultado;

		} else {
			return 0;
		}

	}
    
    public ArrayList<String> buscarListaAseguradora(){
        Statement stms;
        ArrayList<String> lista = new ArrayList<>();
        try{
            stms=conexion.createStatement();
            ResultSet resultado = stms.executeQuery("select codigo_aseg,nombre_aseg,estado_aseg  from aseguradora where estado_aseg=1 order by nombre_aseg");
            
            while(resultado.next()==true)
            {
                lista.add(String.valueOf(resultado.getInt("codigo_aseg")));
                lista.add(resultado.getString("nombre_aseg"));
            }
            
            return lista;
        }catch(Exception e){
            e.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"La consulta de aseguradoraes esta mal\n" + e,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
        }
            return null;
    }
}
