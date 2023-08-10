package historias;

import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Escuela;
import pacientes.Alumno;
import pacientes.FabricaPaciente;
import pacientes.Paciente;

public class HistoriaClinicaManagerAlumno extends HistoriaClinicaManager {

    @Override
    public Paciente crearPaciente() {
        try {
            return FabricaPaciente.crearPaciente("Alumno");
        } catch (Exception ex) {
            Logger.getLogger(HistoriaClinicaManagerAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public void setDatos(HistoriaClinica historia){
        super.setDatos(historia);
        System.out.print("Escuela Profesional: ");
        ((Alumno) historia.getPaciente()).setEscuela(Escuela.getPorId(input.nextInt()));
    }
    
}
