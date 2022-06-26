/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.alan.view;

import hr.alan.businessModel.Actor;
import hr.alan.businessModel.Director;
import hr.alan.businessModel.Movie;
import hr.alan.businessModel.MovieCast;
import hr.alan.businessModel.Person;
import hr.alan.businessModel.PersonAddable;
import hr.alan.businessModel.PersonTransferable;
import hr.alan.dal.Repository;
import hr.alan.dal.RepositoryFactory;
import hr.algebra.utils.MessageUtils;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.JComponent;
import javax.swing.JList;
import javax.swing.ListSelectionModel;
import javax.swing.TransferHandler;
import static javax.swing.TransferHandler.COPY;

/**
 *
 * @author Alan
 */
public class DragAndDropPanel extends javax.swing.JPanel implements PersonAddable{

    /**
     * Creates new form DragAndDropPanel
     */
    
    private Repository repo;
    private Movie selectedMovie;
    
    public DragAndDropPanel() {
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

        cbMovies = new javax.swing.JComboBox<>();
        jScrollPane1 = new javax.swing.JScrollPane();
        lsMovieCast = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        lsActors = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnRemoveActor = new javax.swing.JButton();

        addComponentListener(new java.awt.event.ComponentAdapter() {
            public void componentShown(java.awt.event.ComponentEvent evt) {
                formComponentShown(evt);
            }
        });

        cbMovies.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cbMoviesActionPerformed(evt);
            }
        });

        lsMovieCast.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lsMovieCastValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lsMovieCast);

        jScrollPane2.setViewportView(lsActors);

        jLabel2.setText("Actors");

        jLabel1.setText("Select movie:");

        btnRemoveActor.setText("Remove actor from movie");
        btnRemoveActor.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveActorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(148, 148, 148)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(222, 222, 222)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 224, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(cbMovies, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(167, 167, 167))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnRemoveActor, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(118, 118, 118))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addContainerGap(50, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(cbMovies, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(47, 47, 47))
                            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18))))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 47, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(8, 8, 8)
                .addComponent(btnRemoveActor, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        try {
            init();
        } catch (Exception ex) {
            Logger.getLogger(DragAndDropPanel.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Error", "Something went wrong");
            System.exit(1);
        }
    }//GEN-LAST:event_formComponentShown

    private void cbMoviesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMoviesActionPerformed
        loadMovieCast();
    }//GEN-LAST:event_cbMoviesActionPerformed

    private void lsMovieCastValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lsMovieCastValueChanged

    }//GEN-LAST:event_lsMovieCastValueChanged

    private void btnRemoveActorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveActorActionPerformed
        if (lsMovieCast.getSelectedValue() == null) {
            MessageUtils.showErrorMessage("Error", "Please select actor");
            return;
        }
        repo.deleteActorFromMovie(((Movie)cbMovies.getSelectedItem()).getId(),
                lsMovieCast.getSelectedValue().getId());
        loadMovieCast();
    }//GEN-LAST:event_btnRemoveActorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRemoveActor;
    private javax.swing.JComboBox<Object> cbMovies;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<Person> lsActors;
    private javax.swing.JList<Person> lsMovieCast;
    // End of variables declaration//GEN-END:variables

    private List<Person> allActors;
    private Set<Person> movieCastPersons = new TreeSet<>();
    
    private final DefaultListModel<Person> actorModel = new DefaultListModel<>();
    private final DefaultListModel<Person> actorMovieCastModel = new DefaultListModel<>();
    
    private void init() throws Exception {
        initRepo();
        loadMovies();
        loadAllActors();
        initDragNDrop();
        loadMovieCast();
    }
    
    private void initRepo() {
        repo = RepositoryFactory.getRepository();
    }
    
    private void loadMovies() {
        cbMovies.setModel(new DefaultComboBoxModel<>(repo.selectMovies().toArray()));
    }

    private void loadAllActors() {
        actorModel.clear();
        allActors = repo.selectActors();
        
        allActors.forEach(actorModel::addElement);
        lsActors.setModel(actorModel);
    }

    private void initDragNDrop() {
        lsActors.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lsActors.setDragEnabled(true);
        lsActors.setTransferHandler(new ExportHandler());
        
        lsMovieCast.setDropMode(DropMode.ON);
        lsMovieCast.setTransferHandler(new ImportHandler());
    }
    
    private void loadMovieCast() {
        if (cbMovies.getSelectedItem() == null) {
            return;
        }
        actorMovieCastModel.clear();
        selectedMovie = (Movie) cbMovies.getSelectedItem();
        
        movieCastPersons = repo.selectMovieCastActor(selectedMovie.getId());
                
        movieCastPersons.forEach(
                actorMovieCastModel::addElement);
        
        lsMovieCast.setModel(actorMovieCastModel);
        
    }

    @Override
    public boolean addPerson(Person person) {
        if (movieCastPersons.add(person)) {
            loadMovieCast();
            return true;
        }
        return false;
    }
    
    private class ExportHandler extends TransferHandler {

        @Override
        public int getSourceActions(JComponent c) {
            return COPY; 
        }

        @Override
        protected Transferable createTransferable(JComponent c) {
            return new PersonTransferable(lsActors.getSelectedValue()); 
        }
    }
    
    private class ImportHandler extends TransferHandler {

        @Override
        public boolean canImport(TransferSupport support) {
            return support.isDataFlavorSupported(PersonTransferable.PERSON_FLAVOR);
        }

        @Override
        public boolean importData(TransferSupport support) {
            
            Transferable transferable = support.getTransferable();
            
            try {
                Person data = (Person) transferable.getTransferData(PersonTransferable.PERSON_FLAVOR);
                if (movieCastPersons.add(data)) {
                    repo.createCastActor(selectedMovie.getId(), (Actor) data);
                    loadMovieCast();
                    return true;
                }                
            } catch (UnsupportedFlavorException | IOException ex) {
                Logger.getLogger(DragAndDropPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            MessageUtils.showErrorMessage("Error", "Already in list");
            return false;
        }
    }

    
}
