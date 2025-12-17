package com.hospital.domain;

import java.util.UUID;

public class Medico {
    private String id;
    private String nombre;
    private String especialidad;
    private String codigoColegiatura;
    private int añosExperiencia;

    public Medico(String nombre, String especialidad, String codigoColegiatura, int añosExperiencia) {
        this.id = UUID.randomUUID().toString();
        this.nombre = nombre;
        this.especialidad = especialidad;
        this.codigoColegiatura = codigoColegiatura;
        this.añosExperiencia = añosExperiencia;
    }

    // Getters y Setters
    public String getId() { return id; }
    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }
    public String getEspecialidad() { return especialidad; }
    public void setEspecialidad(String especialidad) { this.especialidad = especialidad; }
    public String getCodigoColegiatura() { return codigoColegiatura; }
    public void setCodigoColegiatura(String codigoColegiatura) { this.codigoColegiatura = codigoColegiatura; }
    public int getAñosExperiencia() { return añosExperiencia; }
    public void setAñosExperiencia(int añosExperiencia) { this.añosExperiencia = añosExperiencia; }

    @Override
    public String toString() {
        return "Medico{" +
                "id='" + id + '\'' +
                ", nombre='" + nombre + '\'' +
                ", especialidad='" + especialidad + '\'' +
                ", añosExperiencia=" + añosExperiencia +
                '}';
    }
}