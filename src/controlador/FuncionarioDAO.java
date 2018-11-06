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
import modelo.FuncionarioVO;

/**
 *
 * @author SFS
 */
public class FuncionarioDAO {
    
    private Connection conexion;
    private Conexion conector;
    
    public FuncionarioDAO(){
        conector = new Conexion();
        conexion = conector.conexionSQL();
    }
    
    public boolean ingresarFuncionario(FuncionarioVO funcionario) {
        
        PreparedStatement pstmt;
        try {
            pstmt = conexion.prepareStatement("insert into funcionario (id_func,nombre_func,apellido_func,celular_func,correo_func,direccion_func,usuario_func,clave_func,estado_func)"
                    +"values (?,?,?,?,?,?,?,?,?)");
       
        
        pstmt.setString(1,funcionario.getIdentificacion());
        pstmt.setString(2,funcionario.getNombre());
        pstmt.setString(3,funcionario.getApellido());
        pstmt.setString(4,funcionario.getCelular());
        pstmt.setString(5,funcionario.getCorreo());
        pstmt.setString(6,funcionario.getDireccion());
        pstmt.setString(7,funcionario.getUsuario());
        pstmt.setString(8,funcionario.getClave());
        pstmt.setBoolean(9,funcionario.isEstado());
        pstmt.executeUpdate();
         } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(null,
					"No se guardo la información\nIdentificación ya existe, puede estar inactivo\n" + ex,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
			return false;
        }
        return true;
    
    }
    
    public ArrayList<String> listaFuncionario(){
        Statement stms;
        ArrayList<String> lista = new ArrayList<>();
        
        try {
            stms = conexion.createStatement();
            ResultSet resultado = stms.executeQuery("select id_func,nombre_func,apellido_func,celular_func,correo_func,direccion_func,usuario_func, clave_func from funcionario where estado_func=1");
            
            while(resultado.next()==true)
            {
                lista.add(resultado.getString("id_func"));
                lista.add(resultado.getString("nombre_func"));
                lista.add(resultado.getString("apellido_func"));
                lista.add(resultado.getString("celular_func"));
                lista.add(resultado.getString("correo_func"));
                lista.add(resultado.getString("direccion_func"));
                lista.add(resultado.getString("usuario_func"));
                lista.add(resultado.getString("clave_func"));
                
               
            }
        return lista;
         } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
             ex.printStackTrace();
			JOptionPane.showMessageDialog(null,
					"La consulta de Funcionarios esta mal\n" + ex,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
                        
        }
        return null;
    }
    
    public boolean modificarFuncionario(FuncionarioVO funcionario){
        
        try {
            PreparedStatement pstmt = conexion.prepareStatement("update funcionario set id_func=?,nombre_func=?,apellido_func=?,celular_func=?,correo_func=?,direccion_func=?,usuario_func=?,clave_func=? where id_func=?");
            
            pstmt.setString(1, funcionario.getIdentificacion());
            pstmt.setString(2, funcionario.getNombre());
            pstmt.setString(3, funcionario.getApellido());
            pstmt.setString(4, funcionario.getCelular());
            pstmt.setString(5, funcionario.getCorreo());
            pstmt.setString(6, funcionario.getDireccion());
            pstmt.setString(7, funcionario.getUsuario());
            pstmt.setString(8, funcionario.getClave());
            pstmt.setString(9, funcionario.getIdentificacion());
            pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
					"No se realizo la actualización\n" + ex,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
			return false;
        }
        return true;
    }
    
    public boolean modificarEstadoFuncionario(String idFunc){
        
        try {
            PreparedStatement pstmt = conexion.prepareStatement("update funcionario set estado_func=? where id_func=?");
            pstmt.setBoolean(1,false);
            pstmt.setString(2, idFunc);
            pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(null,
					"No se realizo la eliminación\n" + ex,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
			return false;
        }
        return true;
    }
    
    public boolean modificarClaveFuncionario(String idFunc, String clave){
        
        try {
            PreparedStatement pstmt = conexion.prepareStatement("update funcionario set clave_func=? where id_func=?");
            pstmt.setString(1, clave);
            pstmt.setString(2, idFunc);
            pstmt.executeUpdate();
            
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
             JOptionPane.showMessageDialog(null,
					"No se realizo la eliminación\n" + ex,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
			return false;
        }
        return true;
    }
    
    public ArrayList<String> buscarFuncionario(String idFunc){
        PreparedStatement pstmt;
        ResultSet resultado;
        ArrayList<String> funcionario = new ArrayList<>();
        try {
            pstmt=conexion.prepareStatement("select id_func, nombre_func, apellido_func, celular_func, correo_func,direccion_func,usuario_func, estado_func from funcionario where id_func=?");
            pstmt.setString(1,idFunc);
            
            resultado=pstmt.executeQuery();
                            
            while (resultado.next() == true) {
                
	    funcionario.add(resultado.getString("id_func"));
            funcionario.add(resultado.getString("nombre_func"));
            funcionario.add(resultado.getString("apellido_func"));
            funcionario.add(resultado.getString("celular_func"));
            funcionario.add(resultado.getString("correo_func"));
            funcionario.add(resultado.getString("direccion_func"));
            funcionario.add(resultado.getString("usuario_func"));
            funcionario.add(resultado.getString("estado_func"));
            
	}
        return funcionario;                   
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
					"No se realizo la busqueda\n" + ex,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
			
        }
	return null;
	
        
    }
    
    public ArrayList<String> buscarFuncionarioLogin(String idFunc, String clave){
        PreparedStatement pstmt;
        ResultSet resultado;
        
        ArrayList<String> funcionario = new ArrayList<>();
        try {
            pstmt=conexion.prepareStatement("select id_func, nombre_func, apellido_func,estado_func from funcionario where usuario_func=? and clave_func=?");
            pstmt.setString(1,idFunc);
            pstmt.setString(2,clave);
            
            resultado=pstmt.executeQuery();
                            
            while (resultado.next() == true) {
                
	    funcionario.add(resultado.getString("id_func"));
            funcionario.add(resultado.getString("nombre_func"));
            funcionario.add(resultado.getString("apellido_func"));
            funcionario.add(resultado.getString("estado_func"));
            
	}
        return funcionario;                   
        } catch (SQLException ex) {
            Logger.getLogger(FuncionarioDAO.class.getName()).log(Level.SEVERE, null, ex);
            JOptionPane.showMessageDialog(null,
					"No se realizo la busqueda\n" + ex,
					"mensaje Error", JOptionPane.ERROR_MESSAGE);
			
        }
	return null;
	
        
    }
    
     
    
}
