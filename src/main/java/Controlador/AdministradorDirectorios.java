/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

import Modelo.DirectorioEntrada;
import EstructurasDeDatos.Nodo; 
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import Modelo.Disco; 
import javax.swing.JOptionPane;


public class AdministradorDirectorios {

    private DirectorioEntrada raiz; 
    private Disco disco; // Campo de instancia para el disco
    
    
    /**
     * Constructor para inicializar el Administrador, creando la raíz del sistema
     * y estableciendo la referencia al disco físico.
     */
    public AdministradorDirectorios(String nombreRaiz, String propietario, Disco disco) {
        // Inicializa la raíz (que es un Directorio)
        // Directorio: numBloques=0, primerBloqueID=-1
        this.raiz = new DirectorioEntrada(nombreRaiz, true, 0, -1, propietario);
        this.disco = disco; // ✅ CORRECCIÓN 1: Asigna la referencia del Disco
    }
    
    // --- Métodos de Interfaz (JTree) ---

    // Método público para obtener el modelo final del JTree
    public DefaultTreeModel obtenerModeloArbol() {
        // 1. Crea el nodo raíz de Swing usando tu objeto DirectorioEntrada
        DefaultMutableTreeNode rootNode = new DefaultMutableTreeNode(this.raiz);
        
        // 2. Llama a la función recursiva para llenar el árbol
        this.construirArbolRecursivo(this.raiz, rootNode);
        
        // 3. Devuelve el modelo completo
        return new DefaultTreeModel(rootNode);
    }

    /**
     * Función recursiva que itera sobre la ListaHijos de DirectorioEntrada
     * y crea la jerarquía de DefaultMutableTreeNode.
     */
    private void construirArbolRecursivo(DirectorioEntrada padreModelo, DefaultMutableTreeNode padreNodoSwing) {
        
        // Solo los directorios tienen hijos
        if (padreModelo.getEsDirectorio()) {
            
            // Iterar sobre tu ListaEnlazada<DirectorioEntrada>
            // Usamos getInicio() de ListaEnlazada para comenzar el recorrido.
            Nodo<DirectorioEntrada> auxiliar = (Nodo<DirectorioEntrada>) padreModelo.ListaHijos.getInicio(); 
            
            while (auxiliar != null) {
                DirectorioEntrada hijoModelo = auxiliar.getDato();
                
                // 1. Crear el nuevo nodo de Swing y asignarle tu objeto de modelo
                DefaultMutableTreeNode hijoNodoSwing = new DefaultMutableTreeNode(hijoModelo);
                
                // 2. Añadir el nodo Swing hijo al nodo Swing padre
                padreNodoSwing.add(hijoNodoSwing);
                
                // 3. Llamada recursiva: si el hijo es un directorio, seguimos bajando
                if (hijoModelo.getEsDirectorio()) {
                    construirArbolRecursivo(hijoModelo, hijoNodoSwing);
                }
                
                // Avanzar al siguiente elemento de la ListaEnlazada
                auxiliar = auxiliar.getSiguiente(); 
            }
        }
    }
    
    // --- Métodos de Operación (Crear, Eliminar, Validar) ---

