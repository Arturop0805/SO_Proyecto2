/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import EstructurasDeDatos.ListaEnlazada;
import javax.swing.JFrame;

/**
 *
 * @author Arturo
 */
public class Archivo extends JFrame{
    
    private String nombre;
    private boolean EsDirectorio;
    private int size;
    public ListaEnlazada<Archivo> ListaHijos;
    
    
    public Archivo (String nombre,Boolean EsDirectorio, int size) {
        this.nombre = nombre;
        this.EsDirectorio = EsDirectorio;
        this.size = size;
        this.ListaHijos = new ListaEnlazada<Archivo>();
    }
    
    
    
    public Archivo () {
        this.nombre = null;
    }
    
    public String getNombre() {
        return this.nombre;
    }
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }
   
    public void setEsDirectorio(boolean valor){
        this.EsDirectorio = valor;
    }
    
    public Boolean getEsDirectorio(){
        return this.EsDirectorio;
    }
            
    public void agregarHijo(Archivo dato){
        if (this.EsDirectorio == true){
            this.ListaHijos.Insertar(dato);
        } else {
            System.out.println("NO ES DIRECTORIO");
            return;
        }
        
        
    }
    
    
    public void eliminarHijo(Archivo dato) {
        this.ListaHijos.eliminar(dato);
    }
}


