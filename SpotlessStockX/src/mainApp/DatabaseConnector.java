package mainApp;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;

import logger.LoggerStockX;

public class DatabaseConnector {
	    private static String host = "myservice-stockx-rasmussen-stockx-ajs.a.aivencloud.com";
	    private static String port = "17817";
	    private static String userName = "avnadmin";
	    private static String password = "AVNS_uYYq-8I32N-sLAwgIO0";
	    private static String databaseName = "defaultdb";
	    
	    public Connection connectToDatabase() {
	        Connection connection = null;
	        try {
	            String jdbcUrl = "jdbc:mysql://" + host + ":" + port + "/" + databaseName;
	            connection = DriverManager.getConnection(jdbcUrl, userName, password);

	            if (connection != null) {
	                LoggerStockX.logger.info("Connected to DB successfully.");
	            } else {
	                LoggerStockX.logger.warning("Connection to DB is null.");
	            }
	        } catch (SQLException e) {
	            LoggerStockX.logger.log(Level.SEVERE, "Error connecting to the DB", e);
	            return null;  // Return null in case of an exception
	        }

	        LoggerStockX.logger.info("DB Connection Started");
	        return connection;
	    }


    public void addItem(String item, int quantity) {
        Connection connection = connectToDatabase();
        if (connection != null) {
            try {
                String sql = "INSERT INTO defaultdb (item_name, quantity) VALUES (?, ?)";
                try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
                    preparedStatement.setString(1, item);
                    preparedStatement.setInt(2, quantity);

                    int rowsAffected = preparedStatement.executeUpdate();

                    if (rowsAffected > 0) {
                        LoggerStockX.logger.info("Item added successfully!");
                    } else {
                        LoggerStockX.logger.info("Item NOT added successfully!");
                    }
                }
            } catch (SQLException e) {
                LoggerStockX.logger.log(Level.SEVERE, "Error executing SQL statement", e);
            } finally {
                try {
                    connection.close();
                } catch (SQLException e) {
                    LoggerStockX.logger.log(Level.SEVERE, "Error closing database connection", e);
                }
            }
        } else {
            LoggerStockX.logger.info("Failed Connection.");
        }
    }
    
    
    
    public void closeConnection(Connection connection) {
        if (connection != null) {
            try {
                connection.close();
            } catch (SQLException e) {
                LoggerStockX.logger.log(Level.SEVERE, "Error closing database connection", e);
            }
        }
    }
}
