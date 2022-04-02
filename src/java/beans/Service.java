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
public class Service {
    
    public static final String tableName = "service";
    
    private int serviseId;
    private String name;
    
    private Connection con;

    public Service(int serviseId, String name) {
        this.serviseId = serviseId;
        this.name = name;
    }

    public Service() {
            serviseId = -1;
            name = "";
    }

    public int getServiseId() {
        return serviseId;
    }

    public void setServiseId(int serviseId) {
        this.serviseId = serviseId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Connection getCon() {
        return con;
    }

    public void setCon(Connection con) {
        this.con = con;
    }
    
    public void load(int id) throws SQLException {
        Statement st = con.createStatement();
        ResultSet rs = st.executeQuery("SELECT service_id, name FROM " + tableName + " WHERE service_id = " + id);
        if (rs.next()) {
            serviseId = rs.getInt("service_id");
            name = rs.getString("name");
            return;
        } else {
            serviseId = -1;
            name = "";
        }
        rs.close();
        st.close();
    }
    
    public static void del(DataSource ds, int id) throws SQLException {
        if (id == -1) {
            return;
        }
        Connection con = ds.getConnection();
        Statement st = con.createStatement();
        st.executeUpdate("DELETE FROM " + tableName + " WHERE service_id=" + id);
        st.close();
        con.close();
    }
    
    public void del( int id) throws SQLException {
        if (id == -1) {
            return;
        }
        Statement st = con.createStatement();
        st.executeUpdate("DELETE FROM " + tableName + " WHERE service_id=" + id);
        st.close();
        serviseId = -1;
        
    }
    
        public void add() throws SQLException {
            if (serviseId != -1) {
                return;
            }

            Statement st = con.createStatement();
            st.executeUpdate("INSERT INTO " + tableName + " (name) VALUES('" + name + "')");
            st.close();
    }
        
        public void update() throws SQLException {
        if (serviseId == -1) {
            return;
        }
        Statement st = con.createStatement();
        String qu = "UPDATE " + tableName + " SET name='" + name + "' WHERE service_id=" + serviseId;
        st.executeUpdate(qu);
        st.close();
    }
        
        public void save() throws SQLException {
        if (serviseId == -1) {
            add();
        } else {
            update();
        }
    }
}
