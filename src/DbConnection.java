
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Nishan Dhungana
 */
public class DbConnection {
    private final String url = "jdbc:mysql://localhost:3306/quiz";
    private final String username = "root";
    private final String password = "";
    
    protected Connection getConnection() throws Exception {
        Connection connection = null;
        
        try {
            Class.forName("com.mysql.jdbc.Driver");
            connection = DriverManager.getConnection(url, username, password);
            
        } catch(ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
            throw ex;
        }
        
        return connection;
    }
}
