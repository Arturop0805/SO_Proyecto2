/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import EstructurasDeDatos.ListaEnlazada;

/**
 * Representa una entrada en el directorio (ya sea un archivo o un subdirectorio).
 * Actúa como nodo en la estructura jerárquica del sistema de archivos.
 * @author yange
 */
public class DirectorioEntrada {
    
    private String nombre;
    private boolean esDirectorio;
    private int numBloques;
    private int primerBloqueID; // CRÍTICO: Conexión con la cadena de bloques en el disco
    private String propietario;
    private String permisos;
    
    // Lista de hijos usando la implementación manual
    public ListaEnlazada<DirectorioEntrada> ListaHijos;
    private boolean enDisco; 
    
    // --- CONSTRUCTOR PRINCIPAL CORREGIDO ---
    
    /**
     * Constructor ideal para el SD. El primerBloqueID y enDisco se gestionan en el SD.
     */
    public DirectorioEntrada(String nombre, boolean esDirectorio, int numBloques, String propietario) {
        this.nombre = nombre;
        this.esDirectorio = esDirectorio;
        this.numBloques = numBloques;
        this.propietario = propietario;
        
        this.primerBloqueID = -1; // Inicialmente no asignado
        this.enDisco = false;
        
        this.permisos = esDirectorio ? "rwxr-xr-x" : "rw-rw-r--";
        this.ListaHijos = new ListaEnlazada<>();
    }
    
    // Constructor de Directorio Raíz o Default
    public DirectorioEntrada(String nombre, boolean esDirectorio, String propietario) {
        this(nombre, esDirectorio, 0, propietario);
    }
    
    // --- MÉTODOS DE MANEJO DE ESTRUCTURA ---
    
    public void agregarHijo(DirectorioEntrada dato){
        if (this.esDirectorio){
            this.ListaHijos.Insertar(dato);
        } else {
            System.out.println("NO ES DIRECTORIO: No se pueden agregar hijos.");
        }
    }
    
    public void eliminarHijo(DirectorioEntrada dato) {
        this.ListaHijos.eliminar(dato);
    }
    
    @Override
    public String toString() {
        return nombre + (esDirectorio ? "/" : "");
    }
    
    // --- GETTERS Y SETTERS ---
    
    public String getNombre() { return this.nombre; }
    public boolean getEsDirectorio() { return this.esDirectorio; }
    public int getNumBloques() { return this.numBloques; }
    public String getPropietario() { return propietario; }
    public boolean getEnDisco() { return this.enDisco; }
    
    public int getPrimerBloqueID() { return this.primerBloqueID; }
    public void setPrimerBloqueID(int primerBloqueID) {
        this.primerBloqueID = primerBloqueID;
        // Actualiza el estado enDisco automáticamente
        this.enDisco = (primerBloqueID != -1);
    }
    
    public void setNumBloques(int numBloques) { this.numBloques = numBloques; }
}