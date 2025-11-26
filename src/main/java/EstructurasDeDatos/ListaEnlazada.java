/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EstructurasDeDatos;

import javax.swing.JOptionPane;



/**
 * Implementación básica de una Lista Enlazada genérica.
 * CRÍTICO: El método buscarPorIndice() es crucial para simular el acceso indexado al disco.
 * @param <T> Tipo de dato almacenado.
 */
public class ListaEnlazada<T> {
    
    private Nodo<T> cabeza;
    private int tamaño;

    public ListaEnlazada() {
        this.cabeza = null;
        this.tamaño = 0;
    }
    
    /** Inserta un nuevo nodo al final de la lista. */
    public void Insertar(T dato) {
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
        tamaño++;
    }

    /** Elimina un dato específico de la lista. */
    public void eliminar(T dato) {
        if (cabeza == null) return;

        if (cabeza.getDato().equals(dato)) {
            cabeza = cabeza.getSiguiente();
            tamaño--;
            return;
        }

        Nodo<T> actual = cabeza;
        while (actual.getSiguiente() != null) {
            if (actual.getSiguiente().getDato().equals(dato)) {
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                tamaño--;
                return;
            }
            actual = actual.getSiguiente();
        }
    }
    
    public int getTamaño() {
        return tamaño;
    }
    
    /**
     * Busca un Nodo específico por su índice (O(N)).
     * Usado en el Disco para simular el acceso a un bloque por su ID (dirección).
     */
    public Nodo<T> buscarPorIndice(int indice) {
        if (indice < 0 || indice >= tamaño) {
            return null;
        }
        Nodo<T> actual = cabeza;
        for (int i = 0; i < indice; i++) {
            actual = actual.getSiguiente();
        }
        return actual;
    }
}    