/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EstructurasDeDatos;
import EstructurasDeDatos.Nodo;
/**
 *
 * @author Arturo
 */
public class Cola<T> {
    
    
    private Nodo<T> cabeza;
    private Nodo<T> cola;
    private int tamaño;
    
    public Cola(){
   
    this.cabeza = null;
    this.cola = null;
    this.tamaño = 0;
    }
         
    public Boolean EstaVacia() {
        if(this.cabeza == null) {
          return true;  
        } else {
            return false;
        }
    }
    
    public int getTamaño() {
        return this.tamaño; 
    }
    
    public void encolar(T dato) {
        Nodo<T> NuevoNodo = new Nodo<>();
        NuevoNodo.setDato(dato);
        
        
        if (this.EstaVacia()) {
            this.cabeza = NuevoNodo;
            this.cola = NuevoNodo;
            
        } else {
            this.cola.setSiguiente(NuevoNodo);
            this.cola = NuevoNodo;
        }  
        
        
        
        this.tamaño++;
        this.ActualizarIndices();
    }
   
    public void desencolar() {
        if (this.EstaVacia()) {
            return;
        } else if (this.tamaño == 1){
            this.cabeza = null;
            
        } else {
            this.cabeza = this.cabeza.getSiguiente();
        }
        tamaño--;
        this.ActualizarIndices();
        
    }
    
    
    public void ActualizarIndices(){
        Nodo auxiliar = this.cabeza;
        int contador = 0;
        while (contador != this.tamaño) {
            auxiliar.setIndice(contador);
            auxiliar = auxiliar.getSiguiente();
            contador++;
        }
        
    }
    
    
    public void print() {
        Nodo<T> auxiliar = this.cabeza;
       
        while (auxiliar != null) {
            System.out.println(auxiliar.getDato() + "----- idx: " + auxiliar.getIndice());
            auxiliar = auxiliar.getSiguiente();
        }
          
        
        
    }
    
}
