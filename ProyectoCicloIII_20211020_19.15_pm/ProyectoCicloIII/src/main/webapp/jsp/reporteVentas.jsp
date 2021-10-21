<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Reporte de Ventas por Cliente</title>
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
                        <strong>Resultado!</strong>${mensaje}
                    </div>
                </c:if>
                <c:if test="${aviso != null}" >
                    <div class="alert alert-danger alert-dismissible">
                        <button type="button" class="close" data-dismiss="alert">&times;</button>
                        <strong>Cuidado!</strong>${aviso}
                    </div>
                </c:if>
                </div>
                <div class="col-sm-8">

                    <table border="0" class="table table-striped">
                        <thead>
                            <tr>
                                <th>Cédula</th>
                                <th>Nombre</th>
                                <th style="text-align: center">Valor Total Ventas</th>
                            </tr>
                        </thead>
                        <tbody>
                        <c:forEach items="${reporteVentas}" var="repv">
                            <tr>
                                <td>${repv.getCedulaCliente()}</td>
                                <td>${repv.getNombreCliente()}</td>
                                <td style="text-align: right">${repv.getVentaTotal()}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div class="form-control col-sm-4">
                    </div>
                    <div class="form-control col-sm-4 d-flex">
                        <label style="text-align: left;size: 20"  >Total Ventas $</label>
                        <input type="text" readonly="" class="form-control" value="${sumaVenta}" 
                               style="text-align: right">
                    </div>
                </div>
            </div>
        </div>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>                                    
    </body>
</html>
