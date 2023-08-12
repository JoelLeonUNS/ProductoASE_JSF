package instancias;

import modelo.ModeloConsulta;
import modelo.ModeloExamen;
import modelo.ModeloFamiliar;
import modelo.ModeloHistoriaClinica;
import modelo.ModeloInforme;
import modelo.ModeloLogin;
import modelo.ModeloMedico;

public class Instanciador {
	private static ModeloConsulta mConsulta;
	private static ModeloExamen mExamen;
	private static ModeloFamiliar mFamiliar;
	private static ModeloHistoriaClinica mHistoriaClinica;
	private static ModeloInforme mInforme;
	private static ModeloLogin mLogin;
	private static ModeloMedico mMedico;
		
	public static ModeloConsulta getModeloConsulta() {
		if (mConsulta == null) {
			mConsulta = new ModeloConsulta();
		}
		return mConsulta;
	}
	
	public static ModeloExamen getModeloExamen() {
		if (mExamen == null) {
			mExamen = new ModeloExamen();
		}
		return mExamen;
	}
	
	public static ModeloFamiliar getModeloFamiliar() {
		if (mFamiliar == null) {
			mFamiliar = new ModeloFamiliar();
		}
		return mFamiliar;
	}
	
	public static ModeloHistoriaClinica getModeloHistoriaClinica() {
		if (mHistoriaClinica == null) {
			mHistoriaClinica = new ModeloHistoriaClinica();
		}
		return mHistoriaClinica;
	}
	
	public static ModeloInforme getModeloInforme() {
		if (mInforme == null) {
			mInforme = new ModeloInforme();
		}
		return mInforme;
	}
	
	public static ModeloLogin getModeloLogin() {
		if (mLogin == null) {
			mLogin = new ModeloLogin();
		}
		return mLogin;
	}
	
	public static ModeloMedico getModeloMedico() {
		if (mMedico == null) {
			mMedico = new ModeloMedico();
		}
		return mMedico;
	}
}