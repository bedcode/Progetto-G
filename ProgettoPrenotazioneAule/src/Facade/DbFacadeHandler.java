/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Facade;

import PrenotareAula.Campus;
import PrenotareAula.Classroom;
import PrenotareAula.Requirements;
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

    private static DbFacadeHandler instance = new DbFacadeHandler();

    public static DbFacadeHandler getInstance() {
        return instance;
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

            java.sql.Connection conn;

            conn = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7112944?user=sql7112944&password=QavZjtyIJw");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from Classroom");
            while (rs.next()) {
                Requirements re = new Requirements(rs.getInt(2), true, true, true, null);
                Classroom cl = new Classroom(rs.getString(1), re);
                classi.add(cl);
            }
            rs.close();

            stmt.close();
            conn.close();
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

            java.sql.Connection conn;

            conn = DriverManager.getConnection("jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7112944?user=sql7112944&password=QavZjtyIJw");
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery("SELECT * from Supervisor");
            while (rs.next()) {
                Supervisor s=new Supervisor(rs.getString(1));
                Account.getInstance().addSuperAccount(s);
                
            }
            rs.close();
            ResultSet rs2 = stmt.executeQuery("SELECT * from Teacher");
            while (rs2.next()) {
                Teacher t=new Teacher(rs2.getString(1));
                Account.getInstance().addTeacherAccount(t);
            }
            rs.close();

            stmt.close();
            conn.close();
        } catch (SQLException E) {
            System.out.println("SQLException: " + E.getMessage());
            System.out.println("SQLState:     " + E.getSQLState());
            System.out.println("VendorError:  " + E.getErrorCode());
        }

        return accounts;

    }


}
