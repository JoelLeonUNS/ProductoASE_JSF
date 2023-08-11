/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package modelo;

import conexiones.SqlServerConexion;
import consultas.ConsultaMedica;
import examenes.Examen;
import examenesMedico.ExamenMedico;
import factoryDAO.DAOFactory;
import factoryDAO.SqlServerDAOFactory;
import historias.HistoriaClinica;
import java.sql.Connection;
import java.util.*;
import pacientes.Paciente;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import pacientes.Alumno;

/**
 *
 * @author ANGIE
 */
public class ModeloInforme {

    private DAOFactory dao;
    private ModeloConsulta mConsulta;
    private SqlServerConexion conexion;

    public ModeloInforme() {
        this.dao = new SqlServerDAOFactory();
        this.dao = new SqlServerDAOFactory();
        this.mConsulta = new ModeloConsulta();
    }

    public List<Object[]> obtenerInforme1() {
        List<Object[]> datosInforme1 = new ArrayList<>();

        for (HistoriaClinica historia : (List<HistoriaClinica>) dao.getHistoriaClinica().listed()) {
            int idPaciente = historia.getPaciente().getIdPaciente();
            Paciente paciente = (Paciente) dao.getPaciente().read(idPaciente);
            List<ConsultaMedica> consultas = mConsulta.obtenerConsultas(idPaciente);

            for (ConsultaMedica consulta : consultas) {
                List<ExamenMedico> examenes = (List<ExamenMedico>) dao.getExamenMedico().listed();
                for (Examen examen : examenes) {
                    if (examen instanceof ExamenMedico) {
                        ExamenMedico examenMedico = (ExamenMedico) examen;
                        if (examenMedico.getIdConsulta() == consulta.getIdConsulta()) {
                            Object[] datos = new Object[6];
                            datos[0] = consulta.getFecha();
                            datos[1] = paciente.getApellido() + " " + paciente.getNombre();
                            datos[2] = consulta.getEdad();
                            datos[3] = paciente.getSexo();
                            datos[4] = examenMedico.getDiagnostico();

                            datosInforme1.add(datos);
                        }
                    }
                }
            }
        }

        return datosInforme1;
    }

    public List<Object[]> obtenerInforme2() {
        Map<String, Integer[]> datosPorEscuela = new HashMap<>();

        for (HistoriaClinica historia : (List<HistoriaClinica>) dao.getHistoriaClinica().listed()) {
            Paciente paciente = historia.getPaciente();

            if (paciente instanceof Alumno) {
                Alumno alumno = (Alumno) paciente;
                String escuela = alumno.getEscuela().getNombre();
                String facultad = alumno.getEscuela().getFacultad(); // Obtener nombre de la facultad
                String escuelaConFacultad = escuela + " - " + facultad; // Combinar escuela y facultad
                String sexo = alumno.getSexo();

                datosPorEscuela.putIfAbsent(escuelaConFacultad, new Integer[]{0, 0});
                Integer[] generoData = datosPorEscuela.get(escuelaConFacultad);

                if (sexo.equals("M")) {
                    generoData[0]++;
                } else if (sexo.equals("F")) {
                    generoData[1]++;
                }
            }
        }

        List<Object[]> datosAlumnos = new ArrayList<>();
        for (String escuelaConFacultad : datosPorEscuela.keySet()) {
            Integer[] generoData = datosPorEscuela.get(escuelaConFacultad);
            String[] escuelaYFacultad = escuelaConFacultad.split(" - "); // Separar escuela y facultad
            Object[] datos = new Object[]{
                escuelaYFacultad[1], // Nombre de la escuela
                escuelaYFacultad[0], // Nombre de la facultad
                generoData[0], // Masculino
                generoData[1], // Femenino
                generoData[0] + generoData[1] // Total
            };
            datosAlumnos.add(datos);
        }

        return datosAlumnos;
    }

    public List<Object[]> obtenerInforme3() {
        List<Object[]> datosInforme3 = new ArrayList<>();
        try {
            Connection connection = SqlServerConexion.getInstance(); // Obtener la conexión desde tu clase SqlServerConexion

            String consultaSQL = "SELECT tipoPaciente, condicion, sexo, "
                    + "COUNT(*) AS cantidad, "
                    + "ROUND(COUNT(*) * 100.0 / SUM(COUNT(*)) OVER (PARTITION BY tipoPaciente, condicion), 2) AS porcentaje "
                    + "FROM ( "
                    + "SELECT CASE "
                    + "WHEN tipoPaciente = 'Alumno' THEN 'Alumno' "
                    + "WHEN tipoPaciente = 'Trabajador' THEN 'Trabajador' "
                    + "END AS tipoPaciente, "
                    + "CASE "
                    + "WHEN tipoPaciente = 'Alumno' THEN 'Pregrado' "
                    + "WHEN tipoPaciente = 'Trabajador' AND docente = 1 THEN 'Docente' "
                    + "WHEN tipoPaciente = 'Trabajador' AND docente = 0 THEN 'No Docente' "
                    + "ELSE 'Condición no aplicable' "
                    + "END AS condicion, "
                    + "sexo "
                    + "FROM Paciente) AS paciente_condicion "
                    + "GROUP BY tipoPaciente, condicion, sexo "
                    + "ORDER BY tipoPaciente, condicion, sexo";

            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(consultaSQL);

            while (resultSet.next()) {
                Object[] fila = new Object[5]; // Ajustar el tamaño según la cantidad de columnas

                fila[0] = resultSet.getString("tipoPaciente");
                fila[1] = resultSet.getString("condicion");
                fila[2] = resultSet.getString("sexo");
                fila[3] = resultSet.getInt("cantidad");
                fila[4] = resultSet.getString("porcentaje") + "%";

                datosInforme3.add(fila);
            }
            resultSet.close();
            statement.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "Error: " + e.getMessage(), "Error de conexión", JOptionPane.ERROR_MESSAGE);
        }
        return datosInforme3;
    }
}
