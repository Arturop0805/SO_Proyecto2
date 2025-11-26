/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Gestor.SistemaArchivos;
import EstructurasDeDatos.ListaEnlazada; 
import Vista.Modelado_Proc; 

/**
 * Clase controladora que gestiona la simulación de procesos (Planificador de CPU)
 * y su interacción con el Planificador de Disco.
 */
public class SimuladorProcesos {
    
    // Lista Enlazada para simular la Cola de Listos (Ready Queue) del CPU
    private ListaEnlazada<Proceso> colaListos;    
    private SistemaArchivos sistemaArchivos;
    private Modelado_Proc vistaProcesos;

    /**
     * Constructor del Simulador de Procesos.
     * @param vista La vista Modelado_Proc para actualizar la interfaz.
     * @param sa La instancia del SistemaArchivos que contiene el Disco y el Planificador de Disco.
     */
    public SimuladorProcesos(Modelado_Proc vista, SistemaArchivos sa) {
        this.vistaProcesos = vista;
        this.sistemaArchivos = sa;
        this.colaListos = new ListaEnlazada<>();
    }
    
    /**
     * Inicia la simulación: intenta ejecutar el siguiente proceso de E/S
     * según la política de planificación de disco activa.
     */
    public void iniciarSimulacion() {
        System.out.println("Iniciando ciclo de planificación de E/S...");
        
        // 1. Obtener el próximo proceso a atender según el Planificador de Disco
        Proceso procesoES = sistemaArchivos.getPlanificador().siguienteProceso();
        
        if (procesoES != null) {
            
            // 2. Simular la ejecución de la operación de E/S
            boolean exito = sistemaArchivos.ejecutarOperacion(procesoES);
            
            // 3. Actualizar la vista con el nuevo estado
            if (exito) {
                System.out.println("Operación de E/S para P" + procesoES.getId() + " completada.");
            } else {
                System.out.println("Operación de E/S para P" + procesoES.getId() + " falló o no tenía permiso.");
            }
            
            // Notificar a la vista
            vistaProcesos.actualizarBoton(procesoES);    
            
        } else {
            System.out.println("Cola de E/S vacía. Esperando nuevas solicitudes.");
        }
    }
    
    /**
     * Notifica a la vista sobre el cambio de estado de un proceso.
     * @param proceso El proceso cuyo estado ha cambiado.
     */
    public void notificarCambioDeEstado(Proceso proceso) {
        vistaProcesos.actualizarBoton(proceso);
    }
    
    /**
     * Agrega un proceso (que no es de E/S) a la cola de listos del CPU.
     */
    public void agregarProcesoAListos(Proceso proceso) {
        // Estado se refiere a Proceso.Estado, accesible directamente por estar en el mismo paquete
        proceso.setEstado(Proceso.Estado.LISTO);    
        this.colaListos.agregar(proceso);
    }
    
    // --- Métodos de utilidad para la simulación ---
    
    public SistemaArchivos getSistemaArchivos() {
        return sistemaArchivos;
    }
}