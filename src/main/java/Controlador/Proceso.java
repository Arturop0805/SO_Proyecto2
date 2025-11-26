/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 * Representa un proceso de usuario con una solicitud de E/S.
 */
public class Proceso {
    
    public enum Estado {
        NUEVO, LISTO, EJECUTANDO, BLOQUEADO, TERMINADO
    }
    
    public enum Operacion {
        CREAR, LEER, ACTUALIZAR, ELIMINAR
    }
    
    private static int CONTADOR_ID = 1;
    private final int id;
    private Estado estado;
    private Operacion operacion;
    private String rutaArchivo; // Archivo sobre el que opera
    private int tamanoSolicitado; // Solo relevante para CREAR
    private int posicionCilindro; // Posición de la solicitud en el disco, para Planificación

    public Proceso(Operacion operacion, String rutaArchivo, int tamanoSolicitado) {
        this.id = CONTADOR_ID++;
        this.estado = Estado.NUEVO; // [cite: 17]
        this.operacion = operacion; // [cite: 17]
        this.rutaArchivo = rutaArchivo;
        this.tamanoSolicitado = tamanoSolicitado;
        this.posicionCilindro = (int) (Math.random() * 100); // Asumimos disco de 100 cilindros/bloques para simulación
    }

    // --- Getters y Setters ---

    public int getId() { return id; }
    public Estado getEstado() { return estado; }
    public void setEstado(Estado estado) { this.estado = estado; }
    public Operacion getOperacion() { return operacion; }
    public String getRutaArchivo() { return rutaArchivo; }
    public int getTamanoSolicitado() { return tamanoSolicitado; }
    public int getPosicionCilindro() { return posicionCilindro; }
}

