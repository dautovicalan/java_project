/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/GUIForms/JPanel.java to edit this template
 */
package hr.alan.view;

import hr.alan.businessModel.Actor;
import hr.alan.businessModel.Director;
import hr.alan.businessModel.Person;
import hr.alan.dal.Repository;
import hr.alan.dal.RepositoryFactory;
import hr.alan.view.model.PersonTableModel;
import hr.algebra.utils.MessageUtils;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ListSelectionModel;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Alan
 */
public class EditActorsPanel extends javax.swing.JPanel {

    /**
     * Creates new form EditDirectorsPanel
     */
    private Repository repo;
    private PersonTableModel actorsTableModel;
    private Map<JTextComponent, JLabel> validationFields;
    private Actor selectedActor;
    
    public EditActorsPanel() {
        initComponents();
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        tbActors = new javax.swing.JTable();
        jLabel1 = new javax.swing.JLabel();
        tfFirstName = new javax.swing.JTextField();
        jLabel2 = new javax.swing.JLabel();
        tfLastName = new javax.swing.JTextField();
        btnUpdate = new javax.swing.JButton();
        btnDelete = new javax.swing.JButton();
        btnAdd = new javax.swing.JButton();
        lbLastNameError = new javax.swing.JLabel();
        lbFirstNameError = new javax.swing.JLabel();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        tbActors.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        tbActors.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                tbActorsMouseClicked(evt);
            }
        });
        tbActors.addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                tbActorsComponentShown(evt);
            }
        });
        tbActors.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyReleased(java.awt.event.KeyEvent evt) {
                tbActorsKeyReleased(evt);
            }
        });
        jScrollPane1.setViewportView(tbActors);

        jLabel1.setText("First Name");

        jLabel2.setText("Last Name");

        btnUpdate.setText("Update");
        btnUpdate.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnUpdateActionPerformed(evt);
            }
        });

        btnDelete.setBackground(new java.awt.Color(255, 51, 51));
        btnDelete.setText("Delete");
        btnDelete.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDeleteActionPerformed(evt);
            }
        });

        btnAdd.setText("Add");
        btnAdd.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAddActionPerformed(evt);
            }
        });

        lbLastNameError.setForeground(new java.awt.Color(255, 51, 102));

        lbFirstNameError.setForeground(new java.awt.Color(255, 51, 51));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(198, 198, 198)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 81, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(tfLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(tfFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addGap(27, 27, 27)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbLastNameError, javax.swing.GroupLayout.PREFERRED_SIZE, 19, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbFirstNameError, javax.swing.GroupLayout.PREFERRED_SIZE, 26, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 933, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfFirstName, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbFirstNameError, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(tfLastName, javax.swing.GroupLayout.PREFERRED_SIZE, 38, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbLastNameError, javax.swing.GroupLayout.PREFERRED_SIZE, 44, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(43, 43, 43)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnUpdate, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnDelete, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAdd, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 197, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(86, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        init();
    }//GEN-LAST:event_formComponentShown

    private void btnAddActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAddActionPerformed
        if (!formValid()) {
            return;
        }
        try {
            Actor actor = new Actor(
                    tfFirstName.getText().trim(), 
                    tfLastName.getText().trim());

            repo.createActor(actor);
            actorsTableModel.setArticles(repo.selectActors());

            clearForm();

        } catch (Exception ex) {
            Logger.getLogger(EditMoviesPanel.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_btnAddActionPerformed

    private void btnUpdateActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnUpdateActionPerformed
        if (selectedActor == null) {
            MessageUtils.showInformationMessage("Info", "Please select a director");
            return;
        }
        if (!formValid()) {
            return;
        }
        try {
            selectedActor.setFirstName(tfFirstName.getText().trim());
            selectedActor.setLastName(tfLastName.getText().trim());

            repo.updateActor(selectedActor.getId(), selectedActor);
            actorsTableModel.setArticles(repo.selectActors());

            clearForm();

        } catch (Exception ex) {
            Logger.getLogger(EditMoviesPanel.class.getName()).log(Level.SEVERE, null, ex);

        }
    }//GEN-LAST:event_btnUpdateActionPerformed

    private void btnDeleteActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDeleteActionPerformed
        if (selectedActor == null) {
            MessageUtils.showInformationMessage("Info", "Please select an actor");
            return;
        }
        if (MessageUtils.showConfirmDialog("Delete", "Are you sure you want to delete?") == JOptionPane.YES_OPTION) {
            try {
                repo.deleteActor(selectedActor.getId());
                actorsTableModel.setArticles(repo.selectActors());
                
                clearForm();
                
            } catch (Exception ex) {
                Logger.getLogger(EditMoviesPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_btnDeleteActionPerformed

    private void tbActorsKeyReleased(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_tbActorsKeyReleased
        showActor();
    }//GEN-LAST:event_tbActorsKeyReleased

    private void tbActorsComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_tbActorsComponentShown
        //init();
    }//GEN-LAST:event_tbActorsComponentShown

    private void tbActorsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_tbActorsMouseClicked
        showActor();
    }//GEN-LAST:event_tbActorsMouseClicked


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAdd;
    private javax.swing.JButton btnDelete;
    private javax.swing.JButton btnUpdate;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbFirstNameError;
    private javax.swing.JLabel lbLastNameError;
    private javax.swing.JTable tbActors;
    private javax.swing.JTextField tfFirstName;
    private javax.swing.JTextField tfLastName;
    // End of variables declaration//GEN-END:variables

    private void init() {
        try {
            initValidation();
            initRepository();
            initTable();
        } catch (Exception e) {
            System.out.println(e);
            System.exit(1);
        }
    }
    
    private void initValidation() {
        validationFields = new HashMap<>();
        validationFields.put(tfFirstName, lbFirstNameError);
        validationFields.put(tfLastName, lbLastNameError);
    }

    private void initRepository() {
        repo = RepositoryFactory.getRepository();
    }

    private void initTable() throws Exception {
        tbActors.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        tbActors.setAutoCreateRowSorter(true);
        tbActors.setRowHeight(25);
        List<Person> selectActors = repo.selectActors();
        actorsTableModel = new PersonTableModel(repo.selectActors());
        tbActors.setModel(actorsTableModel);
    }
    
    private void clearForm(){
        for (JTextComponent field : validationFields.keySet()) {
            field.setText("");
        }
        selectedActor = null;
    }
    
    private void showActor(){
        clearForm();
        
        int selectedRow = tbActors.getSelectedRow();
        int rowIndex = tbActors.convertRowIndexToModel(selectedRow);
        int id = (int) tbActors.getValueAt(rowIndex, 0);
        
        try {
            Optional<Actor> optActor = repo.selectActor(id);
            if (optActor.isPresent()) {
                selectedActor = optActor.get();
                fillForm(selectedActor);
            }
        } catch (Exception e) {
        }
    }
    
    private void fillForm(Actor actor) {
        tfFirstName.setText(actor.getFirstName());
        tfLastName.setText(actor.getLastName());
    }
    
    private boolean formValid() {

        boolean ok = true;
        
        for (Map.Entry<JTextComponent, JLabel> entry : validationFields.entrySet()) {
            System.out.println(entry.getKey() + ":" + entry.getValue());
            ok &= !entry.getKey().getText().trim().isEmpty();
            entry.getValue().setText(entry.getKey().getText().trim().isEmpty() ? "X" : "");
        }
        return ok;

    }
}
