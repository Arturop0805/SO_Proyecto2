/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import Controlador.Simulador;
import javax.swing.JOptionPane;
import Vista.CrearNodo;
import Vista.EliminarNodo;
import Vista.ModificarNodo;
import javax.swing.event.TreeSelectionListener;
import javax.swing.event.TreeSelectionEvent;
import Modelo.Directorio; 
import Modelo.Archivo;


/**
 *
 * @author Arturo
 */
public class Principal extends javax.swing.JFrame implements TreeSelectionListener {
    
    Simulador Gestor = new Simulador(); 
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Principal.class.getName());

    
    public Principal() {
       initComponents();
        
        this.setSize(800, 600);
        this.setResizable(false);
        
        // 1. Obtener el modelo inicial del simulador (árbol de prueba)
        DefaultTreeModel modelo = Gestor.obtenerModeloArbolFS(); 
        
        // 2. Establecer el modelo en el JTree y configuración
        modelo.setAsksAllowsChildren(true);
        Arbol.setModel(modelo);
        
        // CORRECCIÓN 3: Llamar a configurar el panel de información
        configurarInfoPanel();
        
        // CORRECCIÓN 4: Añadir el listener al JTree
        Arbol.addTreeSelectionListener(this);
        
        // 3. Mostrar el usuario actual
        this.etiquetaUser.setText(Gestor.getTipoUsuario());
    }
    
    public javax.swing.JTree getJTreeArbol() {
    return this.Arbol;
    }
    
    private void configurarInfoPanel() {
        // Asignar texto inicial a las etiquetas generadas por el diseñador
        Nombre.setText("Nombre: (Seleccione un nodo)");
        Tipo.setText("Tipo:");
        Tamaño.setText("Tamaño:");
    }
    
    // En Vista/Principal.java
// (Reemplaza el refrescarArbol anterior por este, y corrige el nombre de las variables como ya te indiqué)

