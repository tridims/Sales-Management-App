package projectbasdat;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Dimas Tri Mustakim
 */
public class DatabaseTools {
    private String user = "sa";
    private String password = "DimasTri049";
    
    private Connection connection;
    private String connectionUrl = 
            "jdbc:sqlserver://localhost;" + 
            "database=Project; " + 
            "user=" + user + ";" + 
            "password=" + password + ";" +
            "loginTimeout=30;";

    public DatabaseTools() {
        connectToSqlServer();
    }

    public String getUser() {
        return user;
    }

    public String getPassword() {
        return password;
    }
    
    public void changeDatabaseUserPassword(String user, String password) {
        this.user = user;
        this.password = password;
    }
    
    public ResultSet runQuery(String query) throws SQLException {
        Statement st = connection.createStatement();
        ResultSet rs = st.executeQuery(query);
        
        return rs;
    }
    
    public void runUpdateQuery(String query) throws  SQLException {
        Statement st = connection.createStatement();
        st.executeUpdate(query);
    }
    
    private void connectToSqlServer() {
        try {
            connection = DriverManager.getConnection(connectionUrl);    
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    
    public void close() {
        try {
            connection.close();
        } catch (SQLException ex) {
            Logger.getLogger(DatabaseTools.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
