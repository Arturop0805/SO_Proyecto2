/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Gestor.SistemaArchivos; // Reemplaza Modelo.Disco
import Gestor.SistemaArchivos.ModoUsuario; // Para la gestión de permisos
import javax.swing.tree.DefaultMutableTreeNode; 
import javax.swing.tree.DefaultTreeModel; 
import Modelo.EstructuraArchivo;
import Modelo.Directorio;
import EstructurasDeDatos.Nodo;

/**
 * Controlador principal. Centraliza la inicialización y acceso al Sistema 
 * de Archivos (FS) y al Disco, además de gestionar la sesión del usuario.
 */
public class Simulador { 
    
    // Simplificamos la administración centralizando todo en SistemaArchivos
    private SistemaArchivos sistemaArchivos; 
    private String tipoUsuario; // Usaremos String para representar el modo
    
    // Ciclo de usuarios simplificado (ADMIN y USER)
    private static final String USUARIO_ADMIN = "ADMIN";
    private static final String USUARIO_STANDARD = "USER";
    
    /**
     * Constructor que inicializa el modelo del sistema de archivos.
     * En una implementación real, SimuladorSetup cargaría desde un archivo.
     */
    public Simulador() {
        // Inicializa el sistema de archivos completo (Disco, Raíz, Planificador)
        this.sistemaArchivos = new SistemaArchivos();
        
        // El modo de usuario se sincroniza con el enum en SistemaArchivos
        this.tipoUsuario = (sistemaArchivos.getModoActual() == ModoUsuario.ADMINISTRADOR) 
                            ? USUARIO_ADMIN : USUARIO_STANDARD;
                            
        // Aquí podrías llamar a una clase SimuladorSetup para cargar datos persistentes.
        System.out.println("Simulador inicializado. Modo: " + this.tipoUsuario);
    }
    
    /**
     * Retorna el DefaultTreeModel completo para que la Vista (JTree) pueda 
     * mostrar la jerarquía de archivos.
     * @return El modelo de árbol listo para ser usado en el JTree.
     */
    public DefaultTreeModel crearArbolPrueba() {
        // 1. Obtener el nodo raíz del sistema de archivos
        Directorio raiz = sistemaArchivos.getRaiz();
        
        // 2. Crear el nodo raíz del JTree
        DefaultMutableTreeNode jtreeRaiz = new DefaultMutableTreeNode(raiz.getNombre());
        
        // 3. Llenar el árbol recursivamente
        llenarNodo(jtreeRaiz, raiz);
        
        return new DefaultTreeModel(jtreeRaiz);
    }
    
    /**
     * Método recursivo para poblar el DefaultTreeModel a partir de la estructura Directorio/Archivo.
     * ESTE ES EL CÓDIGO CLAVE PARA LA VISUALIZACIÓN DEL JTREE.
     */
    private void llenarNodo(DefaultMutableTreeNode nodoPadreJTree, Directorio directorioActual) {
        Nodo<EstructuraArchivo> actual = directorioActual.getContenidos().getCabeza();
        
        while (actual != null) {
            EstructuraArchivo estructura = actual.getDato();
            
            // Crear el nodo del JTree, pasando el objeto de modelo para su uso posterior
            DefaultMutableTreeNode nuevoNodoJTree = new DefaultMutableTreeNode(estructura);
            nodoPadreJTree.add(nuevoNodoJTree);
            
            if (estructura.esDirectorio()) {
                // Llamada recursiva si es un subdirectorio
                llenarNodo(nuevoNodoJTree, (Directorio) estructura);
            }
            
            actual = actual.getSiguiente();
        }
    }
    
    // ----------------------------------------------------------------------
    // --- Gestión de Usuario ---
    // ----------------------------------------------------------------------
    
    /**
     * Alterna el tipo de usuario entre ADMIN y USER y actualiza el SistemaArchivos.
     */
    public void cambiarTipoUsuario(){
        if (this.tipoUsuario.equals(USUARIO_ADMIN)) {
            this.tipoUsuario = USUARIO_STANDARD;
            this.sistemaArchivos.setModoActual(ModoUsuario.USUARIO);
        } else {
            this.tipoUsuario = USUARIO_ADMIN;
            this.sistemaArchivos.setModoActual(ModoUsuario.ADMINISTRADOR);
        }
    }
        
    public String getTipoUsuario() {
        return this.tipoUsuario;
    }
    
    // ----------------------------------------------------------------------
    // --- Acceso a Controladores/Modelos (Getters) ---
    // ----------------------------------------------------------------------
    
    /**
     * Devuelve la instancia central del sistema para acceso a CRUD, Disco y Planificador.
     * @return La instancia de SistemaArchivos.
     */
    public SistemaArchivos getSistemaArchivos() {
        return sistemaArchivos;
    }
}