/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package hr.alan.dal.sql;

import javax.sql.DataSource;
import com.microsoft.sqlserver.jdbc.SQLServerDataSource;

/**
 *
 * @author Alan
 */
public final class DataSourceSingleton {
    private static final String SERVER_NAME = "localhost";
    private static final String DATABASE_NAME = "JavaProject";
    private static final String USER = "Admin"; 
    private static final String PASSWORD = "123456789"; 

    private DataSourceSingleton() {}

    private static DataSource instance;

    public static DataSource getInstance() {
        if (instance == null) {
            instance = createInstance();
        }
        return instance;
    }
    private static DataSource createInstance() {
        SQLServerDataSource dataSource = new SQLServerDataSource();
        dataSource.setServerName(SERVER_NAME);
        dataSource.setDatabaseName(DATABASE_NAME);
        dataSource.setUser(USER);
        dataSource.setPassword(PASSWORD);
        return dataSource;
    } 
}
