package se.jensensthlm.testing_helpers;

import se.jensensthlm.jensen_library.Config;

import java.io.Closeable;
import java.sql.*;

/**
 *
 */
public class SchemaInformation implements Closeable {
    private Connection db;
    private String dbName = null;

    public SchemaInformation() {
        try {
            db = DriverManager.getConnection(
                    Config.DB_CONNECTION,
                    Config.DB_USER,
                    Config.DB_PASS
            );
        } catch (Exception e) {
            System.out.printf("Error occurred: %s", e.getMessage());
        }
    }

    public String getDbName() {
        if (dbName == null) {
            dbName = fetchDbName();
        }
        return dbName;
    }

    public void close() {
        try {
            db.close();
        } catch (SQLException e) {
            return;
        }
    }

    /*
    Implement here:

    getPrimaryKeys()

    getForeignKeys()
    
    getTypes()
    */


    private String fetchDbName() {
        try {
            ResultSet rs = executeQuery("SELECT DATABASE() as `db` FROM DUAL");
            rs.next();
            return rs.getString("db");
        } catch (Exception e) {
            return null;
        }
    }
    private ResultSet executeQuery(String query, String... params) throws SQLException {
        Statement stmt = db.createStatement();
        ResultSet rs = stmt.executeQuery(String.format(query, params));
        return rs;
    }
}
