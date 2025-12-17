package com.hospital.infrastructure.controller;

import com.hospital.application.CitaService;
import com.hospital.application.HorarioService;
import com.hospital.domain.*;
import com.hospital.exceptions.HorarioNoDisponibleException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Scanner;

public class CitaController {
    private final CitaService citaService;
    private final HorarioService horarioService;
    private final Scanner scanner;
    
    public CitaController(CitaService citaService, HorarioService horarioService) {
        this.citaService = citaService;
        this.horarioService = horarioService;
        this.scanner = new Scanner(System.in);
    }
    
    public void iniciarSistema() {
        System.out.println("====================================");
        System.out.println("   SISTEMA DE CITAS HOSPITALARIAS   ");
        System.out.println("====================================");
        
        boolean continuar = true;
        while (continuar) {
            mostrarMenu();
            int opcion = leerOpcion();
            continuar = procesarOpcion(opcion);
        }
        
        System.out.println("Gracias por usar el sistema. ¡Hasta pronto!");
        scanner.close();
    }
    
    private void mostrarMenu() {
        System.out.println("\n--- MENÚ PRINCIPAL ---");
        System.out.println("1. Registrar nuevo paciente");
        System.out.println("2. Buscar médicos por especialidad");
        System.out.println("3. Solicitar cita médica");
        System.out.println("4. Generar ticket de cita");
        System.out.println("5. Ver mis citas");
        System.out.println("6. Cancelar cita");
        System.out.println("7. Salir");
        System.out.print("Seleccione una opción: ");
    }
    
    private int leerOpcion() {
        try {
            return Integer.parseInt(scanner.nextLine());
        } catch (NumberFormatException e) {
            return -1;
        }
    }
    
    private boolean procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                registrarPaciente();
                break;
            case 2:
                buscarMedicos();
                break;
            case 3:
                solicitarCita();
                break;
            case 4:
                generarTicket();
                break;
            case 5:
                verCitas();
                break;
            case 6:
                cancelarCita();
                break;
            case 7:
                return false;
            default:
                System.out.println("Opción inválida. Intente nuevamente.");
        }
        return true;
    }
    
    private void registrarPaciente() {
        System.out.println("\n--- REGISTRO DE PACIENTE ---");
        System.out.print("Nombre completo: ");
        String nombre = scanner.nextLine();
        System.out.print("DNI: ");
        String dni = scanner.nextLine();
        System.out.print("Teléfono: ");
        String telefono = scanner.nextLine();
        System.out.print("Email: ");
        String email = scanner.nextLine();
        System.out.print("Edad: ");
        int edad = Integer.parseInt(scanner.nextLine());
        
        Paciente paciente = new Paciente(nombre, dni, telefono, email, edad);
        System.out.println("✅ Paciente registrado: " + paciente);
    }
    
    private void buscarMedicos() {
        System.out.println("\n--- BUSCAR MÉDICOS ---");
        System.out.print("Especialidad (ej: Cardiología, Pediatría, etc.): ");
        String especialidad = scanner.nextLine();
        
        List<Medico> medicos = citaService.buscarMedicosPorEspecialidad(especialidad);
        
        if (medicos.isEmpty()) {
            System.out.println("No se encontraron médicos con esa especialidad.");
        } else {
            System.out.println("Médicos encontrados (" + especialidad + "):");
            for (int i = 0; i < medicos.size(); i++) {
                Medico m = medicos.get(i);
                System.out.println((i + 1) + ". " + m.getNombre() + " - " + 
                                 m.getAñosExperiencia() + " años de experiencia");
            }
        }
    }
    
    private void solicitarCita() {
        try {
            System.out.println("\n--- SOLICITUD DE CITA ---");
            
            // Datos del paciente (simulado)
            System.out.print("Ingrese DNI del paciente: ");
            String dni = scanner.nextLine();
            
            // Crear paciente simulado (en un sistema real, se buscaría en BD)
            Paciente paciente = new Paciente("Paciente Ejemplo", dni, "999888777", "paciente@ejemplo.com", 30);
            
            System.out.print("ID del médico: ");
            String medicoId = scanner.nextLine();
            
            System.out.print("Fecha y hora (dd/MM/yyyy HH:mm): ");
            String fechaStr = scanner.nextLine();
            LocalDateTime fechaHora = LocalDateTime.parse(fechaStr, 
                DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm"));
            
            System.out.print("Motivo de la consulta: ");
            String motivo = scanner.nextLine();
            
            System.out.print("Consultorio: ");
            String consultorio = scanner.nextLine();
            
            Cita cita = citaService.solicitarCita(paciente, medicoId, fechaHora, motivo, consultorio);
            System.out.println("✅ Cita creada exitosamente:");
            System.out.println(cita);
            
        } catch (HorarioNoDisponibleException e) {
            System.out.println("❌ Error: " + e.getMessage());
        } catch (Exception e) {
            System.out.println("❌ Error inesperado: " + e.getMessage());
        }
    }
    
    private void generarTicket() {
        try {
            System.out.println("\n--- GENERAR TICKET ---");
            System.out.print("ID de la cita: ");
            String citaId = scanner.nextLine();
            
            Ticket ticket = citaService.generarTicket(citaId);
            System.out.println("✅ Ticket generado:");
            ticket.imprimir();
            
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
    
    private void verCitas() {
        System.out.println("\n--- MIS CITAS ---");
        System.out.print("ID del paciente: ");
        String pacienteId = scanner.nextLine();
        
        List<Cita> citas = citaService.obtenerCitasPorPaciente(pacienteId);
        
        if (citas.isEmpty()) {
            System.out.println("No tiene citas programadas.");
        } else {
            System.out.println("Citas encontradas: " + citas.size());
            for (Cita cita : citas) {
                System.out.println("• " + cita);
            }
        }
    }
    
    private void cancelarCita() {
        try {
            System.out.println("\n--- CANCELAR CITA ---");
            System.out.print("ID de la cita a cancelar: ");
            String citaId = scanner.nextLine();
            
            citaService.cancelarCita(citaId);
            System.out.println("✅ Cita cancelada exitosamente.");
            
        } catch (Exception e) {
            System.out.println("❌ Error: " + e.getMessage());
        }
    }
}