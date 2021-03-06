/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JFrame.java to edit this template
 */
package proyecto_final;

import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author cesarromanzuniga
 */
public class Consultas extends javax.swing.JFrame {
    
    private void vaciarTabla(DefaultTableModel tabla){
        int size = tabla.getRowCount();
        
        for(int i = 0; i<size; i++){
                try {
                    tabla.removeRow(0);
                } catch (Exception e) {
                    
                }
        }
    }

    
    private void rellenarTabla(DefaultTableModel tabla){
        Base base = new Base();
        int sala = jSala.getSelectedIndex() + 1;
        ResultSet resultado = base.getEventosEnSala(sala);
        
        try {
            while(resultado.next()){
                Object objeto[] = new Object[5];
                objeto[0] = resultado.getString("id");
                objeto[1] = resultado.getString("fecha");
                objeto[2] = resultado.getString("hora");
                objeto[3] = resultado.getString("titulo");
                
                if( resultado.getString("busy_seats") == null ){
                    objeto[4] = 18;
                }else{
                    objeto[4] = 18 - Integer.parseInt(resultado.getString("busy_seats"));
                }
                tabla.addRow(objeto);
            }       
        } 
        catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Los eventos no pudieron ser encontrados", "Error", JOptionPane.INFORMATION_MESSAGE);
        }

        base.closeBase();
        
    }
            

    /**
     * Creates new form Consultas
     */
    DefaultTableModel tabla;
    public Consultas() {
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setSalasList();
        tabla = (DefaultTableModel) jTable1.getModel();
    }
    
    
    public void setSalasList(){
        jSala.removeAllItems();
        try {
            Base base = new Base();
            ResultSet salas = base.getSalas();
            
            while(salas.next()){
                jSala.addItem("Sala " + salas.getString("Id")); 
            }       
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "Las Salas no pudieron ser encontradas, favor de intentarlo mas tarde", "Error", JOptionPane.INFORMATION_MESSAGE);

        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jEditorPane1 = new javax.swing.JEditorPane();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jDelete = new javax.swing.JButton();
        jEdit = new javax.swing.JButton();
        jSala = new javax.swing.JComboBox<>();
        jDetalle = new javax.swing.JButton();

        jScrollPane2.setViewportView(jEditorPane1);

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setText("Consultar : ");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "ID", "Fecha", "Hora", "Titulo", "Asientos Disponibles"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class, java.lang.String.class, java.lang.Integer.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);
        if (jTable1.getColumnModel().getColumnCount() > 0) {
            jTable1.getColumnModel().getColumn(0).setResizable(false);
            jTable1.getColumnModel().getColumn(1).setResizable(false);
            jTable1.getColumnModel().getColumn(2).setResizable(false);
            jTable1.getColumnModel().getColumn(3).setResizable(false);
            jTable1.getColumnModel().getColumn(4).setResizable(false);
        }

        jDelete.setText("Eliminar");
        jDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDeleteActionPerformed(evt);
            }
        });

        jEdit.setText("Editar");
        jEdit.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jEditActionPerformed(evt);
            }
        });

        jSala.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "Item 1", "Item 2", "Item 3", "Item 4" }));
        jSala.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jSalaActionPerformed(evt);
            }
        });

        jDetalle.setText("Detalle");
        jDetalle.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jDetalleActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jSala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jDelete)
                        .addGap(31, 31, 31)
                        .addComponent(jEdit)
                        .addGap(32, 32, 32)
                        .addComponent(jDetalle)
                        .addGap(40, 40, 40))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 641, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 43, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jSala, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 297, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jDelete)
                            .addComponent(jEdit))
                        .addContainerGap(23, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jDetalle)
                        .addGap(23, 23, 23))))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jSalaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jSalaActionPerformed
        tabla = (DefaultTableModel) jTable1.getModel();
        vaciarTabla(tabla);
        rellenarTabla(tabla);

    }//GEN-LAST:event_jSalaActionPerformed

    private void jDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDeleteActionPerformed
        tabla = (DefaultTableModel) jTable1.getModel();
        int row_id = jTable1.getSelectedRow();
        if (row_id == -1){
            JOptionPane.showMessageDialog(null, "Ningun Evento fue seleccionado", "Error", JOptionPane.INFORMATION_MESSAGE);
        }else{
            String event_id = jTable1.getModel().getValueAt(row_id, 0).toString();
            String event = jTable1.getModel().getValueAt(row_id, 3).toString();
            int disponibles = Integer.parseInt(jTable1.getModel().getValueAt(row_id, 4).toString());
            
            System.out.println(disponibles);
            if(disponibles == 18){
                int opcion = JOptionPane.showConfirmDialog(null,String.format("Estas Seguro que quieres eliminar la funci??n: %s", event));
                if(opcion == 0){
                    System.out.println("Eliminando Opcion" + event_id);
                    Base base = new Base();
                    int id = Integer.parseInt(event_id);
                    String response = base.dropEvent(id);

                    vaciarTabla(tabla);
                    rellenarTabla(tabla);

                    JOptionPane.showMessageDialog(null, response, "Exito", JOptionPane.INFORMATION_MESSAGE);
                }
            }
            else{
                JOptionPane.showMessageDialog(null, "No se puede eliminar el evento porque ya existen reservacinones", "Error", JOptionPane.INFORMATION_MESSAGE);
            }  
        }   
    }//GEN-LAST:event_jDeleteActionPerformed

    private void jEditActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jEditActionPerformed
        
        tabla = (DefaultTableModel) jTable1.getModel();
        int row_id = jTable1.getSelectedRow();
        if (row_id == -1){
            JOptionPane.showMessageDialog(null, "Ningun Evento fue seleccionado", "Error", JOptionPane.INFORMATION_MESSAGE);
        }else{
            int event_id = Integer.parseInt(jTable1.getModel().getValueAt(row_id, 0).toString());
            Exhibiciones ventana = new Exhibiciones(event_id);
            ventana.setVisible(true);
            this.setVisible(false);
        }
    }//GEN-LAST:event_jEditActionPerformed

    private void jDetalleActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jDetalleActionPerformed
        tabla = (DefaultTableModel) jTable1.getModel();
        int row_id = jTable1.getSelectedRow();

        if (row_id == -1){
            JOptionPane.showMessageDialog(null, "Ningun Evento fue seleccionado", "Error", JOptionPane.INFORMATION_MESSAGE);
        }else{
            int event_id = Integer.parseInt(jTable1.getModel().getValueAt(row_id, 0).toString());
            new Detalle(event_id);
        }

    }//GEN-LAST:event_jDetalleActionPerformed

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
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Consultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Consultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Consultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Consultas.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new Consultas().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton jDelete;
    private javax.swing.JButton jDetalle;
    private javax.swing.JButton jEdit;
    private javax.swing.JEditorPane jEditorPane1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JComboBox<String> jSala;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables
}
