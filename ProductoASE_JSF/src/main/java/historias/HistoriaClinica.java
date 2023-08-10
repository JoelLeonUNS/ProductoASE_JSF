package historias;

import consultas.ConsultaMedica;
import java.util.ArrayList;
import modelo.Enfermedad;
import pacientes.Paciente;

public class HistoriaClinica {
    private static int nroStaticHistoria;
    private int idHistoriaClinica;
    private Paciente paciente;
    private String otrosAntecedentesPatologicos;
    private ArrayList<Enfermedad> antecedentesPatologicos = new ArrayList<>();
    private ArrayList<ConsultaMedica> consultasMedicas = new ArrayList<>();

    public HistoriaClinica() {
    }

    // solo para los valores por defecto.
    public HistoriaClinica(Paciente paciente, String otrosAntecedentesPatologicos) {
        this.idHistoriaClinica = HistoriaClinica.masNroStaticHistoria();
        this.paciente = paciente;
        this.otrosAntecedentesPatologicos = otrosAntecedentesPatologicos;
    }

    public static int masNroStaticHistoria() {
        return ++nroStaticHistoria;
    }
    
    public static int getNroStaticHistoria() {
        return nroStaticHistoria;
    }

    public int getIdHistoriaClinica() {
        return idHistoriaClinica;
    }

    public void setIdHistoriaClinica(int numeroHistoriaClinica) {
        this.idHistoriaClinica = numeroHistoriaClinica;
    }

    public Paciente getPaciente() {
        return paciente;
    }

    public void setPaciente(Paciente paciente) {
        this.paciente = paciente;
    }

    public String getOtrosAntecedentesPatologicos() {
        return otrosAntecedentesPatologicos;
    }

    public void setOtrosAntecedentesPatologicos(String otrosAntecedentesPatologicos) {
        this.otrosAntecedentesPatologicos = otrosAntecedentesPatologicos;
    }

    public ArrayList<ConsultaMedica> getConsultasMedicas() {
        return consultasMedicas;
    }

    public void setConsultasMedicas(ArrayList<ConsultaMedica> consultasMedicas) {
        this.consultasMedicas = consultasMedicas;
    }

    public void agregarConsulta(ConsultaMedica consultaMedica) {
        consultasMedicas.add(consultaMedica);
    }

    public ArrayList<Enfermedad> getAntecedentesPatologicos() {
        return antecedentesPatologicos;
    }

    public void setAntecedentesPatologicos(ArrayList<Enfermedad> antecedentesPatologicos) {
        this.antecedentesPatologicos = antecedentesPatologicos;
    }

    public void agregarAntecedentePatologico(Enfermedad enfermedad) {
        antecedentesPatologicos.add(enfermedad);
    }

    public void agregarAntecedentesPatologicos(ArrayList<Enfermedad> enfermedades) {
        antecedentesPatologicos.addAll(enfermedades);
    }

    @Override
    public String toString() {
        return this.paciente.getDni();
    }
}
