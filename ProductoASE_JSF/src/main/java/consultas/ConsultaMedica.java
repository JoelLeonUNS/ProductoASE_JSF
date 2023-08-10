package consultas;

import examenes.Examen;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import medicos.Medico;

public class ConsultaMedica {

    private int idConsulta;
    private int idHistoria;
    private LocalDate fecha;
    private LocalTime hora;
    private int edad;
    private String motivo;
    private String tiempoEnfermedad;
    private String apetito;
    private String sueño;
    private String sed;
    private String estadoAnimo;
    private Medico atendidoPor;
    private ArrayList<Examen> examenes = new ArrayList<>();

    public ConsultaMedica() {
        this.atendidoPor = new Medico();
    }
    
    // solo para los valores por defecto.
    public ConsultaMedica(LocalDate fecha, LocalTime hora, int edad, String motivo, String tiempoEnfermedad, String apetito, String sueño, String sed, String estadoAnimo, Medico atendidoPor) {
        this.fecha = fecha;
        this.hora = hora;
        this.edad = edad;
        this.motivo = motivo;
        this.tiempoEnfermedad = tiempoEnfermedad;
        this.apetito = apetito;
        this.sueño = sueño;
        this.sed = sed;
        this.estadoAnimo = estadoAnimo;
        this.atendidoPor = atendidoPor;
    }

    public LocalDate getFecha() {
        return fecha;
    }

    public void setFecha(LocalDate fecha) {
        this.fecha = fecha;
    }

    public LocalTime getHora() {
        return hora;
    }

    public void setHora(LocalTime hora) {
        this.hora = hora;
    }

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getMotivo() {
        return motivo;
    }

    public void setMotivo(String motivo) {
        this.motivo = motivo;
    }

    public String getTiempoEnfermedad() {
        return tiempoEnfermedad;
    }

    public void setTiempoEnfermedad(String tiempoEnfermedad) {
        this.tiempoEnfermedad = tiempoEnfermedad;
    }

    public String getApetito() {
        return apetito;
    }

    public void setApetito(String apetito) {
        this.apetito = apetito;
    }

    public String getSueño() {
        return sueño;
    }

    public void setSueño(String sueño) {
        this.sueño = sueño;
    }

    public String getSed() {
        return sed;
    }

    public void setSed(String sed) {
        this.sed = sed;
    }

    public String getEstadoAnimo() {
        return estadoAnimo;
    }

    public void setEstadoAnimo(String estadoAnimo) {
        this.estadoAnimo = estadoAnimo;
    }

    public Medico getAtendidoPor() {
        return atendidoPor;
    }

    public void setAtendidoPor(Medico atendidoPor) {
        this.atendidoPor = atendidoPor;
    }

    public ArrayList<Examen> getExamenes() {
        return examenes;
    }

    public void setExamenes(ArrayList<Examen> examenes) {
        this.examenes = examenes;
    }
    

    public void agregarExamen(Examen examen) {
        examenes.add(examen);
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }

    public int getIdHistoria() {
        return idHistoria;
    }

    public void setIdHistoria(int idHistoria) {
        this.idHistoria = idHistoria;
    }    

    @Override
    public String toString() {
        return "\nFecha: " + fecha + "\nHora: " + hora + "\nEdad: " + edad + "\nMotivo: " + motivo + "\nTiempo Enfermedad: " + tiempoEnfermedad + "\nApetito: " + apetito + "\nSue\u00f1o: " + sueño + "\nSed=" + sed + "\nEstado Animo: " + estadoAnimo + "\nNro. Examenes: " + examenes.size();
    }

    
}
