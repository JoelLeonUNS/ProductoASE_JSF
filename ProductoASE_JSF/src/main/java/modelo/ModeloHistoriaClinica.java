package modelo;

import factoryDAO.DAOFactory;
import factoryDAO.SqlServerDAOFactory;
import historias.HistoriaClinica;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import pacientes.Alumno;
import pacientes.Familiar;
import pacientes.Paciente;
import pacientes.Trabajador;

public class ModeloHistoriaClinica {

    private final DAOFactory dao;
    private HistoriaClinica historiaClinica;
    private int incIdFamiliar;
    private ArrayList<Integer> idHistorias;

    public ModeloHistoriaClinica() {
        resetModelo("Paciente");
        this.dao = new SqlServerDAOFactory(); // o MySql
    }

    public final void resetModelo(String tipoPaciente) {
        this.historiaClinica = new HistoriaClinica();
        switch (tipoPaciente) {
            case "Alumno" -> {
                this.historiaClinica.setPaciente(new Alumno());
            }
            case "Trabajador" -> {
                this.historiaClinica.setPaciente(new Trabajador());
            }
            case "Paciente" -> {
                this.historiaClinica.setPaciente(new Paciente());
            }
        }
    }

    public HistoriaClinica getHistoriaClinica() {
        return historiaClinica;
    }

    public void setHistoriaClinica(HistoriaClinica historiaClinica) {
        this.historiaClinica = historiaClinica;
    }

    public HistoriaClinica buscarHistoriaClinicaDNI(String dni) {
        for (HistoriaClinica historia : (List<HistoriaClinica>) dao.getHistoriaClinica().listed()) {
            if (historia.getPaciente().getDni().equals(dni)) {
                return historia;
            }
        }
        return null;
    }

    public void buscarHistoriaCoincidente(String cadena) {
        idHistorias = new ArrayList<>();
        for (HistoriaClinica historia : (List<HistoriaClinica>) dao.getHistoriaClinica().listed()) {
            if (historia.getPaciente().getNombre().toUpperCase().contains(cadena.toUpperCase()) || historia.getPaciente().getApellido().toUpperCase().contains(cadena.toUpperCase())) {
                idHistorias.add(historia.getIdHistoriaClinica());
            }
        }
    }

    public ArrayList<HistoriaClinica> getHistoriasCoincidentes() {
        ArrayList<HistoriaClinica> historiaClinicas = new ArrayList<>();
        for (Integer idHistoria : idHistorias) {
            historiaClinicas.add((HistoriaClinica) dao.getHistoriaClinica().read(idHistoria));
        }
        return historiaClinicas;
    }

    public int nextIdHistoriaClinica() {
        return dao.getHistoriaClinica().lastId() + 1;
    }

    public int nextIdPaciente() {
        return dao.getPaciente().lastId() + 1;
    }
    
    public int nextIdFamiliar() {
        incIdFamiliar++;
        return dao.getFamiliar().lastId() + incIdFamiliar;
    }

    public void resetIncIdFamiliar() {
        this.incIdFamiliar = 0;
    }

    public void registrarHistoriaClinica() {
        historiaClinica.setIdHistoriaClinica(nextIdHistoriaClinica());
        historiaClinica.getPaciente().setIdPaciente(nextIdPaciente());
        dao.getHistoriaClinica().create(historiaClinica);

        for (Map.Entry<Integer, Familiar> familiar : historiaClinica.getPaciente().getFamiliares().entrySet()) {
            familiar.getValue().setIdPaciente(historiaClinica.getPaciente().getIdPaciente());
            dao.getFamiliar().create(familiar.getValue());
        }
        for (Enfermedad enfermedad : historiaClinica.getAntecedentesPatologicos()) {
            dao.getHistoriaClinicaEnfermedad().create(historiaClinica.getIdHistoriaClinica(), enfermedad);
        }
    }

    public void editarHistoriaClinica() {
        dao.getHistoriaClinica().update(historiaClinica);
        for (Map.Entry<Integer, Familiar> familiar : historiaClinica.getPaciente().getFamiliares().entrySet()) {
            if (dao.getFamiliar().read(familiar.getValue().getIdFamiliar()) != null) {
                dao.getFamiliar().update(familiar.getValue());
            } else {
                dao.getFamiliar().create(familiar.getValue());
            }    
        }
        dao.getHistoriaClinicaEnfermedad().deleteAll(historiaClinica.getIdHistoriaClinica());
        for (Enfermedad enfermedad : historiaClinica.getAntecedentesPatologicos()) {
            dao.getHistoriaClinicaEnfermedad().create(historiaClinica.getIdHistoriaClinica(), enfermedad);
        }
    }

    public void agregarFamiliar(Familiar familiar) {
        Integer indiceFamiliarActualizado = null;
        int idFamiliarActualizado = 0;
        for (Map.Entry<Integer, Familiar> familiarExiste : historiaClinica.getPaciente().getFamiliares().entrySet()) {
            if (familiarExiste.getValue().getParentesco().equalsIgnoreCase(familiar.getParentesco())) {
                indiceFamiliarActualizado = familiarExiste.getKey();
                idFamiliarActualizado = familiarExiste.getValue().getIdFamiliar();
                break;
            }
        }
        if (indiceFamiliarActualizado != null) {
            familiar.setIdFamiliar(idFamiliarActualizado);
            historiaClinica.getPaciente().agregarFamiliar(indiceFamiliarActualizado, familiar); // actualizar o agregar
        } else {
            familiar.setIdFamiliar(nextIdFamiliar());
            historiaClinica.getPaciente().agregarFamiliar(historiaClinica.getPaciente().cantidadFamiliares() + 1, familiar);
        }
    }

    public Familiar getParentescoFamiliar(String parentesco) {
        for (Map.Entry<Integer, Familiar> familiarExiste : historiaClinica.getPaciente().getFamiliares().entrySet()) {
            if (familiarExiste.getValue().getParentesco().equalsIgnoreCase(parentesco)) {
                return familiarExiste.getValue();
            }
        }
        return null;
    }
}
