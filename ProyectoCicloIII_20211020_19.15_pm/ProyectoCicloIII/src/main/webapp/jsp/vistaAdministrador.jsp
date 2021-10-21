<%-- 
    Document   : vistaAdministrador
    Created on : 6/09/2021, 10:27:00 a. m.
    Author     : Grupo 5
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<%@page import="mintic.edu.proyecto28.modelo.Usuario" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Vista de Administrador</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css"
              integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
    </head>
    <body style="background: url(../img/frutas3.png);background-size: cover">

        <%
            Usuario objusuario = new Usuario();
            HttpSession objSesion = request.getSession();
            objusuario = (Usuario) objSesion.getAttribute("objusuario");
        %>
        <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
            <a class="navbar-brand" href="vistaAdministrador.jsp" style="font-weight:bold">TIENDA VIRTUAL</a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" 
                    aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>

            <div class="collapse navbar-collapse" id="navbarSupportedContent">
                <ul class="navbar-nav mr-auto">
                    <li class="nav-item">
                        <a style= "margin-left: 10px; border:none;" class="btn btn-outline-light" 
                           href="../Controlador?menu=Usuarios&accion=Listar" 
                           target="myFrame">Usuarios</a>
                    </li>
                    <li class="nav-item">
                        <a style= "margin-left: 10px; border:none;" class="btn btn-outline-light" href="../Controlador?menu=Clientes&accion=Listar" 
                           target="myFrame">Clientes</a>
                    </li>
                    <li class="nav-item">
                        <a style= "margin-left: 10px; border:none;"  class="btn btn-outline-light" href="../Controlador?menu=Proveedores&accion=Listar" 
                           target="myFrame">Proveedores</a>
                    </li>
                    <!-- Dropdown -->
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbardrop" data-toggle="dropdown">
                            Productos
                        </a>
                        <div class="dropdown-menu">
                            <a class="dropdown-item" class="btn btn-outline-light" href="../Controlador?menu=Productos&accion=Listar" target="myFrame">Listar Productos</a>
                            <a class="dropdown-item" class="btn btn-outline-light" href="cargaProductos.html" target="myFrame">Cargar Archivo</a>
                        </div>
                    </li>
                    <li class="nav-item">
                        <a style= "margin-left: 10px; border:none;"  class="btn btn-outline-light" href="../Controlador?menu=Ventas&accion=Listar" 
                           target="myFrame">Ventas</a>
                    </li>
                    <li class="nav-item">
                        <a style= "margin-left: 10px; border:none;"  class="btn btn-outline-light" href="reportes.html" 
                           target="myFrame">Reportes</a>
                    </li>

                </ul>
                <ul class="navbar-nav justify-content-end">
                    <li class="nav-item dropdown">
                        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" 
                           data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <i class="fas fa-user-lock"></i> ${objusuario.getNombreUsuario()} ${objusuario.getTipoUsuario()}
                        </a>
                        <div class="dropdown-menu dropdown-menu-right text-center" aria-labelledby="navbarDropdown">
                            <a class="dropdown-item" href="#"><img src="../img/user.png" height="50" width="50"></a>
                            <a class="dropdown-item" href="#">${objusuario.getTipoUsuario()}</a>
                            <a class="dropdown-item" href="#">${objusuario.getCorreo()}</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item" href="cerrarSesion.jsp">Salir</a>
                        </div>
                    </li>
                </ul>
            </div>
        </nav>
        <div class="m-4" style="height: 550px;"> 
            <iframe name="myFrame" style="height: 100%; width:100%; border: none"></iframe>
        </div>
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </body>
</html>
