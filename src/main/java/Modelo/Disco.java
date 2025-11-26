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
public class Disco {
    
    private ListaEnlazada<Bloque> SD;
    private int size;
    private int freeSpace;
    
    public Disco(int numBloques){
        //llena el disco de bloques vacios 
        this.freeSpace = numBloques;
        this.size = numBloques;
        int contador = 0;
        while (contador <= numBloques) {
            Bloque bloqueDisco = new Bloque(true, null, contador);
            
            SD.Insertar(bloqueDisco);
            contador++;
        }
        
        
     
       SD.print();
    }
    
    
    public void agregarArchivo(Archivo Archivo) {
        int tamañoBloques = Archivo.getTamañoBloques();
        int contador = 0;
        int direccion = 0;
        
        if (this.freeSpace < tamañoBloques) {
            return;
        }
        
        Archivo.setEnDisco(true);
        
        while (contador <= tamañoBloques) { //recorre el disco buscando bloques libres, si esta libre lo asigna al archivo, si no busca el siguiente bloque libre
            Bloque bloqueSeleccionado = SD.buscarPorIndice(direccion).getDato();
            if (bloqueSeleccionado.EstaVacio()) {
                bloqueSeleccionado.asignarArchivo(Archivo);
                
                
                Archivo.ListaBloquesAsignados.Insertar(bloqueSeleccionado);
                contador++;
                this.freeSpace--;
            } else {
                direccion++;
            }
            
            
        }
        
        
        
    }
    
    public void eliminarArchivo(Archivo Archivo) {
        int tamañoBloques = Archivo.getTamañoBloques();
        int contador = 0;
        int direccion = 0;
        
        if (Archivo.EstaEnDisco() == false) {
            return;
        }
        
        
        while (contador <= tamañoBloques) {
            Bloque bloqueSeleccionado = SD.buscarPorIndice(direccion).getDato();
            if (bloqueSeleccionado.getArchivo() == Archivo) {
                bloqueSeleccionado.liberar();
                
                
                Archivo.ListaBloquesAsignados.eliminar(bloqueSeleccionado);
                contador++;
                this.freeSpace++;
            } else {
                direccion++;
            }
        }
        
    }
    
}
