package conexiones;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

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
        conexion = url + nombreServidor + ":" + numeroPuerto + ";" + "databaseName=" + nombreBaseDato + ";trustServerCertificate=true;";
        try {
            Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        } catch (ClassNotFoundException e) {
        	System.out.println("Driver no encontrado: " + e.getMessage()+" Error de conexión");
        }
        try {
            conector = DriverManager.getConnection(conexion, nombreUsuario, clave);
            conector.setAutoCommit(false);
            System.out.print("Conexión realizada");
        } catch (SQLException e) {
        	System.out.println("Error de nombre de usuario y/o clave: " + e.getMessage());
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
