/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.EstructuraArchivo;
import Modelo.Directorio;
import Modelo.Archivo; 
import Gestor.DiscoSimulado; 
import EstructurasDeDatos.Nodo; 
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.JOptionPane;


/**
 * Clase controladora para gestionar la jerarquía de directorios/archivos.
 * Contiene la lógica de permisos, asignación de bloques y construcción del JTree.
 */
public class AdministradorDirectorios {

    private Directorio raiz; 
    private DiscoSimulado disco; 
    
    // Constantes de Permisos
    private static final String USUARIO_ROOT = "ROOT";
    private static final String USUARIO_ADMIN = "ADMIN";
    private static final String USUARIO_STANDARD = "USER";
    
    /**
     * Constructor para inicializar el Administrador.
     */
    public AdministradorDirectorios(String nombreRaiz, String propietario, DiscoSimulado disco) {
        // ASUNCIÓN: Se utiliza el constructor de Directorio corregido para recibir propietario.
        this.raiz = new Directorio(nombreRaiz, propietario); 
        this.disco = disco; 
    }
    
    // --- Métodos de Interfaz (JTree) ---

    // Método público para obtener el modelo final del JTree
    public DefaultTreeModel obtenerModeloArbol() {
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(this.raiz);
        this.construirArbolRecursivo(this.raiz, rootNode);
        return new DefaultTreeModel(rootNode);
    }

    /**
     * Función recursiva que itera sobre los hijos de Directorio
     * y crea la jerarquía de DefaultMutableTreeNode.
     */
    private void construirArbolRecursivo(Directorio padreModelo, DefaultMutableTreeNode padreNodoSwing) {
        
        Nodo<EstructuraArchivo> auxiliar = padreModelo.getContenidos().getCabeza();
        
        while (auxiliar != null) {
            EstructuraArchivo hijoModelo = auxiliar.getDato();
            
            DefaultMutableTreeNode hijoNodoSwing = new DefaultMutableTreeNode(hijoModelo);
            padreNodoSwing.add(hijoNodoSwing);
            
            // Llamada recursiva: si el hijo es un Directorio, seguimos bajando
            if (hijoModelo.esDirectorio()) {
                construirArbolRecursivo((Directorio) hijoModelo, hijoNodoSwing); 
            }
            
            auxiliar = auxiliar.getSiguiente();
        }
    }
    
    // --- Métodos de Operación (Crear, Eliminar, Validar) ---

