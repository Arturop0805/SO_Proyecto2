/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import EstructurasDeDatos.ListaEnlazada;
/**
 *
 * @author Arturo
 */
public class NodoArchivo {
    
    private String nombre;
    private Boolean esCarpeta;
    private int bloquesAsignados;
    private ListaEnlazada<NodoArchivo> hijos;
    

    public NodoArchivo (String nombre, Boolean esCarpeta, int BloquesAsignados) {
        this.nombre = nombre;
        this.esCarpeta = esCarpeta;
        this.bloquesAsignados = BloquesAsignados;
    }
    
    public NodoArchivo() {
        this.nombre = null;
        this.esCarpeta = true; 
    }
    
    @Override
    public String toString() {
        return nombre;
    }
    
    public String getNombre() {
        return this.nombre;
    }
    
    public Boolean getEsCarpeta() {
        return this.esCarpeta;
    }
    
    public ListaEnlazada<NodoArchivo> getListaHijos() {
        return this.hijos;
    }
    
    public void setNombre(String nombre){
        this.nombre = nombre;
    }
  
    
            
    
    public void AgregarHijo(NodoArchivo NuevoHijo) {
        this.hijos.Insertar(NuevoHijo);
    }
    
    
    public void EliminarHijo(NodoArchivo hijo){
        this.hijos.eliminar(hijo);
        
       
    }
    
    
}
