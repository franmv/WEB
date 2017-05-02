/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import Database.Database;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Jepa
 */
public class Candidate {

    private int id = -1;
    private String firstName;
    private String lastName;
    private String address;
    private String phone;
    private String email;
    private String title;
    private String university;
    private String certificates;
    private int expectatives;
    private String previous;
    private int interview;
    
    public Candidate(int id, String firstName, String lastName, String address, String phone, 
            String email, String title, String university, String certificates,
            int expectatives, String previous, int interview) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.title = title;
        this.university = university;
        this.certificates = certificates;
        this.expectatives = expectatives;
        this.previous = previous;
        this.interview = interview;
    }
    public static Candidate getCandidate(int id){
        Candidate candidate = null;
        ResultSet rs = null;
        try {
            String query = "SELECT * FROM Candidate WHERE id" + Integer.toString(id);
            rs = Database.query(query, id);
            if (rs.next()){
                candidate = new Candidate(
                        rs.getInt("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("address"), 
                        rs.getString("phone"), 
                        rs.getString("email"),
                        rs.getString("title"),
                        rs.getString("university"),
                        rs.getString("certificates"),
                        rs.getInt("expectatives"),
                        rs.getString("previous"),
                        rs.getInt("interview")
                );
            }
        } catch (SQLException ex) {
            Logger.getLogger(Candidate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return candidate;
    }
    
    public boolean exists() throws SQLException{
        ResultSet rs = null;
        try {
            String query = "SELECT * FROM Candidate WHERE id" + Integer.toString(this.id);
            rs = Database.query(query, this.id);
        } catch (SQLException ex) {
            Logger.getLogger(Candidate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return rs.first();
    }
    
    public static List<Candidate> all(){
        List<Candidate> candidates = new ArrayList<>();
        try {
            String query = "SELECT * FROM Candidate";
            ResultSet rs = Database.query(query);
            while(rs.next()) {
                Candidate candidate = new Candidate(
                        rs.getInt("id"),
                        rs.getString("firstName"),
                        rs.getString("lastName"),
                        rs.getString("address"), 
                        rs.getString("phone"), 
                        rs.getString("email"),
                        rs.getString("title"),
                        rs.getString("university"),
                        rs.getString("certificates"),
                        rs.getInt("expectatives"),
                        rs.getString("previous"),
                        rs.getInt("interview")
                );
                candidates.add(candidate);
            }
        } catch (SQLException ex) {
            Logger.getLogger(Candidate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return candidates;
    }
    
    public boolean save() throws SQLException{
        String query;
        query = "INSERT INTO Candidate (id, firstName, lastName, address, phone,"
            + " email, title, university, certificates, expectatives, previous, interview)" +
        "VALUES ('%d', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%s', '%d', '%s', '%d')";  
        try {
        Database.update(query, this.id, this.firstName, this.lastName, this.address, 
            this.phone, this.email, this.title, this.university, this.certificates, this.expectatives,
            this.previous, this.interview);
        } catch (SQLException ex) {
            Logger.getLogger(Candidate.class.getName()).log(Level.SEVERE, null, ex);
        }  
        return true;
    }
    
    public boolean delete(){
        try {
            String query = "DELETE FROM Candidate WHERE id =" + Integer.toString(this.id);
            Database.update(query, id);
            return true;
        } catch (SQLException ex) {
            Logger.getLogger(Candidate.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false; 
    }

    /**
     * @return the id
     */
    public int getId() {
        return id;
    }

    /**
     * @param id the id to set
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * @return the firstName
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * @param firstName the firstName to set
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * @return the lastName
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * @param lastName the lastName to set
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * @return the address
     */
    public String getAddress() {
        return address;
    }

    /**
     * @param address the address to set
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * @return the phone
     */
    public String getPhone() {
        return phone;
    }

    /**
     * @param phone the phone to set
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * @return the email
     */
    public String getEmail() {
        return email;
    }

    /**
     * @param email the email to set
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * @param title the title to set
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * @return the university
     */
    public String getUniversity() {
        return university;
    }

    /**
     * @param university the university to set
     */
    public void setUniversity(String university) {
        this.university = university;
    }

    /**
     * @return the certificates
     */
    public String getCertificates() {
        return certificates;
    }

    /**
     * @param certificates the certificates to set
     */
    public void setCertificates(String certificates) {
        this.certificates = certificates;
    }

    /**
     * @return the expectatives
     */
    public int getExpectatives() {
        return expectatives;
    }

    /**
     * @param expectatives the expectatives to set
     */
    public void setExpectatives(int expectatives) {
        this.expectatives = expectatives;
    }

    /**
     * @return the previous
     */
    public String getPrevious() {
        return previous;
    }

    /**
     * @param previous the previous to set
     */
    public void setPrevious(String previous) {
        this.previous = previous;
    }

    /**
     * @return the interview
     */
    public int getInterview() {
        return interview;
    }

    /**
     * @param interview the interview to set
     */
    public void setInterview(int interview) {
        this.interview = interview;
    }
    
}

