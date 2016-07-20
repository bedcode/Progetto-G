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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 * This class handles connection and data exchange with a online database.
 * 
 * @author Federico
 */
public class DbFacadeHandler {

    private static final DbFacadeHandler instance = new DbFacadeHandler();
    private final String URL = "jdbc:mysql://sql7.freemysqlhosting.net:3306/sql7112944?user=sql7112944&password=QavZjtyIJw";
    private Connection conn;
    private Statement stmt;

    public static DbFacadeHandler getInstance() {
        return instance;
    }

    private DbFacadeHandler() {
        try {
            Class.forName("com.mysql.jdbc.Driver");            
        } catch(ClassNotFoundException e){
            System.err.println("Unable to load jdbc driver.");
            e.printStackTrace();
            System.exit(-1);
        }
    }
    
    /**
     * Open a connection to the database
     * @return The statement if the connection was open succesfully
     * @throws java.sql.SQLException 
     */
    public Statement openConnection () throws SQLException{
        DriverManager.setLoginTimeout(10);
        conn = DriverManager.getConnection(URL);
        stmt = conn.createStatement();
        return stmt;                
    }    

    /**
     * Method to close the connection to database.
     */
    public void closeConnection() {
        try {
            conn.close();
        } catch (SQLException ex) {
            System.out.println("SQLException: " + ex.getMessage());
        }
    }

    // methods which read informations from database
    
    /**
     * Method to read classrooms and their requirements from database.
     * 
     * @return a list of classrooms
     */
    public List<Classroom> obtainClassroom() {

        List<Classroom> classi = new ArrayList();        

        try {
            stmt = openConnection();
            ResultSet rs = stmt.executeQuery("select nome, capacita, blackboard, whiteboard, projector, laboratory from Classroom join Accessories A on nome=A.classroom");
            while (rs.next()) {
                Requirements re = new Requirements(rs.getInt(2), rs.getBoolean(3), rs.getBoolean(4), rs.getBoolean(5), rs.getString(6));
                Classroom cl = new Classroom(rs.getString(1), re);
                classi.add(cl);
            }
            rs.close();
            stmt.close();
            closeConnection();
        } catch (SQLException E) {
            System.err.println("Unable to retrieve classrooms from database.");
            System.err.println("SQLException: " + E.getMessage());
            System.err.println("SQLState:     " + E.getSQLState());
            System.err.println("VendorError:  " + E.getErrorCode());
            System.exit(-1);
        }

        return classi;
    }

    /**
     * Method to read teacher and supervisor accounts from database.
     * 
     * @return a list of accounts
     */
    public List<Account> obtainAccount() {

        List<Account> accounts = new ArrayList();        

        try {
            stmt = openConnection();
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
            closeConnection();
        } catch (SQLException E) {
            System.err.println("Unable to retrieve accounts from database.");
            System.err.println("SQLException: " + E.getMessage());
            System.err.println("SQLState:     " + E.getSQLState());
            System.err.println("VendorError:  " + E.getErrorCode());
            System.exit(-1);
        }

        return accounts;
    }

    /**
     * Method to read all the reservations from database.
     */
    public void updateReservation() {

        try {
            stmt = openConnection();
            String query = "SELECT * from Reservation";
            ResultSet rs = stmt.executeQuery(query);
            for (Classroom cl : Campus.getInstance().getClassi()) {
                rs.beforeFirst();
                while (rs.next()) {
                    if (cl.getName().equals(rs.getString(6))) {
                        cl.getResReg().makeReservation(rs.getInt(1), rs.getDate(2), rs.getInt(3), rs.getInt(4), rs.getString(5));
                    }
                }
            }
            rs.close();
            stmt.close();
            closeConnection();
        } catch (SQLException E) {
            System.err.println("Unable to retrieve reservations from database.");
            System.err.println("SQLException: " + E.getMessage());
            System.err.println("SQLState:     " + E.getSQLState());
            System.err.println("VendorError:  " + E.getErrorCode());
            System.exit(-1);
        }
    }
    
