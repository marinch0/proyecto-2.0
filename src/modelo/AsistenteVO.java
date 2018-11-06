/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author SFS
 */
//Clase Asistente hereda de Persona
public class AsistenteVO extends PersonaVO{
    //declaración de variables
    String rutaFoto, idFunc;

    public AsistenteVO(String identificacion, String nombre, String apellido, String celular, String correo, String direccion, boolean estado) {
        super(identificacion, nombre, apellido, celular, correo, direccion, estado);
    }
    //metodo constructor
    public AsistenteVO(String identificacion, String nombre, String apellido, String celular, String correo, String direccion,String rutaFoto, String idFunc, boolean estado) {
        super(identificacion, nombre, apellido, celular, correo, direccion, estado);
        this.rutaFoto = rutaFoto;
        this.idFunc = idFunc;
    }
    
    //metodos get y set (transporte de datos)

    public String getRutaFoto() {
        return rutaFoto;
    }

    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }

    public String getIdFunc() {
        return idFunc;
    }

    public void setIdFunc(String idFunc) {
        this.idFunc = idFunc;
    }
    
    
    
}
