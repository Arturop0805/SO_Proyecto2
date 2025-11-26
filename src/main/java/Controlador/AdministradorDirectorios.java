/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Controlador;

/**
 *
 * @author yange
 */

import Modelo.DirectorioEntrada;
import EstructurasDeDatos.Nodo; // Necesitas tu Nodo para iterar la ListaEnlazada
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import Modelo.Disco; // Usar la interfaz o clase general 'Disco'
import javax.swing.JOptionPane;


public class AdministradorDirectorios {

    private DirectorioEntrada raiz; // El objeto raíz de tu sistema de archivos (ej: "/")
    // Campo que mantiene la referencia al disco
    private Disco disco; // Campo de instancia para el disco
    
    
    public AdministradorDirectorios(String nombreRaiz, String propietario) {
        // Inicializa la raíz (que es un Directorio)
        this.raiz = new DirectorioEntrada(nombreRaiz, true, 0, -1, propietario);
        this.disco = disco; // Inicializa el disco
    }
    
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
            // ASUMIMOS que ListaEnlazada tiene un método para obtener el nodo inicial
            Nodo<DirectorioEntrada> auxiliar = (Nodo<DirectorioEntrada>) padreModelo.ListaHijos.getInicio(); 
            // NOTA: Si no tienes getInicio(), tendrás que adaptar la ListaEnlazada o la iteración.

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
    
    public DirectorioEntrada crearEntrada(String nombre, boolean esDirectorio, int tamañoBloques, DirectorioEntrada padre, String tipoUsuario) {
        
        // --- 1. VALIDACIÓN DE PERMISOS ---
        // Ejemplo de validación, adáptala a tu lógica
        if (tipoUsuario.equalsIgnoreCase("USER")) {
            JOptionPane.showMessageDialog(null, "Permiso denegado: Los usuarios estándar no pueden crear entradas.", "Error de Permisos", JOptionPane.ERROR_MESSAGE);
            return null;
        }

        // --- 2. VALIDACIONES DE SISTEMA DE ARCHIVOS (IMPRESCINDIBLE) ---

        // 2a. Verificar que el padre es realmente un directorio
        if (!padre.getEsDirectorio()) {
             JOptionPane.showMessageDialog(null, "El padre seleccionado no es un directorio.", "Error", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
        
        // 2b. Verificar que el nombre no exista ya (USANDO ListaEnlazada - Versión ÚNICA y Correcta)
        Nodo<DirectorioEntrada> auxiliar = (Nodo<DirectorioEntrada>) padre.ListaHijos.getInicio();
        while (auxiliar != null) {
            DirectorioEntrada hijo = auxiliar.getDato();
            if (hijo.getNombre().equalsIgnoreCase(nombre)) {
                  JOptionPane.showMessageDialog(null, "Ya existe una entrada con el nombre '" + nombre + "' en este directorio.", "Error", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            auxiliar = auxiliar.getSiguiente();
        }

        // 2c. Validar espacio en disco (se mantiene la llamada)
        if (!esDirectorio && !verificarEspacio(tamañoBloques)) {
            JOptionPane.showMessageDialog(null, "No hay suficiente espacio libre en el disco para este archivo.", "Error de Espacio", JOptionPane.ERROR_MESSAGE);
            return null;
        }
        
        
        // --- 3. CREACIÓN Y REGISTRO ---
        
        // Definiciones necesarias para el constructor
        // 1. El ID del primer bloque es -1 porque aún no se ha asignado.
        final int PRIMER_BLOQUE_NULO = -1; 
        // 2. El propietario debe ser el tipo de usuario actual o un nombre de usuario real.
        String propietario = tipoUsuario; // Usaremos el tipo de usuario como propietario temporal

        // Cambia la llamada en crearEntrada:
        DirectorioEntrada nuevaEntrada = new DirectorioEntrada(
            nombre, 
            esDirectorio, 
            tamañoBloques, // numBloques
            PRIMER_BLOQUE_NULO, 
            propietario
        );
        
        // 💡 CORRECCIÓN 4: Integrar la asignación de bloques con el Disco
        if (!esDirectorio) {
            // El método allocateBlocks devuelve el blockID del primer bloque
            int firstBlockID = this.disco.allocateBlocks(tamañoBloques, nombre, 1); // Asumimos processID=1
            
            if (firstBlockID == -1) {
                JOptionPane.showMessageDialog(null, "Error al asignar bloques en el disco (Espacio/Fragmentación).", "Error de Asignación", JOptionPane.ERROR_MESSAGE);
                return null;
            }
            
            // Debes tener un método en DirectorioEntrada para guardar el ID del primer bloque
            // Aquí deberías usar algo como: nuevaEntrada.setPrimerBloque(firstBlockID);
        }
        
        // 💡 CORRECCIÓN 5: Usar el método de tu ListaEnlazada para agregar el nodo
        // Asumimos que tu clase ListaEnlazada tiene un método Insertar o Agregar
        padre.ListaHijos.Insertar(nuevaEntrada); 

        return nuevaEntrada; 
    }
    
    // El método verificarEspacio ahora usa el campo 'disco' correctamente
    private boolean verificarEspacio(int bloquesRequeridos) {
        if (bloquesRequeridos <= 0) {
            return true;
        }
        // Utiliza el método del disco
        return this.disco.getBloquesLibres() >= bloquesRequeridos;
    }
    
    public DirectorioEntrada getRaiz() { return this.raiz; }
    
    // En Controlador/AdministradorDirectorios.java
public boolean eliminarEntrada(DirectorioEntrada entrada, DirectorioEntrada padre) {
    
    // 1. Validaciones (ej. ¿Es la raíz? ¿Tiene el usuario permisos?)
    if (entrada.getNombre().equals("raiz/")) {
        // No se puede eliminar la raíz
        return false;
    }
    
    if (entrada.getEsDirectorio() && !entrada.ListaHijos.EstaVacia()) {
        // No se puede eliminar un directorio no vacío
        return false;
    }
    
    // 2. Liberación de bloques del disco (CRÍTICO)
    if (!entrada.getEsDirectorio() && entrada.getPrimerBloqueID() != -1) {
        // Llama al disco para liberar los bloques encadenados
        this.disco.deallocateBlocks(entrada.getPrimerBloqueID());
    }
    
    // 3. Eliminación de la Lista Enlazada del padre
    // DEBES tener un método en ListaEnlazada para buscar y eliminar un dato
    padre.ListaHijos.eliminar(entrada); // Suponiendo que ListaEnlazada.Eliminar existe
    
    return true; // Éxito en la eliminación
   }
}
