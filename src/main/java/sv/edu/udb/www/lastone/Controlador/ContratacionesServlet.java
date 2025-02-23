package sv.edu.udb.www.lastone.Controlador;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import sv.edu.udb.www.lastone.Modelo.Contratacion;
import sv.edu.udb.www.lastone.Modelo.TipoContratacionDAO;

@WebServlet("/contrataciones")
public class ContratacionesServlet extends HttpServlet {

    private TipoContratacionDAO tipoContratacionDAO = new TipoContratacionDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Contratacion> listaContrataciones = obtenerContrataciones();
        request.setAttribute("listaContrataciones", listaContrataciones);
        request.getRequestDispatcher("/WEB-INF/Contrataciones.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int idDepartamento = Integer.parseInt(request.getParameter("idDepartamento"));
            int idEmpleado = Integer.parseInt(request.getParameter("idEmpleado"));
            int idCargo = Integer.parseInt(request.getParameter("idCargo"));
            int idTipoContratacion = Integer.parseInt(request.getParameter("idTipoContratacion"));
            Date fechaContratacion = new SimpleDateFormat("yyyy-MM-dd").parse(request.getParameter("fechaContratacion"));
            double salario = Double.parseDouble(request.getParameter("salario"));
            boolean estado = Boolean.parseBoolean(request.getParameter("estado"));

            Contratacion contratacion = new Contratacion(idDepartamento, idEmpleado, idCargo, idTipoContratacion, fechaContratacion, salario, estado);
            boolean isSaved = tipoContratacionDAO.saveContratacion(contratacion);

            if (isSaved) {
                request.setAttribute("message", "Contratación guardada exitosamente.");
            } else {
                request.setAttribute("message", "Error al guardar la contratación.");
            }

            processRequest(request, response);
        } catch (ParseException | NumberFormatException e) {
            e.printStackTrace();
            request.setAttribute("message", "Error al procesar los datos de la contratación.");
            processRequest(request, response);
        }
    }

    private List<Contratacion> obtenerContrataciones() {
        return List.of();
    }
}
