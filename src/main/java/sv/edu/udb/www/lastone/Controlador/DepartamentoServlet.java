package sv.edu.udb.www.lastone.Controlador;
import sv.edu.udb.www.demo.modelo.Departamento;
import sv.edu.udb.www.demo.modelo.DepartamentoDAO;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

//declaramos el servlet para manejar la URL "/departamento"
//@WebServlet("/departamento")
public class DepartamentoServlet extends HttpServlet {

    // Creamos una instancia de DepartamentoDAO para manejar las operaciones con la base de datos
    private DepartamentoDAO dao;

    @Override
    public void init() throws ServletException {
        dao = new DepartamentoDAO();
    }

    // Este metodo procesa las solicitudes HTTP para agregar departamentos
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Obtenemos el parámetro "action" de la solicitud para determinar qué acción realizar
        String action = request.getParameter("action");

        // Si la acción es "add", significa que debemos agregar un nuevo departamento
        if ("add".equals(action)) {

            // Recuperamos los parámetros enviados desde el formulario
            String nombre = request.getParameter("nombre");
            String descripcion = request.getParameter("descripcion");

            // Creamos un nuevo objeto Departamento con los datos proporcionados
            Departamento dep = new Departamento(0, nombre, descripcion);
            // Llamamos al metodo para agregar el departamento a la base de datos
            dao.agregarDepartamento(dep);
        }

        // Obtenemos la lista de departamentos desde la base de datos
        List<Departamento> lista = dao.listarDepartamentos();
        // Establecemos la lista de departamentos como un atributo en la solicitud para usarla en el JSP
        request.setAttribute("departamentos", lista);
        // Redirigimos la solicitud a la página index.jsp para mostrar la lista de departamentos
        request.getRequestDispatcher("index.jsp").forward(request, response);
    }

    // Metodo que maneja las solicitudes GET, generalmente para mostrar datos
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // processRequest(request, response);

        // Creamos una nueva instancia de DepartamentoDAO para obtener la lista de departamentos
        DepartamentoDAO dao = new DepartamentoDAO();
        // Obtenemos la lista de departamentos desde la base de datos
        List<Departamento> listaDepartamentos = dao.listarDepartamentos(); // Metodo que obtiene los departamentos desde la BD
        // Establecemos la lista de departamentos como un atributo en la solicitud
        request.setAttribute("departamentos", listaDepartamentos); // Enviamos la lista al JSP
        // Creamos un RequestDispatcher para redirigir la solicitud al JSP correspondiente
        RequestDispatcher dispatcher = request.getRequestDispatcher("index.jsp");
        // Redirigimos la solicitud y la respuesta al JSP para mostrar la lista de departamentos
        dispatcher.forward(request, response); // Redirige al JSP

    }

    // Metodo que maneja las solicitudes POST, generalmente para procesar formularios
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        // Obtenemos los parámetros enviados desde el formulario (nombre y descripción del departamento)
        String nombre = request.getParameter("nombreDepartamento");
        String descripcion = request.getParameter("descripcionDepartamento");

        // Validar que los datos no estén vacíos
        if (nombre != null && !nombre.isEmpty() && descripcion != null && !descripcion.isEmpty()) {
            // El ID es 0 porque es autoincremental en la base de datos y MySQL lo asignará automáticamente
            Departamento nuevoDepartamento = new Departamento(0, nombre, descripcion); // 0 porque el ID es autoincremental en BD
            // Llamamos al metodo para agregar el nuevo departamento a la base de datos
            dao.agregarDepartamento(nuevoDepartamento);
        }

        // Redirigir nuevamente al doGet para actualizar la lista
        response.sendRedirect(request.getContextPath() + "/departamento");
    }
}

