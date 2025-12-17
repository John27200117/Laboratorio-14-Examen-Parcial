package com.hospital.application;

import com.hospital.domain.*;
import com.hospital.exceptions.HorarioNoDisponibleException;
import com.hospital.infrastructure.repository.CitaRepository;
import com.hospital.infrastructure.repository.MedicoRepository;
import java.time.LocalDateTime;
import java.util.List;

public class CitaService {
    private final CitaRepository citaRepository;
    private final MedicoRepository medicoRepository;

    public CitaService(CitaRepository citaRepository, MedicoRepository medicoRepository) {
        this.citaRepository = citaRepository;
        this.medicoRepository = medicoRepository;
    }

    public Cita solicitarCita(Paciente paciente, String medicoId, LocalDateTime fechaHora, 
                              String motivo, String consultorio) throws HorarioNoDisponibleException {
        
        // Validar que el médico existe
        var medicoOpt = medicoRepository.buscarPorId(medicoId);
        if (medicoOpt.isEmpty()) {
            throw new IllegalArgumentException("Médico no encontrado");
        }
        
        // Validar que el horario esté disponible
        if (citaRepository.existeCitaEnHorario(medicoId, fechaHora)) {
            throw new HorarioNoDisponibleException("El médico ya tiene una cita en ese horario");
        }
        
        // Crear la cita
        Medico medico = medicoOpt.get();
        Cita cita = new Cita(paciente, medico, fechaHora, motivo, consultorio);
        
        // Guardar en repositorio
        return citaRepository.guardar(cita);
    }

    public Ticket generarTicket(String citaId) throws Exception {
        var citaOpt = citaRepository.buscarPorId(citaId);
        if (citaOpt.isEmpty()) {
            throw new Exception("Cita no encontrada");
        }
        
        Cita cita = citaOpt.get();
        return new Ticket(cita);
    }

    public List<Cita> obtenerCitasPorPaciente(String pacienteId) {
        return citaRepository.buscarPorPaciente(pacienteId);
    }

    public List<Cita> obtenerCitasPorMedico(String medicoId) {
        return citaRepository.buscarPorMedico(medicoId);
    }

    public List<Medico> buscarMedicosPorEspecialidad(String especialidad) {
        return medicoRepository.buscarPorEspecialidad(especialidad);
    }

    public void cancelarCita(String citaId) throws Exception {
        var citaOpt = citaRepository.buscarPorId(citaId);
        if (citaOpt.isEmpty()) {
            throw new Exception("Cita no encontrada");
        }
        
        Cita cita = citaOpt.get();
        cita.cancelar();
        citaRepository.guardar(cita);
    }
}