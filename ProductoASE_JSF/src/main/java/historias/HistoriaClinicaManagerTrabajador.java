package historias;

import static historias.HistoriaClinicaManager.input;
import java.util.logging.Level;
import java.util.logging.Logger;
import pacientes.FabricaPaciente;
import pacientes.Paciente;
import pacientes.Trabajador;

public class HistoriaClinicaManagerTrabajador extends HistoriaClinicaManager {

    @Override
    public Paciente crearPaciente( ) {
        try {
            return FabricaPaciente.crearPaciente("Trabajador");
        } catch (Exception ex) {
            Logger.getLogger(HistoriaClinicaManagerAlumno.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
    
    @Override
    public void setDatos(HistoriaClinica historia){
        super.setDatos(historia);
        System.out.print("Area de trabajo: ");
        ((Trabajador) historia.getPaciente()).setAreaTrabajo(input.next());
    }
    
}
