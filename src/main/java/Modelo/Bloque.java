/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import Modelo.Archivo;
/**
 *
 * @author Arturo
 */
public class Bloque {
    
    private Boolean Vacio;
    private Archivo Archivo;
    private int direccion;
    
    public Bloque(Boolean Vacio, Archivo ArchivoDueño, int direccion) {
        this.Vacio = Vacio;
        this.Archivo = ArchivoDueño;
        this.direccion = direccion;
    }
            
    public Bloque() {
        this.Vacio = true;
        this.Archivo = null;
        
    }
    
    public void liberar(){
        this.Archivo = null;
        this.Vacio = true;
        
    }
    
    
    
    public void asignarArchivo(Archivo Archivo) {
        this.Archivo = Archivo;
        this.Vacio = false;
        
    }
    
    public Archivo getArchivo(){
        return this.Archivo;
    }
    
    public void setVacio(Boolean valor){
        this.Vacio = valor;
    }
    
    public void setDireccion(int direccionDisco){
        this.direccion = direccionDisco;
        
    }
    
    public Boolean EstaVacio() {
        return this.Vacio;
    }
    
    public int getDireccion(){
        return this.direccion;
    }
}
