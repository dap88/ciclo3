<%-- 
    Document   : reporteClientes
    Created on : 20/10/2021, 01:44:04 AM
    Author     : INCLINE ATELIER
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
        <title>Reporte de Clientes</title>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" 
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" crossorigin="anonymous">
    </head>
    <body>
        <div class="container-fluid">
            <div class="row">
                <center>
                    <h1 style="color:darkolivegreen;font-weight:bold">${titulo}</h1>
                    <br/><br/>
                    <div class="col-sm-8">

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
                </center>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>                                    
    </body>
</html>
