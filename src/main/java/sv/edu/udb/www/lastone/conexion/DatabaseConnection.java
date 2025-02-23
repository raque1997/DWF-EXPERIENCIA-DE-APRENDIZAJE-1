package sv.edu.udb.www.lastone.conexion;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

public class DatabaseConnection {
    public Connection getConnection() {
        try {
            // Obtener la referencia al DataSource a través de JNDI
            InitialContext initialContext = new InitialContext();
            DataSource dataSource = (DataSource) initialContext.lookup("jdbc/mysql/db_departamentos");

            // Obtener la conexión desde el DataSource
            return dataSource.getConnection();
        } catch (NamingException | SQLException e) {
            System.out.println("❌ Error al conectar: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}