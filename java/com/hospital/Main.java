package com.hospital;

import com.hospital.application.CitaService;
import com.hospital.application.HorarioService;
import com.hospital.domain.Medico;
import com.hospital.infrastructure.controller.CitaController;
import com.hospital.infrastructure.repository.InMemoryCitaRepository;
import com.hospital.infrastructure.repository.InMemoryMedicoRepository;

import java.time.LocalDateTime;

public class Main {
    public static void main(String[] args) {
        System.out.println("🚀 Inicializando Sistema de Citas Hospitalarias...");
        
        // 1. Inicializar repositorios
        InMemoryCitaRepository citaRepository = new InMemoryCitaRepository();
        InMemoryMedicoRepository medicoRepository = new InMemoryMedicoRepository();
        
        // 2. Cargar datos de prueba
        cargarDatosPrueba(medicoRepository);
        
        // 3. Inicializar servicios
        CitaService citaService = new CitaService(citaRepository, medicoRepository);
        HorarioService horarioService = new HorarioService();
        
        // 4. Inicializar controlador
        CitaController controller = new CitaController(citaService, horarioService);
        
        // 5. Iniciar sistema
        controller.iniciarSistema();
    }
    
    private static void cargarDatosPrueba(InMemoryMedicoRepository medicoRepository) {
        System.out.println("📋 Cargando médicos de prueba...");
        
        Medico medico1 = new Medico("Dr. Carlos Mendoza", "Cardiología", "COL-12345", 15);
        Medico medico2 = new Medico("Dra. Ana López", "Pediatría", "COL-23456", 10);
        Medico medico3 = new Medico("Dr. José Ramírez", "Traumatología", "COL-34567", 20);
        Medico medico4 = new Medico("Dra. María Torres", "Dermatología", "COL-45678", 8);
        
        medicoRepository.guardar(medico1);
        medicoRepository.guardar(medico2);
        medicoRepository.guardar(medico3);
        medicoRepository.guardar(medico4);
        
        System.out.println("✅ " + medicoRepository.buscarTodos().size() + " médicos cargados.");
        System.out.println("\nMédicos disponibles:");
        medicoRepository.buscarTodos().forEach(m -> 
            System.out.println("  • " + m.getNombre() + " - " + m.getEspecialidad()));
    }
}