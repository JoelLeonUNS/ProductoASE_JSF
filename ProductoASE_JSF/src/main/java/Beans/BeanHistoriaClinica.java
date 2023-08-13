package Beans;

import java.io.Serializable;
import java.util.ArrayList;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import historias.HistoriaClinica;
import instancias.Instanciador;
import modelo.ModeloFamiliar;
import modelo.ModeloHistoriaClinica;

@ManagedBean
@RequestScoped
public class BeanHistoriaClinica  implements Serializable {
	private static final long serialVersionUID = 1L;
	
	ModeloHistoriaClinica mHistoriaClinica;
	ModeloFamiliar mFamiliar;
	String valorBuscado;
	ArrayList<HistoriaClinica> historiasClinicas;
	
	public BeanHistoriaClinica() {
		mHistoriaClinica = Instanciador.getModeloHistoriaClinica();
		mFamiliar = Instanciador.getModeloFamiliar();
	}
	
	public String getValorBuscado() {
		return valorBuscado;
	}

	public void setValorBuscado(String valorBuscado) {
		this.valorBuscado = valorBuscado;
	}
	
	public ArrayList<HistoriaClinica> getHistoriasClinicas() {
		return historiasClinicas;
	}

	public void setHistoriasClinicas(ArrayList<HistoriaClinica> historiasClinicas) {
		this.historiasClinicas = historiasClinicas;
	}

	public void buscarHistoriaClinicaCoincidente(){
        mHistoriaClinica.buscarHistoriaCoincidente(valorBuscado);
        historiasClinicas = mHistoriaClinica.getHistoriasCoincidentes();
    }
	
	public HistoriaClinica getHistoriaClinica() {
        return mHistoriaClinica.getHistoriaClinica();
    }

    public void setHistoriaClinica(HistoriaClinica historiaClinica) {
        this.mHistoriaClinica.setHistoriaClinica(historiaClinica);
    }
	
	
}
