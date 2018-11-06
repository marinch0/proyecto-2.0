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
//Clase hereda de Persona
public class EstudianteVO extends PersonaVO{
    //declaración de variables
    String codigo,grado,ciudad,nombreAcudiente,celularAcudiente,mailAcudiente,direccionAcudiente, idFunc;

    public EstudianteVO(String identificacion, String nombre, String apellido, String celular, String correo, String direccion, boolean estado) {
        super(identificacion, nombre, apellido, celular, correo, direccion, estado);
    }
    //metodo constructor
    public EstudianteVO(String codigo, String identificacion, String nombre, String apellido, String celular, String correo, String direccion, String grado, String ciudad, String nombreAcudiente, String celularAcudiente, String mailAcudiente, String direccionAcudiente, String idFunc, boolean estado) {
        super(identificacion, nombre, apellido, celular, correo, direccion, estado);
        this.codigo = codigo;
        this.grado = grado;
        this.ciudad = ciudad;
        this.nombreAcudiente = nombreAcudiente;
        this.celularAcudiente = celularAcudiente;
        this.mailAcudiente = mailAcudiente;
        this.direccionAcudiente = direccionAcudiente;
        this.idFunc = idFunc;
    }

    //metodos get y set (transportar datos)

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getGrado() {
        return grado;
    }

    public void setGrado(String grado) {
        this.grado = grado;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public String getNombreAcudiente() {
        return nombreAcudiente;
    }

    public void setNombreAcudiente(String nombreAcudiente) {
        this.nombreAcudiente = nombreAcudiente;
    }

    public String getCelularAcudiente() {
        return celularAcudiente;
    }

    public void setCelularAcudiente(String celularAcudiente) {
        this.celularAcudiente = celularAcudiente;
    }

    public String getMailAcudiente() {
        return mailAcudiente;
    }

    public void setMailAcudiente(String mailAcudiente) {
        this.mailAcudiente = mailAcudiente;
    }

    public String getDireccionAcudiente() {
        return direccionAcudiente;
    }

    public void setDireccionAcudiente(String direccionAcudiente) {
        this.direccionAcudiente = direccionAcudiente;
    }

    public String getIdFunc() {
        return idFunc;
    }

    public void setIdFunc(String idFunc) {
        this.idFunc = idFunc;
    }
    

   
    
    
    
    
}
