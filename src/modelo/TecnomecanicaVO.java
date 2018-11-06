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
public class TecnomecanicaVO {
    
    //declarar variables
    String codigo,centroDiagnostico;
    Date fechaExpedicion,fechaVigente;
    String rutaFoto,id_func;
    boolean estado;
    
    //metodo constructor

    public TecnomecanicaVO(String codigo, String centroDiagnostico, Date fechaExpedicion, Date fechaVigente, String rutaFoto, String id_func, boolean estado) {
        this.codigo = codigo;
        this.centroDiagnostico = centroDiagnostico;
        this.fechaExpedicion = fechaExpedicion;
        this.fechaVigente = fechaVigente;
        this.rutaFoto = rutaFoto;
        this.id_func = id_func;
        this.estado = estado;
    }
    
    //metodos get y set (transporte de datos)

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getCentroDiagnostico() {
        return centroDiagnostico;
    }

    public void setCentroDiagnostico(String centroDiagnostico) {
        this.centroDiagnostico = centroDiagnostico;
    }

    public Date getFechaExpedicion() {
        return fechaExpedicion;
    }

    public void setFechaExpedicion(Date fechaExpedicion) {
        this.fechaExpedicion = fechaExpedicion;
    }

    public Date getFechaVigente() {
        return fechaVigente;
    }

    public void setFechaVigente(Date fechaVigente) {
        this.fechaVigente = fechaVigente;
    }

    public String getRutaFoto() {
        return rutaFoto;
    }

    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
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
