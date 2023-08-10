package pacientes;

import java.time.LocalDate;
import java.time.Period;
import java.util.HashMap;
import java.util.Map;

public class Paciente {
    private int idPaciente;
    private String dni;
    private String nombre;
    private String apellido;
    private String sexo;
    private LocalDate fechaNac;
    private String lugarNac;
    private String distrito;
    private String departamento;
    private String direccion;
    private String telefono;
    private String estadoCivil;
    private String tipoPaciente;
    private Map<Integer, Familiar> familiares = new HashMap<>();

    public Paciente() {
    }

    public int getIdPaciente() {
        return idPaciente;
    }

    public void setIdPaciente(int idPaciente) {
        this.idPaciente = idPaciente;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }

    public LocalDate getFechaNac() {
        return fechaNac;
    }

    public void setFechaNac(LocalDate fechaNac) {
        this.fechaNac = fechaNac;
    }

    public String getLugarNac() {
        return lugarNac;
    }

    public void setLugarNac(String lugarNac) {
        this.lugarNac = lugarNac;
    }

    public String getDistrito() {
        return distrito;
    }

    public void setDistrito(String distrito) {
        this.distrito = distrito;
    }

    public String getDepartamento() {
        return departamento;
    }

    public void setDepartamento(String departamento) {
        this.departamento = departamento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEstadoCivil() {
        return estadoCivil;
    }

    public void setEstadoCivil(String estadoCivil) {
        this.estadoCivil = estadoCivil;
    }
    
    public Map<Integer, Familiar> getFamiliares() {
        return familiares;
    }
    
    public void setFamiliares(Map<Integer, Familiar> familiares) {
        this.familiares = familiares;
    }
    
    public void agregarFamiliar(int id, Familiar familiar) {
        familiares.put(id, familiar);
    }
    
    public Integer cantidadFamiliares() {
        return familiares.size();
    }

    public String getTipoPaciente() {
        return tipoPaciente;
    }

    public void setTipoPaciente(String tipoPaciente) {
        this.tipoPaciente = tipoPaciente;
    }
    
    @Override
    public String toString() {
        return "\nDNI: " + dni + "\nNombre: " + nombre + "\nApellido: " + apellido + "\nSexo: " + sexo + "\nFecha de Nac.: " + fechaNac + "\nLugar de Nac.: " + lugarNac + "\nDistrito: " + distrito + "\nDepartamento: " + departamento + "\nDireccion: " + direccion + "\nTelefono: " + telefono + "\nEstado Civil: " + estadoCivil + "\nNombre Familiar: ";
    }

    public int calcularEdad() {
        LocalDate fechaActual = LocalDate.now();
        Period periodo = Period.between(this.fechaNac, fechaActual);
        return periodo.getYears();
    }
}
