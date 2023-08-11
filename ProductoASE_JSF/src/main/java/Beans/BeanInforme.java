package Beans;

import java.util.Arrays;
import java.util.List;
import modelo.ModeloInforme;


public class BeanInforme {
	private String opcionSeleccionada;
	private ModeloInforme modeloInforme;
	
    public List<String> getEncabezadosTabla() {
        if ("1".equals(opcionSeleccionada)) {
            return Arrays.asList("Fecha", "Apellidos y Nombres","Edad","Genero","Diagnostico");
        } else if ("2".equals(opcionSeleccionada)) {
            return Arrays.asList("Encabezado Columna ", "Encabezado Columna B");
        }
        return null;
    }
    public List<Object[]> getDatosTabla() {
        if ("1".equals(opcionSeleccionada)) {
            return modeloInforme.obtenerInforme1();
        } else if ("2".equals(opcionSeleccionada)) {
            return modeloInforme.obtenerInforme2();
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
    
}
