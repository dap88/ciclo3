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
public class ProductoDAO {
    // Definir los Atributos. Capa de Datos. Se comunica con la BDs
    Connection con = null; // Hacer la conexion a la BDs
    Conexion cn = new Conexion();
    Statement stm = null; // Separa el espacio para construir un comando SQL
    ResultSet res = null; // Guarda el resultado de la consulta
    PreparedStatement ps = null;

    public boolean agregarProducto(Producto producto) {
        boolean registrar = false; // Permite identificar si ya existe el usuario
        boolean encontrado = false; // Encuentra un usuario con el correo Institucional
        String buscar = "SELECT * FROM productos where codigo = " // Instrucción sql
                + producto.getCodigo(); // Para buscar un registro con el mismo id
        encontrado = buscar(buscar); // Ejecutamos el método con la consulta
        if(!encontrado){
            // La instrucción para insertar el registro
            String sql = "INSERT INTO productos values (" + producto.getCodigo() + ",'" + producto.getNombre()
                    + "'," + producto.getNitProveedor() + "," + producto.getPrecioCompra()+ ","
                    + producto.getIva() + "," + producto.getPrecioVenta() + ")";
            try {
                con = cn.Conexion();
                stm = con.createStatement();
                stm.execute(sql);
                registrar = true;
                stm.close();
                con.close();
            } catch (SQLException e) {
                System.out.println("Error: Clase ProductoDao, método agregarProducto");
                e.printStackTrace();
            }
        }

        return registrar;
    }

    public boolean buscar(String sql){
        boolean encontrado = false;
        con = cn.Conexion();
        try {
            stm = con.createStatement();
            res = stm.executeQuery(sql);
            while(res.next()){
                encontrado = true;
            }
        } catch (SQLException e) {
                System.out.println("Mensaje:"+e.getMessage());
                System.out.println("Estado:"+e.getSQLState());
                System.out.println("Codigo del error:"+e.getErrorCode());
                System.out.println("Error: Clase ProductoDAO, método agregarProducto"+e.getMessage());
            }
        return encontrado;
    }
    
    public List<Producto> getProductos() {
        String sql = "SELECT * FROM productos";

        List<Producto> productos = new ArrayList<>();

        try {
            con = cn.Conexion();
            stm = con.createStatement(); 
            res = stm.executeQuery(sql);
            while (res.next()) { // Recorrer todo el ResultSet
                Producto pro = new Producto(); // Instanciamos un objeto tipo Clientes
                pro.setCodigo(res.getInt(1));
                pro.setNombre(res.getString(2));
                pro.setNitProveedor(res.getInt(3));
                pro.setPrecioCompra(res.getDouble(4));
                pro.setIva(res.getDouble(5));
                pro.setPrecioVenta(res.getDouble(6));
                productos.add(pro); // Agregarlo al ArrayList
            }
            stm.close(); // Cerrar toda la conexión a la BDs
            res.close();
            con.close();
        } catch (SQLException e) {
            System.err.println("Error:" + e);
        }
        return productos; // Devuelve el ArrayList usuarios
    }

    public Producto buscarProductoByCodigo(int codigo) {
        String sql = "SELECT * FROM productos WHERE codigo=" + codigo;
        Producto pro = new Producto();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            res = ps.executeQuery();
            while (res.next()) {
                pro.setCodigo(res.getInt(1));
                pro.setNombre(res.getString(2));
                pro.setNitProveedor(res.getInt(3));
                pro.setPrecioCompra(res.getDouble(4));
                pro.setIva(res.getDouble(5));
                pro.setPrecioVenta(res.getDouble(6));
            }
            // cerramos el jdbc
            ps.close();
            res.close();
            con.close();
        } catch (SQLException er) {
            System.err.println("Error:" + er);
        }
        return pro;
    }

    
}
