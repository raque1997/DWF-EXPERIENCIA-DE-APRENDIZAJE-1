package sv.edu.udb.www.lastone.conexion;

import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Hashtable;

public class DatabaseConnection {
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
}


/*package sv.edu.udb.www.lastone.conexion;

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
*/
