/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.alan.view.model;

import hr.alan.businessModel.Director;
import hr.alan.businessModel.Movie;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Alan
 */
public class DirectorTableModel extends AbstractTableModel{
    private static final String[] COLUMN_NAMES = {"Id", "First Name", "Last Name"};
    
    private List<Director> movies;

    public DirectorTableModel(List<Director> articles) {
        this.movies = articles;
    }

    public void setArticles(List<Director> articles) {
        this.movies = articles;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return movies.size();
    }

    @Override
    public int getColumnCount() {
        return Movie.class.getDeclaredFields().length - 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return movies.get(rowIndex).getId();
            case 1:
                return movies.get(rowIndex).getFirstName();
            case 2:
                return movies.get(rowIndex).getLastName();
            default:
                throw new RuntimeException("No such column");
        }
    }

    @Override
    public String getColumnName(int column) {
        return COLUMN_NAMES[column];
    }

    @Override
    public Class<?> getColumnClass(int columnIndex) {
        switch(columnIndex) {
            case 0:
                return Integer.class;
        }
        return super.getColumnClass(columnIndex); 
    }
}
