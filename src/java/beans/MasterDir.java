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
public class MasterDir {
    
     private String findStr;
    private ArrayList<Master> masters;

    public MasterDir() {
        this.findStr = "";
        masters = new ArrayList<>();
    }

    public int getStudsCount() {
        return masters.size();
    }

    public Master[] getStuds() {
        Master[] res = new Master[masters.size()];
        masters.toArray(res);
        return res;
    }

    public Master getmMasters(int i) {
        if (i < 0 || i >= masters.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        return masters.get(i);
    }

    public void setMaster(int i, Master ob) {
        if (i < 0 || i >= masters.size()) {
            throw new ArrayIndexOutOfBoundsException();
        }
        masters.set(i, ob);
    }

    public void fill(DataSource ds) throws SQLException {
        masters.clear();
        Connection con = ds.getConnection();
        Statement st = con.createStatement();
        String qu = "SELECT master_id, age, first_name, last_name FROM " + Master.tableName + " WHERE last_name LIKE '%" + findStr + "%'";
        ResultSet rs = st.executeQuery(qu);
        while (rs.next()) {
            masters.add(new Master(rs.getInt("master_id"), rs.getString("location"), rs.getString("f_name"), rs.getString("l_name")));
        }
        rs.close();
        st.close();
        con.close();

    }

    public String getFindStr() {
        return findStr;
    }

    public void setFindStr(String findStr) {
        this.findStr = findStr;
    }

    
}
