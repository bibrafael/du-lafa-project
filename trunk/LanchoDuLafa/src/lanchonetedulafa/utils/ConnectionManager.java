package lanchonetedulafa.utils;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {

    private static ConnectionManager instance = null;

    public static ConnectionManager getInstance() {
        if (instance == null) {
            instance = new ConnectionManager();
        }
        return instance;
    }
    private String driver;
    private String url;
    private String user;
    private String pass;
    private Connection conn = null;

    private ConnectionManager() {
        this.driver = "org.postgresql.Driver";
        this.url = "jdbc:postgresql://localhost:5432/dulafa/";
        this.user = "postgres";
        this.pass = "postgres";
    }

    public Connection getConnection() throws Exception {
        if ((conn == null) || (conn.isClosed())) {
            Class.forName(driver);
            conn = DriverManager.getConnection(url, user, pass);
        }
        return (conn);
    }
}
