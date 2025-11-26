/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EstructurasDeDatos;

import javax.swing.JOptionPane;

public class ListaEnlazada<T> {
    
    private Nodo<T> inicio;
    private int tamaño;
    
    public ListaEnlazada() {
        this.inicio = null;
        this.tamaño = 0;
    }
    
    // ✅ Mejora: Simplificación de la lógica booleana
    public boolean EstaVacia() {
        return this.inicio == null;
    }
    
    // ✅ Mejora: Nombre más estándar (getInicio/getHead son comunes)
    public Nodo<T> getInicio(){
        return this.inicio;
    }
    
    // ✅ Mejora: Devolver tipo primitivo int (aunque Integer es funcional)
    public int getTamaño() {
        return this.tamaño;
    }
    
    // --- MÉTODOS DE INSERCIÓN ---
    
    public void Insertar(T dato) {
        Nodo<T> nuevoNodo = new Nodo<>(dato, this.tamaño); 
        
        if (this.EstaVacia()) {
            this.inicio = nuevoNodo;
        } else {
            Nodo<T> auxiliar = this.inicio;
            while (auxiliar.getSiguiente() != null) {
                auxiliar = auxiliar.getSiguiente();
            }
            auxiliar.setSiguiente(nuevoNodo);
        }
        this.tamaño++;
    }
    
    public void InsertarAlInicio(T dato) {
        // O(1) para la inserción
        Nodo<T> nuevoNodo = new Nodo<>(dato, 0); 
        nuevoNodo.setSiguiente(this.inicio);
        this.inicio = nuevoNodo;
        this.tamaño++;
        
        // O(n) para reindexar (necesario por diseño con índices)
        this.IncrementarIndicesExistentes(); 
    }
    
    private void IncrementarIndicesExistentes() {
        // Este método es necesario solo porque InsertarAlInicio mueve todos los índices hacia adelante.
        int contador = 1;
        Nodo<T> auxiliar = this.inicio.getSiguiente(); 

        while (auxiliar != null) {
            auxiliar.setIndice(contador);
            auxiliar = auxiliar.getSiguiente();
            contador++;
        }
    }
    
    // --- MÉTODO CRUCIAL PARA ELIMINACIÓN ---
    public boolean eliminar(T dato) {
        if (this.EstaVacia()) {
            JOptionPane.showMessageDialog(null, "La lista está vacía, no se puede eliminar.", "Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        // Caso 1: Eliminar la cabeza
        if (this.inicio.getDato().equals(dato)) {
            this.inicio = this.inicio.getSiguiente();
            this.tamaño--;
            
            if (this.inicio != null) {
                // ✅ CONSOLIDACIÓN: Usamos el método de reasignación desde la nueva cabeza.
                this.ReasignarIndicesDesde(this.inicio); 
            }
            return true;
        }
        
        // Caso 2: Buscar y eliminar en el medio/final
        Nodo<T> auxiliar = this.inicio;
        
        while (auxiliar.getSiguiente() != null) {
            if (auxiliar.getSiguiente().getDato().equals(dato)) {
                
                Nodo<T> nodoSiguiente = auxiliar.getSiguiente().getSiguiente(); 
                auxiliar.setSiguiente(nodoSiguiente);
                this.tamaño--; 
                
                if (nodoSiguiente != null) {
                    // ✅ CONSOLIDACIÓN: Reasignación solo de los nodos posteriores.
                    this.ReasignarIndicesDesde(nodoSiguiente); 
                }
                return true;
            }
            auxiliar = auxiliar.getSiguiente();
        }
        
        // Caso 3: No se encontró el elemento
        return false;
    }
    
    // --- CONSOLIDACIÓN DE REINDEXACIÓN (Reemplaza a ActualizarIndices y DecrementarIndicesPosteriores) ---
    /**
     * Reasigna los índices de forma progresiva a partir del nodo especificado.
     * Útil después de una eliminación o extracción en cualquier punto.
     */
    private void ReasignarIndicesDesde(Nodo<T> nodoDeCorte) {
        Nodo<T> auxiliar = nodoDeCorte; 
        int contador = nodoDeCorte.getIndice(); // El índice del nodo de corte es el punto de inicio

        while (auxiliar != null) {
            auxiliar.setIndice(contador);
            auxiliar = auxiliar.getSiguiente();
            contador++;
        }
    }
    
    // --- MÉTODOS ADICIONALES ---
    
    public T ExtraerInicio() {
        if (this.EstaVacia()) {
            return null;
        }
        
        T datoExtraido = this.inicio.getDato();
        this.inicio = this.inicio.getSiguiente();
        this.tamaño--;

        if (this.inicio != null) {
            this.ReasignarIndicesDesde(this.inicio); 
        }
        return datoExtraido;
    }
}    