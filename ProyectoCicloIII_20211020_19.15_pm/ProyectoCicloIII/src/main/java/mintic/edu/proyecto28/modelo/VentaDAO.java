package mintic.edu.proyecto28.modelo;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * 
 */
public class VentaDAO {

    // Definir los Atributos. Capa de Datos. Se comunica con la BDs
    Connection con = null; // Hacer la conexion a la BDs
    Conexion cn = new Conexion();
    Statement stm = null; // Separa el espacio para construir un comando SQL
    ResultSet res = null; // Guarda el resultado de la consulta
    PreparedStatement ps = null;
    
    public int calcularIdVenta(){
        int idVenta = 0;
        String sql = "SELECT max(id) from venta";
        try {
            con = cn.Conexion();
            stm = con.createStatement();
            res = stm.executeQuery(sql);
            while(res.next()){
                idVenta = res.getInt(1);
            }
            stm.close();
            res.close();
            con.close();
        } catch (Exception e) {
            System.out.println("Error: " + e);
        }
        return idVenta;
    }
    
    public List<ReporteVenta> verReporteVenta() {
        String sql = "Select v.cedulaCliente, c.nombrecompleto, sum(v.totalVenta) as totalVenta"
                + " from venta v, cliente c "
                + "where v.cedulaCliente = c.cedula "
                + "group by v.cedulaCliente, c.nombrecompleto";
        
        System.out.println("Comando SQL: " +sql);

        List<ReporteVenta> reporteVentas = new ArrayList<>();

        try {
            con = cn.Conexion();
            stm = con.createStatement(); 
            res = stm.executeQuery(sql);
            while (res.next()) { // Recorrer todo el ResultSet
                ReporteVenta repv = new ReporteVenta(); // Instanciamos un objeto tipo ReporteVenta
                repv.setCedulaCliente(res.getInt(1));
                repv.setNombreCliente(res.getString(2));
                repv.setVentaTotal(res.getDouble(3));
                reporteVentas.add(repv); // Agregarlo al ArrayList
            }
            stm.close(); // Cerrar toda la conexión a la BDs
            res.close();
            con.close();
        } catch (SQLException e) {
            System.err.println("Error en Reporte de Ventas:" + e);
        }
        return reporteVentas; // Devuelve el ArrayList usuarios
    }

}
