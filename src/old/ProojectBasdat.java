
package old;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.ResultSet;
import java.sql.Statement;
/**
 *
 * @author tridi
 */
public class ProojectBasdat {

    public static void main(String[] args) {
        String connectionUrl = 
                "jdbc:sqlserver://localhost;" +
                "database=Project; user=sa; password=DimasTri049;" +
                "loginTimeout=30;";
            
        ResultSet rs = null;
        
        try (Connection connection = DriverManager.getConnection(connectionUrl);) {
            Statement statement = connection.createStatement();
            String query_sql = "select * from customer_profile";
            
            rs = statement.executeQuery(query_sql);
            
            while (rs.next()) {
                System.out.println(rs.getInt("id_pelanggan") + ": " + 
                        rs.getString("nama_pelanggan") + " (" + rs.getString("tanggal_lahir") + ")");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
}
