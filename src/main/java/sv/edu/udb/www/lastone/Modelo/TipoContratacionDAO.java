package sv.edu.udb.www.lastone.Modelo;

import sv.edu.udb.www.lastone.conexion.DatabaseConnection;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class TipoContratacionDAO {

    public TipoContratacion getTipoContratacionById(int id) throws SQLException {
        String query = "SELECT * FROM TipoContratacion WHERE idTipoContratacion = ?";
        try (Connection con = new DatabaseConnection().getConnection();
             PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    TipoContratacion tipo = new TipoContratacion();
                    tipo.setIdTipoContratacion(rs.getInt("idTipoContratacion"));
                    tipo.setTipoContratacion(rs.getString("tipoContratacion"));
                    return tipo;
                }
            }
        }
        return null;
    }

    public boolean saveContratacion(Contratacion contratacion) {
        String sql = "INSERT INTO contrataciones (idDepartamento, idEmpleado, idCargo, idTipoContratacion, fechaContratacion, salario, estado) VALUES (?, ?, ?, ?, ?, ?, ?)";

        try (Connection connection = new DatabaseConnection().getConnection();
             PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            preparedStatement.setInt(1, contratacion.getIdDepartamento());
            preparedStatement.setInt(2, contratacion.getIdEmpleado());
            preparedStatement.setInt(3, contratacion.getIdCargo());
            preparedStatement.setInt(4, contratacion.getIdTipoContratacion());
            preparedStatement.setDate(5, new java.sql.Date(contratacion.getFechaContratacion().getTime()));
            preparedStatement.setDouble(6, contratacion.getSalario());
            preparedStatement.setBoolean(7, contratacion.isEstado());

            int rowsInserted = preparedStatement.executeUpdate();
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
}
