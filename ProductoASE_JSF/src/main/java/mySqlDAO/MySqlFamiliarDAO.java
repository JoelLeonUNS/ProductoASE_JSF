package mySqlDAO;

import DAO.FamiliarDAO;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import pacientes.Familiar;

public class MySqlFamiliarDAO extends FamiliarDAO<Familiar> {

    @Override
    public Familiar create(Familiar obj) {
        try {
            setSql("INSERT INTO Familiar (idFamiliar, idPaciente, nombre, parentesco, direccion, telefono) VALUES (?, ?, ?, ?, ?, ?)");
            setPs(getConector().prepareStatement(getSql()));
            getPs().setInt(1, obj.getIdFamiliar());
            getPs().setInt(2, obj.getIdPaciente());
            getPs().setString(3, obj.getNombreFamiliar());
            getPs().setString(4, obj.getParentesco());
            getPs().setString(5, obj.getDireccionFamiliar());
            getPs().setString(6, obj.getTelefonoFamiliar());

            if (!exeUpdate()) {
                obj = null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return obj;
    }

    @Override
    public Familiar delete(Familiar obj) {
        try {
            setSql("DELETE FROM Familiar WHERE idFamiliar = ?");
            setPs(getConector().prepareStatement(getSql()));
            getPs().setInt(1, obj.getIdFamiliar());

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
            setSql("DELETE FROM Familiar WHERE idPaciente = ?");
            setPs(getConector().prepareStatement(getSql()));
            getPs().setInt(1, id);

            exito = exeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return exito;
    }

    @Override
    public Familiar update(Familiar obj) {
        try {
            setSql("UPDATE Familiar SET idPaciente = ?, nombre = ?, parentesco = ?, direccion = ?, telefono = ? WHERE idFamiliar = ?");
            setPs(getConector().prepareStatement(getSql()));
            getPs().setInt(1, obj.getIdPaciente());
            getPs().setString(2, obj.getNombreFamiliar());
            getPs().setString(3, obj.getParentesco());
            getPs().setString(4, obj.getDireccionFamiliar());
            getPs().setString(5, obj.getTelefonoFamiliar());
            getPs().setInt(6, obj.getIdFamiliar());

            if (!exeUpdate()) {
                obj = null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }

    @Override
    public Familiar read(int id) {
        setSql("SELECT * FROM Familiar WHERE idFamiliar = ?");
        Familiar familiar = null;
        try {
            setPs(getConector().prepareStatement(getSql()));
            getPs().setInt(1, id);
            setRs(getPs().executeQuery());

            if (getRs().next()) {
                familiar = new Familiar();
                familiar.setIdFamiliar(getRs().getInt("idFamiliar"));
                familiar.setIdPaciente(getRs().getInt("idPaciente"));
                familiar.setNombreFamiliar(getRs().getString("nombre"));
                familiar.setParentesco(getRs().getString("parentesco"));
                familiar.setDireccionFamiliar(getRs().getString("direccion"));
                familiar.setTelefonoFamiliar(getRs().getString("telefono"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return familiar;
    }

    @Override
    public Map<Integer, Familiar> mapped(int id) {
        Map<Integer, Familiar> mapaFamiliares = new HashMap<>();
        int key = 1;
        try {
            setSql("SELECT * FROM Familiar WHERE idPaciente = ?");
            setPs(getConector().prepareStatement(getSql()));
            getPs().setInt(1, id);
            setRs(getPs().executeQuery());

            while (getRs().next()) {
                Familiar familiar = new Familiar();
                familiar.setIdFamiliar(getRs().getInt("idFamiliar"));
                familiar.setIdPaciente(getRs().getInt("idPaciente"));
                familiar.setNombreFamiliar(getRs().getString("nombre"));
                familiar.setParentesco(getRs().getString("parentesco"));
                familiar.setDireccionFamiliar(getRs().getString("direccion"));
                familiar.setTelefonoFamiliar(getRs().getString("telefono"));

                mapaFamiliares.put(key, familiar);
                key++;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return mapaFamiliares;
    }
    
    @Override
    public int lastId() {
        int lastId = 0;
        try {
            setSql("SELECT TOP 1 idFamiliar FROM Familiar ORDER BY idFamiliar DESC");
            setPs(getConector().prepareStatement(getSql()));
            setRs(getPs().executeQuery());
            if (getRs().next()) {
                lastId = getRs().getInt("idFamiliar");
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
