/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package Vista;

import Modelo.Proceso;
/**
 *
 * @author yange
 */
public class Modelado_Proc extends javax.swing.JFrame {
    
    // 1. Declaraciones de constantes de Color (Faltantes en tu fragmento)
    private static final java.awt.Color COLOR_LIBRE = java.awt.Color.LIGHT_GRAY;
    private static final java.awt.Color COLOR_EJECUCION = java.awt.Color.GREEN;
    private static final java.awt.Color COLOR_TERMINADO = java.awt.Color.RED;
    private static final java.awt.Color COLOR_ESPERA = java.awt.Color.YELLOW; 
    
    
    
    
    // **NUEVO:** Declaración del arreglo de JButtons
    
    
    private static final java.util.logging.Logger logger = java.util.logging.Logger.getLogger(Modelado_Proc.class.getName());

    /**
     * Creates new form Modelado_Proc
     */
    // En Vista/Modelado_Proc.java

    public Modelado_Proc() {
        initComponents();
        inicializarBotones(); // Nuevo método para la inicialización
    }

    private void inicializarBotones() {
        // Inicialización manual de los 30 botones
        // Se establece el color y texto inicial
        
        // **CORRECCIÓN APLICADA:** Cada botón ahora usa su propia referencia para setText().

        // Fila 1 (Botones 1 al 10)
        jButton1.setBackground(COLOR_LIBRE); jButton1.setText("LIBRE");
        jButton2.setBackground(COLOR_LIBRE); jButton2.setText("LIBRE");
        jButton3.setBackground(COLOR_LIBRE); jButton3.setText("LIBRE"); // Corregido: antes usaba jButton30
        jButton4.setBackground(COLOR_LIBRE); jButton4.setText("LIBRE"); // Corregido: antes usaba jButton30
        jButton5.setBackground(COLOR_LIBRE); jButton5.setText("LIBRE"); // Corregido: antes usaba jButton30
        jButton6.setBackground(COLOR_LIBRE); jButton6.setText("LIBRE"); // Corregido: antes usaba jButton30
        jButton7.setBackground(COLOR_LIBRE); jButton7.setText("LIBRE"); // Corregido: antes usaba jButton30
        jButton8.setBackground(COLOR_LIBRE); jButton8.setText("LIBRE"); // Corregido: antes usaba jButton30
        jButton9.setBackground(COLOR_LIBRE); jButton9.setText("LIBRE"); // Corregido: antes usaba jButton30
        jButton10.setBackground(COLOR_LIBRE); jButton10.setText("LIBRE"); // Corregido: antes usaba jButton30
        
        // Fila 2 (Botones 11 al 20)
        jButton11.setBackground(COLOR_LIBRE); jButton11.setText("LIBRE"); // Corregido: antes usaba jButton1
        jButton12.setBackground(COLOR_LIBRE); jButton12.setText("LIBRE"); // Corregido: antes usaba jButton2
        jButton13.setBackground(COLOR_LIBRE); jButton13.setText("LIBRE"); // Corregido: antes usaba jButton30
        jButton14.setBackground(COLOR_LIBRE); jButton14.setText("LIBRE"); // Corregido: antes usaba jButton30
        jButton15.setBackground(COLOR_LIBRE); jButton15.setText("LIBRE"); // Corregido: antes usaba jButton30
        jButton16.setBackground(COLOR_LIBRE); jButton16.setText("LIBRE"); // Corregido: antes usaba jButton30
        jButton17.setBackground(COLOR_LIBRE); jButton17.setText("LIBRE"); // Corregido: antes usaba jButton30
        jButton18.setBackground(COLOR_LIBRE); jButton18.setText("LIBRE"); // Corregido: antes usaba jButton30
        jButton19.setBackground(COLOR_LIBRE); jButton19.setText("LIBRE"); // Corregido: antes usaba jButton30
        jButton20.setBackground(COLOR_LIBRE); jButton20.setText("LIBRE"); // Corregido: antes usaba jButton30
        
        // Fila 3 (Botones 21 al 30)
        jButton21.setBackground(COLOR_LIBRE); jButton21.setText("LIBRE"); // Corregido: antes usaba jButton1
        jButton22.setBackground(COLOR_LIBRE); jButton22.setText("LIBRE"); // Corregido: antes usaba jButton2
        jButton23.setBackground(COLOR_LIBRE); jButton23.setText("LIBRE"); // Corregido: antes usaba jButton30
        jButton24.setBackground(COLOR_LIBRE); jButton24.setText("LIBRE"); // Corregido: antes usaba jButton30
        jButton25.setBackground(COLOR_LIBRE); jButton25.setText("LIBRE"); // Corregido: antes usaba jButton30
        jButton26.setBackground(COLOR_LIBRE); jButton26.setText("LIBRE"); // Corregido: antes usaba jButton30
        jButton27.setBackground(COLOR_LIBRE); jButton27.setText("LIBRE"); // Corregido: antes usaba jButton30
        jButton28.setBackground(COLOR_LIBRE); jButton28.setText("LIBRE"); // Corregido: antes usaba jButton30
        jButton29.setBackground(COLOR_LIBRE); jButton29.setText("LIBRE"); // Corregido: antes usaba jButton30
        jButton30.setBackground(COLOR_LIBRE); jButton30.setText("LIBRE");
    }

