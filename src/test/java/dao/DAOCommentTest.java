/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/UnitTests/JUnit5TestClass.java to edit this template
 */
package dao;

//import beans.Comment;
//import java.time.LocalDate;
//import org.junit.jupiter.api.Test;
//import static org.junit.jupiter.api.Assertions.*;
//import org.junit.jupiter.api.BeforeAll;
//
///**
// *
// * @author Florine Pérabout
// */
//public class DAOCommentTest {
//    int size;
//    DAOComment dao;
//
//    public DAOCommentTest() {
//        dao = DAOFactory.getDAOComment();
//    }
//
//    @BeforeAll
//    public static void setUpClass() {
//        System.out.println("Test classe DAOComment");
//    }
//
//    @Test
//    public void testAllCrudMethods() {
//        //Test méthode count
//        assertDoesNotThrow(() -> size = dao.count(), "Pas d'exception pour compter les enregistrements");
//        //Test méthode all
//        assertDoesNotThrow(() -> dao.all(), "Pas d'exception pour récupérer tous les enregistrements");
//        assertEquals(size, dao.all().size());
//        //test méthode find
//        assertDoesNotThrow(() -> dao.find(1L), "Pas d'exception pour trouver un enregistrement existant");
//        assertNotNull(dao.find(1L), "Trouver le 1er enregistrement");
//        assertNull(dao.find(0L), "Trouver un enregistrement inexistant doit provoquer une erreur");
//        //Test méthode create
//        Comment obj = new Comment();
//        obj.setContent("test");
//        obj.setStatus(1);
//        obj.setTitle("test");
//        obj.setCreated(LocalDate.now());
//        assertDoesNotThrow(() -> dao.create(obj), "Pas d'exception pour créer un enregistrement");
//        Long id = obj.getId();
//        assertNotNull(id, "Objet créé a un id");
//        assertEquals(size + 1, dao.count(), "Augmentation de la taille de la table");
//        assertNotNull(obj.getId(), "Trouver le nouvel enregistrement");
//        //test de la méthode update
//        obj.setTitle("Bzziieng");
//        assertDoesNotThrow(() -> dao.update(obj), "Pas d'exception pour mettre à jour un enregistrement existant");
//        assertEquals("Bzziieng", dao.find(obj.getId()).getTitle(), "modification persistée");
//        //test de la méthode delete
//        assertDoesNotThrow(() -> dao.delete(obj), "Suppression d'un enregistrement existant");
//        assertNull(dao.find(id), "enregistrement supprimé introuvable");
//    }
//
//    @Test
//    public void testIsNew() {
//        System.out.println("testIsNew DAOComment");
//        Comment comment = new Comment();
//        assertTrue(DAOFactory.getDAOComment().isNew(comment));
//        comment.setId(1L);
//        assertFalse(DAOFactory.getDAOComment().isNew(comment));
//    }
//}
