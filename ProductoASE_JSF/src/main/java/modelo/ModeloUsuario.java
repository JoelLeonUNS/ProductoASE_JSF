package modelo;

import factoryDAO.DAOFactory;
import factoryDAO.SqlServerDAOFactory;
import java.util.List;
import medicos.Usuario;

public class ModeloUsuario {
    private final DAOFactory dao;
    private Usuario usuario;
    private int numeroIntentos;
    private boolean datosValido = false;
    private final CareTaker careTaker = new CareTaker();
    private String recuerdoSesion; // usuario;clave

    public ModeloUsuario() {
        usuario = new Usuario();
        this.dao = new SqlServerDAOFactory(); // o MySql
    }
 
    public Usuario getUsuario() {
        return usuario;
    }

    public CareTaker getCareTaker() {
        return careTaker;
    }

    public int getNumeroIntentos() {
        return numeroIntentos;
    }

    public String getRecuerdoSesion() {
        return recuerdoSesion;
    }
 
    public void setRecuerdoSesion(String recuerdoSesion) {
        this.recuerdoSesion = recuerdoSesion;
    }
    
    public void resetNumeroIntentos() {
        numeroIntentos = 0;
    }

    public boolean isDatosValido() {
        return datosValido;
    }
    
    public boolean isHabilitado() {
        return usuario.isEstado(); //
    }

    public void iniciarSesion() {
        for(Usuario usuarioBD: (List<Usuario>)dao.getUsuario().listed()) {
            if (usuarioBD.getUsuario().equals(usuario.getUsuario())
                && usuarioBD.getClave().equals(usuario.getClave())) {
                datosValido = true;
                usuario = usuarioBD;
                break;
            } else {
                datosValido = false;
            }
        }
        if (!datosValido) numeroIntentos++;  
    }
    
    public void recordarSesion() {
        careTaker.agregar(guardarSesion());
    }
    
    public void cerrarSesion() {
        restaurarSesion(careTaker.getUltimo());
    }
    
    public Memento guardarSesion() {
        return new Memento(recuerdoSesion);
    }
    
    public void restaurarSesion(Memento m) {
        recuerdoSesion = m.getEstado();
    }
}
