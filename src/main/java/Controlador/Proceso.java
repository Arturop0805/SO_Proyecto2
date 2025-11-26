/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author yange
 */
// En Modelo/Proceso.java
public class Proceso {
    private int pid; // Process ID
    private String nombre;
    private int tiempoLlegada;
    private int tiempoRafaga; // Tiempo que necesita la CPU
    private EstadoProceso estado; // EN_ESPERA, EN_EJECUCION, TERMINADO, etc.
    // ... otros atributos (prioridad, memoria requerida, etc.)

    public enum EstadoProceso {
    NUEVO, EN_ESPERA, EN_EJECUCION, BLOQUEADO, TERMINADO
    }
    
    public Proceso(int pid, String nombre, int rafaga) {
        this.pid = pid;
        this.nombre = nombre;
        this.tiempoRafaga = rafaga;
        this.estado = EstadoProceso.NUEVO; // O EN_ESPERA
    }
    
    // Getters y Setters
    public int getPid() { return pid; }
    public String getNombre() { return nombre; }
    public EstadoProceso getEstado() { return estado; }
    public void setEstado(EstadoProceso estado) { this.estado = estado; }
    // ...
}

// Enumeración de estados (Opción)


