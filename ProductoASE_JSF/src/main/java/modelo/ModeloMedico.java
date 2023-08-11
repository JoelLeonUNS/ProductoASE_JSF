
package modelo;

import factoryDAO.DAOFactory;
import factoryDAO.SqlServerDAOFactory;
import java.util.ArrayList;
import java.util.List;
import medicos.Medico;
import medicos.Usuario;

public class ModeloMedico {
    private DAOFactory dao;
    private Medico medico;
    private int idMedico;
    private ArrayList<Integer> idMedicos;

    public ModeloMedico() {
        this.dao = new SqlServerDAOFactory();
        this.medico = new Medico();
        medico.setUsuario(new Usuario());
    }
    
    public void setDatosMedico(String dni, String nombre, String apellidos, String telefono){
        medico.setDNI(dni);
        medico.setNombreMedico(nombre);
        medico.setApellidoMedico(apellidos);
        medico.setTelefonoMedico(telefono);
    }
    
    public void setDatosUsuario(String usuario, String clave){
        medico.getUsuario().setUsuario(usuario);
        medico.getUsuario().setClave(clave);
        medico.getUsuario().setEstado(true);
        medico.getUsuario().setRol("Medico");
    }

    public void setMedico(Medico medico) {
        this.medico = medico;
    }
 
    public Medico buscarMedicoDNI(String dni){
        for(Medico medicoBD: (List<Medico>)dao.getMedico().listed()) {
            if (medicoBD.getDNI().equals(dni)) {
                idMedico = medicoBD.getIdMedico();
                return medicoBD;
            }
        }
        return null;
    }
    
    public void buscarMedicoCoincidente(String cadena) {
        idMedicos = new ArrayList<>();
        for(Medico medicoBD: (List<Medico>)dao.getMedico().listed()) {
            if (medicoBD.getNombreMedico().toUpperCase().contains(cadena.toUpperCase()) || medicoBD.getApellidoMedico().toUpperCase().contains(cadena.toUpperCase())) {
                idMedicos.add(medicoBD.getIdMedico());
            }
        }
    }
    
    public ArrayList<Medico> getMedicosCoincidentesBD() {
        ArrayList<Medico> medicos = new ArrayList<>();
        for (Integer idMed : idMedicos) {
            medicos.add((Medico)dao.getMedico().read(idMed));
        }
        return medicos;
    }
    
    public void registrar(){
        try {
            dao.getUsuario().create((Usuario)medico.getUsuario());
            dao.getMedico().create((Medico)medico);
        } catch (Exception e) {}
        
    }
    
    public void editar(){
        medico.setIdMedico(idMedico);
        medico.getUsuario().setIdUsuario(getMedicoBD().getUsuario().getIdUsuario());
        dao.getMedico().update(medico);
        dao.getUsuario().update(medico.getUsuario());
    }
    
    public void darDeBaja(){
        medico = getMedicoBD();
        medico.getUsuario().setEstado(false);
        dao.getUsuario().update(medico.getUsuario());
    }
    
    public Medico getMedicoBD(){
        return idMedico!=-1 ? (Medico)dao.getMedico().read(idMedico) : null;
    }
}
