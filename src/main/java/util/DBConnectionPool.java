package util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;

public class DBConnectionPool {
    String url;
    String username;
    String password;
    ArrayList<Connection> connList = new ArrayList<Connection>();

    public DBConnectionPool(String driver, String url, String username, String password) throws ClassNotFoundException {
        this.url = url;
        this.username = username;
        this.password = password;
        Class.forName(driver);
    }

    public Connection getConnection() throws SQLException {
        if(connList.size() > 0) {
            Connection conn = connList.get(0);
            if(conn.isValid(10)){
                return conn;
            }
        }
        return DriverManager.getConnection(url, username, password);
    }
    // 커넥션 객체를 쓰고 난 후 커넥션 풀에 반환
    public void returnConnection(Connection connection) {
        connList.add(connection);
    }

    public void closeAll() {
        for(Connection conn : connList) {
            try { conn.close(); } catch(Exception e){}
        }
    }
}
