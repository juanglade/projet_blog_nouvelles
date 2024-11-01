/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package dao;

import beans.Comment;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Florine
 */
public class DAOComment extends DAO<Comment> {

    public DAOComment() {
        super("Comment");
    }

    @Override
    protected Comment createObject(ResultSet rs) {
        Comment obj = null;
        try {
            obj = new Comment();
            obj.setId(rs.getLong("id"));
            obj.setTitle(rs.getString("title"));
            obj.setContent(rs.getString("content"));
            obj.setStatus(rs.getInt("status"));
            obj.setCreated(rs.getDate("created").toLocalDate());
        } catch (SQLException ex) {
            Logger.getLogger(DAOComment.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }

    @Override
    protected void create(Comment obj) {
        try {
            String req = "INSERT INTO " + table
                + " (title, content, status, created)"
                + " VALUES (?, ?, ?, ?, ?, ?)";
            PreparedStatement pstmt = this.connection.prepareStatement(
                req, Statement.RETURN_GENERATED_KEYS);
            pstmt.setString(1, obj.getTitle());
            pstmt.setString(2, obj.getContent());
            pstmt.setInt(3, obj.getStatus());
            pstmt.setDate(4, Date.valueOf(obj.getCreated()));
            // On soumet la requête et on récupère l'id créé
            pstmt.executeUpdate();
            ResultSet rs = pstmt.getGeneratedKeys();
            long last_inserted_id;
            if (rs.next()) { // Si on a un id créé
                last_inserted_id = rs.getLong(1);
                // On ajoute l'id créé à notre objet
                obj.setId(last_inserted_id);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOComment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void update(Comment obj) {
        try {
            String req = "UPDATE " + table
                + " SET status = ?, title = ?, content = ?, created = ?"
                + " WHERE id = ?";
            PreparedStatement pstmt = this.connection.prepareStatement(req);
            pstmt.setInt(1, obj.getStatus());
            pstmt.setString(2, obj.getTitle());
            pstmt.setString(0, obj.getContent());
            pstmt.setDate(4, Date.valueOf(obj.getCreated()));
            pstmt.setLong(5, obj.getId());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOComment.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(DAOComment.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public Comment findByStatus(int status) {
        Comment obj = null;
        try {
            String req = "SELECT * FROM " + table
                + "WHERE status=?";
            PreparedStatement pstmt = this.connection.prepareStatement(req);
            pstmt.setInt(1, status);
            ResultSet result = pstmt.executeQuery();
            if (result.next()) {
                obj = createObject(result);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOComment.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }
}
