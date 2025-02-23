<%@ page import="java.sql.*, javax.naming.*, javax.sql.DataSource" %>
<html>
<head>
    <title>Prueba de Base de Datos</title>
</head>
<body>
<h1>Prueba de conexión a la base de datos</h1>

<%
    try {
        InitialContext initialContext = new InitialContext();
        DataSource dataSource = (DataSource) initialContext.lookup("jdbc/mysql/db_departamentos");
        Connection conn = dataSource.getConnection();

        if (conn != null) {
            out.println("<p>✅ Conexión exitosa a la base de datos.</p>");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * FROM departamento");

            out.println("<h2>📋 Datos de la tabla 'departamentos':</h2>");
            out.println("<ul>");

            while (rs.next()) {
                out.println("<li>ID: " + rs.getInt("id") + ", Nombre: " + rs.getString("nombre") + "</li>");
            }
            out.println("</ul>");

            conn.close();
        } else {
            out.println("<p>❌ Error en la conexión.</p>");
        }
    } catch (Exception e) {
        out.println("<p>❌ Error al conectar: " + e.getMessage() + "</p>");
        e.printStackTrace();
    }
%>

</body>
</html>

