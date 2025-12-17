package com.hospital.infrastructure.repository;

import com.hospital.domain.Cita;
import com.hospital.exceptions.CitaNoEncontradaException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class InMemoryCitaRepository implements CitaRepository {
    private final Map<String, Cita> citas = new ConcurrentHashMap<>();

    @Override
    public Cita guardar(Cita cita) {
        citas.put(cita.getId(), cita);
        return cita;
    }

    @Override
    public Optional<Cita> buscarPorId(String id) {
        return Optional.ofNullable(citas.get(id));
    }

    @Override
    public List<Cita> buscarPorPaciente(String pacienteId) {
        return citas.values().stream()
                .filter(c -> c.getPaciente().getId().equals(pacienteId))
                .toList();
    }

    @Override
    public List<Cita> buscarPorMedico(String medicoId) {
        return citas.values().stream()
                .filter(c -> c.getMedico().getId().equals(medicoId))
                .toList();
    }

    @Override
    public List<Cita> buscarTodas() {
        return new ArrayList<>(citas.values());
    }

    @Override
    public void eliminar(String id) throws CitaNoEncontradaException {
        if (!citas.containsKey(id)) {
            throw new CitaNoEncontradaException("Cita con ID " + id + " no encontrada");
        }
        citas.remove(id);
    }

    @Override
    public boolean existeCitaEnHorario(String medicoId, java.time.LocalDateTime fechaHora) {
        return citas.values().stream()
                .anyMatch(c -> c.getMedico().getId().equals(medicoId) 
                        && c.getFechaHora().equals(fechaHora)
                        && !c.getEstado().equals("CANCELADA"));
    }
}