/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
 * @author USER
 */
public class ProveedorDAO implements IProveedorDAO {
    
    // Definir los Atributos. Capa de Datos. Se comunica con la BDs
    Connection con = null; // Hacer la conexion a la BDs
    Conexion cn = new Conexion();
    Statement stm = null; // Separa el espacio para construir un comando SQL
    ResultSet res = null; // Guarda el resultado de la consulta
    PreparedStatement ps = null;

    @Override
    public boolean agregarProveedor(Proveedor proveedor) {
        boolean registrar = false; // Permite identificar si ya existe el usuario
        boolean encontrado = false; // Encuentra un usuario con el correo Institucional
        String buscar = "SELECT * FROM proveedor where nitproveedor = " // Instrucción sql
                + proveedor.getCedula(); // Para buscar un registro con el mismo id
        encontrado = buscar(buscar); // Ejecutamos el método con la consulta
        if(!encontrado){
            // La instrucción para insertar el registro
            String sql = "INSERT INTO proveedor values (" + proveedor.getCedula() + ",'"
                    + proveedor.getCiudad() + "','" + 
                     proveedor.getDireccion()+ "','" +  proveedor.getNombrecompleto()
                    + "','" + proveedor.getTelefono()+ "')";
            try {
                con = cn.Conexion();
                stm = con.createStatement();
                stm.execute(sql);
                registrar = true;
                stm.close();
                con.close();
            } catch (SQLException e) {
                System.out.println("Error: Clase ProveedorDAO, método agregarProveedor");
                e.printStackTrace();
            }
        }

        return registrar;
    }

    @Override
    public List<Proveedor> getProveedores() {
        String sql = "SELECT * FROM proveedor";

        List<Proveedor> proveedores = new ArrayList<>();

        try {
            con = cn.Conexion();
            stm = con.createStatement(); 
            res = stm.executeQuery(sql);
            while (res.next()) { // Recorrer todo el ResultSet
                Proveedor cli = new Proveedor(); // Instanciamos un objeto tipo Provedor
                cli.setCedula(res.getInt(1));
                cli.setNombrecompleto(res.getString(4));
                cli.setDireccion(res.getString(3));
                cli.setTelefono(res.getString(5));
                cli.setCiudad(res.getString(2));
                proveedores.add(cli); // Agregarlo al ArrayList
            }
            stm.close(); // Cerrar toda la conexión a la BDs
            res.close();
            con.close();
        } catch (SQLException e) {
            System.err.println("Error:" + e);
        }
        return proveedores; // Devuelve el ArrayList usuarios proveedores
    }

    @Override
    public boolean actualizarProveedor(Proveedor proveedor) {
        boolean encontrado = false;
        boolean actualizar = false;
        String sql = "UPDATE proveedor SET nombre_proveedor = '" + proveedor.getNombrecompleto()
                + "', ciudad_proveedor = '"  + proveedor.getCiudad() + "', direccion_proveedor='" + 
                proveedor.getDireccion() + "'" + ", telefono_proveedor = '" + proveedor.getTelefono() + "'"
                + " WHERE nitproveedor = " + proveedor.getCedula();
        System.out.println(""+sql);
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate(sql);
            actualizar = true;
        } catch (SQLException e) {
            System.out.println("Error: Clase ProveedorDaoImple, método actualizar");
            e.printStackTrace();
        }
        return actualizar;
    }

    @Override
    public boolean eliminarProveedor(Proveedor proveedor) {
        boolean encontrado = false;
        boolean eliminar = false;
        String buscar = "SELECT * FROM Proveedor where nitproveedor = " // Instrucción sql
                + proveedor.getCedula(); // Para buscar un registro con el mismo id
        encontrado = buscar(buscar);
        String sql = "DELETE FROM Proveedor WHERE nitproveedor = " + proveedor.getCedula();
        if(encontrado){
            try {
                con = cn.Conexion();
                ps = con.prepareStatement(sql);
                ps.executeUpdate(sql);
                eliminar = true;
            } catch (SQLException e) {
                System.out.println("Error: Clase ProveedorDao, método eliminar");
                e.printStackTrace();
            }
        }
        return eliminar;
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
                System.out.println("Error: Clase ProveedorDao, método agregarProveedor"+e.getMessage());
            }
        return encontrado;
    }

    @Override
    public Proveedor buscarProveedorByCedula(int cedula) {
        String sql = "SELECT * FROM proveedor WHERE nitproveedor=" + cedula;
        Proveedor provee = new Proveedor();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            res = ps.executeQuery();
            while (res.next()) {
                provee.setCedula(res.getInt(1));
                provee.setNombrecompleto(res.getString(4));
                provee.setDireccion(res.getString(3));
                provee.setTelefono(res.getString(5));
                provee.setCiudad(res.getString(2));
            }
            // cerramos el jdbc
            ps.close();
            res.close();
            con.close();
        } catch (SQLException er) {
            System.err.println("Error:" + er);
        }
        return provee;
    }    
}
