package test;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import jakarta.annotation.Resource;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import javax.sql.DataSource;

@WebServlet("/departamentos")
public class DepartamentoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    // Intentar inyectar el DataSource usando el JNDI configurado en Payara
    //@Resource(name = "jdbc/mysql/db_departamentos")
    @Resource(lookup = "jdbc/mysql/db_departamentos")

    private DataSource dataSource;

        protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            out.println("<html><body>");
            out.println("<h2>Lista de Departamentos</h2>");
            try {
                // Si la inyección de @Resource falla, obtener el DataSource manualmente
                if (dataSource == null) {
                    InitialContext ctx = new InitialContext();
                    //dataSource = (DataSource) ctx.lookup("java:comp/env/jdbc/mysql/db_departamentos");
                    dataSource = (DataSource) ctx.lookup("jdbc/mysql/db_departamentos");
                }

                try (Connection conn = dataSource.getConnection();
                     PreparedStatement stmt = conn.prepareStatement("SELECT id, nombre FROM departamento");
                     ResultSet rs = stmt.executeQuery()) {

                    out.println("<ul>");
                    while (rs.next()) {
                        out.println("<li>ID: " + rs.getInt("id") + ", Nombre: " + rs.getString("nombre") + "</li>");
                    }
                    out.println("</ul>");
                }
            } catch (Exception e) {
                out.println("<p style='color:red;'>Error: " + e.getMessage() + "</p>");
                e.printStackTrace(out);
            }
            out.println("</body></html>");
        }
    }
}
