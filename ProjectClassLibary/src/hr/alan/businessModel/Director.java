/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.alan.businessModel;

/**
 *
 * @author Alan
 */
public class Director extends Person{
    
    public Director(int id, String firstName, String lastName) {
        super(id, firstName, lastName);
    }
    public Director(String firstName, String lastName) {
        super(firstName, lastName);
    }
}
