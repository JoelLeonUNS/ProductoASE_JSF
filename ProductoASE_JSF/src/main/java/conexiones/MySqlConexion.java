package conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class MySqlConexion {
    
    private static Connection conector;
    private static String url;
    private static String nombreServidor;
    private static String numeroPuerto;
    private static String nombreBaseDato;
    private static String nombreUsuario;
    private static String clave;
    private static String conexion;

    public static void setDatos(String nombreServidor, String numeroPuerto, String nombreBaseDato, String nombreUsuario, String clave) {
        MySqlConexion.url = "jdbc:mysql://";
        MySqlConexion.nombreServidor = nombreServidor;
        MySqlConexion.numeroPuerto = numeroPuerto;
        MySqlConexion.nombreBaseDato = nombreBaseDato;
        MySqlConexion.nombreUsuario = nombreUsuario;
        MySqlConexion.clave = clave;
    }
    
    private static void conectar() {
        conexion = url + nombreServidor + ":" + numeroPuerto + ";" + "databaseName=" + nombreBaseDato;
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
    
    public static void desconectar() throws SQLException  {
	conector.close();
    }
}
