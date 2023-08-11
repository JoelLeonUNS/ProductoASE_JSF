
package mySqlDAO;

import DAO.ExamenClinicoDAO;
import examenesClinico.ExamenClinico;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class MySqlExamenClinicoDAO extends ExamenClinicoDAO<ExamenClinico> {

    @Override
    public ExamenClinico create(ExamenClinico obj) {
        obj.setIdExamen(lastId());
        try {
            setSql("INSERT INTO ExamenClinico (idExamenClinico, idConsulta, observacion, apCardiovascular, apRespiratorio) VALUES (?, ?, ?, ?, ?)");
            setPs(getConector().prepareStatement(getSql()));
            getPs().setInt(1, obj.getIdExamen());
            getPs().setInt(2, obj.getIdConsulta());
            getPs().setString(3, obj.getObservacion());
            getPs().setBoolean(4, obj.isAPCardiovascular());
            getPs().setBoolean(5, obj.isAPRespiratorio());

            if (!exeUpdate()) obj = null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return obj;
    }

    @Override
    public ExamenClinico delete(ExamenClinico obj) {
        try {
            setSql("DELETE FROM ExamenClinico WHERE idExamenClinico = ?");
            setPs(getConector().prepareStatement(getSql()));
            getPs().setInt(1, obj.getIdConsulta());

            if (!exeUpdate()) obj = null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }

    @Override
    public ExamenClinico update(ExamenClinico obj) {
        try {
            setSql("UPDATE ExamenClinico SET observacion = ?, apCardiovascular = ?, apRespiratorio = ? WHERE idExamenClinico = ?");
            setPs(getConector().prepareStatement(getSql()));
            getPs().setString(1, obj.getObservacion());
            getPs().setBoolean(2, obj.isAPCardiovascular());
            getPs().setBoolean(3, obj.isAPRespiratorio());
            getPs().setInt(4, obj.getIdExamen());

            if (!exeUpdate()) {
                obj = null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }

    @Override
    public ExamenClinico read(int id) {
        setSql("SELECT * FROM ExamenClinico WHERE idExamenClinico = ?");
        ExamenClinico examen = null;
        try {
            setPs(getConector().prepareStatement(getSql()));
            getPs().setInt(1, id);
            setRs(getPs().executeQuery());

            if (getRs().next()) {
                examen = new ExamenClinico();
                examen.setIdExamen(getRs().getInt("idExamenClinico"));                
                examen.setIdConsulta(getRs().getInt("idConsulta"));
                examen.setObservacion(getRs().getString("observacion"));
                examen.setAPCardiovascular(getRs().getBoolean("apCardiovascular"));
                examen.setAPRespiratorio(getRs().getBoolean("apRespiratorio"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return examen;
    }

    @Override
    public List<ExamenClinico> listed() {
        List<ExamenClinico> listaExamenes = new ArrayList<>();
        try {
            setSql("SELECT * FROM ExamenClinico");
            setPs(getConector().prepareStatement(getSql()));
            setRs(getPs().executeQuery());

            while (getRs().next()) {
                ExamenClinico examen = new ExamenClinico();
                examen.setIdExamen(getRs().getInt("IdExamenClinico"));                
                examen.setIdConsulta(getRs().getInt("idConsulta"));
                examen.setObservacion(getRs().getString("observacion"));
                examen.setAPCardiovascular(getRs().getBoolean("apCardiovascular"));
                examen.setAPRespiratorio(getRs().getBoolean("apRespiratorio"));
                
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
            setSql("SELECT TOP 1 idExamenClinico FROM ExamenClinico ORDER BY idExamenClinico DESC");
            setPs(getConector().prepareStatement(getSql()));
            setRs(getPs().executeQuery());
            if (getRs().next()) {
                lastId = getRs().getInt("idExamenClinico");
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
