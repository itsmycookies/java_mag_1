/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import javax.sql.DataSource;

/**
 *
 * @author Andrey Belov
 */
public class ServiceDir {
    
    private String findStr;
    private ArrayList <Service> services;
    
    public ServiceDir(){
        findStr = "";
        services = new ArrayList<>();
    }
    
    public String getFindStr() {
        return findStr;
    }
    
     public void setFindStr(String findStr) {
        this.findStr = findStr;
    }
     
     public int getServicesCount() {
        return services.size();
    }
    
    public Service[] getServices() {
        Service[] res = new Service[services.size()];
        services.toArray(res);
        return res;
    } 
    
     public Service getServices(int i) {
        if (i < 0 || i >= services.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return services.get(i);
    }
    
     public void getServices(int i, Service ob) {
        if (i < 0 || i >= services.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        services.set(i, ob);
    }
     
     public void fill(DataSource ds) throws SQLException {
        services.clear();
        Connection con = ds.getConnection();
        Statement st = con.createStatement();
        String qu = "SELECT service_id, name FROM " + Service.tableName + " WHERE name LIKE '%" + findStr + "%'";
        ResultSet rs = st.executeQuery(qu);
        while (rs.next()) {
            services.add(new Service(rs.getInt("service_id"), rs.getString("name")));
        }
        rs.close();
        st.close();
        con.close();

    }
     }