package examenesFisico;
import examenes.Examen;

public class ExamenFisico extends Examen {

    private double temperatura;
    private String PA;
    private double FR;
    private double SPO2;
    private double peso;
    private double talla;
    private double IMC;
    private double FC;
    private double perimetroAbdominal;

    public ExamenFisico() {
    }

    public ExamenFisico(double temperatura, String PA, double FR, double SPO2, double peso, double talla, double IMC, double FC, double perimetroAbdominal) {
        this.temperatura = temperatura;
        this.PA = PA;
        this.FR = FR;
        this.SPO2 = SPO2;
        this.peso = peso;
        this.talla = talla;
        this.IMC = IMC;
        this.FC = FC;
        this.perimetroAbdominal = perimetroAbdominal;
    }
        
    public double getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(double temperatura) {
        this.temperatura = temperatura;
    }

    public String getPA() {
        return PA;
    }

    public void setPA(String PA) {
        this.PA = PA;
    }

    public double getFR() {
        return FR;
    }

    public void setFR(double FR) {
        this.FR = FR;
    }

    public double getSPO2() {
        return SPO2;
    }

    public void setSPO2(double SPO2) {
        this.SPO2 = SPO2;
    }

    public double getPeso() {
        return peso;
    }

    public void setPeso(double peso) {
        this.peso = peso;
    }

    public double getTalla() {
        return talla;
    }

    public void setTalla(double talla) {
        this.talla = talla;
    }

    public double getIMC() {
        return IMC;
    }

    public void setIMC(double IMC) {
        this.IMC = IMC;
    }

    public double getFC() {
        return FC;
    }

    public void setFC(double FC) {
        this.FC = FC;
    }

    public double getPerimetroAbdominal() {
        return perimetroAbdominal;
    }

    public void setPerimetroAbdominal(double perimetroAbdominal) {
        this.perimetroAbdominal = perimetroAbdominal;
    }
}
