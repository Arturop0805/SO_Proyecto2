/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import EstructurasDeDatos.ListaEnlazada;
import EstructurasDeDatos.Nodo;

/**
 * Simula el almacenamiento físico del disco, gestionando la asignación
 * de bloques mediante el método de Asignación Encadenada.
 * Utiliza la ListaEnlazada del usuario para simular el arreglo de bloques.
 */
public class Disco {
    
    // Atributos
    private final int capacidadBloques;
    // CRÍTICO: Lista Enlazada para simular el almacenamiento físico (O(n) en acceso por ID)
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
            this.sdBlocks.Insertar(nuevoBloque); 
        }
        this.bloquesLibres = this.capacidadBloques;
    }

    // --- Métodos de Gestión de Asignación Encadenada ---

    /**
     * Busca y asigna bloques para un nuevo archivo usando el método encadenado.
     * Recorre la Lista Enlazada para encontrar bloques libres y los enlaza.
     * @param numBloques Cantidad de bloques requeridos.
     * @param fileName Nombre del archivo a asignar.
     * @param processID ID del proceso que realiza la asignación.
     * @return La dirección (blockID) del primer bloque asignado, o -1 si no hay espacio.
     */
    public int allocateBlocks(int numBloques, String fileName, int processID) {

        if (numBloques <= 0 || numBloques > this.bloquesLibres) {
            System.out.println("ERROR: Espacio insuficiente en el disco o tamaño no válido.");
            return -1; 
        }

        int blocksToFind = numBloques;
        int firstBlockID = -1;

        // CRÍTICO: Usamos un Nodo para mantener la referencia al bloque anterior asignado
        Nodo<Bloque> previousBlockNode = null; 

        // 1. Obtener el nodo inicial para comenzar el recorrido (O(n) por buscarPorIndice(0))
        // Se asume que ListaEnlazada.buscarPorIndice(0) retorna el primer Nodo.
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
                    // 4. Establecer el puntero del último bloque como FIN DE ARCHIVO (-1)
                    currentBlock.setPunteroSiguiente(-1);
                    return firstBlockID;
                }
            }
            auxiliar = auxiliar.getSiguiente();
        }

        // Esta sección solo se alcanza si la pre-verificación de bloques libres fue incorrecta.
        // Se asume que no debería ocurrir si bloquesLibres se gestiona bien.
        return -1; 
    }

    /**
     * Libera todos los bloques de un archivo, siguiendo la cadena de punteros.
     * @param firstBlockID La dirección del primer bloque a liberar.
     */
    public void deallocateBlocks(int firstBlockID) {
        int currentBlockID = firstBlockID;

        while (currentBlockID != -1) {
            // 1. Se usa buscarPorIndice para "saltar" a la dirección del bloque
            // CRÍTICO: Esto hace que la liberación sea O(N * n_blocks) en el peor caso.
            Nodo<Bloque> nodoActual = (Nodo<Bloque>) this.sdBlocks.buscarPorIndice(currentBlockID);

            if (nodoActual == null) break; 

            Bloque currentBlock = nodoActual.getDato();
            int nextBlockID = currentBlock.getPunteroSiguiente(); // Guarda el puntero antes de liberar

            currentBlock.liberar();
            this.bloquesLibres++;

            currentBlockID = nextBlockID; // Moverse al siguiente bloque en la cadena
        }
    }

    // --- Métodos de Consulta ---
    
    public int getBloquesLibres() {
        return bloquesLibres;
    }
    
    public int getCapacidadTotal() {
        return capacidadBloques;
    }
    
    /**
     * Obtener un bloque por su ID (dirección), usando la búsqueda por índice.
     * @param blockID La dirección del bloque.
     * @return El objeto Bloque o null si no existe.
     */
    public Bloque getBlock(int blockID) {
        if (blockID >= 0 && blockID < capacidadBloques) {
            // CRÍTICO: Costo O(N) para acceder a un bloque específico.
            Nodo<Bloque> nodo = (Nodo<Bloque>) this.sdBlocks.buscarPorIndice(blockID);
            if (nodo != null) {
                return nodo.getDato();
            }
        }
        return null; 
    }
}
