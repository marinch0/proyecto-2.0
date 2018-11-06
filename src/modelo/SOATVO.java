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
public class SOATVO {
        
        //declaración de variables
        String codigoSoat,rutaFoto;
        Date fechaSoat,fechaVigenciaSoat;
        String id_func;
        boolean estado;
        int aseguradora;
        
    
    //metodo constructor
    public SOATVO(String codigoSoat, int aseguradora, Date fechaSoat, Date fechaVigenciaSoat, String rutaFoto, String id_func, boolean estado) {
        this.codigoSoat = codigoSoat;
        this.rutaFoto = rutaFoto;
        this.fechaSoat = fechaSoat;
        this.fechaVigenciaSoat = fechaVigenciaSoat;
        this.id_func = id_func;
        this.estado = estado;
        this.aseguradora = aseguradora;
    }
    
    //metodos get y set (transporte de datos)

    public String getCodigoSoat() {
        return codigoSoat;
    }

    public void setCodigoSoat(String codigoSoat) {
        this.codigoSoat = codigoSoat;
    }

    public String getRutaFoto() {
        return rutaFoto;
    }

    public void setRutaFoto(String rutaFoto) {
        this.rutaFoto = rutaFoto;
    }

    public Date getFechaSoat() {
        return fechaSoat;
    }

    public void setFechaSoat(Date fechaSoat) {
        this.fechaSoat = fechaSoat;
    }

    public Date getFechaVigenciaSoat() {
        return fechaVigenciaSoat;
    }

    public void setFechaVigenciaSoat(Date fechaVigenciaSoat) {
        this.fechaVigenciaSoat = fechaVigenciaSoat;
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

    public int getAseguradora() {
        return aseguradora;
    }

    public void setAseguradora(int aseguradora) {
        this.aseguradora = aseguradora;
    }
    
}
