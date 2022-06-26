/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.alan.view.model;

import hr.alan.businessModel.Actor;
import hr.alan.businessModel.Movie;
import hr.alan.businessModel.Person;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Alan
 */
public class PersonTableModel extends AbstractTableModel{
    private static final String[] COLUMN_NAMES = {"Id", "First Name", "Last Name"};
    
    private List<Person> persons;

    public PersonTableModel(List<Person> persons) {
        this.persons = persons;
    }

    public void setArticles(List<Person> persons) {
        this.persons = persons;
        fireTableDataChanged();
    }

    @Override
    public int getRowCount() {
        return persons.size();
    }

    @Override
    public int getColumnCount() {
        return Person.class.getDeclaredFields().length;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        switch (columnIndex) {
            case 0:
                return persons.get(rowIndex).getId();
            case 1:
                return persons.get(rowIndex).getFirstName();
            case 2:
                return persons.get(rowIndex).getLastName();
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
