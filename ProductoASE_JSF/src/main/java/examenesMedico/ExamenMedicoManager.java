package examenesMedico;

import examenes.Examen;
import examenes.ExamenManager;

public class ExamenMedicoManager extends ExamenManager {
    
    @Override
    public Examen registrar() {
        return new ExamenMedico();
    }

    public ExamenMedico setDatos(Examen exa, String diagnostico, String tratamiento, String examAux, String observacion) {
        ExamenMedico examen = (ExamenMedico) exa;
        examen.setDiagnostico(diagnostico);
        examen.setTratamiento(tratamiento);
        examen.setExamenesAux(examAux);
        examen.setObservacion(observacion);
        
        return examen;
    }
    
}
