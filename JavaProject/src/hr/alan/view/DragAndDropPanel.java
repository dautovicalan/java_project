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
import hr.alan.businessModel.PersonTransferable;
import hr.alan.dal.Repository;
import hr.alan.dal.RepositoryFactory;
import hr.algebra.utils.MessageUtils;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.JComponent;
import javax.swing.ListSelectionModel;
import javax.swing.TransferHandler;
import static javax.swing.TransferHandler.COPY;

/**
 *
 * @author Alan
 */
public class DragAndDropPanel extends javax.swing.JPanel {

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
        jScrollPane4 = new javax.swing.JScrollPane();
        lsDirectors = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        lsActors = new javax.swing.JList<>();
        Directors = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

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

        jScrollPane1.setViewportView(lsMovieCast);

        jScrollPane4.setViewportView(lsDirectors);

        jScrollPane2.setViewportView(lsActors);

        Directors.setText("Directors");

        jLabel2.setText("Actors");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(58, 58, 58)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 71, Short.MAX_VALUE)
                .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 283, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addGroup(layout.createSequentialGroup()
                .addGap(419, 419, 419)
                .addComponent(cbMovies, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(Directors, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(90, 90, 90))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(99, 99, 99)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(793, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(54, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(cbMovies, javax.swing.GroupLayout.PREFERRED_SIZE, 56, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(43, 43, 43))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(Directors, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jScrollPane4, javax.swing.GroupLayout.PREFERRED_SIZE, 443, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(83, 83, 83))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(90, 90, 90)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 46, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addContainerGap(547, Short.MAX_VALUE)))
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel Directors;
    private javax.swing.JComboBox<Object> cbMovies;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JScrollPane jScrollPane4;
    private javax.swing.JList<Actor> lsActors;
    private javax.swing.JList<Director> lsDirectors;
    private javax.swing.JList<Person> lsMovieCast;
    // End of variables declaration//GEN-END:variables

    private List<Actor> allActors;
    private List<Director> allDirectors;
    private List<Person> movieCastPersons;
    private List<MovieCast> allMovieCast;
    
    private final DefaultListModel<Actor> actorModel = new DefaultListModel<>();
    private final DefaultListModel<Director> directorModel = new DefaultListModel<>();
    private final DefaultListModel<Person> personMovieCastModel = new DefaultListModel<>();
    
    private void init() throws Exception {
        initRepo();
        loadMovies();
        loadAllActors();
        loadAllDirectors();
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

    private void loadAllDirectors() throws Exception {
        directorModel.clear();
        allDirectors = repo.selectDirectors();
        
        allDirectors.forEach(directorModel::addElement);
        lsDirectors.setModel(directorModel);
    }

    private void initDragNDrop() {
        lsActors.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lsActors.setDragEnabled(true);
        lsActors.setTransferHandler(new ExportHandler());
        
        lsDirectors.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lsDirectors.setDragEnabled(true);
        lsDirectors.setTransferHandler(new ExportHandler());
        
        lsMovieCast.setDropMode(DropMode.ON);
        lsMovieCast.setTransferHandler(new ImportHandler());
    }
    
    private void loadMovieCast() {
        if (cbMovies.getSelectedItem() == null) {
            return;
        }
        personMovieCastModel.clear();
        selectedMovie = (Movie) cbMovies.getSelectedItem();
        allMovieCast = repo.selectMovieCast(selectedMovie.getId());
        
        allMovieCast.forEach(element -> {
            if (element.getActor() != null) {
                personMovieCastModel.addElement(element.getActor());
            }
            if (element.getDirector() != null) {
                personMovieCastModel.addElement(element.getDirector());
            }
        });
        
        lsMovieCast.setModel(personMovieCastModel);
        
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
                repo.createCastDirector(selectedMovie.getId(), (Director) data);
                loadMovieCast();
                return true;
            } catch (UnsupportedFlavorException | IOException ex) {
                Logger.getLogger(DragAndDropPanel.class.getName()).log(Level.SEVERE, null, ex);
            }
            
            return false;
        }
    }

    
}
