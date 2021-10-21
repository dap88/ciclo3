<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <title>Página de Usuarios</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" 
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" crossorigin="anonymous">
    </head>
    <body>
        <div class="d-flex"> 
            <div class="card col-sm-4">
                <div class="card-body">
                    <div class="card-header" style="color:darkolivegreen;font-weight:bold">ADMINISTRACION DE USUARIOS</div>
                    <form class="needs-validation" action="Controlador?menu=Usuarios" method="POST" novalidate>
                        <div class="form-group" >
                            <label style="color:darkolivegreen;font-weight:bold">No ID</label>
                            <c:if test="${usuarioEdit.idUsuario!=0}">
                                <input  type="text" name="txtId" value="${usuarioEdit.idUsuario}" readonly="" 
                                        class="form-control">
                            </c:if>
                            <c:if test="${usuarioEdit.idUsuario==0}">
                                <input  type="text" name="txtId" class="form-control" required="">
                                <div class="valid-feedback">Campo OK</div>
                                <div class="invalid-feedback">Complete los datos</div>
                            </c:if>
                        </div>
                        <div class="form-group" >
                            <label style="color:darkolivegreen;font-weight:bold">USUARIO</label>
                            <input  type="text" name="txtNombre" value="${usuarioEdit.nombreUsuario}" 
                                    class="form-control" required="">
                            <div class="valid-feedback">Campo OK</div>
                            <div class="invalid-feedback">Complete los datos</div>
                        </div>
                        <div class="form-group" >
                            <label style="color:darkolivegreen;font-weight:bold">CONTRASEÑA</label>
                            <input  type="password" name="txtClave" value="${usuarioEdit.clave}" 
                                    class="form-control" required="">
                            <div class="valid-feedback">Campo OK</div>
                            <div class="invalid-feedback">Faltan datos del usuario</div>
                        </div>
                        <div class="form-group" >
                            <label style="color:darkolivegreen;font-weight:bold">CORREO ELECTRONICO</label>
                            <input  type="text" name="txtCorreo" value="${usuarioEdit.correo}" 
                                    class="form-control" required="">
                            <div class="valid-feedback">Campo OK</div>
                            <div class="invalid-feedback">Faltan datos del usuario</div>
                        </div>
                        <div class="form-group" >
                            <label style="color:darkolivegreen;font-weight:bold">TIPO USUARIO</label>
                            <select class="form-control" name="txtTipo" required="">
                                <c:if test="${empty categorias}">
                                    <option selected="" disabled=" " value="">Por favor Seleccione..</option>
                                    <option value="Administrador">Administrador</option>
                                    <option value="Cliente">Cliente</option>
                                </c:if>
                                <c:forEach items="${categorias}" var="cate">
                                    <option value="${cate}" ${cate == usuarioEdit.tipoUsuario ? 'selected' :''}>
                                        <c:out value="${cate}" />
                                    </option>
                                </c:forEach>
                            </select>
                            <div class="valid-feedback">Campo OK</div>
                            <div class="invalid-feedback">Complete los datos</div>
                        </div>
                        <input type="submit" name="accion" value="Agregar" class="btn btn-dark">
                        <input type="submit" name="accion" value="Actualizar" class="btn btn-dark" >
                        <c:if test="${mensaje != null}" >
                            <div class="alert alert-primary alert-dismissable">
                                <button type="button" class="close" data-dismiss="alert">✓</button>
                                <strong>Correcto! </strong> ${mensaje}.
                            </div>
                        </c:if>
                        <c:if test="${aviso != null}" >
                            <div class="alert alert-danger">
                                <button type="button" class="close" data-dismiss="alert">X</button>
                                <strong>Incorrecto! </strong> ${aviso}.
                            </div>
                        </c:if>
                    </form>
                </div>
            </div>
            <div class="col-sm-8">
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>No. ID</th>
                            <th>NOMBRE</th>
                            <th>CLAVE</th>
                            <th>CORREO</th>
                            <th>TIPO USUARIO</th>
                            <th>ACCIONES</th>
                    </thead>
                    <tbody>
                        <c:forEach var="e" items="${usuarios}">
                            <tr>
                                <td>${e.idUsuario}</td>
                                <td>${e.nombreUsuario}</td>
                                <td>${e.clave}</td>
                                <td>${e.correo}</td>
                                <td>${e.tipoUsuario}</td>
                                <td>
                                    <a href="Controlador?menu=Usuarios&accion=Editar&id=${e.idUsuario}" 
                                       class="btn btn-warning btn-sm"><i class="fa fa-edit"></i></a>
                                    <a href="Controlador?menu=Usuarios&accion=Eliminar&id=${e.idUsuario}" 
                                       class="btn btn-danger btn-sm"><i class="fa fa-trash-alt"></i></a>
                                    <!-- Modal -->
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>                                    
        <script>
            // Example starter JavaScript for disabling form submissions if there are invalid fields
            (function () {
                'use strict'

                // Fetch all the forms we want to apply custom Bootstrap validation styles to
                var forms = document.querySelectorAll('.needs-validation')

                // Loop over them and prevent submission
                Array.prototype.slice.call(forms)
                        .forEach(function (form) {
                            form.addEventListener('submit', function (event) {
                                if (!form.checkValidity()) {
                                    event.preventDefault()
                                    event.stopPropagation()
                                }
                                form.classList.add('was-validated')
                            }, false)
                        })
            })()
        </script>
    </body>
</html>
