package sv.edu.udb.www.lastone.conexion;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import sv.edu.udb.www.recursoshumanos.Model.Contratacion;
import sv.edu.udb.www.recursoshumanos.Dao.TipoContratacionDAO;

@WebServlet("/contrataciones")
public class ContratacionesServlet extends HttpServlet {

    private TipoContratacionDAO tipoContratacionDAO = new TipoContratacionDAO();

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Lógica para obtener la lista de contrataciones
        List<Contratacion> listaContrataciones = obtenerContrataciones();

        // Establecer la lista en el atributo de la solicitud
        request.setAttribute("listaContrataciones", listaContrataciones);

        // Redirigir a la página JSP
        request.getRequestDispatcher("/WEB-INF/jsp/contrataciones.jsp").forward(request, response);
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

    // Método simulado para obtener la lista de contrataciones
    private List<Contratacion> obtenerContrataciones() {
        // Aquí puedes implementar la lógica para obtener los datos de la base de datos
        return List.of();  // Ejemplo: retorna una lista vacía
    }
}
