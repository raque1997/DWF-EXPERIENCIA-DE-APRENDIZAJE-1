package sv.edu.udb.www.lastone.conexion;


import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DatabaseTest {
    public static void main(String[] args) {
        DatabaseConnection dbConnection = new DatabaseConnection();
        Connection conn = dbConnection.getConnection();

        if (conn != null) {
            System.out.println("✅ Conexión exitosa a la base de datos.");
            try {
                // Crear una consulta a la base de datos
                Statement stmt = conn.createStatement();
                ResultSet rs = stmt.executeQuery("SELECT * FROM departamento"); // Cambia 'departamentos' si es necesario


                // Verificar si hay resultados
                if (!rs.isBeforeFirst()) {
                    System.out.println("⚠️ No se encontraron datos en la tabla 'departamento'.");
                } else {
                    // Imprimir los resultados
                    System.out.println("📋 Datos de la tabla 'departamento':");
                    while (rs.next()) {
                        System.out.println("ID: " + rs.getInt("id") + ", Nombre: " + rs.getString("nombre"));
                    }
                }

                // Cerrar la conexión
                rs.close();
                stmt.close();
                conn.close();
            } catch (SQLException e) {
                System.out.println("❌ Error al ejecutar la consulta.");
                e.printStackTrace();
            }
        } else {
            System.out.println("❌ Error en la conexión a la base de datos.");
        }
    }
}

/*
package sv.edu.udb.www.lastone.conexion;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;

public class DatabaseTest {
    public Connection getConnection() {
        Connection conn = null;
        try {
            Hashtable<String, String> env = new Hashtable<>();
            env.put(Context.INITIAL_CONTEXT_FACTORY, "com.sun.enterprise.naming.SerialInitContextFactory");
            env.put(Context.URL_PKG_PREFIXES, "com.sun.enterprise.naming");
            env.put(Context.STATE_FACTORIES, "com.sun.corba.ee.impl.presentation.rmi.JNDIStateFactoryImpl");

            Context ctx = new InitialContext(env);
            DataSource ds = (DataSource) ctx.lookup("jdbc/mysql/db_departamentos");
            conn = ds.getConnection();
        } catch (NamingException | SQLException e) {
            System.out.println("❌ Error al conectar: " + e.getMessage());
            e.printStackTrace();
        }
        return conn;
    }
}*/