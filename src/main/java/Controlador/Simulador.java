/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.Disco;
import Utilidades.SimuladorSetup;
import javax.swing.tree.DefaultMutableTreeNode; 
import javax.swing.tree.DefaultTreeModel; 

/**
 * Controlador principal. Centraliza la inicialización y acceso al Sistema 
 * de Archivos (FS) y al Disco, además de gestionar la sesión del usuario.
 */
public class Simulador { 
    
    private AdministradorDirectorios adminFS;
    private Disco discoSimulado;
    private String tipoUsuario;
    
    // Ciclo de usuarios simplificado (ADMIN y USER)
    private static final String USUARIO_ADMIN = "ADMIN";
    private static final String USUARIO_STANDARD = "USER";
    
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
        this.tipoUsuario = setup.getTipoUsuario(); // Inicia como "ADMIN" o lo que defina el setup
    }
    
    /**
     * Retorna el DefaultTreeModel completo para que la Vista (JTree) pueda 
     * mostrar la jerarquía de archivos.
     * @return El modelo de árbol listo para ser usado en el JTree.
     */
    public DefaultTreeModel crearArbolPrueba() {
        // El AdministradorDirectorios ya contiene la lógica de conversión recursiva
        // y devuelve el modelo de árbol completo.
        return this.adminFS.obtenerModeloArbol();
    }
    
    // ----------------------------------------------------------------------
    // --- Gestión de Usuario ---
    // ----------------------------------------------------------------------
    
    /**
     * Alterna el tipo de usuario entre ADMIN y USER.
     * 👈 CORRECCIÓN: Método renombrado a camelCase.
     */
    public void cambiarTipoUsuario(){
        if (this.tipoUsuario.equals(USUARIO_ADMIN)) {
            this.tipoUsuario = USUARIO_STANDARD;
        } else {
            this.tipoUsuario = USUARIO_ADMIN;
        }
    }
            
    public String getTipoUsuario() {
        return this.tipoUsuario;
    }
    
    // ----------------------------------------------------------------------
    // --- Acceso a Controladores/Modelos (Getters) ---
    // ----------------------------------------------------------------------
    
    public AdministradorDirectorios getAdminFS() {
        return adminFS;
    }
    
    public Disco getDiscoSimulado() {
        return discoSimulado;
    }

}