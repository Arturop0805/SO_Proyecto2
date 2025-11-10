/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package EstructurasDeDatos;
import EstructurasDeDatos.ListaEnlazada;
/**
 *
 * @author Arturo
 */
public class NodoArbol<T> {
    
    private T dato;
    private ListaEnlazada<NodoArbol<T>> hijos;
    private int jerarquia; //mientras el valor sea mas cercano a cero, el nodo tiene mayor jerarquia
    
    public NodoArbol(){
        this.dato = null;
        this.hijos = new ListaEnlazada<>();
        
    }
    
    public T getDato(){
        return this.dato;
    }
    
    public ListaEnlazada getHijos() {
        return this.hijos;
    }
    
    public int getJerarquia(){
        return this.jerarquia;
    }
    
    public void setNivel(int jerarquia){
        this.jerarquia = jerarquia;
    }
    
    public void setDato(T dato) {
        this.dato = dato;
    }
    
    public void agregarHijo(T dato) {
        NodoArbol<T> NuevoHijo = new NodoArbol<>();
        NuevoHijo.setDato(dato);
        NuevoHijo.setNivel(this.jerarquia++);  
        this.hijos.Insertar(NuevoHijo);
    }
    
    public void removerHijo(NodoArbol<T> Hijo) {
        
    }
    
    
}
