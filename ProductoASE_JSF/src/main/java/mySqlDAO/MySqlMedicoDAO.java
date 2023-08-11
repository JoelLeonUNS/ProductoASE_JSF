package mySqlDAO;

import DAO.MedicoDAO;
import factoryDAO.DAOFactory;
import factoryDAO.MySqlDAOFactory;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import medicos.Medico;
import medicos.Usuario;

public class MySqlMedicoDAO extends MedicoDAO<Medico> {

    @Override
    public Medico create(Medico obj) {
        obj.setIdMedico(lastId());
        try {
            setSql("INSERT INTO Medico (idMedico, idUsuario, dni, apellido, nombre, telefono) VALUES (?, ?, ?, ?, ?, ?)");
            setPs(getConector().prepareStatement(getSql()));
                        
            getPs().setInt(1, obj.getIdMedico());
            getPs().setInt(2, obj.getUsuario().getIdUsuario());
            getPs().setString(3, obj.getDNI());
            getPs().setString(4, obj.getApellidoMedico());
            getPs().setString(5, obj.getNombreMedico());
            getPs().setString(6, obj.getTelefonoMedico());

            if (!exeUpdate()) {
                obj = null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return obj;
    }

    @Override
    public Medico delete(Medico obj) {
        try {
            setSql("DELETE FROM Medico WHERE idMedico = ?");
            setPs(getConector().prepareStatement(getSql()));
            getPs().setInt(1, obj.getIdMedico());

            if (!exeUpdate()) obj = null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }

    @Override
    public Medico update(Medico obj) {
        try {
            setSql("UPDATE Medico SET apellido = ?, nombre = ?, telefono = ? WHERE idMedico = ?");
            setPs(getConector().prepareStatement(getSql()));
            getPs().setString(1, obj.getApellidoMedico());
            getPs().setString(2, obj.getNombreMedico());
            getPs().setString(3, obj.getTelefonoMedico());
            getPs().setInt(4, obj.getIdMedico());

            if (!exeUpdate()) {
                obj = null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }

    @Override
    public Medico read(int id) {
        setSql("SELECT * FROM Medico WHERE idMedico = ?");
        Medico medico = null;
        try {
            setPs(getConector().prepareStatement(getSql()));
            getPs().setInt(1, id);
            setRs(getPs().executeQuery());

            if (getRs().next()) {
                medico = new Medico();
                medico.setIdMedico(getRs().getInt("idMedico"));
                
                DAOFactory dao = new MySqlDAOFactory();
                medico.setUsuario((Usuario) dao.getUsuario().read(getRs().getInt("idUsuario")));
                
                medico.setApellidoMedico(getRs().getString("apellido"));
                medico.setNombreMedico(getRs().getString("nombre"));
                medico.setTelefonoMedico(getRs().getString("telefono"));
                medico.setDNI(getRs().getString("dni"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return medico;
    }

    @Override
    public List<Medico> listed() {
        List<Medico> listaMedicos = new ArrayList<>();
        try {
            setSql("SELECT * FROM Medico");
            setPs(getConector().prepareStatement(getSql()));
            setRs(getPs().executeQuery());

            while (getRs().next()) {
                Medico medico = new Medico();
                medico.setIdMedico(getRs().getInt("idMedico"));
                
                DAOFactory dao = new MySqlDAOFactory();
                medico.setUsuario((Usuario) dao.getUsuario().read(getRs().getInt("idUsuario")));
                
                medico.setDNI(getRs().getString("dni"));
                medico.setApellidoMedico(getRs().getString("apellido"));
                medico.setNombreMedico(getRs().getString("nombre"));
                medico.setTelefonoMedico(getRs().getString("telefono"));

                listaMedicos.add(medico);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listaMedicos;
    }
    
    public int lastId(){
        int lastId = 0;
        
        try{
            setSql("SELECT TOP 1 idMedico FROM Medico order by idMedico desc");
            setPs(getConector().prepareStatement(getSql()));
            setRs(getPs().executeQuery());
            
            if(getRs().next()){
                lastId = getRs().getInt("idMedico");
            }
            
        } catch (SQLException e){
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
