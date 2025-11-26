/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 * Representa un Bloque de Disco en un Sistema de Archivos con Asignación Encadenada.
 * Cada bloque almacena un puntero al siguiente bloque del mismo archivo.
 * * @author Arturo
 */
public class Bloque {
    
    // Identificación del Bloque (Dirección)
    private final int blockID;              // La "direccion" del bloque (Constante)
    
    // Asignación Encadenada (¡CRÍTICO!)
    // Almacena el blockID del siguiente bloque del archivo. Usar -1 para fin de archivo.
    private int punteroSiguiente;           

    // Estado y Propietario
    private boolean isOccupied;             // True si no está "Vacio"
    private String fileName;                // Nombre del archivo dueño (para la visualización)
    private int processOwnerID;             // ID del proceso que lo ocupa (para la gestión de procesos)
    
    // Constructor (Usado al inicializar el Disco)
    public Bloque(int id) {
        this.blockID = id;
        this.isOccupied = false;
        this.punteroSiguiente = -1; // -1 indica que no apunta a otro bloque (FIN)
        this.fileName = null;
        this.processOwnerID = -1;
    }
    
    // --- Métodos de Operación ---

    /**
     * Marca el bloque como ocupado y asigna los propietarios.
     */
    public void occupy(String fileName, int processID) {
        this.isOccupied = true;
        this.fileName = fileName;
        this.processOwnerID = processID;
    }

    /**
     * Libera el bloque, reseteando su estado para ser reasignado.
     */
    public void liberar() {
        this.fileName = null;
        this.processOwnerID = -1;
        this.isOccupied = false;
        this.punteroSiguiente = -1; // ¡Importante resetear el puntero!
    }
    
    // --- Getters y Setters ---

    // --- GETTERS ---
    
    public int getBlockID() {
        return blockID;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public int getPunteroSiguiente() {
        return punteroSiguiente;
    }

    public String getFileName() {
        return fileName;
    }

    public int getProcessOwnerID() {
        return processOwnerID;
    }


    // --- SETTERS ---
    
    /**
     * Establece la dirección (blockID) del siguiente bloque en la cadena.
     * CRÍTICO para la Asignación Encadenada.
     * @param punteroSiguiente El ID del siguiente bloque.
     */
    public void setPunteroSiguiente(int punteroSiguiente) {
        this.punteroSiguiente = punteroSiguiente;
    }

    // Nota: Se han eliminado los setters redundantes (setOccupied, setFileName, setProcessOwnerID)
    // para forzar el uso de los métodos atómicos occupy() y liberar().
    
    @Override
    public String toString() {
        return String.format("[ID:%02d | Ocupado:%s | Archivo:%s | Puntero:%02d | Proceso:%02d]", 
            blockID, 
            isOccupied ? "SI" : "NO", 
            fileName != null ? fileName : "N/A", 
            punteroSiguiente,
            processOwnerID);
    }
}