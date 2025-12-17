package com.hospital.application;

import com.hospital.domain.Horario;
import com.hospital.domain.Medico;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class HorarioService {
    
    public List<Horario> generarHorariosDisponibles(Medico medico, LocalDateTime fechaInicio, 
                                                     LocalDateTime fechaFin, int duracionCitaMinutos) {
        List<Horario> horarios = new ArrayList<>();
        LocalDateTime current = fechaInicio;
        
        while (current.isBefore(fechaFin)) {
            // Generar horarios cada 30 minutos (o según duración)
            Horario horario = new Horario(medico, current, "Consultorio " + (1 + (horarios.size() % 5)));
            horarios.add(horario);
            current = current.plusMinutes(duracionCitaMinutos);
        }
        
        return horarios;
    }
    
    public List<Horario> filtrarHorariosDisponibles(List<Horario> horarios) {
        return horarios.stream()
                .filter(Horario::isDisponible)
                .toList();
    }
    
    public List<Horario> filtrarPorFecha(List<Horario> horarios, LocalDateTime fecha) {
        return horarios.stream()
                .filter(h -> h.getFechaHora().toLocalDate().equals(fecha.toLocalDate()))
                .toList();
    }
}