/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package logins;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author tej
 */
public class JDBCData {

    public static void main(String[] args) throws SQLException {
        JDBCData jdbcData = new JDBCData();
        //Apps
        //jdbcData.insertApp(4, "a", "b");
        //jdbcData.getAllApps();
        //jdbcData.updateApp(4, "test", "test");
        jdbcData.deleteApp(4);
    }

    private void insertApp(int id, String name, String using_app) throws SQLException {
        Connection con = getConnection();
        Statement stmt = con.createStatement();
        boolean rs = stmt.execute("INSERT INTO apps (id, name, using_apps) VALUES (" + id + ", '" + name + "', '" + using_app + "')");
        stmt.execute("commit");
        stmt.close();
        con.close();
    }

    private void getAllApps() throws SQLException {
        Connection con = getConnection();
        Statement stmt = con.createStatement();
        ResultSet rs = stmt.executeQuery("SELECT id, name, using_apps FROM apps");
        while (rs.next()) {
            int id = rs.getInt("id");
            String name = rs.getString("name");
            String usingApps = rs.getString("using_apps");
            System.out.println("id: " + id + " name: " + name + " using_apps: " + usingApps);
        }
        stmt.close();
        con.close();
    }

    private void updateApp(int id, String name, String usingApps) throws SQLException {
        Connection con = getConnection();
        Statement stmt = con.createStatement();
        boolean rs = stmt.execute("Update apps SET name = '" + name + "', using_apps ='" + usingApps + "' WHERE id=" + id);
        stmt.execute("commit");
        stmt.close();
        con.close();
    }
    
    private void deleteApp(int id) throws SQLException {
        Connection con = getConnection();
        Statement stmt = con.createStatement();
        boolean rs = stmt.execute("DELETE from apps WHERE id=" + id);
        stmt.execute("commit");
        stmt.close();
        con.close();
    }

    private Connection getConnection() {
        try {
            String url = "jdbc:mysql://localhost:3306/jio";
            return DriverManager.getConnection(url, "root", "Anish@123");
        } catch (SQLException ex) {
            Logger.getLogger(JDBCData.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

}
