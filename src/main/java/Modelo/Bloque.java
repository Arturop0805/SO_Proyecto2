/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
/**
 *
 * @author Arturo
 */

public class Bloque {
    
    // Identificación del Bloque (Dirección)
    private int blockID;              // La "direccion" del bloque
    
    // Asignación Encadenada (¡CRÍTICO!)
    // Almacena el blockID del siguiente bloque del archivo. Usar -1 para fin de archivo.
    private int punteroSiguiente;   

    // Estado y Propietario
    private boolean isOccupied;       // True si no está "Vacio"
    private String fileName;          // Nombre del archivo dueño (para la visualización)
    private int processOwnerID;       // ID del proceso que lo ocupa (para la gestión de procesos)
    
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

    /**
     * Obtiene el ID (dirección) del bloque en el disco.
     */
    public int getBlockID() {
        return blockID;
    }

    /**
     * Verifica si el bloque está ocupado.
     */
    public boolean isOccupied() {
        return isOccupied;
    }

    /**
     * Obtiene la dirección (blockID) del siguiente bloque del archivo en la cadena.
     * Retorna -1 si es el final del archivo.
     */
    public int getPunteroSiguiente() {
        return punteroSiguiente;
    }

    /**
     * Obtiene el nombre del archivo que ocupa este bloque.
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * Obtiene el ID del proceso que creó/modificó este bloque.
     */
    public int getProcessOwnerID() {
        return processOwnerID;
    }


    // --- SETTERS ---

    // Nota: blockID se establece en el constructor y no debe cambiar (por eso no hay setBlockID).
    // isOccupied, fileName, y processOwnerID son manipulados por los métodos occupy() y liberar().

    /**
     * Establece la dirección (blockID) del siguiente bloque en la cadena.
     * CRÍTICO para la Asignación Encadenada.
     * @param punteroSiguiente El ID del siguiente bloque.
     */
    public void setPunteroSiguiente(int punteroSiguiente) {
        this.punteroSiguiente = punteroSiguiente;
    }

    /**
     * Establece el estado de ocupación. Usado principalmente por occupy() y liberar().
     * @param occupied true si está ocupado, false si está libre.
     */
    public void setOccupied(boolean occupied) {
        isOccupied = occupied;
    }

    /**
     * Establece el nombre del archivo dueño.
     * @param fileName Nombre del archivo.
     */
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * Establece el ID del proceso dueño.
     * @param processOwnerID ID del proceso.
     */
    public void setProcessOwnerID(int processOwnerID) {
        this.processOwnerID = processOwnerID;
    }

   

    @Override
    public String toString() {
        // Usamos String.format para alinear y mostrar los datos del bloque
        return String.format("[ID:%02d | Ocupado:%s | Archivo:%s | Puntero:%02d | Proceso:%02d]", 
            blockID, 
            isOccupied ? "SI" : "NO", 
            fileName != null ? fileName : "N/A", 
            punteroSiguiente,
            processOwnerID);
    }

}