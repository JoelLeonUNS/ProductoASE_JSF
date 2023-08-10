package modelo;

import consultas.ConsultaMedica;
import factoryDAO.DAOFactory;
import factoryDAO.SqlServerDAOFactory;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class ModeloConsulta {
    private DAOFactory dao;
    private ConsultaMedica consulta;
    private int idConsulta;

    public ModeloConsulta() {
        this.consulta = new ConsultaMedica();
        this.dao = new SqlServerDAOFactory();
    }

    public void setDatosConsulta(LocalDate fecha, LocalTime hora, int edad, String tiempoEnfermedad, String apetito, String sueño, String sed, String estadoAnimo, String motivo) {
        consulta.setFecha(fecha);
        consulta.setHora(hora);
        consulta.setEdad(edad);
        consulta.setTiempoEnfermedad(tiempoEnfermedad);
        consulta.setApetito(apetito);
        consulta.setSueño(sueño);
        consulta.setEstadoAnimo(estadoAnimo);
        consulta.setSed(sed);
        consulta.setMotivo(motivo);
    }

    public ConsultaMedica getConsulta() {
        return consulta;
    }

    public void setConsulta(ConsultaMedica consulta) {
        this.consulta = consulta;
    }

    public int getIdConsulta() {
        return idConsulta;
    }

    public void setIdConsulta(int idConsulta) {
        this.idConsulta = idConsulta;
    }
    
    public void tipoExamen(ConsultaMedica consulta){
        
    }
    
    public void añadirConsulta(){
        dao.getConsulta().create(consulta);
    }
    
    public ArrayList<ConsultaMedica> obtenerConsultas(int idHistoria){
        ArrayList<ConsultaMedica> consultas = new ArrayList();
        for(ConsultaMedica consultaBD: (List<ConsultaMedica>)dao.getConsulta().listed()) {
            if (consultaBD.getIdHistoria()==idHistoria) {
                consultas.add(consultaBD);
            }
        }
        return consultas;
    }

}
