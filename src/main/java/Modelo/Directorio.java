/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import EstructurasDeDatos.ListaEnlazada;
import EstructurasDeDatos.Nodo;

/**
 * Representa un Directorio en el sistema, conteniendo otras EstructurasArchivo.
 */
public class Directorio extends EstructuraArchivo {
    
    private ListaEnlazada<EstructuraArchivo> contenidos;

    /**
     * Constructor que inicializa el directorio y asigna un propietario.
     * @param nombre Nombre del directorio.
     * @param propietario Propietario del directorio.
     */
    public Directorio(String nombre, String propietario) {
        // CORRECCIÓN: Llamamos al constructor de EstructuraArchivo (super) con el propietario.
        // Se asume el constructor de EstructuraArchivo: (nombre, esDirectorio, propietario)
        super(nombre, true, propietario);
        this.contenidos = new ListaEnlazada<>();
    }

    // --- Gestión de Contenidos ---

    /**
     * Agrega una EstructuraArchivo (Archivo o Directorio) a la lista de contenidos.
     */
    public void agregarContenido(EstructuraArchivo estructura) {
        this.contenidos.agregar(estructura);
    }
    
    /**
     * Busca un contenido por nombre en este directorio.
     */
    public EstructuraArchivo buscarContenido(String nombre) {
        Nodo<EstructuraArchivo> actual = contenidos.getCabeza();
        while (actual != null) {
            if (actual.getDato().getNombre().equals(nombre)) {
                return actual.getDato();
            }
            actual = actual.getSiguiente();
        }
        return null;
    }
    
    /**
     * Elimina un contenido por nombre del directorio.
     */
    public boolean eliminarContenido(String nombre) {
        EstructuraArchivo contenido = buscarContenido(nombre);
        if (contenido != null) {
            // CRÍTICO: El controlador AdministradorDirectorios se encarga de liberar
            // los bloques del disco antes de llamar a esta eliminación.
            return contenidos.eliminar(contenido);
        }
        return false;
    }
    
    // --- Getters ---

    /**
     * Retorna la lista enlazada de los contenidos del directorio (hijos).
     */
    public ListaEnlazada<EstructuraArchivo> getContenidos() { return contenidos; }

    /**
     * Retorna el tamaño del directorio (número de contenidos, no bloques de disco).
     */
    @Override
    public int getTamano() { 
        return contenidos.getTamano(); 
    }
}