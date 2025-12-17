package com.hospital.infrastructure.repository;

import com.hospital.domain.Medico;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

public class InMemoryMedicoRepository implements MedicoRepository {
    private final Map<String, Medico> medicos = new ConcurrentHashMap<>();
    private final Map<String, String> colegiaturaToId = new ConcurrentHashMap<>();

    @Override
    public Medico guardar(Medico medico) {
        medicos.put(medico.getId(), medico);
        colegiaturaToId.put(medico.getCodigoColegiatura(), medico.getId());
        return medico;
    }

    @Override
    public Optional<Medico> buscarPorId(String id) {
        return Optional.ofNullable(medicos.get(id));
    }

    @Override
    public List<Medico> buscarPorEspecialidad(String especialidad) {
        return medicos.values().stream()
                .filter(m -> m.getEspecialidad().equalsIgnoreCase(especialidad))
                .collect(Collectors.toList());
    }

    @Override
    public List<Medico> buscarTodos() {
        return new ArrayList<>(medicos.values());
    }

    @Override
    public Optional<Medico> buscarPorCodigoColegiatura(String codigo) {
        String id = colegiaturaToId.get(codigo);
        return id != null ? buscarPorId(id) : Optional.empty();
    }
}