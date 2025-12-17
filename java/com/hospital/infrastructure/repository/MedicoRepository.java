package com.hospital.infrastructure.repository;

import com.hospital.domain.Medico;
import java.util.List;
import java.util.Optional;

public interface MedicoRepository {
    Medico guardar(Medico medico);
    Optional<Medico> buscarPorId(String id);
    List<Medico> buscarPorEspecialidad(String especialidad);
    List<Medico> buscarTodos();
    Optional<Medico> buscarPorCodigoColegiatura(String codigo);
}