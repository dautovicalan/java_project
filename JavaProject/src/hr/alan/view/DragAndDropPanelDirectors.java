/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hr.alan.view;

import hr.alan.businessModel.Actor;
import hr.alan.businessModel.Director;
import hr.alan.businessModel.Movie;
import hr.alan.businessModel.MovieArchive;
import hr.alan.businessModel.MovieCast;
import hr.alan.businessModel.Person;
import hr.alan.businessModel.PersonAddable;
import hr.alan.businessModel.PersonTransferable;
import hr.alan.dal.Repository;
import hr.alan.dal.RepositoryFactory;
import hr.algebra.utils.JAXBUtils;
import hr.algebra.utils.MessageUtils;
import hr.algebra.utils.PrepareArchiveUtils;
import java.awt.datatransfer.Transferable;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.TreeSet;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultComboBoxModel;
import javax.swing.DefaultListModel;
import javax.swing.DropMode;
import javax.swing.JComponent;
import javax.swing.ListModel;
import javax.swing.ListSelectionModel;
import javax.swing.TransferHandler;
import static javax.swing.TransferHandler.COPY;

/**
 *
 * @author Alan
 */
public class DragAndDropPanelDirectors extends javax.swing.JPanel{

    /**
     * Creates new form DragAndDropPanel
     */
    
    private static final String FILENAME = "moviearchive.xml";

    
    private Repository repo;
    private Movie selectedMovie;
    
    public DragAndDropPanelDirectors() {
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
        lsMovieDirectors = new javax.swing.JList<>();
        jScrollPane2 = new javax.swing.JScrollPane();
        lsDirectors = new javax.swing.JList<>();
        jLabel2 = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btnRemoveDirector = new javax.swing.JButton();

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

        lsMovieDirectors.addListSelectionListener(new javax.swing.event.ListSelectionListener() {
            public void valueChanged(javax.swing.event.ListSelectionEvent evt) {
                lsMovieDirectorsValueChanged(evt);
            }
        });
        jScrollPane1.setViewportView(lsMovieDirectors);

        jScrollPane2.setViewportView(lsDirectors);

        jLabel2.setText("Directors");

        jLabel1.setText("Select movie:");

        btnRemoveDirector.setText("Remove actor from movie");
        btnRemoveDirector.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRemoveDirectorActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(148, 148, 148)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 285, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btnRemoveDirector, javax.swing.GroupLayout.PREFERRED_SIZE, 232, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(92, 92, 92))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(222, 222, 222)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(50, 50, 50)
                .addComponent(cbMovies, javax.swing.GroupLayout.PREFERRED_SIZE, 165, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(167, 167, 167))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 447, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 445, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(83, 83, 83))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(btnRemoveDirector, javax.swing.GroupLayout.PREFERRED_SIZE, 59, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(155, 155, 155))))
        );
    }// </editor-fold>//GEN-END:initComponents

    private void formComponentShown(java.awt.event.ComponentEvent evt) {//GEN-FIRST:event_formComponentShown
        try {
            init();
        } catch (Exception ex) {
            Logger.getLogger(DragAndDropPanelDirectors.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Error", "Something went wrong");
            System.exit(1);
        }
    }//GEN-LAST:event_formComponentShown

    private void cbMoviesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cbMoviesActionPerformed
        try {
            loadMovieCast();
        } catch (Exception ex) {
            Logger.getLogger(DragAndDropPanelDirectors.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Error", "Something went wrong. Contact BOSS");
        }
    }//GEN-LAST:event_cbMoviesActionPerformed

    private void lsMovieDirectorsValueChanged(javax.swing.event.ListSelectionEvent evt) {//GEN-FIRST:event_lsMovieDirectorsValueChanged

    }//GEN-LAST:event_lsMovieDirectorsValueChanged

    private void btnRemoveDirectorActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRemoveDirectorActionPerformed
        if (lsMovieDirectors.getSelectedValue() == null) {
            MessageUtils.showErrorMessage("Error", "Please select actor");
            return;
        }
        try {
            repo.deleteDirectorFromMovie(((Movie)cbMovies.getSelectedItem()).getId(),
                    lsMovieDirectors.getSelectedValue().getId());
            loadMovieCast();
        } catch (Exception ex) {
            Logger.getLogger(DragAndDropPanelDirectors.class.getName()).log(Level.SEVERE, null, ex);
            MessageUtils.showErrorMessage("Error", "Something went wrong. Contact BOSS");
        }
        
    }//GEN-LAST:event_btnRemoveDirectorActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnRemoveDirector;
    private javax.swing.JComboBox<Object> cbMovies;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList<Person> lsDirectors;
    private javax.swing.JList<Person> lsMovieDirectors;
    // End of variables declaration//GEN-END:variables

    private List<Person> allDirectors;
    private Person movieDirectors;
    
    private final DefaultListModel<Person> directorModel = new DefaultListModel<>();
    private final DefaultListModel<Person> directorMovieDirectorModel = new DefaultListModel<>();
    
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
    
    private void loadMovies() throws Exception {
        cbMovies.setModel(new DefaultComboBoxModel<>(repo.selectMovies().toArray()));
    }

    private void loadAllActors() throws Exception {
        directorModel.clear();
        allDirectors = repo.selectDirectors();
        
        allDirectors.forEach(directorModel::addElement);
        lsDirectors.setModel(directorModel);
    }

    private void initDragNDrop() {
        lsDirectors.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        lsDirectors.setDragEnabled(true);
        lsDirectors.setTransferHandler(new ExportHandler());
        
        lsMovieDirectors.setDropMode(DropMode.ON);
        lsMovieDirectors.setTransferHandler(new ImportHandler());
    }
    
    private void loadMovieCast() throws Exception {
        if (cbMovies.getSelectedItem() == null) {
            return;
        }
        directorMovieDirectorModel.clear();
        selectedMovie = (Movie) cbMovies.getSelectedItem();

        Optional<Person> movieDir = repo.selectMovieDirectors(selectedMovie.getId());
        if (movieDir.isPresent()) {                
            directorMovieDirectorModel.addElement(movieDir.get());
        }
        lsMovieDirectors.setModel(directorMovieDirectorModel);
        
    }

    private MovieArchive prepareArchive() throws Exception{
        return PrepareArchiveUtils.prepareArchive();
    }
    
    private class ExportHandler extends TransferHandler {

        @Override
        public int getSourceActions(JComponent c) {
            return COPY; 
        }

        @Override
        protected Transferable createTransferable(JComponent c) {
            return new PersonTransferable(lsDirectors.getSelectedValue()); 
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
                if (lsMovieDirectors.getModel().getSize() == 1) {
                    MessageUtils.showErrorMessage("Error", "Comeon, please only one director");
                    return false;
                }
                Person data = (Person) transferable.getTransferData(PersonTransferable.PERSON_FLAVOR);
                directorMovieDirectorModel.addElement(data);
                repo.createCastDirector(selectedMovie.getId(), data.getId());
                loadMovieCast();
                return true;
            } catch (Exception ex) {
                Logger.getLogger(DragAndDropPanelDirectors.class.getName()).log(Level.SEVERE, null, ex);
            }
            return false;
        }
    }

    
}
