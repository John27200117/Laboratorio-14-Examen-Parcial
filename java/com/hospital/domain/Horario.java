package com.hospital.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Horario {
    private String id;
    private Medico medico;
    private LocalDateTime fechaHora;
    private boolean disponible;
    private String consultorio;

    public Horario(Medico medico, LocalDateTime fechaHora, String consultorio) {
        this.id = UUID.randomUUID().toString();
        this.medico = medico;
        this.fechaHora = fechaHora;
        this.disponible = true;
        this.consultorio = consultorio;
    }

    // Getters y Setters
    public String getId() { return id; }
    public Medico getMedico() { return medico; }
    public void setMedico(Medico medico) { this.medico = medico; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
    public boolean isDisponible() { return disponible; }
    public void setDisponible(boolean disponible) { this.disponible = disponible; }
    public String getConsultorio() { return consultorio; }
    public void setConsultorio(String consultorio) { this.consultorio = consultorio; }

    public boolean esMismoDiaYHora(LocalDateTime otraFecha) {
        return this.fechaHora.equals(otraFecha);
    }

    @Override
    public String toString() {
        return "Horario{" +
                "medico=" + medico.getNombre() +
                ", fechaHora=" + fechaHora +
                ", disponible=" + disponible +
                ", consultorio='" + consultorio + '\'' +
                '}';
    }
}