    /**
     * Method to read maximum id from database.
     */
    public void readId() {
        
        try {
            stmt = openConnection();
            String query = "select max(id) from Reservation";
            ResultSet rs = stmt.executeQuery(query);
            while (rs.next()) {
                int id = rs.getInt(1);                
                Reservation.setNextId(id);
            }
            rs.close();
            stmt.close();
            closeConnection();
        } catch (SQLException E) {
            System.err.println("Unable to read the maximum ID from database.");
            System.err.println("SQLException: " + E.getMessage());
            System.err.println("SQLState:     " + E.getSQLState());
            System.err.println("VendorError:  " + E.getErrorCode());
            System.exit(-1);
        }
    }

    // methods which write, update and delete informations in database
    
    /**
     * This method allows the supervisor to add teacher account in the database.
     * 
     * @param t a teacher instance
     */
    public void insertTeacherAccount(Teacher t) {
        
        try {
            stmt = openConnection();
            String query = "insert into Teacher values('" + t.getEmail() + "', '" + t.getName() + "', '" + t.getSurname() + "', '" + t.getPassword() + "')";
            stmt.executeUpdate(query);
            stmt.close();
            closeConnection();
        } catch (SQLException E) {
            System.err.println("Unable to insert new account.");
            System.err.println("SQLException: " + E.getMessage());
            System.err.println("SQLState:     " + E.getSQLState());
            System.err.println("VendorError:  " + E.getErrorCode());
        }
    }

    /**
     * This method allows the supervisor to remove teacher account from database.
     * 
     * @param email email of a teacher
     */
    public void deleteTeacherAccount(String email) {
       
        try {
            stmt = openConnection();
            String query = "delete from Teacher where mail = '" + email + "'";
            stmt.executeUpdate(query);
            stmt.close();
            closeConnection();
        } catch (SQLException E) {
            System.err.println("Unable to delete account.");
            System.err.println("SQLException: " + E.getMessage());
            System.err.println("SQLState:     " + E.getSQLState());
            System.err.println("VendorError:  " + E.getErrorCode());
        }
    }

    /**
     * This method allows a user to change his password.
     * 
     * @param email email of a user
     * @param newPassword new password of a user
     * @param user type of a user: 0 represents teacher, 1 represents supervisor
     */
    public void changePassword(String email, String newPassword, int user) {
        
        String query = null;
        try {
            stmt = openConnection();
            if (user == 0) {
                query = "update Teacher set passw = '" + newPassword + "' where mail = '" + email + "'";
            }
            if (user == 1) {
                query = "update Supervisor set passw = '" + newPassword + "' where mail = '" + email + "'";
            }
            stmt.executeUpdate(query);
            stmt.close();
            closeConnection();
        } catch (SQLException E) {
            System.err.println("Unable to change password.");
            System.err.println("SQLException: " + E.getMessage());
            System.err.println("SQLState:     " + E.getSQLState());
            System.err.println("VendorError:  " + E.getErrorCode());
        }
    }

    /**
     * Method to write new reservations in the database.
     * 
     * @param r a reservation
     * @param cl classroom where you book
     */
    public void writeReservation(Reservation r, String cl) {
       
        try {
            stmt = openConnection();
            String query = "INSERT into Reservation values('" + r.getId() + "', '" + r.getDBDate() + "', '" + r.getStartHour() + "', '" + r.getEndHour() + "', '" + r.getDescription() + "', '" + cl + "', null, null)";
            stmt.executeUpdate(query);
            stmt.close();
            closeConnection();
        } catch (SQLException E) {
            System.err.println("Unable to write reservation.");
            System.err.println("SQLException: " + E.getMessage());
            System.err.println("SQLState:     " + E.getSQLState());
            System.err.println("VendorError:  " + E.getErrorCode());
        }
    }

    /**
     * Method to delete a reservation, identified by its id.
     * 
     * @param id id a reservation
     */
    public void deleteReservation(int id) {
        
        try {
            stmt = openConnection();
            String query = "delete from Reservation where id = " + id;
            stmt.executeUpdate(query);
            stmt.close();
            closeConnection();
        } catch (SQLException E) {
            System.err.println("Unable to delete reservation.");
            System.err.println("SQLException: " + E.getMessage());
            System.err.println("SQLState:     " + E.getSQLState());
            System.err.println("VendorError:  " + E.getErrorCode());
        }
    }
}
