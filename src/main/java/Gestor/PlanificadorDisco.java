/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Gestor;

import Controlador.Proceso;
import EstructurasDeDatos.ListaEnlazada;
import EstructurasDeDatos.Nodo;

/**
 * Gestiona la cola de solicitudes de E/S y aplica las políticas de planificación de disco.
 */
public class PlanificadorDisco {
    
    public enum Politica {
        FIFO, SSTF, SCAN, C_SCAN // Mínimo 4 políticas [cite: 51]
    }
    
    private ListaEnlazada<Proceso> colaES; // Cola de E/S [cite: 18]
    private Politica politicaActual;
    private int posicionCabezal; // Posición actual del cabezal del disco

    public PlanificadorDisco() {
        this.colaES = new ListaEnlazada<>();
        this.politicaActual = Politica.FIFO; // Política inicial
        this.posicionCabezal = 50; // Inicia a mitad de disco
    }

    /**
     * Añade una solicitud a la cola de E/S.
     * @param proceso El proceso que solicita la operación.
     */
    public void agregarSolicitud(Proceso proceso) {
        proceso.setEstado(Proceso.Estado.BLOQUEADO);
        colaES.agregar(proceso);
    }

    /**
     * Determina el próximo proceso a atender según la política activa[cite: 49].
     * @return El proceso a ejecutar o null si la cola está vacía.
     */
    public Proceso siguienteProceso() {
        if (colaES.estaVacia()) {
            return null;
        }

        switch (getPoliticaActual()) {
            case FIFO:
                return colaES.eliminarPrimero(); // Se atiende en el orden que llegaron
            case SSTF:
                return seleccionarSSTF();
            case SCAN:
                return seleccionarSCAN();
            case C_SCAN:
                return seleccionarCSCAN();
            default:
                return colaES.eliminarPrimero();
        }
    }
    
    private Proceso seleccionarSSTF() {
        // Implementación de Shortest Seek Time First (SSTF)
        // Busca la solicitud cuya posición de cilindro esté más cerca de la posición actual del cabezal.
        Proceso procesoSeleccionado = null;
        int menorDistancia = Integer.MAX_VALUE;
        int indiceSeleccionado = -1;
        
        Nodo<Proceso> actual = colaES.getCabeza();
        int i = 0;
        while (actual != null) {
            int distancia = Math.abs(actual.getDato().getPosicionCilindro() - posicionCabezal);
            if (distancia < menorDistancia) {
                menorDistancia = distancia;
                procesoSeleccionado = actual.getDato();
                indiceSeleccionado = i;
            }
            actual = actual.getSiguiente();
            i++;
        }
        
        // Como nuestra ListaEnlazada no tiene un 'eliminarEnIndice', necesitamos recrear una lista
        // (Esto es una limitación de la estructura base que debemos manejar)
        if (procesoSeleccionado != null) {
            // Reconstrucción de la lista sin el elemento seleccionado (INEFICIENTE pero cumple la restricción)
            ListaEnlazada<Proceso> nuevaCola = new ListaEnlazada<>();
            int j = 0;
            Nodo<Proceso> temp = colaES.getCabeza();
            while (temp != null) {
                if (j != indiceSeleccionado) {
                    nuevaCola.agregar(temp.getDato());
                }
                temp = temp.getSiguiente();
                j++;
            }
            colaES = nuevaCola;
            
            posicionCabezal = procesoSeleccionado.getPosicionCilindro(); // Mover cabezal
            return procesoSeleccionado;
        }
        return null;
    }

    private Proceso seleccionarSCAN() { /* Implementación de SCAN */ return null; }
    private Proceso seleccionarCSCAN() { /* Implementación de C-SCAN */ return null; }

    // --- Getters y Setters ---
    
    public void setPoliticaActual(Politica politicaActual) {
        this.politicaActual = politicaActual;
    }
    public ListaEnlazada<Proceso> getColaES() { return colaES; }

    /**
     * @return the politicaActual
     */
    public Politica getPoliticaActual() {
        return politicaActual;
    }
}