    // En Vista/Modelado_Proc.java

    /**
     * Método llamado por el Controlador para actualizar el estado visual.
     * Asigna el botón usando una estructura switch basada en el PID.
     * @param proceso El objeto Proceso que ha cambiado de estado.
     */
    public void actualizarBoton(Proceso proceso) {
        int botonID = proceso.getPid(); // Usamos el PID (1 a 30)

        // Validamos límites (opcional, pero útil)
        if (botonID < 1 || botonID > 30) {
            logger.warning("ID de Proceso fuera de rango (1-30): " + botonID);
            return;
        }

        // 1. **Determinar el botón a actualizar (La parte restrictiva)**
        javax.swing.JButton boton;

        // **USANDO SWITCH (Más legible que 30 if/else)**
        switch (botonID) {
            case 1: boton = jButton1; break;
            case 2: boton = jButton2; break;
            case 3: boton = jButton3; break;
            case 4: boton = jButton4; break;
            case 5: boton = jButton5; break;
            case 6: boton = jButton6; break;
            case 7: boton = jButton7; break;
            case 8: boton = jButton8; break;
            case 9: boton = jButton9; break;
            case 10: boton = jButton10; break;
            case 11: boton = jButton11; break;
            case 12: boton = jButton12; break;
            case 13: boton = jButton13; break;
            case 14: boton = jButton14; break;
            case 15: boton = jButton15; break;
            case 16: boton = jButton16; break;
            case 17: boton = jButton17; break;
            case 18: boton = jButton18; break;
            case 19: boton = jButton19; break;
            case 20: boton = jButton20; break;
            case 21: boton = jButton21; break;
            case 22: boton = jButton22; break;
            case 23: boton = jButton23; break;
            case 24: boton = jButton24; break;
            case 25: boton = jButton25; break;
            case 26: boton = jButton26; break;
            case 27: boton = jButton27; break;
            case 28: boton = jButton28; break;
            case 29: boton = jButton29; break;
            case 30: boton = jButton30; break;
            default: return; // No hacer nada si está fuera de rango
        }

        // 2. **Determinar la apariencia (El resto de tu lógica original)**
        java.awt.Color nuevoColor;
        String nuevoTexto;

        switch (proceso.getEstado()) {
            case EN_EJECUCION:
                nuevoColor = COLOR_EJECUCION;
                nuevoTexto = "PID " + proceso.getPid() + ": EJEC.";
                break;
            case EN_ESPERA:
                nuevoColor = COLOR_ESPERA;
                nuevoTexto = "PID " + proceso.getPid() + ": ESPERA";
                break;
            case TERMINADO:
                nuevoColor = COLOR_TERMINADO;
                nuevoTexto = "PID " + proceso.getPid() + ": FIN";
                break;
            case NUEVO:
            default:
                // Si el proceso termina o se libera el recurso/bloque.
                nuevoColor = COLOR_LIBRE;
                nuevoTexto = "LIBRE";
                break;
        }

        // 3. **Actualizar la GUI**
        javax.swing.SwingUtilities.invokeLater(() -> {
            boton.setBackground(nuevoColor);
            boton.setText(nuevoTexto);
            boton.setToolTipText(proceso.getNombre() + " - Estado: " + proceso.getEstado().name());
        });
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
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        jButton5 = new javax.swing.JButton();
        jButton6 = new javax.swing.JButton();
        jButton7 = new javax.swing.JButton();
        jButton8 = new javax.swing.JButton();
        jButton9 = new javax.swing.JButton();
        jButton10 = new javax.swing.JButton();
        jButton11 = new javax.swing.JButton();
        jButton12 = new javax.swing.JButton();
        jButton13 = new javax.swing.JButton();
        jButton14 = new javax.swing.JButton();
        jButton15 = new javax.swing.JButton();
        jButton16 = new javax.swing.JButton();
        jButton17 = new javax.swing.JButton();
        jButton18 = new javax.swing.JButton();
        jButton19 = new javax.swing.JButton();
        jButton20 = new javax.swing.JButton();
        jButton21 = new javax.swing.JButton();
        jButton22 = new javax.swing.JButton();
        jButton23 = new javax.swing.JButton();
        jButton24 = new javax.swing.JButton();
        jButton25 = new javax.swing.JButton();
        jButton26 = new javax.swing.JButton();
        jButton27 = new javax.swing.JButton();
        jButton28 = new javax.swing.JButton();
        jButton29 = new javax.swing.JButton();
        jButton30 = new javax.swing.JButton();
        jLabel1 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton9)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton10)
                        .addGap(12, 12, 12)
                        .addComponent(jButton8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton7))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton4)
                            .addComponent(jButton5)
                            .addComponent(jButton6)
                            .addComponent(jButton2)
                            .addComponent(jButton3))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton11)
                            .addComponent(jButton12)
                            .addComponent(jButton13)
                            .addComponent(jButton14)
                            .addComponent(jButton15))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton16)
                            .addComponent(jButton17)
                            .addComponent(jButton18)
                            .addComponent(jButton19)
                            .addComponent(jButton20)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(84, 84, 84)
                                .addComponent(jButton21))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(84, 84, 84)
                                .addComponent(jButton22))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(84, 84, 84)
                                .addComponent(jButton23))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(84, 84, 84)
                                .addComponent(jButton24))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(84, 84, 84)
                                .addComponent(jButton25))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(162, 162, 162)
                                .addComponent(jButton26))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(162, 162, 162)
                                .addComponent(jButton27))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(162, 162, 162)
                                .addComponent(jButton28))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(162, 162, 162)
                                .addComponent(jButton29))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(162, 162, 162)
                                .addComponent(jButton30)))))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 126, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jButton8, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton21, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton22, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton23, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton24, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton25, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton7, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton26, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGap(42, 42, 42)
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 48, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton27, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton28, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton29, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton30, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jButton10, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton9, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton11, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton12, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton13, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton14, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton15, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton5, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton6, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jButton16, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton17, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton18, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton19, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jButton20, javax.swing.GroupLayout.PREFERRED_SIZE, 69, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addGap(17, 17, 17))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(27, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ReflectiveOperationException | javax.swing.UnsupportedLookAndFeelException ex) {
            logger.log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(() -> new Modelado_Proc().setVisible(true));
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton10;
    private javax.swing.JButton jButton11;
    private javax.swing.JButton jButton12;
    private javax.swing.JButton jButton13;
    private javax.swing.JButton jButton14;
    private javax.swing.JButton jButton15;
    private javax.swing.JButton jButton16;
    private javax.swing.JButton jButton17;
    private javax.swing.JButton jButton18;
    private javax.swing.JButton jButton19;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton20;
    private javax.swing.JButton jButton21;
    private javax.swing.JButton jButton22;
    private javax.swing.JButton jButton23;
    private javax.swing.JButton jButton24;
    private javax.swing.JButton jButton25;
    private javax.swing.JButton jButton26;
    private javax.swing.JButton jButton27;
    private javax.swing.JButton jButton28;
    private javax.swing.JButton jButton29;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton30;
    private javax.swing.JButton jButton4;
    private javax.swing.JButton jButton5;
    private javax.swing.JButton jButton6;
    private javax.swing.JButton jButton7;
    private javax.swing.JButton jButton8;
    private javax.swing.JButton jButton9;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
