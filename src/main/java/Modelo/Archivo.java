/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import EstructurasDeDatos.ListaEnlazada;

/**
 * Representa un Archivo en el sistema. Almacena el inicio de su cadena de bloques.
 */
public class Archivo extends EstructuraArchivo {
    
    private int tamanoEnBloques;
    private int primerBloque; // Dirección del primer bloque [cite: 62]
    private int procesoCreadorID; // Proceso que lo creó [cite: 21]
    private String color; // Color asociado para la JTable [cite: 63]

    // Los bloques se gestionan en DiscoSimulado, pero guardamos la lista de índices para la asignación encadenada
    private ListaEnlazada<Integer> indicesBloques; 

    public Archivo(String nombre, int tamanoEnBloques, int procesoCreadorID, String propietario) {
        // ASUMIENDO QUE "color" se sustituye por "propietario" para que el controlador funcione:
        super(nombre, false, propietario);
        this.tamanoEnBloques = tamanoEnBloques;
        this.procesoCreadorID = procesoCreadorID;
        this.color = color;
        this.primerBloque = -1; // Inicialmente no asignado
        this.indicesBloques = new ListaEnlazada<>();
    }
    
    // --- Gestión de Bloques ---
    
    /**
     * Asigna un bloque al archivo. Se usa durante la creación.
     * @param indiceBloque El índice del bloque en el DiscoSimulado.
     */
    public void agregarBloque(int indiceBloque) {
        if (this.primerBloque == -1) {
            this.primerBloque = indiceBloque;
        }
        this.indicesBloques.agregar(indiceBloque);
    }
    
    // --- Getters y Setters ---

    @Override
    public int getTamano() { return tamanoEnBloques; } // Tamaño en bloques [cite: 61]
    public int getPrimerBloque() { return primerBloque; }
    public int getProcesoCreadorID() { return procesoCreadorID; }
    public String getColor() { return color; }
    public ListaEnlazada<Integer> getIndicesBloques() { return indicesBloques; }

    public void setTamanoEnBloques(int tamanoEnBloques) {
        this.tamanoEnBloques = tamanoEnBloques;
    }
    
    public void setPrimerBloque(int primerBloque) {
        this.primerBloque = primerBloque;
    }
    
    // Necesario para el controlador (aunque debería ser parte de EstructuraArchivo)
    public int getPrimerBloqueID() { return primerBloque; } 
    // Aunque el Archivo tiene getPrimerBloque(), si mantienes la convención de DiscoSimulado, 
    // debes asegurarte de que este método exista. Si no puedes agregarlo, el controlador 
    // debe usar getPrimerBloque() en su lugar (como se hizo en el código corregido).

    // NECESARIO para el controlador (fue omitido en tu snippet):
    public void setProcesoCreadorID(int id) { this.procesoCreadorID = id; }
}