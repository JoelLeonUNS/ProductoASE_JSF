package Beans;

import modelo.ModeloMedico;
import java.io.Serializable;
import java.util.ArrayList;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.event.ActionEvent;

import medicos.Medico;

@ManagedBean
@ViewScoped
public class BeanInterfazAdmin implements Serializable {
	private static final long serialVersionUID = 1L;
    private ModeloMedico modelo;
    private String tipoGuardado;
    private String valorBuscado;

    public BeanInterfazAdmin() {
       
    }

    public ModeloMedico getModelo() {
        return modelo;
    }
    
    public void setModelo(ModeloMedico modelo) {
         this.modelo = modelo;
    }

    public String getTipoGuardado() {
        return tipoGuardado;
    }

    public void setTipoGuardado(String tipoGuardado) {
        this.tipoGuardado = tipoGuardado;
    }
    
    //Añadidos
    public int getIdMedico(){
        return modelo.getMedico().getIdMedico();
    }
    
    public void setIdMedico(int id){
        modelo.getMedico().setIdMedico(id);
    }
    
    public String getDNI(){
        return modelo.getMedico().getDNI();
    }
    
    public void setDNI(String dni){
        modelo.getMedico().setDNI(dni);
    }
    
    public String getNombreMedico(){
        return modelo.getMedico().getNombreMedico();
    }
    
    public void setNombreMedico(String nombre){
        modelo.getMedico().setNombreMedico(nombre);
    }
    
    public String getApellidoMedico(){
        return modelo.getMedico().getApellidoMedico();
    }
    
    public void setApellidoMedico(String apellido){
        modelo.getMedico().setApellidoMedico(apellido);
    }
    
    public String getTelefonoMedico(){
        return modelo.getMedico().getTelefonoMedico();
    }
    
    public void setTelefonoMedico(String telefono){
        modelo.getMedico().setTelefonoMedico(telefono);
    }
    
    public String getUsuario(){
        return modelo.getMedico().getUsuario().getUsuario();
    }
    
    public void setUsuario(String usuario){
        modelo.getMedico().getUsuario().setUsuario(usuario);
    }
    
    public String getClave(){
        return modelo.getMedico().getUsuario().getClave();
    }
    
    public void setClave(String clave){
        modelo.getMedico().getUsuario().setClave(clave);
    }
    
    public boolean isEstado() {
        return modelo.getMedico().getUsuario().isEstado();
    }

    public void setEstado(boolean estado) {
        this.modelo.getMedico().getUsuario().setEstado(estado);
    }

    public String getRol() {
        return modelo.getMedico().getUsuario().getRol();
    }

    public void setRol(String rol) {
        this.modelo.getMedico().getUsuario().setRol(rol);
    }

    public String getValorBuscado() {
        return valorBuscado;
    }

    public void setValorBuscado(String valorBuscado) {
        this.valorBuscado = valorBuscado;
    }
    
    
    //Metodos Actuales
    public void setDatosMedico(String dni, String nombre, String apellidos, String telefono){
        modelo.setDatosMedico(dni, nombre, apellidos, telefono);
    }
    
    public void setDatosUsuario(String usuario, String clave){
        modelo.setDatosUsuario(usuario, clave);
    }
    
    public Medico buscarMedicoDNI(String dni){
        return modelo.buscarMedicoDNI(dni);
    }
    
    public ArrayList<Medico> buscarMedicoCoincidente(String cadena){
        modelo.buscarMedicoCoincidente(cadena);
        return modelo.getMedicosCoincidentesBD();
    }
    
    public void registrar(){
        modelo.registrar();
    }
    
    public void editar(){
        modelo.editar();
    }
    
    public void guardar(ActionEvent event) {
    	if(tipoGuardado.equals("CREAR")) {
    		registrar();
    	}else if(tipoGuardado.equals("EDITAR")) {
    		editar();
    	}
    }
    
    public void desactivarCuenta(ActionEvent event){
        modelo.darDeBaja();
    }
    
    
    public void nuevoMedico() {
        modelo.setMedico(new Medico());
    }
}
