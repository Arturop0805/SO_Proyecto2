/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Disco;
import Utilidades.SimuladorSetup;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel; // Necesario para trabajar con el Root

/**
 * Controlador principal. Centraliza la inicialización y acceso al Sistema 
 * de Archivos (FS) y al Disco, además de gestionar la sesión del usuario.
 */
public class Simulador { 
    
    private AdministradorDirectorios adminFS;
    private Disco discoSimulado;
    private String TipoUsuario;
    
    /**
     * Constructor que delega la inicialización del modelo a SimuladorSetup.
     */
    public Simulador() {
        // Usa la clase de setup para inicializar todo el modelo
        SimuladorSetup setup = new SimuladorSetup();
        
        // 1. Inicializa el Sistema de Archivos de prueba (crea DirectorioEntrada y asigna bloques en el Disco)
        this.adminFS = setup.setupSistemaArchivosPrueba();
        
        // 2. Obtiene las instancias inicializadas
        this.discoSimulado = setup.getDiscoSimulado();
        this.TipoUsuario = setup.getTipoUsuario();
    }
    
    /**
     * Retorna el nodo raíz (DefaultMutableTreeNode) para que la Vista pueda 
     * construir el DefaultTreeModel. Este método actúa como puente entre el Modelo 
     * (DirectorioEntrada) y la Vista (JTree).
     * @return El nodo raíz listo para ser usado en el JTree.
     */
    public DefaultMutableTreeNode crearArbolPrueba() {
        // Obtenemos el DefaultTreeModel y luego su raíz, asumiendo que 
        // AdministradorDirectorios tiene la lógica de conversión recursiva.
        DefaultTreeModel model = this.adminFS.obtenerModeloArbol();
        if (model != null && model.getRoot() instanceof DefaultMutableTreeNode) {
            return (DefaultMutableTreeNode) model.getRoot();
        }
        return new DefaultMutableTreeNode("Error: Raíz no disponible");
    }
    
    // ----------------------------------------------------------------------
    // --- Gestión de Usuario ---
    // ----------------------------------------------------------------------
    
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
    
    // ----------------------------------------------------------------------
    // --- Acceso a Controladores/Modelos ---
    // ----------------------------------------------------------------------
    
    public AdministradorDirectorios getAdminFS() {
        return adminFS;
    }
    
    public Disco getDiscoSimulado() {
        return discoSimulado;
    }
}