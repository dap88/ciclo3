package mintic.edu.proyecto28.controlador;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import mintic.edu.proyecto28.modelo.Usuario;
import mintic.edu.proyecto28.modelo.UsuarioDAO;

/**
 *
 * 
 */
public class LoginServlet extends HttpServlet {


    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        if (request.getParameter("btnAcceder") != null) {
            Usuario usu = new Usuario();
            String user = request.getParameter("txtUsuario");
            String clave = request.getParameter("txtPassword");
            usu.setNombreUsuario(user);
            usu.setClave(clave);
            UsuarioDAO login = new UsuarioDAO();
            String estado, correo;
            try {
                estado = login.login(usu);
                if ("true".equals(estado) || (user.equals("admininicial") && clave.equals("admininicial123456"))) {
                    HttpSession objSesion = request.getSession();
                    if (usu.getTipoUsuario().equals("Administrador")) {
                        objSesion.setAttribute("usuario", user);
                        objSesion.setAttribute("objusuario", usu);
                        objSesion.setAttribute("nivel", "Administrador");
                        response.sendRedirect("jsp/vistaAdministrador.jsp");
//                        response.sendRedirect("jsp/vistaAdmin.jsp");
                    } else if (usu.getTipoUsuario().equals("Cliente")) {
                        objSesion.setAttribute("usuario", user);
                        objSesion.setAttribute("objusuario", usu);
                        objSesion.setAttribute("nivel", "Cliente");
                        response.sendRedirect("jsp/vistaCliente.jsp");
                    }
                }else{
                    response.sendRedirect("./jsp/error.html");
                }
            } catch (Exception ex) {
                response.sendRedirect("./jsp/error.html");
            }
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
