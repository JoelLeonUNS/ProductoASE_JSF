
package modelo;

import examenes.Examen;
import examenes.ExamenManager;
import examenesClinico.ExamenClinico;
import examenesClinico.ExamenClinicoManager;
import examenesFisico.ExamenFisico;
import examenesFisico.ExamenFisicoManager;
import examenesMedico.ExamenMedico;
import examenesMedico.ExamenMedicoManager;
import factoryDAO.DAOFactory;
import factoryDAO.SqlServerDAOFactory;
import java.util.ArrayList;
import java.util.List;


public class ModeloExamen {
    private DAOFactory dao;
    private ExamenManager examenManager;
    private Examen examen;
    private int idExamen;
    private String examenTmp;
    private ExamenMedico examenMedicoTmp;
    private ExamenFisico examenFisicoTmp;
    private ExamenClinico examenClinicoTmp;

    public ModeloExamen() {
        this.dao = new SqlServerDAOFactory();
    }

    public Examen getExamen() {
        return examen;
    }

    public void setExamen(Examen examen) {
        this.examen = examen;
    }    

    public String getExamenTmp() {
        return examenTmp;
    }

    public void setExamenTmp(String examenTmp) {
        this.examenTmp = examenTmp;
    }

    public ExamenMedico getExamenMedicoTmp() {
        return examenMedicoTmp;
    }

    public void setExamenMedicoTmp(ExamenMedico examenMedicoTmp) {
        this.examenMedicoTmp = examenMedicoTmp;
    }

    public ExamenFisico getExamenFisicoTmp() {
        return examenFisicoTmp;
    }

    public void setExamenFisicoTmp(ExamenFisico examenFisicoTmp) {
        this.examenFisicoTmp = examenFisicoTmp;
    }

    public ExamenClinico getExamenClinicoTmp() {
        return examenClinicoTmp;
    }

    public void setExamenClinicoTmp(ExamenClinico examenClinicoTmp) {
        this.examenClinicoTmp = examenClinicoTmp;
    }
    
    
    public void setTipoExamen(String tipoExamen) {
        ExamenManager examenManager = null;
        
        switch (tipoExamen) {
            case "MEDICO": {
                examenManager = new ExamenMedicoManager();
                break;
                }
            case "FISICO": {
                examenManager = new ExamenFisicoManager();
                break; 
                }
            case "CLINICO": {
                examenManager = new ExamenClinicoManager();
                break;
                }
        }
        
        if (examenManager != null) {
            this.examen = examenManager.registrar();
        }
    }

    
    public void setDatosExamenMedico(String diagnostico, String tratamiento, String examAux, String observacion){
        this.examen = ((ExamenMedicoManager) examenManager).setDatos(examen, diagnostico, tratamiento, examAux, observacion);
    }
    
    public void setDatosExamenClinico(String observacion, boolean apCardio, boolean apRespiratorio){
        this.examen = ((ExamenClinicoManager) examenManager).setDatos(examen, observacion, apCardio, apRespiratorio);
    }
    
    public void setDatosExamenFisico(String observacion, double temp, String PA, double FR, double SPO2, double peso, double talla, double IMC, double FC, double perAbdominal){
        this. examen = ((ExamenFisicoManager) examenManager).setDatos(examen, observacion, temp, PA, FR, SPO2, peso, talla, IMC, FC, perAbdominal);
    }
    
    public void getTipoExamen (Examen examen){
        if(examen instanceof ExamenMedico){
            examenMedicoTmp = (ExamenMedico) examen;
            examenTmp = "MEDICO";
        }else if(examen instanceof ExamenFisico){
            examenFisicoTmp = (ExamenFisico) examen;
            examenTmp = "FISICO";
        }else if(examen instanceof ExamenClinico){
            examenClinicoTmp = (ExamenClinico) examen;
            examenTmp = "CLINICO";
        }
    }
    
    public void añadirExamen(Examen exa){
        getTipoExamen (exa);
        switch(examenTmp){
            case "MEDICO": {
                dao.getExamenMedico().create(exa);
                break;
            }
            case "FISICO":{
                dao.getExamenFisico().create(exa);
                break;
            }
            case "CLINICO":{
                dao.getExamenClinico().create(exa);
                break;
            }       
        }
    }
    
    public ArrayList<Examen> obtenerExamenes(int id){
        ArrayList<Examen> examenes = new ArrayList();
        for (ExamenMedico examenBD : (List<ExamenMedico>)dao.getExamenMedico().listed()) {
            if(examenBD.getIdConsulta()==id){
                examenes.add(examenBD);
            }
        }
        
        for (ExamenFisico examenBD : (List<ExamenFisico>)dao.getExamenFisico().listed()) {
            if(examenBD.getIdConsulta()==id){
                examenes.add(examenBD);
            }
        }
        
        for (ExamenClinico examenBD : (List<ExamenClinico>)dao.getExamenClinico().listed()) {
            if(examenBD.getIdConsulta()==id){
                examenes.add(examenBD);
            }
        }
        
        return examenes;
    }
    
}
