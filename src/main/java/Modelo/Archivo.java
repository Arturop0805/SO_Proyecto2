/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;



import EstructurasDeDatos.ListaEnlazada;

/**
 * Representa un Archivo o Directorio dentro del simulador del Sistema de Archivos.
 */
public class Archivo {
    
    private final String nombre; 
    private final boolean esDirectorio;
    private int sizeInBlocks;
    private int primerBloqueID;
    private boolean enDisco; 
    private String propietario = "default"; // Añadido para consistencia con Bloque
    
    private final ListaEnlazada<Archivo> listaHijos;

    // --- CONSTRUCTORES ---
    public Archivo (String nombre, boolean esDirectorio, int sizeInBlocks, String propietario) {
        this.nombre = nombre;
        this.esDirectorio = esDirectorio;
        this.sizeInBlocks = sizeInBlocks;
        this.propietario = propietario;
        
        this.primerBloqueID = -1;
        this.enDisco = false;
        this.listaHijos = new ListaEnlazada<>();
    }
    
    // Constructor de Directorio Raíz o Default
    public Archivo (String nombre, boolean esDirectorio, String propietario) {
        this(nombre, esDirectorio, 0, propietario);
    }
    
    // --- MÉTODOS DE MANEJO DE ESTRUCTURA Y PROPIEDADES ---
    public void agregarHijo(Archivo dato){
        if (this.esDirectorio) {
            this.listaHijos.Insertar(dato);
        } else {
            // Error handling
        }
    }
    
    public void eliminarHijo(Archivo dato) {
        this.listaHijos.eliminar(dato);
    }

    @Override
    public String toString() {
        return this.nombre + (this.esDirectorio ? "/" : "");
    }

    // --- GETTERS Y SETTERS ---
    public String getNombre() { return this.nombre; }
    public boolean esDirectorio() { return this.esDirectorio; }
    public int getPrimerBloqueID() { return primerBloqueID; }
    public void setPrimerBloqueID(int primerBloqueID) {
        this.primerBloqueID = primerBloqueID;
        this.enDisco = (primerBloqueID != -1);
    }
    public int getSizeInBlocks() { return this.sizeInBlocks; }
    public void setSizeInBlocks(int sizeInBlocks) { this.sizeInBlocks = sizeInBlocks; }
    public boolean estaEnDisco() { return this.enDisco; }
    public void setEnDisco(boolean valor) { this.enDisco = valor; }
    public String getPropietario() { return propietario; }
    public ListaEnlazada<Archivo> getListaHijos() { return listaHijos; }
}