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
import conexiones.SqlServerConexion;
import sqlServerDAO.SqlServerConsultaDAO;
import sqlServerDAO.SqlServerExamenClinicoDAO;
import sqlServerDAO.SqlServerExamenFisicoDAO;
import sqlServerDAO.SqlServerExamenMedicoDAO;
import sqlServerDAO.SqlServerFamiliarDAO;
import sqlServerDAO.SqlServerHistoriaClinicaDAO;
import sqlServerDAO.SqlServerHistoriaClinicaEnfermedadDAO;
import sqlServerDAO.SqlServerMedicoDAO;
import sqlServerDAO.SqlServerPacienteDAO;
import sqlServerDAO.SqlServerUsuarioDAO;

public class SqlServerDAOFactory extends DAOFactory {

    @Override
    public UsuarioDAO getUsuario() {
        SqlServerUsuarioDAO usuarioDAO = new SqlServerUsuarioDAO();
        usuarioDAO.setConector(SqlServerConexion.getInstance());
        return usuarioDAO;
    }

    @Override
    public MedicoDAO getMedico() {
        SqlServerMedicoDAO medicoDAO = new SqlServerMedicoDAO();
        medicoDAO.setConector(SqlServerConexion.getInstance());
        return medicoDAO;
    }

    @Override
    public PacienteDAO getPaciente() {
        SqlServerPacienteDAO pacienteDAO = new SqlServerPacienteDAO();
        pacienteDAO.setConector(SqlServerConexion.getInstance());
        return pacienteDAO;
    }

    @Override
    public FamiliarDAO getFamiliar() {
        SqlServerFamiliarDAO familiarDAO = new SqlServerFamiliarDAO();
        familiarDAO.setConector(SqlServerConexion.getInstance());
        return familiarDAO;
    }
    
    @Override
    public HistoriaClinicaDAO getHistoriaClinica() {
        SqlServerHistoriaClinicaDAO historiaClinicaDAO = new SqlServerHistoriaClinicaDAO();
        historiaClinicaDAO.setConector(SqlServerConexion.getInstance());
        return historiaClinicaDAO;
    }
    
    @Override
    public HistoriaClinicaEnfermedadDAO getHistoriaClinicaEnfermedad() {
        SqlServerHistoriaClinicaEnfermedadDAO historiaClinicaEnfermedadDAO = new SqlServerHistoriaClinicaEnfermedadDAO();
        historiaClinicaEnfermedadDAO.setConector(SqlServerConexion.getInstance());
        return historiaClinicaEnfermedadDAO;
    }

    @Override
    public ConsultaDAO getConsulta() {
        SqlServerConsultaDAO consultaDAO = new SqlServerConsultaDAO();
        consultaDAO.setConector(SqlServerConexion.getInstance());
        return consultaDAO;
    }

    @Override
    public ExamenFisicoDAO getExamenFisico() {
        SqlServerExamenFisicoDAO examenFisicoDAO = new SqlServerExamenFisicoDAO();
        examenFisicoDAO.setConector(SqlServerConexion.getInstance());
        return examenFisicoDAO;
    }

    @Override
    public ExamenMedicoDAO getExamenMedico() {
        SqlServerExamenMedicoDAO examenMedicoDAO = new SqlServerExamenMedicoDAO();
        examenMedicoDAO.setConector(SqlServerConexion.getInstance());
        return examenMedicoDAO;
    }

    @Override
    public ExamenClinicoDAO getExamenClinico() {
        SqlServerExamenClinicoDAO examenClinicoDAO = new SqlServerExamenClinicoDAO();
        examenClinicoDAO.setConector(SqlServerConexion.getInstance());
        return examenClinicoDAO;
    }

    
}
