/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EstructurasDeDatos;

/**
 * Implementación básica de una Cola (Queue/FIFO) usando una lista enlazada,
 * asegurando la correcta tipificación genérica.
 * @param <T> Tipo de dato almacenado.
 */
public class Cola<T> {
    
    private Nodo<T> cabeza; // Frente (head)
    private Nodo<T> cola; // Final (tail)
    private int tamano;

    public Cola() {
        this.cabeza = null;
        this.cola = null;
        this.tamano = 0;
    }

    /**
     * Añade un elemento al final de la cola (Enqueue).
     * @param dato El dato a añadir.
     */
    public void agregar(T dato) {
        // Corrección de error de inferencia de tipo: Nodo<T> nuevoNodo = new Nodo<>(dato);
        Nodo<T> nuevoNodo = new Nodo<T>(dato); 
        
        if (cola == null) {
            cabeza = nuevoNodo;
            cola = nuevoNodo;
        } else {
            cola.setSiguiente(nuevoNodo);
            cola = nuevoNodo;
        }
        tamano++;
    }

    /**
     * Elimina y devuelve el primer elemento (Dequeue/FIFO).
     * @return El primer dato o null si la cola está vacía.
     */
    public T eliminarPrimero() {
        if (cabeza == null) {
            return null;
        }
        
        T dato = cabeza.getDato();
        cabeza = cabeza.getSiguiente();
        
        if (cabeza == null) {
            cola = null; // La cola queda vacía
        }
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
    
    /**
     * Elimina el elemento del inicio si es el mismo que el proporcionado (útil para Planificador).
     * @param dato El dato a buscar y eliminar.
     * @return true si se eliminó, false si no se encontró.
     */
    public boolean eliminar(T dato) {
        if (cabeza == null) return false;
        
        if (cabeza.getDato().equals(dato)) {
            eliminarPrimero();
            return true;
        }

        Nodo<T> actual = cabeza;
        while (actual.getSiguiente() != null) {
            if (actual.getSiguiente().getDato().equals(dato)) {
                actual.setSiguiente(actual.getSiguiente().getSiguiente());
                tamano--;
                // Si eliminamos el último nodo, actualizamos la 'cola'
                if (actual.getSiguiente() == null) { 
                    cola = actual;
                }
                return true;
            }
            actual = actual.getSiguiente();
        }
        return false;
    }
}