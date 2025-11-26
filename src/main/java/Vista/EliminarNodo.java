/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Controlador.Simulador;
import Modelo.DirectorioEntrada;
import java.util.Enumeration;
import javax.swing.JOptionPane;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import Controlador.AdministradorDirectorios;

/**
 *
 * @author Arturo
 */
public class EliminarNodo extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(EliminarNodo.class.getName());

    private AdministradorDirectorios adminFS; // Acceso directo al FS para la lógica
    private DefaultTreeModel modelo;
    private Simulador gestor; // Acceso al contexto completo (incluido el usuario actual)
    
    // Se consolida el constructor para recibir el Simulador (que contiene el contexto)
    public EliminarNodo(DefaultTreeModel modeloArbol, Simulador gestor) {
        initComponents();
        
        this.gestor = gestor;
        // Obtenemos el AdministradorDirectorios del Simulador
        this.adminFS = gestor.getAdminFS(); 
        
        this.setSize(500, 200);
        this.setResizable(false);
        
        ListaNodos.removeAllItems();
        this.modelo = modeloArbol;
        
        DefaultMutableTreeNode raiz = (DefaultMutableTreeNode) modeloArbol.getRoot();
        this.cargarNodosRecursivo(raiz);
        
        // Inicializar campo de selección
        NodeNameField.setText("Seleccione un nodo a eliminar");
    }
    
    /**
     * Recorre el árbol recursivamente y añade todos los nodos al JComboBox,
     * excluyendo el nodo raíz.
     */
    private void cargarNodosRecursivo(DefaultMutableTreeNode nodo) {
        
        Object userObject = nodo.getUserObject();
        
        if (userObject instanceof DirectorioEntrada) {
            DirectorioEntrada entrada = (DirectorioEntrada) userObject;
            
            // Excluimos el nodo raíz de la lista de elementos a eliminar
            if (!entrada.getNombre().equals("/")) {
                ListaNodos.addItem(entrada.getNombre());
            }
        }
        
        // Si tiene hijos, recorremos los hijos también
        if (nodo.getChildCount() > 0) {
            for (int i = 0; i < nodo.getChildCount(); i++) {
                DefaultMutableTreeNode hijo = (DefaultMutableTreeNode) nodo.getChildAt(i);
                cargarNodosRecursivo(hijo);
            }
        }
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        ListaNodos = new javax.swing.JComboBox<>();
        SelectButton = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();
        NodeNameField = new javax.swing.JLabel();
        DeleteButton = new javax.swing.JButton();
        BackButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        ListaNodos.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));

        SelectButton.setText("Seleccionar");
        SelectButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                SelectButtonActionPerformed(evt);
            }
        });

        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Eliminar:");

        NodeNameField.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        NodeNameField.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        NodeNameField.setText("No hay nodo seleccionado");
        NodeNameField.setHorizontalTextPosition(javax.swing.SwingConstants.LEFT);

        DeleteButton.setText("Eliminar");
        DeleteButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                DeleteButtonActionPerformed(evt);
            }
        });

        BackButton.setText("X");
        BackButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BackButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(ListaNodos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(70, 70, 70)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(52, 52, 52)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(DeleteButton)
                            .addComponent(SelectButton))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 115, Short.MAX_VALUE)
                .addComponent(BackButton)
                .addGap(14, 14, 14))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(NodeNameField, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ListaNodos, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(SelectButton)
                    .addComponent(BackButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(NodeNameField)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(DeleteButton)
                .addGap(22, 22, 22))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BackButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BackButtonActionPerformed
       
        
        this.dispose();
        
        
    }//GEN-LAST:event_BackButtonActionPerformed

    private void SelectButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_SelectButtonActionPerformed
        
        
         NodeNameField.setText(ListaNodos.getSelectedItem().toString());
        
        
    }//GEN-LAST:event_SelectButtonActionPerformed

    private void DeleteButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_DeleteButtonActionPerformed
        String nombreSeleccionado = NodeNameField.getText();

        if (nombreSeleccionado.equals("Seleccione un nodo a eliminar") || nombreSeleccionado.equals("/")) {
            JOptionPane.showMessageDialog(this, "Debe seleccionar un archivo o directorio válido (no la raíz).", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 1. Buscar el nodo Swing (DefaultMutableTreeNode)
        DefaultMutableTreeNode nodoSwing = buscarNodoPorNombre(nombreSeleccionado);

        if (nodoSwing == null) {
            JOptionPane.showMessageDialog(this, "Error interno: No se encontró el nodo seleccionado en el árbol.", "Error Interno", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // 2. Obtener el objeto de datos del sistema de archivos (DirectorioEntrada)
        Object userObject = nodoSwing.getUserObject();
        if (!(userObject instanceof DirectorioEntrada)) {
            JOptionPane.showMessageDialog(this, "Error de modelo: El nodo seleccionado no es una entrada de directorio válida.", "Error de Modelo", JOptionPane.ERROR_MESSAGE);
            return;
        }
        DirectorioEntrada entradaAEliminar = (DirectorioEntrada) userObject;

        // 3. Obtener el nodo padre (necesario para la eliminación en la ListaHijos del modelo)
        DefaultMutableTreeNode padreSwing = (DefaultMutableTreeNode) nodoSwing.getParent();
        
        if (padreSwing == null) {
             JOptionPane.showMessageDialog(this, "No se puede eliminar la raíz.", "Error", JOptionPane.ERROR_MESSAGE);
             return;
        }
        
        // El objeto del padre debe ser DirectorioEntrada (salvo que sea la raíz, que ya se chequeó arriba)
        DirectorioEntrada padreEntrada = (DirectorioEntrada) padreSwing.getUserObject();
        
        // Obtenemos el usuario para la verificación de permisos en el controlador
        // Se asume que getTipoUsuario() devuelve el nombre del usuario o un identificador de permisos.
        String usuarioActual = gestor.getTipoUsuario();

        // 4. DELEGAR la eliminación completa al Controlador (incluye liberación de bloques y verificación de permisos)
        // Se asume que adminFS.eliminarEntrada(entrada, padre, usuario) devuelve true si tuvo éxito.
        boolean exito = adminFS.eliminarEntrada(entradaAEliminar, padreEntrada, usuarioActual);

        if (exito) {
            // 5. Si el modelo de datos se eliminó, actualizar la vista
            modelo.removeNodeFromParent(nodoSwing);
            modelo.reload(padreSwing);
            
            JOptionPane.showMessageDialog(this, "Entrada '" + nombreSeleccionado + "' eliminada con éxito.", "Éxito", JOptionPane.INFORMATION_MESSAGE);
            this.dispose();
        } else {
            // Si retorna false, asumimos que el controlador maneja el mensaje de error específico
            // (ej. "Permiso denegado", "Directorio no vacío", etc.)
            // Si el controlador no maneja el mensaje, se debería añadir un JOptionPane aquí.
        }
    }//GEN-LAST:event_DeleteButtonActionPerformed

    public DefaultMutableTreeNode buscarNodoPorNombre(String nombreBuscado) {
    DefaultMutableTreeNode raiz = (DefaultMutableTreeNode) modelo.getRoot();
        // Usar Enumeration para recorrer todo el árbol (Búsqueda en anchura)
        Enumeration e = raiz.breadthFirstEnumeration(); 
        
        while (e.hasMoreElements()) {
            DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) e.nextElement();
            Object userObject = nodo.getUserObject();

            // 4. Comparar el nombre de la entrada de directorio/archivo
            if (userObject instanceof DirectorioEntrada) {
                 DirectorioEntrada entrada = (DirectorioEntrada) userObject;
                 if (entrada.getNombre().equalsIgnoreCase(nombreBuscado)) {
                     return nodo; // ¡Encontrado!
                 }
            } else if (userObject.toString().equalsIgnoreCase(nombreBuscado)) {
                 // Caso de fallback si el userObject no es DirectorioEntrada, aunque debería serlo
                 return nodo;
            }
        }
        
        return null; // No se encontró nada
}
 

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton BackButton;
    private javax.swing.JButton DeleteButton;
    private javax.swing.JComboBox<String> ListaNodos;
    private javax.swing.JLabel NodeNameField;
    private javax.swing.JButton SelectButton;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
