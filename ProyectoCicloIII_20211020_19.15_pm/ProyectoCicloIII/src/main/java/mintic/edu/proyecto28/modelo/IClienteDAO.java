package mintic.edu.proyecto28.modelo;

import java.util.List;

/**
 *
 * 
 */
public interface IClienteDAO {
    // Interfaz para 
    // Agregar Registros
    // Mostrar los datos
    // Editar Registros
    // Eliminar Registros
    
    public boolean agregarCliente(Cliente cliente);
    public List<Cliente> getClientes();
    public boolean actualizarCliente(Cliente cliente);
    public boolean eliminarCliente(Cliente cliente);
    public Cliente buscarClienteByCedula(int cedula);
    
}
