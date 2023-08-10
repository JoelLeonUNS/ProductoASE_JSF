package historias;

import java.time.LocalDate;
import java.util.Scanner;
import pacientes.Paciente;

public abstract class HistoriaClinicaManager {
    static Scanner input = new Scanner (System.in); 
    
//    public HistoriaClinica buscar(String DNI) {
//        try {
//            System.out.println(BD.consultarHistoria(DNI));
//        } catch (Exception e) {
//        }
//        return BD.consultarHistoria(DNI);      
//    }
//    
//    public HistoriaClinica registrar() {
//        HistoriaClinica historiaClinica = new HistoriaClinica();
//        historiaClinica.setPaciente(crearPaciente());
//        setDatos(historiaClinica);
//        //ver que no haya repetido
//        BD.insertarHistoriaClinica(historiaClinica);
//        return historiaClinica;
//    }
    
    public abstract Paciente crearPaciente();
    
    public void setDatos(HistoriaClinica historia){
        System.out.print("DNI: ");
        historia.getPaciente().setDni(input.next());
        System.out.print("Nombre: ");
        historia.getPaciente().setNombre(input.next());
        System.out.print("Apellido: ");
        historia.getPaciente().setApellido(input.next());
        System.out.print("Sexo: ");
        historia.getPaciente().setSexo(input.next());
        System.out.print("Fecha de Nacimiento: ");
        historia.getPaciente().setFechaNac(LocalDate.parse(input.next()));
        System.out.print("Lugar de Nacimiento: ");
        historia.getPaciente().setLugarNac(input.next());
        System.out.print("Distrito: ");
        historia.getPaciente().setDistrito(input.next());
        System.out.print("Departamento: ");
        input.nextLine();
        historia.getPaciente().setDepartamento(input.nextLine());
        System.out.print("Direccion: ");
        historia.getPaciente().setDireccion(input.nextLine());
        System.out.print("Estado civil: ");
        historia.getPaciente().setEstadoCivil(input.next());
        System.out.print("Telefono: ");
        historia.getPaciente().setTelefono(input.next());
        System.out.print("Nombre de familiar: ");
        input.nextLine();
        ////historia.getPaciente().getFamiliar().setNombreFamiliar(input.nextLine());
        ////System.out.print("Direccion de familiar: ");
        ////historia.getPaciente().getFamiliar().setDireccionFamiliar(input.nextLine());
        ////System.out.print("Parentesco: ");
        ////historia.getPaciente().getFamiliar().setParentesco(input.next());
        ////System.out.print("Telefono de familiar: ");
        ////historia.getPaciente().getFamiliar().setTelefonoFamiliar(input.next());
        ////System.out.print("Antecedentes Patologios: ");
        ////input.nextLine();
        //historia.setAntecedentesPatologicos(input.nextLine());
        ////historia.setNumeroHistoriaClinica(Integer.parseInt(historia.getPaciente().getDNI() + historia.getPaciente().getTelefono()));
    }

    public void editar(HistoriaClinica historia){
        switch(opcionesEdicion()){
            case 1 -> {
                System.out.print("Ingrese nueva dirección del paciente: ");
                historia.getPaciente().setDireccion(input.nextLine());
            }
            case 2 -> {
                System.out.print("Ingrese nuevo teléfono del paciente: ");
                historia.getPaciente().setTelefono(input.next());
            }
            case 3 -> {
                System.out.print("Ingrese nueva estado civil: ");
                historia.getPaciente().setEstadoCivil(input.next());
            }
            case 4 -> {
                System.out.print("Ingrese nueva dirección del familiar: ");
                //historia.getPaciente().getFamiliar().setDireccionFamiliar(input.nextLine());
            }
            case 5 -> {
                System.out.print("Ingrese nuevo teléfono del familiar: ");
               //historia.getPaciente().getFamiliar().setTelefonoFamiliar(input.next());
            }
            case 6 -> {
                System.out.println("Ingrese antecedente (familiar, enfermedad): ");
                //historia.agregarAntecedenteFamiliar(input.nextLine());
            }
        }
        input.nextLine();
        System.out.println("Cambio realizado");
    }
    
    public int opcionesEdicion(){
        System.out.println("Opciones a editar");
        System.out.println("1. Dirección de paciente");
        System.out.println("2. Teléfono de paciente");
        System.out.println("3. Estado civil");
        System.out.println("4. Dirección de familiar");
        System.out.println("5. Teléfono de familiar");
        System.out.println("6. Agregar antecedentes familiares");
        System.out.print("Seleccione: ");
        int opcion = input.nextInt();
        return opcion;
    }
    
}
