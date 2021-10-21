<%-- 
    Document   : Proveedores
    Created on : 19/10/2021, 11:39:54 PM
    Author     : USER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página de Proveedores</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" 
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" crossorigin="anonymous">
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <h1 style="text-align: center">${titulo}</h1>

                <div class="col-sm-2" >
                <c:if test="${mensaje != null}" >
                    <div class="alert alert-success alert-dismissible">
                        <button type="button" class="close" data-dismiss="alert">&times;</button>
                        <strong>Resultado! </strong>${mensaje}
                    </div>
                </c:if>
                <c:if test="${aviso != null}" >
                    <div class="alert alert-danger alert-dismissible">
                        <button type="button" class="close" data-dismiss="alert">&times;</button>
                        <strong>Cuidado! </strong>${aviso}
                    </div>
                </c:if>
                </div>
                <div class="col-sm-8">
                    <form action="/Controlador?menu=Proveedores" method="post" class="form-horizontal">
                        <table border="0">
                            <tbody>
                                <tr>
                                    <td style="color:darkolivegreen;font-weight:bold">NIT:</td>
                                    <c:if test="${proveedorEdit.cedula != 0}">
                                        <td><input type="text" name="txtCedula1" class="form-control" size="50" placeholder="Escriba el NIT" 
                                                   value="${proveedorEdit.cedula}" readonly="">
                                        </td>
                                    </c:if>
                                    <c:if test="${proveedorEdit.cedula == 0}">
                                        <td><input type="text" name="txtCedula1" class="form-control" size="50" placeholder="Escriba el NIT" 
                                                   value="${proveedorEdit.cedula}" required="" >
                                        </td>
                                    </c:if>
                                    <td style="color:darkolivegreen;font-weight:bold">Teléfono:</td>
                                    <td><input type="text" name="txtTelefono1" class="form-control" size="50" placeholder="Escriba el No de Teléfono"
                                               value="${proveedorEdit.telefono}">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="color:darkolivegreen;font-weight:bold">Nombre Completo:</td>
                                    <td><input type="text" name="txtNombre1" class="form-control" size="50" placeholder="Escriba el Nombre Completo"
                                               value="${proveedorEdit.nombrecompleto}">
                                    </td>
                                    <td style="color:darkolivegreen;font-weight:bold">Ciudad:</td>
                                    <td><input type="text" name="txtCorreo1" class="form-control" size="50" placeholder="Escriba la Ciudad del Proveedor"
                                               value="${proveedorEdit.ciudad}">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="color:darkolivegreen;font-weight:bold">Dirección:</td>
                                    <td><input type="text" name="txtDireccion1" class="form-control" size="50" placeholder="Escriba la Dirección del Proveedor"
                                               value="${proveedorEdit.direccion}">
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <br/><br/>
                        <div class="btn-group-justified">
                            <c:if test="${operacion == 1}">
                                <button type="submit" class="btn btn-primary" name="accion" value="Consultar">Consultar</button>
                            </c:if>
                            <c:if test="${operacion != 1}">
                                <button type="submit" class="btn btn-primary" name="accion" value="Consultar" disabled="">Consultar</button>
                            </c:if>
                            <c:if test="${operacion == 1}">
                                <button type="submit" class="btn btn-success" name="accion" value="Agregar">Agregar</button>
                            </c:if>
                            <c:if test="${operacion != 1}">
                                <button type="submit" class="btn btn-success" name="accion" value="Agregar" disabled="">Agregar</button>
                            </c:if>
                            <c:if test="${operacion == 1}">
                                <button type="submit" class="btn btn-warning" name="accion" value="Actualizar" disabled="">Actualizar</button>
                            </c:if>
                            <c:if test="${operacion != 1}">
                                <button type="submit" class="btn btn-warning" name="accion" value="Actualizar">Actualizar</button>
                            </c:if>
                            <c:if test="${operacion == 1}">
                                <button type="submit" class="btn btn-danger" name="accion" value="Eliminar" disabled="">Borrar</button>
                            </c:if>
                            <c:if test="${operacion != 1}">
                                <button type="submit" class="btn btn-danger" name="accion" value="Eliminar">Borrar</button>
                            </c:if>
                        </div>
                        <br/><br/>
                    </form>

                    <table border="0" class="table table-striped">
                        <thead>
                            <tr>
                                <c:if test="${operacion == 1}">
                                    <th style="color:darkolivegreen;font-weight:bold">NIT</th>
                                    <th style="color:darkolivegreen;font-weight:bold">NOMBRE COMPLETO</th>
                                    <th style="color:darkolivegreen;font-weight:bold">DIRECCION</th>
                                    <th style="color:darkolivegreen;font-weight:bold">TELEFONO</th>
                                    <th style="color:darkolivegreen;font-weight:bold">CIUDAD</th>
                                </c:if>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${proveedor}" var="provee">
                                <tr>
                                    <td>${provee.cedula}</td>
                                    <td>${provee.nombrecompleto}</td>
                                    <td>${provee.direccion}</td>
                                    <td>${provee.telefono}</td>
                                    <td>${provee.ciudad}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                    <a href="/" class="btn btn-primary">Ir al Inicio</a>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>                                    
    </body>
</html>
