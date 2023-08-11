
package sqlServerDAO;

import DAO.ExamenMedicoDAO;
import examenesMedico.ExamenMedico;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class SqlServerExamenMedicoDAO extends ExamenMedicoDAO<ExamenMedico>{

    @Override
    public ExamenMedico create(ExamenMedico obj) {
        obj.setIdExamen(lastId());
        try {
            setSql("INSERT INTO ExamenMedico (idExamenMedico, idConsulta, observacion, diagnostico, tratamiento, examenAuxiliar) VALUES (?, ?, ?, ?, ?, ?)");
            setPs(getConector().prepareStatement(getSql()));
            getPs().setInt(1, obj.getIdExamen());
            getPs().setInt(2, obj.getIdConsulta());
            getPs().setString(3, obj.getObservacion());
            getPs().setString(4, obj.getDiagnostico());
            getPs().setString(5, obj.getTratamiento());
            getPs().setString(6, obj.getExamenesAux());

            if (!exeUpdate()) obj = null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return obj;
    }

    @Override
    public ExamenMedico delete(ExamenMedico obj) {
        try {
            setSql("DELETE FROM ExamenMedico WHERE idExamenMedico = ?");
            setPs(getConector().prepareStatement(getSql()));
            getPs().setInt(1, obj.getIdConsulta());

            if (!exeUpdate()) obj = null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }

    @Override
    public ExamenMedico update(ExamenMedico obj) {
        try {
            setSql("UPDATE ExamenMedico SET observacion = ?, diagnostico = ?, tratamiento = ?, examenAuxiliar = ? WHERE idExamenMedico = ?");
            setPs(getConector().prepareStatement(getSql()));
            getPs().setString(1, obj.getObservacion());
            getPs().setString(2, obj.getDiagnostico());
            getPs().setString(3, obj.getTratamiento());
            getPs().setString(4, obj.getExamenesAux());
            getPs().setInt(5, obj.getIdExamen());

            if (!exeUpdate()) {
                obj = null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }

    @Override
    public ExamenMedico read(int id) {
        setSql("SELECT * FROM ExamenMedico WHERE idExamenMedico = ?");
        ExamenMedico examen = null;
        try {
            setPs(getConector().prepareStatement(getSql()));
            getPs().setInt(1, id);
            setRs(getPs().executeQuery());

            if (getRs().next()) {
                examen = new ExamenMedico();
                examen.setIdExamen(getRs().getInt("idExamenMedico"));                
                examen.setIdConsulta(getRs().getInt("idConsulta"));
                examen.setObservacion(getRs().getString("observacion"));
                examen.setDiagnostico(getRs().getString("diagnostico"));
                examen.setTratamiento(getRs().getString("tratamiento"));
                examen.setExamenesAux(getRs().getString("examenAuxiliar"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return examen;
    }

    @Override
    public List<ExamenMedico> listed() {
        List<ExamenMedico> listaExamenes = new ArrayList<>();
        try {
            setSql("SELECT * FROM ExamenMedico");
            setPs(getConector().prepareStatement(getSql()));
            setRs(getPs().executeQuery());

            while (getRs().next()) {
                ExamenMedico examen = new ExamenMedico();
                examen.setIdExamen(getRs().getInt("idExamenMedico"));                
                examen.setIdConsulta(getRs().getInt("idConsulta"));
                examen.setObservacion(getRs().getString("observacion"));
                examen.setDiagnostico(getRs().getString("diagnostico"));
                examen.setTratamiento(getRs().getString("tratamiento"));
                examen.setExamenesAux(getRs().getString("examenAuxiliar"));
                
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
            setSql("SELECT TOP 1 idExamenMedico FROM ExamenMedico ORDER BY idExamenMedico DESC");
            setPs(getConector().prepareStatement(getSql()));
            setRs(getPs().executeQuery());
            if (getRs().next()) {
                lastId = getRs().getInt("idExamenMedico");
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
