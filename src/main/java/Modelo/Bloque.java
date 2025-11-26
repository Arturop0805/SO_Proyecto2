/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 * Representa un Bloque de Disco en un Sistema de Archivos con Asignación Encadenada.
 * CRÍTICO: El punteroSiguiente implementa la lista enlazada a nivel de disco.
 */
public class Bloque {
    
    private final int blockID;
    private int punteroSiguiente;
    private boolean isOccupied;
    private String fileName;
    private int processOwnerID;
    
    public Bloque(int id) {
        this.blockID = id;
        this.isOccupied = false;
        this.punteroSiguiente = -1; // -1 indica que no apunta a otro bloque (FIN)
        this.fileName = null;
        this.processOwnerID = -1;
    }
    
    // --- Métodos de Operación ---
    
    /**
     * Marca el bloque como ocupado y asigna propietarios. 
     * NOTA: El punteroSiguiente se establece externamente en la clase Disco.
     */
    public void occupy(String fileName, int processID) { // <--- CORRECCIÓN: 2 argumentos
        this.isOccupied = true;
        this.fileName = fileName;
        this.processOwnerID = processID;
        // Se mantiene el puntero existente hasta que Disco lo establezca
    }

    public void liberar() {
        this.fileName = null;
        this.processOwnerID = -1;
        this.isOccupied = false;
        this.punteroSiguiente = -1;
    }
    
    // --- Getters y Setters ---
    public int getBlockID() { return blockID; }
    public boolean isOccupied() { return isOccupied; }
    public int getPunteroSiguiente() { return punteroSiguiente; }
    public String getFileName() { return fileName; }
    public int getProcessOwnerID() { return processOwnerID; }

    public void setPunteroSiguiente(int punteroSiguiente) {
        this.punteroSiguiente = punteroSiguiente;
    }

    @Override
    public String toString() {
        String estado = isOccupied ? (fileName != null ? fileName : "OCUPADO") : "LIBRE";
        String puntero = punteroSiguiente == -1 ? "FIN" : String.format("%02d", punteroSiguiente);
        
        return String.format("[ID:%02d | %-8s | Puntero:%s]", blockID, estado, puntero);
    }
}