package factoryDAO;

import DAO.ConsultaDAO;
import DAO.ExamenClinicoDAO;
import DAO.ExamenFisicoDAO;
import DAO.ExamenMedicoDAO;
import DAO.FamiliarDAO;
import DAO.HistoriaClinicaDAO;
import DAO.HistoriaClinicaEnfermedadDAO;
import DAO.MedicoDAO;
import DAO.PacienteDAO;
import DAO.UsuarioDAO;
import conexiones.MySqlConexion;
import mySqlDAO.MySqlConsultaDAO;
import mySqlDAO.MySqlExamenClinicoDAO;
import mySqlDAO.MySqlExamenFisicoDAO;
import mySqlDAO.MySqlExamenMedicoDAO;
import mySqlDAO.MySqlFamiliarDAO;
import mySqlDAO.MySqlHistoriaClinicaDAO;
import mySqlDAO.MySqlHistoriaClinicaEnfermedadDAO;
import mySqlDAO.MySqlMedicoDAO;
import mySqlDAO.MySqlPacienteDAO;
import mySqlDAO.MySqlUsuarioDAO;

public class MySqlDAOFactory extends DAOFactory {

    @Override
    public UsuarioDAO getUsuario() {
        MySqlUsuarioDAO usuarioDAO = new MySqlUsuarioDAO();
        usuarioDAO.setConector(MySqlConexion.getInstance());
        return usuarioDAO;
    }

    @Override
    public MedicoDAO getMedico() {
        MySqlMedicoDAO medicoDAO = new MySqlMedicoDAO();
        medicoDAO.setConector(MySqlConexion.getInstance());
        return medicoDAO;
    }

    @Override
    public PacienteDAO getPaciente() {
        MySqlPacienteDAO pacienteDAO = new MySqlPacienteDAO();
        pacienteDAO.setConector(MySqlConexion.getInstance());
        return pacienteDAO;
    }

    @Override
    public FamiliarDAO getFamiliar() {
        MySqlFamiliarDAO familiarDAO = new MySqlFamiliarDAO();
        familiarDAO.setConector(MySqlConexion.getInstance());
        return familiarDAO;
    }
    
    @Override
    public HistoriaClinicaDAO getHistoriaClinica() {
        MySqlHistoriaClinicaDAO historiaClinicaDAO = new MySqlHistoriaClinicaDAO();
        historiaClinicaDAO.setConector(MySqlConexion.getInstance());
        return historiaClinicaDAO;
    }
    
    @Override
    public HistoriaClinicaEnfermedadDAO getHistoriaClinicaEnfermedad() {
        MySqlHistoriaClinicaEnfermedadDAO historiaClinicaEnfermedadDAO = new MySqlHistoriaClinicaEnfermedadDAO();
        historiaClinicaEnfermedadDAO.setConector(MySqlConexion.getInstance());
        return historiaClinicaEnfermedadDAO;
    }

    @Override
    public ConsultaDAO getConsulta() {
        MySqlConsultaDAO consultaDAO = new MySqlConsultaDAO();
        consultaDAO.setConector(MySqlConexion.getInstance());
        return consultaDAO;
    }

    @Override
    public ExamenFisicoDAO getExamenFisico() {
        MySqlExamenFisicoDAO examenFisicoDAO = new MySqlExamenFisicoDAO();
        examenFisicoDAO.setConector(MySqlConexion.getInstance());
        return examenFisicoDAO;
    }

    @Override
    public ExamenMedicoDAO getExamenMedico() {
        MySqlExamenMedicoDAO examenMedicoDAO = new MySqlExamenMedicoDAO();
        examenMedicoDAO.setConector(MySqlConexion.getInstance());
        return examenMedicoDAO;
    }

    @Override
    public ExamenClinicoDAO getExamenClinico() {
        MySqlExamenClinicoDAO examenClinicoDAO = new MySqlExamenClinicoDAO();
        examenClinicoDAO.setConector(MySqlConexion.getInstance());
        return examenClinicoDAO;
    }    
}
