package pacientes;

import modelo.Escuela;

public class Alumno extends Paciente {

    private Escuela escuela;

    public Alumno() {
    }
    
    public Escuela getEscuela() {
        return escuela;
    }

    public void setEscuela(Escuela escuela) {
        this.escuela = escuela;
    }
    
    @Override
    public String toString() {
        return super.toString() + "\nEscuela: " + escuela;
    }
}
