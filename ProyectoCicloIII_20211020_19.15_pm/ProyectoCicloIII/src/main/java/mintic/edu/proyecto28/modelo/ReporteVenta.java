package mintic.edu.proyecto28.modelo;

/**
 *
 * 
 */
public class ReporteVenta {
    
    private int cedulaCliente;
    private String nombreCliente;
    private double ventaTotal;

    public ReporteVenta() {
    }

    public ReporteVenta(int cedulaCliente, String nombreCliente, double ventaTotal) {
        this.cedulaCliente = cedulaCliente;
        this.nombreCliente = nombreCliente;
        this.ventaTotal = ventaTotal;
    }

    public int getCedulaCliente() {
        return cedulaCliente;
    }

    public void setCedulaCliente(int cedulaCliente) {
        this.cedulaCliente = cedulaCliente;
    }

    public String getNombreCliente() {
        return nombreCliente;
    }

    public void setNombreCliente(String nombreCliente) {
        this.nombreCliente = nombreCliente;
    }

    public double getVentaTotal() {
        return ventaTotal;
    }

    public void setVentaTotal(double ventaTotal) {
        this.ventaTotal = ventaTotal;
    }

    @Override
    public String toString() {
        return "ReporteVenta{" + "cedulaCliente=" + cedulaCliente + ", nombreCliente=" + nombreCliente + ", ventaTotal=" + ventaTotal + '}';
    }
    
    
    
}
