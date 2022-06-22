/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.alan.view.model;

import hr.alan.businessModel.Movie;
import javax.swing.table.AbstractTableModel;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Alan
 */
public class MovieTableModel extends AbstractTableModel{
    private static final String[] COLUMN_NAMES = {"Id", "Title", "Publish Date", "Description", "Original Title", "Duration"
                                                   , "Movie Genre", "Picture Path", "Movie Link", "Movie Begin Date"};
    
    private List<Movie> movies;

    public MovieTableModel(List<Movie> articles) {
        this.movies = articles;
    }

    public void setArticles(List<Movie> articles) {
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
                return movies.get(rowIndex).getTitle();
            case 2:
                return movies.get(rowIndex).getPubDate();
            case 3:
                return movies.get(rowIndex).getMovieDescription();
            case 4:
                return movies.get(rowIndex).getOrigTitle();
            case 5:
                return movies.get(rowIndex).getDuration();
                //return articles.get(rowIndex).getPublishedDate().format(Article.DATE_FORMATTER);
            case 6:
                return movies.get(rowIndex).getId();
            case 7:
                return movies.get(rowIndex).getMoviePicturePath();
            case 8:
                return movies.get(rowIndex).getMovieLink();
            case 9:
                return movies.get(rowIndex).getMovieBegin();
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
