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

public class RutaVO {
    //declaración de variables
    String codigo,periodo,zona,vehiculoPlaca,conductor,asistente,recorrido,horaInicio,horaFin,tRecorrido,cuposAsignados,cuposDisponibles,numeroParadas,id_func;
    boolean estado;

    //metodo constructor
    public RutaVO(String codigo, String periodo, String zona, String vehiculoPlaca, String conductor, String asistente, String recorrido, String horaInicio, String horaFin, String tRecorrido, String cuposAsignados, String cuposDisponibles, String numeroParadas, String id_func, boolean estado) {
        this.codigo = codigo;
        this.periodo = periodo;
        this.zona = zona;
        this.vehiculoPlaca = vehiculoPlaca;
        this.conductor = conductor;
        this.asistente = asistente;
        this.recorrido = recorrido;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.tRecorrido = tRecorrido;
        this.cuposAsignados = cuposAsignados;
        this.cuposDisponibles = cuposDisponibles;
        this.numeroParadas = numeroParadas;
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

    public String getPeriodo() {
        return periodo;
    }

    public void setPeriodo(String periodo) {
        this.periodo = periodo;
    }

    public String getZona() {
        return zona;
    }

    public void setZona(String zona) {
        this.zona = zona;
    }

    public String getVehiculoPlaca() {
        return vehiculoPlaca;
    }

    public void setVehiculoPlaca(String vehiculoPlaca) {
        this.vehiculoPlaca = vehiculoPlaca;
    }

    public String getConductor() {
        return conductor;
    }

    public void setConductor(String conductor) {
        this.conductor = conductor;
    }

    public String getAsistente() {
        return asistente;
    }

    public void setAsistente(String asistente) {
        this.asistente = asistente;
    }

    public String getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(String recorrido) {
        this.recorrido = recorrido;
    }

    public String getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(String horaInicio) {
        this.horaInicio = horaInicio;
    }

    public String getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(String horaFin) {
        this.horaFin = horaFin;
    }

    public String gettRecorrido() {
        return tRecorrido;
    }

    public void settRecorrido(String tRecorrido) {
        this.tRecorrido = tRecorrido;
    }

    public String getCuposAsignados() {
        return cuposAsignados;
    }

    public void setCuposAsignados(String cuposAsignados) {
        this.cuposAsignados = cuposAsignados;
    }

    public String getCuposDisponibles() {
        return cuposDisponibles;
    }

    public void setCuposDisponibles(String cuposDisponibles) {
        this.cuposDisponibles = cuposDisponibles;
    }

    public String getNumeroParadas() {
        return numeroParadas;
    }

    public void setNumeroParadas(String numeroParadas) {
        this.numeroParadas = numeroParadas;
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
