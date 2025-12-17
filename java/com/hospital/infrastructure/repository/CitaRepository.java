package com.hospital.infrastructure.repository;

import com.hospital.domain.Cita;
import com.hospital.exceptions.CitaNoEncontradaException;
import java.util.List;
import java.util.Optional;

public interface CitaRepository {
    Cita guardar(Cita cita);
    Optional<Cita> buscarPorId(String id);
    List<Cita> buscarPorPaciente(String pacienteId);
    List<Cita> buscarPorMedico(String medicoId);
    List<Cita> buscarTodas();
    void eliminar(String id) throws CitaNoEncontradaException;
    boolean existeCitaEnHorario(String medicoId, java.time.LocalDateTime fechaHora);
}