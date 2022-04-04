/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.sql.DataSource;

/**
 *
 * @author Andrey Belov
 */
public class Master {
    
    public static final String tableName = "master";
    
    private int masterId;
    private String location;
    private String fName;
    private String lName;
    
    private Connection con;

    public Master() {
        masterId = -1;
        fName = "";
        lName = "";
    }

    public Master(int masterId, String location, String fName, String lName) {
        this.masterId = masterId;
        this.location = location;
        this.fName = fName;
        this.lName = lName;
    }

    public int getMasterId() {
        return masterId;
    }

    public void setMasterId(int masterId) {
        this.masterId = masterId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getFName() {
        return fName;
    }

    public void setFName(String fName) {
        this.fName = fName;
    }

    public String getLName() {
        return lName;
    }

    public void setLName(String lName) {
        this.lName = lName;
    }
    
     public Connection getCon() {
        return con;
    }

   public void setCon(DataSource ds) throws SQLException {
        this.con = ds.getConnection();
    }
    
    public void load(int id) throws SQLException {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT master_Id, location, f_name, l_name FROM " + tableName + " WHERE master_Id = " + id);
        if (rs.next()) {
            masterId = rs.getInt("master_id");
            location = rs.getString("location");
            fName = rs.getString("f_name");
            lName = rs.getString("l_name");
            return;
        } else {
            masterId = -1;
            fName = "";
            lName = "";
        }
        rs.close();
        st.close();
    }
    public void load(DataSource ds, int id) throws SQLException {
        Connection con = ds.getConnection();
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT master_id, location, f_name, l_name FROM " + tableName + " WHERE master_id = " + id);
        if (rs.next()) {
            masterId = rs.getInt("master_id");
            location = rs.getString("location");
            fName = rs.getString("f_name");
            lName = rs.getString("l_name");
            return;
        } else {
            masterId = -1;
            fName = "";
            lName = "";
        }
        rs.close();
        st.close();
        con.close();
    }
    
    public static void del(DataSource ds, int id) throws SQLException {
        if (id == -1) {
            return;
        }
        Connection con = ds.getConnection();
        Statement st = con.createStatement();
        st.executeUpdate("DELETE FROM " + tableName + " WHERE master_id=" + id);
        st.close();
        con.close();
    }
    
    public void del( int id) throws SQLException {
        if (id == -1) {
            return;
        }
        Statement st = con.createStatement();
        st.executeUpdate("DELETE FROM " + tableName + " WHERE master_id=" + id);
        st.close();
        masterId = -1;
        
    }
    
        public void add() throws SQLException {
            if (masterId != -1) {
                return;
            }

            Statement st = con.createStatement();
            st.executeUpdate("INSERT INTO " + tableName + " (location, f_name, l_name) VALUES(" + location + ", '" + fName + "'" + ", '" + lName + "')");
            st.close();
    }
        
        public void update() throws SQLException {
        if (masterId == -1) {
            return;
        }
        Statement st = con.createStatement();
        String qu = "UPDATE " + tableName + "location=" + location + ", f_name='" + fName + "'," + " l_name='" + lName + "' WHERE master_id=" + masterId;
        st.executeUpdate(qu);
        st.close();
    }
        
        public void save() throws SQLException {
        if (masterId == -1) {
            add();
        } else {
            update();
        }
    }
}
