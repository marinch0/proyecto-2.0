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
public class VehiculoVO {
    
    //declaración de variables
    String placa, modelo, tipo, empresa, marca, ruta_foto,id_cond, id_asis,cod_tecno,cod_SOAT,id_func;
    boolean estado;
    int numero, capacidad;
    
    //metodo Constructor

    public VehiculoVO(String placa,int numero, String modelo, String tipo,int capacidad, String empresa, String marca, String ruta_foto, String id_cond, String id_asis,String cod_SOAT, String cod_tecno, String id_func, boolean estado) {
        this.placa = placa;
        this.modelo = modelo;
        this.tipo = tipo;
        this.empresa = empresa;
        this.marca = marca;
        this.ruta_foto = ruta_foto;
        this.id_cond = id_cond;
        this.id_asis = id_asis;
        this.cod_tecno = cod_tecno;
        this.cod_SOAT = cod_SOAT;
        this.id_func = id_func;
        this.estado = estado;
        this.numero = numero;
        this.capacidad = capacidad;
    }
    
    
    //metodos get y set (transporte de datos)

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getEmpresa() {
        return empresa;
    }

    public void setEmpresa(String empresa) {
        this.empresa = empresa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getRuta_foto() {
        return ruta_foto;
    }

    public void setRuta_foto(String ruta_foto) {
        this.ruta_foto = ruta_foto;
    }

    public String getId_cond() {
        return id_cond;
    }

    public void setId_cond(String id_cond) {
        this.id_cond = id_cond;
    }

    public String getId_asis() {
        return id_asis;
    }

    public void setId_asis(String id_asis) {
        this.id_asis = id_asis;
    }

    public String getCod_tecno() {
        return cod_tecno;
    }

    public void setCod_tecno(String cod_tecno) {
        this.cod_tecno = cod_tecno;
    }

    public String getCod_SOAT() {
        return cod_SOAT;
    }

    public void setCod_SOAT(String cod_SOAT) {
        this.cod_SOAT = cod_SOAT;
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

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }
    
}
