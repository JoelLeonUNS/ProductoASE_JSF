package Beans;

import java.io.Serializable;
import modelo.ModeloLogin;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;


@ManagedBean
@ViewScoped
public class BeanLogin implements Serializable {
	private static final long serialVersionUID = 1L;
	
private ModeloLogin mUsuario;
    
    public BeanLogin() {
    	mUsuario = new ModeloLogin();
    }
    
    public void setModelo(ModeloLogin mUsuario) {
        this.mUsuario = mUsuario;
    }
    
    public int getIdUsuario() {
        return mUsuario.getUsuario().getIdUsuario();
    }

    public void setIdUsuario(int idUsuario) {
        this.mUsuario.getUsuario().setIdUsuario(idUsuario);
    }

    public String getUsuario() {
        return mUsuario.getUsuario().getUsuario();
    }

    public void setUsuario(String usuario) {
        this.mUsuario.getUsuario().setUsuario(usuario);
    }

    public String getClave() {
        return mUsuario.getUsuario().getClave();
    }

    public void setClave(String clave) {
        this.mUsuario.getUsuario().setClave(clave);
    }

    public boolean isEstado() {
        return mUsuario.getUsuario().isEstado();
    }

    public void setEstado(boolean estado) {
        this.mUsuario.getUsuario().setEstado(estado);
    }

    public String getRol() {
        return mUsuario.getUsuario().getRol();
    }

    public void setRol(String rol) {
        this.mUsuario.getUsuario().setRol(rol);
    }

    public String getUsuarioRecordado() {
        if (mUsuario.getRecuerdoSesion() != null) return mUsuario.getRecuerdoSesion().split(";")[0];
        else return "";
    }

    public String getClaveRecordada() {
        if (mUsuario.getRecuerdoSesion() != null) return mUsuario.getRecuerdoSesion().split(";")[1];
        else return "";
    }
    
    public void iniciarSesion() {
        mUsuario.iniciarSesion();
    }
    
    public String login() {
    	return mUsuario.login();
    }

    public void recordarSesion() {
        mUsuario.recordarSesion();
    }
    
    public void noRecordarSesion() {
        mUsuario.setRecuerdoSesion(null);
    }

    public boolean isRecordado() {
        mUsuario.setRecuerdoSesion(mUsuario.getUsuario().getUsuario() + ";" + mUsuario.getUsuario().getClave());
        return mUsuario.getCareTaker().isExiste(mUsuario.guardarSesion());
    }
    
    public void cerrarSesion() {
        try {
            mUsuario.cerrarSesion();
        } catch (Exception e) {
        }
    }
    
    public void resetNumeroIntentos() {
        mUsuario.resetNumeroIntentos();
    }

    public boolean isAcceso() {
        return mUsuario.isDatosValido() && mUsuario.getNumeroIntentos() < 3;
    }
    
    public boolean isHabilitado() {
        return mUsuario.isHabilitado();
    }

    public boolean isBloqueado() {
        return mUsuario.getNumeroIntentos() > 2;
    }

    public String showMensaje() {
        if (!mUsuario.isDatosValido()) {
            return "Datos incorrectos o usuario no encontrado.\nNro Intentos: " + mUsuario.getNumeroIntentos();
        } else {
            return "Inicio de Sesion exitoso.";
        }
    }

}