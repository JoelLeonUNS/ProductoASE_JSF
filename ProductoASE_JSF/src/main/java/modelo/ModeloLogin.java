package modelo;

import conexiones.MySqlConexion;
import factoryDAO.DAOFactory;
import factoryDAO.MySqlDAOFactory;
import java.util.List;
import medicos.Usuario;

public class ModeloLogin {
    private final DAOFactory dao;
    private Usuario usuario;
    private int numeroIntentos;
    private boolean datosValido = false;
    private final CareTaker careTaker = new CareTaker();
    private String recuerdoSesion; // usuario;clave

    public ModeloLogin() {
        usuario = new Usuario();
        this.dao = new MySqlDAOFactory(); // o MySql
        MySqlConexion.setDatos("localhost", "3306", "UnidadMedica", "root", "02122002");	
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
    	List<Usuario> listed = (List<Usuario>)dao.getUsuario().listed();
        for(Usuario usuarioBD: listed) {
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
    
    public String login() {
    	iniciarSesion();
    	if (datosValido) {
    		if(usuario.getRol().equals("Admin")) {
    			return "/vistaAdmin/administrar_cuentas.xhtml";
    		} else {
    			return "/vistaMedico/historias_clinicas.xhtml";
    		}
    		
    	} else {
    		 return "failure"; 
    	}
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
