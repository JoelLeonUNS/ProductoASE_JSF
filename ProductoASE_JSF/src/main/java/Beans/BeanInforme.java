package Beans;

import java.util.Arrays;
import java.util.List;

public class BeanInforme {
	private String opcionSeleccionada;
	
    public List<String> getEncabezadosTabla() {
        if ("1".equals(opcionSeleccionada)) {
            return Arrays.asList("Fecha", "Apellidos y Nombres","Genero","Edad","Diagnostico");
        } else if ("2".equals(opcionSeleccionada)) {
            return Arrays.asList("Encabezado Columna A", "Encabezado Columna B");
        }
        return null;
    }
}
