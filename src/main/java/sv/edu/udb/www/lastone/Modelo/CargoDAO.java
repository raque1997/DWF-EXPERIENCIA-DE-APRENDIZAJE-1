package sv.edu.udb.www.lastone.Modelo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.sql.Connection;

public class CargoDAO {
    private Connection con;

    public CargoDAO(Connection con) {
        this.con = con;
    }

    public CargoDAO(int idCargo, String cargo, String descripcionCargo, boolean jefatura) {
    }

    public List<CargoDAO> getAllCargos() throws SQLException {
        List<CargoDAO> lista = new ArrayList<>();
        String sql = "SELECT * FROM Cargos";
        try (PreparedStatement stmt = con.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            while (rs.next()) {
                lista.add(new CargoDAO(
                        rs.getInt("idCargo"),
                        rs.getString("cargo"),
                        rs.getString("descripcionCargo"),
                        rs.getBoolean("jefatura")
                ));
            }
        }
        return lista;
    }
}

