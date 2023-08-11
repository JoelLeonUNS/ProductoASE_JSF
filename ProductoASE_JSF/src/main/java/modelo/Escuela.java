package modelo;

public enum Escuela {
    INGENIERIA_SISTEMAS_E_INFORMATICA(1, "Ingeniería de Sistemas e Informática", "Facultad de Ingeniería"),
    INGENIERIA_CIVIL(2, "Ingeniería Civil", "Facultad de Ingeniería"),
    INGENIERIA_AGROINDUSTRIAL(3, "Ingeniería Agroindustrial", "Facultad de Ingeniería"),
    INGENIERIA_EN_ENERGIA(4, "Ingeniería En Energía", "Facultad de Ingeniería"),
    INGENIERIA_AGRONOMA(5, "Ingeniería Agrónoma", "Facultad de Ingeniería"),
    INGENIERIA_MECANICA(6, "Ingeniería Mecánica", "Facultad de Ingeniería"),
    ENFERMERIA(7, "Enfermería", "Facultad de Ciencias"),
    BIOTECNOLOGIA(8, "Biotecnología", "Facultad de Ciencias"),
    BIOLOGIA_EN_ACUICULTURA(9, "Biología En Acuicultura", "Facultad de Ciencias"),
    MEDICINA_HUMANA(10, "Medicina Humana", "Facultad de Ciencias"),
    EDUCACION_INICIAL(11, "Educación Inicial", "Facultad De Educación y Humanidades"),
    EDUCACION_PRIMARIA(12, "Educación Primaria", "Facultad De Educación y Humanidades"),
    EDUCACION_SECUNDARIA(13, "Educación Secundaria", "Facultad De Educación y Humanidades"),
    COMUNICACION_SOCIAL(14, "Comunicación Social", "Facultad De Educación y Humanidades"),
    DERECHO_Y_CIENCIAS_POLITICAS(15, "Derecho y Ciencias Políticas", "Facultad De Educación y Humanidades");

    private final int idEscuela;
    private final String nombre;
    private final String facultad;

    Escuela(int idEscuela, String nombre, String facultad) {
        this.idEscuela = idEscuela;
        this.nombre = nombre;
        this.facultad = facultad;
    }
    
    public static Escuela getPorId(int id) {
        for (Escuela escuela : Escuela.values()) {
            if (escuela.getIdEscuela() == id) {
                return escuela;
            }
        }
        return null;
    }

    public int getIdEscuela() {
        return idEscuela;
    }

    public String getNombre() {
        return nombre;
    }

    public String getFacultad() {
        return facultad;
    }
}

