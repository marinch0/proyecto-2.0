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

public class AseguradoraVO {
    //declaración de variables
    int codigo;
    String nombre, id_func;
    boolean estado;
    
    //metodo constructor
    public AseguradoraVO(int codigo, String nombre, String id_func, boolean estado) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.id_func = id_func;
        this.estado = estado;
    }

    //metodo get y set (transporte de datos)
    public int getCodigo() {
        return codigo;
    }

    public void setCodigo(int codigo) {
        this.codigo = codigo;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getId_func() {
        return id_func;
    }

    public void setId_func(String id_func) {
        this.id_func = id_func;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }
    
    
    
}
