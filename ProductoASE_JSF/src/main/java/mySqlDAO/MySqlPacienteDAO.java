package mySqlDAO;

import DAO.PacienteDAO;
import factoryDAO.DAOFactory;
import factoryDAO.MySqlDAOFactory;
import java.sql.SQLException;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import modelo.Escuela;
import pacientes.Alumno;
import pacientes.Familiar;
import pacientes.Paciente;
import pacientes.Trabajador;

public class MySqlPacienteDAO extends PacienteDAO<Paciente> {

    @Override
    public Paciente create(Paciente obj) {
        try {
            setSql("INSERT INTO Paciente (idPaciente, dni, nombre, apellido, fechaNacimiento, lugarNacimiento, distrito, departamento, direccion, telefono, sexo, estadoCivil, tipoPaciente, idEscuela, areaTrabajo, docente) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");
            setPs(getConector().prepareStatement(getSql()));
            getPs().setInt(1, obj.getIdPaciente());
            getPs().setString(2, obj.getDni());
            getPs().setString(3, obj.getNombre());
            getPs().setString(4, obj.getApellido());
            getPs().setDate(5, Date.valueOf(obj.getFechaNac()));
            getPs().setString(6, obj.getLugarNac());
            getPs().setString(7, obj.getDistrito());
            getPs().setString(8, obj.getDepartamento());
            getPs().setString(9, obj.getDireccion());
            getPs().setString(10, obj.getTelefono());
            getPs().setString(11, obj.getSexo());
            getPs().setString(12, obj.getEstadoCivil());
            getPs().setString(13, obj.getTipoPaciente());
            
            if (obj instanceof Alumno alumno) {
                getPs().setInt(14, (alumno.getEscuela().getIdEscuela()));
                getPs().setNull(15, java.sql.Types.VARCHAR);
                getPs().setNull(16, java.sql.Types.BOOLEAN);
            }
            if (obj instanceof Trabajador trabajador) {
                getPs().setNull(14, java.sql.Types.INTEGER);
                getPs().setString(15, trabajador.getAreaTrabajo());
                getPs().setBoolean(16, trabajador.isDocente());
            }
            
            // se crea los familiares a parte, despues que exista el paciente.     
           
            if (!exeUpdate()) {
                obj = null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return obj;
    }

    @Override
    public Paciente delete(Paciente obj) {
        try {
            setSql("DELETE FROM Paciente WHERE idPaciente = ?");
            setPs(getConector().prepareStatement(getSql()));
            getPs().setInt(1, obj.getIdPaciente());

            if (!exeUpdate()) {
                obj = null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }

    @Override
    public Paciente update(Paciente obj) {
        try {
            setSql("UPDATE Paciente SET dni = ?, nombre = ?, apellido = ?, fechaNacimiento = ?, lugarNacimiento = ?, "
                    + "distrito = ?, departamento = ?, direccion = ?, telefono = ?, sexo = ?, estadoCivil = ?, tipoPaciente = ?, "
                    + "idEscuela = ?, areaTrabajo = ?, docente = ? WHERE idPaciente = ?");
            setPs(getConector().prepareStatement(getSql()));

            getPs().setString(1, obj.getDni());
            getPs().setString(2, obj.getNombre());
            getPs().setString(3, obj.getApellido());
            getPs().setDate(4, Date.valueOf(obj.getFechaNac()));
            getPs().setString(5, obj.getLugarNac());
            getPs().setString(6, obj.getDistrito());
            getPs().setString(7, obj.getDepartamento());
            getPs().setString(8, obj.getDireccion());
            getPs().setString(9, obj.getTelefono());
            getPs().setString(10, obj.getSexo());
            getPs().setString(11, obj.getEstadoCivil());
            getPs().setString(12, obj.getTipoPaciente());
            
            if (obj instanceof Alumno alumno) {
                getPs().setInt(13, (alumno.getEscuela().getIdEscuela()));
                getPs().setNull(14, java.sql.Types.VARCHAR);
                getPs().setNull(15, java.sql.Types.BOOLEAN);
            }
            if (obj instanceof Trabajador trabajador) {
                getPs().setNull(13, java.sql.Types.INTEGER);
                getPs().setString(14, trabajador.getAreaTrabajo());
                getPs().setBoolean(15, trabajador.isDocente());
            }
            DAOFactory dao = new MySqlDAOFactory();
            for (Map.Entry<Integer, Familiar> familiar : obj.getFamiliares().entrySet()) {
                dao.getFamiliar().update(familiar.getValue());
            }
            getPs().setInt(16, obj.getIdPaciente());
            
            if (!exeUpdate()) {
                obj = null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }

    @Override
    public Paciente read(int id) {
        setSql("SELECT * FROM Paciente WHERE idPaciente = ?");
        Paciente paciente = null;
        try {
            setPs(getConector().prepareStatement(getSql()));
            getPs().setInt(1, id);
            setRs(getPs().executeQuery());

            if (getRs().next()) {
                switch (getRs().getString("tipoPaciente")) {
                    case "Alumno" -> {
                        paciente = new Alumno();
                        ((Alumno) paciente).setEscuela(Escuela.getPorId(getRs().getInt("idEscuela")));
                    }
                    case "Trabajador" -> {
                        paciente = new Trabajador();
                        ((Trabajador) paciente).setAreaTrabajo(getRs().getString("areaTrabajo"));
                        ((Trabajador) paciente).setDocente(getRs().getBoolean("docente"));
                    }
                }
                if (paciente != null) {
                    paciente.setIdPaciente(getRs().getInt("idPaciente"));
                    paciente.setDni(getRs().getString("dni"));
                    paciente.setNombre(getRs().getString("nombre"));
                    paciente.setApellido(getRs().getString("apellido"));
                    paciente.setFechaNac(getRs().getDate("fechaNacimiento").toLocalDate());
                    paciente.setLugarNac(getRs().getString("lugarNacimiento"));
                    paciente.setDistrito(getRs().getString("distrito"));
                    paciente.setDepartamento(getRs().getString("departamento"));
                    paciente.setDireccion(getRs().getString("direccion"));
                    paciente.setTelefono(getRs().getString("telefono"));
                    paciente.setSexo(getRs().getString("sexo"));
                    paciente.setEstadoCivil(getRs().getString("estadoCivil"));
                    paciente.setTipoPaciente(getRs().getString("tipoPaciente"));
                    
                    DAOFactory dao = new MySqlDAOFactory();
                    paciente.setFamiliares(dao.getFamiliar().mapped(getRs().getInt("idPaciente")));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return paciente;
    }

    @Override
    public List<Paciente> listed() {
        List<Paciente> listaPacientes = new ArrayList<>();
        try {
            setSql("SELECT * FROM Paciente");
            setPs(getConector().prepareStatement(getSql()));
            setRs(getPs().executeQuery());

            while (getRs().next()) {
                Paciente paciente = null;
                switch (getRs().getString("tipoPaciente")) {
                    case "Alumno" -> {
                        paciente = new Alumno();
                        ((Alumno) paciente).setEscuela(Escuela.getPorId(getRs().getInt("idEscuela")));
                    }
                    case "Trabajador" -> {
                        paciente = new Trabajador();
                        ((Trabajador) paciente).setAreaTrabajo(getRs().getString("areaTrabajo"));
                        ((Trabajador) paciente).setDocente(getRs().getBoolean("docente"));
                    }
                    default -> {
                    }
                }
                if (paciente != null) {
                    paciente.setIdPaciente(getRs().getInt("idPaciente"));
                    paciente.setDni(getRs().getString("dni"));
                    paciente.setNombre(getRs().getString("nombre"));
                    paciente.setApellido(getRs().getString("apellido"));
                    paciente.setFechaNac(getRs().getDate("fechaNacimiento").toLocalDate());
                    paciente.setLugarNac(getRs().getString("lugarNacimiento"));
                    paciente.setDistrito(getRs().getString("distrito"));
                    paciente.setDepartamento(getRs().getString("departamento"));
                    paciente.setDireccion(getRs().getString("direccion"));
                    paciente.setTelefono(getRs().getString("telefono"));
                    paciente.setSexo(getRs().getString("sexo"));
                    paciente.setEstadoCivil(getRs().getString("estadoCivil"));
                    paciente.setTipoPaciente(getRs().getString("tipoPaciente"));
                    listaPacientes.add(paciente);
                    
                    DAOFactory dao = new MySqlDAOFactory();
                    paciente.setFamiliares(dao.getFamiliar().mapped(getRs().getInt("idPaciente")));
                }
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listaPacientes;
    }
    
    @Override
    public int lastId() {
        int lastId = 0;
        try {
            setSql("SELECT TOP 1 idPaciente FROM Paciente ORDER BY idPaciente DESC");
            setPs(getConector().prepareStatement(getSql()));
            setRs(getPs().executeQuery());
            if (getRs().next()) {
                lastId = getRs().getInt("idPaciente");
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
