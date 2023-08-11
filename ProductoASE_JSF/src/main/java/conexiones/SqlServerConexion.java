package conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class SqlServerConexion {

    private static Connection conector;
    private static String url;
    private static String nombreServidor;
    private static String numeroPuerto;
    private static String nombreBaseDato;
    private static String nombreUsuario;
    private static String clave;
    private static String conexion;

    public static void setDatos(String nombreServidor, String numeroPuerto, String nombreBaseDato, String nombreUsuario, String clave) {
        SqlServerConexion.url = "jdbc:sqlserver://";
        SqlServerConexion.nombreServidor = nombreServidor;
        SqlServerConexion.numeroPuerto = numeroPuerto;
        SqlServerConexion.nombreBaseDato = nombreBaseDato;
        SqlServerConexion.nombreUsuario = nombreUsuario;
        SqlServerConexion.clave = clave;
    }

    private static void conectar() {
        conexion = url + nombreServidor + ":" + numeroPuerto + ";" + "databaseName=" + nombreBaseDato;
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
            JOptionPane.showMessageDialog(null, "Driver no encontrado: " + e.getMessage(), "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
        try {
            conector = DriverManager.getConnection(conexion, nombreUsuario, clave);
            conector.setAutoCommit(false);
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error de nombre de usuario y/o clave: " + e.getMessage(), "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
    }

    public static Connection getInstance() {
        if (conector == null) {
            conectar();
        }
        return conector;
    }

    public static void desconectar() throws SQLException {
        conector.close();
    }

}
