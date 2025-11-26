/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Gestor.SistemaArchivos; // Contiene la instancia del AdministradorDirectorios
import Gestor.SistemaArchivos.ModoUsuario; // Para la gestión de permisos
import javax.swing.tree.DefaultTreeModel;
import Modelo.Directorio;

/**
 * Controlador principal. Centraliza la inicialización y acceso al Sistema 
 * de Archivos (FS) y al Disco, además de gestionar la sesión del usuario.
 * Nota: Ya no contiene la lógica directa de construcción del JTree, la delega 
 * a AdministradorDirectorios.
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
     */
    public Simulador() {
        // Inicializa el sistema de archivos completo (Disco, Raíz, Planificador)
        this.sistemaArchivos = new SistemaArchivos();

        // El modo de usuario se sincroniza con el enum en SistemaArchivos
        // Asume que SistemaArchivos.getModoActual() existe y retorna el ModoUsuario.
        this.tipoUsuario = (sistemaArchivos.getModoActual() == ModoUsuario.ADMINISTRADOR)
                            ? USUARIO_ADMIN : USUARIO_STANDARD;

        System.out.println("Simulador inicializado. Modo: " + this.tipoUsuario);
    }

    // ----------------------------------------------------------------------
    // --- Métodos de Interfaz (JTree) ---
    // ----------------------------------------------------------------------

    /**
     * Retorna el DefaultTreeModel completo, delegando la construcción
     * a AdministradorDirectorios. Este es el método que la Vista (JTree) debe llamar.
     * @return El modelo de árbol listo para ser usado en el JTree.
     */
    public DefaultTreeModel obtenerModeloArbolFS() {
        // DELEGACIÓN CLAVE: Llama al método que ahora sí sabe construir el árbol.
        return this.getAdminFS().ge;
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
            // Asume que el enum ModoUsuario contiene USUARIO y ADMINISTRADOR
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
     * Retorna la instancia del Administrador de Directorios para operaciones 
     * CRUD y visualización de árbol.
     * @return La instancia de AdministradorDirectorios.
     */
    public Directorio getAdminFS() {
        // Asumiendo que SistemaArchivos expone su AdministradorDirectorios
        return this.sistemaArchivos.getRaiz();
    }

    /**
     * Devuelve la instancia central del sistema para acceso a Disco y Planificador.
     * @return La instancia de SistemaArchivos.
     */
    public SistemaArchivos getSistemaArchivos() {
        return sistemaArchivos;
    }
}