    /**
     * Crea un nuevo Directorio o Archivo, valida permisos y lo registra en la jerarquía.
     */
    public EstructuraArchivo crearEntrada(String nombre, boolean esDirectorio, int tamañoBloques, Directorio padre, String usuarioActual) {
        
        // ... (Validaciones de permisos y duplicados)
        if (usuarioActual.equalsIgnoreCase(USUARIO_STANDARD)) {
             JOptionPane.showMessageDialog(null, "Permiso denegado: Los usuarios estándar no pueden crear entradas.", "Error de Permisos", JOptionPane.ERROR_MESSAGE);
             return null;
        }
        
        if (!padre.esDirectorio()) {
            JOptionPane.showMessageDialog(null, "El padre seleccionado no es un directorio.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
        Nodo<EstructuraArchivo> auxiliar = padre.getContenidos().getCabeza();
        while (auxiliar != null) {
            EstructuraArchivo hijo = auxiliar.getDato();
            if (hijo.getNombre().equalsIgnoreCase(nombre)) {
                JOptionPane.showMessageDialog(null, "Ya existe una entrada con el nombre '" + nombre + "' en este directorio.", "Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            auxiliar = auxiliar.getSiguiente();
        }

        if (!esDirectorio && !verificarEspacio(tamañoBloques)) {
            JOptionPane.showMessageDialog(null, "No hay suficiente espacio libre en el disco para este archivo.", "Error de Espacio", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
        // 3. CREACIÓN E INSTANCIACIÓN
        EstructuraArchivo nuevaEntrada;
        
        if (esDirectorio) {
            // Se asume el constructor corregido de Directorio: new Directorio(nombre, propietario)
            nuevaEntrada = new Directorio(nombre, usuarioActual);
            
        } else {
            // LÓGICA CRÍTICA DE ASIGNACIÓN:
            
            // 3.1. Adaptar el constructor de Archivo a: (nombre, tamanoEnBloques, procesoCreadorID, color)
            // Ya que no tenemos "color" ni "propietario" directo en el constructor de Archivo, 
            // ASUMIMOS: procesoCreadorID=1 y usamos usuarioActual como el parámetro "color" (a corregir en el modelo).
            final int PROCESO_ID_SIMULADO = 1;
            
            // CRÍTICO: Aquí pasamos el usuario actual donde Archivo espera el color.
            Archivo nuevoArchivo = new Archivo(nombre, tamañoBloques, PROCESO_ID_SIMULADO, usuarioActual /* Usado como color/propietario */);
            
            // 3.2. Asignar bloques en el disco
            boolean asignacionExitosa = this.disco.asignarBloques(nuevoArchivo);
            
            if (!asignacionExitosa) {
                JOptionPane.showMessageDialog(null, "Error al asignar bloques en el disco (Espacio/Fragmentación).", "Error de Asignación", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            
            // La asignación fue exitosa, Archivo ya tiene el primer bloque y el ID actualizado.
            nuevaEntrada = nuevoArchivo;
        }
        
        // 4. REGISTRO EN LA JERARQUÍA DEL PADRE
        padre.agregarContenido(nuevaEntrada); 

        return nuevaEntrada;
    }
    
    /**
     * Elimina una entrada del directorio y libera sus bloques asociados en el disco.
     */
    public boolean eliminarEntrada(EstructuraArchivo entrada, Directorio padre, String usuario) {
        
        // 1. Validaciones
        if (entrada.getNombre().equals(this.raiz.getNombre())) {
             JOptionPane.showMessageDialog(null, "No se puede eliminar la raíz del sistema de archivos.", "Error", JOptionPane.ERROR_MESSAGE);
             return false;
        }
        
        if (!verificarPermisos(entrada, usuario, "DELETE")) {
             JOptionPane.showMessageDialog(null, "Permiso denegado para eliminar la entrada: '" + entrada.getNombre() + "'.", "Error de Permisos", JOptionPane.ERROR_MESSAGE);
             return false;
        }
        
        if (entrada.esDirectorio()) {
             if (((Directorio)entrada).getContenidos().getTamano() > 0) {
                 JOptionPane.showMessageDialog(null, "No se puede eliminar un directorio no vacío.", "Error", JOptionPane.ERROR_MESSAGE);
                 return false;
             }
        }
        
        // 2. Liberación de bloques del disco (CRÍTICO)
        if (!entrada.esDirectorio()) {
            Archivo archivoAEliminar = (Archivo) entrada;
            // CORRECCIÓN: Usar getPrimerBloque() para verificar si tiene bloques.
            if (archivoAEliminar.getPrimerBloque() != -1) {
                this.disco.liberarBloques(archivoAEliminar);
            }
        }
        
        // 3. Eliminación de la Lista Enlazada del padre
        // Se asume que el método eliminar de ListaEnlazada es compatible con el dato.
        return padre.eliminarContenido(entrada.getNombre()); 
    }
    
    // --- Métodos de Utilidad ---
    
    private boolean verificarPermisos(EstructuraArchivo entrada, String usuario, String accion) {
        // ADVERTENCIA: Esta lógica necesita que EstructuraArchivo tenga un método getPropietario()
        // implementado y que Archivo lo herede correctamente.
        if (usuario.equalsIgnoreCase(USUARIO_ROOT) || usuario.equalsIgnoreCase(USUARIO_ADMIN)) {
             return true;
        }
        
        // ASUMIMOS que getPropietario() existe y funciona.
        if (entrada.getPropietario().equalsIgnoreCase(usuario)) {
             return true;
        }
        
        if (accion.equals("DELETE")) {
             return entrada.getPropietario().equalsIgnoreCase(usuario);
        }
        
        return false;
    }
    
    private boolean verificarEspacio(int bloquesRequeridos) {
        if (bloquesRequeridos <= 0) {
            return true;
        }
        // Usar el método de DiscoSimulado.
        return this.disco.getBloquesLibresCount() >= bloquesRequeridos;
    }
    
    public Directorio getRaiz() { return this.raiz; } 
    
    public DiscoSimulado getDisco() { return this.disco; }
}