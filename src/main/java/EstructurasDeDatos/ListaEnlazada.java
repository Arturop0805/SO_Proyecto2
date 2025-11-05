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
public class ListaEnlazada<T> {
    
    private Nodo<T> inicio;
    private int tamaño;
    
    public ListaEnlazada() {
        this.inicio = null;
        this.tamaño = 0;
    }
    
    public Boolean EstaVacia() {
        if (this.inicio == null) {
            return true;
        } else {
            return false;
        }
    }
    
    
    public Integer Tamaño() {
        return this.tamaño;
    }
    
    public void Insertar(T dato) {
        
        Nodo<T> NuevoNodo = new Nodo<>();
        NuevoNodo.setDato(dato);
        
        if (this.EstaVacia()) {
            
            this.inicio = NuevoNodo;
            
            
        } else {
           
             Nodo<T> auxiliar = this.inicio;
            
            while (auxiliar.getSiguiente() != null) {
                auxiliar = auxiliar.getSiguiente();
            }
            
            auxiliar.setSiguiente(NuevoNodo);
        }
        
        
        
      
        this.tamaño++;
        this.ActualizarIndices();
    }
    
    
    public void print() {
        
        Nodo<T> auxiliar = this.inicio;
        
        while (auxiliar != null) {
            System.out.println("valor: " + auxiliar.getDato() + " indice: " + auxiliar.getIndice());
            auxiliar = auxiliar.getSiguiente();
        }
     
        
    }
    
    public void InsertarAlInicio(T dato) {
        Nodo<T> NuevoNodo = new Nodo<>();
        NuevoNodo.setDato(dato);
        
        NuevoNodo.setSiguiente(this.inicio);
        this.inicio = NuevoNodo;
        tamaño++;
        this.ActualizarIndices();
        
  
    }
    
    
    public void InsertarPorIndice(int indice, T dato) {
          
        Nodo<T> NuevoNodo = new Nodo<>();
        NuevoNodo.setDato(dato);
        
        Nodo<T> auxiliar = this.inicio;
        
        
       
        
        if (indice > this.tamaño) {
            System.out.println("Indice mayor al tamaño de la lista");
            return;
        }
        
        
        if (indice == 0) {
            this.InsertarAlInicio(dato);
            return;
        }
        
        if (indice == tamaño) {
            this.Insertar(dato);{
            return;
        }
        }
        
        while (auxiliar.getSiguiente().getIndice() != indice) {
       
               auxiliar = auxiliar.getSiguiente();
               
        }
        
        
        if (auxiliar.getSiguiente().getIndice() == indice) {
            NuevoNodo.setSiguiente(auxiliar.getSiguiente());
            auxiliar.setSiguiente(NuevoNodo);

        }
          
        
        this.ActualizarIndices();
        tamaño++;
      }
    
    
    private void ActualizarIndices(){
        int contador = 0;
        Nodo<T> auxiliar = this.inicio;
        
        
        while (auxiliar != null) {
            auxiliar.setIndice(contador);
            auxiliar = auxiliar.getSiguiente();
            contador++;
        }
    }
    
}

    