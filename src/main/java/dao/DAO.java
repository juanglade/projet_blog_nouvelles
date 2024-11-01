package dao;

import interfaces.Crudable;
import interfaces.Identifiable;
import java.sql.ResultSet;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;


/**
 *
 * @author stag
 * @param <T>
 */
public abstract class DAO<T extends Identifiable> implements Crudable<T> {

    protected Connection connection = MariaDbConnection.getInstance();
    protected String table;

    public DAO(String table) {
        this.table = table;
    }

    //indique si un objet est nouveau ou déjà existant en DB
    public boolean isNew(T obj) {
        return (obj.getId() == null);
    }

    //capacité de conservation des données de manière durable dans la DB
    @Override
    public void persist(T obj) {
        if (isNew(obj)) {
            create(obj);
        } else {
            update(obj);
        }
    }

    protected abstract void update(T obj);
    //fabrique du bean à partir d'un enregistrement de la DB
    protected abstract T createObject(ResultSet rs);
    protected abstract void create(T obj);

    @Override
    public T find(Long id) {
        T obj = null;
        try {
            String req = "SELECT * FROM " + table + " WHERE id = ?";
            PreparedStatement pstmt = this.connection.prepareStatement(req);
            pstmt.setLong(1, id);
            ResultSet result = pstmt.executeQuery();
            if (result.next()) {
                obj = createObject(result);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return obj;
    }

    @Override
    public void delete(T obj) {
        if (obj.getId() == null) {
            return;
        }
        delete(obj.getId());
        obj.setId(null);
    }

    public void delete(Long id) {
        try {
            String req = "DELETE FROM " + table + " WHERE id = ?";
            PreparedStatement pstmt = this.connection.prepareStatement(req);
            pstmt.setLong(1, id);
            pstmt.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DAOPerson.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    //retourne tous les enregistrements de la table
    public Collection<T> all() {
        ArrayList<T> list = new ArrayList<>();
        try {
            String req = "SELECT * FROM " + table;
            PreparedStatement pstmt = this.connection.prepareStatement(req);
            ResultSet result = pstmt.executeQuery();
            while (result.next()) {
                list.add(createObject(result));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return list;
    }

    //retourne la taille de la table
    public int count() {
        int count = 0;
        try {
            String req = "SELECT COUNT(*) FROM " + table;
            PreparedStatement pstmt = this.connection.prepareStatement(req);
            ResultSet result = pstmt.executeQuery();
            if (result.next()) {
                count = result.getInt(1);
            }
        } catch (SQLException ex) {
            Logger.getLogger(DAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return count;
    }
}