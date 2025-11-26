/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import EstructurasDeDatos.ListaEnlazada;
// import javax.swing.JFrame; // ELIMINADO - Ya no hereda de la GUI

/**
 * Representa un Archivo o Directorio dentro del simulador del Sistema de Archivos.
 */
public class Archivo {
    
    private String nombre;
    private boolean EsDirectorio;
    private int size; // Tamaño en KB o bloques
    private int primerBloqueID; // 👈 Nuevo: ID del primer bloque asignado en disco
    
    // Lista de hijos (solo si EsDirectorio es true)
    public ListaEnlazada<Archivo> ListaHijos;
    // Lista de Bloques (Solo si EsDirectorio es false, para simular contenido)
    public ListaEnlazada<Bloque> ListaBloquesAsignados; 
    public Boolean enDisco;
    
    
    // --- CONSTRUCTORES ---
    
    /**
     * Constructor principal para crear una nueva entrada (archivo o directorio).
     * @param nombre Nombre del archivo/directorio.
     * @param EsDirectorio True si es directorio.
     * @param size Tamaño inicial o número de bloques a asignar.
     * @param primerBloqueID ID del primer bloque asignado en el disco. Usar -1 si es directorio o no asignado.
     */
    public Archivo (String nombre, Boolean EsDirectorio, int size, int primerBloqueID) {
        this.nombre = nombre;
        this.EsDirectorio = EsDirectorio;
        this.size = size;
        this.primerBloqueID = primerBloqueID;
        
        // Inicialización de listas (siempre se inicializan, pero se usan solo si aplica)
        this.ListaHijos = new ListaEnlazada<>();
        this.ListaBloquesAsignados = new ListaEnlazada<>();
        this.enDisco = false;
    }
    
    public Archivo () {
        this.nombre = null;
        this.ListaHijos = new ListaEnlazada<>();
        this.ListaBloquesAsignados = new ListaEnlazada<>();
        this.enDisco = false;
        this.primerBloqueID = -1;
    }
    
    // --- MÉTODOS DE MANEJO DE ESTRUCTURA Y PROPIEDADES ---
    
    public void agregarHijo(Archivo dato){
        if (this.EsDirectorio) { // Usa directamente el booleano
            this.ListaHijos.Insertar(dato);
        } else {
            System.out.println("NO ES DIRECTORIO: No se pueden agregar hijos.");
        }
    }
    
    public void eliminarHijo(Archivo dato) {
        this.ListaHijos.eliminar(dato);
    }

    @Override
    public String toString() {
        // Útil para la vista del árbol
        return this.nombre + (this.EsDirectorio ? "/" : "");
    }

    // --- GETTERS Y SETTERS ---
    
    public String getNombre() {
        return this.nombre;
    }
    // ... (otros getters y setters, incluyendo los nuevos)
    
    public int getPrimerBloqueID() {
        return primerBloqueID;
    }

    public void setPrimerBloqueID(int primerBloqueID) {
        this.primerBloqueID = primerBloqueID;
    }
    
    public Boolean getEsDirectorio(){
        return this.EsDirectorio;
    }
    
    public int getTamañoBloques() {
        return this.size;
    }
    
    public void setTamañoBloques(int valor) {
        this.size = valor;
    }
    
    public Boolean EstaEnDisco(){
        return this.enDisco;
    }
    
    public void setEnDisco(Boolean valor) {
        this.enDisco = valor;
    }
}