public void refrescarArbol(Object entradaModeloSeleccionada) {
    // 1. Recrear el modelo completo desde el Controlador
    DefaultTreeModel nuevoModelo = this.Gestor.obtenerModeloArbolFS(); // Usa 'Gestor'
    this.Arbol.setModel(nuevoModelo); // Usa 'Arbol'
    this.Arbol.expandRow(0); // Expande la raíz (opcional)

    // 2. Buscar la entrada de modelo en el nuevo árbol de Swing
    if (entradaModeloSeleccionada != null) {

        // ¡CRÍTICO! Buscar el nodo de Swing que contiene este objeto de modelo
        DefaultMutableTreeNode nuevoNodo = encontrarNodoEnArbol(nuevoModelo, entradaModeloSeleccionada);

        if (nuevoNodo != null) {
            // 3. Seleccionar el nuevo nodo y hacerlo visible
            javax.swing.tree.TreePath path = new javax.swing.tree.TreePath(nuevoNodo.getPath());
            this.Arbol.setSelectionPath(path);
            this.Arbol.scrollPathToVisible(path);

            // 4. Forzar la actualización del panel de detalles
            this.actualizarPanelInfo(entradaModeloSeleccionada);
        }
    }
}

    // Necesitas esta función de utilidad dentro de Principal.java
    private DefaultMutableTreeNode encontrarNodoEnArbol(javax.swing.tree.TreeModel modelo, Object userObjectBuscado) {
        DefaultMutableTreeNode raiz = (DefaultMutableTreeNode) modelo.getRoot();
        java.util.Enumeration<?> e = raiz.depthFirstEnumeration();

        while (e.hasMoreElements()) {
            DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) e.nextElement();
            // Comparamos el objeto de modelo real (Directorio/Archivo)
            if (nodo.getUserObject().equals(userObjectBuscado)) {
                return nodo;
            }
        }
        return null;
    }

        public void actualizarPanelInfo(Object objetoUsuario) {
            if (objetoUsuario == null) {
            Nombre.setText("Nombre: (Ninguno)");
            Tipo.setText("Tipo:");
            Tamaño.setText("Tamaño:");
            return;
        }

            // CORRECCIÓN 2: Reemplazar DirectorioEntrada por Directorio
            if (objetoUsuario instanceof Directorio) {
                Directorio entrada = (Directorio) objetoUsuario;
                Nombre.setText("Nombre: " + entrada.getNombre());
                Tipo.setText("Tipo: Directorio");
                // Se muestra 0 Bloques para directorios
                Tamaño.setText("Tamaño: 0 Bloques");

            } else if (objetoUsuario instanceof Archivo) {
                Archivo archivo = (Archivo) objetoUsuario;
                Nombre.setText("Nombre: " + archivo.getNombre());
                Tipo.setText("Tipo: Archivo");
                // CORRECCIÓN 3: Usar getTamano() que retorna el tamaño en bloques
                Tamaño.setText("Tamaño: " + archivo.getTamano() + " Bloques");
            } else {
                // Manejar el nodo raíz o nodos sin objeto de modelo específico
                Nombre.setText("Nombre: " + objetoUsuario.toString());
                Tipo.setText("Tipo: Raíz / Desconocido");
                Tamaño.setText("Tamaño: N/A");
            }
        }
    
    @Override
    public void valueChanged(TreeSelectionEvent e) {
        // Obtener el nodo seleccionado
        DefaultMutableTreeNode nodo = (DefaultMutableTreeNode) Arbol.getLastSelectedPathComponent();
        
        if (nodo == null) {
            // No hay nada seleccionado o la selección se anuló
            Nombre.setText("Nombre: (Ninguno)");
            Tipo.setText("Tipo:");
            Tamaño.setText("Tamaño:");
            actualizarPanelInfo(null);
        
            return;
        }
        
        // El objeto de usuario contiene la instancia de nuestro modelo (Directorio o Archivo)
        Object objetoUsuario = nodo.getUserObject();
        
        // CORRECCIÓN 4: Reemplazar DirectorioEntrada por Directorio
        if (objetoUsuario instanceof Directorio) {
            Directorio entrada = (Directorio) objetoUsuario;
            Nombre.setText("Nombre: " + entrada.getNombre());
            Tipo.setText("Tipo: Directorio");
            // Se muestra 0 Bloques para directorios
            Tamaño.setText("Tamaño: 0 Bloques"); 
            
        } else if (objetoUsuario instanceof Archivo) {
            Archivo archivo = (Archivo) objetoUsuario;
            Nombre.setText("Nombre: " + archivo.getNombre());
            Tipo.setText("Tipo: Archivo");
            // CORRECCIÓN 5: Usar getTamano() que retorna el tamaño en bloques
            Tamaño.setText("Tamaño: " + archivo.getTamano() + " Bloques");
        } else {
            // Manejar el nodo raíz o nodos sin objeto de modelo específico
            Nombre.setText("Nombre: " + objetoUsuario.toString());
            Tipo.setText("Tipo: Raíz / Desconocido");
            Tamaño.setText("Tamaño: N/A");
        }
        actualizarPanelInfo(nodo.getUserObject());
    }
    
    public Principal(DefaultTreeModel modelo){
        initComponents();
        
        Arbol.setModel(modelo);
        
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jMenu1 = new javax.swing.JMenu();
        jMenu2 = new javax.swing.JMenu();
        jMenu3 = new javax.swing.JMenu();
        jMenu4 = new javax.swing.JMenu();
        jPanel3 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        Arbol = new javax.swing.JTree();
        etiquetaUser = new javax.swing.JLabel();
        BotonAgregar = new javax.swing.JButton();
        ChangeTypeButton = new javax.swing.JButton();
        BotonEliminar = new javax.swing.JButton();
        BotonModificar = new javax.swing.JButton();
        BotonTablaArchivos = new javax.swing.JButton();
        Info_Nodo = new javax.swing.JPanel();
        Nombre = new javax.swing.JLabel();
        Tipo = new javax.swing.JLabel();
        Tamaño = new javax.swing.JLabel();

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 100, Short.MAX_VALUE)
        );

        jMenu1.setText("jMenu1");

        jMenu2.setText("jMenu2");

        jMenu3.setText("jMenu3");

        jMenu4.setText("jMenu4");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jScrollPane1.setViewportView(Arbol);

        etiquetaUser.setText("ADMIN");

        BotonAgregar.setText("Agregar");
        BotonAgregar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonAgregarActionPerformed(evt);
            }
        });

        ChangeTypeButton.setText("Cambiar");
        ChangeTypeButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                ChangeTypeButtonActionPerformed(evt);
            }
        });

        BotonEliminar.setText("Eliminar");
        BotonEliminar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonEliminarActionPerformed(evt);
            }
        });

        BotonModificar.setText("Modificar");
        BotonModificar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonModificarActionPerformed(evt);
            }
        });

        BotonTablaArchivos.setText("Archivos");
        BotonTablaArchivos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                BotonTablaArchivosActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout Info_NodoLayout = new javax.swing.GroupLayout(Info_Nodo);
        Info_Nodo.setLayout(Info_NodoLayout);
        Info_NodoLayout.setHorizontalGroup(
            Info_NodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Info_NodoLayout.createSequentialGroup()
                .addGroup(Info_NodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(Nombre, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(Tipo, javax.swing.GroupLayout.DEFAULT_SIZE, 115, Short.MAX_VALUE)
                    .addComponent(Tamaño, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(0, 0, Short.MAX_VALUE))
        );
        Info_NodoLayout.setVerticalGroup(
            Info_NodoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(Info_NodoLayout.createSequentialGroup()
                .addComponent(Nombre, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Tipo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(Tamaño, javax.swing.GroupLayout.DEFAULT_SIZE, 27, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 205, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addGap(39, 39, 39)
                        .addComponent(Info_Nodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel3Layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 184, Short.MAX_VALUE)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addComponent(ChangeTypeButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(etiquetaUser, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                            .addGroup(jPanel3Layout.createSequentialGroup()
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(BotonEliminar)
                                    .addComponent(BotonModificar))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(BotonTablaArchivos)
                                    .addComponent(BotonAgregar))))
                        .addGap(104, 104, 104))))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(51, 51, 51)
                .addComponent(Info_Nodo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(ChangeTypeButton)
                    .addComponent(etiquetaUser, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(25, 25, 25)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotonModificar)
                    .addComponent(BotonTablaArchivos))
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(BotonEliminar)
                    .addComponent(BotonAgregar))
                .addGap(41, 41, 41))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 451, Short.MAX_VALUE)
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(21, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void BotonAgregarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonAgregarActionPerformed
        DefaultTreeModel modelo = (DefaultTreeModel) Arbol.getModel();

        // ¡CORRECCIÓN! Debes pasar la referencia a la ventana principal ('this')
        // El constructor de CrearNodo necesita: (Modelo, Gestor, VentanaPrincipal)
        CrearNodo Interfaz = new CrearNodo(modelo, this.Gestor, this); // 'this' es la instancia de Principal

        Interfaz.setVisible(true);
    }//GEN-LAST:event_BotonAgregarActionPerformed

    private void ChangeTypeButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_ChangeTypeButtonActionPerformed
      Gestor.cambiarTipoUsuario();
        etiquetaUser.setText(Gestor.getTipoUsuario());
        if (etiquetaUser.getText().equals("USER")){
            BotonAgregar.setVisible(false);
            BotonEliminar.setVisible(false);
            BotonModificar.setVisible(false);
        } else {
            BotonAgregar.setVisible(true);
            BotonEliminar.setVisible(true);
            BotonModificar.setVisible(true);
        }
    }//GEN-LAST:event_ChangeTypeButtonActionPerformed

    private void BotonEliminarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonEliminarActionPerformed
        
        DefaultTreeModel modelo = (DefaultTreeModel) Arbol.getModel();
        // Se resuelve el conflicto de Git: se pasa la instancia del Gestor
        EliminarNodo interfaz = new EliminarNodo(modelo, this.Gestor);
        interfaz.setVisible(true);
    }//GEN-LAST:event_BotonEliminarActionPerformed

    private void BotonModificarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonModificarActionPerformed
      DefaultTreeModel modelo = (DefaultTreeModel) Arbol.getModel();
        // ModificarNodo solo recibe el modelo para actualizar, asumiendo que la lógica 
        // de modificación real se realiza en ModificarNodo usando el Gestor.
        ModificarNodo interfaz = new ModificarNodo(modelo);
        interfaz.setVisible(true);
    }//GEN-LAST:event_BotonModificarActionPerformed

    private void BotonTablaArchivosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_BotonTablaArchivosActionPerformed
        
        
        TablaArchivos interfaz = new TablaArchivos(Gestor);
          
        interfaz.setVisible(true);
        
    }//GEN-LAST:event_BotonTablaArchivosActionPerformed

    
    

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTree Arbol;
    private javax.swing.JButton BotonAgregar;
    private javax.swing.JButton BotonEliminar;
    private javax.swing.JButton BotonModificar;
    private javax.swing.JButton BotonTablaArchivos;
    private javax.swing.JButton ChangeTypeButton;
    private javax.swing.JPanel Info_Nodo;
    private javax.swing.JLabel Nombre;
    private javax.swing.JLabel Tamaño;
    private javax.swing.JLabel Tipo;
    private javax.swing.JLabel etiquetaUser;
    private javax.swing.JMenu jMenu1;
    private javax.swing.JMenu jMenu2;
    private javax.swing.JMenu jMenu3;
    private javax.swing.JMenu jMenu4;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    // End of variables declaration//GEN-END:variables
}
