/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EstructurasDeDatos;

import EstructurasDeDatos.Nodo; // Asegúrate de que esta línea esté presente

/**
 * Representa una estructura de datos Cola (Queue) implementada
 * usando una Lista Enlazada para un rendimiento O(1) en encolar y desencolar.
 */
public class Cola<T> {
    
    private Nodo<T> cabeza; // Puntero al inicio (para desencolar)
    private Nodo<T> cola;   // Puntero al final (para encolar)
    private int tamaño;
    
    public Cola(){
        this.cabeza = null;
        this.cola = null;
        this.tamaño = 0;
    }
            
    public boolean EstaVacia() {
        // Mejorado para simplicidad
        return this.cabeza == null;
    }
    
    public int getTamaño() {
        return this.tamaño; 
    }
    
    /**
     * Añade un elemento al final de la cola (O(1)).
     * @param dato El elemento a añadir.
     */
    public void encolar(T dato) {
        // Usamos el constructor de Nodo con el dato (y el índice, si es necesario)
        Nodo<T> NuevoNodo = new Nodo<>(dato, this.tamaño); 
        
        if (this.EstaVacia()) {
            this.cabeza = NuevoNodo;
            this.cola = NuevoNodo;
            
        } else {
            // El nodo actual al final apunta al nuevo nodo
            this.cola.setSiguiente(NuevoNodo);
            // El nuevo nodo es la nueva cola
            this.cola = NuevoNodo; 
        }  
        
        this.tamaño++;
        // ✅ ELIMINADO: this.ActualizarIndices() para mantener rendimiento O(1)
    }
    
    /**
     * Elimina y retorna el elemento al inicio de la cola (O(1)).
     * @return El dato al inicio de la cola o null si está vacía.
     */
    public T desencolar() {
        if (this.EstaVacia()) {
            return null;
        } 
        
        T datoExtraido = this.cabeza.getDato();
        
        if (this.tamaño == 1){
            this.cabeza = null;
            this.cola = null; // ✅ CORRECCIÓN
            
        } else {
            this.cabeza = this.cabeza.getSiguiente();
        }
        
        this.tamaño--;
        // ✅ ELIMINADO: this.ActualizarIndices() para mantener rendimiento O(1)
        
        return datoExtraido;
    }
    
    // Si aún se necesita, el método ActualizarIndices() debe corregirse
    /*
    public void ActualizarIndices(){
        Nodo auxiliar = this.cabeza;
        int contador = 0;
        while (auxiliar != null) { // Bucle corregido para evitar NPE
            auxiliar.setIndice(contador);
            auxiliar = auxiliar.getSiguiente();
            contador++;
        }
    }
    */
    
    public void print() {
        Nodo<T> auxiliar = this.cabeza;
        
        while (auxiliar != null) {
            // Se asume que el índice ya está correcto por el diseño de Nodo y encolar
            System.out.println(auxiliar.getDato() + "----- idx: " + auxiliar.getIndice());
            auxiliar = auxiliar.getSiguiente();
        }
    }
}