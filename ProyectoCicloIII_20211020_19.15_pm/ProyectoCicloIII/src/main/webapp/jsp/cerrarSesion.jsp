

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page session="true" %>
<%  
    HttpSession objSesion = request.getSession();  
    objSesion.invalidate();
    out.print("<script>location.replace('../index.html');</script>");
%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title></title>
    </head>
    <body>
        
    </body>
</html>
