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
import modelo.VehiculoVO;

/**
 *
 * @author SFS
 */
public class VehiculoDAO {
    
    private Connection conexion;
    private Conexion conector;
    
    public VehiculoDAO(){
        conector = new Conexion();
        conexion = conector.conexionSQL();
    }
    
    public boolean ingresarVehiculo(VehiculoVO vehiculo) {
        
        PreparedStatement pstmt;
        try {
            pstmt = conexion.prepareStatement("insert into vehiculo (placa_veh, numero_veh, modelo_veh, tipo_veh, capacidad_veh, empresa_veh, marca_veh, ruta_foto_veh, id_cond, id_asis, cod_soat, cod_tecno, id_func, estado_veh)"
                    +"values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
       
        
        pstmt.setString(1,vehiculo.getPlaca());
        pstmt.setInt(2,vehiculo.getNumero());
        pstmt.setString(3,vehiculo.getModelo());
        pstmt.setString(4,vehiculo.getTipo());
        pstmt.setInt(5,vehiculo.getCapacidad());
        pstmt.setString(6,vehiculo.getEmpresa());
        pstmt.setString(7,vehiculo.getMarca());
        pstmt.setString(8,vehiculo.getRuta_foto());
        pstmt.setString(9,vehiculo.getId_cond());
        pstmt.setString(10,vehiculo.getId_asis());
        pstmt.setString(11,vehiculo.getCod_SOAT());
        pstmt.setString(12,vehiculo.getCod_tecno());
        pstmt.setString(13,vehiculo.getId_func());
        pstmt.setBoolean(14,vehiculo.isEstado());
        pstmt.executeUpdate();
        
         } catch (SQLException ex) {
            Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(null,
					"No se guardo la información\nIdentificación ya existe, puede estar inactivo\n" + ex,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
			return false;
        }
        return true;
    
    }
    
    public ArrayList<String> listaVehiculo(){
        Statement stms;
        ArrayList<String> lista = new ArrayList<>();
        
        try {
            stms = conexion.createStatement();
            ResultSet resultado = stms.executeQuery("select placa_veh, numero_veh, modelo_veh, tipo_veh, capacidad_veh, empresa_veh, marca_veh, ruta_foto_veh, id_cond, id_asis, cod_soat, cod_tecno  from vehiculo where estado_veh=1");
            
            while(resultado.next()==true)
            {
            
            lista.add(resultado.getString("placa_veh"));
            lista.add(resultado.getString("numero_veh"));
            lista.add(resultado.getString("modelo_veh"));
            lista.add(resultado.getString("tipo_veh"));
            lista.add(resultado.getString("capacidad_veh"));
            lista.add(resultado.getString("empresa_veh"));
            lista.add(resultado.getString("marca_veh"));
            lista.add(resultado.getString("ruta_foto_veh"));
            lista.add(resultado.getString("id_cond"));
            lista.add(resultado.getString("id_asis"));
            lista.add(resultado.getString("cod_soat"));
            lista.add(resultado.getString("cod_tecno"));
                                    
               
            }
        return lista;
         } catch (SQLException ex) {
            Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
             ex.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"La consulta de vehiculos esta mal\n" + ex,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
                        
        }
        return null;
    }
    
    public boolean modificarVehiculo(VehiculoVO vehiculo){
        
        try {
        
            PreparedStatement pstmt = conexion.prepareStatement("update vehiculo set placa_veh=?, numero_veh=?,modelo_veh=?, tipo_veh=?, capacidad_veh=?, empresa_veh=?, marca_veh=?, ruta_foto_veh=?,id_cond=?, id_asis=?, cod_soat=?, cod_tecno=? where placa_veh=?");
            
        pstmt.setString(1,vehiculo.getPlaca());
        pstmt.setInt(2,vehiculo.getNumero());
        pstmt.setString(3,vehiculo.getModelo());
        pstmt.setString(4,vehiculo.getTipo());
        pstmt.setInt(5,vehiculo.getCapacidad());
        pstmt.setString(6,vehiculo.getEmpresa());
        pstmt.setString(7,vehiculo.getMarca());
        pstmt.setString(8,vehiculo.getRuta_foto());
        pstmt.setString(9,vehiculo.getId_cond());
        pstmt.setString(10,vehiculo.getId_asis());
        pstmt.setString(11,vehiculo.getCod_SOAT());
        pstmt.setString(12,vehiculo.getCod_tecno());
        pstmt.setString(13, vehiculo.getPlaca());
        pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
					"No se realizo la actualización\n" + ex,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
			return false;
        }
        return true;
    }
    
    public boolean modificarEstadoVehiculo(String idVeh){
        
        try {
            PreparedStatement pstmt = conexion.prepareStatement("update vehiculo set estado_veh=? where placa_veh=?");
            pstmt.setBoolean(1,false);
            pstmt.setString(2, idVeh);
            pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(null,
					"No se realizo la eliminación\n" + ex,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
			return false;
        }
        return true;
    }
    
        
    public ArrayList<String> buscarVehiculo(String idVeh){
        PreparedStatement pstmt;
        ResultSet resultado;
        ArrayList<String> vehiculo = new ArrayList<>();
        try {
            pstmt=conexion.prepareStatement("select placa_veh, numero_veh, modelo_veh, tipo_veh, capacidad_veh, empresa_veh, marca_veh, ruta_foto_veh, id_cond, id_asis, cod_soat, cod_tecno,estado_veh from vehiculo where placa_veh=?");
            pstmt.setString(1,idVeh);
            
            resultado=pstmt.executeQuery();
                            
            while (resultado.next() == true) {
                
	    vehiculo.add(resultado.getString("placa_veh"));
            vehiculo.add(resultado.getString("numero_veh"));
            vehiculo.add(resultado.getString("modelo_veh"));
            vehiculo.add(resultado.getString("tipo_veh"));
            vehiculo.add(resultado.getString("capacidad_veh"));
            vehiculo.add(resultado.getString("empresa_veh"));
            vehiculo.add(resultado.getString("marca_veh"));
            vehiculo.add(resultado.getString("ruta_foto_veh"));
            vehiculo.add(resultado.getString("id_cond"));
            vehiculo.add(resultado.getString("id_asis"));
            vehiculo.add(resultado.getString("cod_soat"));
            vehiculo.add(resultado.getString("cod_tecno"));
            vehiculo.add(resultado.getString("estado_veh"));
            
	}
        return vehiculo;                   
        } catch (SQLException ex) {
            Logger.getLogger(VehiculoDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
					"No se realizo la busqueda\n" + ex,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
			
        }
	return null;
	
        
    }
    
    
     public String buscarRutaFoto(String id) throws SQLException{
        String resultado;
        PreparedStatement pstmt = conexion.prepareStatement("select ruta_foto_veh from vehiculo where placa_veh=?");
        pstmt.setString(1, id);
        
        ResultSet resul= pstmt.executeQuery();
        
        if (resul.next() == true) {
			resultado = resul.getString("ruta_foto_veh");
			return resultado;

		} else {
			return null;
	        }
                       
    }

}
