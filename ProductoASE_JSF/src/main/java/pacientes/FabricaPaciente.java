package pacientes;

public class FabricaPaciente {
    
    public static Paciente crearPaciente (String tipo) throws Exception{
        Paciente paciente = null;
        switch(tipo){
            case "Alumno" -> {
                paciente = new Alumno();
            }
            case "Trabajador" -> {
                paciente = new Trabajador();
            }
        }
        return paciente;
    }
}
