/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 * Representa un bloque de almacenamiento en el Disco Simulado.
 */
public class Bloque {
    private boolean ocupado;
    private String contenido; // Simplificación del contenido
    private int indiceSiguienteBloque; // Implementación de Asignación Encadenada [cite: 32]
    private int idPropietario; // Proceso o archivo que lo ocupa [cite: 21]

    public Bloque() {
        this.ocupado = false;
        this.contenido = "";
        this.indiceSiguienteBloque = -1; // -1 indica el final de la cadena o libre
        this.idPropietario = -1;
    }

    // --- Métodos de gestión ---

    public void ocupar(int idPropietario) {
        this.ocupado = true;
        this.idPropietario = idPropietario;
    }

    public void liberar() {
        this.ocupado = false;
        this.contenido = "";
        this.indiceSiguienteBloque = -1;
        this.idPropietario = -1;
    }

    // --- Getters y Setters ---

    public boolean isOcupado() { return ocupado; }
    public void setIndiceSiguienteBloque(int indiceSiguienteBloque) {
        this.indiceSiguienteBloque = indiceSiguienteBloque;
    }
    public int getIndiceSiguienteBloque() { return indiceSiguienteBloque; }
    public int getIdPropietario() { return idPropietario; }
    // ... otros getters/setters si son necesarios
}