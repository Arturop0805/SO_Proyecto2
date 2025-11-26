/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gestor;

import Modelo.Bloque;
import Modelo.Archivo;
import EstructurasDeDatos.ListaEnlazada;
import Modelo.Archivo;
import Modelo.Bloque;
import EstructurasDeDatos.Nodo;

/**
 * Simula el disco de almacenamiento y gestiona la asignación encadenada.
 */
public class DiscoSimulado {
    
    private final int CAPACIDAD_MAXIMA = 100; // Tamaño limitado del disco [cite: 34]
    private Bloque[] bloques; // Usamos array para el disco físico, no requiere ser lista enlazada.
    private ListaEnlazada<Integer> bloquesLibres; // Usamos ListaEnlazada para los libres 

    public DiscoSimulado() {
        this.bloques = new Bloque[CAPACIDAD_MAXIMA];
        this.bloquesLibres = new ListaEnlazada<>();
        inicializarDisco();
    }

    private void inicializarDisco() {
        for (int i = 0; i < CAPACIDAD_MAXIMA; i++) {
            bloques[i] = new Bloque();
            bloquesLibres.agregar(i); // Todos los bloques inician libres
        }
    }

    /**
     * Asigna los bloques necesarios para un nuevo archivo usando Asignación Encadenada.
     * @param archivo Archivo al que se le asignarán los bloques.
     * @return true si se asignaron todos los bloques, false si no hay espacio[cite: 34].
     */
    public boolean asignarBloques(Archivo archivo) {
        int bloquesNecesarios = archivo.getTamano();
        if (bloquesLibres.getTamano() < bloquesNecesarios) {
            return false; // No hay suficiente espacio libre
        }

        // Usamos una lista temporal para guardar los índices asignados antes de la confirmación
        ListaEnlazada<Integer> indicesAsignados = new ListaEnlazada<>();
        int anteriorBloqueIndex = -1;

        for (int i = 0; i < bloquesNecesarios; i++) {
            // El índice del bloque libre se toma del inicio (FIFO)
            int indiceBloque = bloquesLibres.eliminarPrimero(); 
            
            Bloque bloqueActual = bloques[indiceBloque];
            bloqueActual.ocupar(archivo.getProcesoCreadorID()); // Ocupar bloque

            // Implementación del encadenamiento: actualizar el bloque anterior
            if (anteriorBloqueIndex != -1) {
                bloques[anteriorBloqueIndex].setIndiceSiguienteBloque(indiceBloque);
            }
            anteriorBloqueIndex = indiceBloque;
            indicesAsignados.agregar(indiceBloque);
        }
        
        // El último bloque de la cadena debe apuntar a -1 (final)
        if (anteriorBloqueIndex != -1) {
            bloques[anteriorBloqueIndex].setIndiceSiguienteBloque(-1);
        }

        // Asignar los índices al archivo
        archivo.setPrimerBloque(indicesAsignados.obtener(0));
        
        // Copiamos los índices a la lista interna del Archivo para persistencia/referencia
        Nodo<Integer> actual = indicesAsignados.getCabeza();
        while(actual != null) {
            archivo.agregarBloque(actual.getDato());
            actual = actual.getSiguiente();
        }
        
        return true;
    }

    /**
     * Libera todos los bloques asociados a un archivo[cite: 33].
     * @param archivo Archivo cuyos bloques deben ser liberados.
     */
    public void liberarBloques(Archivo archivo) {
        Nodo<Integer> actual = archivo.getIndicesBloques().getCabeza();
        while (actual != null) {
            int indice = actual.getDato();
            bloques[indice].liberar();
            bloquesLibres.agregar(indice); // Devolver el bloque a la lista de libres
            actual = actual.getSiguiente();
        }
        archivo.setPrimerBloque(-1); // Marcar archivo como sin bloques asignados
        // La lista de bloques del Archivo queda vacía automáticamente
    }
    
    // --- Getters de estado para la GUI ---

    public Bloque[] getBloques() { return bloques; }
    public int getCapacidadMaxima() { return CAPACIDAD_MAXIMA; }
    public int getBloquesLibresCount() { return bloquesLibres.getTamano(); }
}