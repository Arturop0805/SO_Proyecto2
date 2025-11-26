/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;


import Modelo.Disco;
import Utilidades.SimuladorSetup;
import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Controlador principal. Centraliza la inicialización y acceso al Sistema 
 * de Archivos (FS) y al Disco, además de gestionar la sesión del usuario.
 */
public class Simulador { 
    
    private AdministradorDirectorios adminFS;
    private Disco discoSimulado;
    private String TipoUsuario;
    
    public Simulador() {
        // Usa la clase de setup para inicializar todo el modelo
        SimuladorSetup setup = new SimuladorSetup();
        this.adminFS = setup.setupSistemaArchivosPrueba();
        this.discoSimulado = setup.getDiscoSimulado();
        this.TipoUsuario = setup.getTipoUsuario();
    }
    
    /**
     * Retorna el nodo raíz (DefaultMutableTreeNode) para que la Vista pueda 
     * construir el DefaultTreeModel. Este método reemplaza la antigua lógica
     * de construcción manual.
     */
    public DefaultMutableTreeNode crearArbolPrueba() {
        // La llamada a obtenerModeloArbol() invoca la función recursiva
        // que construye el árbol Swing a partir de las DirectorioEntrada.
        return (DefaultMutableTreeNode) this.adminFS.obtenerModeloArbol().getRoot();
    }
    
    // --- Gestión de Usuario (Se mantiene) ---
    
    public void CambiarTipoUsuario(){
        if (this.TipoUsuario.equals("ADMIN")) {
            this.TipoUsuario = "USER";
        } else {
            this.TipoUsuario = "ADMIN";
        }
    }
            
    public String getTipoUsuario() {
        return this.TipoUsuario;
    }
    
    // --- Acceso a Controladores para futuras operaciones (e.g., Agregar/Eliminar) ---
    public AdministradorDirectorios getAdminFS() {
        return adminFS;
    }
    
    public Disco getDiscoSimulado() {
        return discoSimulado;
    }
}