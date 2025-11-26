/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EstructurasDeDatos;

/**
 *
 * @author Arturo
 */
public class Nodo<T> {
    
    private T dato;
    private Nodo<T> siguiente;
    private int indice;
   
    
    public Nodo() {

        this.dato = null;
        this.siguiente = null;
        this.indice = 0;

    }
    
    public Nodo(T dato, int indice) {
        this.dato = dato;
        this.indice = indice;
        this.siguiente = null;
    }
    
    
    public T getDato() {
        return this.dato;
    }
          
    public Nodo<T> getSiguiente() {
        return this.siguiente;
    }
    
    public Integer getIndice(){
        return this.indice;
    }
    
    public void setDato(T dato) {
        this.dato = dato;
    }
    
    public void setSiguiente (Nodo<T> siguiente) {
        this.siguiente = siguiente;
    }
    
    public void setIndice(int index){
        this.indice = index;
    }
    
    
    
}
