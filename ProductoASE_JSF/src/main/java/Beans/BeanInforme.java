package Beans;

import java.io.IOException;
import java.io.Serializable;
import java.util.List;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;

import PDFGenerator.PDFGenerator;
import modelo.ModeloInforme;
@ManagedBean
@ViewScoped

public class BeanInforme implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String opcionSeleccionada;
	private ModeloInforme modeloInforme;
	private PDFGenerator pdf;
	private String nombreInforme;
	
    public BeanInforme() {
        modeloInforme = new ModeloInforme();
    }


    public String[] getEncabezadosTabla() {
        if ("1".equals(opcionSeleccionada)) {
            return new String[]{"Fecha", "Apellidos y Nombres", "Edad", "Genero", "Diagnostico"};
        } else if ("2".equals(opcionSeleccionada)) {
            return new String[]{"Facultad", "Escuela","Masculino","Femenino","Total"};
        }else if ("3".equals(opcionSeleccionada)) {
            return new String[]{"Paciente", "Condicion","Sexo","Cantidad","%"};
        }
        return null;
    }

    
    public List<Object[]> getDatosTabla() {
        if ("1".equals(opcionSeleccionada)) {
            return modeloInforme.obtenerInforme1();
        } else if ("2".equals(opcionSeleccionada)) {
            return modeloInforme.obtenerInforme2();
        }else if ("3".equals(opcionSeleccionada)) {
            return modeloInforme.obtenerInforme3();
        }
        return null;
    }
	public String getOpcionSeleccionada() {
		return opcionSeleccionada;
	}
	public void setOpcionSeleccionada(String opcionSeleccionada) {
		this.opcionSeleccionada = opcionSeleccionada;
	}
	public ModeloInforme getModeloInforme() {
		return modeloInforme;
	}
	public void setModeloInforme(ModeloInforme modeloInforme) {
		this.modeloInforme = modeloInforme;
	}
	
	public String getNombreInforme() {
		return nombreInforme;
	}

	public void setNombreInforme(String nombreInforme) {
		this.nombreInforme = nombreInforme;
	}
	
    public void generarInformePDF() {
        try {
            pdf.generarInformePDF(getDatosTabla(), getEncabezadosTabla(), nombreInforme);
            mostrarMensaje("PDF generado exitosamente: " + nombreInforme + ".pdf");
        } catch (IOException e) {
            mostrarMensaje("Error al generar el PDF: " + e.getMessage());
        }
    }

    private void mostrarMensaje(String mensaje) {
        FacesContext context = FacesContext.getCurrentInstance();
        context.addMessage(null, new FacesMessage(mensaje));
    }


}



