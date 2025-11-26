/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import EstructurasDeDatos.ListaEnlazada;
<<<<<<< HEAD
import EstructurasDeDatos.Nodo; // Asumimos que la clase Nodo es necesaria para obtener el dato

public class Disco {
    
    // Atributos
    private final int capacidadBloques;
    // CRÍTICO: Usamos la ListaEnlazada del usuario para simular el almacenamiento físico
    private ListaEnlazada<Bloque> sdBlocks; 
    private int bloquesLibres;
    
    // Constructor
    public Disco(int totalBlocks) {
        this.capacidadBloques = totalBlocks; 
        this.sdBlocks = new ListaEnlazada<>();
        
        // Inicializa la lista enlazada con la cantidad total de Bloques
        for (int i = 0; i < this.capacidadBloques; i++) {
            // El índice 'i' se usa como el blockID (la dirección)
            Bloque nuevoBloque = new Bloque(i); 
            this.sdBlocks.Insertar(nuevoBloque); // Insertar al final de la ListaEnlazada
=======
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
>>>>>>> 397e2148f20879731e40545b63d12913a1f16d7d
        }
        this.bloquesLibres = this.capacidadBloques;
    }

    // --- Métodos de Gestión de Asignación Encadenada ---

    /**
     * Busca y asigna bloques para un nuevo archivo usando el método encadenado,
     * recorriendo la Lista Enlazada para encontrar bloques libres.
     * @return La dirección (blockID) del primer bloque asignado, o -1 si no hay espacio.
     */
    
<<<<<<< HEAD
    

        // ----------------------------------------------------------------------------------
        // LÓGICA DE ASIGNACIÓN ENCADEANDA OPTIMIZADA (SOLUCIONA POSIBLE CRASH)
        // ----------------------------------------------------------------------------------
        public int allocateBlocks(int numBloques, String fileName, int processID) {

            if (numBloques <= 0 || numBloques > this.bloquesLibres) {
                System.out.println("ERROR: Espacio insuficiente en el disco.");
                return -1; 
            }

            int blocksToFind = numBloques;
            int firstBlockID = -1;

            // CRÍTICO: Usaremos un Nodo para mantener la referencia al bloque anterior asignado,
            // evitando la costosa búsqueda por índice.
            Nodo<Bloque> previousBlockNode = null; 

            // 1. Obtener el nodo inicial para comenzar el recorrido (asumimos que existe)
            Nodo<Bloque> auxiliar = (Nodo<Bloque>) this.sdBlocks.buscarPorIndice(0); 

            // 2. Recorrer la ListaEnlazada una sola vez
            while (auxiliar != null && blocksToFind > 0) {
                Bloque currentBlock = auxiliar.getDato();
                int currentBlockID = currentBlock.getBlockID(); 

                // Si el bloque está libre, lo asignamos y encadenamos
                if (!currentBlock.isOccupied()) {

                    currentBlock.occupy(fileName, processID);
                    this.bloquesLibres--;

                    if (firstBlockID == -1) {
                        firstBlockID = currentBlockID;
                    }

                    if (previousBlockNode != null) {
                        // 3. Encadenar: El Bloque del Nodo anterior apunta al ID del Bloque actual
                        previousBlockNode.getDato().setPunteroSiguiente(currentBlockID);
                    }

                    // El nodo actual pasa a ser el nodo anterior para la próxima iteración
                    previousBlockNode = auxiliar;
                    blocksToFind--;

                    if (blocksToFind == 0) {
                        // 4. Establecer el puntero del último bloque como FIN DE ARCHIVO
                        currentBlock.setPunteroSiguiente(-1);
                        return firstBlockID;
                    }
                }

                auxiliar = auxiliar.getSiguiente();
            }

            // Esta sección solo se alcanza si el cálculo inicial de bloques libres fue incorrecto
            return -1; 
        }

        // ----------------------------------------------------------------------------------
        // LÓGICA DE LIBERACIÓN (Se mantiene, usa buscarPorIndice para saltar a la dirección)
        // ----------------------------------------------------------------------------------
        public void deallocateBlocks(int firstBlockID) {
            int currentBlockID = firstBlockID;

            while (currentBlockID != -1) {
                // Se usa buscarPorIndice para saltar a la dirección del bloque
                Nodo<Bloque> nodoActual = (Nodo<Bloque>) this.sdBlocks.buscarPorIndice(currentBlockID);

                if (nodoActual == null) break; 

                Bloque currentBlock = nodoActual.getDato();
                int nextBlockID = currentBlock.getPunteroSiguiente();

                currentBlock.liberar();
                this.bloquesLibres++;

                currentBlockID = nextBlockID;
=======
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
>>>>>>> 397e2148f20879731e40545b63d12913a1f16d7d
            }
        }

        // --- Métodos de Consulta ---
        public int getBloquesLibres() {
            return bloquesLibres;
        }
  
    
    
    
    
    
    
    

    public int getCapacidadTotal() {
        return capacidadBloques;
    }
    
<<<<<<< HEAD
    /**
     * Obtener un bloque por su ID (dirección), usando la búsqueda por índice.
     */
    public Bloque getBlock(int blockID) {
        if (blockID >= 0 && blockID < capacidadBloques) {
            Nodo<Bloque> nodo = (Nodo<Bloque>) this.sdBlocks.buscarPorIndice(blockID);
            if (nodo != null) {
                return nodo.getDato();
            }
        }
        return null; 
    }
}
=======
    
    
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
>>>>>>> 397e2148f20879731e40545b63d12913a1f16d7d
