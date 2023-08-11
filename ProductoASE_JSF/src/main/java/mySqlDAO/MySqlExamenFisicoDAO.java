
package mySqlDAO;

import DAO.ExamenFisicoDAO;
import examenesFisico.ExamenFisico;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlExamenFisicoDAO extends ExamenFisicoDAO<ExamenFisico> {

    @Override
    public ExamenFisico create(ExamenFisico obj) {
        obj.setIdExamen(lastId());
        try {
            setSql("INSERT INTO ExamenFisico (idExamenFisico, idConsulta, observacion, temperatura, PA, FR, FC, SPO2, peso, talla, "
                    + "perimetroAbdominal, IMC) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            setPs(getConector().prepareStatement(getSql()));
            getPs().setInt(1, obj.getIdExamen());
            getPs().setInt(2, obj.getIdConsulta());
            getPs().setString(3, obj.getObservacion());
            getPs().setDouble(4, obj.getTemperatura());
            getPs().setString(5, obj.getPA());
            getPs().setDouble(6, obj.getFR());
            getPs().setDouble(7, obj.getFC());
            getPs().setDouble(8, obj.getSPO2());
            getPs().setDouble(9, obj.getPeso());
            getPs().setDouble(10, obj.getTalla());
            getPs().setDouble(11, obj.getPerimetroAbdominal());
            getPs().setDouble(12, obj.getIMC());

            if (!exeUpdate()) obj = null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }

    @Override
    public ExamenFisico delete(ExamenFisico obj) {
        try {
            setSql("DELETE FROM ExamenFisico WHERE idExamenFisico = ?");
            setPs(getConector().prepareStatement(getSql()));
            getPs().setInt(1, obj.getIdConsulta());

            if (!exeUpdate()) obj = null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }

    @Override
    public ExamenFisico update(ExamenFisico obj) {
        try {
            setSql("UPDATE ExamenFisico SET observacion = ?, temperatura = ?, PA = ?, FR = ?, FC = ?, SPO2 = ?, peso = ?,"
                    + " talla = ?, perimetroAbdominal = ?, IMC = ? WHERE idExamenFisico = ?");
            setPs(getConector().prepareStatement(getSql()));
            getPs().setString(1, obj.getObservacion());
            getPs().setDouble(2, obj.getTemperatura());
            getPs().setString(3, obj.getPA());
            getPs().setDouble(4, obj.getFR());
            getPs().setDouble(5, obj.getFC());
            getPs().setDouble(6, obj.getSPO2());
            getPs().setDouble(7, obj.getPeso());
            getPs().setDouble(8, obj.getTalla());
            getPs().setDouble(9, obj.getPerimetroAbdominal());
            getPs().setDouble(10, obj.getIMC());
            getPs().setInt(11, obj.getIdExamen());

            if (!exeUpdate()) {
                obj = null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }

    @Override
    public ExamenFisico read(int id) {
        setSql("SELECT * FROM ExamenFisico WHERE idExamenFisico = ?");
        ExamenFisico examen = null;
        try {
            setPs(getConector().prepareStatement(getSql()));
            getPs().setInt(1, id);
            setRs(getPs().executeQuery());

            if (getRs().next()) {
                examen = new ExamenFisico();
                examen.setIdExamen(getRs().getInt("idExamenFisico"));                
                examen.setIdConsulta(getRs().getInt("idConsulta"));
                examen.setObservacion(getRs().getString("observacion"));
                examen.setTemperatura(getRs().getDouble("temperatura"));
                examen.setPA(getRs().getString("PA"));
                examen.setFR(getRs().getDouble("FR"));
                examen.setFC(getRs().getDouble("FC"));
                examen.setSPO2(getRs().getDouble("SPO2"));
                examen.setPeso(getRs().getDouble("peso"));
                examen.setTalla(getRs().getDouble("talla"));
                examen.setPerimetroAbdominal(getRs().getDouble("perimetroAbdominal"));
                examen.setIMC(getRs().getDouble("IMC"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return examen;
    }

    @Override
    public List<ExamenFisico> listed() {
        List<ExamenFisico> listaExamenes = new ArrayList<>();
        try {
            setSql("SELECT * FROM ExamenFisico");
            setPs(getConector().prepareStatement(getSql()));
            setRs(getPs().executeQuery());

            while (getRs().next()) {
                ExamenFisico examen = new ExamenFisico();
                examen.setIdExamen(getRs().getInt("idExamenFisico"));                
                examen.setIdConsulta(getRs().getInt("idConsulta"));
                examen.setObservacion(getRs().getString("observacion"));
                examen.setTemperatura(getRs().getDouble("temperatura"));
                examen.setPA(getRs().getString("PA"));
                examen.setFR(getRs().getDouble("FR"));
                examen.setFC(getRs().getDouble("FC"));
                examen.setSPO2(getRs().getDouble("SPO2"));
                examen.setPeso(getRs().getDouble("peso"));
                examen.setTalla(getRs().getDouble("talla"));
                examen.setPerimetroAbdominal(getRs().getDouble("perimetroAbdominal"));
                examen.setIMC(getRs().getDouble("IMC"));
                
                listaExamenes.add(examen);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listaExamenes;
    }
    
    public int lastId() {
        int lastId = 0;
        try {
            setSql("SELECT TOP 1 idExamenFisico FROM ExamenFisico ORDER BY idExamenFisico DESC");
            setPs(getConector().prepareStatement(getSql()));
            setRs(getPs().executeQuery());
            if (getRs().next()) {
                lastId = getRs().getInt("idExamenFisico");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return lastId+1;
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
