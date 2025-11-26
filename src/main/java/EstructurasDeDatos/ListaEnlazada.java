/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EstructurasDeDatos;

import javax.swing.JOptionPane;

public class ListaEnlazada<T> {
    
    private Nodo<T> inicio;
    private Nodo<T> cola; // ✅ Añadido para optimizar inserción al final a O(1)
    private int tamaño;
    
    public ListaEnlazada() {
        this.inicio = null;
        this.cola = null; // Inicializar cola
        this.tamaño = 0;
    }
    
    public boolean EstaVacia() {
        return this.inicio == null;
    }
    
    public Nodo<T> getInicio(){
        return this.inicio;
    }
    
    public Nodo<T> getCola(){ // ✅ Getter para la cola
        return this.cola;
    }
    
    public int getTamaño() {
        return this.tamaño;
    }
    
    // ----------------------------------------------------------------------
    // --- MÉTODOS DE INSERCIÓN ---
    // ----------------------------------------------------------------------
    
    /**
     * Inserta un dato al final de la lista (O(1) gracias al puntero 'cola').
     * @param dato El dato a insertar.
     */
    public void Insertar(T dato) {
        // El índice es el tamaño actual antes de incrementar
        Nodo<T> nuevoNodo = new Nodo<>(dato, this.tamaño); 
        
        if (this.EstaVacia()) {
            this.inicio = nuevoNodo;
            this.cola = nuevoNodo; // ✅ El inicio y el fin son el mismo nodo
        } else {
            this.cola.setSiguiente(nuevoNodo); // El antiguo fin apunta al nuevo
            this.cola = nuevoNodo; // ✅ El nuevo nodo es la nueva cola
        }
        this.tamaño++;
    }
    
    /**
     * Inserta un dato al inicio de la lista (O(n) debido a la re-indexación).
     * @param dato El dato a insertar.
     */
    public void InsertarAlInicio(T dato) {
        // O(1) para la inserción
        Nodo<T> nuevoNodo = new Nodo<>(dato, 0); 
        nuevoNodo.setSiguiente(this.inicio);
        this.inicio = nuevoNodo;
        
        if (this.cola == null) {
            this.cola = nuevoNodo; // Caso: lista de 0 a 1 elemento
        }
        
        this.tamaño++;
        
        // O(n) para reindexar (necesario por diseño con índices)
        this.ReasignarIndicesDesde(nuevoNodo.getSiguiente()); // Reindexar desde el segundo elemento
    }
    
    // ----------------------------------------------------------------------
    // --- MÉTODOS DE BÚSQUEDA Y EXTRACCIÓN ---
    // ----------------------------------------------------------------------

    /**
     * ✅ CRÍTICO: Busca y retorna el Nodo<T> en la posición (índice) especificada.
     * Necesario para el Disco (acceso por blockID). Costo: O(n).
     * @param indice La posición del nodo a buscar (0-basado).
     * @return El Nodo<T> en esa posición, o null si el índice es inválido.
     */
    public Nodo<T> buscarPorIndice(int indice) {
        if (indice < 0 || indice >= this.tamaño) {
            return null; // Índice fuera de rango
        }
        
        Nodo<T> auxiliar = this.inicio;
        int contador = 0;
        
        while (auxiliar != null) {
            if (contador == indice) {
                return auxiliar; // Retorna el Nodo en la posición 'indice'
            }
            auxiliar = auxiliar.getSiguiente();
            contador++;
        }
        return null; 
    }
    
    public T ExtraerInicio() {
        if (this.EstaVacia()) {
            return null;
        }
        
        T datoExtraido = this.inicio.getDato();
        
        if (this.inicio == this.cola) { // Si solo hay un elemento
            this.inicio = null;
            this.cola = null; // ✅ Resetear la cola
        } else {
            this.inicio = this.inicio.getSiguiente();
        }
        
        this.tamaño--;

        if (this.inicio != null) {
            this.ReasignarIndicesDesde(this.inicio); // Reindexar
        }
        return datoExtraido;
    }
    
    // ----------------------------------------------------------------------
    // --- MÉTODO CRUCIAL PARA ELIMINACIÓN ---
    // ----------------------------------------------------------------------
    
    public boolean eliminar(T dato) {
        if (this.EstaVacia()) {
            JOptionPane.showMessageDialog(null, "La lista está vacía.", "Error", JOptionPane.WARNING_MESSAGE);
            return false;
        }
        
        // Caso 1: Eliminar la cabeza
        if (this.inicio.getDato().equals(dato)) {
            
            if (this.inicio == this.cola) { // Si solo hay un elemento
                this.cola = null; // ✅ Resetear la cola
            }
            
            this.inicio = this.inicio.getSiguiente();
            this.tamaño--;
            
            if (this.inicio != null) {
                this.ReasignarIndicesDesde(this.inicio); 
            }
            return true;
        }
        
        // Caso 2: Buscar y eliminar en el medio/final
        Nodo<T> auxiliar = this.inicio;
        
        while (auxiliar.getSiguiente() != null) {
            if (auxiliar.getSiguiente().getDato().equals(dato)) {
                
                if (auxiliar.getSiguiente() == this.cola) {
                    this.cola = auxiliar; // ✅ El nodo anterior se convierte en la nueva cola
                }
                
                Nodo<T> nodoSiguiente = auxiliar.getSiguiente().getSiguiente(); 
                auxiliar.setSiguiente(nodoSiguiente);
                this.tamaño--; 
                
                if (nodoSiguiente != null) {
                    this.ReasignarIndicesDesde(nodoSiguiente); 
                }
                return true;
            }
            auxiliar = auxiliar.getSiguiente();
        }
        
        // Caso 3: No se encontró el elemento
        return false;
    }
    
    // ----------------------------------------------------------------------
    // --- RE-INDEXACIÓN (Mantiene la consistencia del índice, pero es O(n)) ---
    // ----------------------------------------------------------------------
    
    /**
     * Reasigna los índices de forma progresiva a partir del nodo especificado.
     * El nodo de corte asume el índice de inicio de la secuencia.
     */
    private void ReasignarIndicesDesde(Nodo<T> nodoDeCorte) {
        if (nodoDeCorte == null) return;
        
        Nodo<T> auxiliar = nodoDeCorte; 
        // El índice del nodo de corte es el punto de inicio para la reasignación.
        int contador = nodoDeCorte.getIndice(); 

        while (auxiliar != null) {
            auxiliar.setIndice(contador);
            auxiliar = auxiliar.getSiguiente();
            contador++;
        }
    }
}    