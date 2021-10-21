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
public class UsuarioDAO {
    // Definir los Atributos. Capa de Datos. Se comunica con la BDs
    Connection con = null; // Hacer la conexion a la BDs
    Conexion cn = new Conexion();
    Statement stm = null; // Separa el espacio para construir un comando SQL
    ResultSet res = null; // Guarda el resultado de la consulta
    PreparedStatement ps = null;
    
    public String login(Usuario usu) {
        String estado = "";
        ResultSet rs;
        try 
        {
            con = cn.Conexion();
            String sql = "select tipoUsuario, correo from usuario where nombreUsuario=? and clave=?";
            ps =con.prepareStatement(sql);
            ps.setString(1, usu.getNombreUsuario());
            ps.setString(2, usu.getClave());
            rs= ps.executeQuery();
            if (rs.next()) {
                estado = "true";
            }
            usu.setTipoUsuario(rs.getString("tipoUsuario"));
            usu.setCorreo(rs.getString("correo"));
        } catch (Exception e) {
            System.err.println("Error:" + e);
        }
        return estado;
    }

    public List<Usuario> getUsuarios() {
        String sql = "SELECT * FROM usuario";

        List<Usuario> usuarios = new ArrayList<>();

        try {
            con = cn.Conexion();
            stm = con.createStatement(); 
            res = stm.executeQuery(sql);
            while (res.next()) { // Recorrer todo el ResultSet
                Usuario usu = new Usuario(); // Instanciamos un objeto tipo Usuario
                usu.setIdUsuario(res.getInt(1));
                usu.setNombreUsuario(res.getString(2));
                usu.setClave(res.getString(3));
                usu.setCorreo(res.getString(4));
                usu.setTipoUsuario(res.getString(5));
                usuarios.add(usu); // Agregarlo al ArrayList
            }
            stm.close(); // Cerrar toda la conexión a la BDs
            res.close();
            con.close();
        } catch (SQLException e) {
            System.err.println("Error:" + e);
        }
        return usuarios; // Devuelve el ArrayList usuarios
    }
    
    public boolean agregarUsuario(Usuario usuario) {
        boolean registrar = false; // Permite identificar si ya existe el usuario
        boolean encontrado = false; // Encuentra un usuario con el correo Institucional
        String buscar = "SELECT * FROM usuario where idUsuario = " // Instrucción sql
                + usuario.getIdUsuario(); // Para buscar un registro con el mismo id
        encontrado = buscar(buscar); // Ejecutamos el método con la consulta
        if(!encontrado){
            // La instrucción para insertar el registro
            String sql = "INSERT INTO usuario values (" + usuario.getIdUsuario() + ",'" + usuario.getNombreUsuario()
                    + "','" + usuario.getClave() + "','" + usuario.getCorreo() + "','"
                    + usuario.getTipoUsuario() + "')";
            try {
                con = cn.Conexion();
                stm = con.createStatement();
                stm.execute(sql);
                registrar = true;
                stm.close();
                con.close();
            } catch (SQLException e) {
                System.out.println("Error: Clase UsuarioDao, método agregarUsuario");
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
                System.out.println("Error: Clase UsuarioDao, método agregarUsuario"+e.getMessage());
            }
        return encontrado;
    }

    public Usuario getUsuarioId(int id) {
        String sql = "SELECT * FROM usuario WHERE idUsuario=" + id;
        Usuario usu = new Usuario();
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            res = ps.executeQuery();
            while (res.next()) {
                usu.setIdUsuario(res.getInt(1));
                usu.setNombreUsuario(res.getString(2));
                usu.setClave(res.getString(3));
                usu.setCorreo(res.getString(4));
                usu.setTipoUsuario(res.getString(5));
            }
            // cerramos el jdbc
            stm.close();
            res.close();
            con.close();
        } catch (SQLException er) {
            System.err.println("Error:" + er);
        }
        return usu;
    }

    public boolean actualizarUsuario(Usuario usuario) {
        boolean encontrado = false;
        boolean actualizar = false;
        String sql = "UPDATE usuario SET nombreUsuario = '" + usuario.getNombreUsuario() 
                + "', correo = '"  + usuario.getCorreo() + "', tipoUsuario='" + 
                usuario.getTipoUsuario() + "'" + ", clave = '" + usuario.getClave() + "'"
                + " WHERE idUsuario = " + usuario.getIdUsuario();
        System.out.println(""+sql);
        try {
            con = cn.Conexion();
            ps = con.prepareStatement(sql);
            ps.executeUpdate(sql);
            actualizar = true;
        } catch (SQLException e) {
            System.out.println("Error: Clase UsuarioDaoImple, método actualizar");
            e.printStackTrace();
        }
        return actualizar;
    }
 
    public boolean eliminarUsuario(int id) {
        boolean encontrado = false;
        boolean eliminar = false;
        String buscar = "SELECT * FROM usuario WHERE idUsuario=" + id;
        encontrado = buscar(buscar);
        String sql = "DELETE FROM usuario WHERE idUsuario = " + id;
        if(encontrado){
            try {
                con = cn.Conexion();
                ps = con.prepareStatement(sql);
                ps.executeUpdate(sql);
                eliminar = true;
            } catch (SQLException e) {
                System.out.println("Error: Clase EstudianteDao, método eliminar");
                e.printStackTrace();
            }
        }
        return eliminar;
    }    
}
