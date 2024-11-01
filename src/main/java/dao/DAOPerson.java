/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import beans.Person;
import java.sql.ResultSet;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Caroline Casteras
 */
public class DAOPerson extends DAO<Person>{
    
    public DAOPerson() {
        super("person");
    }
    
    @Override
    protected Person createObject(ResultSet rs){
        Person obj = null;
        try {
            obj = new Person();
            obj.setId(rs.getLong("id"));
            obj.setLogin(rs.getString("login"));
            obj.setName(rs.getString("name"));
            obj.setPwd(rs.getString("pwd"));
            obj.setStatus(rs.getInt("status"));
        } catch (SQLException ex) {
            Logger.getLogger(DAOPerson.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }
    
    @Override
    protected void create(Person obj){
        try {
            String req = "INSERT INTO " + table
                + " (login, name, pwd, status)"
                + " VALUES (?, ?, ?, ?)";
            PreparedStatement pstmt = this.connection.prepareStatement(
                    req, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, obj.getLogin());
            pstmt.setString(2, obj.getName());
            pstmt.setString(3, obj.getPwd());
            pstmt.setInt(4, obj.getStatus());
            pstmt.executeUpdate();
            java.sql.ResultSet rs = pstmt.getGeneratedKeys();
            long last_id;
            if (rs.next()) { 
                last_id = rs.getLong(1);
                obj.setId(last_id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOPerson.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    protected void update(Person obj){
        try {
            String req = "UPDATE " + table
                + " SET login=?, name=?, pwd=?, status=?"
                + " WHERE id=?";
            PreparedStatement pstmt = this.connection.prepareStatement(req);
            pstmt.setString(1, obj.getLogin());
            pstmt.setString(2, obj.getName());
            pstmt.setString(3, obj.getPwd());
            pstmt.setInt(4, obj.getStatus());
            pstmt.setLong(5, obj.getId());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOPerson.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void updateStatus(Long id, int newStatus) {
        try {
            String req = "UPDATE " + table + " SET status=? WHERE id=?";
            PreparedStatement pstmt = this.connection.prepareStatement(req);
            pstmt.setInt(1, newStatus);
            pstmt.setLong(2, id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOPerson.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public Person findByLogin(String login){
        Person obj = null;
        try {
            String req = "SELECT * FROM " + table
                + " WHERE login=?";
            PreparedStatement pstmt = this.connection.prepareStatement(req);
            pstmt.setString(1, login);
            ResultSet result = pstmt.executeQuery();
            if (result.next()) {
                obj = createObject(result);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOPerson.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }
}
