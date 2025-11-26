/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Controlador.Simulador;
import EstructurasDeDatos.Nodo;
import Modelo.Archivo;
import javax.swing.table.DefaultTableModel;
import javax.swing.JOptionPane;

/**
 *
 * @author Arturo
 */
public class TablaArchivos extends javax.swing.JFrame {
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(TablaArchivos.class.getName());
    
    /**
     * Creates new form TablaArchivos
     */
    public TablaArchivos(Simulador Gestor) {
        initComponents();
        this.setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE); // Cambiado a DISPOSE_ON_CLOSE
        this.actualizarTablaArchivos(Gestor);
    }
    
  public void actualizarTablaArchivos(Simulador Gestor) {
      
      
    // 1. Obtener el modelo de tu JTable
        DefaultTableModel model = (DefaultTableModel) TablaArchivo.getModel();
        
        // 2. Definir las columnas de forma explícita, corrigiendo la última etiqueta
        model.setColumnIdentifiers(new Object[] {
            "Nombre", 
            "Cant. Bloques", 
            "¿Directorio?" 
        });

        // 3. Limpiar la tabla actual
        model.setRowCount(0);

        try {
            // **Punto crítico:** Acceso a la lista. Asumiendo la estructura:
            // Simulador -> Disco -> ListaEnlazada<Archivo>
            Nodo<Archivo> actual = Gestor.getDiscoSimulado().getListaArchivos().getInicio(); 

            while (actual != null) {
                Archivo arch = actual.getDato();
                
                // Creamos la fila con los datos
                Object[] fila = new Object[] {
                    arch.getNombre(),
                    arch.getTamañoBloques(),
                    // Se muestra el ID del primer bloque como información de ubicación
                    arch.getPrimerBloqueID() 
                };
                
                // Agregar la fila al modelo
                model.addRow(fila);
                
                // Mover al siguiente nodo
                actual = actual.getSiguiente();
            }
        } catch (NullPointerException e) {
            System.err.println("Error al acceder a la lista de archivos. Verifique el path: Gestor.getDiscoSimulado().getListaArchivos().getHead()");
            JOptionPane.showMessageDialog(this, "Error: No se pudo cargar la lista de archivos del simulador.", "Error de Datos", JOptionPane.ERROR_MESSAGE);
    }
}

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        TablaArchivo = new javax.swing.JTable();
        jButton1 = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        TablaArchivo.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Nombre", "Num Bloques", "Direccion Bloque"
            }
        ));
        jScrollPane1.setViewportView(TablaArchivo);

        jButton1.setText("Back");
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 223, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jButton1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 289, Short.MAX_VALUE)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addComponent(jButton1)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, 311, javax.swing.GroupLayout.PREFERRED_SIZE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        this.dispose();
    }//GEN-LAST:event_jButton1ActionPerformed



    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTable TablaArchivo;
    private javax.swing.JButton jButton1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
