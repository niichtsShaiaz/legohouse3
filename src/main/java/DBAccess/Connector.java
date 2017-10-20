package DBAccess;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Connector {
    

    private static final String url = "jdbc:mysql://165.227.147.156/useradmin";
    private static final String username = "lain";
    private static final String password = "shx";
    private static Connection singleton;

    public static Connection connection() throws ClassNotFoundException, SQLException  {
        if ( singleton == null ) {
            Class.forName( "com.mysql.jdbc.Driver" );
            singleton = DriverManager.getConnection( url, username, password );
        }   
        return singleton;
    }

}
