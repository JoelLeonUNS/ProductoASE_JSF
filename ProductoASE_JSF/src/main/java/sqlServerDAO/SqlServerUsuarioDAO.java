package sqlServerDAO;

import DAO.UsuarioDAO;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import medicos.Usuario;

public class SqlServerUsuarioDAO extends UsuarioDAO<Usuario> {

    @Override
    public Usuario create(Usuario obj) {
        obj.setIdUsuario(lastId());
        try {
            setSql("INSERT INTO Usuario (idUsuario, usuario, clave, rol, estado) VALUES (?, ?, ?, ?, ?)");
            setPs(getConector().prepareStatement(getSql()));
                        
            getPs().setInt(1, obj.getIdUsuario());
            getPs().setString(2, obj.getUsuario());
            getPs().setString(3, obj.getClave());
            getPs().setString(4, obj.getRol());
            getPs().setBoolean(5, obj.isEstado());
            
            if (!exeUpdate()) {
                obj = null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return obj;
    }

    @Override
    public Usuario delete(Usuario obj) {
        try {
            setSql("DELETE FROM Usuario WHERE idUsuario = ?");
            setPs(getConector().prepareStatement(getSql()));
            getPs().setInt(1, obj.getIdUsuario());

            if (!exeUpdate()) {
                obj = null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }

    @Override
    public Usuario update(Usuario obj) {
        try {
            setSql("UPDATE Usuario SET usuario = ?, clave = ?, rol = ?, estado = ? WHERE idUsuario = ?");
            setPs(getConector().prepareStatement(getSql()));
            getPs().setString(1, obj.getUsuario());
            getPs().setString(2, obj.getClave());
            getPs().setString(3, obj.getRol());
            getPs().setBoolean(4, obj.isEstado());
            getPs().setInt(5, obj.getIdUsuario());

            if (!exeUpdate()) {
                obj = null;
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return obj;
    }

    @Override
    public Usuario read(int id) {
        setSql("SELECT * FROM Usuario WHERE idUsuario = ?");
        Usuario usuario = null;
        try {
            setPs(getConector().prepareStatement(getSql()));
            getPs().setInt(1, id);
            setRs(getPs().executeQuery());

            if (getRs().next()) {
                usuario = new Usuario();
                usuario.setIdUsuario(getRs().getInt("idUsuario"));
                usuario.setUsuario(getRs().getString("usuario"));
                usuario.setClave(getRs().getString("clave"));
                usuario.setRol(getRs().getString("rol"));
                usuario.setEstado(getRs().getBoolean("estado"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return usuario;
    }

    @Override
    public List<Usuario> listed() {
        List<Usuario> listaUsuarios = new ArrayList<>();
        try {
            setSql("SELECT * FROM Usuario");
            setPs(getConector().prepareStatement(getSql()));
            setRs(getPs().executeQuery());

            while (getRs().next()) {
                Usuario usuario = new Usuario();
                usuario.setIdUsuario(getRs().getInt("idUsuario"));
                usuario.setUsuario(getRs().getString("usuario"));
                usuario.setClave(getRs().getString("clave"));
                usuario.setRol(getRs().getString("rol"));
                usuario.setEstado(getRs().getBoolean("estado"));

                listaUsuarios.add(usuario);
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return listaUsuarios;
    }

    public int lastId() {
        int lastId = 0;
        try {
            setSql("SELECT TOP 1 idUsuario FROM Usuario ORDER BY idUsuario DESC");
            setPs(getConector().prepareStatement(getSql()));
            setRs(getPs().executeQuery());
            if (getRs().next()) {
                lastId = getRs().getInt("idUsuario");
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
