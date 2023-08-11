package examenesMedico;
import examenes.Examen;

public class ExamenMedico extends Examen {

    private String diagnostico;
    private String tratamiento;
    private String examenesAux;

    public ExamenMedico() {
    }
    
    // solo para poblar
    public ExamenMedico(String diagnostico, String tratamiento, String examenesAux) {
        this.diagnostico = diagnostico;
        this.tratamiento = tratamiento;
        this.examenesAux = examenesAux;
    }
    
    public String getDiagnostico() {
        return diagnostico;
    }

    public void setDiagnostico(String diagnostico) {
        this.diagnostico = diagnostico;
    }

    public String getTratamiento() {
        return tratamiento;
    }

    public void setTratamiento(String tratamiento) {
        this.tratamiento = tratamiento;
    }

    public String getExamenesAux() {
        return examenesAux;
    }

    public void setExamenesAux(String examenesAux) {
        this.examenesAux = examenesAux;
    }  
}
