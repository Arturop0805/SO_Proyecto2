/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EstructurasDeDatos;

/**
 * Clase auxiliar que representa un nodo básico en la Lista Enlazada.
 * @param <T> Tipo de dato almacenado.
 */
public class Nodo<T> {
    
    private T dato;
    private Nodo<T> siguiente;

    public Nodo(T dato) {
        this.dato = dato;
        this.siguiente = null;
    }

    // Getters
    public T getDato() { return dato; }
    public Nodo<T> getSiguiente() { return siguiente; }
    
    // Setters
    public void setDato(T dato) { this.dato = dato; } // Añadido para actualizar
    public void setSiguiente(Nodo<T> siguiente) { this.siguiente = siguiente; }
}