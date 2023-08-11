package mySqlDAO;

import DAO.HistoriaClinicaEnfermedadDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import modelo.Enfermedad;

public class MySqlHistoriaClinicaEnfermedadDAO extends HistoriaClinicaEnfermedadDAO<Enfermedad> {

    @Override
    public Enfermedad create(int id, Enfermedad obj) {
        try {
            setSql("INSERT INTO HistoriaClinicaEnfermedad (idHistoriaClinica, idEnfermedad) VALUES (?, ?)");
            setPs(getConector().prepareStatement(getSql()));
            getPs().setInt(1, id);
            getPs().setInt(2, obj.getIdEnfermedad());
            
            if (!exeUpdate()) {
                obj = null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return obj;
    }

    @Override
    public Enfermedad delete(int id, Enfermedad obj) {
        try {
            setSql("DELETE FROM HistoriaClinicaEnfermedad WHERE idHistoriaClinica = ? AND idEnfermedad = ?");
            setPs(getConector().prepareStatement(getSql()));
            getPs().setInt(1, id);
            getPs().setInt(2, obj.getIdEnfermedad());

            if (!exeUpdate()) {
                obj = null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }
    
    @Override
    public boolean deleteAll(int id) {
        boolean exito = false;
        try {
            setSql("DELETE FROM HistoriaClinicaEnfermedad WHERE idHistoriaClinica = ?");
            setPs(getConector().prepareStatement(getSql()));
            getPs().setInt(1, id);

            exito = exeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return exito;
    }

    @Override
    public Enfermedad update(int id, Enfermedad obj) {
        try {
            setSql("DELETE FROM HistoriaClinicaEnfermedad WHERE idHistoriaClinica = ? AND idEnfermedad = ?");
            setPs(getConector().prepareStatement(getSql()));
            getPs().setInt(1, id);
            getPs().setInt(2, obj.getIdEnfermedad());
            exeUpdate();

            setSql("INSERT INTO HistoriaClinicaEnfermedad (idHistoriaClinica, idEnfermedad) VALUES (?, ?)");
            setPs(getConector().prepareStatement(getSql()));
            getPs().setInt(1, id);
            getPs().setInt(2, obj.getIdEnfermedad());
            exeUpdate();
            
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }

    @Override
    public Enfermedad read(int id, int idSec) {
        return null; // not yet
    }

    @Override
    public List<Enfermedad> listed(int id) {
        List<Enfermedad> listaHistoriasClinicasEnfermedad = new ArrayList<>();
        try {
            setSql("SELECT idEnfermedad FROM HistoriaClinicaEnfermedad WHERE idHistoriaClinica = ?");
            setPs(getConector().prepareStatement(getSql()));
            getPs().setInt(1, id);
            setRs(getPs().executeQuery());

            while (getRs().next()) {
                listaHistoriasClinicasEnfermedad.add(Enfermedad.getPorId(getRs().getInt("idEnfermedad")));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listaHistoriasClinicasEnfermedad;
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
