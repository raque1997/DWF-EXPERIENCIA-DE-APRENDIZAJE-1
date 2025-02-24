package sv.edu.udb.www.lastone.Controlador;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import sv.edu.udb.www.martesnew.Cargo.CargoDAO;

@WebServlet("/CargoServlet")
public class CargoServlet extends HttpServlet {
    private Connection getConnection() throws SQLException {
        return DriverManager.getConnection("jdbc:mysql://localhost:3306/tu_basedatos", "usuario", "contraseña");
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (Connection con = getConnection()) {
            CargoDAO dao = new CargoDAO(con);
            List<CargoDAO> lista = dao.getAllCargos();
            request.setAttribute("cargos", lista);
            request.getRequestDispatcher("cargos.jsp").forward(request, response);
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }
}

