
package mySqlDAO;

import DAO.ConsultaDAO;
import consultas.ConsultaMedica;
import factoryDAO.DAOFactory;
import factoryDAO.MySqlDAOFactory;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;
import medicos.Medico;

public class MySqlConsultaDAO extends ConsultaDAO<ConsultaMedica>{

    @Override
    public ConsultaMedica create(ConsultaMedica obj) {
        obj.setIdConsulta(lastId());
        try {
            setSql("INSERT INTO Consulta (idConsulta, idHistoriaClinica, idMedico, fecha, hora, edad, motivo, tiempoEnfermedad, apetito, "
                    + "sueño, sed, estadoAnimo) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            setPs(getConector().prepareStatement(getSql()));
            getPs().setInt(1, obj.getIdConsulta());
            getPs().setInt(2, obj.getIdHistoria());
            getPs().setInt(3, obj.getAtendidoPor().getIdMedico());
            getPs().setDate(4, Date.valueOf(obj.getFecha()));
            getPs().setTime(5, Time.valueOf(obj.getHora()));
            getPs().setInt(6, obj.getEdad());
            getPs().setString(7, obj.getMotivo());
            getPs().setString(8, obj.getTiempoEnfermedad());
            getPs().setString(9, obj.getApetito());
            getPs().setString(10, obj.getSueño());
            getPs().setString(11, obj.getSed());
            getPs().setString(12, obj.getEstadoAnimo());

            if (!exeUpdate()) obj = null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return obj;
    }

    @Override
    public ConsultaMedica delete(ConsultaMedica obj) {
        try {
            setSql("DELETE FROM Consulta WHERE idConsulta = ?");
            setPs(getConector().prepareStatement(getSql()));
            getPs().setInt(1, obj.getIdConsulta());

            if (!exeUpdate()) obj = null;
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }

    @Override
    public ConsultaMedica update(ConsultaMedica obj) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public ConsultaMedica read(int id) {
        setSql("SELECT * FROM Consulta WHERE idConsulta = ?");
        ConsultaMedica consulta = null;
        try {
            setPs(getConector().prepareStatement(getSql()));
            getPs().setInt(1, id);
            setRs(getPs().executeQuery());

            if (getRs().next()) {
                consulta = new ConsultaMedica();
                consulta.setIdConsulta(getRs().getInt("idConsulta"));
                consulta.setIdHistoria(getRs().getInt("idHistoriaClinica"));
                
                DAOFactory dao = new MySqlDAOFactory();
                consulta.setAtendidoPor((Medico) dao.getMedico().read(getRs().getInt("idMedico")));
                consulta.setFecha(getRs().getDate("fecha").toLocalDate());
                consulta.setHora(getRs().getTime("hora").toLocalTime());
                consulta.setEdad(getRs().getInt("edad"));
                consulta.setMotivo(getRs().getString("motivo"));
                consulta.setTiempoEnfermedad(getRs().getString("tiempoEnfermedad"));
                consulta.setApetito(getRs().getString("apetito"));
                consulta.setSueño(getRs().getString("sueño"));
                consulta.setSed(getRs().getString("sed"));
                consulta.setEstadoAnimo(getRs().getString("estadoAnimo"));
                
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return consulta;
    }

    @Override
    public List<ConsultaMedica> listed() {
        List<ConsultaMedica> listaConsultasMedicas = new ArrayList<>();
        try {
            setSql("SELECT * FROM Consulta");
            setPs(getConector().prepareStatement(getSql()));
            setRs(getPs().executeQuery());

            while (getRs().next()) {
                ConsultaMedica consulta = new ConsultaMedica();
                consulta.setIdConsulta(getRs().getInt("idConsulta"));
                consulta.setIdHistoria(getRs().getInt("idHistoriaClinica"));
                
                DAOFactory dao = new MySqlDAOFactory();
                consulta.setAtendidoPor((Medico) dao.getMedico().read(getRs().getInt("idMedico")));
                consulta.setFecha(getRs().getDate("fecha").toLocalDate());
                consulta.setHora(getRs().getTime("hora").toLocalTime());
                consulta.setEdad(getRs().getInt("edad"));
                consulta.setMotivo(getRs().getString("motivo"));
                consulta.setTiempoEnfermedad(getRs().getString("tiempoEnfermedad"));
                consulta.setApetito(getRs().getString("apetito"));
                consulta.setSueño(getRs().getString("sueño"));
                consulta.setSed(getRs().getString("sed"));
                consulta.setEstadoAnimo(getRs().getString("estadoAnimo"));
                
                listaConsultasMedicas.add(consulta);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listaConsultasMedicas;
    }
    
    public int lastId() {
        int lastId = 0;
        try {
            setSql("SELECT TOP 1 idConsulta FROM Consulta ORDER BY idConsulta DESC");
            setPs(getConector().prepareStatement(getSql()));
            setRs(getPs().executeQuery());
            if (getRs().next()) {
                lastId = getRs().getInt("idConsulta");
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