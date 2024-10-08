import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;


public class ConexaoDB {

    public static Connection getConnection(){
        try{
            return DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/delivery?user=root&password=root"
            );
        }catch (SQLException error){
            System.out.println("Error to connect to database: "+error.toString());
            return null;
        }
    }
}
