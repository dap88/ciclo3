<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página de Clientes</title>
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
                    <form action="/Controlador?menu=Clientes" method="post" class="form-horizontal">
                        <table border="0">
                            <tbody>
                                <tr>
                                    <td style="color:darkolivegreen;font-weight:bold">No. Cedula:</td>
                                    <c:if test="${clienteEdit.cedula != 0}">
                                        <td><input type="text" name="txtCedula" class="form-control" size="50" placeholder="Escriba la cédula" 
                                                   value="${clienteEdit.cedula}" readonly="">
                                        </td>
                                    </c:if>
                                    <c:if test="${clienteEdit.cedula == 0}">
                                        <td><input type="text" name="txtCedula" class="form-control" size="50" placeholder="Escriba la cédula" 
                                                   value="${clienteEdit.cedula}" required="" >
                                        </td>
                                    </c:if>
                                    <td style="color:darkolivegreen;font-weight:bold">Teléfono:</td>
                                    <td><input type="text" name="txtTelefono" class="form-control" size="50" placeholder="Escriba el No de Teléfono"
                                               value="${clienteEdit.telefono}">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="color:darkolivegreen;font-weight:bold">Nombre Completo:</td>
                                    <td><input type="text" name="txtNombre" class="form-control" size="50" placeholder="Escriba el Nombre Completo"
                                               value="${clienteEdit.nombrecompleto}">
                                    </td>
                                    <td style="color:darkolivegreen;font-weight:bold">E-mail:</td>
                                    <td><input type="text" name="txtCorreo" class="form-control" size="50" placeholder="Escriba el correo electrónico"
                                               value="${clienteEdit.correo}">
                                    </td>
                                </tr>
                                <tr>
                                    <td style="color:darkolivegreen;font-weight:bold">Dirección:</td>
                                    <td><input type="text" name="txtDireccion" class="form-control" size="50" placeholder="Escriba la Dirección del Cliente"
                                               value="${clienteEdit.direccion}">
                                    </td>
                                </tr>
                            </tbody>
                        </table>
                        <br/><br/>
                        <div class="btn-group-justified">
                            <c:if test="${operacion == 1}">
                                <button type="submit" class="btn btn-dark" name="accion" value="Consultar">Consultar</button>
                            </c:if>
                            <c:if test="${operacion != 1}">
                                <button type="submit" class="btn btn-dark" name="accion" value="Consultar" disabled="">Consultar</button>
                            </c:if>
                            <c:if test="${operacion == 1}">
                                <button type="submit" class="btn btn-dark" name="accion" value="Agregar">Agregar</button>
                            </c:if>
                            <c:if test="${operacion != 1}">
                                <button type="submit" class="btn btn-dark" name="accion" value="Agregar" disabled="">Agregar</button>
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
                                    <th style="color:darkolivegreen;font-weight:bold">No. CEDULA</th>
                                    <th style="color:darkolivegreen;font-weight:bold">NOMBRE COMPLETO</th>
                                    <th style="color:darkolivegreen;font-weight:bold">DIRECCION</th>
                                    <th style="color:darkolivegreen;font-weight:bold">TELEFONO</th>
                                    <th style="color:darkolivegreen;font-weight:bold">EMAIL</th>
                                </c:if>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${clientes}" var="cli">
                                <tr>
                                    <td>${cli.cedula}</td>
                                    <td>${cli.nombrecompleto}</td>
                                    <td>${cli.direccion}</td>
                                    <td>${cli.telefono}</td>
                                    <td>${cli.correo}</td>
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
