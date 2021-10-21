/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mintic.edu.proyecto28.modelo;

import java.util.List;

/**
 *
 * @author USER
 */
public interface IProveedorDAO {
    // Interfaz para 
    // Agregar Registros
    // Mostrar los datos
    // Editar Registros
    // Eliminar Registros
    
    public boolean agregarProveedor(Proveedor proveedor);
    public List<Proveedor> getProveedores();
    public boolean actualizarProveedor(Proveedor proveedor);
    public boolean eliminarProveedor(Proveedor proveedor);
    public Proveedor buscarProveedorByCedula(int cedula);    
}
