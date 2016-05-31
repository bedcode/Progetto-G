/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import PrenotareAula.Campus;
import PrenotareAula.Classroom;
import PrenotareAula.Requirements;
import PrenotareAula.Reservation;
import Utenti.Account;
import Utenti.Supervisor;
import Utenti.Teacher;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Federico
 */
public class DbFacadeHandler {

    private static final DbFacadeHandler instance = new DbFacadeHandler();
    java.sql.Connection conn;

    public static DbFacadeHandler getInstance() {
        return instance;
    }

    private DbFacadeHandler() {
        try {
            conn = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7112944?user=sql7112944&password=QavZjtyIJw");
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public List<Classroom> obtainClassroom() {

        List<Classroom> classi = new ArrayList();

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

        } catch (Exception E) {
            System.err.println("Non trovo il driver da caricare.");
            E.printStackTrace();
        }

        try {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from Classroom");
            while (rs.next()) {
                Requirements re = new Requirements(rs.getInt(2), true, true, true, null);
                Classroom cl = new Classroom(rs.getString(1), re);
                classi.add(cl);
            }
            rs.close();

            stmt.close();
        } catch (SQLException E) {
            System.out.println("SQLException: " + E.getMessage());
            System.out.println("SQLState:     " + E.getSQLState());
            System.out.println("VendorError:  " + E.getErrorCode());
        }

        return classi;

    }

    public List<Account> obtainAccount() {

        List<Account> accounts = new ArrayList();

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

        } catch (Exception E) {
            System.err.println("Non trovo il driver da caricare.");
            E.printStackTrace();
        }

        try {

            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from Supervisor");
            while (rs.next()) {
                Supervisor s = new Supervisor(rs.getString(1), rs.getString(4));
                Account.getInstance().addSuperAccount(s);

            }
            rs.close();
            ResultSet rs2 = stmt.executeQuery("SELECT * from Teacher");
            while (rs2.next()) {
                Teacher t = new Teacher(rs2.getString(1), rs2.getString(4));
                Account.getInstance().addTeacherAccount(t);
            }
            rs2.close();

            stmt.close();
        } catch (SQLException E) {
            System.out.println("SQLException: " + E.getMessage());
            System.out.println("SQLState:     " + E.getSQLState());
            System.out.println("VendorError:  " + E.getErrorCode());
        }

        return accounts;

    }

    public void UpdateReservation() {

        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();

        } catch (Exception E) {
            System.err.println("Non trovo il driver da caricare.");
            E.printStackTrace();
        }

        try {

            Statement stmt = conn.createStatement();
            String query = "SELECT * from Reservation";
            ResultSet rs = stmt.executeQuery(query);
            for (Classroom cl : Campus.getInstance().getClassi()) {
                rs.beforeFirst();
                while (rs.next()) {
                    if (cl.getName().equals(rs.getString(6))) {
                        cl.getResReg().makeReservation(rs.getDate(2), rs.getInt(3), rs.getInt(4));
                    }
                }
            }
            rs.close();
            stmt.close();
        } catch (SQLException E) {
            System.out.println("SQLException: " + E.getMessage());
            System.out.println("SQLState:     " + E.getSQLState());
            System.out.println("VendorError:  " + E.getErrorCode());
        }

    }

    public void insertAccount(Teacher t) {
        try {
            Class.forName("com.mysql.jdbc.Driver").newInstance();
        } catch (Exception E) {
            System.err.println("Non trovo il driver da caricare.");
            E.printStackTrace();
        }
        try {
            Statement stmt = conn.createStatement();
            String query = "insert into Teacher values('" + t.getEmail() + "', '" + t.getName() + "', '" + t.getSurname() + "', '" + t.getPassword() + "')";
            stmt.executeUpdate(query);
        } catch (SQLException E) {
            System.out.println("SQLException: " + E.getMessage());
            System.out.println("SQLState:     " + E.getSQLState());
            System.out.println("VendorError:  " + E.getErrorCode());
        }
    }

}
