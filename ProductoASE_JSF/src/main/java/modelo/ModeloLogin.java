package modelo;

import conexiones.MySqlConexion;
import factoryDAO.DAOFactory;
import factoryDAO.MySqlDAOFactory;
import java.util.List;
import medicos.Usuario;

public class ModeloLogin {
    private final DAOFactory dao;
    private Usuario usuario;
    private boolean chckbxRecodarSeleccionado;
    private boolean bttnLoginDeshabilitado;
    private int numeroIntentos;
    private boolean datosValido = false;
    private final CareTaker careTaker = new CareTaker();
    private String recuerdoSesion; // usuario;clave

    public ModeloLogin() {
        usuario = new Usuario();
        chckbxRecodarSeleccionado = false;
        this.dao = new MySqlDAOFactory(); // o MySql
        MySqlConexion.setDatos("localhost", "3306", "UnidadMedica", "root", "halamadrid");	
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

	public boolean isChckbxRecodarSeleccionado() {
		return chckbxRecodarSeleccionado;
	}

	public void setChckbxRecodarSeleccionado(boolean chckbxRecodarSeleccionado) {
		this.chckbxRecodarSeleccionado = chckbxRecodarSeleccionado;
	}

	public boolean isBttnLoginDeshabilitado() {
		return bttnLoginDeshabilitado;
	}

	public void setBttnLoginDeshabilitado(boolean bttnLoginDeshabilitado) {
		this.bttnLoginDeshabilitado = bttnLoginDeshabilitado;
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
    
    public boolean isBloqueado() {
        return getNumeroIntentos() > 2;
    }
    
    public boolean isAcceso() {
        return isDatosValido() && getNumeroIntentos() < 3;
    }
    
    public void noRecordarSesion() {
        setRecuerdoSesion(null);
    }

    public boolean isRecordado() {
        setRecuerdoSesion(getUsuario().getUsuario() + ";" + getUsuario().getClave());
        return getCareTaker().isExiste(guardarSesion());
    }
    
    public void recordarSesion() {
        careTaker.agregar(guardarSesion());
    }
    
    public String logout() {
    	try {
    		restaurarSesion(careTaker.getUltimo());
    		return "irLogin";
        } catch (Exception e) {
        	return "irLogin";
        }
    }
    
    public Memento guardarSesion() {
        return new Memento(recuerdoSesion);
    }
    
    public void restaurarSesion(Memento m) {
        recuerdoSesion = m.getEstado();
    }
    
    public void validarSesion() {
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
    
    public String login() {
    	String acceso = "";
    	validarSesion();
        if (isAcceso()) {
            if (isRecordado()) {
                recordarSesion(); // lo recuerda des nuevo
            } else {
                if (chckbxRecodarSeleccionado) {
                    recordarSesion();
                } else {
                    noRecordarSesion();
                }
            }
            if (usuario.isEstado()) {
            	if(usuario.getRol().equals("Admin")) {
        			acceso = "adminAcceso";
        		} else {
        			acceso = "medicoAcceso";
        		}
            } else {
                //mensaje("Lo siento, su cuenta se\nencuentra inhabilitada.");
            }
        } else {
        	return "sinAcceso";
        }
        if (isBloqueado()) {
            setBttnLoginDeshabilitado(false);
        }
        return acceso;
    }

}
