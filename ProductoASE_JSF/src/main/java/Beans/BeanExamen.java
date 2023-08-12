package Beans;

import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalTime;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import examenes.Examen;
import examenesMedico.ExamenMedico;
import examenesClinico.ExamenClinico;
import examenesFisico.ExamenFisico;
import modelo.ModeloConsulta;
import modelo.ModeloExamen;
import modelo.ModeloHistoriaClinica;

@ManagedBean
@ViewScoped
public class BeanExamen implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private ModeloExamen modeloExamen;
    private ModeloConsulta modeloConsulta;
    private ModeloHistoriaClinica modeloHistoriaClinica;

    public BeanExamen() {
    	modeloExamen = new ModeloExamen();
    	modeloConsulta = new ModeloConsulta();
    	modeloHistoriaClinica = new ModeloHistoriaClinica();
    }
    
    /*public void cambiarTipoExamen(JPanel base, JPanel siguiente) {
        siguiente.setSize(764, 250);
        siguiente.setLocation(0, 0);
        base.removeAll();
        base.add(siguiente, BorderLayout.CENTER);
        base.revalidate();
        base.repaint();
    }*/

    public ModeloExamen getModeloExamen() {
        return modeloExamen;
    }

    public void setModeloExamen(ModeloExamen modeloExamen) {
        this.modeloExamen = modeloExamen;
    }

    public ModeloConsulta getModeloConsulta() {
        return modeloConsulta;
    }

    public void setModeloConsulta(ModeloConsulta modeloConsulta) {
        this.modeloConsulta = modeloConsulta;
    }

    public ModeloHistoriaClinica getModeloHistoriaClinica() {
        return modeloHistoriaClinica;
    }

    public void setModeloHistoriaClinica(ModeloHistoriaClinica modeloHistoriaClinica) {
        this.modeloHistoriaClinica = modeloHistoriaClinica;
    }
    
    public void setTipoExamen(String tipoExamen){
        modeloExamen.setTipoExamen(tipoExamen);
    }
    
    public String getTipoExamen() {
    	return modeloExamen.getExamenTmp();
    }
    
    public int getIdExamen() {
    	return modeloExamen.getExamen().getIdExamen();
    }
    
    public void setIdExamen(int id) {
    	modeloExamen.getExamen().setIdExamen(id);
    }
    
    public int getIdConsulta() {
    	return modeloExamen.getExamen().getIdConsulta();
    }
    
    public void setIdConsulta(int id) {
    	modeloExamen.getExamen().setIdConsulta(id);
    }
    
    public String getObservacion() {
    	return modeloExamen.getExamen().getObservacion();
    }
    
    public void setObservacion(String observacion) {
    	modeloExamen.getExamen().setObservacion(observacion);
    }
    
    //EXAMEN MEDICO
    public String getDiagnostico() {
    	return ((ExamenMedico) modeloExamen.getExamen()).getDiagnostico();
    }
    
    public void setDiagnostico(String diagnostico) {
    	((ExamenMedico) modeloExamen.getExamen()).setDiagnostico(diagnostico);
    }
    
    public String getTratamiento() {
    	return ((ExamenMedico) modeloExamen.getExamen()).getTratamiento();
    }
    
    public void setTratamiento(String tratamiento) {
    	((ExamenMedico) modeloExamen.getExamen()).setTratamiento(tratamiento);
    }
    
    public String getExamenesAux() {
    	return ((ExamenMedico) modeloExamen.getExamen()).getExamenesAux();
    }
    
    public void setExamenesAux(String tratamiento) {
    	((ExamenMedico) modeloExamen.getExamen()).setExamenesAux(tratamiento);
    }
    
    //EXAMEN CLINICO
    
    public boolean isAPCardiovascular() {
    	return ((ExamenClinico) modeloExamen.getExamen()).isAPCardiovascular();
    }
    
    public void setAPCardiovascular(boolean ap) {
    	((ExamenClinico) modeloExamen.getExamen()).setAPCardiovascular(ap);
    }
    
    public boolean isAPRespiratorio() {
    	return ((ExamenClinico) modeloExamen.getExamen()).isAPRespiratorio();
    }
    
    public void setAPRespiratorio(boolean ap) {
    	((ExamenClinico) modeloExamen.getExamen()).setAPRespiratorio(ap);
    }
    
    //EXAMEN FISICO
    
    public double getTemperatura() {
    	return ((ExamenFisico) modeloExamen.getExamen()).getTemperatura();
    }
    
    public void setTemperatura(double valor) {
    	((ExamenFisico) modeloExamen.getExamen()).setTemperatura(valor);
    }
    
    public String getPA() {
    	return ((ExamenFisico) modeloExamen.getExamen()).getPA();
    }
    
    public void setPA(String valor) {
    	((ExamenFisico) modeloExamen.getExamen()).setPA(valor);
    }
    
    public double getFR() {
    	return ((ExamenFisico) modeloExamen.getExamen()).getFR();
    }
    
    public void setFR(double valor) {
    	((ExamenFisico) modeloExamen.getExamen()).setFR(valor);
    }
    
    public double getSPO2() {
    	return ((ExamenFisico) modeloExamen.getExamen()).getSPO2();
    }
    
    public void setSPO2(double valor) {
    	((ExamenFisico) modeloExamen.getExamen()).setSPO2(valor);
    }
    
    public double getPeso() {
    	return ((ExamenFisico) modeloExamen.getExamen()).getPeso();
    }
    
    public void setPeso(double valor) {
    	((ExamenFisico) modeloExamen.getExamen()).setPeso(valor);
    }
    
    public double getTalla() {
    	return ((ExamenFisico) modeloExamen.getExamen()).getTalla();
    }
    
    public void setTalla(double valor) {
    	((ExamenFisico) modeloExamen.getExamen()).setTalla(valor);
    }
    
    public double getIMC() {
    	return ((ExamenFisico) modeloExamen.getExamen()).getIMC();
    }
    
    public void setIMC(double valor) {
    	((ExamenFisico) modeloExamen.getExamen()).setIMC(valor);
    }
    
    public double getFC() {
    	return ((ExamenFisico) modeloExamen.getExamen()).getFC();
    }
    
    public void setFC(double valor) {
    	((ExamenFisico) modeloExamen.getExamen()).setFC(valor);
    }
    
    public double getPerimetroAbdominal() {
    	return ((ExamenFisico) modeloExamen.getExamen()).getPerimetroAbdominal();
    }
    
    public void setPerimetroAbdominal(double valor) {
    	((ExamenFisico) modeloExamen.getExamen()).setPerimetroAbdominal(valor);
    }
    
    //CONSULTA
    public LocalDate getFecha() {
    	return modeloConsulta.getConsulta().getFecha();
    }
    
    public void setFecha(LocalDate fecha) {
    	modeloConsulta.getConsulta().setFecha(fecha);
    }
    
    public LocalTime getHora() {
    	return modeloConsulta.getConsulta().getHora();
    }
    
    public void setHora(LocalTime hora) {
    	modeloConsulta.getConsulta().setHora(hora);
    }
    
    public int getEdad() {
    	return modeloConsulta.getConsulta().getEdad();
    }
    
    public void setEdad(int edad) {
    	modeloConsulta.getConsulta().setEdad(edad);
    }
    
    public String getTiempoEnfermedad() {
    	return modeloConsulta.getConsulta().getTiempoEnfermedad();
    }
    
    public void setTiempoEnfermedad(String tiempo) {
    	modeloConsulta.getConsulta().setTiempoEnfermedad(tiempo);
    }
    
    public String getApetito() {
    	return modeloConsulta.getConsulta().getApetito();
    }
    
    public void setApetito(String apetito) {
    	modeloConsulta.getConsulta().setApetito(apetito);
    }
    
    public String getSueño() {
    	return modeloConsulta.getConsulta().getSueño();
    }
    
    public void setSueño(String sueño) {
    	modeloConsulta.getConsulta().setSueño(sueño);
    }
    
    public String getSed() {
    	return modeloConsulta.getConsulta().getSed();
    }
    
    public void setSed(String sed) {
    	modeloConsulta.getConsulta().setSed(sed);
    }
    
    public String getEstadoAnimo() {
    	return modeloConsulta.getConsulta().getEstadoAnimo();
    }
    
    public void setEstadoAnimo(String animo) {
    	modeloConsulta.getConsulta().setEstadoAnimo(animo);
    }
    
    public String getMotivo() {
    	return modeloConsulta.getConsulta().getMotivo();
    }
    
    public void setMotivo(String motivo) {
    	modeloConsulta.getConsulta().setMotivo(motivo);
    }
    
    public void setDatosExamenMedico(String diagnostico, String tratamiento, String examAux, String observacion){
        modeloExamen.setDatosExamenMedico(diagnostico, tratamiento, examAux, observacion);
    }
    
    public void setDatosExamenClinico(String observacion, boolean apCardio, boolean apRespiratorio){
        modeloExamen.setDatosExamenClinico(observacion, apCardio, apRespiratorio);
    }
    
    public void setDatosExamenFisico(String observacion, double temp, String PA, double FR, double SPO2, double peso, double talla, double IMC, double FC, double perAbdominal){
        modeloExamen.setDatosExamenFisico(observacion, temp, PA, FR, SPO2, peso, talla, IMC, FC, perAbdominal);
    }
    
    public void setDatosConculta(LocalDate fecha, LocalTime hora, int edad, String tiempoEnfermedad, String apetito, String sueño, String sed, String estadoAnimo, String motivo){
        modeloConsulta.setDatosConsulta(fecha, hora, edad, tiempoEnfermedad, apetito, sueño, sed, estadoAnimo, motivo);
    }
    
    public void añadirConsultaAHistoria(int idMedico){
        modeloConsulta.getConsulta().setIdHistoria(modeloHistoriaClinica.getHistoriaClinica().getIdHistoriaClinica());
        modeloConsulta.getConsulta().getAtendidoPor().setIdMedico(idMedico);
        modeloConsulta.añadirConsulta();
    }
    
    public void añadirExamenAConsulta(){
        for (Examen examen : modeloConsulta.getConsulta().getExamenes()) {
            examen.setIdConsulta(modeloConsulta.getConsulta().getIdConsulta());
            modeloExamen.añadirExamen(examen);
        }
    }
    
    public void añadirExamen(){
        modeloConsulta.getConsulta().agregarExamen(modeloExamen.getExamen());
    }
    
    public void iniciarExamen(){
        modeloExamen.setExamen(new Examen());
    }
    
    public void setConsultasAHistoria(){
        modeloHistoriaClinica.getHistoriaClinica().setConsultasMedicas(modeloConsulta.obtenerConsultas(modeloHistoriaClinica.getHistoriaClinica().getIdHistoriaClinica()));
    }
    
    public void setExamenesAConsulta(){
        modeloConsulta.getConsulta().setExamenes(modeloExamen.obtenerExamenes(modeloConsulta.getConsulta().getIdConsulta()));
    }

}
