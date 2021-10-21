<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Página de Facturación</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" 
              integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.2/css/all.css" crossorigin="anonymous">
    </head>
    <body>
        <h1 style="text-align: center">GESTION COMERCIAL</h1>
        <br/><br/>
        <form method="post" action="../Controlador?menu=Ventas">
            <div class="d-flex">
                <div class="col-sm-5">
                    <div class="card">
                        <div class="card-body">
                            <div class="form-group">
                                <label style="color:darkolivegreen;font-weight:bold">Datos del Cliente</label>
                            </div>
                            <div class="form-group d-flex">
                                <div class="col-sm-4">
                                    <input type="text" name="txtCedula" class="form-control" placeholder="Cédula del Cliente" 
                                           value="${clienteFactura.getCedula()}">
                                </div>
                                <div class="col-sm-2">
                                    <button type="submit" name="accion" value="buscarCliente" class="btn btn-dark">Consultar</button>
                                </div>
                                <div class="col-sm-6">
                                    <input type="text" name="txtNombreCliente" class="form-control" value="${clienteFactura.getNombrecompleto()}"
                                           placeholder="Nombre del Cliente" readonly="">
                                </div>
                            </div>
                            <br/><br/>
                            <div class="form-group">
                                <label style="color:darkolivegreen;font-weight:bold">Datos del Producto</label>
                            </div>
                            <div class="form-group d-flex">
                                <div class="col-sm-4">
                                    <input type="text" name="txtCodigo" class="form-control" placeholder="Código del Producto" 
                                           value="${productoFactura.getCodigo()}">
                                </div>
                                <div class="col-sm-2">
                                    <button type="submit" name="accion" value="buscarProducto" class="btn btn-dark">Consultar</button>
                                </div>
                                <div class="col-sm-6">
                                    <input type="text" name="txtNombreProducto" class="form-control" placeholder="Nombre del Producto"
                                           value="${productoFactura.getNombre()}" readonly="">
                                </div>
                            </div>
                            <br/><br/>
                            <center>
                            <div class="col-sm-2">
                                <label style="color:darkolivegreen;font-weight:bold">Cantidad</label>
                            </div>
                            </center>
                            <div class="form-group d-flex">
                                <div class="col-sm-6">
                                    <button type="submit" name="accion" value="agregarProducto" class="btn btn-success">Agregar Producto</button>
                                </div>
                                <div class="col-sm-2">
                                    <input type="number" name="txtCantidad" class="form-control" value="1" style="text-align: right">
                                </div>
                                <div class="col-sm-4">
                                    <input type="text" name="txtPrecioVenta" class="form-control" placeholder="$ 0,000.00" style="text-align: right" 
                                           value="${productoFactura.getPrecioVenta()}" readonly="">
                                </div>
                            </div>
                            <div class="form-group d-flex">
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
                        </div>

                    </div>
                </div>
                <div class="col-sm-7">
                    <div class="card">
                        <div class="card-header">
                            <div class="d-flex col-sm-6">
                                <label style="color:darkolivegreen;font-weight:bold">Factura de Venta No: </label>
                                <input type="text" name="numeroFactura" class="form-control" style="text-align: right"
                                       value="${idVenta}" readonly="">
                            </div>

                        </div>
                        <div class="card-body">
                            <div class="col-sm-12 ml-auto">
                                <table border="0" class="table table-hover" >
                                    <thead>
                                        <tr>
                                            <th>No</th>
                                            <th>CODIGO</th>
                                            <th>DESCRIPCION</th>
                                            <th>PRECIO VENTA</th>
                                            <th>CANTIDAD</th>
                                            <th>TOTAL</th>
                                        </tr>
                                    </thead>
                                    <tbody>
                                        <c:forEach items="${detalleVentas}" var="lista">
                                            <tr>
                                                <td>${lista.getId()}</td>
                                                <td>${lista.getIdProducto()}</td>
                                                <td>${lista.getDescripcion()}</td>
                                                <td>${lista.getPrecioVenta()}</td>
                                                <td>${lista.getCantidadProducto()}</td>
                                                <td>${lista.getValorVenta()}</td>
                                            </tr>
                                        </c:forEach>
                                    </tbody>
                                </table>

                            </div>
                        </div>
                        <div class="card-footer">
                            <div class="form-group d-flex" >
                                <table class="table table-active">
                                    <tbody>
                                        <tr>
                                            <td>
                                                <button type="submit" name="accion" value="generarFactura" class="btn btn-success">Generar Factura</button>
                                            </td>
                                            <td>
                                                <button type="submit" name="accion" value="cancelarFactura" class="btn btn-danger">Cancelar Registro</button>
                                            </td>
                                            <td>
                                                <label>Subtotal: </label>
                                            </td>
                                            <td>
                                                <input type="text" name="txtSubtotal" class="form-control" style="text-align: right" readonly=""
                                                       value="${subtotal}" placeholder="$/ 0,000.00">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td></td>
                                            <td>
                                                <label>Valor Iva: </label>
                                            </td>
                                            <td>
                                                <input type="text" name="txtIva" class="form-control" style="text-align: right" 
                                                       value="${totalIva}" readonly="" placeholder="$/ 0,000.00">
                                            </td>
                                        </tr>
                                        <tr>
                                            <td></td>
                                            <td></td>
                                            <td>
                                                <label>Total con Iva: </label>
                                            </td>
                                            <td>
                                                <input type="text" name="txtTotal" class="form-control" style="text-align: right"
                                                       value="${totalFactura}" readonly="" placeholder="$/ 0,000.00">
                                            </td>
                                        </tr>
                                    </tbody>
                                </table>
                            </div>

                        </div>
                    </div>
                </div>
            </div>
        </form>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.6/umd/popper.min.js" integrity="sha384-wHAiFfRlMFy6i5SRaxvfOCifBUQy1xHdJ/yoi7FRNXMRBu5WHdZYu1hA6ZOblgut" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.2.1/js/bootstrap.min.js" integrity="sha384-B0UglyR+jN6CkvvICOB2joaf5I4l3gm9GU6Hc1og6Ls7i6U/mkkaduKaBhlAXv9k" crossorigin="anonymous"></script>                                    
    </body>
</html>
