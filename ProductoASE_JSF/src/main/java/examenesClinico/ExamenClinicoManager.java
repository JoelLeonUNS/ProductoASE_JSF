package examenesClinico;
import examenes.Examen;
import examenes.ExamenManager;
import java.util.Scanner;

public class ExamenClinicoManager extends ExamenManager {
    private final Scanner input = new Scanner(System.in);
    
    @Override
    public Examen registrar() {
        return new ExamenClinico();
    }

    public ExamenClinico setDatos(Examen exa, String observacion, boolean apCardio, boolean apRespiratorio) {
        ExamenClinico examen = (ExamenClinico) exa;
        examen.setAPCardiovascular(apCardio);
        examen.setAPRespiratorio(apRespiratorio);
        examen.setObservacion(observacion);
        
        return examen;
    }
}
