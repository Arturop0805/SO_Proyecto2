/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;
import EstructurasDeDatos.ListaEnlazada;
import EstructurasDeDatos.Nodo;
/**
 *
 * @author Arturo
 */
public class Disco {
    
    private ListaEnlazada<Bloque> SD;
    private ListaEnlazada<Archivo> ArchivosEnDisco;
    private int size;
    private int freeSpace;
    
    public Disco(int numBloques){
        
        this.SD = new ListaEnlazada<>();
        this.ArchivosEnDisco = new ListaEnlazada<>();
        //llena el disco de bloques vacios 
        this.freeSpace = numBloques;
        this.size = numBloques;
        int contador = 0;
        while (contador <= numBloques) {
            Bloque bloqueDisco = new Bloque(true, null, contador);
            
            this.SD.Insertar(bloqueDisco);
            contador++;
        }
        
        
     
       SD.print();
    }
    
    public ListaEnlazada getListaArchivos() {
        return this.ArchivosEnDisco;
    }
    
    //NO USAR AUN NO FUNCIONAN CORRECTAMENTE
   public void agregarArchivo(Archivo archivo) {
    int tamañoBloques = archivo.getTamañoBloques();
    
    int contador = 0;
    int direccion = 0;
    
    if (this.freeSpace < tamañoBloques) {
        return;  // No hay suficiente espacio libre
    }
    
    archivo.setEnDisco(true);
    
    // Asigna bloques libres hasta cubrir el tamaño requerido
    while (contador < tamañoBloques) { 
        
        
        Bloque bloqueSeleccionado = SD.buscarPorIndice(direccion).getDato();
        
        if (bloqueSeleccionado.EstaVacio()) {  
            bloqueSeleccionado.setVacio(false);
            bloqueSeleccionado.asignarArchivo(archivo);
            archivo.ListaBloquesAsignados.Insertar(bloqueSeleccionado);
            contador++;
            this.freeSpace--;
        }
        
        direccion++;  // Siempre incrementa la dirección para buscar el siguiente bloque
    }
    
    this.ArchivosEnDisco.Insertar(archivo);
}
   
     //NO USAR AUN NO FUNCIONAN CORRECTAMENTE
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
                this.ArchivosEnDisco.eliminar(Archivo);
                this.freeSpace++;
            } else {
                direccion++;
            }
        }
        
    }
    
    
    
    public void eliminarArch(Archivo dato) {
       this.ArchivosEnDisco.eliminar(dato);  
   }
    
    
   public void insertarArch(Archivo dato){
       this.ArchivosEnDisco.Insertar(dato);
   }
   
   
    
    
    public Archivo buscarPorNombre(String nombre){
        Nodo<Archivo> auxiliar = this.ArchivosEnDisco.getHead();
        
        while (auxiliar != null) {
            if (nombre.equals(auxiliar.getDato().getNombre())) {
                return auxiliar.getDato();
            } else {
                auxiliar = auxiliar.getSiguiente();
            }
        }
        return null;
    }
}
