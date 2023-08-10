package medicos;

public class Medico {
    private int idMedico;
    private String apellidoMedico;
    private String nombreMedico;
    private String telefonoMedico;
    private String DNI;
    private Usuario usuario;

    public Medico() {
        usuario = new Usuario();
    }

    public Medico(String apellidoMedico, String nombreMedico, String telefonoMedico, String DNI, Usuario usuario) {
        this.apellidoMedico = apellidoMedico;
        this.nombreMedico = nombreMedico;
        this.telefonoMedico = telefonoMedico;
        this.DNI = DNI;
        this.usuario = usuario;
    }

    public int getIdMedico() {
        return idMedico;
    }

    public void setIdMedico(int idMedico) {
        this.idMedico = idMedico;
    }

    public String getApellidoMedico() {
        return apellidoMedico;
    }

    public void setApellidoMedico(String apellidoMedico) {
        this.apellidoMedico = apellidoMedico;
    }

    public String getNombreMedico() {
        return nombreMedico;
    }

    public void setNombreMedico(String nombreMedico) {
        this.nombreMedico = nombreMedico;
    }

    public String getTelefonoMedico() {
        return telefonoMedico;
    }

    public void setTelefonoMedico(String telefonoMedico) {
        this.telefonoMedico = telefonoMedico;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public String toString() {
        return DNI;
    }
    
    
    
}
