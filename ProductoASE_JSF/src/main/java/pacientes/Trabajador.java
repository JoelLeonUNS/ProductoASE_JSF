package pacientes;

public class Trabajador extends Paciente {

    private String areaTrabajo;
    private boolean docente;

    public Trabajador() {
    }

    public String getAreaTrabajo() {
        return areaTrabajo;
    }

    public void setAreaTrabajo(String areaTrabajo) {
        this.areaTrabajo = areaTrabajo;
    }

    public boolean isDocente() {
        return docente;
    }

    public void setDocente(boolean docente) {
        this.docente = docente;
    }
    
    @Override
    public String toString() {
        return super.toString() + "\nArea de Trabajo: " + areaTrabajo + "\nDocente: " + docente;
    }
}
