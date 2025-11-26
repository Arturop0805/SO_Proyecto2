/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EstructurasDeDatos;

import javax.swing.JOptionPane;


/**
 * Implementación básica de una Lista Enlazada genérica para evitar el uso de librerías prohibidas (ArrayList, Queue).
 * @param <T> Tipo de dato almacenado.
 */
public class ListaEnlazada<T> {
    
    private Nodo<T> cabeza;
    private int tamano;

    public ListaEnlazada() {
        this.cabeza = null;
        this.tamano = 0;
    }

    /**
     * Añade un elemento al final de la lista.
     * @param dato El dato a añadir.
     */
    public void agregar(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato);
        if (cabeza == null) {
            cabeza = nuevoNodo;
        } else {
            Nodo<T> actual = cabeza;
            while (actual.getSiguiente() != null) {
                actual = actual.getSiguiente();
            }
            actual.setSiguiente(nuevoNodo);
        }
        tamano++;
    }

    /**
     * Elimina el primer elemento que coincide con el dato.
     * @param dato El dato a eliminar.
     * @return true si se eliminó, false si no se encontró.
     */
    public boolean eliminar(T dato) {
        if (cabeza == null) return false;
        
        if (cabeza.getDato().equals(dato)) {
            cabeza = cabeza.getSiguiente();
            tamano--;
            return true;
        }

        Nodo<T> actual = cabeza;
        while (actual.getSiguiente() != null) {
            if (actual.getSiguiente().getDato().equals(dato)) {
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                tamano--;
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }

    /**
     * Obtiene el dato en una posición específica.
     * @param indice La posición (0-basada).
     * @return El dato en la posición o null si el índice es inválido.
     */
    public T obtener(int indice) {
        if (indice < 0 || indice >= tamano) return null;
        
        Nodo<T> actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.getSiguiente();
        }
        return actual.getDato();
    }

    /**
     * Elimina y devuelve el primer elemento (útil para simular una Queue/FIFO).
     * @return El primer dato o null si la lista está vacía.
     */
    public T eliminarPrimero() {
        if (cabeza == null) return null;
        
        T dato = cabeza.getDato();
        cabeza = cabeza.getSiguiente();
        tamano--;
        return dato;
    }

    public int getTamano() {
        return tamano;
    }
    
    public Nodo<T> getCabeza() {
        return cabeza;
    }
    
    public boolean estaVacia() {
        return tamano == 0;
    }
}    