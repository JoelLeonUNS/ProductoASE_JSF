package examenesFisico;

import examenes.Examen;
import examenes.ExamenManager;
import java.util.Scanner;

public class ExamenFisicoManager extends ExamenManager {

    private final Scanner input = new Scanner(System.in);

    @Override
    public Examen registrar() {
        return new ExamenFisico();
    }

    public ExamenFisico setDatos(Examen exa, String observacion, double temp, String PA, double FR, double SPO2, double peso, double talla, double IMC, double FC, double perAbdominal) {
        ExamenFisico examen = (ExamenFisico) exa;
        examen.setObservacion(observacion);
        examen.setTemperatura(temp);
        examen.setPA(PA);
        examen.setFR(FR);
        examen.setSPO2(SPO2);
        examen.setPeso(peso);
        examen.setTalla(talla);
        examen.setIMC(IMC);
        examen.setFC(FC);
        examen.setPerimetroAbdominal(perAbdominal);
        
        return examen;
    }

}
