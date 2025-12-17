package com.hospital.domain;

import java.time.LocalDateTime;
import java.util.UUID;

public class Cita {
    private String id;
    private Paciente paciente;
    private Medico medico;
    private LocalDateTime fechaHora;
    private String estado; // "PENDIENTE", "CONFIRMADA", "CANCELADA", "COMPLETADA"
    private String motivo;
    private String consultorio;
    private LocalDateTime fechaCreacion;

    public Cita(Paciente paciente, Medico medico, LocalDateTime fechaHora, String motivo, String consultorio) {
        this.id = UUID.randomUUID().toString();
        this.paciente = paciente;
        this.medico = medico;
        this.fechaHora = fechaHora;
        this.estado = "CONFIRMADA";
        this.motivo = motivo;
        this.consultorio = consultorio;
        this.fechaCreacion = LocalDateTime.now();
    }

    // Getters y Setters
    public String getId() { return id; }
    public Paciente getPaciente() { return paciente; }
    public void setPaciente(Paciente paciente) { this.paciente = paciente; }
    public Medico getMedico() { return medico; }
    public void setMedico(Medico medico) { this.medico = medico; }
    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }
    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }
    public String getMotivo() { return motivo; }
    public void setMotivo(String motivo) { this.motivo = motivo; }
    public String getConsultorio() { return consultorio; }
    public void setConsultorio(String consultorio) { this.consultorio = consultorio; }
    public LocalDateTime getFechaCreacion() { return fechaCreacion; }

    public void cancelar() {
        this.estado = "CANCELADA";
    }

    public void completar() {
        this.estado = "COMPLETADA";
    }

    @Override
    public String toString() {
        return "Cita{" +
                "id='" + id + '\'' +
                ", paciente=" + paciente.getNombre() +
                ", medico=" + medico.getNombre() +
                ", fechaHora=" + fechaHora +
                ", estado='" + estado + '\'' +
                ", motivo='" + motivo + '\'' +
                '}';
    }
}