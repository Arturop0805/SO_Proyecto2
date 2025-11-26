/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Controlador.Simulador;
import Modelo.Archivo;
import java.util.Enumeration;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;

/**
 *
 * @author Arturo
 */
public class EliminarNodo extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(EliminarNodo.class.getName());

   private DefaultTreeModel modelo;
   private Simulador gestor;
    public EliminarNodo(DefaultTreeModel modeloArbol, Simulador gestor) {
        initComponents();
        
        this.gestor = gestor;
        this.setSize(500, 200);
        this.setResizable(false);
        

        ListaNodos.removeAllItems();
        this.modelo = modeloArbol;
        DefaultMutableTreeNode raiz = (DefaultMutableTreeNode) modeloArbol.getRoot();
        this.cargarNodosRecursivo(raiz);
        
    }
    
    private void cargarNodosRecursivo(DefaultMutableTreeNode nodo) {
        
        
        ListaNodos.addItem(nodo.toString());
       
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
        String nombreNodo = NodeNameField.getText();
        Archivo archivoEliminado = this.gestor.SD.buscarPorNombre(nombreNodo);
        
        if (archivoEliminado == null) {
            return;
        }
        
        DefaultMutableTreeNode NodoEliminado = buscarNodoPorNombre(nombreNodo);
        
        modelo.removeNodeFromParent(NodoEliminado);
        this.gestor.SD.eliminarArch(archivoEliminado);
        this.dispose();
        
    }//GEN-LAST:event_DeleteButtonActionPerformed

    public DefaultMutableTreeNode buscarNodoPorNombre(String nombreBuscado) {
    // 1. Obtener la raíz del árbol
    DefaultMutableTreeNode raiz = (DefaultMutableTreeNode) modelo.getRoot();
    
    // 2. Crear una enumeración para recorrer todo el árbol (Búsqueda en anchura)
    Enumeration e = raiz.breadthFirstEnumeration();
    
    // 3. Recorrer todos los nodos
    while (e.hasMoreElements()) {
        DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) e.nextElement();
        
        // 4. Comparar el texto del nodo con lo que buscamos
        // Usamos equalsIgnoreCase para que no importen mayúsculas/minúsculas
        if (nodo.getUserObject().toString().equals(nombreBuscado)) {
            return nodo; // ¡Encontrado!
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
