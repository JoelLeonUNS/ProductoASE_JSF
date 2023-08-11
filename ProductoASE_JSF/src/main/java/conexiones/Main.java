package conexiones;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Main {
	public static void main (String[]args) throws SQLException {
		SqlServerConexion.setDatos("localhost", "1433", "UnidadMedica", "sa", "castillo");
		Connection conexion = SqlServerConexion.getInstance();
		
		if(conexion!=null) {
			System.out.println("Conectando...");
			Statement stm = conexion.createStatement();
			ResultSet rs = stm.executeQuery("select * from Usuario");
			while(rs.next()) {
				System.out.println("usuario: "+rs.getString(2)+" clave: "+rs.getString(3));
			}
		}else {
			System.out.println("No se ha conectado.");
		}
	}
}
