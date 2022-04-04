/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package beans;

import static beans.Service.tableName;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import javax.sql.DataSource;
        
/**
 *
 * @author Andrey Belov
 */
public class Rating {
    
public static final String tableName = "rating";

    private int ratingId;
    private Master master;
    private Service service;
    private LocalDate ratingDate;
    private double mark;

    public Rating(int ratingId, int masterId, int serviceId, LocalDate ratingDate, double mark) throws SQLException {
        this.ratingId = ratingId;
        master = new Master();
        master.load(masterId);
        service = new Service();
        service.load(serviceId);
        this.ratingDate = ratingDate;
        this.mark = mark;
    }

    public Rating() {
        ratingId = -1;
        master = new Master();
        service = new Service();
        ratingDate = LocalDate.now();
    }

    public Master getMaster() {
        return master;
    }

    public void setMaster(Master master) {
        this.master = master;
    }

    public Service getService() {
        return service;
    }

    public void setService(Service service) {
        this.service = service;
    }

    
    public int getRatingId() {
        return ratingId;
    }

    public void setRatingId(int ratingId) {
        this.ratingId = ratingId;
    }


    public LocalDate getRatingDate() {
        return ratingDate;
    }

    public void setRatingDate(LocalDate ratingDate) {
        this.ratingDate = ratingDate;
    }

    public double getMark() {
        return mark;
    }

    public void setMark(double mark) {
        this.mark = mark;
    }

    public void load(DataSource ds, int id) throws SQLException {
        Connection con = ds.getConnection();
        Statement st = con.createStatement();
        String qu = "SELECT  master_id, service_id, rating_date, mark  FROM " + tableName + " WHERE rating_id = " + id;
        ResultSet rs = st.executeQuery(qu);
        if (rs.next()) {
            ratingId = id;
            service.load(rs.getInt("service_id")); 
            master.load( rs.getInt("master_id"));
            Date dt = rs.getDate("rating_date");
            ratingDate = dt.toLocalDate();
            mark = rs.getDouble("mark");
            return;
        } else {
            ratingId = -1;
            ratingDate = LocalDate.now();
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
        st.executeUpdate("DELETE FROM " + tableName + " WHERE rating_id=" + id);
        st.close();
        con.close();
    }

    public void add(DataSource ds) throws SQLException {
        if (ratingId != -1) {
            return;
        }
        Connection con = ds.getConnection();
        Statement st = con.createStatement();
        String qu = "INSERT INTO " + tableName + " (master_id, service_id, rating_date, mark) "+
                String.format("VALUES( %d, %d, '%s', %.2f)", master.getMasterId(), service.getServiceId(), 
                    ratingDate.format(DateTimeFormatter.ISO_LOCAL_DATE), mark);
        System.out.println("--- "+qu);
        st.executeUpdate(qu);
        st.close();
    }

    public void update(DataSource ds) throws SQLException {
        if (ratingId == -1) {
            return;
        }
        Connection con = ds.getConnection();
        Statement st = con.createStatement();
        String qu = String.format( 
                "UPDATE %s SET master_id = %d , service_id=%d, rating_date='%s', mark=%.2f WHERE rating_id=%d",  
                tableName,master.getMasterId(), service.getServiceId(), ratingDate.format(DateTimeFormatter.ISO_LOCAL_DATE), mark);
        System.out.println("--- "+qu);
        st.executeUpdate(qu);
        st.close();
    }

    public void save(DataSource ds) throws SQLException {
        if (ratingId == -1) {
            add(ds);
        } else {
            update(ds);
        }
    }

}