    /**
     * Crea un nuevo DirectorioEntrada (Archivo o Directorio), valida permisos,
     * asigna bloques en el Disco (si es archivo) y lo registra en la jerarquía.
     */
    public DirectorioEntrada crearEntrada(String nombre, boolean esDirectorio, int tamañoBloques, DirectorioEntrada padre, String tipoUsuario) {
        
        // --- 1. VALIDACIÓN DE PERMISOS ---
        // Usar un nombre de usuario real en lugar de 'tipoUsuario' sería mejor
        if (tipoUsuario.equalsIgnoreCase("USER")) {
            JOptionPane.showMessageDialog(null, "Permiso denegado: Los usuarios estándar no pueden crear entradas.", "Error de Permisos", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        // --- 2. VALIDACIONES DE SISTEMA DE ARCHIVOS ---
        if (!padre.getEsDirectorio()) {
             JOptionPane.showMessageDialog(null, "El padre seleccionado no es un directorio.", "Error", JOptionPane.ERROR_MESSAGE);
             return null;
        }
        
        // Verificar duplicados (O(n) - correcto para ListaEnlazada)
        Nodo<DirectorioEntrada> auxiliar = (Nodo<DirectorioEntrada>) padre.ListaHijos.getInicio();
        while (auxiliar != null) {
            DirectorioEntrada hijo = auxiliar.getDato();
            if (hijo.getNombre().equalsIgnoreCase(nombre)) {
                 JOptionPane.showMessageDialog(null, "Ya existe una entrada con el nombre '" + nombre + "' en este directorio.", "Error", JOptionPane.ERROR_MESSAGE);
                 return null;
            }
            auxiliar = auxiliar.getSiguiente();
        }

        // 2c. Validar espacio en disco (solo para archivos)
        if (!esDirectorio && !verificarEspacio(tamañoBloques)) {
            JOptionPane.showMessageDialog(null, "No hay suficiente espacio libre en el disco para este archivo.", "Error de Espacio", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
        // --- 3. CREACIÓN Y REGISTRO ---
        
        final int PRIMER_BLOQUE_NULO = -1;
        String propietario = tipoUsuario; // Usaremos el tipo de usuario como propietario temporal

        // Crear la entrada inicialmente sin asignación de bloques
        DirectorioEntrada nuevaEntrada = new DirectorioEntrada(
            nombre, 
            esDirectorio, 
            tamañoBloques, // numBloques
            PRIMER_BLOQUE_NULO, // -1 temporal
            propietario
        );
        
        // 💡 CORRECCIÓN 2: Asignación de bloques y actualización de la entrada
        if (!esDirectorio) {
            // Asignar bloques y obtener el ID del primer bloque
            int firstBlockID = this.disco.allocateBlocks(tamañoBloques, nombre, 1); // Asumimos processID=1
            
            if (firstBlockID == -1) {
                // Si falla la asignación (fragmentación/disco lleno)
                JOptionPane.showMessageDialog(null, "Error al asignar bloques en el disco (Espacio/Fragmentación).", "Error de Asignación", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            
            // ✅ CRÍTICO: Actualizar la entrada con el ID del primer bloque asignado
            nuevaEntrada.setPrimerBloqueID(firstBlockID);
        }
        
        // Registrar la entrada en la jerarquía del padre
        padre.ListaHijos.Insertar(nuevaEntrada); 

        return nuevaEntrada; 
    }
    
    /**
     * Elimina una entrada del directorio y libera sus bloques asociados en el disco.
     */
    public boolean eliminarEntrada(DirectorioEntrada entrada, DirectorioEntrada padre) {
        
        // 1. Validaciones
        if (entrada.getNombre().equals(this.raiz.getNombre())) {
            JOptionPane.showMessageDialog(null, "No se puede eliminar la raíz del sistema de archivos.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        if (entrada.getEsDirectorio() && entrada.ListaHijos.getTamaño() > 0) {
            JOptionPane.showMessageDialog(null, "No se puede eliminar un directorio no vacío.", "Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        
        // 2. Liberación de bloques del disco (CRÍTICO)
        // Solo archivos con bloques asignados
        if (!entrada.getEsDirectorio() && entrada.getPrimerBloqueID() != -1) {
            this.disco.deallocateBlocks(entrada.getPrimerBloqueID());
        }
        
        // 3. Eliminación de la Lista Enlazada del padre
        // Se asume que ListaEnlazada.eliminar(T dato) funciona correctamente
        padre.ListaHijos.eliminar(entrada);
        
        return true; 
    }
    
    // --- Métodos de Utilidad ---
    
    private boolean verificarEspacio(int bloquesRequeridos) {
        if (bloquesRequeridos <= 0) {
            return true;
        }
        // Utiliza el método del disco
        return this.disco.getBloquesLibres() >= bloquesRequeridos;
    }
    
    public DirectorioEntrada getRaiz() { return this.raiz; }
    
    public Disco getDisco() { return this.disco; }
}