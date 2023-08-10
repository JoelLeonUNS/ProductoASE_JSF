package factoryDAO;

import DAO.HistoriaClinicaDAO;
import DAO.UsuarioDAO;
import DAO.MedicoDAO;
import DAO.ConsultaDAO;
import DAO.ExamenClinicoDAO;
import DAO.ExamenFisicoDAO;
import DAO.ExamenMedicoDAO;
import DAO.FamiliarDAO;
import DAO.HistoriaClinicaEnfermedadDAO;
import DAO.PacienteDAO;

public abstract class DAOFactory {
    public abstract MedicoDAO getMedico();
    public abstract UsuarioDAO getUsuario();
    public abstract PacienteDAO getPaciente();
    public abstract FamiliarDAO getFamiliar();
    public abstract HistoriaClinicaDAO getHistoriaClinica();
    public abstract HistoriaClinicaEnfermedadDAO getHistoriaClinicaEnfermedad();
    public abstract ConsultaDAO getConsulta();
    public abstract ExamenFisicoDAO getExamenFisico();
    public abstract ExamenMedicoDAO getExamenMedico();
    public abstract ExamenClinicoDAO getExamenClinico();
}
