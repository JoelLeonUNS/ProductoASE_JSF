package mySqlDAO;

import DAO.HistoriaClinicaDAO;
import factoryDAO.DAOFactory;
import factoryDAO.MySqlDAOFactory;
import historias.HistoriaClinica;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Enfermedad;
import pacientes.Paciente;

public class MySqlHistoriaClinicaDAO extends HistoriaClinicaDAO<HistoriaClinica> {

    @Override
    public HistoriaClinica create(HistoriaClinica obj) {
        try {
            setSql("INSERT INTO HistoriaClinica (idHistoriaClinica, idPaciente, otrosAntecedentes) VALUES (?, ?, ?)");
            setPs(getConector().prepareStatement(getSql()));
            getPs().setInt(1, obj.getIdHistoriaClinica());
            getPs().setInt(2, obj.getPaciente().getIdPaciente());
            getPs().setString(3, obj.getOtrosAntecedentesPatologicos());
            
            DAOFactory dao = new MySqlDAOFactory();
            dao.getPaciente().create(obj.getPaciente());

            if (!exeUpdate()) obj = null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return obj;
    }

    @Override
    public HistoriaClinica delete(HistoriaClinica obj) {
        try {
            setSql("DELETE FROM HistoriaClinica WHERE idHistoriaClinica = ?");
            setPs(getConector().prepareStatement(getSql()));
            getPs().setInt(1, obj.getIdHistoriaClinica());

            if (!exeUpdate()) obj = null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }

    @Override
    public HistoriaClinica update(HistoriaClinica obj) {
        try {
            setSql("UPDATE HistoriaClinica SET otrosAntecedentes = ? WHERE idHistoriaClinica = ?");
            setPs(getConector().prepareStatement(getSql()));
            getPs().setString(1, obj.getOtrosAntecedentesPatologicos());
            getPs().setInt(2, obj.getIdHistoriaClinica());
            
            DAOFactory dao = new MySqlDAOFactory();
            dao.getPaciente().update(obj.getPaciente());
            
            if (!exeUpdate()) obj = null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }

    @Override
    public HistoriaClinica read(int idHistoriaClinica) {
        setSql("SELECT * FROM HistoriaClinica WHERE idHistoriaClinica = ?");
        HistoriaClinica historiaClinica = null;
        try {
            setPs(getConector().prepareStatement(getSql()));
            getPs().setInt(1, idHistoriaClinica);
            setRs(getPs().executeQuery());

            if (getRs().next()) {
                historiaClinica = new HistoriaClinica();
                historiaClinica.setIdHistoriaClinica(getRs().getInt("idHistoriaClinica"));

                DAOFactory dao = new MySqlDAOFactory();
                historiaClinica.setPaciente((Paciente) dao.getPaciente().read(getRs().getInt("idPaciente")));
                historiaClinica.setAntecedentesPatologicos((ArrayList<Enfermedad>)dao.getHistoriaClinicaEnfermedad().listed(getRs().getInt("idHistoriaClinica")));
                historiaClinica.setOtrosAntecedentesPatologicos(getRs().getString("otrosAntecedentes"));
            }
        } catch (SQLException e) {
            System.out.println("[MySqlHistoriaClinicaDAO - read()]" + e.getMessage());
        }
        return historiaClinica;
    }

    @Override
    public List<HistoriaClinica> listed() {
        List<HistoriaClinica> listaHistoriasClinicas = new ArrayList<>();
        try {
            setSql("SELECT * FROM HistoriaClinica");
            setPs(getConector().prepareStatement(getSql()));
            setRs(getPs().executeQuery());

            while (getRs().next()) {
                HistoriaClinica historiaClinica = new HistoriaClinica();
                historiaClinica.setIdHistoriaClinica(getRs().getInt("idHistoriaClinica"));
                
                // falta los antecedentes patológicos
                DAOFactory dao = new MySqlDAOFactory();
                historiaClinica.setPaciente((Paciente) dao.getPaciente().read(getRs().getInt("idPaciente")));
                historiaClinica.setOtrosAntecedentesPatologicos(getRs().getString("otrosAntecedentes"));

                listaHistoriasClinicas.add(historiaClinica);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listaHistoriasClinicas;
    }
    
    @Override
    public int lastId() {
        int lastId = 0;
        try {
            setSql("SELECT TOP 1 idHistoriaClinica FROM HistoriaClinica ORDER BY idHistoriaClinica DESC");
            setPs(getConector().prepareStatement(getSql()));
            setRs(getPs().executeQuery());
            if (getRs().next()) {
                lastId = getRs().getInt("idHistoriaClinica");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lastId;
    }


    @Override
    public boolean exeUpdate() throws SQLException {
        boolean exito;
        try {
            getPs().executeUpdate();
            getConector().commit();
            exito = true;
            System.out.println("Transacción exitosa - " + this.getClass().getSimpleName());
        } catch (SQLException ex) {
            getConector().rollback();
            exito = false;
            System.out.println("Transacciónn NO exitosa - " + this.getClass().getSimpleName() + ":\n" + ex.getMessage());
        } finally {
            if (getPs() != null) {
                getPs().close();
            }
        }
        return exito;
    }
}
