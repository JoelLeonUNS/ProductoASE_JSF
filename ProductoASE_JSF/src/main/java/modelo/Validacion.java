package modelo;

public class Validacion {

    private final String[] mensajeError = new String[4];

    public boolean checkCadena(String entrada) {
        boolean valido = true;
        if (!checkMayuscula(entrada)) {
            mensajeError[0] = "Error: Minimo debe contener una mayuscula.";
            valido |= false;
        }
        if (!checkMinuscula(entrada)) {
            mensajeError[1] = "Error: Minimo debe contener una minuscula.";
            valido |= false;
        }
        if (!checkCifra(entrada)) {
            mensajeError[2] = "Error: Minimo debe contener una cifra.";
            valido |= false;
        }
        if (!checkLongitud(entrada, 6)) {
            mensajeError[3] = "Error: La cantidad de caracteres debe ser minimo 6.";
            valido |= false;
        }
        return valido;
    }

    private boolean checkMinuscula(String entrada) {
        boolean valido = false;
        for (int i = 0; i < entrada.length(); i++) {
            valido |= (entrada.charAt(i)) == entrada.toLowerCase().charAt(i) && !Character.isDigit((entrada.charAt(i)));
        }
        return valido;
    }

    private boolean checkMayuscula(String entrada) {
        boolean valido = false;
        for (int i = 0; i < entrada.length(); i++) {
            valido |= (entrada.charAt(i)) == entrada.toUpperCase().charAt(i) && !Character.isDigit((entrada.charAt(i)));
        }
        return valido;
    }

    private boolean checkCifra(String entrada) {
        boolean valido = false;
        for (int i = 0; i < entrada.length(); i++) {
            valido |= Character.isDigit((entrada.charAt(i)));
        }
        return valido;
    }

    public boolean checkLongitud(String entrada, int longitud) {
        return entrada.length() >= longitud;
    }

    public String showErrorForm() {
        String mensaje = "<html>";
        for (String error : mensajeError) {
            mensaje += (error != null) ? (error + "<br>") : "" ;
        }
        mensaje += "</html>";
        return mensaje;
    }
    
    public String showErrorConsola() {
        String mensaje = "";
        for (String error : mensajeError) {
            mensaje += (error != null) ? (error + "\n") : "" ;
        }
        return mensaje;
    }
}
