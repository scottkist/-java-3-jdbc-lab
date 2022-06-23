package edu.cscc.utils;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionCloser {

     static void closeConnection(Connection connection) {
        try {
            if (connection != null && !connection.isClosed()) {
                try {
                    connection.close();
                } catch (SQLException exception) {
                    exception.printStackTrace();
                }
            }
        } catch (SQLException exception) {
            exception.printStackTrace();
        }
    }
}
