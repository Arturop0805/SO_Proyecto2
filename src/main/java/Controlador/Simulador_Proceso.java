/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author yange
 */
// En Controlador/SimuladorProcesos.java
public class SimuladorProcesos {
    private java.util.List<Proceso> listaProcesos;
    // ... Colas de planificación (Ready Queue, Blocked Queue)
    
    // Referencia a la vista para actualizar los botones
    private Vista.Modelado_Proc vistaProcesos; 
    
    public SimuladorProcesos(Vista.Modelado_Proc vista) {
        this.vistaProcesos = vista;
        this.listaProcesos = new java.util.ArrayList<>();
    }
    
    public void iniciarSimulacion() {
        // Lógica de planificación (e.g., Round Robin, FCFS)
        // Ejemplo:
        // Proceso p = planificador.siguienteProceso();
        // p.setEstado(EstadoProceso.EN_EJECUCION);
        // vistaProcesos.actualizarVista(p);
    }
    
    // Método clave para actualizar la interfaz
    public void notificarCambioDeEstado(Proceso proceso) {
        // Llama a un método en la vista para cambiar el color/texto del botón
        vistaProcesos.actualizarBoton(proceso);
    }
    
    // ... Métodos para crear nuevos procesos ...
}