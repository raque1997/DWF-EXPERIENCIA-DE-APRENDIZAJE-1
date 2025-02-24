package sv.edu.udb.www.lastone.Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class DepartamentoDAO {

    // Metodo para listar todos los departamentos
    public List<Departamento> listarDepartamentos() {
        // Creamos una lista para almacenar los departamentos que obtendremos de la base de datos
        List<Departamento> lista = new ArrayList<>();
        // La consulta SQL que selecciona todos los departamentos de la tabla 'departamento'
        String sql = "SELECT * FROM departamento";

        // Establecemos la conexión a la base de datos y preparamos la consulta SQL
        try (Connection conn = ConexionBD.conectar(); // Abrimos la conexión a la base de datos
             PreparedStatement stmt = conn.prepareStatement(sql); // Preparamos la consulta SQL
             ResultSet rs = stmt.executeQuery()) { // Ejecutamos la consulta y obtenemos el resultado

            // Iteramos sobre el resultado obtenido para crear un objeto Departamento por cada fila
            while (rs.next()) {
                // Creamos un nuevo objeto Departamento usando los datos obtenidos del ResultSet
                Departamento dep = new Departamento(
                        rs.getInt("idDepartamento"), // Obtenemos el ID del departamento
                        rs.getString("nombreDepartamento"), // Obtenemos el nombre del departamento
                        rs.getString("descripcionDepartamento") // Obtenemos la descripción del departamento
                );
                // Añadimos el departamento a la lista
                lista.add(dep);
            }
        } catch (SQLException e) {
            // Si ocurre una excepción al ejecutar la consulta, imprimimos el error
            e.printStackTrace();
        }
        // Devolvemos la lista con todos los departamentos
        return lista;
    }

    // Metodo para agregar un nuevo departamento a la base de datos
    public void agregarDepartamento(Departamento dep) {
        // La consulta SQL para insertar un nuevo departamento en la base de datos
        String sql = "INSERT INTO departamento (nombreDepartamento, descripcionDepartamento) VALUES (?, ?)";

        // Establecemos la conexión a la base de datos y preparamos la consulta SQL
        try (Connection conn = ConexionBD.conectar(); // Abrimos la conexión a la base de datos
             PreparedStatement stmt = conn.prepareStatement(sql)) { // Preparamos la consulta SQL

            // Establecemos los parámetros para la consulta SQL usando los valores del objeto 'dep'
            stmt.setString(1, dep.getNombre()); // Asignamos el nombre del departamento
            stmt.setString(2, dep.getDescripcion()); // Asignamos la descripción del departamento

            // Ejecutamos la consulta para insertar el nuevo departamento
            stmt.executeUpdate();
        } catch (SQLException e) {
            // Si ocurre una excepción al ejecutar la inserción, imprimimos el error
            e.printStackTrace();
        }
    }
}

