package Beans;

import java.io.Serializable;
import modelo.ModeloLogin;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;

import instancias.Instanciador;


@ManagedBean
@RequestScoped
public class BeanLogin implements Serializable {
	private static final long serialVersionUID = 1L;
	
private ModeloLogin mLogin;
    
    public BeanLogin() {
    	mLogin = Instanciador.getModeloLogin();
    }
    
    public void setModelo(ModeloLogin mLogin) {
        this.mLogin = mLogin;
    }
    
    public int getIdUsuario() {
        return mLogin.getUsuario().getIdUsuario();
    }

    public void setIdUsuario(int idUsuario) {
        this.mLogin.getUsuario().setIdUsuario(idUsuario);
    }

    public String getUsuario() {
    	if (mLogin.getRecuerdoSesion() != null) return mLogin.getRecuerdoSesion().split(";")[0];
        else return "";
    }

    public void setUsuario(String usuario) {
        this.mLogin.getUsuario().setUsuario(usuario);
    }

    public String getClave() {
    	if (mLogin.getRecuerdoSesion() != null) return mLogin.getRecuerdoSesion().split(";")[1];
        else return "";
    }

    public void setClave(String clave) {
        this.mLogin.getUsuario().setClave(clave);
    }

    public boolean isEstado() {
        return mLogin.getUsuario().isEstado();
    }

    public void setEstado(boolean estado) {
        this.mLogin.getUsuario().setEstado(estado);
    }

    public String getRol() {
        return mLogin.getUsuario().getRol();
    }

    public void setRol(String rol) {
        this.mLogin.getUsuario().setRol(rol);
    }
    
    public boolean isChckbxRecodarSeleccionado() {
		return mLogin.isChckbxRecodarSeleccionado();
	}

	public void setChckbxRecodarSeleccionado(boolean chckbxRecodarSeleccionado) {
		this.mLogin.setChckbxRecodarSeleccionado(chckbxRecodarSeleccionado);
	}
	
	public boolean isBttnLoginDeshabilitado() {
		return mLogin.isBttnLoginDeshabilitado();
	}

	public void setBttnLoginDeshabilitado(boolean bttnLoginDeshabilitado) {
		this.mLogin.setBttnLoginDeshabilitado(bttnLoginDeshabilitado);
	}

    public String login() {
    	return mLogin.login();
    }
    
    public String logout() {
    	return mLogin.logout();
    }
    
    public String showMensaje() {
        if (!mLogin.isDatosValido()) {
            return "Datos incorrectos o usuario no encontrado.\nNro Intentos: " + mLogin.getNumeroIntentos();
        } else {
            return "Inicio de Sesion exitoso.";
        }
    }

}