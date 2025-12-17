package com.hospital.domain;

import java.time.format.DateTimeFormatter;
import java.util.UUID;

public class Ticket {
    private String id;
    private Cita cita;
    private String codigoQR;
    private LocalDateTime fechaGeneracion;

    public Ticket(Cita cita) {
        this.id = UUID.randomUUID().toString();
        this.cita = cita;
        this.codigoQR = "TICKET-" + cita.getId().substring(0, 8).toUpperCase();
        this.fechaGeneracion = LocalDateTime.now();
    }

    // Getters
    public String getId() { return id; }
    public Cita getCita() { return cita; }
    public String getCodigoQR() { return codigoQR; }
    public LocalDateTime getFechaGeneracion() { return fechaGeneracion; }

    public void imprimir() {
        System.out.println("====================================");
        System.out.println("          HOSPITAL CENTRAL          ");
        System.out.println("====================================");
        System.out.println("TICKET ID: " + id);
        System.out.println("CÓDIGO QR: " + codigoQR);
        System.out.println("------------------------------------");
        System.out.println("PACIENTE: " + cita.getPaciente().getNombre());
        System.out.println("DNI: " + cita.getPaciente().getDni());
        System.out.println("MÉDICO: " + cita.getMedico().getNombre());
        System.out.println("ESPECIALIDAD: " + cita.getMedico().getEspecialidad());
        System.out.println("FECHA: " + cita.getFechaHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")));
        System.out.println("CONSULTORIO: " + cita.getConsultorio());
        System.out.println("MOTIVO: " + cita.getMotivo());
        System.out.println("------------------------------------");
        System.out.println("Fecha emisión: " + fechaGeneracion.format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss")));
        System.out.println("====================================");
    }

    public String obtenerResumen() {
        return "Ticket{" +
                "codigoQR='" + codigoQR + '\'' +
                ", paciente='" + cita.getPaciente().getNombre() + '\'' +
                ", fechaCita=" + cita.getFechaHora().format(DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm")) +
                '}';
    }
}