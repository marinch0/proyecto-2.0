/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import java.util.Date;

/**
 *
 * @author SFS
 */
public class ConductorVO extends PersonaVO{
    //declaración de variables
    String catLin, ciuLin, restLin, rutaFoto, rutaFotoLin, id_func;
    Date vigLin;

    public ConductorVO(String identificacion, String nombre, String apellido, String celular, String correo, String direccion, boolean estado) {
        super(identificacion, nombre, apellido, celular, correo, direccion, estado);
    }
    //metodo constructor
    public ConductorVO(String identificacion, String nombre, String apellido,String rutaFoto, String celular, String correo, String direccion, String catLin,Date vigLin, String ciuLin, String restLin,  String rutaFotoLin, String id_func, boolean estado) {
        super(identificacion, nombre, apellido, celular, correo, direccion, estado);
        this.catLin = catLin;
        this.ciuLin = ciuLin;
        this.restLin = restLin;
        this.rutaFoto = rutaFoto;
        this.rutaFotoLin = rutaFotoLin;
        this.id_func = id_func;
        this.vigLin = vigLin;
    }
    
    //metodos get y set (transporte de datos)
    public String getCatLin() {
        return catLin;
    }

    public void setCatLin(String catLin) {
        this.catLin = catLin;
    }

    public String getCiuLin() {
        return ciuLin;
    }

    public void setCiuLin(String ciuLin) {
        this.ciuLin = ciuLin;
    }

    public String getRestLin() {
        return restLin;
    }

    public void setRestLin(String restLin) {
        this.restLin = restLin;
    }

    public String getRutaFoto() {
        return rutaFoto;
    }

    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }

    public String getRutaFotoLin() {
        return rutaFotoLin;
    }

    public void setRutaFotoLin(String rutaFotoLin) {
        this.rutaFotoLin = rutaFotoLin;
    }

    public String getId_func() {
        return id_func;
    }

    public void setId_func(String id_func) {
        this.id_func = id_func;
    }

    public Date getVigLin() {
        return vigLin;
    }

    public void setVigLin(Date vigLin) {
        this.vigLin = vigLin;
    }
    
    
}
