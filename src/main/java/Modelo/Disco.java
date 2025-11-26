/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import EstructurasDeDatos.ListaEnlazada;
import EstructurasDeDatos.Nodo;
import java.util.logging.Logger;

/**
 * Simula el almacenamiento físico del disco, gestionando la asignación
 * de bloques mediante el método de Asignación Encadenada.
 * Utiliza ListaEnlazada para simular el arreglo de bloques.
 */
public class Disco {
    private static final Logger logger = Logger.getLogger(Disco.class.getName());
    
    private final int capacidadBloques;
    private final ListaEnlazada<Bloque> bloquesDeDisco; 
    private int bloquesLibres;
    private final ListaEnlazada<Archivo> listaArchivos; 
    
    public Disco(int totalBlocks) {
        this.capacidadBloques = totalBlocks;  
        this.bloquesDeDisco = new ListaEnlazada<>(); 
        this.listaArchivos = new ListaEnlazada<>(); 
        
        // Inicializa la lista enlazada con la cantidad total de Bloques
        for (int i = 0; i < this.capacidadBloques; i++) {
            Bloque nuevoBloque = new Bloque(i); 
            this.bloquesDeDisco.Insertar(nuevoBloque); 
        }
        this.bloquesLibres = this.capacidadBloques;
        logger.info("Disco inicializado. Capacidad: " + capacidadBloques);
    }

    // --- Métodos de Gestión de Asignación Encadenada ---

    /**
     * Busca y asigna bloques para un nuevo archivo usando el método encadenado.
     * @param numBloques Cantidad de bloques requeridos.
     * @param fileName Nombre del archivo a asignar.
     * @param processID ID del proceso.
     * @return La dirección (blockID) del primer bloque asignado, o -1 si no hay espacio.
     */
    public int allocateBlocks(int numBloques, String fileName, int processID) {

        if (numBloques <= 0 || numBloques > this.bloquesLibres) {
            logger.warning("ERROR: Espacio insuficiente en el disco o tamaño no válido.");
            return -1; 
        }

        int blocksToFind = numBloques;
        int firstBlockID = -1;
        
        Nodo<Bloque> previousBlockNode = null; 
        
        // 1. Obtener el nodo inicial para comenzar el recorrido (O(N) inicial)
        Nodo<Bloque> auxiliar = this.bloquesDeDisco.buscarPorIndice(0); 

        while (auxiliar != null && blocksToFind > 0) {
            Bloque currentBlock = auxiliar.getDato();
            int currentBlockID = currentBlock.getBlockID(); 

            if (!currentBlock.isOccupied()) {

                // Asignar el bloque (NOTA: occupy ahora es de 2 argumentos)
                currentBlock.occupy(fileName, processID); 
                this.bloquesLibres--;

                if (firstBlockID == -1) {
                    firstBlockID = currentBlockID;
                }

                if (previousBlockNode != null) {
                    // 2. Encadenar: El Bloque del Nodo anterior apunta al ID del Bloque actual
                    previousBlockNode.getDato().setPunteroSiguiente(currentBlockID);
                }

                // El nodo actual pasa a ser el nodo anterior para la próxima iteración
                previousBlockNode = auxiliar;
                blocksToFind--;

                if (blocksToFind == 0) {
                    // 3. Establecer el puntero del último bloque como FIN DE ARCHIVO (-1)
                    currentBlock.setPunteroSiguiente(-1);
                    return firstBlockID;
                }
            }
            auxiliar = auxiliar.getSiguiente();
        }

        // Si falló, liberamos los bloques parcialmente asignados
        if (firstBlockID != -1) {
             deallocateBlocks(firstBlockID);
             logger.severe("ERROR INTERNO: Fallo al encadenar todos los bloques. Espacio recuperado.");
        }
        return -1; 
    }

    /**
     * Libera todos los bloques de un archivo, siguiendo la cadena de punteros.
     * Costo: O(N * n_bloques) debido a la búsqueda O(N) por índice en la Lista Enlazada.
     * @param firstBlockID La dirección del primer bloque a liberar.
     */
    public void deallocateBlocks(int firstBlockID) {
        int currentBlockID = firstBlockID;

        while (currentBlockID != -1) {
            
            // Se usa buscarPorIndice para "saltar" a la dirección del bloque
            Nodo<Bloque> nodoActual = this.bloquesDeDisco.buscarPorIndice(currentBlockID); 

            if (nodoActual == null) {
                logger.warning("Bloque ID " + currentBlockID + " no encontrado durante la liberación.");
                break; 
            }

            Bloque currentBlock = nodoActual.getDato();
            int nextBlockID = currentBlock.getPunteroSiguiente(); 

            currentBlock.liberar();
            this.bloquesLibres++;

            currentBlockID = nextBlockID; // Moverse al siguiente bloque en la cadena
        }
    }

    // --- Métodos de Consulta ---
    
    public int getBloquesLibres() { return bloquesLibres; }
    public int getCapacidadTotal() { return capacidadBloques; }
    public ListaEnlazada<Archivo> getListaArchivos() { return listaArchivos; }
    public ListaEnlazada<Bloque> getBloquesDeDisco() { return bloquesDeDisco; }
    
    /**
     * Obtener un bloque por su ID (dirección), usando la búsqueda por índice.
     * @param blockID La dirección del bloque.
     * @return El objeto Bloque o null si no existe.
     */
    public Bloque getBlock(int blockID) {
        if (blockID >= 0 && blockID < capacidadBloques) {
            // Costo O(N) para acceder a un bloque específico.
            Nodo<Bloque> nodo = this.bloquesDeDisco.buscarPorIndice(blockID); 
            if (nodo != null) {
                return nodo.getDato();
            }
        }
        return null; 
    }
}