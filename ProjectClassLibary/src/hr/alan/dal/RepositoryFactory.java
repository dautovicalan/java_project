/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.alan.dal;

import hr.alan.dal.sql.SqlRepository;

/**
 *
 * @author Alan
 */
public class RepositoryFactory {
    
    private RepositoryFactory(){
    }
    
    public static Repository getRepository(){
        return new SqlRepository();
    }
    
}
