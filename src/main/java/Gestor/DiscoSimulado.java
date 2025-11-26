/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gestor;


import EstructurasDeDatos.ListaEnlazada;
import Modelo.Archivo;
import Modelo.Bloque;
import EstructurasDeDatos.Nodo;

/**
 * Simula el disco de almacenamiento y gestiona la asignación encadenada.
 * Se utiliza ListaEnlazada para los bloques del disco para cumplir con la restricción
 * de no usar arreglos estáticos (Bloque[]).
 */
public class DiscoSimulado {
    
    private final int CAPACIDAD_MAXIMA = 100; // Tamaño limitado del disco
    
    // CORRECCIÓN: Se reemplaza el arreglo estático (Bloque[]) por ListaEnlazada<Bloque>
    private ListaEnlazada<Bloque> bloques; 
    
    private ListaEnlazada<Integer> bloquesLibres; // Usamos ListaEnlazada para los índices libres 

    public DiscoSimulado() {
        // Inicialización de la lista de bloques
        this.bloques = new ListaEnlazada<>();
        this.bloquesLibres = new ListaEnlazada<>();
        inicializarDisco();
    }

    private void inicializarDisco() {
        for (int i = 0; i < CAPACIDAD_MAXIMA; i++) {
            // Se añaden los nuevos bloques a la lista enlazada
            bloques.agregar(new Bloque());
            bloquesLibres.agregar(i); // Todos los bloques inician libres
        }
    }

    /**
     * Asigna los bloques necesarios para un nuevo archivo usando Asignación Encadenada.
     * @param archivo Archivo al que se le asignarán los bloques.
     * @return true si se asignaron todos los bloques, false si no hay espacio.
     */
    public boolean asignarBloques(Archivo archivo) {
        int bloquesNecesarios = archivo.getTamano();
        if (bloquesLibres.getTamano() < bloquesNecesarios) {
            return false; // No hay suficiente espacio libre
        }

        int anteriorBloqueIndex = -1;
        int primerBloqueIndex = -1;

        for (int i = 0; i < bloquesNecesarios; i++) {
            // El índice del bloque libre se toma del inicio (FIFO)
            int indiceBloque = bloquesLibres.eliminarPrimero();    
            
            if (i == 0) {
                primerBloqueIndex = indiceBloque;
            }
            
            // ACCESO CORREGIDO: Usamos .obtener(indiceBloque) en lugar de bloques[indiceBloque]
            Bloque bloqueActual = bloques.obtener(indiceBloque); 
            bloqueActual.ocupar(archivo.getProcesoCreadorID()); 

            // Implementación del encadenamiento: actualizar el puntero del bloque anterior
            if (anteriorBloqueIndex != -1) {
                // ACCESO CORREGIDO: Usamos .obtener(anteriorBloqueIndex)
                bloques.obtener(anteriorBloqueIndex).setIndiceSiguienteBloque(indiceBloque);
            }
            anteriorBloqueIndex = indiceBloque;
        }
        
        // El último bloque de la cadena debe apuntar a -1 (final)
        if (anteriorBloqueIndex != -1) {
            // ACCESO CORREGIDO: Usamos .obtener(anteriorBloqueIndex)
            bloques.obtener(anteriorBloqueIndex).setIndiceSiguienteBloque(-1);
        }

        // Asignar solo el índice del primer bloque al archivo
        archivo.setPrimerBloque(primerBloqueIndex);
        
        return true;
    }

    /**
     * Libera todos los bloques asociados a un archivo, recorriendo la cadena de punteros
     * almacenados en los bloques.
     * * @param archivo Archivo cuyos bloques deben ser liberados.
     */
    public void liberarBloques(Archivo archivo) {
        int indiceActual = archivo.getPrimerBloque();
        
        if (indiceActual == -1) {
            return;
        }

        int siguienteIndice;
        
        // Iteramos a través de la cadena de bloques usando los punteros internos
        while (indiceActual != -1) {
            // ACCESO CORREGIDO: Usamos .obtener(indiceActual) en lugar de bloques[indiceActual]
            Bloque bloqueActual = bloques.obtener(indiceActual); 
            
            siguienteIndice = bloqueActual.getIndiceSiguienteBloque();
            
            // 1. Liberar el bloque físico y devolverlo a la lista de libres
            bloqueActual.liberar();
            bloquesLibres.agregar(indiceActual);
            
            // 2. Moverse al siguiente bloque de la cadena
            indiceActual = siguienteIndice;
        }
        
        // Limpiar el estado del archivo
        archivo.setPrimerBloque(-1); // Marcar archivo como sin bloques asignados
    }
    
    // --- Getters de estado para la GUI ---

    // ACCESO CORREGIDO: El método ahora devuelve la ListaEnlazada<Bloque>
    public ListaEnlazada<Bloque> getBloques() { return bloques; } 
    public int getCapacidadMaxima() { return CAPACIDAD_MAXIMA; }
    public int getBloquesLibresCount() { return bloquesLibres.getTamano(); }
}