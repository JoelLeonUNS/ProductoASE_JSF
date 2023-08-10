package modelo;

public enum Enfermedad {
    SARAMPION(1, "Sarampión"),
    VARICELA(2, "Varicela"),
    TIFOIDEA(3, "Tifoidea"),
    HEPATITIS(4, "Hepatitis"),
    GONORREA(5, "Gonorrea"),
    SIFILIS(6, "Sífilis"),
    TUBERCULOSIS(7, "Tuberculosis"),
    NEUMONIA(8, "Neumonía"),
    FARINGITIS(9, "Faringitis"),
    AMIGDALITIS(10, "Amigdalitis"),
    COLERA(11, "Cólera"),
    PARASITOS(12, "Parásitos"),
    ALERGIAS(13, "Alergias"),
    ANEMIA(14, "Anemia"),
    FRACTURAS(15, "Fracturas"),
    OPERACIONES(16, "Operaciones"),
    MIOPIA(17, "Miopía"),
    HIPERTENSION_ARTERIAL(18, "Hipertensión Arterial"),
    SOPLO_CARDIACO(19, "Soplo Cardiaco"),
    INSUFICIENCIA_URINARIA(20, "Insuficiencia Urinaria"),
    QUISTE_OVARIO(21, "Quiste de Ovario"),
    DESMAYOS(22, "Desmayos"),
    HONGOS_PIEL_UNA(23, "Hongos en la Piel o Uña"),
    TRANSFUSION_SANGUINEA(24, "Transfusión Sanguínea");
    
    private int idEnfermedad;
    private String nombre;

    private Enfermedad(int idEnfermedad, String nombre) {
        this.idEnfermedad = idEnfermedad;
        this.nombre = nombre;
    }
    
    public static Enfermedad getPorId(int id) {
        for (Enfermedad enfermedad : Enfermedad.values()) {
            if (enfermedad.getIdEnfermedad() == id) {
                return enfermedad;
            }
        }
        return null;
    }

    public int getIdEnfermedad() {
        return idEnfermedad;
    }

    public void setIdEnfermedad(int idEnfermedad) {
        this.idEnfermedad = idEnfermedad;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
    
    
}
