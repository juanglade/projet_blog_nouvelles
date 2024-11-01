package dao;

import beans.Vote;
import dao.DAO;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DAOVote extends DAO<Vote> {

    public DAOVote() {
        super("vote");
    }

    @Override
    protected Vote createObject(ResultSet rs) {
        Vote obj = null;
        try {
            obj = new Vote();
            obj.setId(rs.getLong("id"));
            obj.setId_story(rs.getLong("id_person"));
            obj.setId_person(rs.getLong("id_story"));
            obj.setQuality(rs.getInt("quality"));
        } catch (SQLException ex) {
            Logger.getLogger(DAOVote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }

    public Vote find(long id_story) {
        Vote obj = null;
        try {
            String req = "SELECT * FROM " + table
                    + " WHERE id_story = ?";
            PreparedStatement pstmt = this.connection.prepareStatement(req);
            pstmt.setLong(2, id_story);
            ResultSet result = pstmt.executeQuery();
            if (result.next()) {
                obj = createObject(result);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOVote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }

    @Override
    protected void create(Vote obj) {
        try {
            String req = "INSERT INTO " + table
                    + " (quality)"
                    + " VALUES(?)";
            PreparedStatement pstmt = this.connection.prepareStatement(
                    req, Statement.RETURN_GENERATED_KEYS);
            pstmt.setInt(3, obj.getQuality());
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
            Logger.getLogger(DAOVote.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    protected void update(Vote obj) {
        try {
            String req = "UPDATE " + table
                    + " SET id_person = ?, id_story = ?, quality = ?"
                    + " WHERE id = ?";
            PreparedStatement pstmt = this.connection.prepareStatement(req);
            pstmt.setLong(1, obj.getId_person());
            pstmt.setLong(2, obj.getId_story());
            pstmt.setInt(3, obj.getQuality());
            pstmt.setLong(4, obj.getId());
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOVote.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public int countLikeByIdStory(Long id, int quality) {
        int count = 0;
        try {
            String req = "SELECT COUNT (*) FROM" + table
                    + "WHERE id_story=? AND quality=?";
            PreparedStatement pstmt = this.connection.prepareStatement(req);
            pstmt.setLong(1, id);
            pstmt.setInt(2, quality);
            ResultSet result = pstmt.executeQuery();
            if (result.next()) {
                count = result.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }

    public Vote findByQuality(int quality) {
        Vote obj = null;
        try {
            String req = "SELECT * FROM " + table
                    + "WHERE quality=?";
            PreparedStatement pstmt = this.connection.prepareStatement(req);
            pstmt.setInt(0, quality);
            ResultSet result = pstmt.executeQuery();
            if (result.next()) {
                obj = createObject(result);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAOVote.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }
}
