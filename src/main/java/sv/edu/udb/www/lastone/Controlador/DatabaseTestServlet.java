package sv.edu.udb.www.lastone.Controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


// URL para acceder: http://localhost:8080/LastOne-1.0-SNAPSHOT/test-db
@WebServlet("/test-db")
public class DatabaseTestServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");

        try (PrintWriter out = response.getWriter()) {
            out.println("<h1>Prueba de conexión a la base de datos</h1>");

            // Obtener conexión desde JNDI
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
                        out.println("<li>ID: " + rs.getInt("idDepartamento") + ", Nombre: " + rs.getString("nombreDepartamento") + "</li>");
                    }
                    out.println("</ul>");

                    conn.close();
                } else {
                    out.println("<p>❌ Error en la conexión.</p>");
                }
            } catch (NamingException | SQLException e) {
                out.println("<p>❌ Error al conectar: " + e.getMessage() + "</p>");
                e.printStackTrace();
            }
        }
    }
}
