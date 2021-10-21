/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mintic.edu.proyecto28.modelo;

/**
 *
 * @author USER
 */
public class Proveedor {

    private int cedula;
    private String nombrecompleto;
    private String direccion;
    private String telefono;
    private String ciudad;

    public Proveedor() {
    }

    public Proveedor(int cedula, String ciudad, String direccion, String nombrecompleto,String telefono) {
        this.cedula = cedula;
        this.nombrecompleto = nombrecompleto;
        this.direccion = direccion;
        this.telefono = telefono;
        this.ciudad = ciudad;
    }

    /**
     * @return the cedula
     */
    public int getCedula() {
        return cedula;
    }

    /**
     * @param cedula the cedula to set
     */
    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    /**
     * @return the nombrecompleto
     */
    public String getNombrecompleto() {
        return nombrecompleto;
    }

    /**
     * @param nombrecompleto the nombrecompleto to set
     */
    public void setNombrecompleto(String nombrecompleto) {
        this.nombrecompleto = nombrecompleto;
    }

    /**
     * @return the direccion
     */
    public String getDireccion() {
        return direccion;
    }

    /**
     * @param direccion the direccion to set
     */
    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    /**
     * @return the telefono
     */
    public String getTelefono() {
        return telefono;
    }

    /**
     * @param telefono the telefono to set
     */
    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    /**
     * @return the ciudad
     */
    public String getCiudad() {
        return ciudad;
    }

    /**
     * @param ciudad the ciudad to set
     */
    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    @Override
    public String toString() {
        return "Proveedor{" + "cedula=" + cedula + ", ciudad=" + ciudad +  ", direccion=" + direccion + ", nombrecompleto=" + nombrecompleto + ", telefono=" + telefono  + '}';
    }   
    
}
