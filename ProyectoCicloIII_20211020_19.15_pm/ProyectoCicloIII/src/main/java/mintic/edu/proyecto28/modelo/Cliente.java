package mintic.edu.proyecto28.modelo;

/**
 *
 *
 */
public class Cliente {
    private int cedula;
    private String nombrecompleto;
    private String direccion;
    private String telefono;
    private String correo;

    public Cliente() {
    }

    public Cliente(int cedula, String nombrecompleto, String direccion, String telefono, String correo) {
        this.cedula = cedula;
        this.nombrecompleto = nombrecompleto;
        this.direccion = direccion;
        this.telefono = telefono;
        this.correo = correo;
    }

    public int getCedula() {
        return cedula;
    }

    public void setCedula(int cedula) {
        this.cedula = cedula;
    }

    public String getNombrecompleto() {
        return nombrecompleto;
    }

    public void setNombrecompleto(String nombrecompleto) {
        this.nombrecompleto = nombrecompleto;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    @Override
    public String toString() {
        return "Cliente{" + "cedula=" + cedula + ", nombrecompleto=" + nombrecompleto + ", direccion=" + direccion + ", telefono=" + telefono + ", correo=" + correo + '}';
    }

    
}
