package mintic.edu.proyecto28.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import mintic.edu.proyecto28.modelo.Cliente;
import mintic.edu.proyecto28.modelo.ClienteDAO;
import mintic.edu.proyecto28.modelo.DetalleVenta;
import mintic.edu.proyecto28.modelo.Producto;
import mintic.edu.proyecto28.modelo.ProductoDAO;
import mintic.edu.proyecto28.modelo.ReporteVenta;
import mintic.edu.proyecto28.modelo.Usuario;
import mintic.edu.proyecto28.modelo.UsuarioDAO;
import mintic.edu.proyecto28.modelo.Venta;
import mintic.edu.proyecto28.modelo.VentaDAO;

import mintic.edu.proyecto28.modelo.Proveedor;
import mintic.edu.proyecto28.modelo.ProveedorDAO;
/**
 *
 * 
 */
public class Controlador extends HttpServlet {

    Usuario usuario = new Usuario();
    UsuarioDAO usuarioDao = new UsuarioDAO();
    Cliente cliente = new Cliente();
    ClienteDAO clienteDAO = new ClienteDAO();
    
    Proveedor proveedor = new Proveedor();
    ProveedorDAO proveedorDAO = new ProveedorDAO();
    
    Producto producto = new Producto();
    ProductoDAO productoDAO = new ProductoDAO();
    Venta venta = new Venta();
    VentaDAO ventaDAO = new VentaDAO();
    DetalleVenta detalleVenta = new DetalleVenta();
    List<DetalleVenta> detalleVentas = new ArrayList<DetalleVenta>();
    int numeroFactura = 0;
    String mensaje = null, aviso = null;
    int cedulaCliente = 0;
    int codigoProducto = 0;
    int item = 0;
    int idProducto = 0;
    String descripcion = null;
    int cantidadProducto = 0;
    double precioVenta = 0;
    double valorTotal = 0;
    double valorVenta = 0;
    double valorIva = 0;
    double subtotal = 0;
    double totalIva = 0;
    double totalFactura = 0;
    ReporteVenta reporteVenta = new ReporteVenta();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String menu = request.getParameter("menu");
        String accion = request.getParameter("accion");
        if (menu.equals("Usuarios")) {
            switch (accion) {
                case "Listar":
                    String tipos [] = {"Administrador","Cliente"};
                    List<Usuario> users = new ArrayList<Usuario>();
                    users = usuarioDao.getUsuarios();
                    request.setAttribute("usuarios", usuarioDao.getUsuarios());
                    System.out.println("Usuario"+users.size());
                    request.setAttribute("tipos", tipos);
                    request.setAttribute("usuarioEdit", new Usuario());
                    break;
                case "Agregar":
                    String mensaje=null;
                    String aviso = null;
                    int idUsuario = Integer.parseInt(request.getParameter("txtId"));
                    String clave = request.getParameter("txtClave");
                    String nombreUsuario = request.getParameter("txtNombre");
                    String correo = request.getParameter("txtCorreo");
                    String tipoUsuario = request.getParameter("txtTipo");
                    if(clave.isEmpty()){
                        mensaje="Faltan Datos de Usuario";
                    }else if(nombreUsuario.isEmpty()){
                        mensaje="Faltan Datos de Usuario";
                    }else if(correo.isEmpty()){
                        mensaje="Faltan Datos de Usuario";
                    }else if(tipoUsuario.isEmpty()){
                        mensaje="Faltan Datos de Usuario";
                    }
                    usuario.setIdUsuario(idUsuario);
                    usuario.setClave(clave);
                    usuario.setCorreo(correo);
                    usuario.setNombreUsuario(nombreUsuario);
                    usuario.setTipoUsuario(tipoUsuario);
                    boolean creado = usuarioDao.agregarUsuario(usuario);
                    if(creado){
                        mensaje = "Usuario Creado Exitosamente";
                    }else {
                        aviso = "Error en datos de Usuario";
                    }
                    request.setAttribute("mensaje", mensaje);
                    request.setAttribute("aviso", aviso);
                    request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar").forward(request, response);
                    break;
                case "Editar":
                    int ideu = Integer.valueOf(request.getParameter("id"));
                    Usuario usu = new Usuario();
                    String [] categorias = {"Administrador","Cliente"};
                    usu = usuarioDao.getUsuarioId(ideu);
                    request.setAttribute("usuarioEdit", usu);
                    request.setAttribute("categorias", categorias);
                    break;
                case "Actualizar":
                    int idUsuarioa = Integer.parseInt(request.getParameter("txtId"));
                    String clavea = request.getParameter("txtClave");
                    String nombreUsuarioa = request.getParameter("txtNombre");
                    String correoa = request.getParameter("txtCorreo");
                    String tipoUsuarioa = request.getParameter("txtTipo");
                    usuario.setIdUsuario(idUsuarioa);
                    usuario.setClave(clavea);
                    usuario.setCorreo(correoa);
                    usuario.setNombreUsuario(nombreUsuarioa);
                    usuario.setTipoUsuario(tipoUsuarioa);
                    usuarioDao.actualizarUsuario(usuario);
                    request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar").forward(request, response);
                    break;
                case "Eliminar":
                    int idUsuarioe = Integer.valueOf(request.getParameter("id"));
                    usuarioDao.eliminarUsuario(idUsuarioe);
                    request.getRequestDispatcher("Controlador?menu=Usuarios&accion=Listar").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("jsp/usuarios.jsp").forward(request, response);
        }
        if (menu.equals("Productos")) {
            switch(accion){
                case "Listar":
                    String titulo = "GESTION DE PRODUCTOS";
                    List<Producto> productos = new ArrayList<Producto>();
                    productos = productoDAO.getProductos();
                    request.setAttribute("titulo", titulo);
                    //request.setAttribute("opcion", "De Base de Datos");
                    request.setAttribute("productos", productos);
                    break;
                case "Agregar":
                    break;
                case "Editar":
                    break;
                case "Eliminar":
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("jsp/productos.jsp").forward(request, response);
        }
        if (menu.equals("Reportes")) {
            switch(accion){
                case "Ventas":
                    String titulo = "TOTAL DE VENTAS POR CLIENTE";
                    double sumaVenta = 0;
                    List<ReporteVenta> reporteVentas = new ArrayList<ReporteVenta>();
                    reporteVentas = ventaDAO.verReporteVenta();
                    if(reporteVentas.size() == 0){
                        aviso = "El reporte de ventas no tiene Datos";
                        mensaje = null;
                    }else{
                        for(ReporteVenta reporteVenta:reporteVentas){
                            sumaVenta += reporteVenta.getVentaTotal();
                        }
                        aviso = null;
                        mensaje = "Reporte de ventas generado exitosamente";
                    }
                    request.setAttribute("titulo", titulo);
                    request.setAttribute("mensaje", mensaje);
                    request.setAttribute("aviso", aviso);
                    request.setAttribute("sumaVenta", sumaVenta);
                    request.setAttribute("reporteVentas", reporteVentas);
                    request.getRequestDispatcher("jsp/reporteVentas.jsp").forward(request, response);
                    break;
                case "Clientes":
                    String titulo1 = "REPORTE DE CLIENTES";
                    request.setAttribute("titulo", titulo1);
                    request.setAttribute("opcion", "LISTA_R");
                    List<Cliente> clientes = new ArrayList<Cliente>();
                    Cliente clienteEdit = new Cliente();
                    clientes = clienteDAO.getClientes();
                    request.setAttribute("clientes",clientes);
                    request.setAttribute("clienteEdit",clienteEdit);
                    request.setAttribute("operacion",1);                    
                    
                    request.getRequestDispatcher("jsp/reporteClientes.jsp").forward(request, response);
                    
                    break;
                case "Usuarios":
                    String tipos [] = {"Administrador","Cliente"};
                    String titulo2 = "REPORTE DE USUARIOS";
                    List<Usuario> users = new ArrayList<Usuario>();
                    users = usuarioDao.getUsuarios();
                    request.setAttribute("titulo", titulo2);
                    request.setAttribute("usuarios", usuarioDao.getUsuarios());
                    System.out.println("Usuario"+users.size());
                    request.setAttribute("tipos", tipos);
                    request.setAttribute("usuarioEdit", new Usuario());  
                    
                    request.getRequestDispatcher("jsp/reporteUsuarios.jsp").forward(request, response);
                    
                    break;
                case "Eliminar":
                    break;
                default:
                    throw new AssertionError();
            }
        }
        if (menu.equals("Ventas")) {
            switch(accion){
                case "Listar":
                    numeroFactura = ventaDAO.calcularIdVenta();
                    numeroFactura += 1;
                    request.setAttribute("idVenta", numeroFactura);
//                    request.setAttribute("titulo", titulo);
                    request.setAttribute("opcion", "LISTA_R");
                    break;
                case "buscarProducto":
                    if(request.getParameter("txtCodigo").isEmpty()){
                        aviso = "Código del Producto en blanco. Reintente";
                        mensaje = null;
                    }else{
                        codigoProducto = Integer.parseInt(request.getParameter("txtCodigo"));
                        // Implementar el código en el DAO de buscar un producto por el código
                        producto = productoDAO.buscarProductoByCodigo(codigoProducto);
                        if(producto.getNombre() == null){
                            aviso = "Código de Producto no se encuentra registrado en la Base de Datos";
                            mensaje = null;
                        }else{
                            aviso = null;
                            mensaje = "Búsqueda de Producto exitosa";
                        }
                    }
                    request.setAttribute("mensaje", mensaje);
                    request.setAttribute("aviso", aviso);
                    request.setAttribute("detalleVentas", detalleVentas);
                    request.setAttribute("idVenta", numeroFactura);
                    request.setAttribute("clienteFactura", cliente);
                    request.setAttribute("productoFactura", producto);
                    break;
                case "agregarProducto":
                    item += 1;
                    codigoProducto = Integer.parseInt(request.getParameter("txtCodigo"));
                    descripcion = request.getParameter("txtNombreProducto");
                    cantidadProducto = Integer.parseInt(request.getParameter("txtCantidad"));
                    precioVenta = Double.parseDouble(request.getParameter("txtPrecioVenta"));
                    valorVenta = precioVenta * cantidadProducto;
                    valorIva = Math.round(valorVenta * producto.getIva() / 100);
//                    valorIva = Math.round((valorVenta * producto.getIva() / 100) * 100 / 100);
                    valorTotal = valorVenta + valorIva;
                    subtotal += valorVenta;
                    totalIva += valorIva;
                    detalleVenta = new DetalleVenta();
                    detalleVenta.setId(item);
                    detalleVenta.setDescripcion(descripcion);
                    detalleVenta.setCantidadProducto(cantidadProducto);
                    detalleVenta.setIdProducto(codigoProducto);
                    detalleVenta.setPrecioVenta(precioVenta);
                    detalleVenta.setValorVenta(valorVenta);
                    detalleVentas.add(detalleVenta);
                    totalFactura = subtotal + totalIva;
                    request.setAttribute("idVenta", numeroFactura);
                    request.setAttribute("mensaje", mensaje);
                    request.setAttribute("aviso", aviso);
                    request.setAttribute("clienteFactura", cliente);
                    request.setAttribute("productoFactura", producto);
                    request.setAttribute("detalleVentas", detalleVentas);
                    request.setAttribute("subtotal", subtotal);
                    request.setAttribute("totalIva", totalIva);
                    request.setAttribute("totalFactura", totalFactura);
                    break;
                case "Agregar":
                    break;
                case "buscarCliente":
                    if(request.getParameter("txtCedula").isEmpty()){
                        aviso = "Cédula de Cliente en blanco. Reintente";
                        mensaje = null;
                    }else{
                        cedulaCliente = Integer.parseInt(request.getParameter("txtCedula"));
                        cliente = clienteDAO.buscarClienteByCedula(cedulaCliente);
                        if (cliente.getNombrecompleto() == null) {
                            aviso = "Cédula de Cleinte no se encuentra registrada en la Base de Datos";
                            mensaje = null;
                        } else {
                            aviso = null;
                            mensaje = "Búsqueda de Cliente exitosa";
                        }
                    }
                    request.setAttribute("mensaje", mensaje);
                    request.setAttribute("aviso", aviso);
                    request.setAttribute("idVenta", numeroFactura);
                    request.setAttribute("productoFactura", producto);
                    request.setAttribute("clienteFactura", cliente);
                    break;
                case "Editar":
                    break;
                case "Eliminar":
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("jsp/facturas.jsp").forward(request, response);
        }
        if (menu.equals("Clientes")) {
            String titulo = "GESTION DE CLIENTES";
            switch(accion){
                case "Listar":
                    request.setAttribute("titulo", titulo);
                    request.setAttribute("opcion", "LISTA_R");
                    List<Cliente> clientes = new ArrayList<Cliente>();
                    Cliente clienteEdit = new Cliente();
                    clientes = clienteDAO.getClientes();
                    request.setAttribute("clientes",clientes);
                    request.setAttribute("clienteEdit",clienteEdit);
                    request.setAttribute("operacion",1);
                    break;
                case "Agregar":
                    int cedula = Integer.parseInt(request.getParameter("txtCedula"));
                    String nombreCompleto = request.getParameter("txtNombre");
                    String direccion = request.getParameter("txtDireccion");
                    String telefono = request.getParameter("txtTelefono");
                    String correo = request.getParameter("txtCorreo");
                    if(cedula == 0 || nombreCompleto.isEmpty() || direccion.isEmpty() || telefono.isEmpty() || correo.isEmpty()){
                        aviso = "Error. Faltan Datos del Cliente";
                        mensaje = null;
                    }else{
                        cliente.setCedula(cedula);
                        cliente.setNombrecompleto(nombreCompleto);
                        cliente.setTelefono(telefono);
                        cliente.setDireccion(direccion);
                        cliente.setCorreo(correo);
                        System.out.println("Cliente a Crear: " + cliente.toString());
                        boolean creado = clienteDAO.agregarCliente(cliente);
                        if(creado){
                            mensaje = "Cliente Creado Exitosamente";
                            aviso = null;
                        }else {
                            aviso = "Error. Faltan Datos del Cliente";
                            mensaje = null;
                        }
                    }
                    request.setAttribute("operacion", 1);
                    request.setAttribute("mensaje", mensaje);
                    request.setAttribute("aviso", aviso);
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;
                case "Consultar":
                    int txtCedula = Integer.parseInt(request.getParameter("txtCedula"));
                    cliente = clienteDAO.buscarClienteByCedula(txtCedula);
                    if(cliente.getNombrecompleto()== null){
                        aviso = "Cliente No existe";
                        mensaje = null;
                        request.setAttribute("aviso", aviso);
                        request.setAttribute("mensaje", mensaje);
                        request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    }else{
                        mensaje = "Cliente Encontrado";
                        aviso = null;
                    }
                    System.out.println("Cliente"+cliente.toString());
                    request.setAttribute("operacion", 0);
                    request.setAttribute("aviso", aviso);
                    request.setAttribute("mensaje", mensaje);
                    request.setAttribute("clienteEdit", cliente);
                    request.setAttribute("opcion","Edición de Clientes");
                    break;
                case "Actualizar":
                    int cedulaa = Integer.parseInt(request.getParameter("txtCedula"));
                    String nombreCompletoa = request.getParameter("txtNombre");
                    String direcciona = request.getParameter("txtDireccion");
                    String telefonoa = request.getParameter("txtTelefono");
                    String correoa = request.getParameter("txtCorreo");
                    if(cedulaa == 0 || nombreCompletoa.isEmpty() || direcciona.isEmpty() || telefonoa.isEmpty() || correoa.isEmpty()){
                        aviso = "Error. Faltan Datos del Cliente";
                        mensaje = null;
                    }else{
                        cliente.setCedula(cedulaa);
                        cliente.setNombrecompleto(nombreCompletoa);
                        cliente.setTelefono(telefonoa);
                        cliente.setDireccion(direcciona);
                        cliente.setCorreo(correoa);
                        System.out.println("Cliente a Crear: " + cliente.toString());
                        boolean creado = clienteDAO.actualizarCliente(cliente);
                        if(creado){
                            mensaje = "Cliente Actualizado Exitosamente";
                            aviso = null;
                        }else {
                            aviso = "Error. Faltan Datos del Cliente";
                            mensaje = null;
                        }
                    }
                    request.setAttribute("operacion", 1);
                    request.setAttribute("mensaje", mensaje);
                    request.setAttribute("aviso", aviso);
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;
                case "Eliminar":
                    int txtCedulae = Integer.parseInt(request.getParameter("txtCedula"));
                    cliente = clienteDAO.buscarClienteByCedula(txtCedulae);
                    if(cliente.getNombrecompleto() == null){
                        aviso = "Cliente No existe. No se puede eliminar";
                        mensaje = null;
                        request.setAttribute("aviso", aviso);
                        request.setAttribute("mensaje", mensaje);
                        request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    }else{
                        boolean eliminado = clienteDAO.eliminarCliente(cliente);
                        if(eliminado){
                            mensaje = "Cliente Eliminado Exitosamente";
                            aviso = null;
                        }else {
                            aviso = "Error. Faltan Datos del Cliente";
                            mensaje = null;
                        }
                    }
                    request.getRequestDispatcher("Controlador?menu=Clientes&accion=Listar").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("jsp/clientes.jsp").forward(request, response);
        }
        
        //Proveedores
        if (menu.equals("Proveedores")) {
            String titulo = "GESTIÓN DE PROVEEDORES";
            switch(accion){
                case "Listar":
                    request.setAttribute("titulo", titulo);
                    request.setAttribute("opcion", "LISTA_R");
                    List<Proveedor> proveedores = new ArrayList<Proveedor>();
                    Proveedor proveedorEdit = new Proveedor();
                    proveedores = proveedorDAO.getProveedores();
                    request.setAttribute("proveedor",proveedores);
                    request.setAttribute("proveedorEdit",proveedorEdit);
                    request.setAttribute("operacion",1);
                    break;
                case "Agregar":
                    String Ced = request.getParameter("txtCedula1");
                    int cedula3 = Integer.parseInt(Ced); 
                    String nombreCompleto3 = request.getParameter("txtNombre1");
                    String direccion3 = request.getParameter("txtDireccion1");
                    String telefono3 = request.getParameter("txtTelefono1");
                    String correo3 = request.getParameter("txtCorreo1");
                    if(cedula3 == 0 || nombreCompleto3.isEmpty() || direccion3.isEmpty() || telefono3.isEmpty() || correo3.isEmpty()){
                        aviso = "Error. Faltan Datos del Proveedor";
                        mensaje = null;
                    }else{
                        proveedor.setCedula(cedula3);
                        proveedor.setCiudad(correo3);
                        proveedor.setDireccion(direccion3);
                        proveedor.setNombrecompleto(nombreCompleto3);
                        proveedor.setTelefono(telefono3);                        
                        
                        System.out.println("Proveedor a Crear: " + proveedor.toString());
                        boolean creado = proveedorDAO.agregarProveedor(proveedor);
                        if(creado){
                            mensaje = "Proveedor Creado Exitosamente";
                            aviso = null;
                        }else {
                            aviso = "Error. Faltan Datos del Proveedor";
                            mensaje = null;
                        }
                    }
                    request.setAttribute("operacion", 1);
                    request.setAttribute("mensaje", mensaje);
                    request.setAttribute("aviso", aviso);
                    request.getRequestDispatcher("Controlador?menu=Proveedores&accion=Listar").forward(request, response);
                    break;
                case "Consultar":
                    int txtCedula = Integer.parseInt(request.getParameter("txtCedula1"));
                    proveedor = proveedorDAO.buscarProveedorByCedula(txtCedula);
                    if(proveedor.getNombrecompleto()== null){
                        aviso = "Proveedor No existe";
                        mensaje = null;
                        request.setAttribute("aviso", aviso);
                        request.setAttribute("mensaje", mensaje);
                        request.getRequestDispatcher("Controlador?menu=Proveedores&accion=Listar").forward(request, response);
                    }else{
                        mensaje = "Proveedor Encontrado";
                        aviso = null;
                    }
                    System.out.println("Proveedor"+proveedor.toString());
                    request.setAttribute("operacion", 0);
                    request.setAttribute("aviso", aviso);
                    request.setAttribute("mensaje", mensaje);
                    request.setAttribute("proveedorEdit", proveedor);
                    request.setAttribute("opcion","Edición de Proveedores");
                    break;
                case "Actualizar":
                    int cedulaa = Integer.parseInt(request.getParameter("txtCedula1"));
                    String nombreCompletoa = request.getParameter("txtNombre1");
                    String direcciona = request.getParameter("txtDireccion1");
                    String telefonoa = request.getParameter("txtTelefono1");
                    String correoa = request.getParameter("txtCorreo1");
                    if(cedulaa == 0 || nombreCompletoa.isEmpty() || direcciona.isEmpty() || telefonoa.isEmpty() || correoa.isEmpty()){
                        aviso = "Error. Faltan Datos del Proveedor";
                        mensaje = null;
                    }else{
                        proveedor.setCedula(cedulaa);
                        proveedor.setNombrecompleto(nombreCompletoa);
                        proveedor.setTelefono(telefonoa);
                        proveedor.setDireccion(direcciona);
                        proveedor.setCiudad(correoa);
                        System.out.println("Proveedor a Crear: " + proveedor.toString());
                        boolean creado = proveedorDAO.actualizarProveedor(proveedor);
                        if(creado){
                            mensaje = "Proveedor Actualizado Exitosamente";
                            aviso = null;
                        }else {
                            aviso = "Error. Faltan Datos del Proveedor";
                            mensaje = null;
                        }
                    }
                    request.setAttribute("operacion", 1);
                    request.setAttribute("mensaje", mensaje);
                    request.setAttribute("aviso", aviso);
                    request.getRequestDispatcher("Controlador?menu=Proveedores&accion=Listar").forward(request, response);
                    break;
                case "Eliminar":
                    int txtCedulae = Integer.parseInt(request.getParameter("txtCedula1"));
                    proveedor = proveedorDAO.buscarProveedorByCedula(txtCedulae);
                    if(proveedor.getNombrecompleto() == null){
                        aviso = "Proveedor No existe. No se puede eliminar";
                        mensaje = null;
                        request.setAttribute("aviso", aviso);
                        request.setAttribute("mensaje", mensaje);
                        request.getRequestDispatcher("Controlador?menu=Proveedores&accion=Listar").forward(request, response);
                    }else{
                        boolean eliminado = proveedorDAO.eliminarProveedor(proveedor);
                        if(eliminado){
                            mensaje = "Proveedor Eliminado Exitosamente";
                            aviso = null;
                        }else {
                            aviso = "Error. Faltan Datos del Proveedor";
                            mensaje = null;
                        }
                    }
                    request.getRequestDispatcher("Controlador?menu=Proveedores&accion=Listar").forward(request, response);
                    break;
                default:
                    throw new AssertionError();
            }
            request.getRequestDispatcher("jsp/Proveedores.jsp").forward(request, response);
        }        
        
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
