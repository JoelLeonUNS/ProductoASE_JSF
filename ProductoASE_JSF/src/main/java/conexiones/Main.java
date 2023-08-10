package conexiones;

public class Main {
	public static void main (String[]args) {
		SqlServerConexion.setDatos("localhost", "1433", "UnidadMedica", "sa", "halamadrid");
		SqlServerConexion.getInstance();
	}
}
